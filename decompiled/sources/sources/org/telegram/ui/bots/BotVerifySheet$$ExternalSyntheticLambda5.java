package org.telegram.ui.bots;

import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class BotVerifySheet$$ExternalSyntheticLambda5 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ BotVerifySheet$$ExternalSyntheticLambda5(boolean[] r1, int r2, long r3, long r5, Utilities.Callback r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r7;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r10, int r11) {
        BotVerifySheet.$r8$lambda$p8rYKYSGCmQGfb_lmklrZFNbtxQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r10, r11);
    }
}
