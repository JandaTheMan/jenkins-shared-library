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

