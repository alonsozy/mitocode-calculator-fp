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
    	/*stage('Build Image for test With Newman') {
    	   steps {
   	          sh 'docker build -t ${NEWMAN_DOCKER_IMAGE} ./test-newman/'
			}
    	}*/
		stage('Run App Calculator') {
    	   steps {
   	          echo 'Creating container app'
   	          sh 'docker run -d -p2080:8080 --name app_calc ${APP_DOCKER_IMAGE}'
			}
    	}
		/*stage('Run test-newman') {
    	   steps {
   	          echo 'Creating container newman'
   	          sh 'docker run -e URL_SERVER=http://${HOST_APP}:2080 --name newman ${NEWMAN_DOCKER_IMAGE}'
			}
    	}*/
    	/*stage('Delete Containers') {
    	   steps {
   	          echo 'Delete containers'
   	          sh 'docker rm -f app_calc'
   	          sh 'docker rm -f newman'
			}
    	}*/
	}
}

