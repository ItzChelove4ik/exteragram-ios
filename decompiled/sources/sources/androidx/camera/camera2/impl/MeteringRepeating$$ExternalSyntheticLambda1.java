package androidx.camera.camera2.impl;

import android.graphics.SurfaceTexture;
import android.view.Surface;

/* loaded from: classes3.dex */
public final /* synthetic */ class MeteringRepeating$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Surface f$0;
    public final /* synthetic */ SurfaceTexture f$1;

    public /* synthetic */ MeteringRepeating$$ExternalSyntheticLambda1(Surface r1, SurfaceTexture r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MeteringRepeating.$r8$lambda$Q7CbjLDnricLd1mJRiIT8an2ZhA(this.f$0, this.f$1);
    }
}
