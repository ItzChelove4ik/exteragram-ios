package org.telegram.ui;

import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacyControlActivity$$ExternalSyntheticLambda18 implements RequestDelegate {
    public final /* synthetic */ PrivacyControlActivity f$0;
    public final /* synthetic */ TLRPC.GlobalPrivacySettings f$1;
    public final /* synthetic */ TL_account.setGlobalPrivacySettings f$2;
    public final /* synthetic */ AtomicInteger f$3;

    public /* synthetic */ PrivacyControlActivity$$ExternalSyntheticLambda18(PrivacyControlActivity r1, TLRPC.GlobalPrivacySettings r2, TL_account.setGlobalPrivacySettings r3, AtomicInteger r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        PrivacyControlActivity.m17620$r8$lambda$NPRUw1VZrsPecsGLTkDaX9zLAg(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
