pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'        // Set in Jenkins Global Tool Configuration
        jdk 'JDK23'           // Or your configured JDK version
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
//                 stage('Compatibility (Java 23)') {
//                     agent {
//                         label 'java23'   // Requires a node with JDK17
//                     }
//                     steps {
//                         bat 'mvn test -Dsuite=regression.xml'
//                     }
//                 }
            }
            post {
                always {
                    echo "Publishing TestNG Results"
                    junit '**/surefire-reports/testng-results.xml'

                    echo "Archiving raw reports"
                    archiveArtifacts artifacts: '**/surefire-reports/**', fingerprint: true
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                // Make sure allure plugin is installed on Jenkins
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
            // Example Slack notification
            // slackSend channel: '#automation', message: "Test pipeline SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
        }

        failure {
            echo 'Build failed!'
            // Example email notification
            emailext to: 'nishatr50@gmail.com',
                     subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                     body: "The build has failed. Check Jenkins for details."
        }
    }
}
