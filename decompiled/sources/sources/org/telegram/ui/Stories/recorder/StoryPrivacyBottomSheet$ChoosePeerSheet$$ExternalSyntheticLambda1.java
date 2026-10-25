package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$ChoosePeerSheet$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ TLRPC.InputPeer f$2;

    public /* synthetic */ StoryPrivacyBottomSheet$ChoosePeerSheet$$ExternalSyntheticLambda1(AlertDialog r1, Utilities.Callback r2, TLRPC.InputPeer r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        StoryPrivacyBottomSheet.ChoosePeerSheet.m20296$r8$lambda$n3fTCQAITu8YC5M_ytfMu6Aojs(this.f$0, this.f$1, this.f$2, (Boolean) r3);
    }
}
