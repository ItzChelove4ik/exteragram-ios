package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
public enum zzml extends Enum<zzml> {
    public static final zzml zza = null;
    public static final zzml zzb = null;
    public static final zzml zzc = null;
    public static final zzml zzd = null;
    public static final zzml zze = null;
    public static final zzml zzf = null;
    public static final zzml zzg = null;
    public static final zzml zzh = null;
    public static final zzml zzi = null;
    public static final zzml zzj = null;
    public static final zzml zzk = null;
    public static final zzml zzl = null;
    public static final zzml zzm = null;
    public static final zzml zzn = null;
    public static final zzml zzo = null;
    public static final zzml zzp = null;
    public static final zzml zzq = null;
    public static final zzml zzr = null;
    private static final /* synthetic */ zzml[] zzu = null;
    private final zzmo zzs;
    private final int zzt;

    static {
        zzml r0 = new zzml("DOUBLE", 0, zzmo.zzd, 1);
        zza = r0;
        zzml r1 = new zzml("FLOAT", 1, zzmo.zzc, 5);
        zzb = r1;
        zzmo r5 = zzmo.zzb;
        zzml r2 = new zzml("INT64", 2, r5, 0);
        zzc = r2;
        zzml r7 = new zzml("UINT64", 3, r5, 0);
        zzd = r7;
        zzmo r11 = zzmo.zza;
        zzml r9 = new zzml("INT32", 4, r11, 0);
        zze = r9;
        zzml r12 = new zzml("FIXED64", 5, r5, 1);
        zzf = r12;
        zzml r14 = new zzml("FIXED32", 6, r11, 5);
        zzg = r14;
        zzml r15 = new zzml("BOOL", 7, zzmo.zze, 0);
        zzh = r15;
        zzmk r4 = new zzmk("STRING", 8, zzmo.zzf, 2);
        zzi = r4;
        zzmo r13 = zzmo.zzi;
        zzmn r6 = new zzmn("GROUP", 9, r13, 3);
        zzj = r6;
        zzmm r3 = new zzmm("MESSAGE", 10, r13, 2);
        zzk = r3;
        zzmp r8 = new zzmp("BYTES", 11, zzmo.zzg, 2);
        zzl = r8;
        zzml r02 = new zzml("UINT32", 12, r11, 0);
        zzm = r02;
        zzml r10 = new zzml("ENUM", 13, zzmo.zzh, 0);
        zzn = r10;
        zzml r03 = new zzml("SFIXED32", 14, r11, 5);
        zzo = r03;
        zzml r16 = new zzml("SFIXED64", 15, r5, 1);
        zzp = r16;
        zzml r04 = new zzml("SINT32", 16, r11, 0);
        zzq = r04;
        zzml r22 = new zzml("SINT64", 17, r5, 0);
        zzr = r22;
        zzu = new zzml[]{r0, r1, r2, r7, r9, r12, r14, r15, r4, r6, r3, r8, r02, r10, r03, r16, r04, r22};
    }

    zzml(String r1, int r2, zzmo r3, int r4) {
        this.zzs = r3;
        this.zzt = r4;
    }

    /* synthetic */ zzml(String r1, int r2, zzmo r3, int r4, zzmi r5) {
        this(r1, r2, r3, r4);
    }

    public static zzml[] values() {
        return (zzml[]) zzu.clone();
    }

    public final zzmo zza() {
        return this.zzs;
    }
}
