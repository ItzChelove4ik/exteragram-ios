package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda16 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda16(BaseFragment r1, TLRPC.User r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        AlertsCreator.m8769$r8$lambda$otAJCGEVgtFbZWfVWG7cKeRdxU(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
