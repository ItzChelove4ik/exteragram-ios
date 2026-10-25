package org.telegram.ui.Components.Paint.Views;

import android.animation.ValueAnimator;

/* loaded from: classes7.dex */
public final /* synthetic */ class MaskPaintView$3$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ MaskPaintView f$0;

    public /* synthetic */ MaskPaintView$3$$ExternalSyntheticLambda0(MaskPaintView r1) {
        this.f$0 = r1;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r1) {
        this.f$0.onRenderViewAlphaUpdate(r1);
    }
}
