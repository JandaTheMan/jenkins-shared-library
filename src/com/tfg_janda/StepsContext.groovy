package com.tfg_janda

import com.tfg_janda.console.IConsoleExecutor
import com.tfg_janda.console.linux.LinuxConsoleExecutor
import com.tfg_janda.console.windows.WindowsConsoleExecutor
import com.tfg_janda.scm.IScm
import com.tfg_janda.scm.git.GitScm

class StepsContext implements Serializable {

    private _script
    private String _os
    private String _scm
    private  _executionCommonValues

    StepsContext(steps) {
        this._script = steps
        _executionCommonValues = [:]
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
        IConsoleExecutor consoleExecutor
        switch (_os) {
            case 'linux':
                consoleExecutor = LinuxConsoleExecutor.getInstance(this._script)
                break
            case 'windows':
                consoleExecutor = WindowsConsoleExecutor.getInstance(this._script)
                break
            default:
                throw new Exception('The Context must be initialized with valid OS')
        }
        consoleExecutor
    }

    IScm getSourceControlManager() {
        IScm sourceControlManager
        switch (_scm) {
            case 'git':
                sourceControlManager = GitScm.getInstance(this._script)
                break
            default:
                throw new Exception('The Context must be initialized with valid SCM')
        }
        sourceControlManager
    }

    def setCommonExecutionValue(String key, String value){
        _executionCommonValues[key] = value
    }
    def getExecutionCommmonValue(String key){
        _executionCommonValues[key]
    }
}
