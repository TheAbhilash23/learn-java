FROM openjdk:23-jdk AS base

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    apt-get clean


WORKDIR /usr/src/app

# Copy your project files (optional)
# ADD . /usr/src/app

