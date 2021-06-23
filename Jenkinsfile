@Library("AutomationLibrary") _
pipeline {
    agent { label 'slave1' }
    environment {
        AWS_ACCOUNT_ID="489994096722"
        AWS_DEFAULT_REGION="us-east-2" 
        IMAGE_REPO_NAME="jehangir-jenkins-demo"
        IMAGE_TAG="latest"
        REPOSITORY_URI = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}"
        Public_Subnet_1 = "subnet-eaa81381"
        Public_Subnet_2 = "subnet-09c93874"
    }
   
    stages {
        
        stage('Logging into AWS ECR') {
            steps {
                script {
                sh "aws ecr get-login-password --region ${AWS_DEFAULT_REGION} | docker login --username AWS --password-stdin ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com"
                }
            }
        }
        
  
        // Building Docker images
        stage('Building image') {
        steps{
            script {
            dockerImage = docker.build "${IMAGE_REPO_NAME}:${IMAGE_TAG}"
            }
        }
        }
    
        // Uploading Docker images into AWS ECR
        stage('Pushing to ECR') {
        steps{  
            script {
                    sh "docker tag ${IMAGE_REPO_NAME}:${IMAGE_TAG} ${REPOSITORY_URI}:$IMAGE_TAG"
                    sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${IMAGE_TAG}"
            }
            }
        }
        
        // Delpoy on ECS
        stage('Deploy ECR Image to ECS') {
                steps {
                       createService("${Public_Subnet_1}")
                       ecsDeploy("${REPOSITORY_URI}","$IMAGE_TAG")
                }
        }  
      
      
    }


     post {
        success {
            script {
                if ( env.BRANCH_NAME == 'master')
                slackSuccess()
            }
           
        }
        failure {
            script {
                if ( env.BRANCH_NAME == 'master')
                slackFailure()
            }
        }
    }
}
