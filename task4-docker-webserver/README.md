## Task 4: Web Server using Docker

This task runs an Nginx web server inside a Docker container, serving a custom web page, with a built-in health check for monitoring.

### Files
- `index.html`: the web page served on localhost:8080.
- `Dockerfile`: builds a custom image from `nginx:1.27-alpine`, copies `index.html` into Nginx's web folder inside the container (`/usr/share/nginx/html/`), and adds a health check that requests the home page every 30 seconds.
- `docker-compose.yml`: defines the build, port mapping (8080:80) and restart policy, so the whole setup starts/ends with one command - docker compose up/down.

### How to run

Option 1, with Docker commands:
    docker build -t habeeb-web:1.0 .
    docker run -d --name habeeb-web -p 8080:80 --restart unless-stopped habeeb-web:1.0

Option 2, with Docker Compose (builds and runs in one step): **(preferred)**
    docker compose up -d --build

Then open http://localhost:8080. Stop with `docker rm -f habeeb-web` or `docker compose down`.

### Monitoring
- `docker ps` shows the container status as healthy or unhealthy.
- `docker inspect --format "{{json .State.Health}}" <container>` shows recent health check results.
- `docker logs` and `docker stats` show access logs and live resource usage.

### Troubleshooting test
I deleted `index.html` inside the running container to simulate a failure. The health check failed, the container was marked unhealthy, and the logs showed 403 errors. Copying the file back in with `docker cp` restored it to healthy. This showed why fixes belong in the image, since changes made inside a container are lost when it is removed.
