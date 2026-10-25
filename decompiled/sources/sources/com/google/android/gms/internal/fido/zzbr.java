package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
final class zzbr implements zzbp {
    private static final zzbp zza = null;
    private volatile zzbp zzb;

    static {
        zza = zzbq.zza;
    }

    public zzbr(zzbp r1) {
        this.zzb = r1;
    }

    public final String toString() {
        Object r2 = this.zzb;
        if (r2 != zza) goto L6;
        r2 = "<supplier that returned null>";
    L6:
        return "Suppliers.memoize(" + String.valueOf(r2) + ")";
    }

    @Override // com.google.android.gms.internal.fido.zzbp
    public final Object zza() {
        throw null;
    }
}
