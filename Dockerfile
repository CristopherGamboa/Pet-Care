FROM openjdk:21

ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
WORKDIR /app
COPY target/petcare-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_AZM52DPU5UUIPVEU /app/oracle_wallet
EXPOSE 8081

ENTRYPOINT exec java $JAVA_OPTS -jar app.jar