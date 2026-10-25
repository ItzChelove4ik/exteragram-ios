package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;

/* loaded from: classes4.dex */
public abstract class Animatable2Compat$AnimationCallback {
    Animatable2.AnimationCallback mPlatformCallback;

    /* renamed from: androidx.vectordrawable.graphics.drawable.Animatable2Compat$AnimationCallback$1, reason: invalid class name */
    public class AnonymousClass1 extends Animatable2.AnimationCallback {
        final /* synthetic */ Animatable2Compat$AnimationCallback this$0;

        public AnonymousClass1(Animatable2Compat$AnimationCallback r1) {
            this.this$0 = r1;
        }

        @Override // android.graphics.drawable.Animatable2.AnimationCallback
        public void onAnimationEnd(Drawable r1) {
            this.this$0.onAnimationEnd(r1);
        }

        @Override // android.graphics.drawable.Animatable2.AnimationCallback
        public void onAnimationStart(Drawable r1) {
            this.this$0.onAnimationStart(r1);
        }
    }

    public Animatable2Compat$AnimationCallback() {
    }

    public Animatable2.AnimationCallback getPlatformCallback() {
        if (this.mPlatformCallback != null) goto L6;
        this.mPlatformCallback = new AnonymousClass1(this);
    L6:
        return this.mPlatformCallback;
    }

    public void onAnimationEnd(Drawable r1) {
    }

    public void onAnimationStart(Drawable r1) {
    }
}
