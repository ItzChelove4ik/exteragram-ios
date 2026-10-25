package com.google.android.gms.internal.vision;

import kotlin.text.Typography;

/* loaded from: classes5.dex */
public enum zzha extends Enum<zzha> implements zzje {
    private static final zzha zza = null;
    private static final zzha zzb = null;
    private static final zzha zzc = null;
    private static final zzha zzd = null;
    private static final zzha zze = null;
    private static final zzha zzf = null;
    private static final zzha zzg = null;
    private static final zzha zzh = null;
    private static final zzha zzi = null;
    private static final zzha zzj = null;
    private static final zzha zzk = null;
    private static final zzha zzl = null;
    private static final zzha zzm = null;
    private static final zzha zzn = null;
    private static final zzjh<zzha> zzo = null;
    private static final /* synthetic */ zzha[] zzq = null;
    private final int zzp;

    static {
        zzha r0 = new zzha("UNKNOWN_FORMAT", 0, 0);
        zza = r0;
        zzha r1 = new zzha("CONTACT_INFO", 1, 1);
        zzb = r1;
        zzha r2 = new zzha("EMAIL", 2, 2);
        zzc = r2;
        zzha r3 = new zzha("ISBN", 3, 3);
        zzd = r3;
        zzha r4 = new zzha("PHONE", 4, 4);
        zze = r4;
        zzha r5 = new zzha("PRODUCT", 5, 5);
        zzf = r5;
        zzha r6 = new zzha("SMS", 6, 6);
        zzg = r6;
        zzha r7 = new zzha("TEXT", 7, 7);
        zzh = r7;
        zzha r8 = new zzha("URL", 8, 8);
        zzi = r8;
        zzha r9 = new zzha("WIFI", 9, 9);
        zzj = r9;
        zzha r10 = new zzha("GEO", 10, 10);
        zzk = r10;
        zzha r11 = new zzha("CALENDAR_EVENT", 11, 11);
        zzl = r11;
        zzha r12 = new zzha("DRIVER_LICENSE", 12, 12);
        zzm = r12;
        zzha r13 = new zzha("BOARDING_PASS", 13, 13);
        zzn = r13;
        zzq = new zzha[]{r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13};
        zzo = new zzhd();
    }

    zzha(String r1, int r2, int r3) {
        this.zzp = r3;
    }

    public static zzha[] values() {
        return (zzha[]) zzq.clone();
    }

    public static zzha zza(int r0) {
        switch(r0) {
            case 0: goto L32;
            case 1: goto L30;
            case 2: goto L28;
            case 3: goto L26;
            case 4: goto L24;
            case 5: goto L22;
            case 6: goto L20;
            case 7: goto L18;
            case 8: goto L16;
            case 9: goto L14;
            case 10: goto L12;
            case 11: goto L10;
            case 12: goto L8;
            case 13: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return zzn;
    L8:
        return zzm;
    L10:
        return zzl;
    L12:
        return zzk;
    L14:
        return zzj;
    L16:
        return zzi;
    L18:
        return zzh;
    L20:
        return zzg;
    L22:
        return zzf;
    L24:
        return zze;
    L26:
        return zzd;
    L28:
        return zzc;
    L30:
        return zzb;
    L32:
        return zza;
    }

    public static zzjg zzb() {
        return zzhc.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + zzha.class.getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzp + " name=" + name() + Typography.greater;
    }

    @Override // com.google.android.gms.internal.vision.zzje
    public final int zza() {
        return this.zzp;
    }
}
