package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.Timer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda242 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ Timer.Task f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ LongSparseArray f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ Timer f$5;
    public final /* synthetic */ AtomicInteger f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda242(MediaDataController r1, Timer.Task r2, long r3, LongSparseArray r5, boolean r6, Timer r7, AtomicInteger r8, Runnable r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r12, TLRPC.TL_error r13) {
        MediaDataController.$r8$lambda$1WLmCgC3S_9WydQRva_XKLVmkQI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, r12, r13);
    }
}
