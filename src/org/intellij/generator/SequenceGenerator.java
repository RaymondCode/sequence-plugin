package org.intellij.generator;

import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import org.intellij.utils.PsiUtil;

import java.util.Stack;

/**
 * Created by ryker.zhang on 2016/2/2.
 */
public class SequenceGenerator extends JavaElementVisitor {
    String text = "";
    Stack<PsiMethod> methodStack = new Stack<>();

    public SequenceGenerator(PsiMethod psiMethod) {
        methodStack.push(psiMethod);
        psiMethod.accept(this);
    }

    @Override
    public void visitElement(PsiElement psiElement) {
        PsiUtil.acceptChildren(psiElement, this);
    }

    @Override
    public void visitMethodCallExpression(PsiMethodCallExpression psiMethodCallExpression) {
        PsiMethod psiMethod = psiMethodCallExpression.resolveMethod();
        PsiMethod baseMethod = methodStack.peek();

        if (psiMethod != null) {
            methodStack.push(psiMethod);

            text += baseMethod.getName() + "->" + psiMethod.getName();
            text += "\n";

            psiMethod.accept(this);

            text += baseMethod.getName() + "<-" + psiMethod.getName();
            text += "\n";

            // Make sure the initial method not deleted
            if (methodStack.size() > 1) {
                methodStack.pop();
            }
        }

        super.visitMethodCallExpression(psiMethodCallExpression);
    }

    public String get() {
        return text;
    }
}
