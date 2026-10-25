package androidx.camera.core.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.Quirk;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import kotlin.UByte;

/* loaded from: classes3.dex */
public final class IncorrectJpegMetadataQuirk implements Quirk {
    private static final Set<String> SAMSUNG_DEVICES = null;

    static {
        SAMSUNG_DEVICES = new HashSet(Arrays.asList(new String[]{"A24", "BEYOND0", "BEYOND2"}));
    }

    public IncorrectJpegMetadataQuirk() {
    }

    private boolean canParseSosMarker(byte[] r4) {
        int r0 = 2;
    L4:
        if ((r0 + 4) > r4.length) goto L14;
        byte r1 = r4[r0];
        if (r1 != (-1)) goto L21;
        if (r1 != (-1)) goto L13;
        if (r4[r0 + 1] != (-38)) goto L13;
        return true;
    L13:
        r0 = r0 + ((((r4[r0 + 2] & UByte.MAX_VALUE) << 8) | (r4[r0 + 3] & UByte.MAX_VALUE)) + 2);
        goto L4
    L21:
        return false;
    L14:
        return false;
    }

    private int findSecondFfd8Position(byte[] r4) {
        int r3 = 2;
    L3:
        int r0 = r3 + 1;
        if (r0 > r4.length) goto L5;
        if (r4[r3] != (-1)) goto L11;
        if (r4[r0] != (-40)) goto L11;
        return r3;
    L11:
        r3 = r0;
        goto L3
    L5:
        return -1;
    }

    private static boolean isSamsungProblematicDevice() {
        if ("Samsung".equalsIgnoreCase(Build.BRAND) == true) goto L5;
        return false;
    L5:
        if (SAMSUNG_DEVICES.contains(Build.DEVICE.toUpperCase(Locale.US)) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean load() {
        return isSamsungProblematicDevice();
    }

    public byte[] jpegImageToJpegByteArray(ImageProxy r4) {
        int r0 = 0;
        ByteBuffer r42 = r4.getPlanes()[0].getBuffer();
        byte[] r1 = new byte[r42.capacity()];
        r42.rewind();
        r42.get(r1);
        if (canParseSosMarker(r1) == true) goto L9;
        r0 = findSecondFfd8Position(r1);
        if (r0 != (-1)) goto L9;
        return r1;
    L9:
        return Arrays.copyOfRange(r1, r0, r42.limit());
    }
}
