package org.telegram.ui.Components;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

/* loaded from: classes7.dex */
public class PorterDuffColorFilterState {
    private ColorFilter colorFilter;
    private int lastColor;
    private PorterDuff.Mode lastMode;

    public PorterDuffColorFilterState() {
    }

    public ColorFilter get(int r2, PorterDuff.Mode r3) {
        if (this.colorFilter != null) goto L5;
    L8:
        this.colorFilter = new PorterDuffColorFilter(r2, r3);
        this.lastColor = r2;
        this.lastMode = r3;
    L10:
        return this.colorFilter;
    L5:
        if (this.lastColor != r2) goto L8;
        if (this.lastMode == r3) goto L10;
        goto L8
    }
}
