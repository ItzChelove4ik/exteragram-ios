package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ EmojiBottomSheet.GifPage.GifAdapter f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_messages_getInlineBotResults f$3;
    public final /* synthetic */ String f$4;

    public /* synthetic */ EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda4(EmojiBottomSheet.GifPage.GifAdapter r1, TLObject r2, boolean r3, TLRPC.TL_messages_getInlineBotResults r4, String r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        EmojiBottomSheet.GifPage.GifAdapter.m19969$r8$lambda$ey4fPZfTKnKupTAp0ai0f8Nc4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
