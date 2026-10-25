package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda372 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.TL_messages_peerDialogs f$4;
    public final /* synthetic */ LongSparseArray f$5;
    public final /* synthetic */ TLRPC.TL_messages_dialogs f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda372(MessagesController r1, int r2, ArrayList r3, boolean r4, TLRPC.TL_messages_peerDialogs r5, LongSparseArray r6, TLRPC.TL_messages_dialogs r7) {
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
        MessagesController.$r8$lambda$2lfugyglnvtmW8TqExbwMPqrFvE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
