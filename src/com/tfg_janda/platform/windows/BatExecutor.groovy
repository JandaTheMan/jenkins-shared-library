package com.tfg_janda.platform.windows

import com.tfg_janda.platform.IConsoleExecutor

class BatExecutor implements IConsoleExecutor{

    private _steps
    private static BatExecutor _instance

    BatExecutor(steps){
        this._steps = steps
    }

    static BatExecutor getInstance(script = null){
        if(_instance == null) _instance = new BatExecutor(script)
        _instance
    }

    def static setInstance(steps){
        _instance = new BatExecutor(steps)
    }
    @Override
    int exec(String command) {
        this._steps.bat script: command
    }

    @Override
    void error(String errorMessage) {
        this._steps.error(errorMessage)
    }

    @Override
    void printMessage(String message) {
        this._steps.bat script: 'echo' + message
    }

    @Override
    def removeDirectoryFiles() {
        this._steps.deleteDir
    }
}
