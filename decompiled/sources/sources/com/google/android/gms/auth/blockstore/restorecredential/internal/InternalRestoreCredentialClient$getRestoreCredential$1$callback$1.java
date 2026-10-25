package com.google.android.gms.auth.blockstore.restorecredential.internal;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialResponse;
import com.google.android.gms.auth.blockstore.restorecredential.internal.IGetRestoreCredentialCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/google/android/gms/auth/blockstore/restorecredential/internal/InternalRestoreCredentialClient$getRestoreCredential$1$callback$1", "Lcom/google/android/gms/auth/blockstore/restorecredential/internal/IGetRestoreCredentialCallback$Stub;", "onGetRestoreCredential", _UrlKt.FRAGMENT_ENCODE_SET, "status", "Lcom/google/android/gms/common/api/Status;", PluginsConstants.RESPONSE, "Lcom/google/android/gms/auth/blockstore/restorecredential/GetRestoreCredentialResponse;", "java.com.google.android.gmscore.integ.client.auth_blockstore_client_auth_blockstore"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class InternalRestoreCredentialClient$getRestoreCredential$1$callback$1 extends IGetRestoreCredentialCallback.Stub {
    final /* synthetic */ TaskCompletionSource<GetRestoreCredentialResponse> $completionSource;

    public InternalRestoreCredentialClient$getRestoreCredential$1$callback$1(TaskCompletionSource<GetRestoreCredentialResponse> r1) {
        this.$completionSource = r1;
    }

    @Override // com.google.android.gms.auth.blockstore.restorecredential.internal.IGetRestoreCredentialCallback
    public void onGetRestoreCredential(Status r1, GetRestoreCredentialResponse r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.$completionSource);
    }
}
