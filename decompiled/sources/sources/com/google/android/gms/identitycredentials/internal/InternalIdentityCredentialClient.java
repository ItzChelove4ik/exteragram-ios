package com.google.android.gms.identitycredentials.internal;

import android.content.Context;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.identitycredentials.CreateCredentialHandle;
import com.google.android.gms.identitycredentials.CreateCredentialRequest;
import com.google.android.gms.identitycredentials.GetCredentialRequest;
import com.google.android.gms.identitycredentials.IdentityCredentialClient;
import com.google.android.gms.identitycredentials.PendingGetCredentialHandle;
import com.google.android.gms.identitycredentials.SignalCredentialStateRequest;
import com.google.android.gms.identitycredentials.SignalCredentialStateResponse;
import com.google.android.gms.internal.identity_credentials.zze;
import com.google.android.gms.internal.identity_credentials.zzh;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001$B%\b\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\u000bJ\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e2\u0006\u0010\r\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u000e2\u0006\u0010\r\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\u0006\u0010\r\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000e2\u0006\u0010\r\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001f\u0010\u001dJ\u001d\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u000e2\u0006\u0010\r\u001a\u00020\u001aH\u0016¢\u0006\u0004\b!\u0010\u001dJ\u001d\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u000e2\u0006\u0010\r\u001a\u00020\u001aH\u0016¢\u0006\u0004\b#\u0010\u001d¨\u0006%"}, d2 = {"Lcom/google/android/gms/identitycredentials/internal/InternalIdentityCredentialClient;", "Lcom/google/android/gms/common/api/GoogleApi;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "Lcom/google/android/gms/identitycredentials/IdentityCredentialClient;", "Landroid/content/Context;", "context", "Lcom/google/android/gms/common/api/Api$AbstractClientBuilder;", "Lcom/google/android/gms/identitycredentials/internal/IdentityCredentialClientImpl;", "clientBuilder", "<init>", "(Landroid/content/Context;Lcom/google/android/gms/common/api/Api$AbstractClientBuilder;)V", "(Landroid/content/Context;)V", "Lcom/google/android/gms/identitycredentials/GetCredentialRequest;", PluginsConstants.REQUEST, "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/identitycredentials/PendingGetCredentialHandle;", "getCredential", "(Lcom/google/android/gms/identitycredentials/GetCredentialRequest;)Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/identitycredentials/CreateCredentialRequest;", "Lcom/google/android/gms/identitycredentials/CreateCredentialHandle;", "createCredential", "(Lcom/google/android/gms/identitycredentials/CreateCredentialRequest;)Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/identitycredentials/SignalCredentialStateRequest;", "Lcom/google/android/gms/identitycredentials/SignalCredentialStateResponse;", "signalCredentialState", "(Lcom/google/android/gms/identitycredentials/SignalCredentialStateRequest;)Lcom/google/android/gms/tasks/Task;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/android/gms/identitycredentials/ClearExportResponse;", "clearExport", "(Ljava/lang/Object;)Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/identitycredentials/ExportCredentialsToDeviceSetupResponse;", "exportCredentialsToDeviceSetup", "Lcom/google/android/gms/identitycredentials/ImportCredentialsForDeviceSetupResponse;", "importCredentialsForDeviceSetup", "Lcom/google/android/gms/identitycredentials/CredentialTransferCapabilities;", "getCredentialTransferCapabilities", "Companion", "java.com.google.android.gmscore.integ.client.identity_credentials_identity_credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class InternalIdentityCredentialClient extends GoogleApi<Api.ApiOptions.NoOptions> implements IdentityCredentialClient {
    private static final Api<Api.ApiOptions.NoOptions> API = null;
    private static final InternalIdentityCredentialClient$Companion$CLIENT_BUILDER$1 CLIENT_BUILDER = null;
    private static final Api.ClientKey<IdentityCredentialClientImpl> CLIENT_KEY = null;
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/google/android/gms/identitycredentials/internal/InternalIdentityCredentialClient$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CLIENT_KEY", "Lcom/google/android/gms/common/api/Api$ClientKey;", "Lcom/google/android/gms/identitycredentials/internal/IdentityCredentialClientImpl;", "CLIENT_BUILDER", "com/google/android/gms/identitycredentials/internal/InternalIdentityCredentialClient$Companion$CLIENT_BUILDER$1", "Lcom/google/android/gms/identitycredentials/internal/InternalIdentityCredentialClient$Companion$CLIENT_BUILDER$1;", "API_NAME", _UrlKt.FRAGMENT_ENCODE_SET, "API", "Lcom/google/android/gms/common/api/Api;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "java.com.google.android.gmscore.integ.client.identity_credentials_identity_credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        Api.ClientKey<IdentityCredentialClientImpl> r0 = new Api.ClientKey();
        CLIENT_KEY = r0;
        InternalIdentityCredentialClient$Companion$CLIENT_BUILDER$1 r1 = new InternalIdentityCredentialClient$Companion$CLIENT_BUILDER$1();
        CLIENT_BUILDER = r1;
        API = new Api("IdentityCredentials.API", r1, r0);
    }

    public InternalIdentityCredentialClient(Context r4) {
        super(r4, API, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    private static final void createCredential$lambda$8(CreateCredentialRequest r1, IdentityCredentialClientImpl r2, TaskCompletionSource r3) {
        InternalIdentityCredentialClient$createCredential$1$callback$1 r0 = new InternalIdentityCredentialClient$createCredential$1$callback$1(r3);
        ((IIdentityCredentialService) r2.getService()).createCredential(r0, r1, zzh.zza(r2.getContext()));
    }

    private static final void getCredential$lambda$0(GetCredentialRequest r1, IdentityCredentialClientImpl r2, TaskCompletionSource r3) {
        InternalIdentityCredentialClient$getCredential$1$callback$1 r0 = new InternalIdentityCredentialClient$getCredential$1$callback$1(r3);
        ((IIdentityCredentialService) r2.getService()).getCredential(r0, r1, zzh.zza(r2.getContext()));
    }

    private static final void signalCredentialState$lambda$13(SignalCredentialStateRequest r1, IdentityCredentialClientImpl r2, TaskCompletionSource r3) {
        InternalIdentityCredentialClient$signalCredentialState$1$callback$1 r0 = new InternalIdentityCredentialClient$signalCredentialState$1$callback$1(r3);
        ((IIdentityCredentialService) r2.getService()).signalCredentialState(r0, r1, zzh.zza(r2.getContext()));
    }

    public static /* synthetic */ void zza(GetCredentialRequest r0, IdentityCredentialClientImpl r1, TaskCompletionSource r2) {
        getCredential$lambda$0(r0, r1, r2);
    }

    public static /* synthetic */ void zzh(CreateCredentialRequest r0, IdentityCredentialClientImpl r1, TaskCompletionSource r2) {
        createCredential$lambda$8(r0, r1, r2);
    }

    public static /* synthetic */ void zzj(SignalCredentialStateRequest r0, IdentityCredentialClientImpl r1, TaskCompletionSource r2) {
        signalCredentialState$lambda$13(r0, r1, r2);
    }

    @Override // com.google.android.gms.identitycredentials.IdentityCredentialClient
    public Task<CreateCredentialHandle> createCredential(CreateCredentialRequest r3) {
        return doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zze.zzf}).run(new zzg(r3)).setMethodKey(32704).build());
    }

    @Override // com.google.android.gms.identitycredentials.IdentityCredentialClient
    public Task<PendingGetCredentialHandle> getCredential(GetCredentialRequest r3) {
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{zze.zza}).run(new zzj(r3)).setMethodKey(32701).build());
    }

    @Override // com.google.android.gms.identitycredentials.IdentityCredentialClient
    public Task<SignalCredentialStateResponse> signalCredentialState(SignalCredentialStateRequest r3) {
        return doWrite(TaskApiCall.builder().setFeatures(new Feature[]{zze.zzj}).run(new zzi(r3)).setMethodKey(32709).build());
    }
}
