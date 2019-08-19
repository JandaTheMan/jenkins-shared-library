package com.tfg_janda.stages

import com.tfg_janda.StepsContextRegistry

class SetUpWorkspaceStage extends BaseStage{

    private String _gitRepo
    private String _gitBranch
    private String _gitCredentials

    SetUpWorkspaceStage(String name, String gitRepo, String gitBranch, String gitCredentials) {
        super(name)
        _gitRepo = gitRepo
        _gitBranch = gitBranch
        _gitCredentials = gitCredentials
    }

    @Override
    def stageSteps() {

        def context = StepsContextRegistry.getContext()
        def console = context.getConsoleExecutor()
        def scm = context.getSourceControlManager()

        //Remove all items from working directory
        console.wipeOutWorkSpace()

        //Download the git project and checkout branch
        scm.cloneAndCheckout(_gitRepo, _gitBranch, _gitCredentials)
    }
}
