package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
public final class zzaq implements zzbp {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static volatile zzaj zzb;
    private final String zzc;
    private final String zzd;
    private final Object zze;
    private volatile int zzg;
    private final zzak zzi;

    static {
        zzb = new zzaj(zzap.zza);
    }

    public zzaq(String r1, String r2, Object r3, zzak r4, boolean r5) {
        this.zzg = -1;
        this.zzc = "com.google.android.gms.fido";
        this.zzd = r2;
        this.zze = r3;
        this.zzi = r4;
    }

    @Override // com.google.android.gms.internal.fido.zzbp
    public final Object zza() {
        zzae.zza();
        monitor-enter(this);
        zzae.zzb();     // Catch: Throwable -> L6
        int r0 = zzan.$r8$clinit;     // Catch: Throwable -> L6
        throw null;     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }
}
