package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda8(TLObject r1, MessagesController r2, Utilities.Callback r3, Utilities.Callback r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BoostRepository.$r8$lambda$C4jQG5DhGfHGAkCMZQkFcRhfFTU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
