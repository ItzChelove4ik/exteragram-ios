package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
final class zzhe extends zzhc {
    private final Object zza;

    public zzhe(Object r1) {
        this.zza = r1;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzhe) == true) goto L5;
        return false;
    L5:
        return this.zza.equals(((zzhe) r2).zza);
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String r2 = this.zza.toString();
        StringBuilder r1 = new StringBuilder(r2.length() + 13);
        r1.append("Optional.of(");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    public final Object zza(Object r2) {
        zzhd.zza(r2, "use Optional.orNull() instead of Optional.or(null)");
        return this.zza;
    }
}
