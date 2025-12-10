pipeline{
    agent {
      label 'mvn'
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
      stage('compile') {
         steps {
            sh 'mvn clean compile'
         }
      }
      stage('unit test') {
         steps {
            sh 'mvn test'
         }
      }
      stage('package') {
         steps {
            sh 'mvn package'
         }
      }
      stage('nexus deploy') {
         steps {
            sh 'mvn deploy -DskipTests=true'
         }
      }
   }

}