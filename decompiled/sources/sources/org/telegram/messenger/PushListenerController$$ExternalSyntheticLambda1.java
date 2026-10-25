package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class PushListenerController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ TLRPC.TL_updates f$1;

    public /* synthetic */ PushListenerController$$ExternalSyntheticLambda1(int r1, TLRPC.TL_updates r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PushListenerController.$r8$lambda$iPr3PgkHJLl5aQDihBXflfLF_jI(this.f$0, this.f$1);
    }
}
