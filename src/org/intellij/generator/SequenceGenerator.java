package org.intellij.generator;

import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import org.intellij.utils.PsiUtil;

/**
 * Generate sequence scripts
 * Created by ryker.zhang on 2016/2/2.
 */
public class SequenceGenerator extends JavaElementVisitor {
    String text = "";

    public SequenceGenerator(PsiMethod psiMethod) {
        psiMethod.accept(this);
    }

    @Override
    public void visitElement(PsiElement psiElement) {
        PsiUtil.acceptChildren(psiElement, this);
    }

    @Override
    public void visitMethodCallExpression(PsiMethodCallExpression psiMethodCallExpression) {
        PsiMethod psiMethod = psiMethodCallExpression.resolveMethod();
        if (psiMethod != null) {
            text += psiMethod.getName();
            text += "\n";
            psiMethod.accept(this);
        }
        super.visitMethodCallExpression(psiMethodCallExpression);
    }

    public String get() {
        return text;
    }
}
