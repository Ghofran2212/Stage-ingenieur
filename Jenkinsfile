pipeline {
  agent any
  tools {
    maven 'maven'
  }
  stages {

    stage("clean up") {
      steps {
        deleteDir()
      }
    }

    stage("clone code") {
      steps {
        sh "git clone https://github.com/Ghofran2212/Stage-ingenieur.git"
      }
    }

    stage('Build Image Backend') {
      steps {
        dir('Stage-ingenieur/backend') {
          sh 'mvn clean package -DskipTests'       
          sh 'docker build -t ghofran/spring-app . --no-cache'
          sh 'docker push ghofran/spring-app'
        }
      }
    }

  }
}
