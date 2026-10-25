package androidx.core.view;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class NestedScrollingChildHelper {
    private boolean mIsNestedScrollingEnabled;
    private ViewParent mNestedScrollingParentNonTouch;
    private ViewParent mNestedScrollingParentTouch;
    private int[] mTempNestedScrollConsumed;
    private final View mView;

    public NestedScrollingChildHelper(View r1) {
        this.mView = r1;
    }

    private boolean dispatchNestedScrollInternal(int r14, int r15, int r16, int r17, int[] r18, int r19, int[] r20) {
        if (isNestedScrollingEnabled() == false) goto L25;
        ViewParent r3 = getNestedScrollingParentForType(r19);
        if (r3 != null) goto L8;
        return false;
    L8:
        if (r14 != 0) goto L15;
        if (r15 != 0) goto L15;
        if (r16 != 0) goto L15;
        if (r17 != 0) goto L15;
        if (r18 == null) goto L25;
        r18[0] = 0;
        r18[1] = 0;
    L15:
        if (r18 == null) goto L17;
        this.mView.getLocationInWindow(r18);
        int r11 = r18[0];
        int r12 = r18[1];
    L18:
        if (r20 != null) goto L20;
        int[] r4 = getTempNestedScrollConsumed();
        r4[0] = 0;
        r4[1] = 0;
        int[] r10 = r4;
    L21:
        ViewParentCompat.onNestedScroll(r3, this.mView, r14, r15, r16, r17, r19, r10);
        if (r18 == null) goto L24;
        this.mView.getLocationInWindow(r18);
        r18[0] = r18[0] - r11;
        r18[1] = r18[1] - r12;
    L24:
        return true;
    L20:
        r10 = r20;
        goto L21
    L17:
        r11 = 0;
        r12 = 0;
    L25:
        return false;
    }

    private ViewParent getNestedScrollingParentForType(int r2) {
        if (r2 == 0) goto L10;
        if (r2 == 1) goto L8;
        return null;
    L8:
        return this.mNestedScrollingParentNonTouch;
    L10:
        return this.mNestedScrollingParentTouch;
    }

    private int[] getTempNestedScrollConsumed() {
        if (this.mTempNestedScrollConsumed != null) goto L6;
        this.mTempNestedScrollConsumed = new int[2];
    L6:
        return this.mTempNestedScrollConsumed;
    }

    private void setNestedScrollingParentForType(int r2, ViewParent r3) {
        if (r2 != 0) goto L4;
        this.mNestedScrollingParentTouch = r3;
        return;
    L4:
        if (r2 == 1) goto L6;
        return;
    L6:
        this.mNestedScrollingParentNonTouch = r3;
    }

    public boolean dispatchNestedFling(float r3, float r4, boolean r5) {
        if (isNestedScrollingEnabled() == false) goto L8;
        ViewParent r0 = getNestedScrollingParentForType(0);
        if (r0 == null) goto L8;
        return ViewParentCompat.onNestedFling(r0, this.mView, r3, r4, r5);
    L8:
        return false;
    }

    public boolean dispatchNestedPreFling(float r3, float r4) {
        if (isNestedScrollingEnabled() == false) goto L8;
        ViewParent r0 = getNestedScrollingParentForType(0);
        if (r0 == null) goto L8;
        return ViewParentCompat.onNestedPreFling(r0, this.mView, r3, r4);
    L8:
        return false;
    }

    public boolean dispatchNestedPreScroll(int r7, int r8, int[] r9, int[] r10) {
        return dispatchNestedPreScroll(r7, r8, r9, r10, 0);
    }

    public boolean dispatchNestedPreScroll(int r11, int r12, int[] r13, int[] r14, int r15) {
        if (isNestedScrollingEnabled() == false) goto L28;
        ViewParent r2 = getNestedScrollingParentForType(r15);
        if (r2 != null) goto L8;
        return false;
    L8:
        if (r11 != 0) goto L13;
        if (r12 != 0) goto L13;
        if (r14 == null) goto L28;
        r14[0] = 0;
        r14[1] = 0;
    L13:
        if (r14 == null) goto L15;
        this.mView.getLocationInWindow(r14);
        int r8 = r14[0];
        int r9 = r14[1];
    L16:
        if (r13 != null) goto L18;
        r13 = getTempNestedScrollConsumed();
    L18:
        int[] r6 = r13;
        r6[0] = 0;
        r6[1] = 0;
        ViewParentCompat.onNestedPreScroll(r2, this.mView, r11, r12, r6, r15);
        if (r14 == null) goto L22;
        this.mView.getLocationInWindow(r14);
        r14[0] = r14[0] - r8;
        r14[1] = r14[1] - r9;
    L22:
        if (r6[0] == 0) goto L24;
    L27:
        return true;
    L24:
        if (r6[1] != 0) goto L27;
        return false;
    L15:
        r8 = 0;
        r9 = 0;
    L28:
        return false;
    }

    public void dispatchNestedScroll(int r1, int r2, int r3, int r4, int[] r5, int r6, int[] r7) {
        dispatchNestedScrollInternal(r1, r2, r3, r4, r5, r6, r7);
    }

    public boolean dispatchNestedScroll(int r9, int r10, int r11, int r12, int[] r13) {
        return dispatchNestedScrollInternal(r9, r10, r11, r12, r13, 0, null);
    }

    public boolean dispatchNestedScroll(int r9, int r10, int r11, int r12, int[] r13, int r14) {
        return dispatchNestedScrollInternal(r9, r10, r11, r12, r13, r14, null);
    }

    public boolean hasNestedScrollingParent() {
        return hasNestedScrollingParent(0);
    }

    public boolean hasNestedScrollingParent(int r1) {
        if (getNestedScrollingParentForType(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isNestedScrollingEnabled() {
        return this.mIsNestedScrollingEnabled;
    }

    public void setNestedScrollingEnabled(boolean r2) {
        if (this.mIsNestedScrollingEnabled == false) goto L5;
        ViewCompat.stopNestedScroll(this.mView);
    L5:
        this.mIsNestedScrollingEnabled = r2;
    }

    public boolean startNestedScroll(int r2) {
        return startNestedScroll(r2, 0);
    }

    public boolean startNestedScroll(int r5, int r6) {
        if (hasNestedScrollingParent(r6) == false) goto L6;
        return true;
    L6:
        if (isNestedScrollingEnabled() == false) goto L17;
        ViewParent r0 = this.mView.getParent();
        View r2 = this.mView;
    L8:
        if (r0 == null) goto L23;
        if (ViewParentCompat.onStartNestedScroll(r0, r2, this.mView, r5, r6) == true) goto L11;
        if ((r0 instanceof View) == false) goto L16;
        r2 = (View) r0;
    L16:
        r0 = r0.getParent();
        goto L8
    L11:
        setNestedScrollingParentForType(r6, r0);
        ViewParentCompat.onNestedScrollAccepted(r0, r2, this.mView, r5, r6);
        return true;
    L23:
        return false;
    L17:
        return false;
    }

    public void stopNestedScroll() {
        stopNestedScroll(0);
    }

    public void stopNestedScroll(int r3) {
        ViewParent r0 = getNestedScrollingParentForType(r3);
        if (r0 == null) goto L6;
        ViewParentCompat.onStopNestedScroll(r0, this.mView, r3);
        setNestedScrollingParentForType(r3, null);
        return;
    }
}
