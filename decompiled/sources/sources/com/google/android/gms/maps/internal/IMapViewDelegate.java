package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;

/* loaded from: classes5.dex */
public interface IMapViewDelegate extends IInterface {
    void getMapAsync(zzat r1);

    IObjectWrapper getView();

    void onCreate(Bundle r1);

    void onDestroy();

    void onEnterAmbient(Bundle r1);

    void onExitAmbient();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle r1);

    void onStart();

    void onStop();
}
