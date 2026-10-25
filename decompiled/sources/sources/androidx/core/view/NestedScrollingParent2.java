package androidx.core.view;

import android.view.View;

/* loaded from: classes3.dex */
public interface NestedScrollingParent2 {
    void onNestedPreScroll(View r1, int r2, int r3, int[] r4, int r5);

    void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6);

    void onNestedScrollAccepted(View r1, View r2, int r3, int r4);

    boolean onStartNestedScroll(View r1, View r2, int r3, int r4);

    void onStopNestedScroll(View r1, int r2);
}
