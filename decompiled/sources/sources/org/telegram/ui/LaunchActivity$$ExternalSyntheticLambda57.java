package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda57 implements Runnable {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Bundle f$4;
    public final /* synthetic */ TLObject f$5;
    public final /* synthetic */ TL_account.sendConfirmPhoneCode f$6;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda57(LaunchActivity r1, AlertDialog r2, TLRPC.TL_error r3, String r4, Bundle r5, TLObject r6, TL_account.sendConfirmPhoneCode r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LaunchActivity.m15612$r8$lambda$SQl19lZn8j_xFXtmuoYtbHiyKs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
