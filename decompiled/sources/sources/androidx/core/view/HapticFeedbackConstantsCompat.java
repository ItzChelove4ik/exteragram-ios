package androidx.core.view;

import android.os.Build;

/* loaded from: classes3.dex */
public abstract class HapticFeedbackConstantsCompat {
    public static int getFeedbackConstantOrFallback(int r5) {
        if (r5 != (-1)) goto L5;
        return -1;
    L5:
        int r1 = Build.VERSION.SDK_INT;
        int r3 = 6;
        if (r1 >= 34) goto L13;
        switch(r5) {
            case 21: goto L11;
            case 22: goto L10;
            case 23: goto L11;
            case 24: goto L10;
            case 25: goto L9;
            case 26: goto L11;
            case 27: goto L10;
            default: goto L13;
        };
    L9:
        r5 = 0;
        goto L13
    L10:
        r5 = 4;
        goto L13
    L11:
        r5 = 6;
    L13:
        if (r1 < 30) goto L15;
    L25:
        r3 = r5;
    L27:
        if (r1 < 27) goto L29;
    L36:
        return r3;
    L29:
        if (r3 != 7) goto L31;
    L35:
        return -1;
    L31:
        if (r3 == 8) goto L35;
        if (r3 == 9) goto L35;
    L15:
        if (r5 != 12) goto L17;
    L24:
        r3 = 1;
        goto L27
    L17:
        if (r5 == 13) goto L27;
        if (r5 == 16) goto L24;
        if (r5 != 17) goto L25;
        r3 = 0;
        goto L27
    }
}
