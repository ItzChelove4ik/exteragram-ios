package com.google.android.gms.internal.play_billing;

import com.google.android.gms.cast.MediaError;

/* loaded from: classes5.dex */
enum zzb extends Enum {
    public static final zzb zza = null;
    public static final zzb zzb = null;
    public static final zzb zzc = null;
    public static final zzb zzd = null;
    public static final zzb zze = null;
    public static final zzb zzf = null;
    public static final zzb zzg = null;
    public static final zzb zzh = null;
    public static final zzb zzi = null;
    public static final zzb zzj = null;
    public static final zzb zzk = null;
    public static final zzb zzl = null;
    public static final zzb zzm = null;
    public static final zzb zzn = null;
    public static final zzb zzo = null;
    private static final zzbw zzp = null;
    private static final /* synthetic */ zzb[] zzq = null;
    private final int zzr;

    static {
        zzb r0 = new zzb("RESPONSE_CODE_UNSPECIFIED", 0, -999);
        zza = r0;
        zzb r1 = new zzb("SERVICE_TIMEOUT", 1, -3);
        zzb = r1;
        zzb r2 = new zzb("FEATURE_NOT_SUPPORTED", 2, -2);
        zzc = r2;
        zzb r3 = new zzb("SERVICE_DISCONNECTED", 3, -1);
        zzd = r3;
        zzb r5 = new zzb("OK", 4, 0);
        zze = r5;
        zzb r52 = new zzb("USER_CANCELED", 5, 1);
        zzf = r52;
        zzb r4 = new zzb("SERVICE_UNAVAILABLE", 6, 2);
        zzg = r4;
        zzb r7 = new zzb("BILLING_UNAVAILABLE", 7, 3);
        zzh = r7;
        zzb r8 = new zzb("ITEM_UNAVAILABLE", 8, 4);
        zzi = r8;
        zzb r9 = new zzb("DEVELOPER_ERROR", 9, 5);
        zzj = r9;
        zzb r10 = new zzb(MediaError.ERROR_TYPE_ERROR, 10, 6);
        zzk = r10;
        zzb r11 = new zzb("ITEM_ALREADY_OWNED", 11, 7);
        zzl = r11;
        zzb r12 = new zzb("ITEM_NOT_OWNED", 12, 8);
        zzm = r12;
        zzb r13 = new zzb("EXPIRED_OFFER_TOKEN", 13, 11);
        zzn = r13;
        zzb r14 = new zzb("NETWORK_ERROR", 14, 12);
        zzo = r14;
        zzq = new zzb[]{r0, r1, r2, r3, r5, r52, r4, r7, r8, r9, r10, r11, r12, r13, r14};
        zzbv r02 = new zzbv();
        zzb[] r15 = values();
        int r22 = r15.length;
        int r152 = 0;
    L3:
        if (r152 >= r22) goto L5;
        zzb r32 = r15[r152];
        r02.zza(Integer.valueOf(r32.zzr), r32);
        r152 = r152 + 1;
        goto L3
    L5:
        zzp = r02.zzb();
    }

    zzb(String r1, int r2, int r3) {
        this.zzr = r3;
    }

    public static zzb[] values() {
        return (zzb[]) zzq.clone();
    }

    public static zzb zza(int r2) {
        zzbw r0 = zzp;
        Integer r22 = Integer.valueOf(r2);
        if (r0.containsKey(r22) == true) goto L7;
        return zza;
    L7:
        return (zzb) r0.get(r22);
    }
}
