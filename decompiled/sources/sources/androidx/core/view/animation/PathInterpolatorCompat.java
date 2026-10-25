package androidx.core.view.animation;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* loaded from: classes3.dex */
public abstract class PathInterpolatorCompat {
    public static Interpolator create(float r1, float r2, float r3, float r4) {
        return new PathInterpolator(r1, r2, r3, r4);
    }
}
