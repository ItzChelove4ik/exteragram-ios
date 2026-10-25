package org.telegram.ui.Components;

import android.content.DialogInterface;
import androidx.core.util.Consumer;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda164 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda164(AtomicBoolean r1, Consumer r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        AlertsCreator.$r8$lambda$e_UUPM06Fsq_qCQnM0W1euYrafk(this.f$0, this.f$1, r2);
    }
}
