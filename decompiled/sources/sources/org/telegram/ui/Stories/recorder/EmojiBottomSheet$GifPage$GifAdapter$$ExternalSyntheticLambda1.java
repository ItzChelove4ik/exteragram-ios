package org.telegram.ui.Stories.recorder;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ EmojiBottomSheet.GifPage.GifAdapter f$0;

    public /* synthetic */ EmojiBottomSheet$GifPage$GifAdapter$$ExternalSyntheticLambda1(EmojiBottomSheet.GifPage.GifAdapter r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        EmojiBottomSheet.GifPage.GifAdapter.$r8$lambda$QRt6zM62u5vGAo2QTrEjT6K84vs(this.f$0, r1, r2);
    }
}
