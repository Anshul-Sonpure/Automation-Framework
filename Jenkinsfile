pipeline {
    agent any

    parameters {
        choice(
            name: 'TEST_PACKAGE',
            choices: ['api test', 'security test', 'UI test'],
            description: 'Choose which test package to run'
        )
    }

    environment {
        MAVEN_HOME = tool 'Maven' // Adjust this to your Maven installation name in Jenkins
        JAVA_HOME = tool 'JDK' // Adjust this to your JDK installation name in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Pull the code from your GitHub repository
                git url: 'https://github.com/Anshul-Sonpure/Automation-Framework.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                // Run Maven build
                script {
                    def testPackage = params.TEST_PACKAGE
                    sh "${MAVEN_HOME}/bin/mvn clean test -Dtest=**/${testPackage}/*.java"
                }
            }
        }

        stage('Post-Build Actions') {
            steps {
                // Publish TestNG results (if needed)
                junit 'target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            echo "Cleaning up workspace"
            cleanWs()
        }
        success {
            echo "Build successful!"
        }
        failure {
            echo "Build failed!"
        }
    }
}
