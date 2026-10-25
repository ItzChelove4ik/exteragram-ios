package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda529 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.messages_Dialogs f$4;
    public final /* synthetic */ LongSparseArray f$5;
    public final /* synthetic */ LongSparseArray f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda529(MessagesController r1, int r2, int r3, int r4, TLRPC.messages_Dialogs r5, LongSparseArray r6, LongSparseArray r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$W5wqf4bMKWcI_CVPKLDTfcxyAeA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
