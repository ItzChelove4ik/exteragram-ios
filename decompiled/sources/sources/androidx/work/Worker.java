package androidx.work;

import android.content.Context;
import androidx.work.ListenableWorker;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH'J\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000bJ\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u000bH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0017¨\u0006\u000f"}, d2 = {"Landroidx/work/Worker;", "Landroidx/work/ListenableWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "startWork", "Lcom/google/common/util/concurrent/ListenableFuture;", "getForegroundInfoAsync", "Landroidx/work/ForegroundInfo;", "getForegroundInfo", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class Worker extends ListenableWorker {
    /* renamed from: $r8$lambda$i0tC8mXpIGwMgdaXb-ZFThaMPvQ, reason: not valid java name */
    public static ForegroundInfo m971$r8$lambda$i0tC8mXpIGwMgdaXbZFThaMPvQ(Worker r0) {
        return r0.getForegroundInfo();
    }

    public static ListenableWorker.Result $r8$lambda$opNRPVQx7zi4ENxyGPOIhxFU0oM(Worker r0) {
        return r0.doWork();
    }

    public Worker(Context r1, WorkerParameters r2) {
        super(r1, r2);
    }

    public abstract ListenableWorker.Result doWork();

    public ForegroundInfo getForegroundInfo() {
        throw new IllegalStateException("Expedited WorkRequests require a Worker to provide an implementation for `getForegroundInfo()`");
    }

    @Override // androidx.work.ListenableWorker
    public ListenableFuture<ForegroundInfo> getForegroundInfoAsync() {
        return WorkerKt.access$future(getBackgroundExecutor(), new Worker$$ExternalSyntheticLambda1(this));
    }

    @Override // androidx.work.ListenableWorker
    public final ListenableFuture<ListenableWorker.Result> startWork() {
        return WorkerKt.access$future(getBackgroundExecutor(), new Worker$$ExternalSyntheticLambda0(this));
    }
}
