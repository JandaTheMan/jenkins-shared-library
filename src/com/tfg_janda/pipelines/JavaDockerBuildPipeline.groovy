package com.tfg_janda.pipelines

import com.tfg_janda.stages.GreetingStage
import com.tfg_janda.stages.SetUpWorkspaceStage

class JavaDockerBuildPipeline {

    private _pipeline
    private _gitRepo

    JavaDockerBuildPipeline(script, String node, String gitRepo) {
        _pipeline = new PipelineWrapper(
                script,
                node
        )
        _gitRepo = gitRepo
    }

    def run() {
        _pipeline
                .setUp('linux', 'git')
                //.addStageToNode(new GreetingStage(new SetUpWorkspaceStage(), 'master'))
                .exec()
    }
}
