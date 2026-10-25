package org.telegram.ui.LNavigation;

import java.util.ArrayList;
import java.util.List;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;

/* loaded from: classes7.dex */
public abstract class NavigationExt {

    public interface FragmentConsumer {
        boolean consume(BaseFragment r1);
    }

    public static boolean backToFragment(BaseFragment r6, FragmentConsumer r7) {
        if (r6 != null) goto L5;
    L20:
        return false;
    L5:
        if (r6.getParentLayout() == null) goto L20;
        INavigationLayout r1 = r6.getParentLayout();
        BaseFragment r62 = r6.getParentLayout().getLastFragment();
        List<BaseFragment> r2 = r62.getParentLayout().getFragmentStack();
        ArrayList r3 = new ArrayList();
        int r12 = r1.getFragmentStack().size() - 1;
    L8:
        if (r12 < 0) goto L20;
        if (r7.consume(r2.get(r12)) == true) goto L11;
        r3.add(r2.get(r12));
        r12 = r12 - 1;
        goto L8
    L11:
        int r72 = r3.size() - 1;
    L12:
        if (r72 < 0) goto L17;
        if (r3.get(r72) == r62) goto L16;
        ((BaseFragment) r3.get(r72)).removeSelfFromStack();
    L16:
        r72 = r72 - 1;
        goto L12
    L17:
        r62.finishFragment();
        return true;
    }
}
