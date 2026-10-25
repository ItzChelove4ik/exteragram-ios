package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda136 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ TLRPC.TL_langPackLanguage f$0;
    public final /* synthetic */ LaunchActivity f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda136(TLRPC.TL_langPackLanguage r1, LaunchActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        AlertsCreator.$r8$lambda$6ZqQMNFwAbwiSWlJWIeA75Lfd6A(this.f$0, this.f$1, r2, r3);
    }
}
