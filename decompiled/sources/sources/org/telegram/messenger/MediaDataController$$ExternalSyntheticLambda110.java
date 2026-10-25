package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda110 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.TL_messages_stickerSet f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ TLRPC.InputStickerSet f$5;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda110(MediaDataController r1, TLRPC.TL_messages_stickerSet r2, String r3, Utilities.Callback r4, boolean r5, TLRPC.InputStickerSet r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.m4411$r8$lambda$PszCSYMNGfIpwn4KBIrmGdbkI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
