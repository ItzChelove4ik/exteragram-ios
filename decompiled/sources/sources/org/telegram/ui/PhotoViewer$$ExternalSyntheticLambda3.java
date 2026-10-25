package org.telegram.ui;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.ClippingImageView;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda3 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ ClippingImageView[] f$1;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda3(PhotoViewer r1, ClippingImageView[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        PhotoViewer.m16911$r8$lambda$SCaBNjzB9US45kFSMGlqxhPUGM(this.f$0, this.f$1, r2);
    }
}
