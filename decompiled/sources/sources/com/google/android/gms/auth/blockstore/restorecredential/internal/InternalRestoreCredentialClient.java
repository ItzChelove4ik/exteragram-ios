package com.google.android.gms.auth.blockstore.restorecredential.internal;

import android.content.Context;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialRequest;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialResponse;
import com.google.android.gms.auth.blockstore.restorecredential.RestoreCredentialClient;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.auth_blockstore.zzab;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u000eB\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/google/android/gms/auth/blockstore/restorecredential/internal/InternalRestoreCredentialClient;", "Lcom/google/android/gms/common/api/GoogleApi;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "Lcom/google/android/gms/auth/blockstore/restorecredential/RestoreCredentialClient;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lcom/google/android/gms/auth/blockstore/restorecredential/GetRestoreCredentialRequest;", PluginsConstants.REQUEST, "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/auth/blockstore/restorecredential/GetRestoreCredentialResponse;", "getRestoreCredential", "(Lcom/google/android/gms/auth/blockstore/restorecredential/GetRestoreCredentialRequest;)Lcom/google/android/gms/tasks/Task;", "Companion", "java.com.google.android.gmscore.integ.client.auth_blockstore_client_auth_blockstore"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class InternalRestoreCredentialClient extends GoogleApi<Api.ApiOptions.NoOptions> implements RestoreCredentialClient {
    private static final Api<Api.ApiOptions.NoOptions> API = null;
    private static final Api.ClientKey<RestoreCredentialClientImpl> CLIENT_KEY = null;
    public static final Companion Companion = null;
    private static final InternalRestoreCredentialClient$Companion$clientBuilder$1 clientBuilder = null;

    @Metadata(d1 = {"\u0000'\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/android/gms/auth/blockstore/restorecredential/internal/InternalRestoreCredentialClient$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CLIENT_KEY", "Lcom/google/android/gms/common/api/Api$ClientKey;", "Lcom/google/android/gms/auth/blockstore/restorecredential/internal/RestoreCredentialClientImpl;", "clientBuilder", "com/google/android/gms/auth/blockstore/restorecredential/internal/InternalRestoreCredentialClient$Companion$clientBuilder$1", "Lcom/google/android/gms/auth/blockstore/restorecredential/internal/InternalRestoreCredentialClient$Companion$clientBuilder$1;", "API", "Lcom/google/android/gms/common/api/Api;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "java.com.google.android.gmscore.integ.client.auth_blockstore_client_auth_blockstore"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static /* synthetic */ void $r8$lambda$6nzcDLrXx618XzL1jxRATH2b6h0(GetRestoreCredentialRequest r0, RestoreCredentialClientImpl r1, TaskCompletionSource r2) {
        getRestoreCredential$lambda$0(r0, r1, r2);
    }

    static {
        Companion = new Companion(null);
        Api.ClientKey<RestoreCredentialClientImpl> r0 = new Api.ClientKey();
        CLIENT_KEY = r0;
        InternalRestoreCredentialClient$Companion$clientBuilder$1 r1 = new InternalRestoreCredentialClient$Companion$clientBuilder$1();
        clientBuilder = r1;
        API = new Api("RestoreCredential.API", r1, r0);
    }

    public InternalRestoreCredentialClient(Context r4) {
        super(r4, API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    private static final void getRestoreCredential$lambda$0(GetRestoreCredentialRequest r1, RestoreCredentialClientImpl r2, TaskCompletionSource r3) {
        InternalRestoreCredentialClient$getRestoreCredential$1$callback$1 r0 = new InternalRestoreCredentialClient$getRestoreCredential$1$callback$1(r3);
        ((IRestoreCredentialService) r2.getService()).getRestoreCredential(r1, r0);
    }

    @Override // com.google.android.gms.auth.blockstore.restorecredential.RestoreCredentialClient
    public Task<GetRestoreCredentialResponse> getRestoreCredential(GetRestoreCredentialRequest r3) {
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{zzab.zzk}).run(new InternalRestoreCredentialClient$$ExternalSyntheticLambda2(r3)).setMethodKey(1695).build());
    }
}
