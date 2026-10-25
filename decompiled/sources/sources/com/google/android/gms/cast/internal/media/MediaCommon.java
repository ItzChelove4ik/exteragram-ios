package com.google.android.gms.cast.internal.media;

/* loaded from: classes4.dex */
public class MediaCommon {
    public MediaCommon() {
    }

    public static Integer mediaRepeatModeFromString(String r1) {
        if (r1 != null) goto L5;
        return null;
    L5:
        switch(r1.hashCode()) {
            case -1118317585: goto L20;
            case -962896020: goto L16;
            case 1645938909: goto L12;
            case 1645952171: goto L8;
            default: goto L29;
        };
    L29:
        return null;
    L8:
        if (r1.equals("REPEAT_OFF") == true) goto L10;
        return null;
    L10:
        return 0;
    L12:
        if (r1.equals("REPEAT_ALL") == true) goto L14;
        return null;
    L14:
        return 1;
    L16:
        if (r1.equals("REPEAT_SINGLE") == true) goto L18;
        return null;
    L18:
        return 2;
    L20:
        if (r1.equals("REPEAT_ALL_AND_SHUFFLE") == true) goto L22;
        return null;
    L22:
        return 3;
    }

    public static String zza(Integer r1) {
        if (r1 == null) goto L22;
        int r12 = r1.intValue();
        if (r12 != 0) goto L7;
        return "REPEAT_OFF";
    L7:
        if (r12 != 1) goto L9;
        return "REPEAT_ALL";
    L9:
        if (r12 != 2) goto L11;
        return "REPEAT_SINGLE";
    L11:
        if (r12 == 3) goto L14;
        return null;
    L14:
        return "REPEAT_ALL_AND_SHUFFLE";
    L22:
        return null;
    }
}
