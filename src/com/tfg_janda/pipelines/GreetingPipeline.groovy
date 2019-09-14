package com.tfg_janda.pipelines


import com.tfg_janda.stages.GreetingStage

class GreetingPipeline {

    private PipelineWrapper _pipeline
    private String _greetingMessage

    GreetingPipeline(script, String greetingMessage) {
        _pipeline = new PipelineWrapper(
                script,
        )
        _greetingMessage = greetingMessage
    }

    def run() {
        _pipeline
                .setUp('linux')
                .addStageToNode(new GreetingStage('Custom Greeting', _greetingMessage), 'master')
                .addStageToNode(new GreetingStage('Default Greeting'), 'master')
                .exec()
    }

}
