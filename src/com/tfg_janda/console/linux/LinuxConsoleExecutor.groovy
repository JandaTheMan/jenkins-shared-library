package com.tfg_janda.console.linux

import com.tfg_janda.console.IConsoleExecutor


class LinuxConsoleExecutor implements IConsoleExecutor, Serializable {

    private _steps
    private static LinuxConsoleExecutor _instance

    private LinuxConsoleExecutor(steps) {
        this._steps = steps
    }

    static IConsoleExecutor getInstance(script) {
        if(_instance == null) _instance = new LinuxConsoleExecutor(script)
        _instance
    }

    def static setInstance(_steps) {
        _instance = new LinuxConsoleExecutor(_steps)
    }

    @Override
    int exec(String command) {
        this._steps.sh returnStatus: true, script: "$command"
    }

    @Override
    void printMessage(String message) {
        def command = 'echo "' + message + '"'
        this.exec(command)
    }

    @Override
    def wipeOutWorkSpace() {
        this._steps.deleteDir()
    }
}
