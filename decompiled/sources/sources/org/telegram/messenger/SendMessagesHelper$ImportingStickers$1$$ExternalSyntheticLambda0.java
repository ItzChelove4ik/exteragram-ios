package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$ImportingStickers$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SendMessagesHelper.ImportingStickers.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.TL_stickers_createStickerSet f$2;
    public final /* synthetic */ TLObject f$3;

    public /* synthetic */ SendMessagesHelper$ImportingStickers$1$$ExternalSyntheticLambda0(SendMessagesHelper.ImportingStickers.AnonymousClass1 r1, TLRPC.TL_error r2, TLRPC.TL_stickers_createStickerSet r3, TLObject r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.ImportingStickers.AnonymousClass1.$r8$lambda$cv4tbiJSP7zbybwND9LPFGwcJv8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
