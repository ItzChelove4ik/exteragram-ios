package com.exteragram.messenger.camera;

import android.animation.ValueAnimator;

/* loaded from: classes4.dex */
public final /* synthetic */ class CameraZoomSliderView$$ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ CameraZoomSliderView f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ CameraZoomSliderView$$ExternalSyntheticLambda1(CameraZoomSliderView r1, boolean r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        CameraZoomSliderView.m1143$r8$lambda$w3gVNOEoFX2DWrdQPJqNF0hc(this.f$0, this.f$1, this.f$2, r3);
    }
}
