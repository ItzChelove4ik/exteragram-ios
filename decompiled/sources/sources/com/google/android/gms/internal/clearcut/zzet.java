package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
abstract class zzet {
    public static String zzc(zzbb r5) {
        zzeu r0 = new zzeu(r5);
        StringBuilder r52 = new StringBuilder(r0.size());
        int r1 = 0;
    L4:
        if (r1 >= r0.size()) goto L31;
        int r2 = r0.zzj(r1);
        if (r2 != 34) goto L8;
        String r22 = "\\\"";
    L19:
        r52.append(r22);
    L29:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r2 != 39) goto L10;
        r22 = "\\'";
        goto L19
    L10:
        if (r2 == 92) goto L26;
        switch(r2) {
            case 7: goto L25;
            case 8: goto L24;
            case 9: goto L23;
            case 10: goto L22;
            case 11: goto L21;
            case 12: goto L20;
            case 13: goto L18;
            default: goto L13;
        };
    L18:
        r22 = "\\r";
        goto L19
    L20:
        r22 = "\\f";
        goto L19
    L21:
        r22 = "\\v";
        goto L19
    L22:
        r22 = "\\n";
        goto L19
    L23:
        r22 = "\\t";
        goto L19
    L24:
        r22 = "\\b";
        goto L19
    L25:
        r22 = "\\a";
        goto L19
    L13:
        if (r2 >= 32) goto L15;
    L17:
        r52.append('\\');
        r52.append((char) (((r2 >>> 6) & 3) + 48));
        r52.append((char) (((r2 >>> 3) & 7) + 48));
        r2 = (r2 & 7) + 48;
    L16:
        r52.append((char) r2);
        goto L29
    L15:
        if (r2 > 126) goto L17;
    L26:
        r22 = "\\\\";
        goto L19
    L31:
        return r52.toString();
    }
}
