def call
{
    sh "ecs-deploy -p default -c JehangirFargate -n nginxservice -i '${REPOSITORY_URI}:$IMAGE_TAG' -D 1"
}