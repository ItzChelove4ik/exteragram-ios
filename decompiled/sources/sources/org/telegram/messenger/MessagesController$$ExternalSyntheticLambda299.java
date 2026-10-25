package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.HashMap;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda299 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ HashMap f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ LongSparseArray f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ int f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda299(MessagesController r1, HashMap r2, String r3, TLObject r4, LongSparseArray r5, long r6, int r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$mgrnFGnHQJaFxiAGsHdW4yC_Y5k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
