package androidx.work.impl;

import android.content.Context;
import androidx.work.Configuration;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* synthetic */ class WorkManagerImplExtKt$WorkManagerImpl$1 extends FunctionReferenceImpl implements Function6<Context, Configuration, TaskExecutor, WorkDatabase, Trackers, Processor, List<? extends Scheduler>> {
    public static final WorkManagerImplExtKt$WorkManagerImpl$1 INSTANCE = null;

    static {
        INSTANCE = new WorkManagerImplExtKt$WorkManagerImpl$1();
    }

    public WorkManagerImplExtKt$WorkManagerImpl$1() {
        super(6, WorkManagerImplExtKt.class, "createSchedulers", "createSchedulers(Landroid/content/Context;Landroidx/work/Configuration;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/WorkDatabase;Landroidx/work/impl/constraints/trackers/Trackers;Landroidx/work/impl/Processor;)Ljava/util/List;", 1);
    }

    @Override // kotlin.jvm.functions.Function6
    public /* bridge */ /* synthetic */ List<? extends Scheduler> invoke(Context r1, Configuration r2, TaskExecutor r3, WorkDatabase r4, Trackers r5, Processor r6) {
        return invoke2(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final List<Scheduler> invoke2(Context r1, Configuration r2, TaskExecutor r3, WorkDatabase r4, Trackers r5, Processor r6) {
        return WorkManagerImplExtKt.access$createSchedulers(r1, r2, r3, r4, r5, r6);
    }
}
