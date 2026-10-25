package com.google.android.gms.cast;

import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbl extends com.google.android.gms.cast.internal.zzai {
    final /* synthetic */ zzbm zza;

    public zzbl(zzbm r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzb(int r3) {
        this.zza.zzK().post(new zzbk(this, r3));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzc(int r3) {
        this.zza.zzK().post(new zzbe(this, r3));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzd(int r3) {
        this.zza.zzK().post(new zzbf(this, r3));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zze(ApplicationMetadata r7, String r8, String r9, boolean r10) {
        zzbm r6 = this.zza;
        r6.zzP(r7);
        r6.zzQ(r8);
        r6.zzG(new com.google.android.gms.cast.internal.zzr(new Status(0), r7, r8, r9, r10));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzf(int r1) {
        this.zza.zzH(r1);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzg(int r1) {
        this.zza.zzI(r1);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzh(int r1) {
        this.zza.zzI(r1);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzi(int r3) {
        zzbm r0 = this.zza;
        r0.zzI(r3);
        if (r0.zzS() == null) goto L6;
        r0.zzK().post(new zzbg(this, r3));
        return;
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzj(String r1, double r2, boolean r4) {
        zzbm.zzM().d("Deprecated callback: \"onStatusReceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzk(com.google.android.gms.cast.internal.zzac r3) {
        this.zza.zzK().post(new zzbh(this, r3));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzl(com.google.android.gms.cast.internal.zza r3) {
        this.zza.zzK().post(new zzbi(this, r3));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzm(String r4, String r5) {
        Logger r1 = zzbm.zzM();
        r1.d("Receive (type=text, ns=%s) %s", new Object[]{r4, r5});
        this.zza.zzK().post(new zzbj(this, r4, r5));
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzn(String r1, byte[] r2) {
        int r0 = zzbm.zzf;
        Object[] r02 = {r1, Integer.valueOf(r2.length)};
        zzbm.zzM().d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", r02);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzo(String r1, long r2, int r4) {
        this.zza.zzJ(r2, r4);
    }

    @Override // com.google.android.gms.cast.internal.zzaj
    public final void zzp(String r1, long r2) {
        this.zza.zzJ(r2, 0);
    }
}
