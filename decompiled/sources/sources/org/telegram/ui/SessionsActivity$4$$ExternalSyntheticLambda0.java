package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.SessionsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$4$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ SessionsActivity.AnonymousClass4 f$0;
    public final /* synthetic */ TLRPC.TL_authorization f$1;

    public /* synthetic */ SessionsActivity$4$$ExternalSyntheticLambda0(SessionsActivity.AnonymousClass4 r1, TLRPC.TL_authorization r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SessionsActivity.AnonymousClass4.$r8$lambda$b1XRCMrM8V1Zo14GNwt7cBoEDbQ(this.f$0, this.f$1, r2, r3);
    }
}
