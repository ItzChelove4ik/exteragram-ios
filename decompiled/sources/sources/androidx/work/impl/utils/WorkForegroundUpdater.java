package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableFutureKt;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class WorkForegroundUpdater implements ForegroundUpdater {
    private static final String TAG = null;
    final ForegroundProcessor mForegroundProcessor;
    private final TaskExecutor mTaskExecutor;
    final WorkSpecDao mWorkSpecDao;

    /* renamed from: $r8$lambda$Ik9WY-k9fFXWE3Htab7GTHBbvxc, reason: not valid java name */
    public static /* synthetic */ Void m1002$r8$lambda$Ik9WYk9fFXWE3Htab7GTHBbvxc(WorkForegroundUpdater r3, UUID r4, ForegroundInfo r5, Context r6) {
        r3.getClass();
        String r42 = r4.toString();
        WorkSpec r0 = r3.mWorkSpecDao.getWorkSpec(r42);
        if (r0 != null) goto L5;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
        return null;
    L5:
        if (r0.state.isFinished() == true) goto L8;
        r3.mForegroundProcessor.startForeground(r42, r5);
        r6.startService(SystemForegroundDispatcher.createNotifyIntent(r6, WorkSpecKt.generationalId(r0), r5));
        return null;
    }

    static {
        TAG = Logger.tagWithPrefix("WMFgUpdater");
    }

    @SuppressLint({"LambdaLast"})
    public WorkForegroundUpdater(WorkDatabase r1, ForegroundProcessor r2, TaskExecutor r3) {
        this.mForegroundProcessor = r2;
        this.mTaskExecutor = r3;
        this.mWorkSpecDao = r1.workSpecDao();
    }

    @Override // androidx.work.ForegroundUpdater
    public ListenableFuture<Void> setForegroundAsync(Context r3, UUID r4, ForegroundInfo r5) {
        return ListenableFutureKt.executeAsync(this.mTaskExecutor.getSerialTaskExecutor(), "setForegroundAsync", new WorkForegroundUpdater$$ExternalSyntheticLambda0(this, r4, r5, r3));
    }
}
