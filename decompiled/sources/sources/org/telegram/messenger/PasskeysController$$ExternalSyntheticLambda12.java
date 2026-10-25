package org.telegram.messenger;

import android.content.Context;
import androidx.credentials.CredentialManager;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$$ExternalSyntheticLambda12 implements Utilities.Callback2 {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ CredentialManager f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ PasskeysController$$ExternalSyntheticLambda12(AlertDialog r1, Utilities.Callback2 r2, CredentialManager r3, Context r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r8, Object r9) {
        PasskeysController.m4813$r8$lambda$uzlz7C9RirnBVnV8ty3z_v7jkQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TL_account.passkeyRegistrationOptions) r8, (TLRPC.TL_error) r9);
    }
}
