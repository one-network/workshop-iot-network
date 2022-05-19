FROM nginx:1.21.6-alpine

RUN apk add openssl && \
    mkdir -p /etc/ssl/private /etc/ssl/certs && \
    openssl req -x509 -newkey rsa:4096 -sha256 -days 3650 -nodes \
        -keyout /etc/ssl/private/nginx-selfsigned.key -out /etc/ssl/certs/nginx-selfsigned.crt -subj "/CN=example.com" \
        -addext "subjectAltName=DNS:localhost,IP:127.0.0.1" && \
    apk del openssl

COPY --chown=root:root proxy.conf /etc/nginx/conf.d/proxy.conf
