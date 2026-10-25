package androidx.camera.core.internal.compat.workaround;

import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.LargeJpegImageQuirk;
import kotlin.UByte;

/* loaded from: classes3.dex */
public class InvalidJpegDataParser {
    private final LargeJpegImageQuirk mQuirk;

    public InvalidJpegDataParser() {
        this.mQuirk = (LargeJpegImageQuirk) DeviceQuirks.get(LargeJpegImageQuirk.class);
    }

    public static int getJfifEoiMarkEndPosition(byte[] r7) {
        int r1 = 2;
    L4:
        if ((r1 + 4) > r7.length) goto L22;
        byte r2 = r7[r1];
        if (r2 != (-1)) goto L22;
        int r3 = r1 + 2;
        int r5 = ((r7[r3] & UByte.MAX_VALUE) << 8) | (r7[r1 + 3] & UByte.MAX_VALUE);
        if (r2 != (-1)) goto L21;
        if (r7[r1 + 1] != (-38)) goto L21;
    L12:
        int r0 = r3 + 2;
        if (r0 > r7.length) goto L14;
        if (r7[r3] != (-1)) goto L20;
        if (r7[r3 + 1] != (-39)) goto L20;
        return r0;
    L20:
        r3 = r3 + 1;
        goto L12
    L14:
        return -1;
    L21:
        r1 = r1 + (r5 + 2);
    L22:
        return -1;
    }

    public int getValidDataLength(byte[] r2) {
        LargeJpegImageQuirk r1 = this.mQuirk;
        if (r1 == null) goto L13;
        if (r1.shouldCheckInvalidJpegData(r2) == false) goto L13;
        int r12 = getJfifEoiMarkEndPosition(r2);
        if (r12 == (-1)) goto L11;
        return r12;
    L11:
        return r2.length;
    L13:
        return r2.length;
    }
}
