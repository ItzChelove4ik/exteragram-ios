package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.Timer;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda241 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ Timer.Task f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ LongSparseArray f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ Timer f$6;
    public final /* synthetic */ AtomicInteger f$7;
    public final /* synthetic */ Runnable f$8;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda241(MediaDataController r1, Timer.Task r2, long r3, long r5, LongSparseArray r7, boolean r8, Timer r9, AtomicInteger r10, Runnable r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
        this.f$4 = r7;
        this.f$5 = r8;
        this.f$6 = r9;
        this.f$7 = r10;
        this.f$8 = r11;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r14, TLRPC.TL_error r15) {
        MediaDataController.m4457$r8$lambda$wNFYTRGiK5eb4bmqyWTYYFfgYE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, r14, r15);
    }
}
