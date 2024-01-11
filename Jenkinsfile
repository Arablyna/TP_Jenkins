pipeline {
  agent any
  stages {
  stage('Tests'){
       steps{
       bat 'gradlew test'
       }
       post {
                    success{
                    archiveArtifacts 'target/*.json'
                    }
       }
  }
  stage('Test Reporting') {
              steps {
                cucumber 'reports/*json'
              }
            }
  stage('Code Analysis') {
          parallel {
            stage('Code Quality') {
              steps {
                withSonarQubeEnv('sonar') {
                  bat(script: 'gradlew sonarqube', returnStatus: true)
                }

                waitForQualityGate true
              }
            }

            stage('Test Reporting') {
              steps {
                cucumber 'reports/*json'
              }
            }
          }
        }

}
}