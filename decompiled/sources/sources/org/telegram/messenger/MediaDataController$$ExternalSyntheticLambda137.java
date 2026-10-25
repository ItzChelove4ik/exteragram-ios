package org.telegram.messenger;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda137 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.Message f$1;
    public final /* synthetic */ MessagesStorage.TopicKey f$2;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda137(MediaDataController r1, TLRPC.Message r2, MessagesStorage.TopicKey r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$KoIGksBHZPanj9AfcoJ27eZlPOQ(this.f$0, this.f$1, this.f$2);
    }
}
