pipeline {
  agent any
  stages {
  stage('Test') {
          steps {
              echo 'Running unit tests...'
              sh './gradlew test'
              junit 'build/test-results/test/TEST-Matrix.xml'
              echo 'Archiving artifacts...'
              archiveArtifacts 'build/test-results/**/*'
              echo 'Generation Cucumber report'
              cucumber buildStatus: 'UNSTABLE',
                         reportTitle: 'My report',
                         fileIncludePattern: 'target/report.json',
                         trendsLimit: 10
          }
      }


}