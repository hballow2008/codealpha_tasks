# 1. Start from a small, version-pinned base image
FROM nginx:1.27-alpine

# 2. Add a label describing the image
LABEL maintainer="Habeeb Balogun" description="Task 4 - custom Nginx web server"

# 3. Replace the default page with ours
COPY index.html /usr/share/nginx/html/index.html

# 4. Document the port the server listens on
EXPOSE 80

# 5. Health check: every 30s, request the home page. 3 failures in a row = unhealthy
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD wget -q --spider http://127.0.0.1/ || exit 1