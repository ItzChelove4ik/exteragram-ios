package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* loaded from: classes4.dex */
public abstract class zzdr extends UIController {
    private boolean zza;

    public zzdr() {
        this.zza = true;
    }

    public abstract void zza(long r1);

    public void zzb(boolean r1) {
        this.zza = r1;
    }

    public final boolean zzc() {
        return this.zza;
    }
}
