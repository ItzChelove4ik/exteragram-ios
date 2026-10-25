package org.telegram.ui.Components;

import android.content.SharedPreferences;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda115 implements RequestDelegate {
    public final /* synthetic */ SharedPreferences f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ BaseFragment f$3;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda115(SharedPreferences r1, AlertDialog r2, int r3, BaseFragment r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        AlertsCreator.$r8$lambda$DmCpeigz3pbtaYG4DvqKXDA9KMw(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
