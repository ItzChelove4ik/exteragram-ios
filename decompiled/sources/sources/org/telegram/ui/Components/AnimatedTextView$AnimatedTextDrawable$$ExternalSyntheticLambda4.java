package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.AnimatedTextView;

/* loaded from: classes7.dex */
public final /* synthetic */ class AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda4 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ AnimatedTextView.AnimatedTextDrawable f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ AnimatedTextView$AnimatedTextDrawable$$ExternalSyntheticLambda4(AnimatedTextView.AnimatedTextDrawable r1, int r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        AnimatedTextView.AnimatedTextDrawable.$r8$lambda$6VvrGEsQhszgZjlaqlpWXoQfvYc(this.f$0, this.f$1, this.f$2, r3);
    }
}
