package androidx.camera.core;

import androidx.camera.core.impl.ImageReaderProxy;

/* loaded from: classes3.dex */
public final /* synthetic */ class AndroidImageReaderProxy$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AndroidImageReaderProxy f$0;
    public final /* synthetic */ ImageReaderProxy.OnImageAvailableListener f$1;

    public /* synthetic */ AndroidImageReaderProxy$$ExternalSyntheticLambda1(AndroidImageReaderProxy r1, ImageReaderProxy.OnImageAvailableListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AndroidImageReaderProxy.m581$r8$lambda$VU285rPvGaxlLdZyrpjv6_gV1k(this.f$0, this.f$1);
    }
}
