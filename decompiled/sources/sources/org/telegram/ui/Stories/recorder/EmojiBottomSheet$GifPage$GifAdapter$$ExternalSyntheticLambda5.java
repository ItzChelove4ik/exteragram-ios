package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ EmojiBottomSheet.GifPage.GifAdapter f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda5(EmojiBottomSheet.GifPage.GifAdapter r1, String r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        EmojiBottomSheet.GifPage.GifAdapter.m19968$r8$lambda$Kua113DtW0wMaGa1Bz7YChIXaA(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
