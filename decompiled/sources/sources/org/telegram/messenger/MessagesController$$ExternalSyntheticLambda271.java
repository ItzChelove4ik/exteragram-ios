package org.telegram.messenger;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda271 implements MessagesStorage.IntCallback {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.InputPeer f$4;
    public final /* synthetic */ long f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda271(MessagesController r1, long r2, int r4, boolean r5, TLRPC.InputPeer r6, long r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // org.telegram.messenger.MessagesStorage.IntCallback
    public final void run(int r10) {
        MessagesController.$r8$lambda$h7RiC73DwFujhLFBk81ecvFcowQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r10);
    }
}
