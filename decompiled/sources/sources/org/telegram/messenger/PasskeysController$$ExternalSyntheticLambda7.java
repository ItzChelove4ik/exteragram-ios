package org.telegram.messenger;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes5.dex */
public final /* synthetic */ class PasskeysController$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TL_account.registerPasskey f$2;
    public final /* synthetic */ Utilities.Callback2 f$3;

    public /* synthetic */ PasskeysController$$ExternalSyntheticLambda7(Context r1, int r2, TL_account.registerPasskey r3, Utilities.Callback2 r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PasskeysController.$r8$lambda$y0t8xdzxstUVdPjML0pMI_yGUOg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
