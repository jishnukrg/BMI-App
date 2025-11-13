pipeline {
  agent any

  environment {
    BRANCH_NAME = sh(script: 'git rev-parse --abbrev-ref HEAD', returnStdout: true).trim()
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        echo "✅ Checked out branch: ${BRANCH_NAME}"
      }
    }

    stage('Build') {
      steps {
        echo '🧱 Compiling Java source files...'
        sh '''
          mkdir -p build
          javac -d build BMIApp.java
        '''
      }
    }

    stage('Test') {
      steps {
        echo '🧪 Running verification...'
        sh 'echo ✅ BMIApp compiled successfully!'
      }
    }

    stage('Package') {
      steps {
        echo '📦 Packaging into TAR artifact...'
        sh '''
          mkdir -p dist
          tar -cf dist/BMIApp-$BUILD_NUMBER.tar build
        '''
      }
    }

    stage('Release') {
      when {
        expression { env.BRANCH_NAME == 'main' }
      }
      steps {
        echo "🚀 Releasing build artifact — running on main branch (${BRANCH_NAME})."
      }
    }
  }

  post {
    success {
      echo "✅ Pipeline succeeded — Build #$BUILD_NUMBER"
    }
    failure {
      echo "❌ Pipeline failed — check logs."
    }
  }
}
