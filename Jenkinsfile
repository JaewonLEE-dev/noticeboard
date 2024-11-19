pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub')
        DOCKER_IMAGE_NAME = 'potato264/noticeboard'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/JaewonLEE-dev/noticeboard.git'
            }
        }

        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
                junit '**/build/test-results/test/*.xml'
            }
        }

        stage('Code Coverage') {
            steps {
                sh './gradlew jacocoTestReport'
                jacoco execPattern: '**/build/jacoco/test.exec'
            }
        }

        stage('Static Analysis') {
            steps {
                sh './gradlew sonarqube' // SonarQube를 통한 정적 분석
            }
        }

        stage('Docker Build & Push') {
            steps {
                script {
                    docker.withRegistry('', 'docker-hub') {
                        docker.build(DOCKER_IMAGE_NAME).push('latest')
                    }
                }
            }
        }
    }
}