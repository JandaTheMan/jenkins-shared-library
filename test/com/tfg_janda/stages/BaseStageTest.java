package com.tfg_janda.stages;

import com.tfg_janda.StepsContext;
import com.tfg_janda.StepsContextRegistry;
import com.tfg_janda.console.IConsoleExecutor;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BaseStageTest {

    private IConsoleExecutor bash;
    private StepsContext context;

    @Before
    public void setup() {

        //create mocks
        context = mock(StepsContext.class);
        bash = mock(IConsoleExecutor.class);
        //set context to context registry
        when(context.getConsoleExecutor()).thenReturn(bash);

        StepsContextRegistry.registerContext(context);

    }

    @Test
    public void GreetingStage_custom_greeting() {

        BaseStage stage = new GreetingStage("Say Hello","This stage only says you Hello!");
        stage.stageSteps();

        verify(bash).printMessage("This stage only says you Hello!");
    }

    @Test
    public void GreetingStage_default_greeting() {

        BaseStage stage = new GreetingStage("Say Hello");
        stage.stageSteps();

        verify(bash).printMessage("Hello human being!");
    }
}


