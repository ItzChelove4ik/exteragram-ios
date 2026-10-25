package org.telegram.ui.Components;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class AudioPlayerAlert$$ExternalSyntheticLambda50 implements RequestDelegate {
    public final /* synthetic */ AudioPlayerAlert f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ TLRPC.Document f$6;

    public /* synthetic */ AudioPlayerAlert$$ExternalSyntheticLambda50(AudioPlayerAlert r1, boolean r2, MessageObject r3, boolean r4, Runnable r5, long r6, TLRPC.Document r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        AudioPlayerAlert.$r8$lambda$FeNWvUcey5yCPVTr9Qapz_s2ujg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
