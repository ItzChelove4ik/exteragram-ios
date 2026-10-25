package com.google.android.material.search;

import android.animation.Animator;
import com.google.android.material.animation.AnimatableView;

/* loaded from: classes5.dex */
public final /* synthetic */ class SearchBarAnimationHelper$$ExternalSyntheticLambda2 implements AnimatableView.Listener {
    public final /* synthetic */ Animator f$0;

    public /* synthetic */ SearchBarAnimationHelper$$ExternalSyntheticLambda2(Animator r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.material.animation.AnimatableView.Listener
    public final void onAnimationEnd() {
        this.f$0.start();
    }
}
