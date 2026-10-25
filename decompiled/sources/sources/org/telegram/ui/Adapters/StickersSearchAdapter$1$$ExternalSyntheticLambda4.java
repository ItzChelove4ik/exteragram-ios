package org.telegram.ui.Adapters;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.StickersSearchAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class StickersSearchAdapter$1$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ StickersSearchAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_messages_searchStickerSets f$1;
    public final /* synthetic */ TLObject f$2;

    public /* synthetic */ StickersSearchAdapter$1$$ExternalSyntheticLambda4(StickersSearchAdapter.AnonymousClass1 r1, TLRPC.TL_messages_searchStickerSets r2, TLObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StickersSearchAdapter.AnonymousClass1.$r8$lambda$aEoPFTJYSRKeD3Zg_1HnHYVFDwQ(this.f$0, this.f$1, this.f$2);
    }
}
