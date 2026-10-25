package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.JoinCallAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinCallAlert$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ AccountInstance f$2;
    public final /* synthetic */ JoinCallAlert.JoinCallAlertDelegate f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ Context f$5;
    public final /* synthetic */ BaseFragment f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ TLRPC.Peer f$8;

    public /* synthetic */ JoinCallAlert$$ExternalSyntheticLambda8(AlertDialog r1, TLObject r2, AccountInstance r3, JoinCallAlert.JoinCallAlertDelegate r4, long r5, Context r7, BaseFragment r8, int r9, TLRPC.Peer r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JoinCallAlert.$r8$lambda$WulgJK5bRda5E1DckB3UPxblCDI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
