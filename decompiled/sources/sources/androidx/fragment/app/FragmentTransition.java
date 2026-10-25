package androidx.fragment.app;

import android.view.View;
import androidx.collection.ArrayMap;
import androidx.transition.FragmentTransitionSupport;
import java.util.ArrayList;

/* loaded from: classes3.dex */
abstract class FragmentTransition {
    static final FragmentTransitionImpl PLATFORM_IMPL = null;
    static final FragmentTransitionImpl SUPPORT_IMPL = null;

    static {
        PLATFORM_IMPL = new FragmentTransitionCompat21();
        SUPPORT_IMPL = resolveSupportImpl();
    }

    public static void callSharedElementStartEnd(Fragment r0, Fragment r1, boolean r2, ArrayMap<String, View> r3, boolean r4) {
        if (r2 == false) goto L5;
        r1.getEnterTransitionCallback();
        return;
    L5:
        r0.getEnterTransitionCallback();
    }

    private static FragmentTransitionImpl resolveSupportImpl() {
        return (FragmentTransitionImpl) FragmentTransitionSupport.class.getDeclaredConstructor(null).newInstance(null);
    L5:
        return null;
    }

    public static void retainValues(ArrayMap<String, String> r2, ArrayMap<String, View> r3) {
        int r0 = r2.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (r3.containsKey(r2.valueAt(r0)) == true) goto L7;
        r2.removeAt(r0);
    L7:
        r0 = r0 - 1;
        goto L3
    }

    public static void setViewVisibility(ArrayList<View> r2, int r3) {
        if (r2 == null) goto L7;
        int r0 = r2.size() - 1;
    L5:
        if (r0 < 0) goto L9;
        r2.get(r0).setVisibility(r3);
        r0 = r0 - 1;
        goto L5
    L9:
        return;
    }
}
