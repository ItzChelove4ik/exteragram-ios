package com.google.android.gms.maps;

import android.graphics.Point;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes5.dex */
public final class Projection {
    private final IProjectionDelegate zza;

    public Projection(IProjectionDelegate r1) {
        this.zza = r1;
    }

    public Point toScreenLocation(LatLng r1) {
        Preconditions.checkNotNull(r1);
        return (Point) ObjectWrapper.unwrap(this.zza.toScreenLocation(r1));
    L5:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }
}
