package androidx.work;

import android.net.Network;
import android.net.Uri;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes4.dex */
public final class WorkerParameters {
    private Executor mBackgroundExecutor;
    private ForegroundUpdater mForegroundUpdater;
    private int mGeneration;
    private UUID mId;
    private Data mInputData;
    private ProgressUpdater mProgressUpdater;
    private int mRunAttemptCount;
    private RuntimeExtras mRuntimeExtras;
    private Set<String> mTags;
    private TaskExecutor mWorkTaskExecutor;
    private CoroutineContext mWorkerContext;
    private WorkerFactory mWorkerFactory;

    public static class RuntimeExtras {
        public Network network;
        public List<String> triggeredContentAuthorities;
        public List<Uri> triggeredContentUris;

        public RuntimeExtras() {
            List r0 = Collections.EMPTY_LIST;
            this.triggeredContentAuthorities = r0;
            this.triggeredContentUris = r0;
        }
    }

    public WorkerParameters(UUID r1, Data r2, Collection<String> r3, RuntimeExtras r4, int r5, int r6, Executor r7, CoroutineContext r8, TaskExecutor r9, WorkerFactory r10, ProgressUpdater r11, ForegroundUpdater r12) {
        this.mId = r1;
        this.mInputData = r2;
        this.mTags = new HashSet(r3);
        this.mRuntimeExtras = r4;
        this.mRunAttemptCount = r5;
        this.mGeneration = r6;
        this.mBackgroundExecutor = r7;
        this.mWorkerContext = r8;
        this.mWorkTaskExecutor = r9;
        this.mWorkerFactory = r10;
        this.mProgressUpdater = r11;
        this.mForegroundUpdater = r12;
    }

    public Executor getBackgroundExecutor() {
        return this.mBackgroundExecutor;
    }

    public ForegroundUpdater getForegroundUpdater() {
        return this.mForegroundUpdater;
    }

    public UUID getId() {
        return this.mId;
    }

    public Data getInputData() {
        return this.mInputData;
    }

    public Network getNetwork() {
        return this.mRuntimeExtras.network;
    }

    public ProgressUpdater getProgressUpdater() {
        return this.mProgressUpdater;
    }

    public int getRunAttemptCount() {
        return this.mRunAttemptCount;
    }

    public Set<String> getTags() {
        return this.mTags;
    }

    public TaskExecutor getTaskExecutor() {
        return this.mWorkTaskExecutor;
    }

    public List<String> getTriggeredContentAuthorities() {
        return this.mRuntimeExtras.triggeredContentAuthorities;
    }

    public List<Uri> getTriggeredContentUris() {
        return this.mRuntimeExtras.triggeredContentUris;
    }

    public CoroutineContext getWorkerContext() {
        return this.mWorkerContext;
    }

    public WorkerFactory getWorkerFactory() {
        return this.mWorkerFactory;
    }
}
