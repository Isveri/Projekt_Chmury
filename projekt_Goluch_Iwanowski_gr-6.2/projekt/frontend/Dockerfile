FROM node:alpine

WORKDIR '/app'
ENV NODE_OPTIONS=--openssl-legacy-provider
COPY package.json .
RUN npm install -g @angular/cli
RUN npm install --legacy-peer-deps

COPY . .
EXPOSE 4200

RUN chown -R node /app/node_modules

USER node

CMD ng serve --host 0.0.0.0 -c production
