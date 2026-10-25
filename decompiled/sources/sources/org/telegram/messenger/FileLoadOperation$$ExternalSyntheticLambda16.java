package org.telegram.messenger;

import java.io.File;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileLoadOperation$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ FileLoadOperation f$0;
    public final /* synthetic */ File[] f$1;
    public final /* synthetic */ CountDownLatch f$2;

    public /* synthetic */ FileLoadOperation$$ExternalSyntheticLambda16(FileLoadOperation r1, File[] r2, CountDownLatch r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FileLoadOperation.$r8$lambda$DfvQlsgruMEVOteDUzHI3SAwV7Q(this.f$0, this.f$1, this.f$2);
    }
}
