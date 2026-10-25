package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.internal.p000authapi.zbaw;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class zbe extends GmsClient {
    private final GoogleSignInOptions zba;

    public zbe(Context r8, Looper r9, ClientSettings r10, GoogleSignInOptions r11, GoogleApiClient.ConnectionCallbacks r12, GoogleApiClient.OnConnectionFailedListener r13) {
        super(r8, r9, 91, r10, r12, r13);
        if (r11 == null) goto L5;
        GoogleSignInOptions.Builder r7 = new GoogleSignInOptions.Builder(r11);
    L6:
        r7.setLogSessionId(zbaw.zba());
        if (r10.getAllRequestedScopes().isEmpty() == true) goto L12;
        Iterator<Scope> r82 = r10.getAllRequestedScopes().iterator();
    L10:
        if (r82.hasNext() == false) goto L12;
        r7.requestScopes(r82.next(), new Scope[0]);
    L12:
        this.zba = r7.build();
        return;
    L5:
        r7 = new GoogleSignInOptions.Builder();
        goto L6
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.auth.api.signin.internal.ISignInService");
        if ((r1 instanceof zbs) == false) goto L10;
        return (zbs) r1;
    L10:
        return new zbs(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final Intent getSignInIntent() {
        return zbm.zba(getContext(), this.zba);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.auth.api.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean providesSignIn() {
        return true;
    }

    public final GoogleSignInOptions zba() {
        return this.zba;
    }
}
