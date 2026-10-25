package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
public final class zzp {
    Object zza;
    zzt zzb;
    private zzv zzc;
    private boolean zzd;

    public zzp() {
        this.zzc = zzv.zze();
    }

    public final void finalize() {
        zzt r0 = this.zzb;
        if (r0 == null) goto L8;
        if (r0.isDone() == true) goto L8;
        r0.zzc(new zzq("The completer object was garbage collected - this future would otherwise never complete. The tag was: ".concat(String.valueOf(this.zza))));
    L8:
        if (this.zzd == true) goto L13;
        zzv r4 = this.zzc;
        if (r4 == null) goto L14;
        r4.zzd(null);
        return;
    L14:
        return;
    }

    public final void zza() {
        this.zza = null;
        this.zzb = null;
        this.zzc.zzd(null);
    }

    public final boolean zzb(Object r4) {
        boolean r0 = true;
        this.zzd = true;
        zzt r1 = this.zzb;
        if (r1 != null) goto L5;
    L7:
        r0 = false;
    L8:
        if (r0 == false) goto L10;
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
    L10:
        return r0;
    L5:
        if (r1.zza(r4) == false) goto L7;
        goto L7
    }
}
