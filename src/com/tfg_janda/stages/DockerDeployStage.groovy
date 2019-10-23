package com.tfg_janda.stages

import com.tfg_janda.StepsContextRegistry
import com.tfg_janda.utils.Variables

class DockerDeployStage extends BaseStage{

    String _dockerRegistry
    String _projectName

    DockerDeployStage(String name, String dockerRegistry, projectName) {
        super(name)
        _dockerRegistry = dockerRegistry
        _projectName = projectName
    }

    @Override
    def stageSteps() {
        def console = StepsContextRegistry.getContext().getConsoleExecutor()
        def tag = StepsContextRegistry.getContext().getExecutionCommmonValue(Variables.GIT_TAG)
        console.exec('docker stop '+_projectName +' || true')
        console.exec('docker run -d -p 8080:8080 --name '+ _projectName +' '+_dockerRegistry+'/'+_projectName +'-artifact:'+tag)
    }
}
