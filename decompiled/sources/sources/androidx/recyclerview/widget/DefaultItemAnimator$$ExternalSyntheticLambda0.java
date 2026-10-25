package androidx.recyclerview.widget;

import android.animation.ValueAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultItemAnimator$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ DefaultItemAnimator f$0;
    public final /* synthetic */ RecyclerView.ViewHolder f$1;

    public /* synthetic */ DefaultItemAnimator$$ExternalSyntheticLambda0(DefaultItemAnimator r1, RecyclerView.ViewHolder r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        DefaultItemAnimator.m934$r8$lambda$CZ6bhXrdh991CVtOmXu6Jhq070(this.f$0, this.f$1, r2);
    }
}
