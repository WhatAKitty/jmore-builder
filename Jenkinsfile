pipeline {
  agent {
    node {
      label 'agent1'
    }

  }
  tools {
    maven 'M3' 
  }
  stages {
    stage('mvn test&package') {
      steps {
        sh 'mvn test'
        sh 'mvn package'
      }
    }
  }
}
