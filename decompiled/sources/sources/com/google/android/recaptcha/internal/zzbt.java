package com.google.android.recaptcha.internal;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* loaded from: classes5.dex */
final class zzbt implements ComponentCallbacks2 {
    final /* synthetic */ zzby zza;

    public zzbt(zzby r1) {
        this.zza = r1;
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration r1) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        zzby r1 = this.zza;
        if (zzby.zzg(r1).zzc() == true) goto L6;
        r1.zzd();
        return;
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int r1) {
        zzby r0 = this.zza;
        if (zzby.zzg(r0).zzc() == true) goto L6;
        r0.zzd();
        return;
    }
}
