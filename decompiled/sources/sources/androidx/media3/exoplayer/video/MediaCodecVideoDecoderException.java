package androidx.media3.exoplayer.video;

import android.view.Surface;
import androidx.media3.exoplayer.mediacodec.MediaCodecDecoderException;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;

/* loaded from: classes4.dex */
public class MediaCodecVideoDecoderException extends MediaCodecDecoderException {
    public final boolean isSurfaceValid;
    public final int surfaceIdentityHashCode;

    public MediaCodecVideoDecoderException(Throwable r1, MediaCodecInfo r2, Surface r3) {
        super(r1, r2);
        this.surfaceIdentityHashCode = System.identityHashCode(r3);
        if (r3 != null) goto L5;
    L8:
        boolean r12 = true;
    L9:
        this.isSurfaceValid = r12;
        return;
    L5:
        if (r3.isValid() == true) goto L8;
        r12 = false;
        goto L9
    }
}
