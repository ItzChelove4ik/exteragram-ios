package androidx.core.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EdgeEffect;

/* loaded from: classes3.dex */
public abstract class EdgeEffectCompat {

    public static class Api31Impl {
        public static EdgeEffect create(Context r1, AttributeSet r2) {
            return new EdgeEffect(r1, r2);
        L5:
            return new EdgeEffect(r1);
        }

        public static float getDistance(EdgeEffect r0) {
            return r0.getDistance();
        L4:
            return 0.0f;
        }

        public static float onPullDistance(EdgeEffect r0, float r1, float r2) {
            return r0.onPullDistance(r1, r2);
        L4:
            r0.onPull(r1, r2);
            return 0.0f;
        }
    }

    public static EdgeEffect create(Context r2, AttributeSet r3) {
        if (Build.VERSION.SDK_INT < 31) goto L7;
        return Api31Impl.create(r2, r3);
    L7:
        return new EdgeEffect(r2);
    }

    public static float getDistance(EdgeEffect r2) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return 0.0f;
    L5:
        return Api31Impl.getDistance(r2);
    }

    public static void onPull(EdgeEffect r0, float r1, float r2) {
        r0.onPull(r1, r2);
    }

    public static float onPullDistance(EdgeEffect r2, float r3, float r4) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        onPull(r2, r3, r4);
        return r3;
    L5:
        return Api31Impl.onPullDistance(r2, r3, r4);
    }
}
