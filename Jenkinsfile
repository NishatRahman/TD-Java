pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'
        jdk 'JDK23'
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=true"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests in Parallel') {
            parallel {
                stage('Regression Suite') {
                    steps {
                        bat 'mvn test -Dsuite=regression.xml'
                    }
                }
                stage('Smoke Suite') {
                    steps {
                        bat 'mvn test -Dsuite=smoke.xml'
                    }
                }
            }
            post {
                always {
                    echo "Publishing TestNG Results"
                    junit 'target/surefire-reports/*.xml'

                    archiveArtifacts artifacts: 'target/surefire-reports/*', fingerprint: true
                    publishHTML([
                                        reportDir: 'target/surefire-reports',
                                        reportFiles: 'index.html',
                                        reportName: 'Test Report',
                                        keepAll: true,
                                        allowMissing: false,
                                        alwaysLinkToLastBuild: true
                    ])
                }

            }
        }

        stage('Generate Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }

    post {

        always {
            echo "Pipeline completed."
        }

        success {
            echo 'Build succeeded!'
            // slackSend channel: '#automation', message: "Test pipeline SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
        }

        failure {
            echo 'Build failed!'
            emailext to: 'nishatr50@gmail.com',
                     subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                     body: "The build has failed. Check Jenkins for details."
        }
    }
}
