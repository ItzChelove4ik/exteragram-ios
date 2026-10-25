package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda72 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ ArrayList f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ TLRPC.messages_Dialogs f$5;
    public final /* synthetic */ ArrayList f$6;
    public final /* synthetic */ LongSparseArray f$7;
    public final /* synthetic */ LongSparseArray f$8;
    public final /* synthetic */ Runnable f$9;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda72(MessagesController r1, int r2, ArrayList r3, ArrayList r4, ArrayList r5, TLRPC.messages_Dialogs r6, ArrayList r7, LongSparseArray r8, LongSparseArray r9, Runnable r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$okK_QYyfG2LHuRqLxzoYJ1ufw_Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9);
    }
}
