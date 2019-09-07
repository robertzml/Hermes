FROM openjdk:12.0.2

VOLUME /tmp

# SET FILES
WORKDIR /home/zml/hermes

COPY build/libs/*.jar /home/zml/hermes/hermes-company.jar

# SET TIMEZONE
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 23581

ENTRYPOINT ["java","-jar","./hermes-company.jar"]
