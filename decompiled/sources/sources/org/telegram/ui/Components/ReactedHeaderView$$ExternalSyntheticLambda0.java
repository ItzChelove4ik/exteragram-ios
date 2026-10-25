package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ReactedHeaderView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ReactedHeaderView f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.Chat f$2;

    public /* synthetic */ ReactedHeaderView$$ExternalSyntheticLambda0(ReactedHeaderView r1, long r2, TLRPC.Chat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        ReactedHeaderView.$r8$lambda$DSbHacLPp0RJl_2x7kUvY0nNLa8(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
