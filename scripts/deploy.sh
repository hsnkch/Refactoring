#!/bin/bash
BUILD_JAR=$(ls /home/ubuntu/action/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
DEPLOY_LOG=/home/ubuntu/action/deploy.log
DEPLOY_PATH=/home/ubuntu/action/

echo "> build 파일명: $JAR_NAME" >> $DEPLOY_LOG

echo "> build 파일 복사" >> $DEPLOY_LOG

cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 pid 확인" >> $DEPLOY_LOG
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> $DEPLOY_LOG
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> $DEPLOY_LOG
nohup java -jar $BUILD_JAR >> /home/ubuntu/deploy.log 2>/home/ubuntu/action/deploy_err.log &
