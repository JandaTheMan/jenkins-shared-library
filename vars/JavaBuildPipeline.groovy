import com.tfg_janda.pipelines.JavaDockerBuildPipeline

def call(
        String node,
        String gitRepo,
        String gitBranch,
        String gitCredentials
) {

    def pipe = new JavaDockerBuildPipeline(

            this,
            node,
            gitRepo,
            gitBranch,
            gitCredentials
    )

    pipe.run()
}

/**
 * Usage in Jenkinsfiles :
 *
 * library identifier: 'shared-liraries-POC@master', retriever: modernSCM(
 *   [$class: 'GitSCMSource',
 *    remote: 'git@github.com:JandaTheMan/jenkins-shared-library.git',
 *    credentialsId: 'Github-jandaTheMan-credentials'])
 * GreetingStagePipeline('master')
 */
