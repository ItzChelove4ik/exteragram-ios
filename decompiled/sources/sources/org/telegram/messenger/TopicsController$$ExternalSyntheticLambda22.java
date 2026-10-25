package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.HashSet;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class TopicsController$$ExternalSyntheticLambda22 implements Runnable {
    public final /* synthetic */ TopicsController f$0;
    public final /* synthetic */ TLRPC.TL_messages_savedDialogs f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ LongSparseArray f$4;
    public final /* synthetic */ HashSet f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ TopicsController$$ExternalSyntheticLambda22(TopicsController r1, TLRPC.TL_messages_savedDialogs r2, long r3, ArrayList r5, LongSparseArray r6, HashSet r7, Runnable r8) {
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
        TopicsController.$r8$lambda$53lPsQHNEw0cIHI5V_MORCIF4Iw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
