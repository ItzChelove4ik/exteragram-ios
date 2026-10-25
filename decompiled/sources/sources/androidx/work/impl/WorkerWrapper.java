package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.util.Consumer;
import androidx.work.Clock;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.ForegroundUpdater;
import androidx.work.InputMerger;
import androidx.work.ListenableFutureKt;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkInfo$State;
import androidx.work.WorkerExceptionInfo;
import androidx.work.WorkerParameters;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.WorkForegroundUpdater;
import androidx.work.impl.utils.WorkProgressUpdater;
import androidx.work.impl.utils.WorkerExceptionUtilsKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001:\u0002@AB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*J\u000e\u0010,\u001a\u00020-H\u0082@¢\u0006\u0002\u0010.J\u0010\u0010/\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u000206H\u0007J\u0010\u00107\u001a\u00020+2\u0006\u00105\u001a\u000206H\u0002J\u0012\u00108\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u000101H\u0002J\b\u00109\u001a\u00020+H\u0002J\u0010\u0010:\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0007J\u0010\u0010;\u001a\u0002042\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010<\u001a\u00020+2\u0006\u00105\u001a\u000206H\u0002J\b\u0010=\u001a\u00020+H\u0002J\u0010\u0010>\u001a\u00020+2\u0006\u00100\u001a\u000201H\u0002J\u0016\u0010?\u001a\u00020\r2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0!H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(¨\u0006B"}, d2 = {"Landroidx/work/impl/WorkerWrapper;", _UrlKt.FRAGMENT_ENCODE_SET, "builder", "Landroidx/work/impl/WorkerWrapper$Builder;", "<init>", "(Landroidx/work/impl/WorkerWrapper$Builder;)V", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "appContext", "Landroid/content/Context;", "workSpecId", _UrlKt.FRAGMENT_ENCODE_SET, "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "builderWorker", "Landroidx/work/ListenableWorker;", "workTaskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "configuration", "Landroidx/work/Configuration;", "clock", "Landroidx/work/Clock;", "foregroundProcessor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "workSpecDao", "Landroidx/work/impl/model/WorkSpecDao;", "dependencyDao", "Landroidx/work/impl/model/DependencyDao;", "tags", _UrlKt.FRAGMENT_ENCODE_SET, "workDescription", "workerJob", "Lkotlinx/coroutines/CompletableJob;", "workGenerationalId", "Landroidx/work/impl/model/WorkGenerationalId;", "getWorkGenerationalId", "()Landroidx/work/impl/model/WorkGenerationalId;", "launch", "Lcom/google/common/util/concurrent/ListenableFuture;", _UrlKt.FRAGMENT_ENCODE_SET, "runWorker", "Landroidx/work/impl/WorkerWrapper$Resolution;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onWorkFinished", "result", "Landroidx/work/ListenableWorker$Result;", "onWorkFailed", "interrupt", _UrlKt.FRAGMENT_ENCODE_SET, "stopReason", _UrlKt.FRAGMENT_ENCODE_SET, "resetWorkerStatus", "handleResult", "trySetRunning", "setFailed", "iterativelyFailWorkAndDependents", "reschedule", "resetPeriodic", "setSucceeded", "createWorkDescription", "Resolution", "Builder", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWorkerWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapper\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n*L\n1#1,623:1\n29#2:624\n29#2:625\n27#2:626\n32#2:627\n24#2:628\n19#2:629\n19#2:630\n19#2:631\n24#2:632\n24#2:633\n24#2:634\n24#2:635\n19#2:636\n*S KotlinDebug\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapper\n*L\n203#1:624\n237#1:625\n312#1:626\n315#1:627\n344#1:628\n360#1:629\n369#1:630\n382#1:631\n390#1:632\n397#1:633\n400#1:634\n493#1:635\n148#1:636\n*E\n"})
/* loaded from: classes.dex */
public final class WorkerWrapper {
    private final Context appContext;
    private final ListenableWorker builderWorker;
    private final Clock clock;
    private final Configuration configuration;
    private final DependencyDao dependencyDao;
    private final ForegroundProcessor foregroundProcessor;
    private final WorkerParameters.RuntimeExtras runtimeExtras;
    private final List<String> tags;
    private final WorkDatabase workDatabase;
    private final String workDescription;
    private final WorkSpec workSpec;
    private final WorkSpecDao workSpecDao;
    private final String workSpecId;
    private final TaskExecutor workTaskExecutor;
    private final CompletableJob workerJob;

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001BG\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0015\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010&\u001a\u0004\b'\u0010(R\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010)\u001a\u0004\b*\u0010+R\u0017\u0010,\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R$\u00101\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006<"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "context", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "workTaskExecutor", "Landroidx/work/impl/foreground/ForegroundProcessor;", "foregroundProcessor", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/impl/model/WorkSpec;", "workSpec", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "tags", "<init>", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/foreground/ForegroundProcessor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/model/WorkSpec;Ljava/util/List;)V", "Landroidx/work/WorkerParameters$RuntimeExtras;", "runtimeExtras", "withRuntimeExtras", "(Landroidx/work/WorkerParameters$RuntimeExtras;)Landroidx/work/impl/WorkerWrapper$Builder;", "Landroidx/work/impl/WorkerWrapper;", "build", "()Landroidx/work/impl/WorkerWrapper;", "Landroidx/work/Configuration;", "getConfiguration", "()Landroidx/work/Configuration;", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "getWorkTaskExecutor", "()Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "Landroidx/work/impl/foreground/ForegroundProcessor;", "getForegroundProcessor", "()Landroidx/work/impl/foreground/ForegroundProcessor;", "Landroidx/work/impl/WorkDatabase;", "getWorkDatabase", "()Landroidx/work/impl/WorkDatabase;", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "Ljava/util/List;", "getTags", "()Ljava/util/List;", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "Landroidx/work/ListenableWorker;", "worker", "Landroidx/work/ListenableWorker;", "getWorker", "()Landroidx/work/ListenableWorker;", "setWorker", "(Landroidx/work/ListenableWorker;)V", "Landroidx/work/WorkerParameters$RuntimeExtras;", "getRuntimeExtras", "()Landroidx/work/WorkerParameters$RuntimeExtras;", "setRuntimeExtras", "(Landroidx/work/WorkerParameters$RuntimeExtras;)V", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Builder {
        private final Context appContext;
        private final Configuration configuration;
        private final ForegroundProcessor foregroundProcessor;
        private WorkerParameters.RuntimeExtras runtimeExtras;
        private final List<String> tags;
        private final WorkDatabase workDatabase;
        private final WorkSpec workSpec;
        private final TaskExecutor workTaskExecutor;
        private ListenableWorker worker;

        @SuppressLint({"LambdaLast"})
        public Builder(Context r1, Configuration r2, TaskExecutor r3, ForegroundProcessor r4, WorkDatabase r5, WorkSpec r6, List<String> r7) {
            this.configuration = r2;
            this.workTaskExecutor = r3;
            this.foregroundProcessor = r4;
            this.workDatabase = r5;
            this.workSpec = r6;
            this.tags = r7;
            this.appContext = r1.getApplicationContext();
            this.runtimeExtras = new WorkerParameters.RuntimeExtras();
        }

        public final WorkerWrapper build() {
            return new WorkerWrapper(this);
        }

        public final Context getAppContext() {
            return this.appContext;
        }

        public final Configuration getConfiguration() {
            return this.configuration;
        }

        public final ForegroundProcessor getForegroundProcessor() {
            return this.foregroundProcessor;
        }

        public final WorkerParameters.RuntimeExtras getRuntimeExtras() {
            return this.runtimeExtras;
        }

        public final List<String> getTags() {
            return this.tags;
        }

        public final WorkDatabase getWorkDatabase() {
            return this.workDatabase;
        }

        public final WorkSpec getWorkSpec() {
            return this.workSpec;
        }

        public final TaskExecutor getWorkTaskExecutor() {
            return this.workTaskExecutor;
        }

        public final ListenableWorker getWorker() {
            return this.worker;
        }

        public final Builder withRuntimeExtras(WorkerParameters.RuntimeExtras r1) {
            if (r1 == null) goto L4;
            this.runtimeExtras = r1;
        L4:
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ResetWorkerStatus", "Failed", "Finished", "Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static abstract class Resolution {

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Failed;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "<init>", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Failed extends Resolution {
            private final ListenableWorker.Result result;

            public Failed(ListenableWorker.Result r2) {
                super(null);
                this.result = r2;
            }

            public /* synthetic */ Failed(ListenableWorker.Result r1, int r2, DefaultConstructorMarker r3) {
                if ((r2 & 1) == 0) goto L5;
                r1 = new ListenableWorker.Result.Failure();
            L5:
                this(r1);
            }

            public final ListenableWorker.Result getResult() {
                return this.result;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$Finished;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "result", "Landroidx/work/ListenableWorker$Result;", "<init>", "(Landroidx/work/ListenableWorker$Result;)V", "getResult", "()Landroidx/work/ListenableWorker$Result;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Finished extends Resolution {
            private final ListenableWorker.Result result;

            public Finished(ListenableWorker.Result r2) {
                super(null);
                this.result = r2;
            }

            public final ListenableWorker.Result getResult() {
                return this.result;
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/WorkerWrapper$Resolution$ResetWorkerStatus;", "Landroidx/work/impl/WorkerWrapper$Resolution;", "reason", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "getReason", "()I", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class ResetWorkerStatus extends Resolution {
            private final int reason;

            public ResetWorkerStatus(int r2) {
                super(null);
                this.reason = r2;
            }

            public /* synthetic */ ResetWorkerStatus(int r1, int r2, DefaultConstructorMarker r3) {
                if ((r2 & 1) == 0) goto L5;
                r1 = -256;
            L5:
                this(r1);
            }

            public final int getReason() {
                return this.reason;
            }
        }

        private Resolution() {
        }

        public /* synthetic */ Resolution(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.WorkerWrapper$launch$1", f = "WorkerWrapper.kt", i = {}, l = {98}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nWorkerWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapper$launch$1\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n*L\n1#1,623:1\n32#2:624\n*S KotlinDebug\n*F\n+ 1 WorkerWrapper.kt\nandroidx/work/impl/WorkerWrapper$launch$1\n*L\n105#1:624\n*E\n"})
    /* renamed from: androidx.work.impl.WorkerWrapper$launch$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        int label;
        final /* synthetic */ WorkerWrapper this$0;

        public static Boolean $r8$lambda$Y7GYLbRKyArUGZkgQLkfflh3BxY(Resolution r1, WorkerWrapper r2) {
            if ((r1 instanceof Resolution.Finished) == false) goto L6;
            boolean r12 = WorkerWrapper.access$onWorkFinished(r2, ((Resolution.Finished) r1).getResult());
        L12:
            return Boolean.valueOf(r12);
        L6:
            if ((r1 instanceof Resolution.Failed) == false) goto L9;
            r12 = WorkerWrapper.access$onWorkFailed(r2, ((Resolution.Failed) r1).getResult());
            goto L12
        L9:
            if ((r1 instanceof Resolution.ResetWorkerStatus) == false) goto L13;
            r12 = WorkerWrapper.access$resetWorkerStatus(r2, ((Resolution.ResetWorkerStatus) r1).getReason());
            goto L12
        L13:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        }

        public AnonymousClass1(WorkerWrapper r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Boolean> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L13;
            if (r1 != 1) goto L11;
            ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L7 WorkerStoppedException -> L9 CancellationException -> L20
        L17:
            Resolution r62 = (Resolution) r6;     // Catch: Throwable -> L7 WorkerStoppedException -> L9 CancellationException -> L20
        L23:
            return WorkerWrapper.access$getWorkDatabase$p(this.this$0).runInTransaction(new WorkerWrapper$launch$1$$ExternalSyntheticLambda0(r62, this.this$0));
        L11:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L13:
            ResultKt.throwOnFailure(r6);
            CompletableJob r63 = WorkerWrapper.access$getWorkerJob$p(this.this$0);     // Catch: Throwable -> L7 WorkerStoppedException -> L9 CancellationException -> L20
            WorkerWrapper$launch$1$resolution$1 r12 = new WorkerWrapper$launch$1$resolution$1(this.this$0, null);     // Catch: Throwable -> L7 WorkerStoppedException -> L9 CancellationException -> L20
            this.label = 1;     // Catch: Throwable -> L7 WorkerStoppedException -> L9 CancellationException -> L20
            r6 = BuildersKt.withContext(r63, r12, this);     // Catch: Throwable -> L7 WorkerStoppedException -> L9 CancellationException -> L20
            if (r6 != r0) goto L17;
            return r0;
        L9:
            e = move-exception;
            r62 = new Resolution.ResetWorkerStatus(e.getReason());
        L20:
            r62 = new Resolution.Failed(null, 1, null);
        L7:
            th = move-exception;
            String r02 = WorkerWrapperKt.access$getTAG$p();
            Logger.get().error(r02, "Unexpected error in WorkerWrapper", th);
            r62 = new Resolution.Failed(null, 1, null);
            goto L23
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.work.impl.WorkerWrapper", f = "WorkerWrapper.kt", i = {0}, l = {296}, m = "runWorker", n = {PluginsConstants.PARAMS}, s = {"L$0"})
    /* renamed from: androidx.work.impl.WorkerWrapper$runWorker$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes4.dex */
    public static final class C01291 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ WorkerWrapper this$0;

        public C01291(WorkerWrapper r1, Continuation<? super C01291> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return WorkerWrapper.access$runWorker(this.this$0, this);
        }
    }

    public static Unit $r8$lambda$a0mi6STqFu3M5cm3Gtv6LbwfItA(ListenableWorker r1, boolean r2, String r3, WorkerWrapper r4, Throwable r5) {
        if ((r5 instanceof WorkerStoppedException) == false) goto L5;
        r1.stop(((WorkerStoppedException) r5).getReason());
    L5:
        if (r2 == false) goto L9;
        if (r3 == null) goto L9;
        r4.configuration.getTracer().endAsyncSection(r3, r4.workSpec.hashCode());
    L9:
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$j68Df9ndtTL98ATtdE-28ReOAb4, reason: not valid java name */
    public static Boolean m980$r8$lambda$j68Df9ndtTL98ATtdE28ReOAb4(WorkerWrapper r3) {
        if (r3.workSpecDao.getState(r3.workSpecId) != WorkInfo$State.ENQUEUED) goto L5;
        r3.workSpecDao.setState(WorkInfo$State.RUNNING, r3.workSpecId);
        r3.workSpecDao.incrementWorkSpecRunAttemptCount(r3.workSpecId);
        r3.workSpecDao.setStopReason(r3.workSpecId, -256);
        boolean r32 = true;
    L7:
        return Boolean.valueOf(r32);
    L5:
        r32 = false;
        goto L7
    }

    /* renamed from: $r8$lambda$pgTj0TbLDPXkl5H_yrOVgH1dL-U, reason: not valid java name */
    public static Boolean m981$r8$lambda$pgTj0TbLDPXkl5H_yrOVgH1dLU(WorkerWrapper r4) {
        WorkSpec r0 = r4.workSpec;
        if (r0.state == WorkInfo$State.ENQUEUED) goto L7;
        String r02 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().debug(r02, r4.workSpec.workerClassName + " is not in ENQUEUED state. Nothing more to do");
        return Boolean.TRUE;
    L7:
        if (r0.isPeriodic() == true) goto L11;
        if (r4.workSpec.isBackedOff() == true) goto L11;
    L15:
        return Boolean.FALSE;
    L11:
        if (r4.clock.currentTimeMillis() >= r4.workSpec.calculateNextRunTime()) goto L15;
        Logger.get().debug(WorkerWrapperKt.access$getTAG$p(), "Delaying execution for " + r4.workSpec.workerClassName + " because it is being executed before schedule.");
        return Boolean.TRUE;
    }

    public WorkerWrapper(Builder r3) {
        WorkSpec r0 = r3.getWorkSpec();
        this.workSpec = r0;
        this.appContext = r3.getAppContext();
        this.workSpecId = r0.id;
        this.runtimeExtras = r3.getRuntimeExtras();
        this.builderWorker = r3.getWorker();
        this.workTaskExecutor = r3.getWorkTaskExecutor();
        Configuration r02 = r3.getConfiguration();
        this.configuration = r02;
        this.clock = r02.getClock();
        this.foregroundProcessor = r3.getForegroundProcessor();
        WorkDatabase r03 = r3.getWorkDatabase();
        this.workDatabase = r03;
        this.workSpecDao = r03.workSpecDao();
        this.dependencyDao = r03.dependencyDao();
        List<String> r32 = r3.getTags();
        this.tags = r32;
        this.workDescription = createWorkDescription(r32);
        this.workerJob = JobKt.Job$default(null, 1, null);
    }

    public static final /* synthetic */ Context access$getAppContext$p(WorkerWrapper r0) {
        return r0.appContext;
    }

    public static final /* synthetic */ WorkDatabase access$getWorkDatabase$p(WorkerWrapper r0) {
        return r0.workDatabase;
    }

    public static final /* synthetic */ TaskExecutor access$getWorkTaskExecutor$p(WorkerWrapper r0) {
        return r0.workTaskExecutor;
    }

    public static final /* synthetic */ CompletableJob access$getWorkerJob$p(WorkerWrapper r0) {
        return r0.workerJob;
    }

    public static final /* synthetic */ boolean access$onWorkFailed(WorkerWrapper r0, ListenableWorker.Result r1) {
        return r0.onWorkFailed(r1);
    }

    public static final /* synthetic */ boolean access$onWorkFinished(WorkerWrapper r0, ListenableWorker.Result r1) {
        return r0.onWorkFinished(r1);
    }

    public static final /* synthetic */ boolean access$resetWorkerStatus(WorkerWrapper r0, int r1) {
        return r0.resetWorkerStatus(r1);
    }

    public static final /* synthetic */ Object access$runWorker(WorkerWrapper r0, Continuation r1) {
        return r0.runWorker(r1);
    }

    private final String createWorkDescription(List<String> r11) {
        return "Work [ id=" + this.workSpecId + ", tags={ " + CollectionsKt.joinToString$default(r11, ",", null, null, 0, null, null, 62, null) + " } ]";
    }

    private final boolean handleResult(ListenableWorker.Result r5) {
        if ((r5 instanceof ListenableWorker.Result.Success) == false) goto L11;
        String r0 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().info(r0, "Worker result SUCCESS for " + this.workDescription);
        if (this.workSpec.isPeriodic() == false) goto L9;
        return resetPeriodic();
    L9:
        return setSucceeded(r5);
    L11:
        if ((r5 instanceof ListenableWorker.Result.Retry) == false) goto L14;
        String r52 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().info(r52, "Worker result RETRY for " + this.workDescription);
        return reschedule(-256);
    L14:
        String r02 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().info(r02, "Worker result FAILURE for " + this.workDescription);
        if (this.workSpec.isPeriodic() == true) goto L17;
        if (r5 != null) goto L21;
        r5 = new ListenableWorker.Result.Failure();
    L21:
        return setFailed(r5);
    L17:
        return resetPeriodic();
    }

    private final void iterativelyFailWorkAndDependents(String r4) {
        List r42 = CollectionsKt.mutableListOf(new String[]{r4});
    L4:
        if (r42.isEmpty() == true) goto L9;
        String r0 = (String) CollectionsKt.removeLast(r42);
        if (this.workSpecDao.getState(r0) == WorkInfo$State.CANCELLED) goto L8;
        this.workSpecDao.setState(WorkInfo$State.FAILED, r0);
    L8:
        r42.addAll(this.dependencyDao.getDependentWorkIds(r0));
        goto L4
    }

    private final boolean onWorkFailed(ListenableWorker.Result r5) {
        String r0 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().info(r0, "Worker result FAILURE for " + this.workDescription);
        if (this.workSpec.isPeriodic() == false) goto L5;
        resetPeriodic();
        return false;
    L5:
        setFailed(r5);
        return false;
    }

    private final boolean onWorkFinished(ListenableWorker.Result r4) {
        WorkInfo$State r0 = this.workSpecDao.getState(this.workSpecId);
        this.workDatabase.workProgressDao().delete(this.workSpecId);
        if (r0 != null) goto L6;
        return false;
    L6:
        if (r0 != WorkInfo$State.RUNNING) goto L10;
        return handleResult(r4);
    L10:
        if (r0.isFinished() == false) goto L12;
        return false;
    L12:
        return reschedule(-512);
    }

    private final boolean reschedule(int r5) {
        this.workSpecDao.setState(WorkInfo$State.ENQUEUED, this.workSpecId);
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, this.clock.currentTimeMillis());
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1);
        this.workSpecDao.setStopReason(this.workSpecId, r5);
        return true;
    }

    private final boolean resetPeriodic() {
        this.workSpecDao.setLastEnqueueTime(this.workSpecId, this.clock.currentTimeMillis());
        this.workSpecDao.setState(WorkInfo$State.ENQUEUED, this.workSpecId);
        this.workSpecDao.resetWorkSpecRunAttemptCount(this.workSpecId);
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.incrementPeriodCount(this.workSpecId);
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1);
        return false;
    }

    private final boolean resetWorkerStatus(int r8) {
        if (Intrinsics.areEqual(this.workSpec.getBackOffOnSystemInterruptions(), Boolean.TRUE) == false) goto L6;
        String r0 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().debug(r0, "Worker " + this.workSpec.workerClassName + " was interrupted. Backing off.");
        reschedule(r8);
        return true;
    L6:
        WorkInfo$State r02 = this.workSpecDao.getState(this.workSpecId);
        if (r02 != null) goto L9;
    L12:
        String r82 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().debug(r82, "Status for " + this.workSpecId + " is " + r02 + " ; not doing any work");
        return false;
    L9:
        if (r02.isFinished() == true) goto L12;
        String r4 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().debug(r4, "Status for " + this.workSpecId + " is " + r02 + "; not doing any work and rescheduling for later execution");
        this.workSpecDao.setState(WorkInfo$State.ENQUEUED, this.workSpecId);
        this.workSpecDao.setStopReason(this.workSpecId, r8);
        this.workSpecDao.markWorkSpecScheduled(this.workSpecId, -1);
        return true;
    }

    private final Object runWorker(Continuation<? super Resolution> r23) {
        if ((r23 instanceof C01291) == false) goto L7;
        C01291 r2 = (C01291) r23;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.label = r3 - Integer.MIN_VALUE;
    L8:
        Object r0 = r2.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.label;
        if (r4 == 0) goto L20;
        if (r4 != 1) goto L18;
        WorkerParameters r22 = (WorkerParameters) r2.L$0;
        ResultKt.throwOnFailure(r0);     // Catch: Throwable -> L14 CancellationException -> L16
    L60:
        return new Resolution.Finished((ListenableWorker.Result) r0);
    L14:
        th = th;
    L64:
        String r33 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().error(r33, this.workDescription + " failed because it threw an exception/error", th);
        Consumer<WorkerExceptionInfo> r34 = this.configuration.getWorkerExecutionExceptionHandler();
        if (r34 == null) goto L68;
        WorkerExceptionUtilsKt.safeAccept(r34, new WorkerExceptionInfo(this.workSpec.workerClassName, r22, th), WorkerWrapperKt.access$getTAG$p());
    L68:
        return new Resolution.Failed(null, 1, null);
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        ResultKt.throwOnFailure(r0);
        boolean r02 = this.configuration.getTracer().isEnabled();
        String r42 = this.workSpec.getTraceTag();
        if (r02 == false) goto L25;
        if (r42 == null) goto L25;
        this.configuration.getTracer().beginAsyncSection(r42, this.workSpec.hashCode());
    L25:
        if (((Boolean) this.workDatabase.runInTransaction(new WorkerWrapper$$ExternalSyntheticLambda0(this))).booleanValue() == false) goto L29;
        return new Resolution.ResetWorkerStatus(0, 1, null);
    L29:
        if (this.workSpec.isPeriodic() == false) goto L32;
        Data r7 = this.workSpec.input;
    L31:
        Data r11 = r7;
        UUID r10 = UUID.fromString(this.workSpecId);
        List<String> r12 = this.tags;
        WorkerParameters.RuntimeExtras r13 = this.runtimeExtras;
        WorkSpec r72 = this.workSpec;
        WorkerParameters r9 = new WorkerParameters(r10, r11, r12, r13, r72.runAttemptCount, r72.getGeneration(), this.configuration.getExecutor(), this.configuration.getWorkerCoroutineContext(), this.workTaskExecutor, this.configuration.getWorkerFactory(), new WorkProgressUpdater(this.workDatabase, this.workTaskExecutor), new WorkForegroundUpdater(this.workDatabase, this.foregroundProcessor, this.workTaskExecutor));
        ListenableWorker r5 = this.builderWorker;
        if (r5 == null) goto L75;
    L47:
        r5.setUsed();
        Job r6 = (Job) r2.getContext().get(Job.Key);
        r6.invokeOnCompletion(new WorkerWrapper$$ExternalSyntheticLambda1(r5, r02, r42, this));
        if (trySetRunning() == true) goto L52;
        return new Resolution.ResetWorkerStatus(0, 1, null);
    L52:
        if (r6.isCancelled() == true) goto L54;
        ForegroundUpdater r03 = r9.getForegroundUpdater();
        CoroutineDispatcher r43 = ExecutorsKt.from(this.workTaskExecutor.getMainThreadExecutor());
        WorkerWrapper$runWorker$result$1 r62 = new WorkerWrapper$runWorker$result$1(this, r5, r03, null);     // Catch: CancellationException -> L16 Throwable -> L62
        r2.L$0 = r9;     // Catch: CancellationException -> L16 Throwable -> L62
        r2.label = 1;     // Catch: CancellationException -> L16 Throwable -> L62
        r0 = BuildersKt.withContext(r43, r62, r2);     // Catch: CancellationException -> L16 Throwable -> L62
        if (r0 != r32) goto L59;
        return r32;
    L59:
        r22 = r9;
    L62:
        th = th;
        r22 = r9;
        goto L64
    L54:
        return new Resolution.ResetWorkerStatus(0, 1, null);
    L75:
        r5 = this.configuration.getWorkerFactory().createWorkerWithDefaultFallback(this.appContext, this.workSpec.workerClassName, r9);     // Catch: Throwable -> L41
    L41:
        th = move-exception;
        String r24 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().error(r24, "Could not create Worker " + this.workSpec.workerClassName);
        Consumer<WorkerExceptionInfo> r25 = this.configuration.getWorkerInitializationExceptionHandler();
        if (r25 == null) goto L46;
        WorkerExceptionUtilsKt.safeAccept(r25, new WorkerExceptionInfo(this.workSpec.workerClassName, r9, th), WorkerWrapperKt.access$getTAG$p());
    L46:
        return new Resolution.Failed(null, 1, null);
    L32:
        InputMerger r73 = this.configuration.getInputMergerFactory().createInputMergerWithDefaultFallback(this.workSpec.inputMergerClassName);
        if (r73 != null) goto L36;
        String r04 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().error(r04, "Could not create Input Merger " + this.workSpec.inputMergerClassName);
        return new Resolution.Failed(null, 1, null);
    L36:
        r7 = r73.merge(CollectionsKt.plus(CollectionsKt.listOf(this.workSpec.input), this.workSpecDao.getInputsFromPrerequisites(this.workSpecId)));
    L16:
        e = move-exception;
        String r26 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().info(r26, this.workDescription + " was cancelled", e);
        throw e;
    L7:
        r2 = new C01291(this, r23);
        goto L8
    }

    private final boolean setSucceeded(ListenableWorker.Result r8) {
        this.workSpecDao.setState(WorkInfo$State.SUCCEEDED, this.workSpecId);
        this.workSpecDao.setOutput(this.workSpecId, ((ListenableWorker.Result.Success) r8).getOutputData());
        long r0 = this.clock.currentTimeMillis();
        Iterator<String> r82 = this.dependencyDao.getDependentWorkIds(this.workSpecId).iterator();
    L4:
        if (r82.hasNext() == false) goto L10;
        String r2 = r82.next();
        if (this.workSpecDao.getState(r2) != WorkInfo$State.BLOCKED) goto L4;
        if (this.dependencyDao.hasCompletedAllPrerequisites(r2) == false) goto L4;
        String r3 = WorkerWrapperKt.access$getTAG$p();
        Logger.get().info(r3, "Setting status to enqueued for " + r2);
        this.workSpecDao.setState(WorkInfo$State.ENQUEUED, r2);
        this.workSpecDao.setLastEnqueueTime(r2, r0);
        goto L4
    L10:
        return false;
    }

    private final boolean trySetRunning() {
        return ((Boolean) this.workDatabase.runInTransaction(new WorkerWrapper$$ExternalSyntheticLambda2(this))).booleanValue();
    }

    public final WorkGenerationalId getWorkGenerationalId() {
        return WorkSpecKt.generationalId(this.workSpec);
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }

    public final void interrupt(int r2) {
        this.workerJob.cancel(new WorkerStoppedException(r2));
    }

    public final ListenableFuture<Boolean> launch() {
        return ListenableFutureKt.launchFuture$default(this.workTaskExecutor.getTaskCoroutineDispatcher().plus(JobKt.Job$default(null, 1, null)), null, new AnonymousClass1(this, null), 2, null);
    }

    public final boolean setFailed(ListenableWorker.Result r4) {
        iterativelyFailWorkAndDependents(this.workSpecId);
        Data r42 = ((ListenableWorker.Result.Failure) r4).getOutputData();
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.getNextScheduleTimeOverrideGeneration());
        this.workSpecDao.setOutput(this.workSpecId, r42);
        return false;
    }
}
