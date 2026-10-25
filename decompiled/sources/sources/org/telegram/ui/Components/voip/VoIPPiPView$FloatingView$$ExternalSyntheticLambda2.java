package org.telegram.ui.Components.voip;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.voip.VoIPPiPView;

/* loaded from: classes7.dex */
public final /* synthetic */ class VoIPPiPView$FloatingView$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ float f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ VoIPPiPView f$2;

    public /* synthetic */ VoIPPiPView$FloatingView$$ExternalSyntheticLambda2(float r1, float r2, VoIPPiPView r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r3) {
        VoIPPiPView.FloatingView.m13983$r8$lambda$nnUQfHzfHnDL_MoShkmrtDfByk(this.f$0, this.f$1, this.f$2, r3);
    }
}
