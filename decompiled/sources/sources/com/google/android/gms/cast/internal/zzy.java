package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.internal.cast.zzff;

/* loaded from: classes4.dex */
public final class zzy extends GmsClient {
    private static final Logger zze = null;
    private final CastDevice zzf;
    private final long zzg;
    private final Bundle zzh;
    private final String zzi;

    static {
        zze = new Logger("CastClientImplCxless");
    }

    public zzy(Context r8, Looper r9, ClientSettings r10, CastDevice r11, long r12, Bundle r14, String r15, GoogleApiClient.ConnectionCallbacks r16, GoogleApiClient.OnConnectionFailedListener r17) {
        super(r8, r9, 10, r10, r16, r17);
        this.zzf = r11;
        this.zzg = r12;
        this.zzh = r14;
        this.zzi = r15;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if ((r1 instanceof zzah) == false) goto L10;
        return (zzah) r1;
    L10:
        return new zzah(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        ((zzah) getService()).zze(zzff.zza(getContext()));     // Catch: Throwable -> L4 Throwable -> L6 RemoteException -> L8
    L10:
        super.disconnect();
        return;
    L4:
        th = move-exception;
        super.disconnect();
        throw th;
    L6:
        e = move-exception;
        zze.d(e, "Error while disconnecting the controller interface", new Object[0]);     // Catch: Throwable -> L4
        goto L10
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return com.google.android.gms.cast.zzaq.zzo;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle r0 = new Bundle();
        zze.d("getRemoteService()", new Object[0]);
        this.zzf.putInBundle(r0);
        r0.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzg);
        r0.putString("connectionless_client_record_id", this.zzi);
        Bundle r4 = this.zzh;
        if (r4 == null) goto L5;
        r0.putAll(r4);
    L5:
        return r0;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 19390000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }
}
