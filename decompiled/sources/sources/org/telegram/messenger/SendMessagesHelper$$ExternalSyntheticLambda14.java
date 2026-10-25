package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_messages_forwardMessages f$1;
    public final /* synthetic */ TLRPC.Peer f$10;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ LongSparseArray f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ ArrayList f$8;
    public final /* synthetic */ MessageObject f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda14(SendMessagesHelper r1, TLRPC.TL_messages_forwardMessages r2, long r3, int r5, boolean r6, boolean r7, LongSparseArray r8, ArrayList r9, ArrayList r10, MessageObject r11, TLRPC.Peer r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$x5jssyLCb_qqgISyBTfLlBFOtV0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10);
    }
}
