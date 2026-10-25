package com.google.android.gms.common.util;

import android.os.StrictMode;

/* loaded from: classes4.dex */
public abstract class zzd {
    public static StrictMode.VmPolicy zza() {
        StrictMode.VmPolicy r0 = StrictMode.getVmPolicy();
        if (PlatformVersion.isAtLeastS() == false) goto L5;
        StrictMode.setVmPolicy(zzc.zza(new StrictMode.VmPolicy.Builder(r0)).build());
    L5:
        return r0;
    }
}
