package com.tfg_janda.console

interface IConsoleExecutor {

    int exec(String command)

    void printMessage(String message)

    def wipeOutWorkSpace()
}
