package org.telegram.ui.Components;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda254 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ NumberPicker f$1;
    public final /* synthetic */ NumberPicker f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda254(Utilities.Callback r1, NumberPicker r2, NumberPicker r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r3) {
        AlertsCreator.$r8$lambda$mijQ80opxDuz6vy_2xRFlG5lLzc(this.f$0, this.f$1, this.f$2, r3);
    }
}
