package androidx.camera.core;

import androidx.camera.core.impl.ImageReaderProxy;

/* loaded from: classes3.dex */
public final /* synthetic */ class MetadataImageReader$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MetadataImageReader f$0;
    public final /* synthetic */ ImageReaderProxy.OnImageAvailableListener f$1;

    public /* synthetic */ MetadataImageReader$$ExternalSyntheticLambda1(MetadataImageReader r1, ImageReaderProxy.OnImageAvailableListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MetadataImageReader.$r8$lambda$OjJ1V45j4ZKbpW9cCAbCbNa1Ia4(this.f$0, this.f$1);
    }
}
