package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class PrivacyControlActivity$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ PrivacyControlActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ TLRPC.GlobalPrivacySettings f$3;
    public final /* synthetic */ TL_account.setGlobalPrivacySettings f$4;

    public /* synthetic */ PrivacyControlActivity$$ExternalSyntheticLambda23(PrivacyControlActivity r1, TLRPC.TL_error r2, boolean[] r3, TLRPC.GlobalPrivacySettings r4, TL_account.setGlobalPrivacySettings r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PrivacyControlActivity.$r8$lambda$JCBa1yz7BbU1OEayqsS5QpmZEF0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
