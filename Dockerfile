# Base image: Java 17 JDK with Alpine Linux
FROM openjdk:17-jdk-alpine

# 작업 디렉터리 설정
WORKDIR /app

# ARG로 JAR 파일 설정 (유연성 제공)
ARG JAR_FILE=build/libs/noticeboard-0.0.1-SNAPSHOT.jar

# 애플리케이션 JAR 파일 복사
COPY ${JAR_FILE} app.jar

# 컨테이너가 사용하는 포트 정의
EXPOSE 8080

# 컨테이너 실행 시 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

