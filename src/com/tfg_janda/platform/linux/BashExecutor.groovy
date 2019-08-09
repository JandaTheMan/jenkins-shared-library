package com.tfg_janda.platform.linux

import com.tfg_janda.platform.IConsoleExecutor


class BashExecutor implements IConsoleExecutor, Serializable {

    private _steps
    private static BashExecutor _instance

    private BashExecutor(steps) {
        this._steps = steps
    }

    static BashExecutor getInstance(script = null) {
        if(_instance == null) _instance = new BashExecutor(script)
        _instance
    }

    def static setInstance(_steps) {
        _instance = new BashExecutor(_steps)
    }

    @Override
    int exec(String command) {
        this._steps.sh returnStatus: true, script: "$command"
    }

    @Override
    void error(String errorMessage) {
        this._steps.error(errorMessage)
    }

    @Override
    void printMessage(String message) {
        def command = 'echo "' + message + '"'
        this.exec(command)
    }

    @Override
    def removeDirectoryFiles() {
        this.exec("rm -rf ./*")
    }
}
