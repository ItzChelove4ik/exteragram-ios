package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda414 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.updates_ChannelDifference f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.Chat f$3;
    public final /* synthetic */ LongSparseArray f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ long f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda414(MessagesController r1, TLRPC.updates_ChannelDifference r2, long r3, TLRPC.Chat r5, LongSparseArray r6, int r7, long r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$L5OkG7mAaygMtlTkzyQT5JgfnT4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
