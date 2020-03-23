author = 'AlonsoZY'
versionApp = '0.0.0'

pipeline{
	agent any
	environment {
	    APP_DOCKER_IMAGE = "${params.APP_DOCKER_IMAGE}"
	    NEWMAN_DOCKER_IMAGE = "${params.NEWMAN_DOCKER_IMAGE}"
		USER_DOCKER = "${params.USER_DOCKER}"
		PASS_DOCKER = "${params.PASS_DOCKER}"
	}
	stages {
	    stage('Build Image of Calculator') {
    	   steps {
   	         echo 'Building ${APP_DOCKER_IMAGE}'
			 sh 'ls -la'
   	         sh 'docker build -t ${APP_DOCKER_IMAGE} .'
			}
    	}
    	stage('Build Image for test With Newman') {
    	   steps {
			   echo 'Building ${NEWMAN_DOCKER_IMAGE}'
   	          sh 'docker build -t ${NEWMAN_DOCKER_IMAGE} ./test-newman/'
			}
    	}
		stage('Pushing images..'){
			steps{
				echo 'Autentificacion'
				sh 'docker login --username=${USER_DOCKER} --password=${PASS_DOCKER}'
				sh "docker push ${APP_DOCKER_IMAGE}"
				sh "docker push ${NEWMAN_DOCKER_IMAGE}"
                sh "docker rmi ${APP_DOCKER_IMAGE}"
				sh "docker rmi ${NEWMAN_DOCKER_IMAGE}"
			}
		}
		
	}
}

