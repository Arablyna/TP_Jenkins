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
        stage("Deploy & notification"){
                   steps {
                       bat './gradlew publish'
                   }
                   post {
                         success {
                              notifyEvents message: 'Success ',
                              token: 'whwnvf3djfhujrmgn4v_8royb_7dgor7'
                              mail to: 'jl_arab@esi.dz',
                              subject: "Success",
                              body: "Deployment successful"
                         }
                         failure {
                               notifyEvents message: 'Failure',
                               token: 'whwnvf3djfhujrmgn4v_8royb_7dgor7'
                               mail to: 'jl_arab@esi.dz',
                               subject: "Failure",
                               body: "Something went wrong "
                         }
                   }
               }



}
}