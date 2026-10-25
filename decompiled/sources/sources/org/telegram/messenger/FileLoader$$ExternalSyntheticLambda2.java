package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class FileLoader$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ FileLoader f$0;
    public final /* synthetic */ FileLoadOperation[] f$1;
    public final /* synthetic */ CountDownLatch f$10;
    public final /* synthetic */ TLRPC.Document f$2;
    public final /* synthetic */ ImageLocation f$3;
    public final /* synthetic */ Object f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ FileLoadOperationStream f$6;
    public final /* synthetic */ long f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ FileLoader$$ExternalSyntheticLambda2(FileLoader r1, FileLoadOperation[] r2, TLRPC.Document r3, ImageLocation r4, Object r5, int r6, FileLoadOperationStream r7, long r8, boolean r10, int r11, CountDownLatch r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FileLoader.$r8$lambda$i4OvadzAbHwIFJ_692SihZzn3xQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10);
    }
}
