package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.Data;
import androidx.work.ListenableFutureKt;
import androidx.work.Logger;
import androidx.work.ProgressUpdater;
import androidx.work.WorkInfo$State;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;

/* loaded from: classes4.dex */
public class WorkProgressUpdater implements ProgressUpdater {
    static final String TAG = null;
    final TaskExecutor mTaskExecutor;
    final WorkDatabase mWorkDatabase;

    /* renamed from: $r8$lambda$8aYEZ3xQO44kbdD--xDjbyAMTkw, reason: not valid java name */
    public static /* synthetic */ Void m1003$r8$lambda$8aYEZ3xQO44kbdDxDjbyAMTkw(WorkProgressUpdater r6, UUID r7, Data r8) {
        r6.getClass();
        String r1 = r7.toString();
        Logger r2 = Logger.get();
        String r3 = TAG;
        r2.debug(r3, "Updating progress for " + r7 + " (" + r8 + ")");
        r6.mWorkDatabase.beginTransaction();
        WorkSpec r72 = r6.mWorkDatabase.workSpecDao().getWorkSpec(r1);     // Catch: Throwable -> L8
        if (r72 == null) goto L15;
        if (r72.state != WorkInfo$State.RUNNING) goto L10;
        r6.mWorkDatabase.workProgressDao().insert(new WorkProgress(r1, r8));     // Catch: Throwable -> L8
    L11:
        r6.mWorkDatabase.setTransactionSuccessful();     // Catch: Throwable -> L8
        r6.mWorkDatabase.endTransaction();
        return null;
    L10:
        Logger.get().warning(r3, "Ignoring setProgressAsync(...). WorkSpec (" + r1 + ") is not in a RUNNING state.");     // Catch: Throwable -> L8
        goto L11
    L15:
        throw new IllegalStateException("Calls to setProgressAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        Logger.get().error(TAG, "Error updating Worker progress", th);     // Catch: Throwable -> L18
        throw th;     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        r6.mWorkDatabase.endTransaction();
        throw th;
    }

    static {
        TAG = Logger.tagWithPrefix("WorkProgressUpdater");
    }

    public WorkProgressUpdater(WorkDatabase r1, TaskExecutor r2) {
        this.mWorkDatabase = r1;
        this.mTaskExecutor = r2;
    }

    @Override // androidx.work.ProgressUpdater
    public ListenableFuture<Void> updateProgress(Context r2, UUID r3, Data r4) {
        return ListenableFutureKt.executeAsync(this.mTaskExecutor.getSerialTaskExecutor(), "updateProgress", new WorkProgressUpdater$$ExternalSyntheticLambda0(this, r3, r4));
    }
}
