FROM ubuntu:latest
LABEL authors="Kim Chansokpheng"

ENTRYPOINT ["java", "c","app.jar"]