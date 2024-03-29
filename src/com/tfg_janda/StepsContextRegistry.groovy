package com.tfg_janda

class StepsContextRegistry implements Serializable {

    protected static StepsContext _context

    static void registerContext(context) {
        _context = context
    }

    static void registerDefaultContext(steps, String os, String scm) {
        _context = new StepsContext(steps)
        _context.setOs(os)
                .setScm(scm)
    }

    static StepsContext getContext() {
        _context
    }
}
