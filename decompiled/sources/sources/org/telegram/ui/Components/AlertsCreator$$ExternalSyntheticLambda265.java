package org.telegram.ui.Components;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda265 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ NumberPicker f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda265(Utilities.Callback r1, NumberPicker r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        AlertsCreator.$r8$lambda$MXrVsrwggVFspmfqS18NtXBMRT0(this.f$0, this.f$1, r2);
    }
}
