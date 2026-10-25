package androidx.camera.core.imagecapture;

import androidx.camera.core.ImageInfo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.ImmutableImageInfo;
import androidx.camera.core.SettableImageProxy;
import androidx.camera.core.processing.Operation;
import androidx.camera.core.processing.Packet;

/* loaded from: classes3.dex */
public class JpegImage2Result implements Operation<Packet<ImageProxy>, ImageProxy> {
    public JpegImage2Result() {
    }

    /* renamed from: apply, reason: avoid collision after fix types in other method */
    public ImageProxy apply2(Packet<ImageProxy> r8) {
        ImageProxy r7 = r8.getData();
        ImageInfo r0 = ImmutableImageInfo.create(r7.getImageInfo().getTagBundle(), r7.getImageInfo().getTimestamp(), r8.getRotationDegrees(), r8.getSensorToBufferTransform(), r7.getImageInfo().getFlashState());
        SettableImageProxy r1 = new SettableImageProxy(r7, r8.getSize(), r0);
        r1.setCropRect(r8.getCropRect());
        return r1;
    }

    @Override // androidx.camera.core.processing.Operation
    public /* bridge */ /* synthetic */ ImageProxy apply(Packet<ImageProxy> r1) {
        return apply2(r1);
    }
}
