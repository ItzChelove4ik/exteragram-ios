package androidx.core.view;

import android.graphics.Rect;
import android.view.Gravity;

/* loaded from: classes3.dex */
public abstract class GravityCompat {
    public static void apply(int r0, int r1, int r2, Rect r3, Rect r4, int r5) {
        Gravity.apply(r0, r1, r2, r3, r4, r5);
    }

    public static int getAbsoluteGravity(int r0, int r1) {
        return Gravity.getAbsoluteGravity(r0, r1);
    }
}
