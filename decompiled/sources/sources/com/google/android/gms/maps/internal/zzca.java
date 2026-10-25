package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: classes5.dex */
public final class zzca extends com.google.android.gms.internal.maps.zza implements IUiSettingsDelegate {
    public zzca(IBinder r2) {
        super(r2, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setCompassEnabled(boolean r3) {
        Parcel r0 = zza();
        int r1 = com.google.android.gms.internal.maps.zzc.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        zzc(2, r0);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setMyLocationButtonEnabled(boolean r3) {
        Parcel r0 = zza();
        int r1 = com.google.android.gms.internal.maps.zzc.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        zzc(3, r0);
    }

    @Override // com.google.android.gms.maps.internal.IUiSettingsDelegate
    public final void setZoomControlsEnabled(boolean r3) {
        Parcel r0 = zza();
        int r1 = com.google.android.gms.internal.maps.zzc.$r8$clinit;
        r0.writeInt(r3 ? 1 : 0);
        zzc(1, r0);
    }
}
