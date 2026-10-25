package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.StickersSearchAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class StickersSearchAdapter$1$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ StickersSearchAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ TLRPC.TL_messages_searchStickerSets f$1;

    public /* synthetic */ StickersSearchAdapter$1$$ExternalSyntheticLambda1(StickersSearchAdapter.AnonymousClass1 r1, TLRPC.TL_messages_searchStickerSets r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        StickersSearchAdapter.AnonymousClass1.$r8$lambda$UIuUF90zytquQ_odmWSGzObCFuY(this.f$0, this.f$1, r2, r3);
    }
}
