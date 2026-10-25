package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\tR\u001a\u0010\b\u001a\u00020\u00078\u0010X\u0090\u0004¢\u0006\f\n\u0004\b\b\u0010\u000b\u001a\u0004\b\f\u0010\tR\u0014\u0010\u000e\u001a\u00020\u00078PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlinx/coroutines/JobImpl;", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/Job;", "parent", "<init>", "(Lkotlinx/coroutines/Job;)V", _UrlKt.FRAGMENT_ENCODE_SET, "handlesException", "()Z", "complete", "Z", "getHandlesException$kotlinx_coroutines_core", "getOnCancelComplete$kotlinx_coroutines_core", "onCancelComplete", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@PublishedApi
/* loaded from: classes.dex */
public class JobImpl extends JobSupport implements CompletableJob {
    private final boolean handlesException;

    public JobImpl(Job r2) {
        super(true);
        initParentJob(r2);
        this.handlesException = handlesException();
    }

    private final boolean handlesException() {
        ChildHandle r3 = getParentHandle$kotlinx_coroutines_core();
        if ((r3 instanceof ChildHandleNode) == false) goto L5;
        ChildHandleNode r32 = (ChildHandleNode) r3;
    L7:
        if (r32 == null) goto L22;
        JobSupport r33 = r32.getJob();
        if (r33 == null) goto L22;
    L12:
        if (r33.getHandlesException$kotlinx_coroutines_core() == true) goto L13;
        ChildHandle r34 = r33.getParentHandle$kotlinx_coroutines_core();
        if ((r34 instanceof ChildHandleNode) == false) goto L18;
        ChildHandleNode r35 = (ChildHandleNode) r34;
    L19:
        if (r35 == null) goto L22;
        r33 = r35.getJob();
        if (r33 != null) goto L12;
    L18:
        r35 = null;
        goto L19
    L13:
        return true;
    L22:
        return false;
    L5:
        r32 = null;
        goto L7
    }

    @Override // kotlinx.coroutines.CompletableJob
    public boolean complete() {
        return makeCompleting$kotlinx_coroutines_core(Unit.INSTANCE);
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean getHandlesException$kotlinx_coroutines_core() {
        return this.handlesException;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}
