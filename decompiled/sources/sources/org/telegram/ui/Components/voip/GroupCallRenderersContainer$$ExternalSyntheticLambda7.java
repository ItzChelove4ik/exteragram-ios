package org.telegram.ui.Components.voip;

import android.animation.ValueAnimator;

/* loaded from: classes7.dex */
public final /* synthetic */ class GroupCallRenderersContainer$$ExternalSyntheticLambda7 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ GroupCallRenderersContainer f$0;
    public final /* synthetic */ GroupCallMiniTextureView f$1;

    public /* synthetic */ GroupCallRenderersContainer$$ExternalSyntheticLambda7(GroupCallRenderersContainer r1, GroupCallMiniTextureView r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        GroupCallRenderersContainer.$r8$lambda$E_W11qJnAucHQrmwVbF1guFBhgM(this.f$0, this.f$1, r2);
    }
}
