pipeline {
    agent any

    parameters {
        choice(
            name: 'SuiteName',
            choices: ['uiTest', 'apiTest', 'securityTest'],
            description: 'Choose the TestNG suite to run the Test Cases'
        )
    }

    stages {
        stage('Clean') {
            steps {
                echo "Cleaning the project..."
                dir('E:/GitProjects/Automation-Framework') {
                    bat "mvn clean"
                }
            }
        }
        stage('Build and Install') {
            steps {
                echo "Building and installing the project..."
                
                dir('E:/GitProjects/Automation-Framework') {
                    bat "mvn clean"
                }
            }
        }
        stage('Test') {
            steps {
                echo "Running test cases from Suite selected: ${params.SuiteName}"
                
                // Change to the directory where the XML files are located
                dir('E:/GitProjects/Automation-Framework') {
                    bat "mvn test -Dsurefire.suiteXmlFiles=E:/GitProjects/Automation-Framework/${params.SuiteName}.xml"

                }
            }
        }
    }
}