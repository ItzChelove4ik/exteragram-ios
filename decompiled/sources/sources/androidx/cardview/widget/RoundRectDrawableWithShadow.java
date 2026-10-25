package androidx.cardview.widget;

import android.graphics.drawable.Drawable;

/* loaded from: classes3.dex */
abstract class RoundRectDrawableWithShadow extends Drawable {
    private static final double COS_45 = 0.0d;

    static {
        COS_45 = Math.cos(Math.toRadians(45.0d));
    }

    public static float calculateHorizontalPadding(float r6, float r7, boolean r8) {
        if (r8 == true) goto L4;
        return r6;
    L4:
        return (float) (r6 + ((1.0d - COS_45) * r7));
    }

    public static float calculateVerticalPadding(float r6, float r7, boolean r8) {
        if (r8 == false) goto L7;
        return (float) ((r6 * 1.5f) + ((1.0d - COS_45) * r7));
    L7:
        return r6 * 1.5f;
    }
}
