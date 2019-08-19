package com.tfg_janda

import com.tfg_janda.console.IConsoleExecutor
import com.tfg_janda.console.OsTypes
import com.tfg_janda.console.linux.LinuxConsoleExecutor
import com.tfg_janda.console.windows.WindowsConsoleExecutor
import com.tfg_janda.scm.IScm
import com.tfg_janda.scm.git.GitScm

class StepsContext implements Serializable {

    private _script
    private OsTypes _os
    private _scm

    StepsContext(steps) {
        this._script = steps
    }

    StepsContext setOs(OsTypes os) {
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
            case OsTypes.linux:
                consoleExecutor = LinuxConsoleExecutor.getInstance(this._script)
                break
            case OsTypes.windows:
                consoleExecutor = WindowsConsoleExecutor.getInstance(this._script)
                break
            default:
                throw new Exception('The Context must be initialized with OS')
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
                throw new Exception('The Context must be initialized with SCM')
        }
        sourceControlManager
    }
}
