package com.tfg_janda

import com.tfg_janda.console.OsTypes

class StepsContextRegistry implements Serializable {

    protected static StepsContext _context

    static void registerContext(context) {
        _context = context
    }

    static void registerDefaultContext(steps, OsTypes os = null , String scm = null) {
        _context = new StepsContext(steps)
        _context.setOs(os)
                .setScm(scm)
    }

    static StepsContext getContext() {
        _context
    }
}
