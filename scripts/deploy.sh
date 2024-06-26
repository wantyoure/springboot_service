#!/bin/bash

REPOSITORY=/home/ubuntu/app/step2
PROJECT_NAME=spring-service

# build 수행
echo "> project build start"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Git pull"

git pull

echo "> 프로젝트 Build 시작"
./gradlew build

echo "> 현재 구동중인 애플리케이션 pid 확인"

CURRENT_PID=$(pgrep -fl spring-service | grep jar | awk '{print $l}')

echo "> 현재 구동중인 애플리케이션 pid: $CURRENT_PID"
if [ -z "$CURRENT_PID" ]; then
	echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
	echo "> kill -15 $CURRENT_PID"
	kill -15 $CURRENT_PID
	sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> Jar Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &