def call(String REPOSITORY_URI, String IMAGE_TAG)
{
    sh "ecs-deploy -p default -c JehangirFargate -n nginxservice -i '${REPOSITORY_URI}:$IMAGE_TAG' -D 1"
}