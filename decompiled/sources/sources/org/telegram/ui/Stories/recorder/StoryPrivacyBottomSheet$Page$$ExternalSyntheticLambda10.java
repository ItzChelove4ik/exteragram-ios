package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda10 implements RequestDelegate {
    public final /* synthetic */ StoryPrivacyBottomSheet.Page f$0;
    public final /* synthetic */ MessagesController f$1;

    public /* synthetic */ StoryPrivacyBottomSheet$Page$$ExternalSyntheticLambda10(StoryPrivacyBottomSheet.Page r1, MessagesController r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StoryPrivacyBottomSheet.Page.$r8$lambda$guI08yEAS7tIkmLlEGfG4SNJ41o(this.f$0, this.f$1, r2, r3);
    }
}
