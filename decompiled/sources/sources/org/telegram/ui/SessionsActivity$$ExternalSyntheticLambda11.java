package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ SessionsActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ SessionsActivity$$ExternalSyntheticLambda11(SessionsActivity r1, TLRPC.TL_error r2, TLObject r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SessionsActivity.m18786$r8$lambda$h7ujV8NUJ6BvfWSJVk0oytKLDc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
