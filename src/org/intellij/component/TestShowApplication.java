package org.intellij.component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Test dialog for showing test information
 * Created by ryker.zhang on 2016/2/2.
 */
public class TestShowApplication implements ApplicationComponent {
    public TestShowApplication() {
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "TestShowApplication";
    }

    public void testShow(String context) throws IOException {
        Messages.showMessageDialog(context, "Sample", Messages.getInformationIcon());
    }
}
