package org.telegram.ui.web;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.WebInstantView;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebInstantView$Loader$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ WebInstantView.Loader f$0;

    public /* synthetic */ WebInstantView$Loader$$ExternalSyntheticLambda1(WebInstantView.Loader r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        WebInstantView.Loader.$r8$lambda$wwmfgbhnLy3gDBY_PgSI6qWa9dw(this.f$0, r1, r2);
    }
}
