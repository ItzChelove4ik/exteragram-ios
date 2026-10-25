package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;

/* loaded from: classes4.dex */
public final class zzaf extends Exception {
    public final ConnectionResult zza;

    public zzaf(ConnectionResult r3) {
        Preconditions.checkArgument(r3.hasResolution(), "ResolvableConnectionException can only be created with a connection result containing a resolution.");
        this.zza = r3;
    }
}
