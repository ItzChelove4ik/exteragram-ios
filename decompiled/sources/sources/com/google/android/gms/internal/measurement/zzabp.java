package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public abstract class zzabp extends zzabn {
    private static final String zza = null;

    static {
        String r0 = System.getProperty("line.separator");     // Catch: SecurityException -> L7
        if (r0.matches("\\n|\\r(?:\\n)?") == false) goto L4;
    L5:
        zza = r0;
        return;
    L4:
        r0 = "\n";
        goto L5
    }

    public zzabp() {
    }

    public static int zze(String r3, int r4) {
    L3:
        if (r4 >= r3.length()) goto L18;
        int r0 = r4 + 1;
        if (r3.charAt(r4) != '%') goto L6;
        if (r0 >= r3.length()) goto L17;
        char r02 = r3.charAt(r0);
        if (r02 != '%') goto L12;
    L15:
        r4 = r4 + 2;
        goto L3
    L12:
        if (r02 == 'n') goto L15;
        return r4;
    L17:
        throw zzabo.zzc("trailing unquoted '%' character", r3, r4);
    L6:
        r4 = r0;
        goto L3
    L18:
        return -1;
    }

    public abstract int zzb(zzabm r1, int r2, String r3, int r4, int r5, int r6);

    @Override // com.google.android.gms.internal.measurement.zzabn
    public final void zzc(zzabm r15) {
        String r3 = r15.zzi();
        int r4 = zze(r3, 0);
        int r0 = 0;
        int r1 = -1;
    L3:
        if (r4 < 0) goto L51;
        int r2 = r4 + 1;
        int r5 = r2;
        int r6 = 0;
    L6:
        if (r5 >= r3.length()) goto L50;
        int r9 = r5 + 1;
        char r11 = r3.charAt(r5);
        char r12 = (char) (r11 - '0');
        if (r12 >= '\n') goto L15;
        r6 = (r6 * 10) + r12;
        if (r6 >= 1000000) goto L13;
        r5 = r9;
        goto L6
    L13:
        throw zzabo.zza("index too large", r3, r4, r9);
    L15:
        if (r11 != '$') goto L30;
        if ((r5 - r2) == 0) goto L28;
        if (r3.charAt(r2) == '0') goto L26;
        int r62 = r6 - 1;
        if (r9 == r3.length()) goto L24;
        r3.charAt(r9);
        int r92 = r0;
        int r02 = r5 + 2;
        int r52 = r9;
        int r22 = r62;
    L40:
        int r63 = r02 - 1;
    L42:
        if (r63 >= r3.length()) goto L48;
        if (((char) ((r3.charAt(r63) & 65503) - 65)) < 26) goto L45;
        r63 = r63 + 1;
        goto L42
    L45:
        zzabp r03 = this;
        r4 = zze(r3, r03.zzb(r15, r22, r3, r4, r52, r63));
        zzabp this = r03;
        r1 = r22;
        r0 = r92;
        goto L3
    L48:
        throw zzabo.zzc("unterminated parameter", r3, r4);
    L24:
        throw zzabo.zzc("unterminated parameter", r3, r4);
    L26:
        throw zzabo.zza("index has leading zero", r3, r4, r9);
    L28:
        throw zzabo.zza("missing index", r3, r4, r9);
    L30:
        if (r11 != '<') goto L39;
        if (r1 == (-1)) goto L38;
        if (r9 == r3.length()) goto L36;
        r3.charAt(r9);
        r92 = r0;
        r02 = r5 + 2;
        r52 = r9;
        r22 = r1;
        goto L40
    L36:
        throw zzabo.zzc("unterminated parameter", r3, r4);
    L38:
        throw zzabo.zza("invalid relative parameter", r3, r4, r9);
    L39:
        int r13 = r0 + 1;
        r52 = r2;
        r22 = r0;
        r02 = r9;
        r92 = r13;
        goto L40
    L50:
        throw zzabo.zzc("unterminated parameter", r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzabn
    public final void zzd(StringBuilder r4, String r5, int r6, int r7) {
        int r3 = r6;
    L3:
        if (r6 >= r7) goto L17;
        int r0 = r6 + 1;
        if (r5.charAt(r6) != '%') goto L16;
        if (r0 == r7) goto L17;
        char r1 = r5.charAt(r0);
        if (r1 == '%') goto L11;
        if (r1 != 'n') goto L16;
        r4.append(r5, r3, r6);
        r4.append(zza);
    L15:
        r3 = r6 + 2;
        r6 = r3;
        goto L3
    L11:
        r4.append(r5, r3, r0);
    L16:
        r6 = r0;
    L17:
        if (r3 >= r7) goto L29;
        r4.append(r5, r3, r7);
        return;
    }
}
