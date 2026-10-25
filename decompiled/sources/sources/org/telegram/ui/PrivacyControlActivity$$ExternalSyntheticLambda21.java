package org.telegram.ui;

import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacyControlActivity$$ExternalSyntheticLambda21 implements Runnable {
    public final /* synthetic */ PrivacyControlActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ AtomicInteger f$3;

    public /* synthetic */ PrivacyControlActivity$$ExternalSyntheticLambda21(PrivacyControlActivity r1, TLRPC.TL_error r2, TLObject r3, AtomicInteger r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PrivacyControlActivity.$r8$lambda$0o3BObn6pM7dAnNh74UnZCV2VG4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
