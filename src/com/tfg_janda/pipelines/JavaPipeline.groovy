package com.tfg_janda.pipelines

import com.tfg_janda.stages.DockerDeployStage
import com.tfg_janda.stages.SetUpWorkspaceStage
import com.tfg_janda.stages.java.JavaBuildStage

class JavaPipeline {

    private PipelineWrapper _pipeline
    private String _gitRepo
    private String _gitBranch
    private String _gitCredentials
    private String _testNode
    private String _dockerRegistry
    private String _projectName
    private String _buildNode

    JavaPipeline(script, String buildNode,String deployTestNode, String gitRepo, String gitBranch, String gitCredentials, String dockerRegistry, String projectName) {
        _pipeline = new PipelineWrapper(
                script
        )
        _gitRepo = gitRepo
        _gitBranch = gitBranch
        _gitCredentials = gitCredentials
        _dockerRegistry = dockerRegistry
        _projectName = projectName
        _buildNode = buildNode
        _testNode = deployTestNode
    }

    def run() {
        _pipeline
                .setUp('linux', 'git')
                .addStageToNode(new SetUpWorkspaceStage('set-up', _gitRepo, _gitBranch,  _gitCredentials), _buildNode)
                .addStageToNode(new JavaBuildStage('build',_dockerRegistry,_projectName), _testNode)
                .addStageToNode(new DockerDeployStage('deploy staging', _dockerRegistry, _projectName), _testNode)
                .exec()
    }
}

