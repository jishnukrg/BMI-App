pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build') {
      steps {
        echo 'Compiling Java source files...'
        bat '''
          if not exist build mkdir build
          javac -d build BMIApp.java
        '''
      }
    }

    stage('Test') {
      steps {
        echo 'Running simple verification...'
        bat 'echo ✅ BMIApp compiled successfully!'
      }
    }

    stage('Package') {
      steps {
        echo 'Packaging into ZIP artifact...'
        bat '''
          if not exist dist mkdir dist
          powershell -NoProfile -Command ^
            "Compress-Archive -Path build -DestinationPath dist\\BMIApp-$Env:BUILD_NUMBER.zip -Force"
        '''
      }
    }

    stage('Release') {              // ← your required modification
      when { branch 'main' }
      steps { echo '🚀 Releasing build artifact for main branch only.' }
    }
  }

  post {
    success { echo "✅ Pipeline succeeded — Build #$Env:BUILD_NUMBER" }
    failure { echo "❌ Pipeline failed — Check console log." }
  }
}
