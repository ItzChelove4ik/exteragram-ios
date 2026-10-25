package com.google.android.gms.internal.fido;

/* loaded from: classes4.dex */
final class zzaz extends zzbl {
    static final zzaz zza = null;

    static {
        zza = new zzaz();
    }

    private zzaz() {
    }

    public final boolean equals(Object r1) {
        if (r1 != this) goto L5;
        return true;
    L5:
        return false;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.fido.zzbl
    public final Object zza() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.android.gms.internal.fido.zzbl
    public final boolean zzb() {
        return false;
    }
}
