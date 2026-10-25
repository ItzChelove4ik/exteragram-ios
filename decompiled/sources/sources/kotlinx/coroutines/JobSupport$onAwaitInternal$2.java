package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* synthetic */ class JobSupport$onAwaitInternal$2 extends FunctionReferenceImpl implements Function3<JobSupport, Object, Object, Object> {
    public static final JobSupport$onAwaitInternal$2 INSTANCE = null;

    static {
        INSTANCE = new JobSupport$onAwaitInternal$2();
    }

    public JobSupport$onAwaitInternal$2() {
        super(3, JobSupport.class, "onAwaitInternalProcessResFunc", "onAwaitInternalProcessResFunc(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", 0);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(JobSupport r1, Object r2, Object r3) {
        return invoke2(r1, r2, r3);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(JobSupport r1, Object r2, Object r3) {
        return JobSupport.access$onAwaitInternalProcessResFunc(r1, r2, r3);
    }
}
