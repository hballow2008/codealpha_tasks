## Task 4: Web Server using Docker

This task runs an Nginx web server inside a Docker container, serving a custom web page, with a built-in health check for monitoring.

### Files
- `index.html`: the web page served to visitors.
- `Dockerfile`: builds a custom image from `nginx:1.27-alpine`, copies `index.html` into Nginx's web folder inside the container (`/usr/share/nginx/html/`), and adds a health check that requests the home page every 30 seconds.
- `docker-compose.yml`: defines the build, port mapping (8080:80) and restart policy, so the whole setup starts with one command.

### How to run

Option 1, with Docker commands:
    docker build -t habeeb-web:1.0 .
    docker run -d --name myweb -p 8080:80 --restart unless-stopped habeeb-web:1.0

Option 2, with Docker Compose (builds and runs in one step):
    docker compose up -d --build

Then open http://localhost:8080. Stop with `docker rm -f myweb` or `docker compose down`.

### Monitoring
- `docker ps` shows the container status as healthy or unhealthy.
- `docker inspect --format "{{json .State.Health}}" <container>` shows recent health check results.
- `docker logs` and `docker stats` show access logs and live resource usage.

### Troubleshooting test
I deleted `index.html` inside the running container to simulate a failure. The health check failed, the container was marked unhealthy, and the logs showed 403 errors. Copying the file back in with `docker cp` restored it to healthy. This showed why fixes belong in the image, since changes made inside a container are lost when it is removed.

### Best practices applied
- Pinned, lightweight base image (Alpine)
- Health check for automatic failure detection
- Restart policy (`unless-stopped`) for self-healing
- Configuration baked into the image instead of edited manually