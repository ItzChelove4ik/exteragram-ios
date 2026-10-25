package org.telegram.ui.Components;

import android.content.DialogInterface;
import org.telegram.messenger.AccountInstance;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinCallAlert$$ExternalSyntheticLambda1 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ AccountInstance f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ JoinCallAlert$$ExternalSyntheticLambda1(AccountInstance r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r2) {
        JoinCallAlert.m11163$r8$lambda$CfMbXpJbAAye0f5VNCg9opDVkk(this.f$0, this.f$1, r2);
    }
}
