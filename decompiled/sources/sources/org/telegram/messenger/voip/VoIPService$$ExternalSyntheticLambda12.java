package org.telegram.messenger.voip;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLRPC.GroupCallParticipant f$1;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda12(VoIPService r1, TLRPC.GroupCallParticipant r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.m5308$r8$lambda$PuCZxzce8swtUpKnTfkdGhCMtI(this.f$0, this.f$1);
    }
}
