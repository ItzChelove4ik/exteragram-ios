package androidx.recyclerview.widget;

import android.animation.ValueAnimator;
import androidx.recyclerview.widget.DefaultItemAnimator;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultItemAnimator$$ExternalSyntheticLambda3 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ DefaultItemAnimator f$0;
    public final /* synthetic */ DefaultItemAnimator.ChangeInfo f$1;

    public /* synthetic */ DefaultItemAnimator$$ExternalSyntheticLambda3(DefaultItemAnimator r1, DefaultItemAnimator.ChangeInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        DefaultItemAnimator.m937$r8$lambda$qo3yeEFTJSB_GpxX4y6sM5qgvg(this.f$0, this.f$1, r2);
    }
}
