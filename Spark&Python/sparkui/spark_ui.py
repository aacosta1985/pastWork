from flask import Flask, jsonify, url_for, Markup, render_template, request, redirect, make_response, Response, send_from_directory
from kubernetes import client, config
import requests
import subprocess
import socket
import logging
from urllib.parse import urljoin
from htmlutils import htmlutils_app
from api.spark_routes import sparkui_bp
from api.kube_routes import kube_bp, init_k8s_config

app = Flask(__name__)

app.register_blueprint(htmlutils_app)
app.register_blueprint(kube_bp)
app.register_blueprint(sparkui_bp)

init_k8s_config()

@app.route('/health')
def health_check():
    return "Healthy!"





