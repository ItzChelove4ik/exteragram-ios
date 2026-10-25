package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zaw;

/* loaded from: classes5.dex */
public class SignInClientImpl extends GmsClient<zaf> implements com.google.android.gms.signin.zae {
    public static final /* synthetic */ int $r8$clinit = 0;
    private final boolean zab;
    private final ClientSettings zac;
    private final Bundle zad;
    private final Integer zae;

    public SignInClientImpl(Context r8, Looper r9, boolean r10, ClientSettings r11, Bundle r12, GoogleApiClient.ConnectionCallbacks r13, GoogleApiClient.OnConnectionFailedListener r14) {
        super(r8, r9, 44, r11, r13, r14);
        this.zab = true;
        this.zac = r11;
        this.zad = r12;
        this.zae = r11.zad();
    }

    public static Bundle createBundleFromClientSettings(ClientSettings r4) {
        r4.zac();
        Integer r0 = r4.zad();
        Bundle r1 = new Bundle();
        r1.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", r4.getAccount());
        if (r0 == null) goto L5;
        r1.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", r0.intValue());
    L5:
        r1.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", false);
        r1.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", false);
        r1.putString("com.google.android.gms.signin.internal.serverClientId", null);
        r1.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
        r1.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", false);
        r1.putString("com.google.android.gms.signin.internal.hostedDomain", null);
        r1.putString("com.google.android.gms.signin.internal.logSessionId", null);
        r1.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", false);
        return r1;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r1 = r2.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        if ((r1 instanceof zaf) == false) goto L10;
        return (zaf) r1;
    L10:
        return new zaf(r2);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Bundle getGetServiceRequestExtraArgs() {
        ClientSettings r0 = this.zac;
        String r1 = r0.getRealClientPackageName();
        if (getContext().getPackageName().equals(r1) == true) goto L6;
        this.zad.putString("com.google.android.gms.signin.internal.realClientPackageName", r0.getRealClientPackageName());
    L6:
        return this.zad;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final boolean requiresSignIn() {
        return this.zab;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.signin.zae
    public final void zaa(zae r7) {
        Preconditions.checkNotNull(r7, "Expecting a valid ISignInCallbacks");
        Account r2 = this.zac.getAccountOrDefault();     // Catch: RemoteException -> L6
        if (BaseGmsClient.DEFAULT_ACCOUNT.equals(r2.name) == false) goto L8;
        GoogleSignInAccount r3 = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount();     // Catch: RemoteException -> L6
    L9:
        ((zaf) getService()).zag(new zai(1, new zaw(r2, ((Integer) Preconditions.checkNotNull(this.zae)).intValue(), r3)), r7);     // Catch: RemoteException -> L6
        return;
    L8:
        r3 = null;
    L6:
        e = move-exception;
        Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
        r7.zab(new zak(1, new ConnectionResult(8, null), null));     // Catch: RemoteException -> L14
        return;
    L14:
        Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.signin.zae
    public final void zab(IAccountAccessor r2, boolean r3) {
        ((zaf) getService()).zaf(r2, ((Integer) Preconditions.checkNotNull(this.zae)).intValue(), r3);     // Catch: RemoteException -> L4
        return;
    L4:
        Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.signin.zae
    public final void zac() {
        ((zaf) getService()).zae(((Integer) Preconditions.checkNotNull(this.zae)).intValue());     // Catch: RemoteException -> L4
        return;
    L4:
        Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
    }

    @Override // com.google.android.gms.signin.zae
    public final void zad() {
        connect(new BaseGmsClient.LegacyClientCallbackAdapter(this));
    }
}
