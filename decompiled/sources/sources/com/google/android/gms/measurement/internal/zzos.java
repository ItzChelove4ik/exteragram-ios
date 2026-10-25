package com.google.android.gms.measurement.internal;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
abstract class zzos extends zzol {
    private boolean zza;

    public zzos(zzpg r1) {
        super(r1);
        this.zzg.zzaf();
    }

    public final boolean zzax() {
        if (this.zza == false) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzay() {
        if (zzax() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Not initialized");
    }

    public final void zzaz() {
        if (this.zza == true) goto L6;
        zzbc();
        this.zzg.zzag();
        this.zza = true;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Can't initialize twice");
    }

    public abstract boolean zzbc();
}
