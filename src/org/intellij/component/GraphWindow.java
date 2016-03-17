package org.intellij.component;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import net.sourceforge.plantuml.SourceStringReader;
import org.intellij.generator.SequenceGenerator;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * UML绘图窗口
 * Created by ryker.zhang on 2016/3/1.
 */
public class GraphWindow implements ToolWindowFactory {
    private static Logger logger = Logger.getInstance(GraphWindow.class);

    private JPanel myToolWindowContent;
    private JLabel label;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindowContent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    public static void generateUMLImage(final ToolWindow toolWindow, final SequenceGenerator generator) {
        Content content = toolWindow.getContentManager().getContent(0);

        if (content != null && content.isValid()) {
            JPanel panel = (JPanel) content.getComponent();
            JLabel label = (JLabel) panel.getComponent(0);
            refreshImageWithUML(generator.get(), label);
        }
    }

    private static void refreshImageWithUML(String umlSource, JLabel imageLabel) {
        SourceStringReader reader = new SourceStringReader(umlSource);

        try {
            ByteArrayOutputStream png = new ByteArrayOutputStream();
            reader.generateImage(png);

            ByteArrayInputStream input = new ByteArrayInputStream(png.toByteArray());
            BufferedImage myPicture = ImageIO.read(input);

            imageLabel.setIcon(new ImageIcon(myPicture));

            png.close();
        } catch (IOException e) {
            logger.error("Refresh uml image failed", e);
        }
    }
}
