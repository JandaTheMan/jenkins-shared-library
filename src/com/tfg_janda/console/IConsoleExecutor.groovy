package com.tfg_janda.console

interface IConsoleExecutor {

    String exec(String command)

    void printMessage(String message)

    def wipeOutWorkSpace()
}
