package org.telegram.messenger;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.messenger.Timer;

/* loaded from: classes.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda187 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ Timer.Task f$1;
    public final /* synthetic */ Timer f$2;
    public final /* synthetic */ ArrayList f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ LongSparseArray f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda187(MediaDataController r1, Timer.Task r2, Timer r3, ArrayList r4, long r5, LongSparseArray r7, Runnable r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$bTvSSYxJQDxybCGLV3lubjLmdU4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
