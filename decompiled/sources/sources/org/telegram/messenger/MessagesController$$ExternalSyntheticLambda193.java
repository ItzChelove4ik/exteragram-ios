package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda193 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_channels_sendAsPeers f$1;
    public final /* synthetic */ LongSparseArray f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ MessagesController.SendAsPeersInfo f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda193(MessagesController r1, TLRPC.TL_channels_sendAsPeers r2, LongSparseArray r3, long r4, MessagesController.SendAsPeersInfo r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$gOPj6OmWLLiTUYTX3alGT8JM3hY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
