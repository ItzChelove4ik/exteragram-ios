package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda488 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.messages_SavedReactionTags f$3;
    public final /* synthetic */ TLRPC.TL_messages_getSavedReactionTags f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda488(MessagesController r1, TLObject r2, long r3, TLRPC.messages_SavedReactionTags r5, TLRPC.TL_messages_getSavedReactionTags r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$5kKcGCKqz1m9MEDODl_5OhBrRBA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
