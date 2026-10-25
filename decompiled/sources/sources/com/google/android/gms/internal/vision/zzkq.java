package com.google.android.gms.internal.vision;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzkq<T> implements zzlc<T> {
    private final zzkk zza;
    private final zzlu<?, ?> zzb;
    private final boolean zzc;
    private final zziq<?> zzd;

    private zzkq(zzlu<?, ?> r1, zziq<?> r2, zzkk r3) {
        this.zzb = r1;
        this.zzc = r2.zza(r3);
        this.zzd = r2;
        this.zza = r3;
    }

    public static <T> zzkq<T> zza(zzlu<?, ?> r1, zziq<?> r2, zzkk r3) {
        return new zzkq(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zza(T r3) {
        int r0 = this.zzb.zzb(r3).hashCode();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        return (r0 * 53) + this.zzd.zza(r3).hashCode();
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final T zza() {
        return (T) this.zza.zzq().zze();
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zza(T r3, zzmr r4) {
        Iterator r0 = this.zzd.zza(r3).zzd();
        if (r0.hasNext() == true) goto L6;
        zzlu<?, ?> r2 = this.zzb;
        r2.zzb(r2.zzb(r3), r4);
        return;
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) r0.next()).getKey());
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zza(T r1, byte[] r2, int r3, int r4, zzhn r5) {
        zzjb r0 = (zzjb) r1;
        if (r0.zzb != zzlx.zza()) goto L5;
        r0.zzb = zzlx.zzb();
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final boolean zza(T r3, T r4) {
        if (this.zzb.zzb(r3).equals(this.zzb.zzb(r4)) == true) goto L7;
        return false;
    L7:
        if (this.zzc == true) goto L9;
        return true;
    L9:
        return this.zzd.zza(r3).equals(this.zzd.zza(r4));
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final int zzb(T r3) {
        zzlu<?, ?> r0 = this.zzb;
        int r02 = r0.zze(r0.zzb(r3));
        if (this.zzc == true) goto L5;
        return r02;
    L5:
        return r02 + this.zzd.zza(r3).zzg();
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzb(T r2, T r3) {
        zzle.zza(this.zzb, r2, r3);
        if (this.zzc == false) goto L6;
        zzle.zza(this.zzd, r2, r3);
        return;
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final void zzc(T r2) {
        this.zzb.zzd(r2);
        this.zzd.zzc(r2);
    }

    @Override // com.google.android.gms.internal.vision.zzlc
    public final boolean zzd(T r1) {
        return this.zzd.zza(r1).zzf();
    }
}
