package androidx.camera.core.internal.compat.workaround;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.internal.compat.quirk.IncorrectJpegMetadataQuirk;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class JpegMetadataCorrector {
    private final IncorrectJpegMetadataQuirk mQuirk;

    public JpegMetadataCorrector(Quirks r2) {
        this.mQuirk = (IncorrectJpegMetadataQuirk) r2.get(IncorrectJpegMetadataQuirk.class);
    }

    public byte[] jpegImageToJpegByteArray(ImageProxy r1) {
        IncorrectJpegMetadataQuirk r0 = this.mQuirk;
        if (r0 != null) goto L7;
        ByteBuffer r02 = r1.getPlanes()[0].getBuffer();
        byte[] r12 = new byte[r02.capacity()];
        r02.rewind();
        r02.get(r12);
        return r12;
    L7:
        return r0.jpegImageToJpegByteArray(r1);
    }
}
