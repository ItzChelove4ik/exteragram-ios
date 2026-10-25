package org.telegram.ui.Stories.recorder;

import android.animation.ValueAnimator;
import android.view.View;

/* loaded from: classes7.dex */
public final /* synthetic */ class PaintView$$ExternalSyntheticLambda16 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PaintView f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ View f$2;

    public /* synthetic */ PaintView$$ExternalSyntheticLambda16(PaintView r1, View r2, View r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        PaintView.m20066$r8$lambda$FVuPW8_UDGrNKelICBLpHINndI(this.f$0, this.f$1, this.f$2, r3);
    }
}
