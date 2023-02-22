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
                ./scripts/build/mvn.sh mvn clean package -DskipTests
            '''
            }
            post {
                success {
                    archiveArtifacts artifacts: 'java-app/target/*.jar', fingerprint: true
                }
            }
        }
        stage('Deploy') {
                steps {
                    sh 'docker-compose up -d'
                }
        }
    }
}