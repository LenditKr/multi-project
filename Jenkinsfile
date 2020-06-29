pipeline {
  agent {
    node {
      label 'spot-agents'
    }
  }

  stages {
    stage('Prepare') {
      steps {
        sh 'echo "Hello World"'
         docker.image('mysql:5.7.12').withRun('-e "MYSQL_ALLOW_EMPTY_PASSWORD=yes" -p 3306:3306') { c ->
                  /* Wait until mysql service is up */
                  sh 'while ! mysqladmin ping -h0.0.0.0 --silent; do sleep 1; done'
                  /* Run some tests which require MySQL */
                  sh 'mysql -uroot -h 127.0.0.1 -e "CREATE DATABASE lendit_test CHARACTER SET utf8 COLLATE utf8_general_ci;'
              }
      }
    }

    stage('Build') {
      steps {
        sh './gradlew clean'
        sh './gradlew build'
      }
    }

  }
}