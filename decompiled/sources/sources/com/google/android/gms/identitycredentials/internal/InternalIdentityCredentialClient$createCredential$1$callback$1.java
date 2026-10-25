package com.google.android.gms.identitycredentials.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.identitycredentials.CreateCredentialHandle;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"com/google/android/gms/identitycredentials/internal/InternalIdentityCredentialClient$createCredential$1$callback$1", "Lcom/google/android/gms/identitycredentials/internal/IdentityCredentialBaseCallbacks;", "onCreateCredentialV2", _UrlKt.FRAGMENT_ENCODE_SET, "status", "Lcom/google/android/gms/common/api/Status;", "result", "Lcom/google/android/gms/identitycredentials/CreateCredentialHandle;", "java.com.google.android.gmscore.integ.client.identity_credentials_identity_credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class InternalIdentityCredentialClient$createCredential$1$callback$1 extends IdentityCredentialBaseCallbacks {
    final /* synthetic */ TaskCompletionSource<CreateCredentialHandle> $completionSource;

    public InternalIdentityCredentialClient$createCredential$1$callback$1(TaskCompletionSource<CreateCredentialHandle> r1) {
        this.$completionSource = r1;
    }

    @Override // com.google.android.gms.identitycredentials.internal.IdentityCredentialBaseCallbacks, com.google.android.gms.identitycredentials.internal.IIdentityCredentialCallbacks
    public void onCreateCredentialV2(Status r1, CreateCredentialHandle r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.$completionSource);
    }
}
