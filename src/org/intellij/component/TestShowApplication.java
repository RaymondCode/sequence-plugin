package org.intellij.component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;
import net.sourceforge.plantuml.SourceStringReader;
import org.jetbrains.annotations.NotNull;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
        OutputStream png = new FileOutputStream("D:\\test\\test.png");
        String source = "@startuml\n";
        source += "Bob -> Alice : hello\n";
        source += "@enduml\n";

        SourceStringReader reader = new SourceStringReader(source);
        reader.generateImage(png);

        try {
            png.close();
        } catch (Exception ignored) {
        }

        Messages.showMessageDialog(context, "Sample", Messages.getInformationIcon());
    }
}
