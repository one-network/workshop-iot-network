FROM node:18-alpine3.15

VOLUME "/app"
WORKDIR /app

ENTRYPOINT [ "npx", "webpack", "serve", "--host", "0.0.0.0", "--client-web-socket-url", "wss://0.0.0.0:8443/data-collector/ws", "--allowed-hosts", "all" ]
