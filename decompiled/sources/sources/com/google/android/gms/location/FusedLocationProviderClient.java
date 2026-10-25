package com.google.android.gms.location;

import android.location.Location;
import android.os.Looper;
import com.google.android.gms.tasks.Task;

/* loaded from: classes5.dex */
public interface FusedLocationProviderClient {
    Task<Location> getLastLocation();

    Task<Void> removeLocationUpdates(LocationCallback r1);

    Task<Void> requestLocationUpdates(LocationRequest r1, LocationCallback r2, Looper r3);
}
