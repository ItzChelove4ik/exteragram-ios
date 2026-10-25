package org.telegram.ui.Stories.recorder;

import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public final /* synthetic */ class ToggleButton2$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ToggleButton2 f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ Drawable f$2;

    public /* synthetic */ ToggleButton2$$ExternalSyntheticLambda0(ToggleButton2 r1, AtomicBoolean r2, Drawable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        ToggleButton2.m20592$r8$lambda$Yxyi8iUnhrJg17ntbuqGHnAVN8(this.f$0, this.f$1, this.f$2, r3);
    }
}
