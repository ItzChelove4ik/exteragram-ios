package com.google.android.material.carousel;

import android.graphics.RectF;

/* loaded from: classes5.dex */
interface Maskable {
    RectF getMaskRectF();

    @Deprecated
    float getMaskXPercentage();

    void setMaskRectF(RectF r1);

    @Deprecated
    void setMaskXPercentage(float r1);

    void setOnMaskChangedListener(OnMaskChangedListener r1);
}
