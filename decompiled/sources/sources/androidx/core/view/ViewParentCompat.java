package androidx.core.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* loaded from: classes3.dex */
public abstract class ViewParentCompat {
    public static boolean onNestedFling(ViewParent r0, View r1, float r2, float r3, boolean r4) {
        return r0.onNestedFling(r1, r2, r3, r4);
    L4:
        e = move-exception;
        Log.e("ViewParentCompat", "ViewParent " + r0 + " does not implement interface method onNestedFling", e);
        return false;
    }

    public static boolean onNestedPreFling(ViewParent r0, View r1, float r2, float r3) {
        return r0.onNestedPreFling(r1, r2, r3);
    L4:
        e = move-exception;
        Log.e("ViewParentCompat", "ViewParent " + r0 + " does not implement interface method onNestedPreFling", e);
        return false;
    }

    public static void onNestedPreScroll(ViewParent r1, View r2, int r3, int r4, int[] r5, int r6) {
        if ((r1 instanceof NestedScrollingParent2) == false) goto L6;
        ((NestedScrollingParent2) r1).onNestedPreScroll(r2, r3, r4, r5, r6);
        return;
    L6:
        if (r6 != 0) goto L14;
        r1.onNestedPreScroll(r2, r3, r4, r5);     // Catch: AbstractMethodError -> L9
        return;
    L9:
        e = move-exception;
        Log.e("ViewParentCompat", "ViewParent " + r1 + " does not implement interface method onNestedPreScroll", e);
        return;
    }

    public static void onNestedScroll(ViewParent r3, View r4, int r5, int r6, int r7, int r8, int r9, int[] r10) {
        if ((r3 instanceof NestedScrollingParent3) == false) goto L6;
        ((NestedScrollingParent3) r3).onNestedScroll(r4, r5, r6, r7, r8, r9, r10);
        return;
    L6:
        r10[0] = r10[0] + r7;
        r10[1] = r10[1] + r8;
        if ((r3 instanceof NestedScrollingParent2) == false) goto L10;
        ((NestedScrollingParent2) r3).onNestedScroll(r4, r5, r6, r7, r8, r9);
        return;
    L10:
        if (r9 == 0) goto L17;
        return;
    L17:
        r3.onNestedScroll(r4, r5, r6, r7, r8);     // Catch: AbstractMethodError -> L14
        return;
    L14:
        e = move-exception;
        Log.e("ViewParentCompat", "ViewParent " + r3 + " does not implement interface method onNestedScroll", e);
    }

    public static void onNestedScrollAccepted(ViewParent r1, View r2, View r3, int r4, int r5) {
        if ((r1 instanceof NestedScrollingParent2) == false) goto L6;
        ((NestedScrollingParent2) r1).onNestedScrollAccepted(r2, r3, r4, r5);
        return;
    L6:
        if (r5 != 0) goto L14;
        r1.onNestedScrollAccepted(r2, r3, r4);     // Catch: AbstractMethodError -> L9
        return;
    L9:
        e = move-exception;
        Log.e("ViewParentCompat", "ViewParent " + r1 + " does not implement interface method onNestedScrollAccepted", e);
        return;
    }

    public static boolean onStartNestedScroll(ViewParent r1, View r2, View r3, int r4, int r5) {
        if ((r1 instanceof NestedScrollingParent2) == true) goto L5;
        if (r5 != 0) goto L15;
        return r1.onStartNestedScroll(r2, r3, r4);
    L9:
        e = move-exception;
        Log.e("ViewParentCompat", "ViewParent " + r1 + " does not implement interface method onStartNestedScroll", e);
        return false;
    L15:
        return false;
    L5:
        return ((NestedScrollingParent2) r1).onStartNestedScroll(r2, r3, r4, r5);
    }

    public static void onStopNestedScroll(ViewParent r1, View r2, int r3) {
        if ((r1 instanceof NestedScrollingParent2) == false) goto L6;
        ((NestedScrollingParent2) r1).onStopNestedScroll(r2, r3);
        return;
    L6:
        if (r3 != 0) goto L14;
        r1.onStopNestedScroll(r2);     // Catch: AbstractMethodError -> L9
        return;
    L9:
        e = move-exception;
        Log.e("ViewParentCompat", "ViewParent " + r1 + " does not implement interface method onStopNestedScroll", e);
        return;
    }
}
