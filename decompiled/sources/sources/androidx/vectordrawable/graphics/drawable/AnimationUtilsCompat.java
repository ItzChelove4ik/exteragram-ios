package androidx.vectordrawable.graphics.drawable;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* loaded from: classes4.dex */
public abstract class AnimationUtilsCompat {
    public static Interpolator loadInterpolator(Context r0, int r1) {
        return AnimationUtils.loadInterpolator(r0, r1);
    }
}
