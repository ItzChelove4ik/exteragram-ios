package org.telegram.ui.Components.Premium.boosts;

import android.content.DialogInterface;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostDialogs$$ExternalSyntheticLambda26 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ BoostDialogs$$ExternalSyntheticLambda26(AtomicBoolean r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        BoostDialogs.$r8$lambda$hQzqBx4sRzdZK3tycFZNmRhRFFM(this.f$0, this.f$1, r2);
    }
}
