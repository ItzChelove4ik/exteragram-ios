package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.R$bool;
import androidx.work.impl.background.greedy.GreedyScheduler;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function6;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aá\u0001\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0097\u0001\b\u0002\u0010\u0012\u001a\u0090\u0001\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\fj\u0002`\u0011H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001aE\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0017\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u001b\u0010\u001c* \u0002\u0010\u001e\"\u008c\u0001\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\f2\u008c\u0001\u0012\u0013\u0012\u00110\u0000¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0001\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\f¨\u0006\u001f"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/Configuration;", "configuration", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "workTaskExecutor", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/impl/constraints/trackers/Trackers;", "trackers", "Landroidx/work/impl/Processor;", "processor", "Lkotlin/Function6;", "Lkotlin/ParameterName;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/Scheduler;", "Landroidx/work/impl/SchedulersCreator;", "schedulersCreator", "Landroidx/work/impl/WorkManagerImpl;", "createWorkManager", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;Lkotlin/jvm/functions/Function6;)Landroidx/work/impl/WorkManagerImpl;", "WorkManagerImpl", "createSchedulers", "(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;)Ljava/util/List;", "taskExecutor", "Lkotlinx/coroutines/CoroutineScope;", "createWorkManagerScope", "(Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)Lkotlinx/coroutines/CoroutineScope;", "WorkManagerScope", "SchedulersCreator", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class WorkManagerImplExtKt {
    public static final /* synthetic */ List access$createSchedulers(Context r0, Configuration r1, TaskExecutor r2, WorkDatabase r3, Trackers r4, Processor r5) {
        return createSchedulers(r0, r1, r2, r3, r4, r5);
    }

    private static final List<Scheduler> createSchedulers(Context r7, Configuration r8, TaskExecutor r9, WorkDatabase r10, Trackers r11, Processor r12) {
        return CollectionsKt.listOf(new Scheduler[]{Schedulers.createBestAvailableBackgroundScheduler(r7, r10, r8), new GreedyScheduler(r7, r8, r11, r12, new WorkLauncherImpl(r12, r9), r9)});
    }

    @JvmOverloads
    @JvmName(name = "createWorkManager")
    public static final WorkManagerImpl createWorkManager(Context r9, Configuration r10) {
        return createWorkManager$default(r9, r10, null, null, null, null, null, 124, null);
    }

    @JvmOverloads
    @JvmName(name = "createWorkManager")
    public static final WorkManagerImpl createWorkManager(Context r9, Configuration r10, TaskExecutor r11, WorkDatabase r12, Trackers r13, Processor r14, Function6<? super Context, ? super Configuration, ? super TaskExecutor, ? super WorkDatabase, ? super Trackers, ? super Processor, ? extends List<? extends Scheduler>> r15) {
        List<? extends Scheduler> r5 = r15.invoke(r9, r10, r11, r12, r13, r14);
        return new WorkManagerImpl(r9.getApplicationContext(), r10, r11, r12, r5, r14, r13);
    }

    public static /* synthetic */ WorkManagerImpl createWorkManager$default(Context r10, Configuration r11, TaskExecutor r12, WorkDatabase r13, Trackers r14, Processor r15, Function6 r16, int r17, Object r18) {
        if ((r17 & 4) == 0) goto L5;
        r12 = new WorkManagerTaskExecutor(r11.getTaskExecutor());
    L5:
        TaskExecutor r3 = r12;
        if ((r17 & 8) == 0) goto L9;
        r13 = WorkDatabase.Companion.create(r10.getApplicationContext(), r3.getSerialTaskExecutor(), r11.getClock(), r10.getResources().getBoolean(R$bool.workmanager_test_configuration));
    L9:
        if ((r17 & 16) == 0) goto L11;
        Trackers r5 = new Trackers(r10.getApplicationContext(), r3, null, null, null, null, 60, null);
    L13:
        if ((r17 & 32) == 0) goto L15;
        Processor r6 = new Processor(r10.getApplicationContext(), r11, r3, r13);
    L17:
        if ((r17 & 64) == 0) goto L20;
        Function6 r7 = WorkManagerImplExtKt$WorkManagerImpl$1.INSTANCE;
    L22:
        return createWorkManager(r10, r11, r3, r13, r5, r6, r7);
    L20:
        r7 = r16;
        goto L22
    L15:
        r6 = r15;
        goto L17
    L11:
        r5 = r14;
        goto L13
    }

    @JvmName(name = "createWorkManagerScope")
    public static final CoroutineScope createWorkManagerScope(TaskExecutor r0) {
        return CoroutineScopeKt.CoroutineScope(r0.getTaskCoroutineDispatcher());
    }
}
