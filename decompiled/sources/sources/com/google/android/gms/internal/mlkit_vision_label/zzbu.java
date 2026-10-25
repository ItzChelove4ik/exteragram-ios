package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes.dex */
final class zzbu extends zzbe {
    static final zzbe zza = null;
    final transient Object[] zzb;
    private final transient int zzc;

    static {
        zza = new zzbu(new Object[0], 0);
    }

    public zzbu(Object[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzs.zza(r3, this.zzc, "index");
        Object r2 = this.zzb[r3];
        r2.getClass();
        return r2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzbe, com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final int zza(Object[] r3, int r4) {
        System.arraycopy(this.zzb, 0, r3, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzaz
    public final Object[] zze() {
        return this.zzb;
    }
}
