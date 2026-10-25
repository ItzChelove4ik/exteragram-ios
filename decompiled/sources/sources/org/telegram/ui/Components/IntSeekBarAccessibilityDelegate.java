package org.telegram.ui.Components;

import android.view.View;

/* loaded from: classes7.dex */
public abstract class IntSeekBarAccessibilityDelegate extends SeekBarAccessibilityDelegate {
    public IntSeekBarAccessibilityDelegate() {
    }

    @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
    public boolean canScrollBackward(View r1) {
        if (getProgress() <= getMinValue()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
    public boolean canScrollForward(View r1) {
        if (getProgress() >= getMaxValue()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
    public void doScroll(View r3, boolean r4) {
        int r32 = getDelta();
        if (r4 == false) goto L5;
        r32 = r32 * (-1);
    L5:
        setProgress(Math.min(getMaxValue(), Math.max(getMinValue(), getProgress() + r32)));
    }

    public int getDelta() {
        return 1;
    }

    public abstract int getMaxValue();

    public int getMinValue() {
        return 0;
    }

    public abstract int getProgress();

    public abstract void setProgress(int r1);
}
