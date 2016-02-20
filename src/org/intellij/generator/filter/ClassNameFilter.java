package org.intellij.generator.filter;

import com.intellij.psi.PsiMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class name filter
 * Created by raymond on 2016/2/20.
 */
public class ClassNameFilter implements MethodFilter {
    private final List<String> SKIP_CLASSE_NAMES = new ArrayList<>();

    public ClassNameFilter(String... names) {
        Collections.addAll(SKIP_CLASSE_NAMES, names);
    }

    @Override
    public boolean skip(PsiMethod psiMethod) {
        if (psiMethod.getContainingClass() != null) {
            String currentMethodClass = psiMethod.getContainingClass().getName();
            return SKIP_CLASSE_NAMES.contains(currentMethodClass);
        } else {
            return true;
        }
    }
}
