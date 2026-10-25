package com.google.android.gms.internal.play_billing;

/* loaded from: classes.dex */
public enum zzil extends Enum implements zzfk {
    public static final zzil zza = null;
    public static final zzil zzb = null;
    public static final zzil zzc = null;
    public static final zzil zzd = null;
    private static final /* synthetic */ zzil[] zze = null;
    private final int zzf;

    static {
        zzil r0 = new zzil("BROADCAST_ACTION_UNSPECIFIED", 0, 0);
        zza = r0;
        zzil r1 = new zzil("PURCHASES_UPDATED_ACTION", 1, 1);
        zzb = r1;
        zzil r2 = new zzil("LOCAL_PURCHASES_UPDATED_ACTION", 2, 2);
        zzc = r2;
        zzil r3 = new zzil("ALTERNATIVE_BILLING_ACTION", 3, 3);
        zzd = r3;
        zze = new zzil[]{r0, r1, r2, r3};
    }

    zzil(String r1, int r2, int r3) {
        this.zzf = r3;
    }

    public static zzil[] values() {
        return (zzil[]) zze.clone();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.zzf);
    }

    public final int zza() {
        return this.zzf;
    }
}
