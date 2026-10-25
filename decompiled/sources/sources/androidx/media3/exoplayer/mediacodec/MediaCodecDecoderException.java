package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import androidx.media3.decoder.DecoderException;

/* loaded from: classes4.dex */
public class MediaCodecDecoderException extends DecoderException {
    public final MediaCodecInfo codecInfo;
    public final String diagnosticInfo;
    public final int errorCode;

    public MediaCodecDecoderException(Throwable r4, MediaCodecInfo r5) {
        StringBuilder r0 = new StringBuilder("Decoder failed: ");
        String r1 = null;
        if (r5 != null) goto L5;
        String r2 = null;
    L6:
        r0.append(r2);
        super(r0.toString(), r4);
        this.codecInfo = r5;
        if ((r4 instanceof MediaCodec.CodecException) == false) goto L9;
        r1 = ((MediaCodec.CodecException) r4).getDiagnosticInfo();
    L9:
        this.diagnosticInfo = r1;
        this.errorCode = getErrorCodeV23(r4);
        return;
    L5:
        r2 = r5.name;
        goto L6
    }

    private static int getErrorCodeV23(Throwable r1) {
        if ((r1 instanceof MediaCodec.CodecException) == true) goto L5;
        return 0;
    L5:
        return ((MediaCodec.CodecException) r1).getErrorCode();
    }
}
