package org.telegram.messenger.voip;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class VoIPService$$ExternalSyntheticLambda65 implements Runnable {
    public final /* synthetic */ VoIPService f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ byte[] f$3;

    public /* synthetic */ VoIPService$$ExternalSyntheticLambda65(VoIPService r1, TLRPC.TL_error r2, TLObject r3, byte[] r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VoIPService.$r8$lambda$6GVqzEJMWq3WiObgCgfMD2HDXrU(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
