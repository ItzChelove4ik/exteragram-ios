package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;

/* loaded from: classes5.dex */
class GetAuthTokenListener implements StateListener {
    private final TaskCompletionSource<InstallationTokenResult> resultTaskCompletionSource;
    private final Utils utils;

    public GetAuthTokenListener(Utils r1, TaskCompletionSource<InstallationTokenResult> r2) {
        this.utils = r1;
        this.resultTaskCompletionSource = r2;
    }

    @Override // com.google.firebase.installations.StateListener
    public boolean onException(Exception r1) {
        this.resultTaskCompletionSource.trySetException(r1);
        return true;
    }

    @Override // com.google.firebase.installations.StateListener
    public boolean onStateReached(PersistedInstallationEntry r4) {
        if (r4.isRegistered() == true) goto L5;
        return false;
    L5:
        if (this.utils.isAuthTokenExpired(r4) == true) goto L10;
        this.resultTaskCompletionSource.setResult(InstallationTokenResult.builder().setToken(r4.getAuthToken()).setTokenExpirationTimestamp(r4.getExpiresInSecs()).setTokenCreationTimestamp(r4.getTokenCreationEpochInSecs()).build());
        return true;
    L10:
        return false;
    }
}
