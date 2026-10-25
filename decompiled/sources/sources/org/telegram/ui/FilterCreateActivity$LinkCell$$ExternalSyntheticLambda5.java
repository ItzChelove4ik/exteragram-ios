package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.FilterCreateActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FilterCreateActivity$LinkCell$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ FilterCreateActivity.LinkCell f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ FilterCreateActivity$LinkCell$$ExternalSyntheticLambda5(FilterCreateActivity.LinkCell r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        FilterCreateActivity.LinkCell.m14816$r8$lambda$QjsyL2O0SICtVWSTKcd0Tj2hs4(this.f$0, this.f$1, r2, r3);
    }
}
