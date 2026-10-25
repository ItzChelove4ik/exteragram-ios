package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$$ExternalSyntheticLambda53 implements RequestDelegate {
    public final /* synthetic */ AudioPlayerAlert f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ AudioPlayerAlert$$ExternalSyntheticLambda53(AudioPlayerAlert r1, int r2, boolean r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        AudioPlayerAlert.$r8$lambda$p3kOe0sqm9xMrIyfO9_Mdcoda7s(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
