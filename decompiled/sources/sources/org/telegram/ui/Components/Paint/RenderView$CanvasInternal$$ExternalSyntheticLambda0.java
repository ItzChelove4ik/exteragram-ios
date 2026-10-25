package org.telegram.ui.Components.Paint;

import android.graphics.Bitmap;
import java.util.concurrent.CountDownLatch;
import org.telegram.ui.Components.Paint.RenderView;

/* loaded from: classes7.dex */
public final /* synthetic */ class RenderView$CanvasInternal$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ RenderView.CanvasInternal f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ Bitmap[] f$3;
    public final /* synthetic */ CountDownLatch f$4;

    public /* synthetic */ RenderView$CanvasInternal$$ExternalSyntheticLambda0(RenderView.CanvasInternal r1, boolean r2, boolean r3, Bitmap[] r4, CountDownLatch r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RenderView.CanvasInternal.$r8$lambda$OSNQXgqVWiQqaOxmqk50NcKfeTg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
