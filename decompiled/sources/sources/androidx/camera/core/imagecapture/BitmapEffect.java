package androidx.camera.core.imagecapture;

import android.graphics.Bitmap;
import androidx.camera.core.processing.ImageProcessorRequest;
import androidx.camera.core.processing.InternalImageProcessor;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;

/* loaded from: classes3.dex */
public class BitmapEffect implements Operation<Packet<Bitmap>, Packet<Bitmap>> {
    private final InternalImageProcessor mProcessor;

    public BitmapEffect(InternalImageProcessor r1) {
        this.mProcessor = r1;
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public Packet<Bitmap> apply2(Packet<Bitmap> r3) {
        this.mProcessor.safeProcess(new ImageProcessorRequest(new RgbaImageProxy(r3), 1));
        throw null;
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ Packet<Bitmap> apply(Packet<Bitmap> r1) {
        return apply2(r1);
    }
}
