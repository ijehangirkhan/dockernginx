def call()
{
    slackSend channel: 'U01S70WTAQL', message: "Build Failed. The Job: ${env.JOB_NAME} build failed with Build# ${env.BUILD_NUMBER} which triggered with change in ${env.BRANCH_NAME} Branch on ${env.GIT_URL}"
}