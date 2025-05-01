FROM openjdk:21

ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
WORKDIR /app
COPY target/pet-community-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_J3JSZ128ZNNXBZ7W /app/oracle_wallet
EXPOSE 8081

ENTRYPOINT exec java $JAVA_OPTS -jar app.jar