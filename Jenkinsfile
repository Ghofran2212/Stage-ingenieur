pipeline {

  agent any 
  stages{
    stage("clean up"){
      steps{
        deleteDir()
      }

    }
    stage("clone code"){
      steps{
        sh "git clone https://github.com/Ghofran2212/Stage-ingenieur.git"

        
      
      }
    stage('Build Image Backend') {
    steps {
        dir('Stage-ingenieur/backend') {
            sh 'mvn clean package'
            sh 'docker build -t ghofran/spring-app .'
            sh 'docker push ghofran/spring-app'
        }
      }
    }
  }

}
