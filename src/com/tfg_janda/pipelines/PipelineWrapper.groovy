package com.tfg_janda.pipelines

import com.tfg_janda.StepsContextRegistry
import com.tfg_janda.console.OsTypes
import com.tfg_janda.scm.ScmTypes
import com.tfg_janda.stages.IStage

/**
 * BasePipeline is basic pipeline class.
 * @param node : jenkins executor node where stages will be executed
 */

class PipelineWrapper implements Serializable {

    protected _script
    protected String _node

    protected  _nodeStages

    PipelineWrapper(script, String node) {
        this._script = script
        this._node = node
        this._nodeStages = [:]
    }

    /**
     * Set up of pipeline. Construction of the de registry with the script.
     * @param os : operating system where the pipeline is executed.
     */
    PipelineWrapper setUp(String os = null, String scm = null) {
        //Storing the script (context) to access the script objects as node, stage and sh or git
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
