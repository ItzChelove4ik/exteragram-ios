package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;

/* loaded from: classes5.dex */
public final class UiSettings {
    private final IUiSettingsDelegate zza;

    public UiSettings(IUiSettingsDelegate r1) {
        this.zza = r1;
    }

    public void setCompassEnabled(boolean r1) {
        this.zza.setCompassEnabled(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setMyLocationButtonEnabled(boolean r1) {
        this.zza.setMyLocationButtonEnabled(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setZoomControlsEnabled(boolean r1) {
        this.zza.setZoomControlsEnabled(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }
}
