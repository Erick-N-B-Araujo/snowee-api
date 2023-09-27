pipeline{
    agent none

    stages {
        stage('Building') {
            agent any

            steps {
            sh '''
                ./local/scripts/build/mvn.sh mvn clean package -DskipTests
            '''
            }
            post {
                success {
                    archiveArtifacts artifacts: 'local/java-app/target/*.jar', fingerprint: true
                }
            }
        }
        stage('Cleaning') {
            agent any

                steps {
                    sh './local/scripts/clean/clean_dump.sh'
                }
        }
        stage('Deploying') {
            agent {
                label 'agent-prd'
            }
            steps {
                sh ''' 
                    ./remote/scripts/deploy/stop_app.sh
                    ./remote/scripts/deploy/deploy_app.sh
                '''
            }
        }
    }
}