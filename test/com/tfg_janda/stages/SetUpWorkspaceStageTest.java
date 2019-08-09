package com.tfg_janda.stages;

import com.tfg_janda.StepsContext;
import com.tfg_janda.StepsContextRegistry;
import com.tfg_janda.platform.IConsoleExecutor;
import com.tfg_janda.scm.IScm;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class SetUpWorkspaceStageTest {

    private IConsoleExecutor bash;
    private IScm git;
    private StepsContext context;

    @Before
    public void setup() {

        //create mocks
        context = mock(StepsContext.class);
        bash = mock(IConsoleExecutor.class);
        git = mock(IScm.class);
        //set context to context registry
        when(context.getConsoleExecutor()).thenReturn(bash);
        when(context.getSourceControlManager()).thenReturn(git);

        StepsContextRegistry.registerContext(context);

    }

    @Test
    public void testWorkingDirectorySetUp() {

        SetUpWorkspaceStage stage = new SetUpWorkspaceStage("SetUpDirectory","GIT_URL","GIT_BRANCH","GIT_CREDENTIALS");
        stage.stageSteps();

        verify(bash).removeDirectoryFiles();
        verify(git).cloneAndCheckout("GIT_URL","GIT_BRANCH","GIT_CREDENTIALS");
    }
}
