package com.tfg_janda.pipelines

import com.tfg_janda.console.OsTypes
import com.tfg_janda.stages.GreetingStage

class GreetingPipeline {

    private PipelineWrapper _pipeline
    private String _greetingMessage

    GreetingPipeline(script, String node, String greetingMessage) {
        _pipeline = new PipelineWrapper(
                script,
                node
        )
        _greetingMessage = greetingMessage
    }

    def run() {
        _pipeline
                .setUp(OsTypes.linux)
                .addStageToNode(new GreetingStage('Custom Greeting', _greetingMessage), 'master')
                .addStageToNode(new GreetingStage('Default Greeting'), 'master')
                .exec()
    }

}
