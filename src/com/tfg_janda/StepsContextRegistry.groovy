package com.tfg_janda

class StepsContextRegistry implements Serializable {

    protected static StepsContext _context

    static void registerContext(context) {
        _context = context
    }

    static void registerDefaultContext(steps, String os) {
        _context = new StepsContext(steps)
        _context.setOs(os)
                .init()
    }

    static StepsContext getContext() {
        _context
    }
}
