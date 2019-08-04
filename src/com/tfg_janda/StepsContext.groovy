package com.tfg_janda

import com.tfg_janda.platform.IConsoleExecutor
import com.tfg_janda.platform.linux.BashExecutor


class StepsContext implements Serializable {

    private _steps
    private _os

    StepsContext(steps) {
        this._steps = steps
    }

    def setOs(String os) {
        _os = os
    }

    IConsoleExecutor getConsoleExecutor() {
        def consoleExecutor
        switch (_os) {
            case 'linux':
                consoleExecutor = new BashExecutor(this._steps)
                break
            default:
                throw Exception('The Context must be initialized with OS')
        }
        consoleExecutor
    }
}
