package org.telegram.ui;

import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacyControlActivity$$ExternalSyntheticLambda16 implements RequestDelegate {
    public final /* synthetic */ PrivacyControlActivity f$0;
    public final /* synthetic */ AtomicInteger f$1;

    public /* synthetic */ PrivacyControlActivity$$ExternalSyntheticLambda16(PrivacyControlActivity r1, AtomicInteger r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PrivacyControlActivity.$r8$lambda$nQ0e258vBx5D9HCiJLJPh6RYrJ8(this.f$0, this.f$1, r2, r3);
    }
}
