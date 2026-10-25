package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;

/* loaded from: classes5.dex */
class GetIdListener implements StateListener {
    final TaskCompletionSource<String> taskCompletionSource;

    public GetIdListener(TaskCompletionSource<String> r1) {
        this.taskCompletionSource = r1;
    }

    @Override // com.google.firebase.installations.StateListener
    public boolean onException(Exception r1) {
        return false;
    }

    @Override // com.google.firebase.installations.StateListener
    public boolean onStateReached(PersistedInstallationEntry r2) {
        if (r2.isUnregistered() == false) goto L5;
    L11:
        this.taskCompletionSource.trySetResult(r2.getFirebaseInstallationId());
        return true;
    L5:
        if (r2.isRegistered() == true) goto L11;
        if (r2.isErrored() == true) goto L11;
        return false;
    }
}
