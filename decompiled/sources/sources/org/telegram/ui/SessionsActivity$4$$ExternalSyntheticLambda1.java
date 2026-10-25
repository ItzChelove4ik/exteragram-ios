package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.SessionsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$4$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SessionsActivity.AnonymousClass4 f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.TL_authorization f$2;

    public /* synthetic */ SessionsActivity$4$$ExternalSyntheticLambda1(SessionsActivity.AnonymousClass4 r1, TLRPC.TL_error r2, TLRPC.TL_authorization r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SessionsActivity.AnonymousClass4.$r8$lambda$5dCgd3KgszYdZCfVViwVAyJSk6k(this.f$0, this.f$1, this.f$2);
    }
}
