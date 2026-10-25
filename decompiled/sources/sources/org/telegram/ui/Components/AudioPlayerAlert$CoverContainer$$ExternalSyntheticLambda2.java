package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import org.telegram.ui.Components.AudioPlayerAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$CoverContainer$$ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ BackupImageView f$0;
    public final /* synthetic */ BackupImageView f$1;

    public /* synthetic */ AudioPlayerAlert$CoverContainer$$ExternalSyntheticLambda2(BackupImageView r1, BackupImageView r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator r2) {
        AudioPlayerAlert.CoverContainer.$r8$lambda$KUh1H3SP_GHVwhtYm2kMJv3tCrA(this.f$0, this.f$1, r2);
    }
}
