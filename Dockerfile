FROM openjdk:21

WORKDIR /app
COPY target/petcare-0.0.1-SNAPSHOT.jar app.jar
COPY Wallet_AZM52DPU5UUIPVEU /app/oracle_wallet
EXPOSE 8081

ENTRYPOINT exec java -jar app.jar