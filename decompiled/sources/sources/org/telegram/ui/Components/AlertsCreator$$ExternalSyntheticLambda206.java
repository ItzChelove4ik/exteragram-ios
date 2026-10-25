package org.telegram.ui.Components;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda206 implements View.OnClickListener {
    public final /* synthetic */ TLRPC.User f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ AlertDialog.Builder f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda206(TLRPC.User r1, BaseFragment r2, AlertDialog.Builder r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        AlertsCreator.m8739$r8$lambda$UzKwpdmH_YIHrzuajqlglY1go(this.f$0, this.f$1, this.f$2, r3);
    }
}
