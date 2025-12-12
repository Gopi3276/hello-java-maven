pipeline{
    agent {
      label 'slave1'
   }
   tools{
      jdk 'jdk'
      maven 'mvn'
   }
   stages{
      stage('clean workspace'){
         steps{
          cleanWs()  
         }
      }
      stage('git clone'){
         steps{
            checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[credentialsId: 'git-cred', url: 'https://github.com/pavan-1309/hello-java-maven.git']])
         }
      }
      stage('compile'){
         steps{
            sh 'mvn compile'
         }
      }
      stage('unit test'){
         steps{
            sh 'mvn test'
         }
      }
      stage('build'){
         steps{
            sh 'mvn clean package'
         }
      }
   }
}