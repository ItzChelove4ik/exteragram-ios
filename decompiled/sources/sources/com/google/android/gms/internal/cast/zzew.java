package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

@Deprecated
/* loaded from: classes4.dex */
public final class zzew extends GmsClient implements IBinder.DeathRecipient {
    private static final Logger zze = null;
    private final CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzf;
    private final CastDevice zzg;
    private final Bundle zzh;

    static {
        zze = new Logger("CastRemoteDisplayClientImpl");
    }

    public zzew(Context r8, Looper r9, ClientSettings r10, CastDevice r11, Bundle r12, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks r13, GoogleApiClient.ConnectionCallbacks r14, GoogleApiClient.OnConnectionFailedListener r15) {
        super(r8, r9, 83, r10, r14, r15);
        zze.d("instance created", new Object[0]);
        this.zzf = r13;
        this.zzg = r11;
        this.zzh = r12;
    }

    public static /* synthetic */ Logger zzr() {
        return zze;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        if ((r1 instanceof zzez) == false) goto L10;
        return (zzez) r1;
    L10:
        return new zzez(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        zze.d("disconnect", new Object[0]);
        ((zzez) getService()).zze(zzff.zza(getContext()));     // Catch: Throwable -> L5 Throwable -> L10
    L5:
        th = move-exception;
        super.disconnect();
        throw th;
    L8:
        super.disconnect();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.remote_display.service.START";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzp(zzey r11, zzfb r12, String r13) {
        zze.d("startRemoteDisplay", new Object[0]);
        zzev r5 = new zzev(this, r12);
        zzez r3 = (zzez) getService();
        String r6 = this.zzg.getDeviceId();
        ApiMetadata r9 = zzff.zza(getContext());
        r3.zzf(r11, r5, r6, r13, this.zzh, r9);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzq(zzey r4) {
        zze.d("stopRemoteDisplay", new Object[0]);
        ((zzez) getService()).zzi(r4, zzff.zza(getContext()));
    }

    public final /* synthetic */ CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzs() {
        return this.zzf;
    }
}
