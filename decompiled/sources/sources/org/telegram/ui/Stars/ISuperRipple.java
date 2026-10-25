package org.telegram.ui.Stars;

import android.view.View;

/* loaded from: classes7.dex */
public abstract class ISuperRipple {
    public final View view;

    public ISuperRipple(View r1) {
        this.view = r1;
    }

    public abstract void animate(float r1, float r2, float r3);
}
