package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelColorActivity$$ExternalSyntheticLambda7 implements Utilities.Callback {
    public final /* synthetic */ ChannelColorActivity f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ int[] f$3;

    public /* synthetic */ ChannelColorActivity$$ExternalSyntheticLambda7(ChannelColorActivity r1, boolean[] r2, int[] r3, int[] r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ChannelColorActivity.$r8$lambda$dm3hVaRGrt5FLzDQT5aHBRpqCzE(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.TL_error) r4);
    }
}
