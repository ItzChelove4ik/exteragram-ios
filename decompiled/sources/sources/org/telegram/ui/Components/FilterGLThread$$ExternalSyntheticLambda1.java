package org.telegram.ui.Components;

import android.graphics.Bitmap;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes7.dex */
public final /* synthetic */ class FilterGLThread$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FilterGLThread f$0;
    public final /* synthetic */ Bitmap[] f$1;
    public final /* synthetic */ CountDownLatch f$2;

    public /* synthetic */ FilterGLThread$$ExternalSyntheticLambda1(FilterGLThread r1, Bitmap[] r2, CountDownLatch r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FilterGLThread.m10578$r8$lambda$WldJrnZoIw1FnwVYWDjnPGfDkQ(this.f$0, this.f$1, this.f$2);
    }
}
