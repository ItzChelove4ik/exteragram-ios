package com.google.android.gms.internal.p000authapi;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.zbc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

/* loaded from: classes4.dex */
public final class zbd extends GmsClient {
    private final com.google.android.gms.auth.api.zbd zba;

    public zbd(Context r8, Looper r9, ClientSettings r10, com.google.android.gms.auth.api.zbd r11, GoogleApiClient.ConnectionCallbacks r12, GoogleApiClient.OnConnectionFailedListener r13) {
        super(r8, r9, 68, r10, r12, r13);
        if (r11 != null) goto L5;
        r11 = com.google.android.gms.auth.api.zbd.zba;
    L5:
        zbc r7 = new zbc(r11);
        r7.zba(zbaw.zba());
        this.zba = new com.google.android.gms.auth.api.zbd(r7);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        if ((r1 instanceof zbe) == false) goto L10;
        return (zbe) r1;
    L10:
        return new zbe(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle getGetServiceRequestExtraArgs() {
        return this.zba.zba();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12800000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }
}
