package org.telegram.messenger.voip;

import android.content.Context;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPGroupNotification$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ Context f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ VoIPGroupNotification$$ExternalSyntheticLambda3(int r1, long r2, long r4, int r6, boolean r7, Context r8, String r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r6;
        this.f$4 = r7;
        this.f$5 = r8;
        this.f$6 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        VoIPGroupNotification.$r8$lambda$l9oGtBJzWocEzM9fCtddt9UoyiE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r12, r13);
    }
}
