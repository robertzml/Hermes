FROM openjdk:12.0.2

VOLUME /tmp

COPY hermes-log.jar hermes-log.jar

# SET TIMEZONE
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 9090

ENTRYPOINT ["java","-jar","/hermes-log.jar"]