author = 'AlonsoZY'
versionApp = '0.0.0'

pipeline{
	agent any
	
	environment{
		URL_SERVER = "${params.URL_SERVER}"
	}
	
	stages{
		
		stage('Construyendo el app Calculadora ...'){
			agent{
				docker{ image 'maven:3.6.3-jdk-11-slim'}
			}
			steps{
				//sh  'mvn help:evaluate -Dexpression=project.version -q -DforceStdout > backend.txt'
				//script{
                //    versionApp = readFile('backend.txt').trim()+"-" + env.BUILD_NUMBER
                //}
                //echo 'Version del Proyecto: ${versionApp}'
				sh 'mvn clean package'
			}
		}
		
		stage('Iniciando el App.. .. ..'){
			steps{
				sh 'exec java -jar /target/mitocode-calculator.jar'
				sh 'ls -la'
			}
		}
		
		stage('Construyendo el artefacto para las pruebas Postman'){
			agent{
				docker{image 'node:12.2.0-alpine'}
			}
			steps{
				sh 'npm install -g newman'
				sh 'newman run test-newman/calculator-collection.json  --global-var url_server=${URL_SERVER}'
			}
		}
	}
	
}