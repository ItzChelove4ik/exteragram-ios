package org.telegram.ui;

import android.animation.ValueAnimator;
import android.view.View;
import org.telegram.ui.Components.CubicBezierInterpolator;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda110 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ CubicBezierInterpolator f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ float f$4;
    public final /* synthetic */ float f$5;
    public final /* synthetic */ View f$6;
    public final /* synthetic */ float f$7;
    public final /* synthetic */ float f$8;
    public final /* synthetic */ float f$9;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda110(PhotoViewer r1, CubicBezierInterpolator r2, float r3, float r4, float r5, float r6, View r7, float r8, float r9, float r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r12) {
        PhotoViewer.m16924$r8$lambda$fri0S3DFGQm3G0G64TVQI6Ln_s(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r12);
    }
}
