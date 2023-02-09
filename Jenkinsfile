pipeline{
    agent any

	environment {
		PASS = credentials('dockerhub-pass')
	}
    stages {
        stage('Build') {
            steps {
            sh '''
                ./scripts/build/copy_resources.sh
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
        stage('Test') {
            steps {
            sh './scripts/build/mvn.sh mvn test'
            }
            post {
                always {
                    junit 'java-app/target/surefire-reports/junitreports/*.xml'
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