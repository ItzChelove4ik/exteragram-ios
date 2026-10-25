package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.AccountInstance;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.JoinCallAlert;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinCallAlert$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ JoinCallAlert.JoinCallAlertDelegate f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ BaseFragment f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ TLRPC.Peer f$7;

    public /* synthetic */ JoinCallAlert$$ExternalSyntheticLambda0(AlertDialog r1, AccountInstance r2, JoinCallAlert.JoinCallAlertDelegate r3, long r4, Context r6, BaseFragment r7, int r8, TLRPC.Peer r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        JoinCallAlert.$r8$lambda$70ZW1ygh4lEyGwNrbLJBvkhCsZQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
