package org.telegram.ui.Components.Premium.boosts;

import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ TLRPC.Chat f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda16(TLRPC.Chat r1, int r2, List r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BoostRepository.$r8$lambda$a40JYBpE5PrUDHQjsroWMGf1XzQ(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
