pipeline {
  agent any
  tools {
    maven 'maven'
  }
   environment {
    JAVA_HOME = '/usr/lib/jvm/java-17-openjdk-amd64'
    PATH = "${JAVA_HOME}/bin:${env.PATH}"
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
    sh "cd Stage-ingenieur && git checkout dev && git pull origin dev"
  }
}

    stage("Login to Docker Hub") {
      steps {
        withCredentials([usernamePassword(
          credentialsId: 'docker-hub-cred',
          usernameVariable: 'DOCKERHUB_USERNAME',
          passwordVariable: 'DOCKERHUB_TOKEN'
        )]) {
          sh 'echo $DOCKERHUB_TOKEN | docker login -u $DOCKERHUB_USERNAME --password-stdin'
        }
      }
    }

    stage('Build & Push Backend') {
      steps {
        dir('Stage-ingenieur/backend') {
          sh 'mvn clean package -DskipTests'
          sh 'docker build -t ghofranhajjej/spring-app .'
          sh 'docker push ghofranhajjej/spring-app'
        }
      }
    }

    stage('Build & Push Frontend') {
      steps {
        dir('Stage-ingenieur/frontend') {
          sh 'docker build -t ghofranhajjej/angular-app . --no-cache'
          sh 'docker push ghofranhajjej/angular-app'
        }
      }
    }
    stage("docker compose for production") {
  steps {
    dir("Stage-ingenieur") {
      sh "docker-compose up -d"
    }
  }
}

  }
}
