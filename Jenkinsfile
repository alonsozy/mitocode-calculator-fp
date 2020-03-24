author = 'AlonsoZY'
versionApp = '0.0.0'

pipeline{
	agent any
	environment {
	    APP_DOCKER_IMAGE = "${params.APP_DOCKER_IMAGE}"
		VERSION_IMAGE = "${params.VERSION_IMAGE}"
	}
	stages {
	    stage('Construyendo el app Calculadora ...'){
			agent{
				docker{ image 'maven:3.6.3-jdk-11-slim'}
			}
			steps{
				sh 'mvn clean package'
				sh 'ls -la target'
				sh 'cp target/mitocode-calculator.jar app-calc-${VERSION_IMAGE}.jar'
				sh 'ls -la'
			}
		}
		
	}
	post{
		always{
			echo "Se guardara el jar en el repositorio de artefactos de jenkins"
			sh 'ls -la'
			archiveArtifacts artifacts: 'app-calc-${VERSION_IMAGE}.jar', onlyIfSuccessful: true
		}
		success{
			echo "========pipeline executed successfully ========"
		}
		failure{
			echo "========pipeline execution failed========"
		}
	}
}


