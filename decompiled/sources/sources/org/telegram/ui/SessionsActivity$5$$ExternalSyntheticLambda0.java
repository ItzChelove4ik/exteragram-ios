package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.SessionsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$5$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ SessionsActivity.AnonymousClass5 f$0;
    public final /* synthetic */ TLRPC.TL_authorization f$1;

    public /* synthetic */ SessionsActivity$5$$ExternalSyntheticLambda0(SessionsActivity.AnonymousClass5 r1, TLRPC.TL_authorization r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SessionsActivity.AnonymousClass5.$r8$lambda$MqhEaezZKH7pFjT5yHEf_ZweUNk(this.f$0, this.f$1, r2, r3);
    }
}
