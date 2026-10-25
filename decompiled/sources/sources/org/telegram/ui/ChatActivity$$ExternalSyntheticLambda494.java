package org.telegram.ui;

import android.animation.ValueAnimator;
import android.widget.HorizontalScrollView;
import org.telegram.ui.Components.ReactionTabHolderView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda494 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ HorizontalScrollView f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ float f$2;
    public final /* synthetic */ ReactionTabHolderView f$3;
    public final /* synthetic */ ReactionTabHolderView f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda494(HorizontalScrollView r1, float r2, float r3, ReactionTabHolderView r4, ReactionTabHolderView r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r7) {
        ChatActivity.$r8$lambda$xMONhvSKmlIij6hwMDVpstPsk8M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
