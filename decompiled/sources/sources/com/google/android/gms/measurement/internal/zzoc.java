package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes5.dex */
public final class zzoc extends zzg {
    protected final zzob zza;
    protected final zzoa zzb;
    protected final zzny zzc;
    private Handler zzd;
    private boolean zze;

    public zzoc(zzic r1) {
        super(r1);
        this.zze = true;
        this.zza = new zzob(this);
        this.zzb = new zzoa(this);
        this.zzc = new zzny(this);
    }

    private final void zzn() {
        zzg();
        if (this.zzd != null) goto L6;
        this.zzd = new com.google.android.gms.internal.measurement.zzcl(Looper.getMainLooper());
        return;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zze() {
        return false;
    }

    public final void zzh(boolean r1) {
        zzg();
        this.zze = r1;
    }

    public final boolean zzi() {
        zzg();
        return this.zze;
    }

    public final /* synthetic */ void zzj() {
        zzn();
    }

    public final /* synthetic */ void zzk(long r11) {
        zzg();
        zzn();
        zzic r0 = this.zzu;
        r0.zzaW().zzk().zzb("Activity resumed, time", Long.valueOf(r11));
        if (r0.zzc().zzp(null, zzfy.zzaS) == false) goto L10;
        if (r0.zzc().zzv() == false) goto L7;
    L8:
        this.zzb.zza(r11);
    L14:
        this.zzc.zza();
        zzob r4 = this.zza;
        zzoc r10 = r4.zza;
        r10.zzg();
        if (r10.zzu.zzB() == true) goto L17;
        return;
    L17:
        zzic r102 = r10.zzu;
        long r5 = r102.zzba().currentTimeMillis();
        if (r102.zzc().zzp(null, zzfy.zzbe) == false) goto L21;
        long r103 = r102.zzba().elapsedRealtime();
    L22:
        r4.zzb(r5, r103, false);
        return;
    L21:
        r103 = 0;
        goto L22
    L7:
        if (this.zze == false) goto L14;
    L10:
        if (r0.zzc().zzv() == false) goto L12;
    L13:
        this.zzb.zza(r11);
        goto L14
    L12:
        if (r0.zzd().zzn.zza() == false) goto L14;
        goto L13
    }

    public final /* synthetic */ void zzl(long r5) {
        zzg();
        zzn();
        zzic r0 = this.zzu;
        r0.zzaW().zzk().zzb("Activity paused, time", Long.valueOf(r5));
        this.zzc.zzb(r5);
        if (r0.zzc().zzv() == false) goto L6;
        this.zzb.zzb(r5);
        return;
    }

    public final /* synthetic */ Handler zzm() {
        return this.zzd;
    }
}
