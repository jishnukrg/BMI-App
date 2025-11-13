pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build') {
      steps {
        echo 'Compiling Java source files...'
        sh '''
          mkdir -p build
          javac -d build BMIApp.java
        '''
      }
    }

    stage('Test') {
      steps {
        echo 'Running simple verification...'
        sh 'echo ✅ BMIApp compiled successfully!'
      }
    }

    stage('Package') {
      steps {
        echo 'Packaging into ZIP artifact...'
        sh '''
          mkdir -p dist
          apt-get update -y
          apt-get install -y zip
          zip -r dist/BMIApp-$BUILD_NUMBER.zip build
        '''
      }
    }

    stage('Release') {
      when { branch 'main' }
      steps {
        echo '🚀 Releasing build artifact for main branch only.'
      }
    }
  }

  post {
    success { echo "✅ Pipeline succeeded — Build #$BUILD_NUMBER" }
    failure { echo "❌ Pipeline failed — Check console log." }
  }
}
