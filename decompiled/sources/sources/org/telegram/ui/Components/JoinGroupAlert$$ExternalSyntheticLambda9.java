package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class JoinGroupAlert$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ JoinGroupAlert f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.Updates f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.TL_messages_importChatInvite f$4;

    public /* synthetic */ JoinGroupAlert$$ExternalSyntheticLambda9(JoinGroupAlert r1, TLRPC.TL_error r2, TLRPC.Updates r3, int r4, TLRPC.TL_messages_importChatInvite r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        JoinGroupAlert.$r8$lambda$4wMXaiw5W9k8YjZSy1ZU0duA2lc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
