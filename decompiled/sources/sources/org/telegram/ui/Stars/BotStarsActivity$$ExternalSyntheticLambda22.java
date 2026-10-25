package org.telegram.ui.Stars;

import android.app.Activity;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsActivity$$ExternalSyntheticLambda22 implements RequestDelegate {
    public final /* synthetic */ BotStarsActivity f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;
    public final /* synthetic */ Activity f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ BotStarsActivity$$ExternalSyntheticLambda22(BotStarsActivity r1, TwoStepVerificationActivity r2, Activity r3, boolean r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        BotStarsActivity.$r8$lambda$3jojpMwrmFCZvY4c1PeLggRF7hk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9, r10);
    }
}
