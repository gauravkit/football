pipeline {
    agent any
    stages {
        stage('Compile and Clean') {
            steps {

                sh "mvn clean compile"
            }
        }


        stage('deploy') {
            steps {
                sh "mvn package"
            }
        }


        stage('Build Docker image'){
            steps {

                sh 'docker build -t  gaaurabk/docker_jenkins_springboot:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Login'){

            steps {
                 withCredentials([string(credentialsId: 'DockerId', variable: 'Dockerpwd')]) {
                    sh "docker login -u gaaurabk -p panu@1234"
                }
            }
        }

        stage('Docker Push'){
            steps {
                sh 'docker push gaaurabk/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }

        stage('Docker deploy'){
            steps {

                sh 'docker run -itd -p  8081:8081 gaaurabk/docker_jenkins_springboot:${BUILD_NUMBER}'
            }
        }


        stage('Archving') {
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
    }
}