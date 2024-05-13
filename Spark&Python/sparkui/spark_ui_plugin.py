#!/usr/bin/env python3

from pathlib import Path

def generate_deploy_file(<EDITED OUT>, spark_ui_app_docker_image):
    file = "<EDITED OUT>_sparkui_deploy.yml"

    print(f"Spark UI Plugin - Generating file: {file.absolute()} (Env: {os.getenv('<EDITED OUT>', 'n/a')})")

    print(f"Spark UI Plugin - Name: {name}")
    print(f"Spark UI Plugin - Env: {<EDITED OUT>}")
    print(f"Spark UI Plugin - Organization: {organization}")
    print(f"Spark UI Plugin - Docker - Image: {spark_ui_app_docker_image}")

    header = "# Generated by Spark UI Plugin. Please don't manually update"

    contents = f"""
{header}
# Documentation for EKS integration:
# <EDITED OUT>

eks_services:

  sparkui:

    name: "sparkui"

    image:
      full_path: {spark_ui_app_docker_image}

    environment: {<EDITED OUT>}

    args:

    container_port: 5000

    replicas: 1

    cpu: 3

    memory: 4G

    health_endpoint: /health

    readiness_delay_seconds: 120

    readiness_period_seconds: 300

    liveness_delay_seconds: 120

    liveness_period_seconds: 300

    has_lb: true

    lb_type: internet-facing

    host_name: <EDITED OUT>

    lb_path:

    security_groups:

    has_autoscaling: false

    tags:
      App: sparkui
      Cost-code: <EDITED OUT>
      Env: var.env
      Source: aws
      Stack: eks
    """.strip()

    with open(file, "w") as f:
        f.write(contents)

    print(f"Spark UI Plugin - Done generating deployment file: {file.absolute()}")

    return file


def main():
    <EDITED OUT> = str(sys.argv[1])
    spark_ui_app_docker_image = str(sys.argv[2])
    generate_deploy_file(<EDITED OUT>, spark_ui_app_docker_image)

if __name__ == '__main__':
    main()