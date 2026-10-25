package androidx.camera.core.impl;

import android.os.SystemClock;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.RetryPolicy;
import androidx.camera.core.impl.CameraValidator;

/* loaded from: classes3.dex */
public final class CameraProviderExecutionState implements RetryPolicy.ExecutionState {
    private final int mAttemptCount;
    private final Throwable mCause;
    private final int mStatus;
    private final long mTaskExecutedTimeInMillis;

    public CameraProviderExecutionState(long r3, int r5, Throwable r6) {
        this.mTaskExecutedTimeInMillis = SystemClock.elapsedRealtime() - r3;
        this.mAttemptCount = r5;
        if ((r6 instanceof CameraValidator.CameraIdListIncorrectException) == false) goto L7;
        this.mStatus = 2;
        this.mCause = r6;
        return;
    L7:
        if ((r6 instanceof InitializationException) == false) goto L21;
        Throwable r32 = r6.getCause();
        if (r32 == null) goto L11;
        r6 = r32;
    L11:
        this.mCause = r6;
        if ((r6 instanceof CameraUnavailableException) == false) goto L16;
        this.mStatus = 2;
        return;
    L16:
        if ((r6 instanceof IllegalArgumentException) == false) goto L19;
        this.mStatus = 1;
        return;
    L19:
        this.mStatus = 0;
        return;
    L21:
        this.mStatus = 0;
        this.mCause = r6;
    }

    @Override // androidx.camera.core.RetryPolicy.ExecutionState
    public Throwable getCause() {
        return this.mCause;
    }

    @Override // androidx.camera.core.RetryPolicy.ExecutionState
    public long getExecutedTimeInMillis() {
        return this.mTaskExecutedTimeInMillis;
    }

    @Override // androidx.camera.core.RetryPolicy.ExecutionState
    public int getStatus() {
        return this.mStatus;
    }
}
