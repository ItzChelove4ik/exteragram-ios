package org.telegram.messenger;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.VideoPlayer;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaController$$ExternalSyntheticLambda14 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ MediaController f$0;
    public final /* synthetic */ VideoPlayer f$1;

    public /* synthetic */ MediaController$$ExternalSyntheticLambda14(MediaController r1, VideoPlayer r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        MediaController.$r8$lambda$78qytVABInrbJVlVhaJWay85dYM(this.f$0, this.f$1, r2);
    }
}
