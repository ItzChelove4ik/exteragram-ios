package org.telegram.ui.Stories;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.telegram.messenger.SharedConfig;

/* loaded from: classes7.dex */
abstract class HwFrameLayout extends FrameLayout {
    static boolean hwEnabled;
    static final Set<View> hwViews = null;
    private final boolean isFastDevice;

    static {
        hwViews = new HashSet();
        hwEnabled = false;
    }

    public HwFrameLayout(Context r2) {
        super(r2);
        if (SharedConfig.getDevicePerformanceClass() != 2) goto L5;
        boolean r22 = true;
    L6:
        this.isFastDevice = r22;
        return;
    L5:
        r22 = false;
        goto L6
    }

    private void disableHwAcceleration(boolean r2) {
        hwEnabled = false;
        if (r2 == false) goto L5;
        setLayerType(0, null);
    L5:
        Iterator<View> r1 = hwViews.iterator();
    L7:
        if (r1.hasNext() == false) goto L9;
        r1.next().invalidate();
        goto L7
    L9:
        hwViews.clear();
    }

    public void checkHwAcceleration(float r2) {
        if (r2 > 0.6f) goto L5;
        return;
    L5:
        if (hwEnabled == true) goto L7;
        return;
    L7:
        if (this.isFastDevice == false) goto L12;
        disableHwAcceleration(false);
        return;
    }

    public void disableHwAcceleration() {
        disableHwAcceleration(true);
    }

    public void enableHwAcceleration() {
        hwEnabled = true;
        setLayerType(2, null);
    }

    @Override // android.view.View
    public void invalidate() {
        if (hwEnabled == false) goto L6;
        hwViews.add(this);
        return;
    L6:
        super.invalidate();
    }

    @Override // android.view.View
    public void invalidate(int r2, int r3, int r4, int r5) {
        if (hwEnabled == false) goto L6;
        hwViews.add(this);
        return;
    L6:
        super.invalidate(r2, r3, r4, r5);
    }
}
