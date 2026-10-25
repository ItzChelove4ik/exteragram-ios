package androidx.camera.core;

import android.media.ImageReader;
import androidx.camera.core.impl.ImageReaderProxy;
import java.util.concurrent.Executor;

/* loaded from: classes3.dex */
public final /* synthetic */ class AndroidImageReaderProxy$$ExternalSyntheticLambda0 implements ImageReader.OnImageAvailableListener {
    public final /* synthetic */ AndroidImageReaderProxy f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ ImageReaderProxy.OnImageAvailableListener f$2;

    public /* synthetic */ AndroidImageReaderProxy$$ExternalSyntheticLambda0(AndroidImageReaderProxy r1, Executor r2, ImageReaderProxy.OnImageAvailableListener r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.media.ImageReader.OnImageAvailableListener
    public final void onImageAvailable(ImageReader r3) {
        AndroidImageReaderProxy.$r8$lambda$0d9VKBtme9NUYt20OFXADDntWdE(this.f$0, this.f$1, this.f$2, r3);
    }
}
