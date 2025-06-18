# 사용할 베이스 이미지 명시 (공식 이미지 권장)
FROM openjdk:17-jdk-slim

# 작업 디렉토리 생성 및 설정
WORKDIR /app

# 실제 jar 파일 이름으로 수정
COPY build/libs/backendProject-0.0.1-SNAPSHOT.jar /app/app.jar

# 환경변수 설정
ENV INSTANCE_NAME="환경변수입니다."

# 컨테이너 실행 시 기본으로 실행할 명령
CMD ["java", "-jar", "/app/app.jar"]
