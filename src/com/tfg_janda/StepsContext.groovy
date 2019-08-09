package com.tfg_janda

import com.tfg_janda.platform.IConsoleExecutor
import com.tfg_janda.platform.linux.BashExecutor
import com.tfg_janda.scm.IScm
import com.tfg_janda.scm.git.GitScm


class StepsContext implements Serializable {

    private _scrip
    private _os
    private _scm

    StepsContext(steps) {
        this._scrip = steps
    }

    StepsContext setOs(String os) {
        _os = os
        this
    }

    StepsContext setScm(String scm) {
        _scm = scm
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

    IScm getSourceControlManager() {
        def sourceControlManager
        switch (_scm) {
            case 'git':
                sourceControlManager = GitScm.getInstance()
                break
            default:
                throw Exception('The Context must be initialized with SCM')
        }
        sourceControlManager
    }
}
