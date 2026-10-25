package org.telegram.messenger;

import android.util.LongSparseArray;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class FilePathDatabase$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FilePathDatabase f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ LongSparseArray f$2;
    public final /* synthetic */ CountDownLatch f$3;

    public /* synthetic */ FilePathDatabase$$ExternalSyntheticLambda1(FilePathDatabase r1, ArrayList r2, LongSparseArray r3, CountDownLatch r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FilePathDatabase.$r8$lambda$6PYebqesT6JWlfPlQcpXJTLCrNY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
