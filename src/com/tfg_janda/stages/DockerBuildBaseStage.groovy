package com.tfg_janda.stages

import com.tfg_janda.StepsContextRegistry
import com.tfg_janda.utils.Variables

abstract class DockerBuildBaseStage extends BaseStage{

    protected  String _dockerRegistry
    protected  String _projectName
    protected int _tag


    DockerBuildBaseStage(String stageName, String dockerRegistry, String project_name) {
        super(stageName)
        _dockerRegistry = dockerRegistry
        _projectName = project_name
    }

    @Override
    def stageSteps() {
        //Context and console
        def context = StepsContextRegistry.getContext()
        def console = context.getConsoleExecutor()

        //tag of the git HEAD
        def tag = console.exec('git log -1 --pretty=format:%h')
        context.setCommonExecutionValue(Variables.GIT_TAG, tag)


        console.exec("echo '" + dockerImage() +"'  > Dockerfile")
        console.exec("docker build -t artifact .")


        console.exec('docker tag artifact '+_dockerRegistry +'/'+_projectName+'-artifact:' + tag)
        console.exec('docker push ' + _dockerRegistry + '/' +_projectName+ '-artifact:' + tag)
    }

    abstract def dockerImage()
}
