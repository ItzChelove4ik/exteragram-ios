package com.google.android.gms.measurement.internal;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
abstract class zzg extends zzf {
    private boolean zza;

    public zzg(zzic r1) {
        super(r1);
        this.zzu.zzF();
    }

    public final boolean zza() {
        if (this.zza == false) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzb() {
        if (zza() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Not initialized");
    }

    public final void zzc() {
        if (this.zza == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Can't initialize twice");
        return;
    L5:
        if (zze() == true) goto L10;
        this.zzu.zzG();
        this.zza = true;
        return;
    }

    public final void zzd() {
        if (this.zza == true) goto L6;
        zzf();
        this.zzu.zzG();
        this.zza = true;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Can't initialize twice");
    }

    public abstract boolean zze();

    public void zzf() {
    }
}
