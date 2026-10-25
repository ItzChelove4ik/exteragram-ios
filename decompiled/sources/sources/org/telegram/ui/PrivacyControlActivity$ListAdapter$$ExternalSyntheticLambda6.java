package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.PrivacyControlActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda6 implements RequestDelegate {
    public final /* synthetic */ PrivacyControlActivity.ListAdapter f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;
    public final /* synthetic */ TL_account.TL_birthday f$2;

    public /* synthetic */ PrivacyControlActivity$ListAdapter$$ExternalSyntheticLambda6(PrivacyControlActivity.ListAdapter r1, TLRPC.UserFull r2, TL_account.TL_birthday r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        PrivacyControlActivity.ListAdapter.m17704$r8$lambda$H_zWYnTRl79SlhGWARbpEQxczo(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
