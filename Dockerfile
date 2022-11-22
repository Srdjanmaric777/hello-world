FROM openjdk:17-alpine
ADD target/hello-world-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM nginx
RUN rm /etc/nginx/nginx.conf
COPY conf /etc/nginx
COPY src/main/resources/static /usr/share/nginx/html
COPY src/main/resources/templates /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]