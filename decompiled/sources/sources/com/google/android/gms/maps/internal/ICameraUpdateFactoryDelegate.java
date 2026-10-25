package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes5.dex */
public interface ICameraUpdateFactoryDelegate extends IInterface {
    IObjectWrapper newLatLng(LatLng r1);

    IObjectWrapper newLatLngBounds(LatLngBounds r1, int r2);

    IObjectWrapper newLatLngZoom(LatLng r1, float r2);
}
