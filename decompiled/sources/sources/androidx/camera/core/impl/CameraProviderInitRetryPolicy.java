package androidx.camera.core.impl;

import androidx.camera.core.Logger;
import androidx.camera.core.RetryPolicy;
import androidx.camera.core.impl.CameraValidator;

/* loaded from: classes3.dex */
public final class CameraProviderInitRetryPolicy implements RetryPolicyInternal {
    private final RetryPolicy mDelegatePolicy;

    /* renamed from: androidx.camera.core.impl.CameraProviderInitRetryPolicy$1, reason: invalid class name */
    public class AnonymousClass1 implements RetryPolicy {
        final /* synthetic */ CameraProviderInitRetryPolicy this$0;
        final /* synthetic */ long val$timeoutInMillis;

        public AnonymousClass1(CameraProviderInitRetryPolicy r1, long r2) {
            this.this$0 = r1;
            this.val$timeoutInMillis = r2;
        }

        @Override // androidx.camera.core.RetryPolicy
        public long getTimeoutInMillis() {
            return this.val$timeoutInMillis;
        }

        @Override // androidx.camera.core.RetryPolicy
        public RetryPolicy.RetryConfig onRetryDecisionRequested(RetryPolicy.ExecutionState r1) {
            if (r1.getStatus() != 1) goto L7;
            return RetryPolicy.RetryConfig.NOT_RETRY;
        L7:
            return RetryPolicy.RetryConfig.DEFAULT_DELAY_RETRY;
        }
    }

    public static final class Legacy implements RetryPolicyInternal {
        private final RetryPolicy mBasePolicy;

        public Legacy(long r2) {
            this.mBasePolicy = new CameraProviderInitRetryPolicy(r2);
        }

        @Override // androidx.camera.core.impl.RetryPolicyInternal
        public RetryPolicy copy(long r1) {
            return new Legacy(r1);
        }

        @Override // androidx.camera.core.RetryPolicy
        public long getTimeoutInMillis() {
            return this.mBasePolicy.getTimeoutInMillis();
        }

        @Override // androidx.camera.core.RetryPolicy
        public RetryPolicy.RetryConfig onRetryDecisionRequested(RetryPolicy.ExecutionState r2) {
            if (this.mBasePolicy.onRetryDecisionRequested(r2).shouldRetry() == true) goto L13;
            Throwable r1 = r2.getCause();
            if ((r1 instanceof CameraValidator.CameraIdListIncorrectException) == false) goto L11;
            Logger.e("CameraX", "The device might underreport the amount of the cameras. Finish the initialize task since we are already reaching the maximum number of retries.");
            if (((CameraValidator.CameraIdListIncorrectException) r1).getAvailableCameraCount() <= 0) goto L11;
            return RetryPolicy.RetryConfig.COMPLETE_WITHOUT_FAILURE;
        L11:
            return RetryPolicy.RetryConfig.NOT_RETRY;
        L13:
            return RetryPolicy.RetryConfig.DEFAULT_DELAY_RETRY;
        }
    }

    public CameraProviderInitRetryPolicy(long r3) {
        this.mDelegatePolicy = new TimeoutRetryPolicy(r3, new AnonymousClass1(this, r3));
    }

    @Override // androidx.camera.core.impl.RetryPolicyInternal
    public RetryPolicy copy(long r1) {
        return new CameraProviderInitRetryPolicy(r1);
    }

    @Override // androidx.camera.core.RetryPolicy
    public long getTimeoutInMillis() {
        return this.mDelegatePolicy.getTimeoutInMillis();
    }

    @Override // androidx.camera.core.RetryPolicy
    public RetryPolicy.RetryConfig onRetryDecisionRequested(RetryPolicy.ExecutionState r1) {
        return this.mDelegatePolicy.onRetryDecisionRequested(r1);
    }
}
