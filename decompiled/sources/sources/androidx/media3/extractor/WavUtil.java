package androidx.media3.extractor;

import androidx.media3.common.util.Util;

/* loaded from: classes4.dex */
public abstract class WavUtil {
    public static int getPcmEncodingForType(int r2, int r3) {
        if (r2 == 1) goto L15;
        if (r2 == 3) goto L10;
        if (r2 == 65534) goto L15;
        return 0;
    L10:
        if (r3 != 32) goto L13;
        return 4;
    L13:
        return 0;
    L15:
        return Util.getPcmEncoding(r3);
    }
}
