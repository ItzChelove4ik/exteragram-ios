package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryRecorder$$ExternalSyntheticLambda125 implements Utilities.Callback {
    public final /* synthetic */ StoryRecorder f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.InputPeer f$3;
    public final /* synthetic */ StoryPrivacyBottomSheet.StoryPrivacy f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ int f$7;

    public /* synthetic */ StoryRecorder$$ExternalSyntheticLambda125(StoryRecorder r1, Runnable r2, boolean r3, TLRPC.InputPeer r4, StoryPrivacyBottomSheet.StoryPrivacy r5, boolean r6, boolean r7, int r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r10) {
        StoryRecorder.$r8$lambda$PX7sq6EmwwQTLWXATGXrgj6_C3I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, (Boolean) r10);
    }
}
