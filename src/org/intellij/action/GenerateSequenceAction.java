package org.intellij.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiMethod;
import org.intellij.component.GraphWindow;
import org.intellij.component.SequencePlugin;
import org.intellij.generator.SequenceGenerator;

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

        updateUMLImage(project, generator);
    }

    private void updateUMLImage(final Project project, final SequenceGenerator generator) {
        ToolWindowManager windowManager = ToolWindowManager.getInstance(project);
        final ToolWindow toolWindow = windowManager.getToolWindow("Graph");

        if (toolWindow.isVisible()) {
            GraphWindow.generateUMLImage(toolWindow, generator);
        } else {
            toolWindow.show(new Runnable() {
                @Override
                public void run() {
                    GraphWindow.generateUMLImage(toolWindow, generator);
                }
            });
        }
    }

    private SequencePlugin getPlugin(AnActionEvent event) {
        Project project = event.getRequiredData(CommonDataKeys.PROJECT);
        return getPlugin(project);
    }

    private SequencePlugin getPlugin(Project project) {
        return SequencePlugin.getInstance(project);
    }
}
