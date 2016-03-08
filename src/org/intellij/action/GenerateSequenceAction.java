package org.intellij.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiMethod;
import com.intellij.ui.content.Content;
import org.intellij.component.GraphWindow;
import org.intellij.component.SequencePlugin;
import org.intellij.component.TestShowApplication;
import org.intellij.generator.SequenceGenerator;

import javax.swing.*;
import java.io.IOException;

/**
 * Generate sequence graph action
 * Created by ryker.zhang on 2016/2/2.
 */
public class GenerateSequenceAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        SequencePlugin plugin = getPlugin(actionEvent);
        PsiMethod psiMethod = plugin.getCurrentPsiMethod();

        SequenceGenerator generator = new SequenceGenerator(psiMethod);

        Project project = actionEvent.getRequiredData(CommonDataKeys.PROJECT);

        updateUMLImage(project);

        Application application = ApplicationManager.getApplication();
        TestShowApplication testShow = application.getComponent(TestShowApplication.class);
        if (testShow != null) {
            try {
                testShow.testShow(generator.get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUMLImage(Project project) {
        ToolWindowManager windowManager = ToolWindowManager.getInstance(project);
        ToolWindow toolWindow = windowManager.getToolWindow("Graph");
        Content content = toolWindow.getContentManager().getContent(0);
        JPanel panel = (JPanel) content.getComponent();
        JLabel label = (JLabel) panel.getComponent(0);

        String source = "@startuml\n";
        source += "ButtonBob -> Alice : hello\n";
        source += "@enduml\n";

        GraphWindow.updateImageWithUML(source, label);
    }

    private SequencePlugin getPlugin(AnActionEvent event) {
        Project project = event.getRequiredData(CommonDataKeys.PROJECT);
        return getPlugin(project);
    }

    private SequencePlugin getPlugin(Project project) {
        return SequencePlugin.getInstance(project);
    }
}
