package org.telegram.ui.Components;

import android.content.SharedPreferences;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda165 implements Runnable {
    public final /* synthetic */ SharedPreferences f$0;
    public final /* synthetic */ TLRPC.TL_help_support f$1;
    public final /* synthetic */ AlertDialog f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ BaseFragment f$4;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda165(SharedPreferences r1, TLRPC.TL_help_support r2, AlertDialog r3, int r4, BaseFragment r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertsCreator.$r8$lambda$47BZmzpDx8mGT7Vhowhn1LRRYME(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
