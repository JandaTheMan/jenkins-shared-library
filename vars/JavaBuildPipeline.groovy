import com.tfg_janda.pipelines.JavaPipeline

def call(
        String gitRepo,
        String gitBranch,
        String gitCredentials,
        String dockerRegistry,
        String projectName,
        String buildNode,
        String testNode
) {

    def pipeline = new JavaPipeline(

            this,
            buildNode,
            testNode,
            gitRepo,
            gitBranch,
            gitCredentials,
            dockerRegistry,
            projectName
    )

    pipeline.run()
}

/*

Usage in Jenkinsfile:

library identifier: 'shared-liraries-POC@master', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'git@github.com:JandaTheMan/jenkins-shared-library.git',
     credentialsId: 'Github-jandaTheMan-credentials'])
  JavaBuildPipeline($GIT_REPO, $GIT_BRANCH, $GIT_PROJECT_CREDENTIALS, $DOCKER_REGISTRY_URL, $PROJECT_NAME ,$DEPLOY_NODE)

 */

