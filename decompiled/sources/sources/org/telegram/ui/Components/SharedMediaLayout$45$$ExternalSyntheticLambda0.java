package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.SharedMediaLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$45$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ SharedMediaLayout.AnonymousClass45 f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ RecyclerListView f$2;

    public /* synthetic */ SharedMediaLayout$45$$ExternalSyntheticLambda0(SharedMediaLayout.AnonymousClass45 r1, int r2, RecyclerListView r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        SharedMediaLayout.AnonymousClass45.m12930$r8$lambda$jPUPpyzykiFJ_OQ0VqefXg19AE(this.f$0, this.f$1, this.f$2, r3);
    }
}
