package kotlinx.coroutines;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0002\u000e\u000fB\u001d\u0012\u0014\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0086@¢\u0006\u0002\u0010\rR\u001e\u0010\u0003\u001a\u0010\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\bR\t\u0010\t\u001a\u00020\nX\u0082\u0004¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/AwaitAll;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "deferreds", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Deferred;", "<init>", "([Lkotlinx/coroutines/Deferred;)V", "[Lkotlinx/coroutines/Deferred;", "notCompletedCount", "Lkotlinx/atomicfu/AtomicInt;", "await", _UrlKt.FRAGMENT_ENCODE_SET, "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DisposeHandlersOnCancel", "AwaitAllNode", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,121:1\n433#2,9:122\n442#2:133\n13805#3,2:131\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll\n*L\n63#1:122,9\n63#1:133\n75#1:131,2\n*E\n"})
/* loaded from: classes5.dex */
final class AwaitAll<T> {
    private static final /* synthetic */ AtomicIntegerFieldUpdater notCompletedCount$volatile$FU = null;
    private final Deferred<T>[] deferreds;
    private volatile /* synthetic */ int notCompletedCount$volatile;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016R\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001b\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0018\u00010\u000fR\b\u0012\u0004\u0012\u00028\u00000\u00100\u000eX\u0082\u0004R<\u0010\u0012\u001a\u000e\u0018\u00010\u000fR\b\u0012\u0004\u0012\u00028\u00000\u00102\u0012\u0010\u0011\u001a\u000e\u0018\u00010\u000fR\b\u0012\u0004\u0012\u00028\u00000\u00108F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/JobNode;", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlinx/coroutines/AwaitAll;Lkotlinx/coroutines/CancellableContinuation;)V", "handle", "Lkotlinx/coroutines/DisposableHandle;", "getHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "_disposer", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/AwaitAll;", "value", "disposer", "getDisposer", "()Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "setDisposer", "(Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;)V", "onCancelling", _UrlKt.FRAGMENT_ENCODE_SET, "getOnCancelling", "()Z", "invoke", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$AwaitAllNode\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,121:1\n11561#2:122\n11896#2,3:123\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$AwaitAllNode\n*L\n115#1:122\n115#1:123,3\n*E\n"})
    public final class AwaitAllNode extends JobNode {
        private static final /* synthetic */ AtomicReferenceFieldUpdater _disposer$volatile$FU = null;
        private volatile /* synthetic */ Object _disposer$volatile;
        private final CancellableContinuation<List<? extends T>> continuation;
        public DisposableHandle handle;
        final /* synthetic */ AwaitAll<T> this$0;

        static {
            _disposer$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(AwaitAllNode.class, Object.class, "_disposer$volatile");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AwaitAllNode(AwaitAll r1, CancellableContinuation<? super List<? extends T>> r2) {
            this.this$0 = r1;
            this.continuation = r2;
        }

        private static final /* synthetic */ AtomicReferenceFieldUpdater get_disposer$volatile$FU() {
            return _disposer$volatile$FU;
        }

        public final AwaitAll<T>.DisposeHandlersOnCancel getDisposer() {
            return (DisposeHandlersOnCancel) get_disposer$volatile$FU().get(this);
        }

        public final DisposableHandle getHandle() {
            DisposableHandle r0 = this.handle;
            if (r0 == null) goto L5;
            return r0;
        L5:
            return null;
        }

        @Override // kotlinx.coroutines.JobNode
        public boolean getOnCancelling() {
            return false;
        }

        @Override // kotlinx.coroutines.JobNode
        public void invoke(Throwable r5) {
            if (r5 == null) goto L10;
            Object r52 = this.continuation.tryResumeWithException(r5);
            if (r52 == null) goto L17;
            this.continuation.completeResume(r52);
            AwaitAll<T>.DisposeHandlersOnCancel r4 = getDisposer();
            if (r4 == null) goto L18;
            r4.disposeAll();
            return;
        L18:
            return;
        L17:
            return;
        L10:
            if (AwaitAll.access$getNotCompletedCount$volatile$FU().decrementAndGet(this.this$0) != 0) goto L19;
            CancellableContinuation<List<? extends T>> r53 = this.continuation;
            Deferred[] r42 = AwaitAll.access$getDeferreds$p(this.this$0);
            ArrayList r0 = new ArrayList(r42.length);
            int r1 = r42.length;
            int r2 = 0;
        L12:
            if (r2 >= r1) goto L14;
            r0.add(r42[r2].getCompleted());
            r2 = r2 + 1;
            goto L12
        L14:
            r53.resumeWith(Result.m2018constructorimpl(r0));
            return;
        }

        public final void setDisposer(AwaitAll<T>.DisposeHandlersOnCancel r2) {
            get_disposer$volatile$FU().set(this, r2);
        }

        public final void setHandle(DisposableHandle r1) {
            this.handle = r1;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016R \u0010\u0002\u001a\u0012\u0012\u000e\u0012\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u00050\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel;", "Lkotlinx/coroutines/CancelHandler;", "nodes", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "Lkotlinx/coroutines/AwaitAll;", "<init>", "(Lkotlinx/coroutines/AwaitAll;[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;)V", "[Lkotlinx/coroutines/AwaitAll$AwaitAllNode;", "disposeAll", _UrlKt.FRAGMENT_ENCODE_SET, "invoke", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAwait.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,121:1\n13805#2,2:122\n*S KotlinDebug\n*F\n+ 1 Await.kt\nkotlinx/coroutines/AwaitAll$DisposeHandlersOnCancel\n*L\n88#1:122,2\n*E\n"})
    public final class DisposeHandlersOnCancel implements CancelHandler {
        private final AwaitAll<T>.AwaitAllNode[] nodes;
        final /* synthetic */ AwaitAll<T> this$0;

        public DisposeHandlersOnCancel(AwaitAll r1, AwaitAll<T>.AwaitAllNode[] r2) {
            this.this$0 = r1;
            this.nodes = r2;
        }

        public final void disposeAll() {
            AwaitAll<T>.AwaitAllNode[] r3 = this.nodes;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].getHandle().dispose();
            r1 = r1 + 1;
            goto L3
        }

        @Override // kotlinx.coroutines.CancelHandler
        public void invoke(Throwable r1) {
            disposeAll();
        }

        public String toString() {
            return "DisposeHandlersOnCancel[" + this.nodes + ']';
        }
    }

    static {
        notCompletedCount$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(AwaitAll.class, "notCompletedCount$volatile");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AwaitAll(Deferred<? extends T>[] r1) {
        this.deferreds = r1;
        this.notCompletedCount$volatile = r1.length;
    }

    public static final /* synthetic */ Deferred[] access$getDeferreds$p(AwaitAll r0) {
        return r0.deferreds;
    }

    public static final /* synthetic */ AtomicIntegerFieldUpdater access$getNotCompletedCount$volatile$FU() {
        return getNotCompletedCount$volatile$FU();
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater getNotCompletedCount$volatile$FU() {
        return notCompletedCount$volatile$FU;
    }

    public final Object await(Continuation<? super List<? extends T>> r10) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r10), 1);
        r0.initCancellability();
        int r1 = access$getDeferreds$p(this).length;
        AwaitAllNode[] r3 = new AwaitAllNode[r1];
        int r4 = 0;
        int r5 = 0;
    L3:
        if (r5 >= r1) goto L5;
        Deferred r6 = access$getDeferreds$p(this)[r5];
        r6.start();
        AwaitAllNode r7 = new AwaitAllNode(this, r0);
        r7.setHandle(JobKt.invokeOnCompletion$default(r6, false, r7, 1, null));
        Unit r62 = Unit.INSTANCE;
        r3[r5] = r7;
        r5 = r5 + 1;
        goto L3
    L5:
        AwaitAll<T>.DisposeHandlersOnCancel r2 = new DisposeHandlersOnCancel(this, r3);
    L6:
        if (r4 >= r1) goto L9;
        r3[r4].setDisposer(r2);
        r4 = r4 + 1;
        goto L6
    L9:
        if (r0.isCompleted() == false) goto L11;
        r2.disposeAll();
    L12:
        Object r9 = r0.getResult();
        if (r9 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L15;
        DebugProbesKt.probeCoroutineSuspended(r10);
    L15:
        return r9;
    L11:
        CancellableContinuationKt.invokeOnCancellation(r0, r2);
        goto L12
    }
}
