package kotlinx.coroutines;

import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\u001a%\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u0019\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u001b\u0010\r\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\f\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0014\u0010\u0010\u001a\u00020\u000f*\u00020\u0000H\u0086@¢\u0006\u0004\b\u0010\u0010\u0011\u001a#\u0010\u0016\u001a\u00020\u000f*\u00020\u00122\u0010\b\u0002\u0010\u0015\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0011\u0010\u0018\u001a\u00020\u000f*\u00020\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0011\u0010\u0018\u001a\u00020\u000f*\u00020\u0012¢\u0006\u0004\b\u0018\u0010\u001a\u001a%\u0010\u0016\u001a\u00020\u000f*\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001b2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u001d¢\u0006\u0004\b\u0016\u0010\u001e\u001a#\u0010\u001f\u001a\u00020\u000f*\u00020\u00122\u0010\b\u0002\u0010\u0015\u001a\n\u0018\u00010\u0013j\u0004\u0018\u0001`\u0014¢\u0006\u0004\b\u001f\u0010\u0017\"\u0015\u0010\"\u001a\u00020\u0000*\u00020\u00128F¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006#"}, d2 = {"Lkotlinx/coroutines/Job;", _UrlKt.FRAGMENT_ENCODE_SET, "invokeImmediately", "Lkotlinx/coroutines/JobNode;", "handler", "Lkotlinx/coroutines/DisposableHandle;", "invokeOnCompletion", "(Lkotlinx/coroutines/Job;ZLkotlinx/coroutines/JobNode;)Lkotlinx/coroutines/DisposableHandle;", "parent", "Lkotlinx/coroutines/CompletableJob;", "Job", "(Lkotlinx/coroutines/Job;)Lkotlinx/coroutines/CompletableJob;", "handle", "disposeOnCompletion", "(Lkotlinx/coroutines/Job;Lkotlinx/coroutines/DisposableHandle;)Lkotlinx/coroutines/DisposableHandle;", _UrlKt.FRAGMENT_ENCODE_SET, "cancelAndJoin", "(Lkotlinx/coroutines/Job;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cause", "cancel", "(Lkotlin/coroutines/CoroutineContext;Ljava/util/concurrent/CancellationException;)V", "ensureActive", "(Lkotlinx/coroutines/Job;)V", "(Lkotlin/coroutines/CoroutineContext;)V", _UrlKt.FRAGMENT_ENCODE_SET, "message", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlinx/coroutines/Job;Ljava/lang/String;Ljava/lang/Throwable;)V", "cancelChildren", "getJob", "(Lkotlin/coroutines/CoroutineContext;)Lkotlinx/coroutines/Job;", "job", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/JobKt")
@SourceDebugExtension({"SMAP\nJob.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Job.kt\nkotlinx/coroutines/JobKt__JobKt\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,692:1\n1321#2,2:693\n1321#2,2:695\n1321#2,2:697\n1321#2,2:699\n*S KotlinDebug\n*F\n+ 1 Job.kt\nkotlinx/coroutines/JobKt__JobKt\n*L\n520#1:693,2\n534#1:695,2\n628#1:697,2\n652#1:699,2\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class JobKt__JobKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.JobKt__JobKt$invokeOnCompletion$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Throwable, Unit> {
        public AnonymousClass1(Object r8) {
            super(1, r8, JobNode.class, "invoke", "invoke(Ljava/lang/Throwable;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
            invoke2(r1);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable r1) {
            ((JobNode) this.receiver).invoke(r1);
        }
    }

    public static final CompletableJob Job(Job r1) {
        return new JobImpl(r1);
    }

    public static /* synthetic */ CompletableJob Job$default(Job r0, int r1, Object r2) {
        if ((r1 & 1) == 0) goto L6;
        r0 = null;
    L6:
        return JobKt.Job(r0);
    }

    public static final void cancel(CoroutineContext r1, CancellationException r2) {
        Job r12 = (Job) r1.get(Job.Key);
        if (r12 == null) goto L6;
        r12.cancel(r2);
        return;
    }

    public static final void cancel(Job r0, String r1, Throwable r2) {
        r0.cancel(ExceptionsKt.CancellationException(r1, r2));
    }

    public static /* synthetic */ void cancel$default(Job r0, String r1, Throwable r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        JobKt.cancel(r0, r1, r2);
    }

    public static final Object cancelAndJoin(Job r2, Continuation<? super Unit> r3) {
        Job.cancel$default(r2, null, 1, null);
        Object r22 = r2.join(r3);
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r22;
    L6:
        return Unit.INSTANCE;
    }

    public static final void cancelChildren(CoroutineContext r1, CancellationException r2) {
        Job r12 = (Job) r1.get(Job.Key);
        if (r12 == null) goto L10;
        Sequence<Job> r13 = r12.getChildren();
        if (r13 == null) goto L12;
        Iterator<Job> r14 = r13.iterator();
    L8:
        if (r14.hasNext() == false) goto L13;
        r14.next().cancel(r2);
        goto L8
    L13:
        return;
    L12:
        return;
    }

    public static /* synthetic */ void cancelChildren$default(CoroutineContext r0, CancellationException r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = null;
    L5:
        JobKt.cancelChildren(r0, r1);
    }

    public static final DisposableHandle disposeOnCompletion(Job r3, DisposableHandle r4) {
        return JobKt.invokeOnCompletion$default(r3, false, new DisposeOnCompletion(r4), 1, null);
    }

    public static final void ensureActive(CoroutineContext r1) {
        Job r12 = (Job) r1.get(Job.Key);
        if (r12 == null) goto L6;
        JobKt.ensureActive(r12);
        return;
    }

    public static final void ensureActive(Job r1) {
        if (r1.isActive() == false) goto L6;
        return;
    L6:
        throw r1.getCancellationException();
    }

    public static final Job getJob(CoroutineContext r1) {
        Job r0 = (Job) r1.get(Job.Key);
        if (r0 == null) goto L5;
        return r0;
    L5:
        DurationKt$$ExternalSyntheticBUOutline0.m("Current context doesn't contain Job in it: ", r1);
        return null;
    }

    public static final DisposableHandle invokeOnCompletion(Job r2, boolean r3, JobNode r4) {
        if ((r2 instanceof JobSupport) == false) goto L7;
        return ((JobSupport) r2).invokeOnCompletionInternal$kotlinx_coroutines_core(r3, r4);
    L7:
        return r2.invokeOnCompletion(r4.getOnCancelling(), r3, new AnonymousClass1(r4));
    }

    public static /* synthetic */ DisposableHandle invokeOnCompletion$default(Job r0, boolean r1, JobNode r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = true;
    L6:
        return JobKt.invokeOnCompletion(r0, r1, r2);
    }
}
