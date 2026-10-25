package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ TLRPC.TL_messages_savedDialogs f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda11(TopicsController r1, TLRPC.TL_messages_savedDialogs r2, long r3, LongSparseArray r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TopicsController.m5012$r8$lambda$X6YUdIsbg17keSuQNKdw6v42ng(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
