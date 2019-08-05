package com.tfg_janda

import com.tfg_janda.platform.IConsoleExecutor
import com.tfg_janda.platform.linux.BashExecutor


class StepsContext implements Serializable {

    private _scrip
    private _os

    StepsContext(steps) {
        this._scrip = steps
    }

    StepsContext setOs(String os) {
        _os = os
        this
    }

    StepsContext init() {
        BashExecutor.setInstance(_scrip)
        this
    }

    IConsoleExecutor getConsoleExecutor() {
        def consoleExecutor
        switch (_os) {
            case 'linux':
                consoleExecutor = BashExecutor.getInstance()
                break
            default:
                throw Exception('The Context must be initialized with OS')
        }
        consoleExecutor
    }
}
