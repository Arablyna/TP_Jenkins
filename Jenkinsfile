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
   stage("Code Analysis"){
      steps{

        withSonarQubeEnv('sonar') {
                          bat "./gradlew sonar"
                      }
      }
      }
       stage("Code Quality") {
                   steps {
                       waitForQualityGate abortPipeline: true
                   }
       }
       stage("Build") {
                 steps {
                     bat './gradlew build'
                     bat './gradlew javadoc'
                     archiveArtifacts 'build/libs/*.jar'
                     archiveArtifacts 'build/docs/'
                 }
             }



}
}