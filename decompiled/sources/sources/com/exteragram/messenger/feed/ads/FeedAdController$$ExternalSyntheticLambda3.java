package com.exteragram.messenger.feed.ads;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedAdController$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback2 f$0;

    public /* synthetic */ FeedAdController$$ExternalSyntheticLambda3(Utilities.Callback2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        FeedAdController.$r8$lambda$8hucFROLudfrxYOpOPN8_6OECqs(this.f$0, r1, r2);
    }
}
