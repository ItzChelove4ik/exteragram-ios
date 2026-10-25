package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda141 implements RequestDelegate {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;
    public final /* synthetic */ TL_account.TL_birthday f$2;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda141(DialogsActivity r1, TLRPC.UserFull r2, TL_account.TL_birthday r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        DialogsActivity.m14393$r8$lambda$kZ8XcgUa61ldxJJ_yNsgw53Si0(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
