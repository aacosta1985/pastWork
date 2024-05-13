from bs4 import BeautifulSoup
from urllib.parse import urljoin
from flask import url_for, Blueprint, Response, send_from_directory
import logging
import requests
from global_vars import get_global, set_global

htmlutils_app = Blueprint('htmlutils_app', __name__)
logger = logging.getLogger('htmlutils_app')
logger.setLevel(logging.INFO)
handler = logging.StreamHandler()
handler.setLevel(logging.INFO)
logger.addHandler(handler)

def parse_html_with_styles(response, url, namespace):
    soup = BeautifulSoup(response.content, 'html.parser')
    # Rewrite URLs of links and scripts
    for link in soup.find_all('link'):
        if link.get('href'):
            href = link.get('href').replace("/static/", "/templates")
            link['href'] = url_for('htmlutils_app.serve_js', filepath = href)

    for script in soup.find_all('script'):
        if script.get('src'):
            src = script.get('src').replace("/static/", "/static/js")
            script['src'] = url_for('htmlutils_app.serve_js', filepath = src)

    # Rewrite URLs of images
    for img in soup.find_all('img'):
        if img.get('src'):
            src = img.get('src').replace("/static", "/static/images")
            img['src'] = url_for('htmlutils_app.serve_js', filepath = src)

    # Rewrite URLs of nav-links
    for link in soup.find_all('nav'):
        for a in link.find_all('a'):
            if a.get('href'):
                href = a.get('href')
                if href.startswith('/'):
                    a['href'] = url_for('htmlutils_app.get_spark_ui_path_tabs', namespace = namespace, path=href[1:])
                else:
                    a['href'] = url_for('htmlutils_app.get_spark_ui_path_tabs', namespace = namespace, path='/' + href)


    # Find the table you want to edit by its class or ID
    table = soup.find('table', {'id': 'active-table'})
    if table:
        # Find all the links in the tbody of the table and edit their href attribute
        for link in table.tbody.find_all('a'):
            link['href'] = f'/sparkui/{namespace}' + link['href']


    return soup

@htmlutils_app.route('/sparkui/<namespace>/<path:path>')
def get_spark_ui_path_tabs(namespace, path):
    base_url = get_global('base_url')
    full_url = urljoin(base_url, path)
    response = requests.get(full_url, stream=True)
    soup = parse_html_with_styles(response, full_url, namespace)
    soupstr = str(soup)
    return Response(soupstr, content_type=response.headers['content-type'])


@htmlutils_app.route('/static/<path:filepath>')
def serve_js(filepath):
    parts = filepath.split("/")
    filename = parts[-1]
    partial_path = "/".join(parts[:-1])
    logger.info(f"Serving {filename} from {partial_path}")
    return send_from_directory(f"/app/static/{partial_path}" , filename)