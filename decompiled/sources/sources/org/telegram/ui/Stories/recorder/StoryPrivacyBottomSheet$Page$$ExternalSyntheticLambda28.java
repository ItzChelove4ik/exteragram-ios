package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda28 implements Utilities.Callback {
    public final /* synthetic */ StoryPrivacyBottomSheet.Page f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda28(StoryPrivacyBottomSheet.Page r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        StoryPrivacyBottomSheet.Page.$r8$lambda$tZG4Utso5fuabxS65JdIY8k39LA(this.f$0, this.f$1, (TLRPC.TL_channels_channelParticipants) r4);
    }
}
