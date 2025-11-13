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
        echo 'Packaging into TAR artifact...'
        sh '''
          mkdir -p dist
          tar -cf dist/BMIApp-$BUILD_NUMBER.tar build
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
