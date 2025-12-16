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
      stage('dp-check'){
         steps{
            sh ''' dependencyCheck additionalArguments: '''--scan ./
                   --out ./ dp-report
                   --format HTML ''', odcInstallation: 'dp'
            
            '''
         }
      }
      stage('package'){
         steps{
            sh 'mvn package -DskipTests'
         }
      }
      stage('nexus deploy'){
         steps{
            configFileProvider([configFile(fileId: 'f18433ee-d6f2-46fa-a692-e4765eebb3d5', variable: 'mvn_setting')]){
               sh 'mvn deploy -s $mvn_setting'
            }
         }
      }
   }
}
