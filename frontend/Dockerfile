FROM node:8.10.0
RUN mkdir -p /home/ubuntu/Documents/docker/angular_server
WORKDIR /home/ubuntu/Documents/docker/angular_server
COPY server.js /home/ubuntu/Documents/docker/angular_server
COPY dist /home/ubuntu/Documents/docker/angular_server/dist
COPY package-container.json /home/ubuntu/Documents/docker/angular_server
RUN mv package-container.json package.json
RUN npm install express --save
expose 4200
CMD ["node", "server.js"]