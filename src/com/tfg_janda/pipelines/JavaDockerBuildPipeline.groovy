package com.tfg_janda.pipelines


import com.tfg_janda.stages.SetUpWorkspaceStage

class JavaDockerBuildPipeline {

    private PipelineWrapper _pipeline
    private _gitRepo
    private _gitBranch
    private _gitCredentials

    JavaDockerBuildPipeline(script, String node, String gitRepo, String gitBranch, String gitCredentials) {
        _pipeline = new PipelineWrapper(
                script,
                node
        )
        _gitRepo = gitRepo
        _gitBranch = gitBranch
        _gitCredentials = gitCredentials
    }

    def run() {
        _pipeline
                .setUp('linux', 'git')
                .addStageToNode(new SetUpWorkspaceStage(
                        'set up workspace',
                        _gitRepo,
                        _gitBranch,
                        _gitCredentials
                ), 'master')
                .exec()
    }
}
