package androidx.work;

import android.content.Context;
import android.net.Network;
import android.net.Uri;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class ListenableWorker {
    private Context mAppContext;
    private final AtomicInteger mStopReason;
    private boolean mUsed;
    private WorkerParameters mWorkerParams;

    /* loaded from: classes4.dex */
    public static abstract class Result {

        public static final class Failure extends Result {
            private final Data mOutputData;

            public Failure() {
                this(Data.EMPTY);
            }

            public Failure(Data r1) {
                this.mOutputData = r1;
            }

            public boolean equals(Object r3) {
                if (this != r3) goto L5;
                return true;
            L5:
                if (r3 != null) goto L7;
                return false;
            L7:
                if (Failure.class == r3.getClass()) goto L10;
                return false;
            L10:
                return this.mOutputData.equals(((Failure) r3).mOutputData);
            }

            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return (Failure.class.getName().hashCode() * 31) + this.mOutputData.hashCode();
            }

            public String toString() {
                return "Failure {mOutputData=" + this.mOutputData + '}';
            }
        }

        public static final class Retry extends Result {
            public Retry() {
            }

            public boolean equals(Object r2) {
                if (this != r2) goto L5;
                return true;
            L5:
                if (r2 != null) goto L7;
                return false;
            L7:
                if (Retry.class != r2.getClass()) goto L11;
                return true;
            L11:
                return false;
            }

            public int hashCode() {
                return Retry.class.getName().hashCode();
            }

            public String toString() {
                return "Retry";
            }
        }

        public static final class Success extends Result {
            private final Data mOutputData;

            public Success() {
                this(Data.EMPTY);
            }

            public Success(Data r1) {
                this.mOutputData = r1;
            }

            public boolean equals(Object r3) {
                if (this != r3) goto L5;
                return true;
            L5:
                if (r3 != null) goto L7;
                return false;
            L7:
                if (Success.class == r3.getClass()) goto L10;
                return false;
            L10:
                return this.mOutputData.equals(((Success) r3).mOutputData);
            }

            public Data getOutputData() {
                return this.mOutputData;
            }

            public int hashCode() {
                return (Success.class.getName().hashCode() * 31) + this.mOutputData.hashCode();
            }

            public String toString() {
                return "Success {mOutputData=" + this.mOutputData + '}';
            }
        }

        public Result() {
        }

        public static Result failure() {
            return new Failure();
        }

        public static Result retry() {
            return new Retry();
        }

        public static Result success() {
            return new Success();
        }
    }

    public ListenableWorker(Context r3, WorkerParameters r4) {
        this.mStopReason = new AtomicInteger(-256);
        if (r3 == null) goto L9;
        if (r4 == null) goto L7;
        this.mAppContext = r3;
        this.mWorkerParams = r4;
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("WorkerParameters is null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("Application Context is null");
        throw null;
    }

    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    public Executor getBackgroundExecutor() {
        return this.mWorkerParams.getBackgroundExecutor();
    }

    public abstract ListenableFuture<ForegroundInfo> getForegroundInfoAsync();

    public final UUID getId() {
        return this.mWorkerParams.getId();
    }

    public final Data getInputData() {
        return this.mWorkerParams.getInputData();
    }

    public final Network getNetwork() {
        return this.mWorkerParams.getNetwork();
    }

    public final int getRunAttemptCount() {
        return this.mWorkerParams.getRunAttemptCount();
    }

    public final int getStopReason() {
        return this.mStopReason.get();
    }

    public final Set<String> getTags() {
        return this.mWorkerParams.getTags();
    }

    public TaskExecutor getTaskExecutor() {
        return this.mWorkerParams.getTaskExecutor();
    }

    public final List<String> getTriggeredContentAuthorities() {
        return this.mWorkerParams.getTriggeredContentAuthorities();
    }

    public final List<Uri> getTriggeredContentUris() {
        return this.mWorkerParams.getTriggeredContentUris();
    }

    public WorkerFactory getWorkerFactory() {
        return this.mWorkerParams.getWorkerFactory();
    }

    public final boolean isStopped() {
        if (this.mStopReason.get() == (-256)) goto L6;
        return true;
    L6:
        return false;
    }

    public final boolean isUsed() {
        return this.mUsed;
    }

    public void onStopped() {
    }

    public final ListenableFuture<Void> setForegroundAsync(ForegroundInfo r3) {
        return this.mWorkerParams.getForegroundUpdater().setForegroundAsync(getApplicationContext(), getId(), r3);
    }

    public ListenableFuture<Void> setProgressAsync(Data r3) {
        return this.mWorkerParams.getProgressUpdater().updateProgress(getApplicationContext(), getId(), r3);
    }

    public final void setUsed() {
        this.mUsed = true;
    }

    public abstract ListenableFuture<Result> startWork();

    public final void stop(int r3) {
        if (this.mStopReason.compareAndSet(-256, r3) == false) goto L6;
        onStopped();
        return;
    }
}
