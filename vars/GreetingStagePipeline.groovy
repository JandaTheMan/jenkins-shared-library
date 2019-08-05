import com.tfg_janda.pipelines.GreetingPipeline

def call(
        String node
) {

    def pipe = new GreetingPipeline(

                     this,
                     node,
                    'Hello alien!'

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
