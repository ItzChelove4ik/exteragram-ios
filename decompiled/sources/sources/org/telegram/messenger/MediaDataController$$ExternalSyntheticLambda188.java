package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.concurrent.atomic.AtomicInteger;
import org.telegram.messenger.Timer;

/* loaded from: classes.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda188 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ Timer.Task f$1;
    public final /* synthetic */ long f$10;
    public final /* synthetic */ LongSparseArray f$2;
    public final /* synthetic */ AtomicInteger f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ Timer f$6;
    public final /* synthetic */ LongSparseArray f$7;
    public final /* synthetic */ LongSparseArray f$8;
    public final /* synthetic */ boolean f$9;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda188(MediaDataController r1, Timer.Task r2, LongSparseArray r3, AtomicInteger r4, Runnable r5, int r6, Timer r7, LongSparseArray r8, LongSparseArray r9, boolean r10, long r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$LfaSk3QHQofX3vRpgncbP0suO6w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10);
    }
}
