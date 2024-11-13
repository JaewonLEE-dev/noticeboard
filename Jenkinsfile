pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-credentials-id')
        DOCKER_IMAGE_NAME = 'your-dockerhub-username/your-repo-name'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/your-username/your-repository.git', credentialsId: 'github-credentials-id'
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
                    docker.withRegistry('', 'docker-hub-credentials-id') {
                        docker.build(DOCKER_IMAGE_NAME).push('latest')
                    }
                }
            }
        }
    }
}
