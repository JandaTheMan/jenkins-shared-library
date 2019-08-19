package com.tfg_janda.pipelines

import com.tfg_janda.console.OsTypes
import com.tfg_janda.scm.ScmTypes
import com.tfg_janda.stages.BuildStage
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
                .setUp(OsTypes.linux, ScmTypes.git)
                .addStageToNode(new SetUpWorkspaceStage(
                        'set up workspace',
                        _gitRepo,
                        _gitBranch,
                        _gitCredentials
                ), 'master')
                .addStageToNode(new BuildStage('Build'),'master')
                .exec()
    }
}
