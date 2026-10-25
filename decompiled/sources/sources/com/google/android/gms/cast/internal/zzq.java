package com.google.android.gms.cast.internal;

/* loaded from: classes4.dex */
public class zzq {
    protected final Logger zza;
    private final String zzb;
    private zzas zzc;

    public zzq(String r1, String r2, String r3) {
        CastUtils.throwIfInvalidNamespace(r1);
        this.zzb = r1;
        this.zza = new Logger("MediaControlChannel", null);
    }

    public final String zzd() {
        return this.zzb;
    }

    public final void zze(zzas r1) {
        this.zzc = r1;
        if (r1 != null) goto L6;
        zzh();
        return;
    }

    public final void zzf(String r9, long r10, String r12) {
        Logger r0 = this.zza;
        r0.v("Sending text message: %s to: %s", new Object[]{r9, null});
        zzas r2 = this.zzc;
        if (r2 != null) goto L6;
        r0.e("Attempt to send text message without a sink", new Object[0]);
        return;
    L6:
        r2.zzb(this.zzb, r9, r10, null);
    }

    public final long zzg() {
        zzas r0 = this.zzc;
        if (r0 != null) goto L7;
        this.zza.e("Attempt to generate requestId without a sink", new Object[0]);
        return 0;
    L7:
        return r0.zzc();
    }

    public void zzh() {
        throw null;
    }
}
