package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public enum zzagm extends Enum {
    public static final zzagm zza = null;
    public static final zzagm zzb = null;
    public static final zzagm zzc = null;
    public static final zzagm zzd = null;
    public static final zzagm zze = null;
    public static final zzagm zzf = null;
    public static final zzagm zzg = null;
    public static final zzagm zzh = null;
    public static final zzagm zzi = null;
    public static final zzagm zzj = null;
    public static final zzagm zzk = null;
    public static final zzagm zzl = null;
    public static final zzagm zzm = null;
    public static final zzagm zzn = null;
    public static final zzagm zzo = null;
    public static final zzagm zzp = null;
    public static final zzagm zzq = null;
    public static final zzagm zzr = null;
    private static final /* synthetic */ zzagm[] zzu = null;
    private final zzagn zzs;
    private final int zzt;

    static {
        zzagm r1 = new zzagm("DOUBLE", 0, zzagn.zzd, 1);
        zza = r1;
        zzagm r2 = new zzagm("FLOAT", 1, zzagn.zzc, 5);
        zzb = r2;
        zzagn r5 = zzagn.zzb;
        zzagm r0 = new zzagm("INT64", 2, r5, 0);
        zzc = r0;
        zzagm r7 = new zzagm("UINT64", 3, r5, 0);
        zzd = r7;
        zzagn r11 = zzagn.zza;
        zzagm r9 = new zzagm("INT32", 4, r11, 0);
        zze = r9;
        zzagm r12 = new zzagm("FIXED64", 5, r5, 1);
        zzf = r12;
        zzagm r72 = new zzagm("FIXED32", 6, r11, 5);
        zzg = r72;
        zzagm r14 = new zzagm("BOOL", 7, zzagn.zze, 0);
        zzh = r14;
        zzagm r92 = new zzagm("STRING", 8, zzagn.zzf, 2);
        zzi = r92;
        zzagn r6 = zzagn.zzi;
        zzagm r3 = new zzagm("GROUP", 9, r6, 3);
        zzj = r3;
        zzagm r8 = new zzagm("MESSAGE", 10, r6, 2);
        zzk = r8;
        zzagm r122 = new zzagm("BYTES", 11, zzagn.zzg, 2);
        zzl = r122;
        zzagm r13 = new zzagm("UINT32", 12, r11, 0);
        zzm = r13;
        zzagm r142 = new zzagm("ENUM", 13, zzagn.zzh, 0);
        zzn = r142;
        zzagm r15 = new zzagm("SFIXED32", 14, r11, 5);
        zzo = r15;
        zzagm r02 = new zzagm("SFIXED64", 15, r5, 1);
        zzp = r02;
        zzagm r16 = new zzagm("SINT32", 16, r11, 0);
        zzq = r16;
        zzagm r22 = new zzagm("SINT64", 17, r5, 0);
        zzr = r22;
        zzu = new zzagm[]{r1, r2, r0, r7, r9, r12, r72, r14, r92, r3, r8, r122, r13, r142, r15, r02, r16, r22};
    }

    zzagm(String r1, int r2, zzagn r3, int r4) {
        this.zzs = r3;
        this.zzt = r4;
    }

    public static zzagm[] values() {
        return (zzagm[]) zzu.clone();
    }

    public final zzagn zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }
}
