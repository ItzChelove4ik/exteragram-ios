package androidx.core.view.insets;

import android.graphics.RectF;
import androidx.core.graphics.Insets;
import androidx.core.view.insets.SystemBarStateMonitor;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
class ProtectionGroup implements SystemBarStateMonitor.Callback {
    private int mAnimationCount;
    private boolean mDisposed;
    private Insets mInsets;
    private Insets mInsetsIgnoringVisibility;
    private final SystemBarStateMonitor mMonitor;
    private final ArrayList<Protection> mProtections;

    public ProtectionGroup(SystemBarStateMonitor r2, List<Protection> r3) {
        this.mProtections = new ArrayList();
        Insets r0 = Insets.NONE;
        this.mInsets = r0;
        this.mInsetsIgnoringVisibility = r0;
        addProtections(r3, false);
        addProtections(r3, true);
        r2.addCallback(this);
        this.mMonitor = r2;
    }

    private void addProtections(List<Protection> r5, boolean r6) {
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L13;
        Protection r2 = r5.get(r1);
        if (r2.occupiesCorners() != r6) goto L10;
        Object r3 = r2.getController();
        if (r3 != null) goto L12;
        r2.setController(this);
        this.mProtections.add(r2);
        goto L10
    L12:
        throw new IllegalStateException(r2 + " (" + (r1 + 1) + "/" + r0 + ") is already controlled by " + r3 + " but is still added to " + this);
    L10:
        r1 = r1 + 1;
        goto L3
    }

    private void updateInsets() {
        Insets r0 = Insets.NONE;
        int r1 = this.mProtections.size() - 1;
    L3:
        if (r1 < 0) goto L5;
        r0 = Insets.max(r0, this.mProtections.get(r1).dispatchInsets(this.mInsets, this.mInsetsIgnoringVisibility, r0));
        r1 = r1 - 1;
        goto L3
    }

    public void dispose() {
        if (this.mDisposed == false) goto L5;
        return;
    L5:
        this.mDisposed = true;
        this.mMonitor.removeCallback(this);
        int r1 = this.mProtections.size() - 1;
    L6:
        ArrayList<Protection> r0 = this.mProtections;
        if (r1 < 0) goto L9;
        r0.get(r1).setController(null);
        r1 = r1 - 1;
        goto L6
    L9:
        r0.clear();
    }

    public Protection getProtection(int r1) {
        return this.mProtections.get(r1);
    }

    @Override // androidx.core.view.insets.SystemBarStateMonitor.Callback
    public void onAnimationEnd() {
        int r0 = this.mAnimationCount;
        if (r0 <= 0) goto L5;
        boolean r2 = true;
    L6:
        int r02 = r0 - 1;
        this.mAnimationCount = r02;
        if (r2 == false) goto L11;
        if (r02 != 0) goto L12;
        updateInsets();
        return;
    L12:
        return;
    L11:
        return;
    L5:
        r2 = false;
        goto L6
    }

    @Override // androidx.core.view.insets.SystemBarStateMonitor.Callback
    public void onAnimationProgress(int r7, Insets r8, RectF r9) {
        Insets r0 = this.mInsetsIgnoringVisibility;
        int r1 = this.mProtections.size() - 1;
    L3:
        if (r1 < 0) goto L33;
        Protection r3 = this.mProtections.get(r1);
        int r4 = r3.getSide();
        if ((r4 & r7) == 0) goto L32;
        r3.setSystemVisible(true);
        if (r4 != 1) goto L10;
        int r42 = r0.left;
        if (r42 <= 0) goto L31;
        r3.setSystemInsetAmount(r8.left / r42);
    L31:
        r3.setSystemAlpha(r9.left);
        goto L32
    L10:
        if (r4 != 2) goto L12;
        int r43 = r0.top;
        if (r43 <= 0) goto L27;
        r3.setSystemInsetAmount(r8.top / r43);
    L27:
        r3.setSystemAlpha(r9.top);
        goto L32
    L12:
        if (r4 != 4) goto L14;
        int r44 = r0.right;
        if (r44 <= 0) goto L23;
        r3.setSystemInsetAmount(r8.right / r44);
    L23:
        r3.setSystemAlpha(r9.right);
        goto L32
    L14:
        if (r4 != 8) goto L32;
        int r45 = r0.bottom;
        if (r45 <= 0) goto L19;
        r3.setSystemInsetAmount(r8.bottom / r45);
    L19:
        r3.setSystemAlpha(r9.bottom);
    L32:
        r1 = r1 - 1;
        goto L3
    }

    @Override // androidx.core.view.insets.SystemBarStateMonitor.Callback
    public void onAnimationStart() {
        this.mAnimationCount++;
    }

    @Override // androidx.core.view.insets.SystemBarStateMonitor.Callback
    public void onColorHintChanged(int r3) {
        int r0 = this.mProtections.size() - 1;
    L3:
        if (r0 < 0) goto L5;
        this.mProtections.get(r0).dispatchColorHint(r3);
        r0 = r0 - 1;
        goto L3
    }

    @Override // androidx.core.view.insets.SystemBarStateMonitor.Callback
    public void onInsetsChanged(Insets r1, Insets r2) {
        this.mInsets = r1;
        this.mInsetsIgnoringVisibility = r2;
        updateInsets();
    }

    public int size() {
        return this.mProtections.size();
    }
}
