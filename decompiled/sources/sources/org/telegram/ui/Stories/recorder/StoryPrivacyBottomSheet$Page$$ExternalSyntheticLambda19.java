package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda19 implements RequestDelegate {
    public final /* synthetic */ StoryPrivacyBottomSheet.Page f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TL_phone.getGroupCallStreamRtmpUrl f$2;

    public /* synthetic */ StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda19(StoryPrivacyBottomSheet.Page r1, AlertDialog r2, TL_phone.getGroupCallStreamRtmpUrl r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StoryPrivacyBottomSheet.Page.$r8$lambda$pTi830J1JkMDiEr3vq2LCypwNZE(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
