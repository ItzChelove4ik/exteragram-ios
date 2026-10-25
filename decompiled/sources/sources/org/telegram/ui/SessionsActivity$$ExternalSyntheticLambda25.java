package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionsActivity$$ExternalSyntheticLambda25 implements Runnable {
    public final /* synthetic */ SessionsActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLRPC.TL_authorization f$3;

    public /* synthetic */ SessionsActivity$$ExternalSyntheticLambda25(SessionsActivity r1, AlertDialog r2, TLRPC.TL_error r3, TLRPC.TL_authorization r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SessionsActivity.m18784$r8$lambda$X2zxl5yNBC70IUfQ62qy9vk9Gk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
