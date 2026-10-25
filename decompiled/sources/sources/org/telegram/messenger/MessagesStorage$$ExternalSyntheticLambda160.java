package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda160 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ LongSparseArray f$4;
    public final /* synthetic */ LongSparseArray f$5;
    public final /* synthetic */ ArrayList f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ CountDownLatch f$8;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda160(MessagesStorage r1, int r2, ArrayList r3, int r4, LongSparseArray r5, LongSparseArray r6, ArrayList r7, ArrayList r8, CountDownLatch r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4724$r8$lambda$WF5ccUa86RN6IjLqnT41ZGraZU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
