package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinGroupAlert$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ JoinGroupAlert f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_messages_importChatInvite f$3;

    public /* synthetic */ JoinGroupAlert$$ExternalSyntheticLambda11(JoinGroupAlert r1, TLRPC.TL_error r2, boolean r3, TLRPC.TL_messages_importChatInvite r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JoinGroupAlert.$r8$lambda$Bu_bjDryU4sMRJTu4W2Reb1G_cM(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
