package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.WearableStatusCodes;

/* loaded from: classes5.dex */
public abstract class zzhf {
    public static Status zza(int r2) {
        return new Status(r2, WearableStatusCodes.getStatusCodeString(r2));
    }
}
