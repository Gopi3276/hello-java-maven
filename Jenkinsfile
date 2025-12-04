pipeline{
    agent any
    tools {
      jdk 'jdk'
      maven 'mvn'
      dockerTool 'docker'
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

        stage('compile'){
            steps{
                sh 'mvn compile'
            }
        }

        stage('package'){
            steps{
                sh 'mvn package'
            }
        }
        stage('build docker image'){
            steps{
                script{
                    dockerImage = docker.build("pavan1309/hello-java-maven:${env.BUILD_ID}")
                }
            }
        }
        stage('push docker image'){
            steps{
                script{
                    docker.withRegistry('', 'dockerhub-credentials') {
                        dockerImage.push()
                    }
                }
            }
        }
    }


}