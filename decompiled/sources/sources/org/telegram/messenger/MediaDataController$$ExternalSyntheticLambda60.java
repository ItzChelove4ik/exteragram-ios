package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda60 implements Utilities.Callback2 {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda60(MediaDataController r1, String r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        MediaDataController.$r8$lambda$ATHu0nIsQTDgrQWCP5FpBxqMKbU(this.f$0, this.f$1, this.f$2, (Boolean) r3, (TLRPC.TL_messages_stickerSet) r4);
    }
}
