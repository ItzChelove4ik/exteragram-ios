package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.messages_Dialogs f$1;
    public final /* synthetic */ LongSparseArray f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ LongSparseIntArray f$5;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda8(MessagesController r1, TLRPC.messages_Dialogs r2, LongSparseArray r3, LongSparseArray r4, boolean r5, LongSparseIntArray r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$lPm1gXwFMO7yh7rQPO6if3LsSCA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
