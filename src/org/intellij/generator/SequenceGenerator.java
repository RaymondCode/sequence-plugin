package org.intellij.generator;

import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import org.intellij.generator.filter.MethodFilter;
import org.intellij.generator.filter.NoGetterSetter;
import org.intellij.utils.UmlFormatUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Generate PlantUML script of current method
 * Created by ryker.zhang on 2016/2/2.
 */
public class SequenceGenerator extends JavaElementVisitor {
    private String plantUMLScript = "";
    private Stack<PsiMethod> methodStack = new Stack<>();
    private final Integer LIMIT = 3;
    private final List<MethodFilter> filters = new ArrayList<>();

    public SequenceGenerator(PsiMethod psiMethod) {
        filters.add(new NoGetterSetter());
        methodStack.push(psiMethod);
        psiMethod.accept(this);
    }

    @Override
    public void visitElement(PsiElement psiElement) {
        psiElement.acceptChildren(this);
    }

    @Override
    public void visitMethodCallExpression(PsiMethodCallExpression psiMethodCallExpression) {
        PsiMethod psiMethod = psiMethodCallExpression.resolveMethod();
        PsiMethod baseMethod = methodStack.peek();

        if (psiMethod != null && !shouldSkip(psiMethod)) {
            methodStack.push(psiMethod);

            plantUMLScript += UmlFormatUtil.out(baseMethod, psiMethod);
            psiMethod.accept(this);
            plantUMLScript += UmlFormatUtil.in(baseMethod, psiMethod);

            // Make sure the initial method not deleted
            if (methodStack.size() > 1) {
                methodStack.pop();
            }
        }

        super.visitMethodCallExpression(psiMethodCallExpression);
    }

    private boolean shouldSkip(PsiMethod psiMethod) {
        for (MethodFilter filter : filters) {
            if (filter.skip(psiMethod)) {
                return true;
            }
        }

        return false;
    }

    public String get() {
        String source = "@startuml\n";
        source += plantUMLScript;
        source += "@enduml\n";
        return source;
    }
}
