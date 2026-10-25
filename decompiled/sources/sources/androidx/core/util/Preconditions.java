package androidx.core.util;

import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Structure$$ExternalSyntheticBUOutline0;
import java.util.Locale;
import okio.Segment$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public abstract class Preconditions {
    public static void checkArgument(boolean r0) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline0.m();
    }

    public static void checkArgument(boolean r0, Object r1) {
        if (r0 == false) goto L5;
        return;
    L5:
        throw new IllegalArgumentException(String.valueOf(r1));
    }

    public static int checkArgumentInRange(int r1, int r2, int r3, String r4) {
        if (r1 < r2) goto L8;
        if (r1 > r3) goto L6;
        return r1;
    L6:
        f$$ExternalSyntheticBUOutline1.m(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", new Object[]{r4, Integer.valueOf(r2), Integer.valueOf(r3)}));
        return 0;
    L8:
        f$$ExternalSyntheticBUOutline1.m(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", new Object[]{r4, Integer.valueOf(r2), Integer.valueOf(r3)}));
        return 0;
    }

    public static int checkArgumentNonnegative(int r0) {
        if (r0 < 0) goto L4;
        return r0;
    L4:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    public static int checkArgumentNonnegative(int r0, String r1) {
        if (r0 < 0) goto L4;
        return r0;
    L4:
        f$$ExternalSyntheticBUOutline1.m(r1);
        return 0;
    }

    public static int checkFlagsArgument(int r3, int r4) {
        if ((r3 & r4) != r3) goto L5;
        return r3;
    L5:
        Structure$$ExternalSyntheticBUOutline0.m("Requested flags 0x", Integer.toHexString(r3), ", but only 0x", Integer.toHexString(r4), " are allowed");
        return 0;
    }

    public static <T> T checkNotNull(T r0) {
        r0.getClass();
        return r0;
    }

    public static <T> T checkNotNull(T r0, Object r1) {
        if (r0 == null) goto L5;
        return r0;
    L5:
        throw new NullPointerException(String.valueOf(r1));
    }

    public static void checkState(boolean r1) {
        checkState(r1, null);
    }

    public static void checkState(boolean r0, String r1) {
        if (r0 == false) goto L4;
        return;
    L4:
        Segment$$ExternalSyntheticBUOutline1.m(r1);
    }

    public static <T extends CharSequence> T checkStringNotEmpty(T r1, Object r2) {
        if (TextUtils.isEmpty(r1) == true) goto L6;
        return r1;
    L6:
        throw new IllegalArgumentException(String.valueOf(r2));
    }
}
