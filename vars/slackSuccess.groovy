def call() 
{
    slackSend channel: 'U01S70WTAQL', message: "Build Succeeded. The Job: ${env.JOB_NAME} built successfully with Build# ${env.BUILD_NUMBER} which triggered with change in ${env.BRANCH_NAME} Branch on ${env.GIT_URL}"
}