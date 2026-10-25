package androidx.camera.core;

import androidx.camera.core.impl.CameraProviderInitRetryPolicy;
import androidx.camera.core.impl.RetryPolicyInternal;
import androidx.camera.core.impl.TimeoutRetryPolicy;
import androidx.core.util.Preconditions;

/* loaded from: classes3.dex */
public interface RetryPolicy {
    public static final RetryPolicy DEFAULT = null;
    public static final RetryPolicy NEVER = null;
    public static final RetryPolicy RETRY_UNAVAILABLE_CAMERA = null;

    public static final class Builder {
        private final RetryPolicy mBasePolicy;
        private long mTimeoutInMillis;

        public Builder(RetryPolicy r3) {
            this.mBasePolicy = r3;
            this.mTimeoutInMillis = r3.getTimeoutInMillis();
        }

        public RetryPolicy build() {
            RetryPolicy r0 = this.mBasePolicy;
            if ((r0 instanceof RetryPolicyInternal) == false) goto L7;
            return ((RetryPolicyInternal) r0).copy(this.mTimeoutInMillis);
        L7:
            return new TimeoutRetryPolicy(this.mTimeoutInMillis, this.mBasePolicy);
        }
    }

    public interface ExecutionState {
        Throwable getCause();

        long getExecutedTimeInMillis();

        int getStatus();
    }

    public static final class RetryConfig {
        public static RetryConfig COMPLETE_WITHOUT_FAILURE;
        public static final RetryConfig DEFAULT_DELAY_RETRY = null;
        public static final RetryConfig MINI_DELAY_RETRY = null;
        public static final RetryConfig NOT_RETRY = null;
        private final boolean mCompleteWithoutFailure;
        private final long mDelayInMillis;
        private final boolean mShouldRetry;

        static {
            NOT_RETRY = new RetryConfig(false, 0);
            DEFAULT_DELAY_RETRY = new RetryConfig(true);
            MINI_DELAY_RETRY = new RetryConfig(true, 100);
            COMPLETE_WITHOUT_FAILURE = new RetryConfig(false, 0, true);
        }

        private RetryConfig(boolean r3) {
            this(r3, getDefaultRetryDelayInMillis());
        }

        private RetryConfig(boolean r2, long r3) {
            this(r2, r3, false);
        }

        private RetryConfig(boolean r1, long r2, boolean r4) {
            this.mShouldRetry = r1;
            this.mDelayInMillis = r2;
            if (r4 == false) goto L5;
            Preconditions.checkArgument(!r1, "shouldRetry must be false when completeWithoutFailure is set to true");
        L5:
            this.mCompleteWithoutFailure = r4;
        }

        public static long getDefaultRetryDelayInMillis() {
            return 500;
        }

        public long getRetryDelayInMillis() {
            return this.mDelayInMillis;
        }

        public boolean shouldCompleteWithoutFailure() {
            return this.mCompleteWithoutFailure;
        }

        public boolean shouldRetry() {
            return this.mShouldRetry;
        }
    }

    /* renamed from: $r8$lambda$Pod7zgv84-X-fgdew8tx8Xu5mIY, reason: not valid java name */
    static /* synthetic */ RetryConfig m585$r8$lambda$Pod7zgv84Xfgdew8tx8Xu5mIY(ExecutionState r0) {
        return RetryConfig.NOT_RETRY;
    }

    static {
        NEVER = new RetryPolicy$$ExternalSyntheticLambda0();
        DEFAULT = new CameraProviderInitRetryPolicy.Legacy(getDefaultRetryTimeoutInMillis());
        RETRY_UNAVAILABLE_CAMERA = new CameraProviderInitRetryPolicy(getDefaultRetryTimeoutInMillis());
    }

    static long getDefaultRetryTimeoutInMillis() {
        return 6000;
    }

    default long getTimeoutInMillis() {
        return 0;
    }

    RetryConfig onRetryDecisionRequested(ExecutionState r1);
}
