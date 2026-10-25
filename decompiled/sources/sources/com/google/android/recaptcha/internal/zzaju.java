package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public enum zzaju extends Enum implements zzafz {
    public static final zzaju zza = null;
    public static final zzaju zzb = null;
    public static final zzaju zzc = null;
    public static final zzaju zzd = null;
    public static final zzaju zze = null;
    public static final zzaju zzf = null;
    public static final zzaju zzg = null;
    public static final zzaju zzh = null;
    public static final zzaju zzi = null;
    public static final zzaju zzj = null;
    public static final zzaju zzk = null;
    public static final zzaju zzl = null;
    public static final zzaju zzm = null;
    public static final zzaju zzn = null;
    public static final zzaju zzo = null;
    public static final zzaju zzp = null;
    public static final zzaju zzq = null;
    public static final zzaju zzr = null;
    public static final zzaju zzs = null;
    private static final /* synthetic */ zzaju[] zzu = null;
    private final int zzt;

    static {
        zzaju r1 = new zzaju("MEC_COLUMN_UNSPECIFIED", 0, 0);
        zza = r1;
        zzaju r2 = new zzaju("MEC_ID", 1, 60624);
        zzb = r2;
        zzaju r3 = new zzaju("MEC_ACTIVITY_NAME", 2, 27932);
        zzc = r3;
        zzaju r4 = new zzaju("MEC_ACTION", 3, 16297);
        zzd = r4;
        zzaju r5 = new zzaju("MEC_EVENT_TIME", 4, 87675);
        zze = r5;
        zzaju r6 = new zzaju("MEC_DOWN_TIME", 5, 24368);
        zzf = r6;
        zzaju r7 = new zzaju("MEC_POINTER_COUNT", 6, 39954);
        zzg = r7;
        zzaju r8 = new zzaju("MEC_META_STATE", 7, 81898);
        zzh = r8;
        zzaju r9 = new zzaju("MEC_BUTTON_STATE", 8, 41153);
        zzi = r9;
        zzaju r10 = new zzaju("MEC_X", 9, 81015);
        zzj = r10;
        zzaju r11 = new zzaju("MEC_Y", 10, 79969);
        zzk = r11;
        zzaju r12 = new zzaju("MEC_DEVICE_ID", 11, 14294);
        zzl = r12;
        zzaju r13 = new zzaju("MEC_TOOL_TYPE", 12, 97243);
        zzm = r13;
        zzaju r14 = new zzaju("MEC_EDGE_FLAGS", 13, 91068);
        zzn = r14;
        zzaju r15 = new zzaju("MEC_SOURCE", 14, 99646);
        zzo = r15;
        zzaju r0 = new zzaju("MEC_PRESSURE", 15, 74067);
        zzp = r0;
        zzaju r16 = new zzaju("MEC_SIZE", 16, 90142);
        zzq = r16;
        zzaju r02 = new zzaju("MEC_FLAGS", 17, 75726);
        zzr = r02;
        zzaju r17 = new zzaju("UNRECOGNIZED", 18, -1);
        zzs = r17;
        zzu = new zzaju[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r0, r16, r02, r17};
    }

    zzaju(String r1, int r2, int r3) {
        this.zzt = r3;
    }

    public static zzaju[] values() {
        return (zzaju[]) zzu.clone();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzt);
    }

    public final int zza() {
        if (this != zzs) goto L7;
        return zzagf.zza();
    L7:
        return this.zzt;
    }
}
