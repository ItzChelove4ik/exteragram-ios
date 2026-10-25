package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
abstract class zzab {
    final String zzb;
    final int zzc;
    Boolean zzd;
    Boolean zze;
    Long zzf;
    Long zzg;

    public zzab(String r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    private static Boolean zzd(String r2, int r3, boolean r4, String r5, List r6, String r7, zzgu r8) {
        if (r3 != 7) goto L8;
        if (r6 != null) goto L6;
    L7:
        return null;
    L6:
        if (r6.isEmpty() == true) goto L7;
    L10:
        if (r4 == true) goto L15;
        if (r3 == 2) goto L15;
        r2 = r2.toUpperCase(Locale.ENGLISH);
    L15:
        switch((r3 - 1)) {
            case 1: goto L29;
            case 2: goto L28;
            case 3: goto L26;
            case 4: goto L24;
            case 5: goto L22;
            case 6: goto L17;
            default: goto L16;
        };
    L16:
        return null;
    L17:
        if (r6 != null) goto L20;
        return null;
    L20:
        return Boolean.valueOf(r6.contains(r2));
    L29:
        if (r7 != null) goto L32;
        return null;
    L32:
        if (true == r4) goto L34;
        int r32 = 66;
    L40:
        return Boolean.valueOf(Pattern.compile(r7, r32).matcher(r2).matches());
    L37:
        if (r8 == null) goto L39;
        r8.zze().zzb("Invalid regular expression in REGEXP audience filter. expression", r7);
    L39:
        return null;
    L34:
        r32 = 0;
        goto L40
    L22:
        return Boolean.valueOf(r2.equals(r5));
    L24:
        return Boolean.valueOf(r2.contains(r5));
    L26:
        return Boolean.valueOf(r2.endsWith(r5));
    L28:
        return Boolean.valueOf(r2.startsWith(r5));
    L8:
        if (r5 != null) goto L10;
        return null;
    }

    public static Boolean zze(Boolean r0, boolean r1) {
        if (r0 != null) goto L6;
        return null;
    L6:
        if (r0.booleanValue() == r1) goto L8;
        boolean r02 = true;
    L10:
        return Boolean.valueOf(r02);
    L8:
        r02 = false;
        goto L10
    }

    public static Boolean zzf(String r10, com.google.android.gms.internal.measurement.zzfr r11, zzgu r12) {
        Preconditions.checkNotNull(r11);
        if (r10 != null) goto L6;
    L42:
        return null;
    L6:
        if (r11.zza() == false) goto L42;
        if (r11.zzk() == 1) goto L42;
        if (r11.zzk() != 7) goto L15;
        if (r11.zzh() == 0) goto L42;
    L17:
        int r4 = r11.zzk();
        boolean r5 = r11.zze();
        if (r5 == true) goto L24;
        if (r4 == 2) goto L24;
        if (r4 == 7) goto L24;
        String r2 = r11.zzc().toUpperCase(Locale.ENGLISH);
    L23:
        String r6 = r2;
        if (r11.zzh() != 0) goto L28;
        List r7 = null;
    L36:
        if (r4 != 2) goto L39;
        String r8 = r6;
    L41:
        return zzd(r10, r4, r5, r6, r7, r8, r12);
    L39:
        r8 = null;
        goto L41
    L28:
        List r112 = r11.zzf();
        if (r5 == true) goto L35;
        ArrayList r22 = new ArrayList(r112.size());
        Iterator r113 = r112.iterator();
    L32:
        if (r113.hasNext() == false) goto L34;
        r22.add(((String) r113.next()).toUpperCase(Locale.ENGLISH));
        goto L32
    L34:
        r112 = Collections.unmodifiableList(r22);
    L35:
        r7 = r112;
    L24:
        r2 = r11.zzc();
        goto L23
    L15:
        if (r11.zzb() == true) goto L17;
        goto L17
    }

    public static Boolean zzg(long r1, com.google.android.gms.internal.measurement.zzfl r3) {
        return zzj(new BigDecimal(r1), r3, 0.0d);
    L4:
        return null;
    }

    public static Boolean zzh(double r1, com.google.android.gms.internal.measurement.zzfl r3) {
        return zzj(new BigDecimal(r1), r3, Math.ulp(r1));
    L4:
        return null;
    }

    public static Boolean zzi(String r4, com.google.android.gms.internal.measurement.zzfl r5) {
        if (zzpk.zzm(r4) == true) goto L8;
        return null;
    L8:
        return zzj(new BigDecimal(r4), r5, 0.0d);
    L7:
        return null;
    }

    public static Boolean zzj(BigDecimal r8, com.google.android.gms.internal.measurement.zzfl r9, double r10) {
        Preconditions.checkNotNull(r9);
        if (r9.zza() == false) goto L91;
        boolean r2 = true;
        if (r9.zzm() == 1) goto L91;
        if (r9.zzm() != 5) goto L15;
        if (r9.zzf() == true) goto L12;
    L13:
        return null;
    L12:
        if (r9.zzi() == false) goto L13;
    L17:
        int r0 = r9.zzm();
        if (r9.zzm() != 5) goto L28;
        if (zzpk.zzm(r9.zzh()) == true) goto L22;
    L26:
        return null;
    L22:
        if (zzpk.zzm(r9.zzj()) == false) goto L26;
        BigDecimal r4 = new BigDecimal(r9.zzh());     // Catch: NumberFormatException -> L92
        BigDecimal r5 = new BigDecimal(r9.zzj());     // Catch: NumberFormatException -> L92
        BigDecimal r92 = r4;
        BigDecimal r42 = null;
    L32:
        if (r0 != 5) goto L36;
        if (r92 != null) goto L38;
        return null;
    L38:
        int r02 = r0 - 1;
        if (r02 != 1) goto L41;
        if (r42 != null) goto L86;
        return null;
    L86:
        if (r8.compareTo(r42) < 0) goto L90;
        r2 = false;
    L90:
        return Boolean.valueOf(r2);
    L41:
        if (r02 != 2) goto L43;
        if (r42 != null) goto L78;
        return null;
    L78:
        if (r8.compareTo(r42) > 0) goto L82;
        r2 = false;
    L82:
        return Boolean.valueOf(r2);
    L43:
        if (r02 != 3) goto L45;
        if (r42 != null) goto L60;
        return null;
    L60:
        if (r10 == 0.0d) goto L70;
        if (r8.compareTo(r42.subtract(new BigDecimal(r10).multiply(new BigDecimal(2)))) > 0) goto L64;
    L66:
        r2 = false;
    L68:
        return Boolean.valueOf(r2);
    L64:
        if (r8.compareTo(r42.add(new BigDecimal(r10).multiply(new BigDecimal(2)))) >= 0) goto L66;
    L70:
        if (r8.compareTo(r42) == 0) goto L74;
        r2 = false;
    L74:
        return Boolean.valueOf(r2);
    L45:
        if (r02 == 4) goto L47;
        return null;
    L47:
        if (r92 != null) goto L49;
        return null;
    L49:
        if (r8.compareTo(r92) >= 0) goto L51;
    L53:
        r2 = false;
    L55:
        return Boolean.valueOf(r2);
    L51:
        if (r8.compareTo(r5) > 0) goto L53;
    L36:
        if (r42 != null) goto L38;
        return null;
    L28:
        if (zzpk.zzm(r9.zze()) == true) goto L96;
        return null;
    L96:
        r42 = new BigDecimal(r9.zze());     // Catch: NumberFormatException -> L93
        r92 = null;
        r5 = null;
        goto L32
    L15:
        if (r9.zzd() == true) goto L17;
        return null;
    L91:
        return null;
    }

    public abstract int zza();

    public abstract boolean zzb();

    public abstract boolean zzc();
}
