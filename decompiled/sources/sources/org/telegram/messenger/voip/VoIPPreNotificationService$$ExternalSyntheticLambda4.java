package org.telegram.messenger.voip;

import android.content.Context;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPPreNotificationService$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ VoIPPreNotificationService$$ExternalSyntheticLambda4(TLObject r1, TLRPC.TL_error r2, Context r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPPreNotificationService.$r8$lambda$FKGlbdAziKn85Rug4hrUyX7sDaA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
