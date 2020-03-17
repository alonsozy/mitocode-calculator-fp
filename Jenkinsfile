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
	    stage('Example Build  1') {
    	   steps {
   	        echo 'Building ${APP_DOCKER_IMAGE}'
			sh 'ls -la'
   	        sh 'docker build -t ${APP_DOCKER_IMAGE} .'
			}
    	}
    	stage('Example Build 2') {
    	   steps {
   	         sh 'docker build -t ${NEWMAN_DOCKER_IMAGE} ./test-newman/'
			}
    	}
		stage('Example Build 3') {
    	   steps {
   	          echo 'Generate a compose file'
   	        sh "sed -i 's@{{APP_DOCKER_IMAGE}}@${APP_DOCKER_IMAGE}@g' docker-compose.dist"
   	        sh "sed -i 's@{{NEWMAN_DOCKER_IMAGE}}@${NEWMAN_DOCKER_IMAGE}@g' docker-compose.dist"
   	        sh "sed -i 's@{{HOST_APP}}@${HOST_APP}@g' docker-compose.dist"
   	        sh "cat docker-compose.dist"
			}
    	}
		stage('Example Build 5') {
    	   steps {
   	          sh 'docker-compose -f docker-compose.dist up -d'
   	        sh 'sleep 10'
   	        sh 'docker-compose -f docker-compose.dist ps'
			}
    	}
	}
}

