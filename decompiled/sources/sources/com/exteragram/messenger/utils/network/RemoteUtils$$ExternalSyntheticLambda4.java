package com.exteragram.messenger.utils.network;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TLRPC.TL_messages_search f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ AtomicReference f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ AtomicBoolean f$5;
    public final /* synthetic */ Utilities.Callback2 f$6;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda4(TLRPC.TL_messages_search r1, AccountInstance r2, Runnable r3, AtomicReference r4, int r5, AtomicBoolean r6, Utilities.Callback2 r7) {
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
        RemoteUtils.m1793$r8$lambda$Q2nxzL4QXDhawk8oMmFpxL7pOE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
