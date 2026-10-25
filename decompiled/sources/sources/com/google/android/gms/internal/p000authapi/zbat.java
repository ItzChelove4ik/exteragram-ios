package com.google.android.gms.internal.p000authapi;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.zbv;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ApiMetadata;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class zbat extends GoogleApi implements SignInClient {
    private static final Api.ClientKey zba = null;
    private static final Api.AbstractClientBuilder zbb = null;
    private static final Api zbc = null;
    private final String zbd;

    static {
        Api.ClientKey r0 = new Api.ClientKey();
        zba = r0;
        zbak r1 = new zbak();
        zbb = r1;
        zbc = new Api("Auth.Api.Identity.SignIn.API", r1, r0);
    }

    public zbat(Activity r3, zbv r4) {
        super(r3, zbc, r4, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zbd = zbaw.zba();
    }

    public zbat(Context r3, zbv r4) {
        super(r3, zbc, r4, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zbd = zbaw.zba();
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<BeginSignInResult> beginSignIn(BeginSignInRequest r6) {
        Preconditions.checkNotNull(r6);
        BeginSignInRequest.Builder r62 = BeginSignInRequest.zba(r6);
        r62.zba(this.zbd);
        BeginSignInRequest r63 = r62.build();
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{new Feature("auth_api_credentials_begin_sign_in", 8)}).run(new zbas(this, r63)).setAutoResolveMissingFeatures(false).setMethodKey(1553).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final String getPhoneNumberFromIntent(Intent r2) {
        if (r2 == null) goto L17;
        Status r1 = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(r2, "status", Status.CREATOR);
        if (r1 == null) goto L15;
        if (r1.isSuccess() == false) goto L13;
        String r12 = r2.getStringExtra("phone_number_hint_result");
        if (r12 == null) goto L11;
        return r12;
    L11:
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    L13:
        throw new ApiException(r1);
    L15:
        throw new ApiException(Status.RESULT_CANCELED);
    L17:
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<PendingIntent> getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest r3) {
        Preconditions.checkNotNull(r3);
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{zbav.zbi}).run(new zbar(this, r3)).setMethodKey(1653).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final SignInCredential getSignInCredentialFromIntent(Intent r2) {
        if (r2 == null) goto L17;
        Status r1 = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(r2, "status", Status.CREATOR);
        if (r1 == null) goto L15;
        if (r1.isSuccess() == false) goto L13;
        SignInCredential r12 = (SignInCredential) SafeParcelableSerializer.deserializeFromIntentExtra(r2, "sign_in_credential", SignInCredential.CREATOR);
        if (r12 == null) goto L11;
        return r12;
    L11:
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    L13:
        throw new ApiException(r1);
    L15:
        throw new ApiException(Status.RESULT_CANCELED);
    L17:
        throw new ApiException(Status.RESULT_INTERNAL_ERROR);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<PendingIntent> getSignInIntent(GetSignInIntentRequest r3) {
        Preconditions.checkNotNull(r3);
        GetSignInIntentRequest.Builder r32 = GetSignInIntentRequest.zba(r3);
        r32.zba(this.zbd);
        GetSignInIntentRequest r33 = r32.build();
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{zbav.zbg}).run(new zbaq(this, r33)).setMethodKey(1555).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<Void> signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        Iterator<GoogleApiClient> r0 = GoogleApiClient.getAllClients().iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().maybeSignOut();
        goto L4
    L6:
        GoogleApiManager.reportSignOut();
        return doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zbav.zbb}).run(new zbap(this)).setAutoResolveMissingFeatures(false).setMethodKey(1554).build());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zba(zbau r2, TaskCompletionSource r3) {
        zbam r0 = new zbam(this, r3);
        zbv r32 = (zbv) r2.getService();
        ApiMetadata r22 = zbba.zba(r2.getContext());
        r32.zbd(r0, this.zbd, r22);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ void zbb(GetPhoneNumberHintIntentRequest r2, zbau r3, TaskCompletionSource r4) {
        zbao r0 = new zbao(this, r4);
        zbv r42 = (zbv) r3.getService();
        ApiMetadata r32 = zbba.zba(r3.getContext());
        r42.zbf(r0, r2, this.zbd, r32);
    }
}
