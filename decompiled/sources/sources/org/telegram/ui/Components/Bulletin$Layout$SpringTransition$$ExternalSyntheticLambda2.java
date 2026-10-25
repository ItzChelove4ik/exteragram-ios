package org.telegram.ui.Components;

import androidx.dynamicanimation.animation.DynamicAnimation;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes7.dex */
public final /* synthetic */ class Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda2 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ Bulletin.Layout f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda2(Bulletin.Layout r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation r7, boolean r8, float r9, float r10) {
        Bulletin.Layout.SpringTransition.m9065$r8$lambda$l75tbtgbX7e0wzIDlEvtqUbJ8Y(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
