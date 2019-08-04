package com.tfg_janda.platform

interface IConsoleExecutor {

    int exec(String command)

    void error(String errorMessage)

    void printMessage(String message)

}
