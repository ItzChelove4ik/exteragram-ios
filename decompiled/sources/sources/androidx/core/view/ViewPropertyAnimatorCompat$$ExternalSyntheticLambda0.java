package androidx.core.view;

import android.animation.ValueAnimator;
import android.view.View;

/* loaded from: classes3.dex */
public final /* synthetic */ class ViewPropertyAnimatorCompat$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ ViewPropertyAnimatorUpdateListener f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ ViewPropertyAnimatorCompat$$ExternalSyntheticLambda0(ViewPropertyAnimatorUpdateListener r1, View r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        ViewPropertyAnimatorCompat.$r8$lambda$yIm158IzmaGNEXn6TreBnR2w5dM(this.f$0, this.f$1, r2);
    }
}
