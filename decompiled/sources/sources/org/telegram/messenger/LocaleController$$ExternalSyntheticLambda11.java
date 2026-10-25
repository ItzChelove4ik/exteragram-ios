package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class LocaleController$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ LocaleController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ LocaleController.LocaleInfo f$2;
    public final /* synthetic */ TLRPC.TL_langPackDifference f$3;
    public final /* synthetic */ HashMap f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ LocaleController$$ExternalSyntheticLambda11(LocaleController r1, int r2, LocaleController.LocaleInfo r3, TLRPC.TL_langPackDifference r4, HashMap r5, Runnable r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LocaleController.$r8$lambda$Pg2PBPxRCoepG2Tkpuyt86V0Onw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
