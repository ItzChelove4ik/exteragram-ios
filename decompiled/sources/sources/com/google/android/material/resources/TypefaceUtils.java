package com.google.android.material.resources;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Build;
import androidx.core.math.MathUtils;

/* loaded from: classes5.dex */
public class TypefaceUtils {
    private TypefaceUtils() {
    }

    public static Typeface maybeCopyWithFontWeightAdjustment(Context r0, Typeface r1) {
        return maybeCopyWithFontWeightAdjustment(r0.getResources().getConfiguration(), r1);
    }

    public static Typeface maybeCopyWithFontWeightAdjustment(Configuration r2, Typeface r3) {
        if (Build.VERSION.SDK_INT < 31) goto L10;
        int r0 = TypefaceUtils$$ExternalSyntheticApiModelOutline0.m(r2);
        if (r0 == Integer.MAX_VALUE) goto L12;
        if (r0 == 0) goto L13;
        if (r3 != null) goto L9;
        return null;
    L9:
        return Typeface.create(r3, MathUtils.clamp(r3.getWeight() + TypefaceUtils$$ExternalSyntheticApiModelOutline0.m(r2), 1, 1000), r3.isItalic());
    L13:
        return null;
    L12:
        return null;
    L10:
        return null;
    }
}
