package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class ShareTopView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ShareTopView f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ShareTopView$$ExternalSyntheticLambda0(ShareTopView r1, int r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        ShareTopView.$r8$lambda$zY9mcGV5kM599tjfwGXtmI_rAaw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
