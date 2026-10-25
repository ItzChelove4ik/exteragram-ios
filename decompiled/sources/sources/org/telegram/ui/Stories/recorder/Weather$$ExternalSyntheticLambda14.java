package org.telegram.ui.Stories.recorder;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes7.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ double f$3;
    public final /* synthetic */ double f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ Weather$$ExternalSyntheticLambda14(int[] r1, TLObject r2, Utilities.Callback r3, double r4, double r6, String r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Weather.$r8$lambda$pYcAfzKADThXEZeQ0_S7R5cfPv4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
