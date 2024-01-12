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
        stage('Deploy') {
                steps {
                   bat './gradlew publish'

                }
             }
        stage('Notification') {
                parallel{
                stage('Email Notification'){
                steps {
                    mail(subject: 'success notification', body: mail, cc: 'jl_arab@esi.dz', bcc: 'jl_arab@esi.dz')
                }
                }
                stage('others Notification'){
                steps{notifyEvents message: mail, token: 'whwnvf3djfhujrmgn4v_8royb_7dgor7'
                }
                }}
            }
        }



}
}