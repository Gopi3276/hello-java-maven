pipeline{
   agent {
      label 'mvn'
   }
   tools {
       jdk 'jdk21'
       maven 'mvn'
   }
   stages{
      stage('clean workspace'){
         steps{
            cleanWs()
         }
      }
      stage('checkout code'){
         steps{
            checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/pavan-1309/hello-java-maven.git']])
         }
      }
      stage('build code'){
         steps{
            sh 'mvn clean compile'
         }
      }
      stage('run unit tests'){
         steps{
            sh 'mvn test'
         }
      }
      stage('owasp dependency check'){
         steps{
            dependencyCheck additionalArguments: '''--scan ./ 
            --out ./dependency-check-report
            --format HTML ''', odcInstallation: 'dp'
         }
      }
      stage('package code'){
         steps{
            sh 'mvn package'
         }
      }
      stage('nexus deploy'){
         steps{
            configFileProvider([configFile(fileId: '729b294a-0868-4cbf-bfa6-6476551b3c27', variable: 'mavensettings')]) {
    
               sh 'mvn deploy -DskipTests -s $mavensettings'
            }  
         }
      }
      stage('docker build and deploy'){
         steps{
            withCredentials([usernamePassword(credentialsId: 'dockerhub-pavan1309', passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
               sh '''
                  docker build -t hello-java-maven:latest .
                  docker run --name hello-java-maven -d -p 8080:8080 hello-java-maven:latest
                  
               '''
            }
         }
      }
   }




}