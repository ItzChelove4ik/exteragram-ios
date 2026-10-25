package androidx.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

/* loaded from: classes4.dex */
abstract class ObjectAnimatorUtils {

    public static class Api21Impl {
        public static <T, V> ObjectAnimator ofObject(T r1, Property<T, V> r2, Path r3) {
            return ObjectAnimator.ofObject(r1, r2, null, r3);
        }
    }

    public static <T> ObjectAnimator ofPointF(T r0, Property<T, PointF> r1, Path r2) {
        return Api21Impl.ofObject(r0, r1, r2);
    }
}
