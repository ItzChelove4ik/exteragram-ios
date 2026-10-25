package org.telegram.messenger;

import java.util.concurrent.ConcurrentHashMap;
import org.telegram.messenger.utils.EphemeralMessagesHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda320 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ EphemeralMessagesHelper.EphemeralUpdates.StructBuilder f$1;
    public final /* synthetic */ ConcurrentHashMap f$2;
    public final /* synthetic */ ConcurrentHashMap f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda320(MessagesController r1, EphemeralMessagesHelper.EphemeralUpdates.StructBuilder r2, ConcurrentHashMap r3, ConcurrentHashMap r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$bWsW1rXOrKVT9_5K2raoqZdSsL0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
