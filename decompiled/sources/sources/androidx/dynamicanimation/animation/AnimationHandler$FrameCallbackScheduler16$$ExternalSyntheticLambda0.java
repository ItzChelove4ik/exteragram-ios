package androidx.dynamicanimation.animation;

import android.view.Choreographer;
import androidx.dynamicanimation.animation.AnimationHandler;

/* loaded from: classes3.dex */
public final /* synthetic */ class AnimationHandler$FrameCallbackScheduler16$$ExternalSyntheticLambda0 implements Choreographer.FrameCallback {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ AnimationHandler$FrameCallbackScheduler16$$ExternalSyntheticLambda0(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long r1) {
        AnimationHandler.FrameCallbackScheduler16.$r8$lambda$svrv50zXHmA8JWvDy7GTzVnyoog(this.f$0, r1);
    }
}
