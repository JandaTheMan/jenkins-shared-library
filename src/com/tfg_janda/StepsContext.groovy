package com.tfg_janda

import com.tfg_janda.platform.IConsoleExecutor
import com.tfg_janda.platform.linux.BashExecutor
import com.tfg_janda.platform.windows.BatExecutor
import com.tfg_janda.scm.IScm
import com.tfg_janda.scm.git.GitScm


class StepsContext implements Serializable {

    private _script
    private _os
    private _scm

    StepsContext(steps) {
        this._script = steps
    }

    StepsContext setOs(String os) {
        _os = os
        this
    }

    StepsContext setScm(String scm) {
        _scm = scm
        this
    }

    IConsoleExecutor getConsoleExecutor() {
        def consoleExecutor
        switch (_os) {
            case 'linux':
                consoleExecutor = BashExecutor.getInstance(this._script)
                break
            case 'windows':
                consoleExecutor = BatExecutor.getInstance(this._script)
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
                sourceControlManager = GitScm.getInstance(this._script)
                break
            default:
                throw Exception('The Context must be initialized with SCM')
        }
        sourceControlManager
    }
}
