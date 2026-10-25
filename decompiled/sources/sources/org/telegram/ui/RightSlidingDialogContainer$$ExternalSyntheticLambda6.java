package org.telegram.ui;

import androidx.dynamicanimation.animation.DynamicAnimation;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class RightSlidingDialogContainer$$ExternalSyntheticLambda6 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ RightSlidingDialogContainer f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ BaseFragment f$2;

    public /* synthetic */ RightSlidingDialogContainer$$ExternalSyntheticLambda6(RightSlidingDialogContainer r1, BaseFragment r2, BaseFragment r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation r8, boolean r9, float r10, float r11) {
        RightSlidingDialogContainer.$r8$lambda$iDGN7A3IOouHtvXsmPJ0_t23ehY(this.f$0, this.f$1, this.f$2, r8, r9, r10, r11);
    }
}
