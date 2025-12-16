pipeline{
   agent {
      label 'mvn'
   }
   tools {
      jdk 'jdk'
      maven 'mvn'
    }
    stages{
      stage('clean workspace'){
         steps{
            cleanWs()
         }
      }
      stage('git clone or git checkout'){
         steps{
            checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/pavan-1309/hello-java-maven.git']])
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
      stage('package'){
         steps{
            sh 'mvn package -DskipTests'
         }
      }
      stage('nexus deploy'){
         steps{
            sh 'echo "deploy"'
         }
      }
   }
}
