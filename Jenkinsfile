pipeline{
    agent {
        label 'agent-jenkins'
    }
	environment {
		PASS = credentials('dockerhub-pass')
	}
    stages {
        stage('Build') {
            steps {
            sh '''
                ./scripts/deploy/stop_app.sh
                ./scripts/build/mvn.sh mvn clean package -DskipTests
            '''
            }
            post {
                success {
                    archiveArtifacts artifacts: 'java-app/target/*.jar', fingerprint: true
                }
            }
        }
        stage('Clean-UP') {
                steps {
                    sh './scripts/clean/clean_dump.sh'
                }
        }
        stage('Push') {
            steps {
                    sh './scripts/push/push_docker_image.sh'
                }	
        }
        stage('Deploy') {
                steps {
                    sh './scripts/deploy/deploy_app.sh'
                }
        }
    }
}