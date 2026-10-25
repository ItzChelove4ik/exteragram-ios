package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BulletinFactory$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Bulletin f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ BulletinFactory$$ExternalSyntheticLambda2(int r1, Bulletin r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r5) {
        BulletinFactory.$r8$lambda$yygjfMhXwGKIBOss6ktGnM1K6ow(this.f$0, this.f$1, this.f$2, (TLRPC.TL_messages_stickerSet) r5);
    }
}
