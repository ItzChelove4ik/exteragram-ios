package org.telegram.messenger;

import android.content.DialogInterface;
import org.telegram.messenger.PasskeysController;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$1$$ExternalSyntheticLambda1 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Utilities.Callback3 f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ PasskeysController$1$$ExternalSyntheticLambda1(int r1, int r2, Utilities.Callback3 r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r7) {
        PasskeysController.AnonymousClass1.$r8$lambda$aD_TSB9_08HxxMQbIkVP1CwvnZ0(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
