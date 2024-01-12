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
        stage('Notify') {
                steps {
                    echo "Notification..."
                    notifyEvents message: 'Build is created with success', token: 'whwnvf3djfhujrmgn4v_8royb_7dgor7'
                }
           }
         post {
            success {
                mail to: "jl_arab@esi.dz",
                subject: "Build Succeeded",
                body: "This is an email that informs that the new Build is deployed with success!"
            }
            failure {
                mail to: "jl_arab@esi.dz",
                subject: "Build failed",
                body: "This is an email that informs that the new Build is deployed with failure!"
            }
          }
}
}