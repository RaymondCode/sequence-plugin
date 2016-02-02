package org.intellij.component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Created by ryker.zhang on 2016/2/2.
 */
public class TestShowApplication implements ApplicationComponent {
    public TestShowApplication() {
    }

    @Override
    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    @Override
    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @Override
    @NotNull
    public String getComponentName() {
        return "TestShowApplication";
    }

    public void testShow() {
        Messages.showMessageDialog("Hello", "Sample", Messages.getInformationIcon());
    }
}
