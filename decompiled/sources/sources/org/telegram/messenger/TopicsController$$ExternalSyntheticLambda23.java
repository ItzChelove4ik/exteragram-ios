package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda23 implements Runnable {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.TL_messages_forumTopics f$3;
    public final /* synthetic */ LongSparseArray f$4;
    public final /* synthetic */ Runnable f$5;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda23(TopicsController r1, TLObject r2, long r3, TLRPC.TL_messages_forumTopics r5, LongSparseArray r6, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TopicsController.$r8$lambda$Ji32cTbokDzcKfANM8e3wIIuPP4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
