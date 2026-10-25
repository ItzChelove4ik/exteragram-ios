package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ EmojiBottomSheet.GifPage.GifAdapter f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.TL_messages_getInlineBotResults f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda2(EmojiBottomSheet.GifPage.GifAdapter r1, boolean r2, TLRPC.TL_messages_getInlineBotResults r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        EmojiBottomSheet.GifPage.GifAdapter.$r8$lambda$Fimq5Ak0ikWtcT9psqNpPRZHc7c(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
