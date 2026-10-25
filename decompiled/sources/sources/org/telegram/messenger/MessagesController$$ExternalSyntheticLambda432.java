package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda432 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.updates_ChannelDifference f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ LongSparseArray f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ long f$7;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda432(MessagesController r1, ArrayList r2, long r3, TLRPC.updates_ChannelDifference r5, TLRPC.Chat r6, LongSparseArray r7, int r8, long r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4534$r8$lambda$JSCCh6FqawYVZiHwhHXbRxKBcg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
