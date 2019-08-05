package com.tfg_janda.stages

/**
 * BaseStage is basic stage class that provides the the stageSteps() function used to construct all the stages in the pipelines.
 */

abstract class BaseStage implements IStage, Serializable {

    protected String _name

    BaseStage(String name) {
        this._name = name
    }

    @Override
    String getName() {
        return _name
    }

    /**
     * Usage: To create custom stage:
     *  -create CustomStage.groovy class and extend this (BaseStage) class
     *  -override stage steps method providing the necessary steps in stage
     */

    def abstract stageSteps()

}
