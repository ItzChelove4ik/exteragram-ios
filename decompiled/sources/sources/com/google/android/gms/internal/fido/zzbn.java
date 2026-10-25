package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
final class zzbn extends zzbl {
    private final Object zza;

    public zzbn(Object r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzbn) == true) goto L5;
        return false;
    L5:
        return this.zza.equals(((zzbn) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        return "Optional.of(" + this.zza.toString() + ")";
    }

    @Override // com.google.android.gms.internal.fido.zzbl
    public final Object zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.fido.zzbl
    public final boolean zzb() {
        return true;
    }
}
