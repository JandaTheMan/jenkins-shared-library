package com.tfg_janda.stages

import com.tfg_janda.StepsContextRegistry

class BuildStage extends BaseStage{


    BuildStage(String name = 'Build') {
        super(name)
    }

    @Override
    def stageSteps(){
        def console = StepsContextRegistry.getContext().getConsoleExecutor()
        console.exec('make build')
    }
}
