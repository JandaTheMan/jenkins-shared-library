package com.tfg_janda.scm.git

import com.tfg_janda.scm.IScm

class GitScm implements IScm{

    private _steps
    private static GitScm _instance

    private GitScm(steps) {
        this._steps = steps
    }

    static GitScm getInstance(script) {
        if(_instance == null) _instance = new GitScm(script)
        _instance
    }

    def static setInstance(_steps) {
        _instance = new GitScm(_steps)
    }

    @Override
    void cloneAndCheckout(String url, String branch, String credentials) {
        _steps.git(
                url: url,
                branch: branch,
                credentialsId: credentials
        )
    }
}
