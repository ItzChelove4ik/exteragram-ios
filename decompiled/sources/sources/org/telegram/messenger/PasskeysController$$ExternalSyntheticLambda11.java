package org.telegram.messenger;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$$ExternalSyntheticLambda11 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ PasskeysController$$ExternalSyntheticLambda11(int r1, int r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r3) {
        PasskeysController.$r8$lambda$nn76CV_Y1sHdcydbtd3_ZJgwwi4(this.f$0, this.f$1, this.f$2, r3);
    }
}
