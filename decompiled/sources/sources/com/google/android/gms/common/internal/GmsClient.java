package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Api.Client, zam {
    private static volatile Executor zaa;
    private final ClientSettings zab;
    private final Set zac;
    private final Account zad;

    @Deprecated
    public GmsClient(Context r1, Looper r2, int r3, ClientSettings r4, GoogleApiClient.ConnectionCallbacks r5, GoogleApiClient.OnConnectionFailedListener r6) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public GmsClient(Context r10, Looper r11, int r12, ClientSettings r13, ConnectionCallbacks r14, OnConnectionFailedListener r15) {
        this(r10, r11, GmsClientSupervisor.getInstance(r10), GoogleApiAvailability.getInstance(), r12, r13, (ConnectionCallbacks) Preconditions.checkNotNull(r14), (OnConnectionFailedListener) Preconditions.checkNotNull(r15));
    }

    public GmsClient(Context r10, Looper r11, GmsClientSupervisor r12, GoogleApiAvailability r13, int r14, ClientSettings r15, ConnectionCallbacks r16, OnConnectionFailedListener r17) {
        zal r2 = null;
        if (r16 != null) goto L5;
        zak r6 = null;
    L6:
        if (r17 == null) goto L9;
        r2 = new zal(r17);
    L9:
        super(r10, r11, r12, r13, r14, r6, r2, r15.zab());
        this.zab = r15;
        this.zad = r15.getAccount();
        this.zac = zab(r15.getAllRequestedScopes());
        return;
    L5:
        r6 = new zak(r16);
        goto L6
    }

    private final Set zab(Set r3) {
        Set<Scope> r2 = validateScopes(r3);
        Iterator<Scope> r0 = r2.iterator();
    L4:
        if (r0.hasNext() == false) goto L13;
        if (r3.contains(r0.next()) == true) goto L4;
        Segment$$ExternalSyntheticBUOutline1.m("Expanding scopes is not permitted, use implied scopes instead");
        return null;
    L13:
        return r2;
    }

    public static void zag(Executor r0) {
        zaa = r0;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Account getAccount() {
        return this.zad;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public Executor getBindServiceExecutor() {
        return zaa;
    }

    public final ClientSettings getClientSettings() {
        return this.zab;
    }

    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Set<Scope> getScopes() {
        return this.zac;
    }

    @Override // com.google.android.gms.common.api.Api.Client
    public Set<Scope> getScopesForConnectionlessNonSignIn() {
        if (requiresSignIn() == false) goto L7;
        return this.zac;
    L7:
        return Collections.EMPTY_SET;
    }

    public Set<Scope> validateScopes(Set<Scope> r1) {
        return r1;
    }
}
