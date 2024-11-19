pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub')
        DOCKER_IMAGE_NAME = 'potato264/noticeboard'
    }

    stages {
        stage('Checkout') {
            steps {
                 git branch: 'main', url: 'https://github.com/JaewonLEE-dev/noticeboard.git', credentialsId: 'github'
            }
        }


        stage('Set Permissions') {
            steps {
                sh 'chmod +x ./gradlew'
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

       stage("test coverage"){
          steps{
               sh "./gradlew test jacocoTestCoverageVerification"
               sh "./gradlew test jacocoTestReport"
          }
       }

       stage("Static Code Analysis"){
         steps{
             // 권한 부여
             sh """
             chmod -R 755 config/checkstyle/
             chmod -R 755 build/reports/checkstyle/
                """

             sh "./gradlew checkstyleMain"
                 publishHTML(target: [
                             reportDir: 'build/reports/checkstyle/',
                             reportFiles: 'main.html',
                             reportName: 'Checkstyle Report'
                 ])
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