package org.telegram.messenger;

import java.util.concurrent.ConcurrentHashMap;
import org.telegram.messenger.utils.EphemeralMessagesHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda319 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ EphemeralMessagesHelper.EphemeralUpdates.StructBuilder f$1;
    public final /* synthetic */ ConcurrentHashMap f$2;
    public final /* synthetic */ ConcurrentHashMap f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda319(MessagesController r1, EphemeralMessagesHelper.EphemeralUpdates.StructBuilder r2, ConcurrentHashMap r3, ConcurrentHashMap r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$8hwVIeqNnC2WNulqlP58hDaq524(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
