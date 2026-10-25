package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes3.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda28 implements RequestDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Bundle f$3;
    public final /* synthetic */ TL_account.sendConfirmPhoneCode f$4;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda28(LaunchActivity r1, AlertDialog r2, String r3, Bundle r4, TL_account.sendConfirmPhoneCode r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        LaunchActivity.m15644$r8$lambda$tWarMWUYomkLYr2Ir07PmK0uo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
