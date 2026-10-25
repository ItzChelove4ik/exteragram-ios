package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ long f$10;
    public final /* synthetic */ int f$11;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ SparseArray f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ TLRPC.Message f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda12(SendMessagesHelper r1, int[] r2, int[] r3, boolean r4, boolean r5, MessageObject r6, SparseArray r7, ArrayList r8, TLRPC.Message r9, int r10, long r11, int r13) {
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
        this.f$10 = r11;
        this.f$11 = r13;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4936$r8$lambda$o2JT4jJiNXJT8pA7xA5R_ugP18(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
