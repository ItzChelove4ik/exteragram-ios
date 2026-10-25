package org.telegram.ui.Components;

import androidx.dynamicanimation.animation.DynamicAnimation;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes7.dex */
public final /* synthetic */ class Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda0 implements DynamicAnimation.OnAnimationEndListener {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda0(Runnable r1) {
        this.f$0 = r1;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation r1, boolean r2, float r3, float r4) {
        Bulletin.Layout.SpringTransition.$r8$lambda$rSAb8tEiJp0HQ6S06SDw6bq0Jh0(this.f$0, r1, r2, r3, r4);
    }
}
