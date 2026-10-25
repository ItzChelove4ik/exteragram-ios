package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ StoryPrivacyBottomSheet.Page f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ MessagesStorage f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda16(StoryPrivacyBottomSheet.Page r1, TLRPC.Chat r2, MessagesStorage r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoryPrivacyBottomSheet.Page.$r8$lambda$rCDd0xLHbReyIld380sTBgxQSh0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
