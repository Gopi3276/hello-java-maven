pipeline{
    agent {
      label 'maven'
   }
   tools {
      jdk 'jdk21'
      maven 'mvn'
   }
   stages {
      stage('clean workspace') {
         steps {
            cleanWs()
         }
      }
      stage('scm checkout') {
         steps {
            checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/pavan-1309/hello-java-maven.git']])
         }
      }
   }

}