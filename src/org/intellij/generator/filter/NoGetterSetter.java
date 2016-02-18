package org.intellij.generator.filter;

import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PropertyUtil;

/**
 * Skip getter and setter
 * Created by ryker.zhang on 2016/2/18.
 */
public class NoGetterSetter implements MethodFilter {
    @Override
    public boolean skip(PsiMethod psiMethod) {
        return PropertyUtil.isSimplePropertyGetter(psiMethod) || PropertyUtil.isSimplePropertySetter(psiMethod);
    }
}
