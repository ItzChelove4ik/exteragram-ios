package org.telegram.messenger.utils;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.RenderEffect;

/* loaded from: classes.dex */
public abstract class RenderNodeEffects {
    private static RenderEffect saturationUpX3Effect;

    public static RenderEffect createSaturationXRenderEffect(float r1) {
        ColorMatrix r0 = new ColorMatrix();
        r0.setSaturation(r1);
        return RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(r0));
    }

    public static RenderEffect getSaturationX3RenderEffect() {
        if (saturationUpX3Effect != null) goto L6;
        ColorMatrix r0 = new ColorMatrix();
        r0.setSaturation(3.0f);
        saturationUpX3Effect = RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(r0));
    L6:
        return saturationUpX3Effect;
    }
}
