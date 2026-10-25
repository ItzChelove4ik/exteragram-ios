package com.google.firebase.crashlytics.internal.send;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class ReportQueue$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ReportQueue f$0;
    public final /* synthetic */ CountDownLatch f$1;

    public /* synthetic */ ReportQueue$$ExternalSyntheticLambda1(ReportQueue r1, CountDownLatch r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ReportQueue.$r8$lambda$8U76pxOta423qVuKYJ0HhfFLxJ4(this.f$0, this.f$1);
    }
}
