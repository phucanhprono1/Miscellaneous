FROM ubuntu:latest
LABEL authors="phuca"

ENTRYPOINT ["top", "-b"]