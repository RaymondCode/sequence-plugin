package org.intellij.component;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import net.sourceforge.plantuml.SourceStringReader;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by ryker.zhang on 2016/3/1.
 */
public class GraphWindow implements ToolWindowFactory {
    private static Logger logger = Logger.getInstance(GraphWindow.class);

    private JPanel myToolWindowContent;
    private ToolWindow myToolWindow;
    private JLabel label;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        myToolWindow = toolWindow;
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);

        String source = "@startuml\n";
        source += "Bob -> Alice : hello\n";
        source += "@enduml\n";

        SourceStringReader reader = new SourceStringReader(source);

        try {
            ByteArrayOutputStream png = new ByteArrayOutputStream();
            reader.generateImage(png);

            ByteArrayInputStream input = new ByteArrayInputStream(png.toByteArray());
            BufferedImage myPicture = ImageIO.read(input);
            label.setIcon(new ImageIcon(myPicture));

            png.close();
        } catch (IOException e) {
            logger.error("Generate uml image failed", e);
        }
    }
}
