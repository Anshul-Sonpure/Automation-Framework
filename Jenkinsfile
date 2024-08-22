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
                    bat "mvn install -DskipTests"
                }
            }
        }
        stage('Test') {
            steps {
                echo "Running test cases from Suite selected: ${params.SuiteName}"
                dir('E:/GitProjects/Automation-Framework') {
                    bat "mvn test -Dsurefire.suiteXmlFiles=E:/GitProjects/Automation-Framework/${params.SuiteName}.xml"
                }
            }
        }
        stage('Check Reports') {
            steps {
                echo "Checking if reports are generated..."
                dir('E:/GitProjects/Automation-Framework/target') {
                    bat "dir" // Lists all files in the target directory
                }
            }
        }
    }

    post {
        always {
            echo 'Publishing TestNG and Extent Reports...'
            
            publishHTML([target: [
                allowMissing: true,  // Change to true to avoid failure if the directory is missing
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'TestNG Report'
            ]])

            publishHTML([target: [
                allowMissing: true,  // Change to true to avoid failure if the directory is missing
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/extent-reports',
                reportFiles: 'index.html',
                reportName: 'Extent Report'
            ]])
        }
    }
}
