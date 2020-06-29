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
      }
    }

    stage('Build') {
      steps {
        sh '''./gradlew clean
'''
        sh './gradlew build'
      }
    }

  }
}