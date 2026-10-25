package org.telegram.ui.bots;

import android.app.Activity;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotLocation$$ExternalSyntheticLambda4 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ Activity f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ BotLocation$$ExternalSyntheticLambda4(Activity r1, boolean[] r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        BotLocation.$r8$lambda$NrUSfLmt5mpXY0LtaVefOUiGIzk(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
