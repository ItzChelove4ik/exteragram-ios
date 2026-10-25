package org.telegram.ui;

import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacyControlActivity$$ExternalSyntheticLambda15 implements RequestDelegate {
    public final /* synthetic */ PrivacyControlActivity f$0;
    public final /* synthetic */ AtomicInteger f$1;

    public /* synthetic */ PrivacyControlActivity$$ExternalSyntheticLambda15(PrivacyControlActivity r1, AtomicInteger r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PrivacyControlActivity.$r8$lambda$MTsO0Wx8cKy98TqCTS8abWRkEDk(this.f$0, this.f$1, r2, r3);
    }
}
