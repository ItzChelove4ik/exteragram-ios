package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzk;
import com.google.android.gms.maps.GoogleMapOptions;

/* loaded from: classes5.dex */
public interface zzf extends IInterface {
    int zzd();

    ICameraUpdateFactoryDelegate zze();

    IMapViewDelegate zzg(IObjectWrapper r1, GoogleMapOptions r2);

    zzk zzj();

    void zzl(IObjectWrapper r1, int r2);

    void zzm(IObjectWrapper r1, int r2);

    void zzn(IObjectWrapper r1);
}
