pipeline{
    agent any
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

        stage ('OWASP Dependency-Check Vulnerabilities') {
            steps {
                dependencyCheck additionalArguments: ''' 
                    -o "./" 
                    -s "./"
                    -f "ALL" 
                    --prettyPrint''', odcInstallation: 'dp'

                dependencyCheckPublisher pattern: 'dependency-check-report.xml'
              }
             }     
         
    
        stage('package'){
            steps{
                sh 'mvn package'
            }
        }
        stage('deploy to nexus'){
            steps{
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]) {
                    sh 'mvn deploy -DskipTests'
                }
            }
        }
        stage('build docker image'){
            steps{
                script{
                    def dockerImage = docker.build("pavan1309/hello-java-maven:${env.BUILD_ID}")
                    docker.withRegistry('', 'docker') {
                        dockerImage.push()
                    }
                }
            }
        }
    }


}