author = 'AlonsoZY'
versionApp = '0.0.0'

pipeline{
	agent any
	environment {
	    APP_DOCKER_IMAGE = "${params.APP_DOCKER_IMAGE}"
		VERSION_IMAGE = "${params.VERSION_IMAGE}"
	}
	stages {
	    stage('Build Image of Calculator') {
    	   steps {
   	         echo 'Building ${APP_DOCKER_IMAGE}'
			 sh 'ls -la'
   	         sh 'docker build -t ${APP_DOCKER_IMAGE} .'
			 sh 'cp /workspace/app.jar /app-calc-${VERSION_IMAGE}.jar'
			 sh 'ls -la'
			}
    	}
		
	}
	post{
		always{
			echo "Se guardara el jar en el repositorio de artefactos de jenkins"
			archiveArtifacts artifacts: '/app-calc-${VERSION_IMAGE}.jar', onlyIfSuccessful: true
		}
		success{
			echo "========pipeline executed successfully ========"
		}
		failure{
			echo "========pipeline execution failed========"
		}
	}
}


