package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.SessionsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$6$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ SessionsActivity.AnonymousClass6 f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ SessionsActivity$6$$ExternalSyntheticLambda2(SessionsActivity.AnonymousClass6 r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SessionsActivity.AnonymousClass6.$r8$lambda$QVwea_wvsgxht0SpZycwkqDKEeU(this.f$0, this.f$1, r2, r3);
    }
}
