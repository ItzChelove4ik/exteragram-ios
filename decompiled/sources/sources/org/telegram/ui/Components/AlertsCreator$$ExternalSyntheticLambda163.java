package org.telegram.ui.Components;

import androidx.core.util.Consumer;
import java.util.concurrent.atomic.AtomicBoolean;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda163 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda163(AtomicBoolean r1, Consumer r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        AlertsCreator.$r8$lambda$HEvbBaVkS3tygf2fstRGhrdMj8k(this.f$0, this.f$1, r2, r3);
    }
}
