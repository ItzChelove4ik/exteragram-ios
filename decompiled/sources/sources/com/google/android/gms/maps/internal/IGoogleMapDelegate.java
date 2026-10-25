package com.google.android.gms.maps.internal;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

/* loaded from: classes5.dex */
public interface IGoogleMapDelegate extends IInterface {
    zzn addCircle(CircleOptions r1);

    zzaj addMarker(MarkerOptions r1);

    void animateCamera(IObjectWrapper r1);

    void animateCameraWithCallback(IObjectWrapper r1, zzd r2);

    void animateCameraWithDurationAndCallback(IObjectWrapper r1, int r2, zzd r3);

    CameraPosition getCameraPosition();

    float getMaxZoomLevel();

    float getMinZoomLevel();

    IProjectionDelegate getProjection();

    IUiSettingsDelegate getUiSettings();

    void moveCamera(IObjectWrapper r1);

    boolean setMapStyle(MapStyleOptions r1);

    void setMapType(int r1);

    void setMyLocationEnabled(boolean r1);

    void setOnCameraIdleListener(zzp r1);

    void setOnCameraMoveListener(zzt r1);

    void setOnCameraMoveStartedListener(zzv r1);

    void setOnMapLoadedCallback(zzap r1);

    void setOnMarkerClickListener(zzav r1);

    void setOnMyLocationChangeListener(zzbb r1);

    void setPadding(int r1, int r2, int r3, int r4);
}
