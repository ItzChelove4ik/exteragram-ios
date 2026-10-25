package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
final class zzha extends zzhc {
    static final zzha zza = null;

    static {
        zza = new zzha();
    }

    private zzha() {
    }

    public final boolean equals(Object r1) {
        if (this != r1) goto L5;
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

    @Override // com.google.android.gms.internal.cast.zzhc
    public final Object zza(Object r1) {
        zzhd.zza(r1, "use Optional.orNull() instead of Optional.or(null)");
        return r1;
    }
}
