package androidx.camera.core.impl;

import androidx.camera.core.RetryPolicy;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
public final class TimeoutRetryPolicy implements RetryPolicy {
    private final RetryPolicy mDelegatePolicy;
    private final long mTimeoutInMillis;

    public TimeoutRetryPolicy(long r3, RetryPolicy r5) {
        if (r3 < 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "Timeout must be non-negative.");
        this.mTimeoutInMillis = r3;
        this.mDelegatePolicy = r5;
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.camera.core.RetryPolicy
    public long getTimeoutInMillis() {
        return this.mTimeoutInMillis;
    }

    @Override // androidx.camera.core.RetryPolicy
    public RetryPolicy.RetryConfig onRetryDecisionRequested(RetryPolicy.ExecutionState r6) {
        RetryPolicy.RetryConfig r0 = this.mDelegatePolicy.onRetryDecisionRequested(r6);
        if (getTimeoutInMillis() > 0) goto L5;
    L8:
        return r0;
    L5:
        if (r6.getExecutedTimeInMillis() < (getTimeoutInMillis() - r0.getRetryDelayInMillis())) goto L8;
        return RetryPolicy.RetryConfig.NOT_RETRY;
    }
}
