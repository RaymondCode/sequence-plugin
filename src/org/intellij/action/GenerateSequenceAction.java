package org.intellij.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import org.intellij.component.TestShowApplication;

/**
 * Created by ryker.zhang on 2016/2/2.
 */
public class GenerateSequenceAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Application application = ApplicationManager.getApplication();
        TestShowApplication testShow = application.getComponent(TestShowApplication.class);
        if (testShow != null) {
            testShow.testShow();
        }
    }
}
