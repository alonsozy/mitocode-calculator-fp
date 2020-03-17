author = 'AlonsoZY'
versionApp = '0.0.0'

pipeline{
	agent any
	environment {
	    APP_DOCKER_IMAGE = "${params.APP_DOCKER_IMAGE}"
	}
	stages {
	    stage('Example Build') {
    	   steps {
   	        echo 'Building ${APP_DOCKER_IMAGE}'
   	        sh 'docker build -t ${APP_DOCKER_IMAGE} .'
   	        echo 'Generate a compose file'
   	        sh "sed -i 's@{{APP_DOCKER_IMAGE}}@${APP_DOCKER_IMAGE}@g' docker-compose.dist"
   	        sh "cat docker-compose.dist"
   	        sh 'docker-compose -f docker-compose.dist up -d'
   	        sh 'sleep 10'
   	        sh 'docker-compose -f docker-compose.dist ps'
   	    }
    	}
	}
}

