package org.telegram.ui.Stories;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Stories.SelfStoryViewsPage;

/* loaded from: classes7.dex */
public final /* synthetic */ class SelfStoryViewsPage$ViewsModel$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ SelfStoryViewsPage.ViewsModel f$0;
    public final /* synthetic */ int[] f$1;

    public /* synthetic */ SelfStoryViewsPage$ViewsModel$$ExternalSyntheticLambda1(SelfStoryViewsPage.ViewsModel r1, int[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SelfStoryViewsPage.ViewsModel.$r8$lambda$qHSazAMsWEta2v_5p35B_LurRDI(this.f$0, this.f$1, r2, r3);
    }
}
