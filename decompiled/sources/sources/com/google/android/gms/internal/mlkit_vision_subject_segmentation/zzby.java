package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
final class zzby implements zzcd {
    private final int zza;
    private final zzcc zzb;

    public zzby(int r1, zzcc r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzcd.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzcd) == true) goto L8;
        return false;
    L8:
        zzcd r52 = (zzcd) r5;
        if (this.zza == r52.zza()) goto L11;
    L13:
        return false;
    L11:
        if (this.zzb.equals(r52.zzb()) == false) goto L13;
        return true;
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        int r0 = this.zzb.hashCode() ^ 2041407134;
        return (this.zza ^ 14552422) + r0;
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzcd
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzcd
    public final zzcc zzb() {
        return this.zzb;
    }
}
