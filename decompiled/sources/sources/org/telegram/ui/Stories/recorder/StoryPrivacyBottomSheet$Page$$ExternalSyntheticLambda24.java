package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_phone;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda24 implements Runnable {
    public final /* synthetic */ StoryPrivacyBottomSheet.Page f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_phone.getGroupCallStreamRtmpUrl f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda24(StoryPrivacyBottomSheet.Page r1, AlertDialog r2, TLObject r3, TL_phone.getGroupCallStreamRtmpUrl r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoryPrivacyBottomSheet.Page.$r8$lambda$oK6jh7TrvMjUaOX1REI7j4TK_QM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
