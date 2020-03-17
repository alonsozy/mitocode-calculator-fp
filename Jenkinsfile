author = 'AlonsoZY'
versionApp = '0.0.0'

pipeline{
	agent any
	environment {
	    APP_DOCKER_IMAGE = "${params.APP_DOCKER_IMAGE}"
	    NEWMAN_DOCKER_IMAGE = "${params.NEWMAN_DOCKER_IMAGE}"
	    HOST_APP = "${params.HOST_APP}"
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
   	          sh 'docker build -t ${NEWMAN_DOCKER_IMAGE} ./test-newman/'
			}
    	}
		stage('Generate docker-compose.yml') {
    	   steps {
   	          echo 'Generate a docker-compose file'
   	          sh "sed -i 's@{{APP_DOCKER_IMAGE}}@${APP_DOCKER_IMAGE}@g' docker-compose.dist"
   	          sh "sed -i 's@{{NEWMAN_DOCKER_IMAGE}}@${NEWMAN_DOCKER_IMAGE}@g' docker-compose.dist"
   	          sh "sed -i 's@{{HOST_APP}}@${HOST_APP}@g' docker-compose.dist"
   	          sh "cat docker-compose.dist"
			}
    	}
		stage('Executing docker-compose') {
    	   steps {
   	          sh 'docker-compose -f docker-compose.dist up -d'
   	          sh 'sleep 10'
   	          sh 'docker-compose -f docker-compose.dist ps'
   	          echo '*********** LOGS DEL SERVICE TEST-NEWMAN ********************'
   	          sh 'docker-compose logs test-newman'
			}
    	}
	}
}

