package org.telegram.ui.Stars;

import android.app.Activity;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda74 implements Runnable {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentStarsTopup f$2;
    public final /* synthetic */ TL_stars.TL_starsTopupOption f$3;
    public final /* synthetic */ Activity f$4;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda74(List r1, Utilities.Callback2 r2, TLRPC.TL_inputStorePaymentStarsTopup r3, TL_stars.TL_starsTopupOption r4, Activity r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.$r8$lambda$DErIybbYfQ854cklBZI8aDA5518(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
