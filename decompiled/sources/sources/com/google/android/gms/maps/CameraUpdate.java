package com.google.android.gms.maps;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes5.dex */
public final class CameraUpdate {
    private final IObjectWrapper zza;

    public CameraUpdate(IObjectWrapper r1) {
        this.zza = (IObjectWrapper) Preconditions.checkNotNull(r1);
    }

    public final IObjectWrapper zza() {
        return this.zza;
    }
}
