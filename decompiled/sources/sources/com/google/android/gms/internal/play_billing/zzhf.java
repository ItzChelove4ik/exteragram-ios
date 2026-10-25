package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
abstract class zzhf {
    public static String zza(zzei r5) {
        StringBuilder r0 = new StringBuilder(r5.zzd());
        int r1 = 0;
    L4:
        if (r1 >= r5.zzd()) goto L30;
        byte r2 = r5.zza(r1);
        if (r2 != 34) goto L8;
        r0.append("\\\"");
    L28:
        r1 = r1 + 1;
        goto L4
    L8:
        if (r2 != 39) goto L10;
        r0.append("\\'");
        goto L28
    L10:
        if (r2 == 92) goto L25;
        switch(r2) {
            case 7: goto L24;
            case 8: goto L23;
            case 9: goto L22;
            case 10: goto L21;
            case 11: goto L20;
            case 12: goto L19;
            case 13: goto L18;
            default: goto L13;
        };
    L18:
        r0.append("\\r");
        goto L28
    L19:
        r0.append("\\f");
        goto L28
    L20:
        r0.append("\\v");
        goto L28
    L21:
        r0.append("\\n");
        goto L28
    L22:
        r0.append("\\t");
        goto L28
    L23:
        r0.append("\\b");
        goto L28
    L24:
        r0.append("\\a");
        goto L28
    L13:
        if (r2 >= 32) goto L15;
    L17:
        r0.append('\\');
        r0.append((char) (((r2 >>> 6) & 3) + 48));
        r0.append((char) (((r2 >>> 3) & 7) + 48));
        r0.append((char) ((r2 & 7) + 48));
        goto L28
    L15:
        if (r2 > 126) goto L17;
        r0.append((char) r2);
        goto L28
    L25:
        r0.append("\\\\");
        goto L28
    L30:
        return r0.toString();
    }
}
