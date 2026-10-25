package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BulletinFactory$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ TLRPC.InputStickerSet f$1;

    public /* synthetic */ BulletinFactory$$ExternalSyntheticLambda1(Utilities.Callback r1, TLRPC.InputStickerSet r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BulletinFactory.$r8$lambda$efZiD0xXuti1KR9p63wAu8mw3Qo(this.f$0, this.f$1);
    }
}
