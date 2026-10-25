package org.telegram.messenger;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public final /* synthetic */ class FilePathDatabase$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ FilePathDatabase f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ long[] f$2;
    public final /* synthetic */ CountDownLatch f$3;

    public /* synthetic */ FilePathDatabase$$ExternalSyntheticLambda10(FilePathDatabase r1, ArrayList r2, long[] r3, CountDownLatch r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FilePathDatabase.$r8$lambda$sCjsA8v8FOBY0rQXCUYWKxxAycw(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
