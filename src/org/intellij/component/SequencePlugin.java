package org.intellij.component;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import org.intellij.utils.PsiUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Generate sequence graph code of plantUML
 * Created by ryker.zhang on 2016/2/2.
 */
public class SequencePlugin implements ProjectComponent {
    private final Project project;

    public SequencePlugin(Project project) {
        this.project = project;
    }

    public static SequencePlugin getInstance(Project project) {
        return project.getComponent(SequencePlugin.class);
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
        return "SequencePlugin";
    }

    @Override
    public void projectOpened() {
        // called when project is opened
    }

    @Override
    public void projectClosed() {
        // called when project is being closed
    }

    public PsiMethod getCurrentPsiMethod() {
        Editor editor = getSelectedEditor();
        if (editor == null)
            return null;
        VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(editor.getDocument());
        if (virtualFile == null)
            return null;
        PsiFile psiFile = getPsiFile(virtualFile);
        return PsiUtil.getEnclosingMethod(psiFile, editor.getCaretModel().getOffset());
    }

    private Editor getSelectedEditor() {
        Editor selectedEditor = getFileEditorManager().getSelectedTextEditor();
        if(selectedEditor == null)
            return null;
        return selectedEditor;
    }

    private FileEditorManager getFileEditorManager() {
        return FileEditorManager.getInstance(project);
    }

    private PsiFile getPsiFile(VirtualFile virtualFile) {
        return PsiManager.getInstance(project).findFile(virtualFile);
    }
}
