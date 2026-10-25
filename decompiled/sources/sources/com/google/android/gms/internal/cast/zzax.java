package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zzax extends com.google.android.gms.cast.framework.zzac {
    static final int zzb = 0;
    private static final Logger zzc = null;
    public final Set zza;
    private int zzd;

    static {
        zzc = new Logger("AppVisibilityProxy");
        zzb = 1;
    }

    public zzax() {
        this.zza = Collections.synchronizedSet(new HashSet());
        this.zzd = zzb;
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this);
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zzc() {
        zzc.i("onAppEnteredForeground", new Object[0]);
        this.zzd = 1;
        Iterator r3 = this.zza.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        ((zzaw) r3.next()).zza();
        goto L4
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zzd() {
        zzc.i("onAppEnteredBackground", new Object[0]);
        this.zzd = 2;
        Iterator r3 = this.zza.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        ((zzaw) r3.next()).zzb();
        goto L4
    }

    public final boolean zze() {
        if (this.zzd != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public final void zzf(zzaw r1) {
        this.zza.add(r1);
    }
}
