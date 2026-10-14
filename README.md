# DevOps Tasks

![Java CI](https://github.com/hballow2008/devops-tasks/actions/workflows/ci.yml/badge.svg)

A set of hands-on DevOps tasks covering containerisation, automated testing and continuous integration. Each task lives in its own folder with everything needed to build and run it.

| Task | Folder | What it shows |
|------|--------|---------------|
| Task 3 | [`task3/app`](task3/app) | Spring Boot app built with Gradle, unit tests with coverage, a Docker image and a GitHub Actions CI pipeline |
| Task 4 | [`task4-docker-webserver`](task4-docker-webserver) | Nginx web server in Docker with a health check, run via Docker Compose |

---

## Task 3: Spring Boot App with CI

A small Java 25 / Spring Boot REST service, tested and packaged automatically on every push.

### Endpoints
- `GET /` returns the app name, status and version as JSON.
- `GET /api/greet?name=Habeeb` returns `{"message": "Hello, Habeeb!"}` (falls back to `Hello, World!` with no name).
- `GET /actuator/health` returns the health status, which is also used by the Docker health check.

### Build and test locally
    cd task3/app
    ./gradlew build

This compiles the app, runs the unit tests and generates a JaCoCo coverage report at `build/reports/jacoco/test/html/index.html`.

### Run with Docker
    cd task3/app
    docker build -t devops-app .
    docker run -d -p 8080:8080 --name devops-app devops-app

The Dockerfile uses a multi-stage build: a JDK image compiles the jar, and a smaller JRE image runs it as a non-root user with a health check on `/actuator/health`.

### CI pipeline
[`.github/workflows/ci.yml`](.github/workflows/ci.yml) runs on every push and pull request to `main`:
1. Sets up Java 25 (Temurin) and Gradle with caching.
2. Builds the app and runs the tests.
3. Uploads the test/coverage reports and the jar as build artifacts.
4. Builds the Docker image tagged with the commit SHA.

---

## Task 4: Web Server using Docker

An Nginx web server in a Docker container serving a custom page, with a built-in health check.

    cd task4-docker-webserver
    docker compose up -d --build

Then open http://localhost:8080. See the [Task 4 README](task4-docker-webserver/README.md) for the full walkthrough, monitoring commands and a troubleshooting exercise.
