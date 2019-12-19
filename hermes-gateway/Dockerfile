FROM openjdk:12.0.2

VOLUME /tmp

COPY hermes-gateway.jar hermes-gateway.jar

# SET TIMEZONE
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 10000

ENTRYPOINT ["java","-jar","/hermes-gateway.jar"]