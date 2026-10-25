package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes3.dex */
public class NestedScrollingParentHelper {
    private int mNestedScrollAxesNonTouch;
    private int mNestedScrollAxesTouch;

    public NestedScrollingParentHelper(ViewGroup r1) {
    }

    public int getNestedScrollAxes() {
        int r0 = this.mNestedScrollAxesTouch;
        return this.mNestedScrollAxesNonTouch | r0;
    }

    public void onNestedScrollAccepted(View r2, View r3, int r4) {
        onNestedScrollAccepted(r2, r3, r4, 0);
    }

    public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
        if (r4 != 1) goto L6;
        this.mNestedScrollAxesNonTouch = r3;
        return;
    L6:
        this.mNestedScrollAxesTouch = r3;
    }

    public void onStopNestedScroll(View r2) {
        onStopNestedScroll(r2, 0);
    }

    public void onStopNestedScroll(View r2, int r3) {
        if (r3 != 1) goto L6;
        this.mNestedScrollAxesNonTouch = 0;
        return;
    L6:
        this.mNestedScrollAxesTouch = 0;
    }
}
