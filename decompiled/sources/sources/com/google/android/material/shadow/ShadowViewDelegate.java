package com.google.android.material.shadow;

import android.graphics.drawable.Drawable;

/* loaded from: classes5.dex */
public interface ShadowViewDelegate {
    float getRadius();

    boolean isCompatPaddingEnabled();

    void setBackgroundDrawable(Drawable r1);

    void setShadowPadding(int r1, int r2, int r3, int r4);
}
