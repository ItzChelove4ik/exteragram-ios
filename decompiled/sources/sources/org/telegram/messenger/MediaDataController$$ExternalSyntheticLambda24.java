package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda24 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda24(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        MediaDataController.$r8$lambda$ef9mi7LpkV1YHBMF2dnDqeJP3Qg(this.f$0, (TLRPC.messages_FoundStickerSets) r1, (TLRPC.TL_error) r2);
    }
}
