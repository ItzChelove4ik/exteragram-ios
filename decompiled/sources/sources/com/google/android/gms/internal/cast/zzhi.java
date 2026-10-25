package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
final class zzhi implements zzhg {
    private static final zzhg zzb = null;
    private final zzhk zza;
    private volatile zzhg zzc;
    private Object zzd;

    static {
        zzb = zzhh.zza;
    }

    public zzhi(zzhg r2) {
        this.zza = new zzhk();
        this.zzc = r2;
    }

    public final String toString() {
        Object r0 = this.zzc;
        if (r0 != zzb) goto L5;
        String r2 = String.valueOf(this.zzd);
        StringBuilder r1 = new StringBuilder(r2.length() + 25);
        r1.append("<supplier that returned ");
        r1.append(r2);
        r1.append(">");
        r0 = r1.toString();
    L5:
        String r22 = String.valueOf(r0);
        StringBuilder r12 = new StringBuilder(r22.length() + 19);
        r12.append("Suppliers.memoize(");
        r12.append(r22);
        r12.append(")");
        return r12.toString();
    }

    @Override // com.google.android.gms.internal.cast.zzhg
    public final Object zza() {
        zzhg r0 = this.zzc;
        zzhg r1 = zzb;
        if (r0 == r1) goto L18;
        zzhk r02 = this.zza;
        monitor-enter(r02);
    L11:
        th = move-exception;
        throw th;
    L7:
        if (this.zzc == r1) goto L13;
        Object r2 = this.zzc.zza();     // Catch: Throwable -> L11
        this.zzd = r2;     // Catch: Throwable -> L11
        this.zzc = r1;     // Catch: Throwable -> L11
        monitor-exit(r02);     // Catch: Throwable -> L11
        return r2;
    L13:
        monitor-exit(r02);     // Catch: Throwable -> L11
    L18:
        return this.zzd;
    }
}
