package com.exteragram.messenger.utils.network;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.telegram.messenger.AccountInstance;

/* loaded from: classes4.dex */
public final /* synthetic */ class RemoteUtils$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ AtomicInteger f$2;
    public final /* synthetic */ AtomicInteger f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ AtomicReference f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ Runnable f$7;

    public /* synthetic */ RemoteUtils$$ExternalSyntheticLambda3(AtomicBoolean r1, AccountInstance r2, AtomicInteger r3, AtomicInteger r4, Runnable r5, AtomicReference r6, int r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RemoteUtils.$r8$lambda$4QYm8KFjMBfJJ4laGtinXysTpck(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
