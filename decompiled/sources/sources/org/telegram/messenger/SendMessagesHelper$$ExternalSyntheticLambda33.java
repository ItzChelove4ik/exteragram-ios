package org.telegram.messenger;

import android.util.SparseArray;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda33 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ ArrayList f$10;
    public final /* synthetic */ long f$11;
    public final /* synthetic */ int f$12;
    public final /* synthetic */ TLRPC.Message f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ArrayList f$4;
    public final /* synthetic */ int[] f$5;
    public final /* synthetic */ int[] f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ MessageObject f$8;
    public final /* synthetic */ SparseArray f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda33(SendMessagesHelper r1, boolean r2, TLRPC.Message r3, int r4, ArrayList r5, int[] r6, int[] r7, boolean r8, MessageObject r9, SparseArray r10, ArrayList r11, long r12, int r14) {
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
        this.f$11 = r12;
        this.f$12 = r14;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4912$r8$lambda$E50e6TU_fK1C86P0kKWKLpWQA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12);
    }
}
