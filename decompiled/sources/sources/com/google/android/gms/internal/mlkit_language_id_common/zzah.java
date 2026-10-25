package com.google.android.gms.internal.mlkit_language_id_common;

/* loaded from: classes4.dex */
final class zzah implements zzam {
    private final int zza;
    private final zzal zzb;

    public zzah(int r1, zzal r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzam.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzam) == true) goto L8;
        return false;
    L8:
        zzam r52 = (zzam) r5;
        if (this.zza == r52.zza()) goto L11;
    L13:
        return false;
    L11:
        if (this.zzb.equals(r52.zzb()) == false) goto L13;
        return true;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzam
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzam
    public final zzal zzb() {
        return this.zzb;
    }
}
