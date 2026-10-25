package org.telegram.messenger.voip;

import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoipAudioManager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ VoipAudioManager$$ExternalSyntheticLambda2(Utilities.Callback2 r1, boolean r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoipAudioManager.m5376$r8$lambda$Uy8WgwUuc7Z63HOCKQXEBuQqQs(this.f$0, this.f$1, this.f$2);
    }
}
