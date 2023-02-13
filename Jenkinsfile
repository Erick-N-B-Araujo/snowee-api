pipeline{
    agent {
        label 'mestre'
    }

	environment {
		PASS = credentials('dockerhub-pass')
	}
    stages {
        stage('Build') {
            steps {
            sh '''
                ./scripts/build/mvn.sh mvn clean package -DskipTests
                ./scripts/build/build_docker_image.sh 
            '''
            }
            post {
                success {
                    archiveArtifacts artifacts: 'java-app/target/*.jar', fingerprint: true
                }
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