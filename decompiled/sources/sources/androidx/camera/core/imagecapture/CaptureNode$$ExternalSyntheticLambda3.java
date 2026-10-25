package androidx.camera.core.imagecapture;

import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.core.util.Consumer;

/* loaded from: classes3.dex */
public final /* synthetic */ class CaptureNode$$ExternalSyntheticLambda3 implements Consumer {
    public final /* synthetic */ CaptureNode f$0;

    public /* synthetic */ CaptureNode$$ExternalSyntheticLambda3(CaptureNode r1) {
        this.f$0 = r1;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r1) {
        this.f$0.sendCaptureError((TakePictureManager.CaptureError) r1);
    }
}
