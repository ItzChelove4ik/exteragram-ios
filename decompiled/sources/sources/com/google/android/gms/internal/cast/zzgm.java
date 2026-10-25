package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public final class zzgm extends GmsClient {
    private final AtomicReference zze;

    public zzgm(Context r8, Looper r9, ClientSettings r10, GoogleApiClient.ConnectionCallbacks r11, GoogleApiClient.OnConnectionFailedListener r12) {
        super(r8, r9, 41, r10, r11, r12);
        this.zze = new AtomicReference();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.usagereporting.internal.IUsageReportingService");
        if ((r1 instanceof zzgh) == false) goto L10;
        return (zzgh) r1;
    L10:
        return new zzgh(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zze.getAndSet(null));     // Catch: RemoteException -> L4
    L6:
        super.disconnect();
        return;
    L4:
        e = move-exception;
        Log.e("UsageReportingClientImp", "disconnect(): Could not unregister listener from remote:", e);
        goto L6
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return zzfr.zzg;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12600000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.usagereporting.internal.IUsageReportingService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.usagereporting.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }
}
