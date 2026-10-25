package org.telegram.ui.Components;

import android.content.Context;
import android.content.DialogInterface;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda73 implements DialogInterface.OnClickListener {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ Theme.ResourcesProvider f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda73(int[] r1, Context r2, Theme.ResourcesProvider r3, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r7, int r8) {
        AlertsCreator.m8773$r8$lambda$qtgXQWVVuTfjmRipXSim1E8P8(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
