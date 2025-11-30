pipeline {
  agent any

  // Module 6 monitoring options
  options {
    // Keep only the last 10 builds / 7 days to reduce storage and log clutter
    buildDiscarder(logRotator(numToKeepStr: '10', daysToKeepStr: '7'))

    // Fail the build if it runs longer than 5 minutes
    timeout(time: 5, unit: 'MINUTES')

    // Add timestamps to every log line for easier monitoring
    timestamps()
  }

  environment {
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

    // Enhancement 1 from Module 5 – still here
    stage('Advanced Tests') {
      steps {
        echo '🔍 Running enhanced tests...'
        sh '''
          if [ ! -d "build" ]; then
            echo "❌ Build folder missing!"
            exit 1
          fi

          if [ ! -f "build/BMIApp.class" ]; then
            echo "❌ BMIApp.class not found!"
            exit 1
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

    // NEW for Module 6 – Health & Monitoring stage
    stage('Health & Monitoring') {
      steps {
        echo '📊 Collecting basic health information...'
        sh '''
          echo "Workspace disk usage:"
          du -sh . || echo "du command not available"

          echo ""
          echo "System uptime:"
          uptime || echo "uptime command not available"
        '''
      }
    }

    stage('Release') {
      when {
        expression {
          def branch = env.GIT_BRANCH ?: env.BRANCH_NAME
          echo "🔎 Detected branch for release: ${branch}"
          return branch?.contains('main')
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
      emailext(
        subject: "BMI Pipeline SUCCESS – Build #${BUILD_NUMBER}",
        to: "jishnu944@gmail.com",
        body: "The BMIApp pipeline finished successfully on branch ${BRANCH_NAME}.\\nBuild URL: ${BUILD_URL}"
      )
    }

    failure {
      echo "❌ Pipeline failed — Build #$BUILD_NUMBER"
      emailext(
        subject: "BMI Pipeline FAILED – Build #${BUILD_NUMBER}",
        to: "jishnu944@gmail.com",
        body: "The BMIApp pipeline FAILED on branch ${BRANCH_NAME}.\\nPlease check logs: ${BUILD_URL}"
      )
    }
  }
}
