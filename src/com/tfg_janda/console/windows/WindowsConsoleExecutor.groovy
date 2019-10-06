package com.tfg_janda.console.windows

import com.tfg_janda.console.IConsoleExecutor

class WindowsConsoleExecutor implements IConsoleExecutor{

    private _steps
    private static WindowsConsoleExecutor _instance

    WindowsConsoleExecutor(steps){
        this._steps = steps
    }

    static IConsoleExecutor getInstance(script){
        if(_instance == null) _instance = new WindowsConsoleExecutor(script)
        _instance
    }

    def static setInstance(steps){
        _instance = new WindowsConsoleExecutor(steps)
    }

    @Override
    String exec(String command) {
        this._steps.bat script: command, returnStdout: true
    }

    @Override
    void printMessage(String message) {
        this._steps.bat script: 'echo' + message
    }

    @Override
    def wipeOutWorkSpace() {
        this._steps.deleteDir()
    }
}
