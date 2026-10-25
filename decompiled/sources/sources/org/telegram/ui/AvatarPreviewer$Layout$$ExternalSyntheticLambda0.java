package org.telegram.ui;

import android.animation.ValueAnimator;
import org.telegram.ui.AvatarPreviewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class AvatarPreviewer$Layout$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AvatarPreviewer.Layout f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ AvatarPreviewer$Layout$$ExternalSyntheticLambda0(AvatarPreviewer.Layout r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        AvatarPreviewer.Layout.m6274$r8$lambda$qNYBwyvpfpAGxWllbYlt8vuQg(this.f$0, this.f$1, r2);
    }
}
