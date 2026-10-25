package com.google.android.gms.internal.cast;

import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzeh extends ConnectivityManager.NetworkCallback {
    final /* synthetic */ zzek zza;

    public zzeh(zzek r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network r1) {
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLinkPropertiesChanged(Network r1, LinkProperties r2) {
        this.zza.zzc(r1, r2);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onLost(Network r1) {
        this.zza.zzd(r1);
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onUnavailable() {
        this.zza.zze();
    }
}
