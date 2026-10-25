package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes5.dex */
public final class BitmapDescriptor {
    private final IObjectWrapper zza;

    public BitmapDescriptor(IObjectWrapper r1) {
        this.zza = (IObjectWrapper) Preconditions.checkNotNull(r1);
    }

    public final IObjectWrapper zza() {
        return this.zza;
    }
}
