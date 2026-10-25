package com.google.android.gms.measurement.internal;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
abstract class zzjf extends zzje {
    private boolean zza;

    public zzjf(zzic r1) {
        super(r1);
        this.zzu.zzF();
    }

    public abstract boolean zza();

    public void zzbb() {
    }

    public final boolean zzv() {
        if (this.zza == false) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzw() {
        if (zzv() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Not initialized");
    }

    public final void zzx() {
        if (this.zza == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Can't initialize twice");
        return;
    L5:
        if (zza() == true) goto L10;
        this.zzu.zzG();
        this.zza = true;
        return;
    }

    public final void zzy() {
        if (this.zza == true) goto L6;
        zzbb();
        this.zzu.zzG();
        this.zza = true;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Can't initialize twice");
    }
}
