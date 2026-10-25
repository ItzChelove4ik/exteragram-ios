package org.telegram.ui;

import android.content.DialogInterface;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ExternalActionActivity$$ExternalSyntheticLambda10 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ ExternalActionActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ ExternalActionActivity$$ExternalSyntheticLambda10(ExternalActionActivity r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        ExternalActionActivity.$r8$lambda$yPHF6DaYfMtRV_2Sdlpg6i0SSOA(this.f$0, this.f$1, r2);
    }
}
