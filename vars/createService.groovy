def call(String Public_Subnet_1)
{
    sh "aws ecs create-service --cluster JehangirFargate --service-name nginxservice --task-definition nginxtask --desired-count 1  --launch-type 'FARGATE' --platform-version 'LATEST' --network-configuration 'awsvpcConfiguration={subnets=[${Public_Subnet_1}],securityGroups=[sg-0d0f4aed29ce82204],assignPublicIp=ENABLED}' "
}