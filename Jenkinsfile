pipeline {
    agent {
        node {
            label 'spot-agents'
        }
    }
    stages {
        stage('Prepare') {
            parallel {
                stage('pull mysql') {
                    steps {
                        sh 'docker pull mysql:5.7.12'
                    }
                }
                stage('pull mongo') {
                    steps {
                        sh 'docker pull mongo:4.2.8'
                    }
                }
            }
        }

        stage('Build') {
            steps {
                sh './gradlew assemble'
            }
        }

        stage('Test') {
            steps {
                sh 'docker run -it -d -e "MYSQL_ALLOW_EMPTY_PASSWORD=yes" -p 3306:3306 mysql:5.7.12'
                sh 'docker run -it -d -p 27017:27017 mongo:4.2.8'
                /* Wait until mysql service is up */
                sh 'until mysql -uroot -h 127.0.0.1 -e "select version();"; do sleep 1; done'
                /* Run some tests which require MySQL */
                sh 'mysql -uroot -h 127.0.0.1 -e "CREATE DATABASE lendit_test CHARACTER SET utf8 COLLATE utf8_general_ci;"'
                sh './gradlew test jacocoTestReport -S'
            }
        }
    }

    post {
        cleanup {
            sh 'docker rm -f $(docker ps -qa)'
        }
    }
}
