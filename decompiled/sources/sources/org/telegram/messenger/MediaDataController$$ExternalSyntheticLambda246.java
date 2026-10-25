package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda246 implements Utilities.Callback2 {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ TLRPC.InputStickerSet f$3;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda246(MediaDataController r1, String r2, Utilities.Callback r3, TLRPC.InputStickerSet r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        MediaDataController.$r8$lambda$sh6_OdNNEA7wk_jYe51izXywAuo(this.f$0, this.f$1, this.f$2, this.f$3, (Boolean) r7, (TLRPC.TL_messages_stickerSet) r8);
    }
}
