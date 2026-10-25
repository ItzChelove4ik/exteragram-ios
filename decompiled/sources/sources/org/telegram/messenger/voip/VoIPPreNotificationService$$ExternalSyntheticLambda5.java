package org.telegram.messenger.voip;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPPreNotificationService$$ExternalSyntheticLambda5 implements RequestDelegate {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ VoIPPreNotificationService$$ExternalSyntheticLambda5(Context r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        VoIPPreNotificationService.m5289$r8$lambda$nK1ZLTIlrG_zCIeNBBwOPP7xo(this.f$0, this.f$1, r2, r3);
    }
}
