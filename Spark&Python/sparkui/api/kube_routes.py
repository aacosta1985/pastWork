from flask import Blueprint, make_response, render_template, Response, send_from_directory, redirect, url_for, request
import logging
from kubernetes import client, config
import subprocess
import os
import requests
from htmlutils import parse_html_with_styles, htmlutils_app
from urllib.parse import urljoin
from global_vars import get_global, set_global
from stream_config import dataloaders_namespaces, streaming_namespaces, cluster_nm, aws_reg

kube_bp = Blueprint('kube_bp', __name__, template_folder="../templates")
logger = logging.getLogger('kube_bp')
logger.setLevel(logging.INFO)
handler = logging.StreamHandler()
handler.setLevel(logging.INFO)
logger.addHandler(handler)


@kube_bp.route('/')
def get_namespaces():
    return render_template("namespaces_ui.html", dataloaders_namespaces = dataloaders_namespaces, streaming_namespaces=streaming_namespaces, cluster_name=cluster_nm, region=aws_reg)

@kube_bp.route('/sparkui/<namespace>')
def get_spark_ui(namespace):
    try:
        set_global('namespace', namespace)
        logger.info(f"SparkUI Namespace - {namespace}")
        # Get the pod running the Spark app
        pod_name = get_spark_app_pod_name(namespace)
        logger.info(f'Driver Pod - {pod_name}')

        # route to SparkUI on kubernetes cluster
        url = f'http://{pod_name}-svc.{namespace}.svc:4040'
        set_global('base_url', url)
        response = requests.get(url, stream=True)
        soup = parse_html_with_styles(response, url, namespace)

        return Response(str(soup), content_type=response.headers['content-type'])
    except ValueError as e:
        message, code = e.args
        logger.error(f"Exception while loading SparkUI: {e}\n")
        return render_template('error.html', err_status_code = code, error_message = message)


def init_k8s_config():
    v1 = get_global('v1')
    logger.info(f"==== V1 output before init {v1} =====")
    if not v1:
        cluster_name = os.environ.get('KUBERNETES_CLUSTER', '<EDITED OUT>')
        region = os.environ.get('AWS_REGION', 'us-west-2')
        if cluster_name and region:
            logger.info(f"==== SparkUI App for {cluster_name} in {region} =====")
            subprocess.run(['aws', 'eks', 'update-kubeconfig', '--name', cluster_name, '--region', region], check=True)
            config.load_kube_config()
            v1 = client.CoreV1Api()
            set_global('v1', v1)
            logger.info(f"==== V1 output {v1} =====")


def get_spark_app_pod_name(namespace):
    try:
        # Get the pods in the namespace
        v1 = get_global('v1')
        pods = [pod.metadata.name for pod in v1.list_namespaced_pod(namespace=namespace).items]
        # Find the pod running the Spark app
        driver_pods = [pod for pod in pods if 'driver' in pod]
        if len(driver_pods) > 0:
            return driver_pods[0]
        else:
            logger.error("Driver pod not found")
            raise ValueError(f"Driver Pod not found for namespace {namespace}. Check if the driver is running.", 404)
    except client.rest.ApiException as e:
        raise ValueError(f"Exception when calling CoreV1Api->list_namespaced_pod: {e.body}", e.status)

# def get_namespaces_eks(cluster_name):
#     try:
#         # Retrieve a list of all namespaces in the cluster
#         v1 = get_global('v1')
#         namespace_list = v1.list_namespace().items

#         # Render the HTML in your template
#         return render_template('namespaces.html', cluster = cluster_name, namespace_list=namespace_list)
#     except client.rest.ApiException as e:
#         logger.error(f"Exception while calling CoreV1Api->list_namespace: {e}\n")
#         data = {'message': f'{e.body}'}
#         response = make_response(jsonify(data), e.status)
#         return response

# @kube_bp.route('/sparkuipf/<namespace>')
# def port_forward_spark_ui(namespace):
#     global global_namespace
#     global base_url
#     global_namespace = namespace
#     # Get the pod running the Spark app
#     pod_name = get_spark_app_pod_name(namespace)
#     print(f'Driver Pod - {pod_name}')
#     # Define the kubectl command as a list of arguments
#     kubectl_cmd = ['kubectl', 'port-forward', '-n', namespace, pod_name, '4040:4040']
#
#     # Run the kubectl port-forward command as a subprocess
#     portforward_process = subprocess.Popen(kubectl_cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
#
#     # Wait for the port-forward process to start
#     while 'Forwarding from' not in portforward_process.stdout.readline().decode():
#         pass
#
#     # The port-forward process is now running and you can access the forwarded port locally
#     url = f'http://127.0.0.1:4040'
#     base_url = url
#     response = requests.get(url, stream=True)
#     soup = parse_html_with_styles(response, url, namespace)
#
#     return Response(str(soup), content_type=response.headers['content-type'])