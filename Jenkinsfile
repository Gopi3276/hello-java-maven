pipeline{
    agent any
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

        stage('git checkout'){
            steps{
                checkout changelog: false, poll: false, scm: scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/pavan-1309/hello-java-maven.git']])
            }
        }
    }


}