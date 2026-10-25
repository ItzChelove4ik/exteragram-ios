package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$$ExternalSyntheticLambda52 implements RequestDelegate {
    public final /* synthetic */ AudioPlayerAlert f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ AudioPlayerAlert$$ExternalSyntheticLambda52(AudioPlayerAlert r1, int r2, boolean r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        AudioPlayerAlert.m8844$r8$lambda$DrXoS5R39WQJIas_NYABEDebA(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
