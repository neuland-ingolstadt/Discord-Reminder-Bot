FROM node:14

WORKDIR /opt/bot

COPY package*.json ./
RUN npm install
COPY . .

USER node
VOLUME [ "/opt/bot/config.json", "/opt/bot/data" ]
CMD [ "node", "app.js" ]
