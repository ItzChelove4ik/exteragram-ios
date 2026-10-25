package org.telegram.ui.Components;

import androidx.core.util.Consumer;
import androidx.dynamicanimation.animation.DynamicAnimation;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes7.dex */
public final /* synthetic */ class Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda3 implements DynamicAnimation.OnAnimationUpdateListener {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ Bulletin.Layout f$1;

    public /* synthetic */ Bulletin$Layout$SpringTransition$$ExternalSyntheticLambda3(Consumer r1, Bulletin.Layout r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
    public final void onAnimationUpdate(DynamicAnimation r2, float r3, float r4) {
        Bulletin.Layout.SpringTransition.$r8$lambda$PHmnhKumdkiFxUnowk2C5oLOEDw(this.f$0, this.f$1, r2, r3, r4);
    }
}
