pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub')
    }

    stages {
        stage('Permission') {
            steps {
                sh "chmod +x ./gradlew"
            }
        }

        stage('Compile') {
            steps {
                sh "./gradlew compileJava"
            }
        }

        stage('Test') {
            steps {
                sh './gradlew clean test'
            }
        }

        stage('Code Coverage') {
            steps {
                sh './gradlew jacocoTestReport'
                sh "./gradlew test jacocoTestReport"
            }
        }

        stage('Static Code Analysis') {
            steps {
                sh "./gradlew checkstyleMain"
                    publishHTML(target: [
                                                 reportDir: 'build/reports/checkstyle/',
                                                 reportFiles: 'main.html',
                                                 reportName: 'Checkstyle Report'
            }
        }

        stage("Gradle Build"){
          steps{
              sh "./gradlew clean build"
          }
        }

        stage("Docker Image Build"){
          steps{
              sh "docker build -t potato264/noticeboard ."
          }
        }

        stage('docker hub login'){
          steps{
              sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
          }
        }

        stage('docker hub push'){
         steps{
             sh 'docker push potato264/noticeboard:latest'
         }
        }

        }
    }
}
