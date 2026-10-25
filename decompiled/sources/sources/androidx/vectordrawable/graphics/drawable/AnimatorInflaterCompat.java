package androidx.vectordrawable.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;

/* loaded from: classes4.dex */
public abstract class AnimatorInflaterCompat {
    public static Animator loadAnimator(Context r0, int r1) {
        return AnimatorInflater.loadAnimator(r0, r1);
    }
}
