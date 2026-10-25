package com.exteragram.messenger.debug;

import com.exteragram.messenger.debug.LoadMonitor;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class LoadMonitor$$ExternalSyntheticLambda2 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ LoadMonitor.PendingAlert f$1;

    public /* synthetic */ LoadMonitor$$ExternalSyntheticLambda2(LaunchActivity r1, LoadMonitor.PendingAlert r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        LoadMonitor.$r8$lambda$WeeawD0afuH0k272RT_v_fiH5FA(this.f$0, this.f$1, r2, r3);
    }
}
