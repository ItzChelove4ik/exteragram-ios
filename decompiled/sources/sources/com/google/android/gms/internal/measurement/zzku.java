package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

/* loaded from: classes4.dex */
public final class zzku extends GmsClient {
    public zzku(Context r8, Looper r9, ClientSettings r10, GoogleApiClient.ConnectionCallbacks r11, GoogleApiClient.OnConnectionFailedListener r12) {
        super(r8, r9, 51, r10, r11, r12);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.phenotype.internal.IPhenotypeService");
        if ((r1 instanceof zzkt) == false) goto L10;
        return (zzkt) r1;
    L10:
        return new zzkt(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return zzjn.zzj;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 9410000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.phenotype.internal.IPhenotypeService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.phenotype.service.START";
    }
}
