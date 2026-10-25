package androidx.media3.common.util;

import android.media.MediaFormat;
import androidx.media3.common.ColorInfo;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class MediaFormatUtil {
    public static void maybeSetByteBuffer(MediaFormat r0, String r1, byte[] r2) {
        if (r2 == null) goto L5;
        r0.setByteBuffer(r1, ByteBuffer.wrap(r2));
        return;
    }

    public static void maybeSetColorInfo(MediaFormat r2, ColorInfo r3) {
        if (r3 == null) goto L5;
        maybeSetInteger(r2, "color-transfer", r3.colorTransfer);
        maybeSetInteger(r2, "color-standard", r3.colorSpace);
        maybeSetInteger(r2, "color-range", r3.colorRange);
        maybeSetByteBuffer(r2, "hdr-static-info", r3.hdrStaticInfo);
        return;
    }

    public static void maybeSetFloat(MediaFormat r1, String r2, float r3) {
        if (r3 == (-1.0f)) goto L6;
        r1.setFloat(r2, r3);
        return;
    }

    public static void maybeSetInteger(MediaFormat r1, String r2, int r3) {
        if (r3 == (-1)) goto L6;
        r1.setInteger(r2, r3);
        return;
    }

    public static void setCsdBuffers(MediaFormat r3, List<byte[]> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L6;
        r3.setByteBuffer("csd-" + r0, ByteBuffer.wrap(r4.get(r0)));
        r0 = r0 + 1;
        goto L4
    }
}
