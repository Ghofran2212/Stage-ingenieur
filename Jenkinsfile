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
   stage("Login to docker hub") {
  steps {
    withCredentials([
      usernamePassword(
        credentialsId: 'docker-hub-cred',       
        usernameVariable: 'DOCKERHUB_USERNAME',
        passwordVariable: 'DOCKERHUB_TOKEN'
      )
    ]) {
      sh 'echo $DOCKERHUB_TOKEN | docker login -u $DOCKERHUB_USERNAME --password-stdin'
    }
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
