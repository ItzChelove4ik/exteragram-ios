package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotStarsActivity$$ExternalSyntheticLambda15 implements RequestDelegate {
    public final /* synthetic */ BotStarsActivity f$0;
    public final /* synthetic */ Context f$1;

    public /* synthetic */ BotStarsActivity$$ExternalSyntheticLambda15(BotStarsActivity r1, Context r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        BotStarsActivity.m18865$r8$lambda$potYUyDVR_z7JVZmuT50J2YPGs(this.f$0, this.f$1, r2, r3);
    }
}
