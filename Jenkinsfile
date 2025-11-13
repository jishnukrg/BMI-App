pipeline {
  agent any

  environment {
    // Prefer Jenkins' built-in env vars for multibranch pipelines
    BRANCH_NAME = "${env.GIT_BRANCH ?: env.BRANCH_NAME ?: 'unknown'}"
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
        expression { 
          def branch = env.GIT_BRANCH ?: env.BRANCH_NAME
          echo "🔎 Detected branch for release: ${branch}"
          return branch?.contains("main")
        }
      }
      steps {
        echo "🚀 Releasing build artifact — running on main branch."
      }
    }
  }

  post {
    success {
      echo "✅ Pipeline succeeded — Build #$BUILD_NUMBER"
    }
    failure {
      echo "❌ Pipeline failed — Check console log."
    }
  }
}
