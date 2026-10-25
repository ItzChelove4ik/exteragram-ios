package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda48 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ TLRPC.EncryptedChat f$0;
    public final /* synthetic */ NumberPicker f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda48(TLRPC.EncryptedChat r1, NumberPicker r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        AlertsCreator.m8756$r8$lambda$hMCyO_T3Eby2CzKyFOpV8zJgo(this.f$0, this.f$1, r2, r3);
    }
}
