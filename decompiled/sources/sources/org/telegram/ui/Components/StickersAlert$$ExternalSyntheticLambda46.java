package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StickersAlert$$ExternalSyntheticLambda46 implements Utilities.Callback {
    public final /* synthetic */ StickersAlert f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ StickersAlert$$ExternalSyntheticLambda46(StickersAlert r1, AlertDialog r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r5) {
        StickersAlert.m13078$r8$lambda$TCE1KZ9wFm_tAtSAKGp4tPHTgY(this.f$0, this.f$1, this.f$2, (TLRPC.User) r5);
    }
}
