package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public enum zzabf extends Enum {
    public static final zzabf zzA = null;
    public static final zzabf zzB = null;
    public static final zzabf zzC = null;
    public static final zzabf zzD = null;
    public static final zzabf zzE = null;
    private static final Map zzF = null;
    private static final /* synthetic */ zzabf[] zzH = null;
    public static final zzabf zza = null;
    public static final zzabf zzb = null;
    public static final zzabf zzc = null;
    public static final zzabf zzd = null;
    public static final zzabf zze = null;
    public static final zzabf zzf = null;
    public static final zzabf zzg = null;
    public static final zzabf zzh = null;
    public static final zzabf zzi = null;
    public static final zzabf zzj = null;
    public static final zzabf zzk = null;
    public static final zzabf zzl = null;
    public static final zzabf zzm = null;
    public static final zzabf zzn = null;
    public static final zzabf zzo = null;
    public static final zzabf zzp = null;
    public static final zzabf zzq = null;
    public static final zzabf zzr = null;
    public static final zzabf zzs = null;
    public static final zzabf zzt = null;
    public static final zzabf zzu = null;
    public static final zzabf zzv = null;
    public static final zzabf zzw = null;
    public static final zzabf zzx = null;
    public static final zzabf zzy = null;
    public static final zzabf zzz = null;
    private final char zzG;

    static {
        zzabf r1 = new zzabf("TIME_HOUR_OF_DAY_PADDED", 0, 'H');
        zza = r1;
        zzabf r2 = new zzabf("TIME_HOUR_OF_DAY", 1, 'k');
        zzb = r2;
        zzabf r3 = new zzabf("TIME_HOUR_12H_PADDED", 2, 'I');
        zzc = r3;
        zzabf r4 = new zzabf("TIME_HOUR_12H", 3, 'l');
        zzd = r4;
        zzabf r5 = new zzabf("TIME_MINUTE_OF_HOUR_PADDED", 4, 'M');
        zze = r5;
        zzabf r6 = new zzabf("TIME_SECONDS_OF_MINUTE_PADDED", 5, 'S');
        zzf = r6;
        zzabf r7 = new zzabf("TIME_MILLIS_OF_SECOND_PADDED", 6, 'L');
        zzg = r7;
        zzabf r8 = new zzabf("TIME_NANOS_OF_SECOND_PADDED", 7, 'N');
        zzh = r8;
        zzabf r9 = new zzabf("TIME_AM_PM", 8, 'p');
        zzi = r9;
        zzabf r10 = new zzabf("TIME_TZ_NUMERIC", 9, 'z');
        zzj = r10;
        zzabf r11 = new zzabf("TIME_TZ_SHORT", 10, 'Z');
        zzk = r11;
        zzabf r12 = new zzabf("TIME_EPOCH_SECONDS", 11, 's');
        zzl = r12;
        zzabf r13 = new zzabf("TIME_EPOCH_MILLIS", 12, 'Q');
        zzm = r13;
        zzabf r14 = new zzabf("DATE_MONTH_FULL", 13, 'B');
        zzn = r14;
        zzabf r15 = new zzabf("DATE_MONTH_SHORT", 14, 'b');
        zzo = r15;
        zzabf r0 = new zzabf("DATE_MONTH_SHORT_ALT", 15, 'h');
        zzp = r0;
        zzabf r16 = new zzabf("DATE_DAY_FULL", 16, 'A');
        zzq = r16;
        zzabf r02 = new zzabf("DATE_DAY_SHORT", 17, 'a');
        zzr = r02;
        zzabf r17 = new zzabf("DATE_CENTURY_PADDED", 18, 'C');
        zzs = r17;
        zzabf r03 = new zzabf("DATE_YEAR_PADDED", 19, 'Y');
        zzt = r03;
        zzabf r18 = new zzabf("DATE_YEAR_OF_CENTURY_PADDED", 20, 'y');
        zzu = r18;
        zzabf r04 = new zzabf("DATE_DAY_OF_YEAR_PADDED", 21, 'j');
        zzv = r04;
        zzabf r19 = new zzabf("DATE_MONTH_PADDED", 22, 'm');
        zzw = r19;
        zzabf r05 = new zzabf("DATE_DAY_OF_MONTH_PADDED", 23, 'd');
        zzx = r05;
        zzabf r110 = new zzabf("DATE_DAY_OF_MONTH", 24, 'e');
        zzy = r110;
        zzabf r06 = new zzabf("DATETIME_HOURS_MINUTES", 25, 'R');
        zzz = r06;
        zzabf r111 = new zzabf("DATETIME_HOURS_MINUTES_SECONDS", 26, 'T');
        zzA = r111;
        zzabf r07 = new zzabf("DATETIME_HOURS_MINUTES_SECONDS_12H", 27, 'r');
        zzB = r07;
        zzabf r112 = new zzabf("DATETIME_MONTH_DAY_YEAR", 28, 'D');
        zzC = r112;
        zzabf r08 = new zzabf("DATETIME_YEAR_MONTH_DAY", 29, 'F');
        zzD = r08;
        zzabf r113 = new zzabf("DATETIME_FULL", 30, 'c');
        zzE = r113;
        int r09 = 0;
        zzH = new zzabf[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17, r03, r18, r04, r19, r05, r110, r06, r111, r07, r112, r08, r113};
        HashMap r114 = new HashMap();
        zzabf[] r22 = values();
        int r32 = r22.length;
    L3:
        if (r09 >= r32) goto L9;
        zzabf r42 = r22[r09];
        if (r114.put(Character.valueOf(r42.zzG), r42) != null) goto L7;
        r09 = r09 + 1;
        goto L3
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("duplicate format character: ".concat(String.valueOf(r42)));
        return;
    L9:
        zzF = Collections.unmodifiableMap(r114);
    }

    zzabf(String r1, int r2, char r3) {
        this.zzG = r3;
    }

    public static zzabf[] values() {
        return (zzabf[]) zzH.clone();
    }

    public static final zzabf zza(char r1) {
        return (zzabf) zzF.get(Character.valueOf(r1));
    }

    public final char zzb() {
        return this.zzG;
    }
}
