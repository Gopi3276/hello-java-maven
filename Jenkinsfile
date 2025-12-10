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
      stage('OWASP Dependency-Check') {
         steps {
            dependencyCheck additionalArguments: '''--scan ./ \
               --out ./dependency-check-report \
               --format HTML''', odcInstallation: 'dp'
         }
      }
      stage('package') {
         steps {
            sh 'mvn package'
         }
      }
      stage('nexus deploy') {
         steps {
            configFileProvider([configFile(fileId: '3a157982-da20-46a4-b9ac-50707b580235', variable: 'mavensettings')]) {
               sh 'mvn clean deploy -DskipTests -s $mavensettings'
            }
         }
      }
      stage('sonarqube analysis') {
         steps {
            withSonarQubeEnv('sonar-server') {
               sh 'mvn sonar:sonar'
            }
         }
      }
      stage('quality gate') {
         steps {
            timeout(time: 5, unit: 'MINUTES') {
               waitForQualityGate abortPipeline: true
            }
         }
      }
   }

}