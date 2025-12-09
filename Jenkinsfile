pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'
        jdk 'JDK 17'
    }

    environment {
        SPRING_PROFILES_ACTIVE = 'test'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo 'Repository checked out successfully'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
                echo 'Project built successfully'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test -Dtest="*Test" -DfailIfNoTests=false'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
                success {
                    echo 'Unit tests passed!'
                }
                failure {
                    echo 'Unit tests failed!'
                }
            }
        }

        stage('Mock Data Tests') {
            steps {
                sh 'mvn test -Dtest="*MockData*Test" -DfailIfNoTests=false'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
                success {
                    echo 'Mock data tests passed!'
                }
                failure {
                    echo 'Mock data tests failed!'
                }
            }
        }

        stage('Integration Tests') {
            steps {
                script {
                    try {
                        sh 'mvn test -Dtest="*IntegrationTest" -Dspring.profiles.active=test'
                        echo 'Integration tests passed!'
                    } catch (Exception e) {
                        echo 'Integration tests failed!'
                        throw e
                    }
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn clean package -DskipTests'
                echo 'Application packaged successfully!'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                    echo ' Artifacts archived'
                }
            }
        }

        stage('Generate Test Report') {
            steps {
                // Создаем HTML отчет из результатов тестов
                sh '''
                    echo "<html><head><title>Test Results</title></head><body>" > target/test-report.html
                    echo "<h1>Test Results Summary</h1>" >> target/test-report.html
                    echo "<p>Build Number: ${BUILD_NUMBER}</p>" >> target/test-report.html
                    echo "<p>Job Name: ${JOB_NAME}</p>" >> target/test-report.html
                    echo "<h2>Test Categories:</h2>" >> target/test-report.html
                    echo "<ul>" >> target/test-report.html
                    echo "<li> Unit Tests</li>" >> target/test-report.html
                    echo "<li> Mock Data Tests</li>" >> target/test-report.html
                    echo "<li>Integration Tests</li>" >> target/test-report.html
                    echo "</ul>" >> target/test-report.html
                    echo "</body></html>" >> target/test-report.html
                '''
                archiveArtifacts 'target/test-report.html'
            }
        }
    }

    post {
        always {
            echo "Build ${currentBuild.result} - ${currentBuild.fullDisplayName}"

            // Отправляем уведомление
            emailext (
                subject: "Build ${currentBuild.result}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                    Build: ${env.JOB_NAME} #${env.BUILD_NUMBER}
                    Result: ${currentBuild.result}
                    URL: ${env.BUILD_URL}

                    Test Results: ${env.BUILD_URL}testReport/
                    Console Output: ${env.BUILD_URL}console

                    ${currentBuild.result == 'SUCCESS' ? ' Все тесты прошли успешно!' : 'Некоторые тесты не прошли!'}
                """,
                to: 'developer@example.com',
                attachLog: true
            )

            // Очистка workspace (опционально)
            cleanWs()
        }
        success {
            echo 'Pipeline успешно завершен!'
        }
        failure {
            echo 'Pipeline завершился с ошибкой'
        }
    }
}