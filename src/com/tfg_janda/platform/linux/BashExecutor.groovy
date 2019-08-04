package com.tfg_janda.platform.linux

import com.tfg_janda.platform.IConsoleExecutor


class BashExecutor implements IConsoleExecutor, Serializable {

    private _steps

    BashExecutor(steps) {
        this._steps = steps
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
}
