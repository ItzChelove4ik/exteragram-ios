package com.exteragram.messenger.utils;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class IntentsController$$ExternalSyntheticLambda15 implements Utilities.Callback {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ ProfileActivity f$1;

    public /* synthetic */ IntentsController$$ExternalSyntheticLambda15(AlertDialog r1, ProfileActivity r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        IntentsController.$r8$lambda$fMSENhVoH4_aOqMBacbw6n0YDio(this.f$0, this.f$1, (TLRPC.User) r2);
    }
}
