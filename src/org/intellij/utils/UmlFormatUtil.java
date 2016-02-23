package org.intellij.utils;

import com.intellij.psi.PsiMethod;

/**
 * PlantUML formatter
 * Created by ryker.zhang on 2016/2/15.
 */
public class UmlFormatUtil {
    public static String in(PsiMethod leftMethod, PsiMethod rightMethod) {
        return connect(leftMethod, rightMethod, "<--") + "\n";
    }

    public static String out(PsiMethod leftMethod, PsiMethod rightMethod) {
        return connect(leftMethod, rightMethod, "->") + "\n";
    }

    private static String connect(PsiMethod leftMethod, PsiMethod rightMethod, String symbol) {
        return leftMethod.getName() + symbol + rightMethod.getName();
    }
}
