package com.google.android.gms.dynamic;

import android.os.Bundle;

/* loaded from: classes4.dex */
public interface LifecycleDelegate {
    void onCreate(Bundle r1);

    void onDestroy();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle r1);

    void onStart();

    void onStop();
}
