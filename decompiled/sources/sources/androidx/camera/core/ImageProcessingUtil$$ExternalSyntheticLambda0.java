package androidx.camera.core;

import androidx.camera.core.ForwardingImageProxy;

/* loaded from: classes3.dex */
public final /* synthetic */ class ImageProcessingUtil$$ExternalSyntheticLambda0 implements ForwardingImageProxy.OnImageCloseListener {
    public final /* synthetic */ ImageProxy f$0;
    public final /* synthetic */ ImageProxy f$1;

    public /* synthetic */ ImageProcessingUtil$$ExternalSyntheticLambda0(ImageProxy r1, ImageProxy r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.camera.core.ForwardingImageProxy.OnImageCloseListener
    public final void onImageClose(ImageProxy r2) {
        ImageProcessingUtil.$r8$lambda$axt99y5JtrDZQNQirPB4gEFLUhQ(this.f$0, this.f$1, r2);
    }
}
