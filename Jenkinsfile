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
        echo '🧪 Running basic verification...'
        sh 'echo ✅ BMIApp compiled successfully!'
      }
    }

    // 🔹 MODULE 5 ENHANCEMENT #1 – Advanced Tests (from walkthrough)
    stage('Advanced Tests') {
      steps {
        echo '🔍 Running enhanced tests...'
        sh '''
          # 1. Check if build directory exists
          if [ ! -d "build" ]; then
            echo "❌ ERROR: Build folder missing!"
            exit 1
          fi

          # 2. Check compiled class exists
          if [ ! -f "build/BMIApp.class" ]; then
            echo "❌ ERROR: BMIApp.class not found!"
            exit 1
          fi

          # 3. Lightweight lint-style check
          if grep -q "System.out.println" BMIApp.java; then
            echo "ℹ️ Note: Debug print statements found in BMIApp.java."
          fi

          echo "✅ All enhanced tests passed!"
        '''
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

  // 🔹 MODULE 5 ENHANCEMENT #2 – Email notifications (book/online enhancement)
  post {
    success {
      echo "✅ Pipeline succeeded — Build #$BUILD_NUMBER"

      emailext(
        subject: "✅ BMI Pipeline Success – Build #${BUILD_NUMBER}",
        body: """
Hello,

The BMIAppPipeline has completed SUCCESSFULLY.
Branch: ${env.GIT_BRANCH ?: env.BRANCH_NAME}
Build number: ${BUILD_NUMBER}

– Jenkins
""",
        to: "jishnu944@gmail.com"
      )
    }
    failure {
      echo "❌ Pipeline failed — Check console log."

      emailext(
        subject: "❌ BMI Pipeline FAILED – Build #${BUILD_NUMBER}",
        body: """
Hello,

The BMIAppPipeline has FAILED.
Branch: ${env.GIT_BRANCH ?: env.BRANCH_NAME}
Build number: ${BUILD_NUMBER}

Please check the Jenkins console log.

– Jenkins
""",
        to: "jishnu944@gmail.com"
      )
    }
  }
}
