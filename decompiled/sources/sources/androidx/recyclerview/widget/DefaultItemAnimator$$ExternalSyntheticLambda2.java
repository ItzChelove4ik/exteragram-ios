package androidx.recyclerview.widget;

import android.animation.ValueAnimator;
import androidx.recyclerview.widget.DefaultItemAnimator;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultItemAnimator$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ DefaultItemAnimator f$0;
    public final /* synthetic */ DefaultItemAnimator.ChangeInfo f$1;

    public /* synthetic */ DefaultItemAnimator$$ExternalSyntheticLambda2(DefaultItemAnimator r1, DefaultItemAnimator.ChangeInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        DefaultItemAnimator.m936$r8$lambda$ZcJ7nk5e3IQEIVTkDXb8E43Vr0(this.f$0, this.f$1, r2);
    }
}
