from flask import Blueprint, make_response, render_template, Response, send_from_directory, redirect, url_for, request
import logging
from kubernetes import client, config
import subprocess
import os
import requests
from htmlutils import parse_html_with_styles, htmlutils_app
from urllib.parse import urljoin
from global_vars import get_global, set_global

sparkui_bp = Blueprint('sparkui_bp', __name__)
logger = logging.getLogger('sparkui_bp')
logger.setLevel(logging.INFO)
handler = logging.StreamHandler()
handler.setLevel(logging.INFO)
logger.addHandler(handler)

@sparkui_bp.route('/api/v1/<path:path>')
def redirect_rest_api(path):
    return get_element_details_response(f'/api/v1/{path}')

@sparkui_bp.route('/jobs/<path:path>')
def redirect_job_details(path):
    return get_element_details_response(f'/jobs/{path}')

@sparkui_bp.route('/stages/<path:path>')
def redirect_stage_details(path):
    return get_element_details_response(f'/stages/{path}')

@sparkui_bp.route('/executors/<path:path>')
def redirect_executor_details(path):
    return get_element_details_response(f'/executors/{path}')

@sparkui_bp.route('/threadDump/<path:path>')
def redirect_threaddump_details(path):
    return get_element_details_response(f'/threadDump/{path}')

@sparkui_bp.route('/images/<path:filepath>')
def serve_images(filepath):
    return redirect(url_for('htmlutils_app.serve_js', filepath = f'images/{filepath}' ))

@sparkui_bp.route('/sparkui/<namespace>/StreamingQuery/statistics')
def redirect_streamingquery(namespace):
    # Get the value of the "run_id" query parameter
    base_url = get_global('base_url')
    logger.info(f"Base URL - {base_url}")

    run_id = request.args.get('id')
    streamingquery_url = f'{base_url}/StreamingQuery/statistics?id={run_id}'
    response = requests.get(streamingquery_url, stream=True)
    soup = parse_html_with_styles(response, streamingquery_url, namespace = namespace)

    return Response(str(soup), content_type=response.headers['content-type'])

def get_element_details_response(path):
    base_url = get_global('base_url')
    url = f'{base_url}{path}'
    id = request.args.get('id')
    attempt = request.args.get('attempt')
    executorId = request.args.get('executorId')
    if id:
        url = f'{url}?id={id}'

    if attempt:
        url = f'{url}&attempt={attempt}'

    if executorId:
        url = f'{url}?executorId={executorId}'

    logger.info(f'Url for details - {url}')
    response = requests.get(url, stream=True)
    namespace = get_global('namespace')
    soup = parse_html_with_styles(response, url, namespace = namespace)

    return Response(str(soup), content_type=response.headers['content-type'])


