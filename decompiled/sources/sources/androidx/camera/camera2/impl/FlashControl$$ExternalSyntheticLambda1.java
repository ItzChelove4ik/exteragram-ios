package androidx.camera.camera2.impl;

import androidx.camera.core.ImageCapture;
import kotlinx.coroutines.CompletableDeferred;

/* loaded from: classes3.dex */
public final /* synthetic */ class FlashControl$$ExternalSyntheticLambda1 implements ImageCapture.ScreenFlashListener {
    public final /* synthetic */ CompletableDeferred f$0;

    public /* synthetic */ FlashControl$$ExternalSyntheticLambda1(CompletableDeferred r1) {
        this.f$0 = r1;
    }

    @Override // androidx.camera.core.ImageCapture.ScreenFlashListener
    public final void onCompleted() {
        FlashControl.$r8$lambda$m0mZpEIYJi1SscD8hMkjjTqPaT8(this.f$0);
    }
}
