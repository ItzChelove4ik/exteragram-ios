package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ EmojiBottomSheet.Page.Adapter f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ EmojiBottomSheet$Page$Adapter$$ExternalSyntheticLambda2(EmojiBottomSheet.Page.Adapter r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        EmojiBottomSheet.Page.Adapter.$r8$lambda$FRmnd3hwwf8A6or0wakcgl4NF1o(this.f$0, this.f$1, r2, r3);
    }
}
