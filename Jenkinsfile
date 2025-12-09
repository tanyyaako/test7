pipeline {
    agent any

    tools {
        jdk 'JDK 21'
    }

    stages {
        stage('Check Environment') {
            steps {
                sh 'java --version'
                script {
                    def javaHome = tool 'JDK 21'
                    env.JAVA_HOME = javaHome
                    env.PATH = "${javaHome}/bin:${env.PATH}"
                }
            }
        }

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh './mvnw clean test || mvn clean test'
            }
        }
    }
}