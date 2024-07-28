#!/bin/zsh

# JAR 파일 경로 설정
JAR_FILE="/home/ec2-user/test/${app_name}.jar"

# Java 애플리케이션 실행
nohup java -jar $JAR_FILE > /dev/null 2>&1 &