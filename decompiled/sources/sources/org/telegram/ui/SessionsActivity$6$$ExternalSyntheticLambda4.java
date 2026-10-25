package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.SessionsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$6$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ SessionsActivity.AnonymousClass6 f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ SessionsActivity$6$$ExternalSyntheticLambda4(SessionsActivity.AnonymousClass6 r1, TLObject r2, TLRPC.TL_error r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SessionsActivity.AnonymousClass6.m18821$r8$lambda$ehyo9R8BHFGR1BrWYIR1YxOIFo(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
