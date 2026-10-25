package org.telegram.messenger;

import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda200 implements Utilities.Callback2 {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ MediaDataController.SearchStickersKey f$1;
    public final /* synthetic */ MediaDataController.SearchStickersResult f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda200(MediaDataController r1, MediaDataController.SearchStickersKey r2, MediaDataController.SearchStickersResult r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        MediaDataController.$r8$lambda$WK0txn5hFWzPa4HOB4yGNFyJ42Q(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.messages_FoundStickers) r7, (TLRPC.TL_error) r8);
    }
}
