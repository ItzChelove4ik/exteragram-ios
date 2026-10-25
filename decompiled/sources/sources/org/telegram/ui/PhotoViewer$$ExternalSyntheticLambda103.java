package org.telegram.ui;

import android.animation.ValueAnimator;
import android.view.View;

/* loaded from: classes6.dex */
public final /* synthetic */ class PhotoViewer$$ExternalSyntheticLambda103 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ PhotoViewer f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ float[] f$2;

    public /* synthetic */ PhotoViewer$$ExternalSyntheticLambda103(PhotoViewer r1, View r2, float[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        PhotoViewer.$r8$lambda$9v6W3gXgcebGMmBpxcdLBwSuIEY(this.f$0, this.f$1, this.f$2, r3);
    }
}
