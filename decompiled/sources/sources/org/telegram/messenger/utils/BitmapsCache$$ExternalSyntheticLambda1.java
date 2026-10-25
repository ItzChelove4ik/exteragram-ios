package org.telegram.messenger.utils;

import android.graphics.Bitmap;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final /* synthetic */ class BitmapsCache$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ BitmapsCache f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ Bitmap[] f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ImmutableByteArrayOutputStream[] f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ RandomAccessFile f$6;
    public final /* synthetic */ ArrayList f$7;
    public final /* synthetic */ CountDownLatch[] f$8;

    public /* synthetic */ BitmapsCache$$ExternalSyntheticLambda1(BitmapsCache r1, AtomicBoolean r2, Bitmap[] r3, int r4, ImmutableByteArrayOutputStream[] r5, int r6, RandomAccessFile r7, ArrayList r8, CountDownLatch[] r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BitmapsCache.$r8$lambda$FoChbovdHYgidAWB8EndELMo4u4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
