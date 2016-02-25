package org.intellij.generator.filter;

import com.intellij.psi.PsiMethod;

/**
 * Created by ryker.zhang on 2016/2/18.
 */
public interface MethodFilter {
    boolean skip(PsiMethod psiMethod);
}
