package org.telegram.ui.Components;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* loaded from: classes7.dex */
public abstract class FloatSeekBarAccessibilityDelegate extends SeekBarAccessibilityDelegate {
    private final boolean setPercentsEnabled;

    public FloatSeekBarAccessibilityDelegate() {
        this(false);
    }

    public FloatSeekBarAccessibilityDelegate(boolean r1) {
        this.setPercentsEnabled = r1;
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
        float r32 = getDelta();
        if (r4 == false) goto L5;
        r32 = r32 * (-1.0f);
    L5:
        setProgress(Math.min(getMaxValue(), Math.max(getMinValue(), getProgress() + r32)));
    }

    public float getDelta() {
        return 0.05f;
    }

    public float getMaxValue() {
        return 1.0f;
    }

    public float getMinValue() {
        return 0.0f;
    }

    public abstract float getProgress();

    @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
    public void onInitializeAccessibilityNodeInfoInternal(View r3, AccessibilityNodeInfo r4) {
        super.onInitializeAccessibilityNodeInfoInternal(r3, r4);
        if (this.setPercentsEnabled == false) goto L6;
        AccessibilityNodeInfoCompat r32 = AccessibilityNodeInfoCompat.wrap(r4);
        r32.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
        r32.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, getMinValue(), getMaxValue(), getProgress()));
        return;
    }

    @Override // org.telegram.ui.Components.SeekBarAccessibilityDelegate
    public boolean performAccessibilityActionInternal(View r2, int r3, Bundle r4) {
        if (super.performAccessibilityActionInternal(r2, r3, r4) == false) goto L6;
        return true;
    L6:
        if (r3 != AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS.getId()) goto L9;
        setProgress(r4.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"));
        return true;
    L9:
        return false;
    }

    public abstract void setProgress(float r1);
}
