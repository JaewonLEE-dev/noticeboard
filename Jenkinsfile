pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials-id') // Docker Hub 자격 증명 ID
        DOCKER_IMAGE_NAME = 'your-dockerhub-username/your-repo-name' // Docker 이미지 이름
    }

    stages {
        // 소스 코드 체크아웃 단계
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/your-repository.git'
            }
        }

        // 빌드 단계
        stage('Build') {
            steps {
                sh './gradlew clean build' // 빌드 실행
            }
        }

        // 테스트 단계
        stage('Test') {
            steps {
                sh './gradlew test' // 단위 테스트 실행
                junit '**/build/test-results/test/*.xml' // 테스트 결과 리포트 생성
            }
        }

        // 코드 커버리지 분석 단계 (JaCoCo)
        stage('Code Coverage') {

