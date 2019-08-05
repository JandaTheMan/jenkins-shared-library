package com.tfg_janda.stages

import com.tfg_janda.StepsContextRegistry

class GreetingStage extends BaseStage{

    private String _greeting

    GreetingStage(String name, String greeting = 'Hello human being!') {
        super(name)
        _greeting = greeting
    }

    @Override
    def stageSteps(){
        def bash = StepsContextRegistry.getContext().getConsoleExecutor()
        bash.printMessage(_greeting)
    }
}
