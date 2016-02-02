package org.intellij.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiMethod;
import org.intellij.component.SequencePlugin;
import org.intellij.component.TestShowApplication;
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

        Application application = ApplicationManager.getApplication();
        TestShowApplication testShow = application.getComponent(TestShowApplication.class);
        if (testShow != null) {
            testShow.testShow(generator.get());
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
