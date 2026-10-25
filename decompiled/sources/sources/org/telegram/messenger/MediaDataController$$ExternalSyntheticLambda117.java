package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda117 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.messages_Messages f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ LongSparseArray f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda117(MediaDataController r1, TLRPC.TL_error r2, TLRPC.messages_Messages r3, long r4, long r6, LongSparseArray r8, boolean r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r8;
        this.f$6 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        MediaDataController.m4374$r8$lambda$3gx1Vj4LS046id9KDOPh193430(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r12, r13);
    }
}
