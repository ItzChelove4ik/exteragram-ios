package org.telegram.ui.Components;

import android.app.Dialog;
import androidx.dynamicanimation.animation.DynamicAnimation;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda141 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ Dialog f$1;
    public final /* synthetic */ SimpleAvatarView f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ float f$4;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda141(ChatActivityEnterView r1, Dialog r2, SimpleAvatarView r3, float r4, float r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation r10, boolean r11, float r12, float r13) {
        ChatActivityEnterView.m9129$r8$lambda$NMdmZal6mJ8yralkJ21GSPJBjk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r10, r11, r12, r13);
    }
}
