# 자주 사용하는 값 변수에 저장
REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=spring_service

# git clone 받은 위치로 이동
cd $REPOSITORY/$PROJECT_NAME/

# build 수행
echo "> project build start"
./gradlew build

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl spring_service | grep jar | awk '{print $l}')

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

nohup java -jar \
  -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ubuntu/app/application-oauth.propertices,/home/ubuntu/app/application-real-db.properties \
  -Dspring.profiles.active=real \
   $JAR_NAME > $REPOSITORY/$JAR_NAME 2>&1 &