package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestMetadata;

/* loaded from: classes3.dex */
public final /* synthetic */ class ComboRequestListener$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ Request.Listener f$0;
    public final /* synthetic */ RequestMetadata f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ FrameInfo f$3;

    public /* synthetic */ ComboRequestListener$$ExternalSyntheticLambda10(Request.Listener r1, RequestMetadata r2, long r3, FrameInfo r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ComboRequestListener.$r8$lambda$7rkPM_7WH32ODeSIUTwHJqT1D9M(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
