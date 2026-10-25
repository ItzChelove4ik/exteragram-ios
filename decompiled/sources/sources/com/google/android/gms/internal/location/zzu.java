package com.google.android.gms.internal.location;

import android.location.Location;
import android.os.IInterface;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;

/* loaded from: classes4.dex */
public interface zzu extends IInterface {
    void zzD(LocationSettingsRequest r1, zzaa r2, String r3);

    void zzq(LastLocationRequest r1, zzed r2);

    @Deprecated
    void zzr(LastLocationRequest r1, zzy r2);

    @Deprecated
    Location zzs();

    @Deprecated
    void zzv(zzeh r1);

    void zzw(zzed r1, LocationRequest r2, IStatusCallback r3);

    void zzx(zzed r1, IStatusCallback r2);
}
