FROM openjdk:12.0.2

VOLUME /tmp

COPY hermes-authentication.jar hermes-auth.jar

# SET TIMEZONE
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

EXPOSE 10200

ENTRYPOINT ["java","-jar","/hermes-auth.jar"]