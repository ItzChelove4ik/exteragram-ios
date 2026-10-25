package org.telegram.ui.Components;

import org.telegram.messenger.ImageReceiver;
import org.telegram.ui.Components.AudioPlayerAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$CoverContainer$$ExternalSyntheticLambda0 implements ImageReceiver.ImageReceiverDelegate {
    public final /* synthetic */ AudioPlayerAlert.CoverContainer f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ AudioPlayerAlert$CoverContainer$$ExternalSyntheticLambda0(AudioPlayerAlert.CoverContainer r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
    public final void didSetImage(ImageReceiver r7, boolean r8, boolean r9, boolean r10) {
        AudioPlayerAlert.CoverContainer.$r8$lambda$RS4PiwA2welbGSsqpkDqZvq4_2w(this.f$0, this.f$1, r7, r8, r9, r10);
    }
}
