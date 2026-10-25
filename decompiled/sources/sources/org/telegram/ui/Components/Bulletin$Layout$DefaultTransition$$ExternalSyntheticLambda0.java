package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import androidx.core.util.Consumer;
import org.telegram.ui.Components.Bulletin;

/* loaded from: classes7.dex */
public final /* synthetic */ class Bulletin$Layout$DefaultTransition$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ Bulletin.Layout f$1;

    public /* synthetic */ Bulletin$Layout$DefaultTransition$$ExternalSyntheticLambda0(Consumer r1, Bulletin.Layout r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        Bulletin.Layout.DefaultTransition.$r8$lambda$DQfGi1PDMuRxwJqkCozf3FiWw7c(this.f$0, this.f$1, r2);
    }
}
