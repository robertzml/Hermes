FROM openjdk:12.0.2

VOLUME /tmp

COPY hermes-account.jar hermes-account.jar

# SET TIMEZONE
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 8080

ENTRYPOINT ["java","-jar","/hermes-account.jar"]