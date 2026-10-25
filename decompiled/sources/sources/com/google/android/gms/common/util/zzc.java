package com.google.android.gms.common.util;

import android.os.StrictMode;

/* loaded from: classes4.dex */
abstract class zzc {
    public static StrictMode.VmPolicy.Builder zza(StrictMode.VmPolicy.Builder r0) {
        return r0.permitUnsafeIntentLaunch();
    }
}
