package com.tfg_janda.pipelines

import com.tfg_janda.StepsContextRegistry
import com.tfg_janda.stages.IStage


class PipelineWrapper implements Serializable {

    protected _script
    protected  _nodeStages

    PipelineWrapper(script) {
        this._script = script
        this._nodeStages = [:]
    }

    /**
     * Set up of pipeline. Construction of the de registry with the script.
     * @param os : operating system type where the pipeline is executed.
     */
    PipelineWrapper setUp(String os = null, String scm = null) {
        //Storing the script context to access jenkins pipeline.gdsl constructs as node, stage and sh or git.
        StepsContextRegistry.registerDefaultContext(_script, os, scm)
        return this
    }

    PipelineWrapper addStageToNode(IStage stage, String node) {
        if(_nodeStages[node] == null) _nodeStages[node]=[]
        _nodeStages[node] << stage
        return this
    }

    protected executeStageSteps(IStage stage) {
        _script.stage(stage.getName()) {
            stage.stageSteps()
        }
    }

    protected exec() {
        _nodeStages.each {
            node, stages -> _script.node(node) { stages.each {stage->executeStageSteps(stage as IStage) } }
        }

    }

}
