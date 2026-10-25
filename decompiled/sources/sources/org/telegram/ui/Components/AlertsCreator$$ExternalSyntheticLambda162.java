package org.telegram.ui.Components;

import android.content.Context;
import androidx.core.util.Consumer;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda162 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ AtomicBoolean f$2;
    public final /* synthetic */ Consumer f$3;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda162(boolean r1, Context r2, AtomicBoolean r3, Consumer r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        AlertsCreator.m8780$r8$lambda$tUfQ2PKxixHa2KrEl7AuAV6nLo(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
