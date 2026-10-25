package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ TLRPC.TL_messages_savedDialogsSlice f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda12(TopicsController r1, TLRPC.TL_messages_savedDialogsSlice r2, long r3, LongSparseArray r5, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TopicsController.m5008$r8$lambda$4f3Jcyp_RLptkemU7s1sxpNRo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
