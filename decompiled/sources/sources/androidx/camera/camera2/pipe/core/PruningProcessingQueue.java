package androidx.camera.camera2.pipe.core;

import com.android.dx.io.Opcodes;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.selects.SelectImplementation;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 )*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001)Bm\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u001a\b\u0002\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u001a\b\u0002\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0004\u0012\u00020\u00070\u0005\u0012\"\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0082@¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0015\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00028\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR&\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001eR&\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t\u0012\u0004\u0012\u00020\u00070\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u001eR0\u0010\r\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006*"}, d2 = {"Landroidx/camera/camera2/pipe/core/PruningProcessingQueue;", "T", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "prune", _UrlKt.FRAGMENT_ENCODE_SET, "onUnprocessedElements", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "process", "<init>", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", _UrlKt.FRAGMENT_ENCODE_SET, "processingLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "cause", "closeAndReleaseUnprocessedElements", "(Ljava/lang/Throwable;)V", "element", _UrlKt.FRAGMENT_ENCODE_SET, "tryEmit", "(Ljava/lang/Object;)Z", "I", "getCapacity", "()I", "Lkotlin/jvm/functions/Function1;", "Lkotlin/jvm/functions/Function2;", "Lkotlinx/atomicfu/AtomicBoolean;", "started", "Lkotlinx/atomicfu/AtomicBoolean;", "Lkotlinx/coroutines/channels/Channel;", "channel", "Lkotlinx/coroutines/channels/Channel;", "Lkotlin/collections/ArrayDeque;", "queue", "Lkotlin/collections/ArrayDeque;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPruningProcessingQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PruningProcessingQueue.kt\nandroidx/camera/camera2/pipe/core/PruningProcessingQueue\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,213:1\n1#2:214\n*E\n"})
/* loaded from: classes3.dex */
public final class PruningProcessingQueue<T> {
    public static final Companion Companion = null;
    private final int capacity;
    private final Channel<T> channel;
    private final Function1<List<? extends T>, Unit> onUnprocessedElements;
    private final Function2<T, Continuation<? super Unit>, Object> process;
    private final Function1<List<T>, Unit> prune;
    private final ArrayDeque<T> queue;
    private final AtomicBoolean started;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/core/PruningProcessingQueue$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "processIn", "Landroidx/camera/camera2/pipe/core/PruningProcessingQueue;", "T", "scope", "Lkotlinx/coroutines/CoroutineScope;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final <T> PruningProcessingQueue<T> processIn(PruningProcessingQueue<T> r7, CoroutineScope r8) {
            if (PruningProcessingQueue.access$getStarted$p(r7).compareAndSet(false, true) == true) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("PruningProcessingQueue cannot be re-started!");
            return null;
        L5:
            if (BuildersKt.launch$default(r8, null, null, new PruningProcessingQueue$Companion$processIn$job$1(r7, null), 3, null).isCancelled() == false) goto L7;
            PruningProcessingQueue.access$closeAndReleaseUnprocessedElements(r7, null);
        L7:
            return r7;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.core.PruningProcessingQueue$processingLoop$2", f = "PruningProcessingQueue.kt", i = {0, 0}, l = {Opcodes.MUL_INT_LIT8}, m = "invokeSuspend", n = {"$this$supervisorScope", "processDeferred"}, s = {"L$0", "L$1"}, v = 1)
    @SourceDebugExtension({"SMAP\nPruningProcessingQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PruningProcessingQueue.kt\nandroidx/camera/camera2/pipe/core/PruningProcessingQueue$processingLoop$2\n+ 2 Select.kt\nkotlinx/coroutines/selects/SelectKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,213:1\n54#2,5:214\n50#3,2:219\n86#3,2:221\n59#3,2:223\n1#4:225\n*S KotlinDebug\n*F\n+ 1 PruningProcessingQueue.kt\nandroidx/camera/camera2/pipe/core/PruningProcessingQueue$processingLoop$2\n*L\n120#1:214,5\n139#1:219,2\n142#1:221,2\n156#1:223,2\n*E\n"})
    /* renamed from: androidx.camera.camera2.pipe.core.PruningProcessingQueue$processingLoop$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ PruningProcessingQueue<T> this$0;

        public AnonymousClass2(PruningProcessingQueue<T> r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass2 r0 = new AnonymousClass2(this.this$0, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference failed for: r6v3, types: [T, kotlinx.coroutines.Deferred, kotlinx.coroutines.Job] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x006e -> B:21:0x0071). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r12) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L12;
            if (r1 != 1) goto L10;
            Ref.ObjectRef r13 = (Ref.ObjectRef) this.L$1;
            CoroutineScope r5 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(r12);     // Catch: Throwable -> L8 CancellationException -> L36
        L22:
            if (PruningProcessingQueue.access$getQueue$p(this.this$0).isEmpty() == false) goto L24;
        L14:
            if (CoroutineScopeKt.isActive(r5) == false) goto L31;
            PruningProcessingQueue<T> r122 = this.this$0;     // Catch: Throwable -> L8 CancellationException -> L36
            SelectImplementation r6 = new SelectImplementation(getContext());     // Catch: Throwable -> L8 CancellationException -> L36
            r6.invoke(PruningProcessingQueue.access$getChannel$p(r122).getOnReceive(), new PruningProcessingQueue$processingLoop$2$1$1(r122, null));     // Catch: Throwable -> L8 CancellationException -> L36
            Deferred r123 = (Deferred) r13.element;     // Catch: Throwable -> L8 CancellationException -> L36
            if (r123 == null) goto L18;
            r6.invoke(r123.getOnAwait(), new PruningProcessingQueue$processingLoop$2$1$2(r13, null));     // Catch: Throwable -> L8 CancellationException -> L36
        L18:
            this.L$0 = r5;     // Catch: Throwable -> L8 CancellationException -> L36
            this.L$1 = r13;     // Catch: Throwable -> L8 CancellationException -> L36
            this.label = 1;     // Catch: Throwable -> L8 CancellationException -> L36
            if (r6.doSelect(this) != r0) goto L22;
            return r0;
        L31:
            Throwable r124 = null;
        L39:
            PruningProcessingQueue.access$closeAndReleaseUnprocessedElements(this.this$0, r124);
            if (r124 != null) goto L42;
            return null;
        L42:
            throw r124;
        L24:
            if (r13.element != null) goto L14;
            Object r125 = PruningProcessingQueue.access$getQueue$p(this.this$0).first();
            ?? r62 = (T) BuildersKt.async$default(r5, null, null, new PruningProcessingQueue$processingLoop$2$deferred$1(this.this$0, r125, null), 3, null);
            if (r62.isCancelled() == true) goto L29;
            PruningProcessingQueue.access$getQueue$p(this.this$0).removeFirst();
            r13.element = r62;
            goto L14
        L29:
            if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L31;
            android.util.Log.i("CXCP", "Unable to process " + r125 + " due to Job cancellation");
        L8:
            th = move-exception;
            r124 = th;
            if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L39;
            android.util.Log.e("CXCP", "Encountered exception during processing", r124);
        L37:
            if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L31;
            android.util.Log.d("CXCP", "PruningProcessingQueue: Scope cancelled");
            goto L31
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L12:
            ResultKt.throwOnFailure(r12);
            CoroutineScope r126 = (CoroutineScope) this.L$0;
            r13 = new Ref.ObjectRef();
            r5 = r126;
            goto L14
        }
    }

    public static Unit $r8$lambda$RG3ppoJODn6AVP6U8lNR6Aptzas(PruningProcessingQueue r0, Object r1) {
        r0.queue.add(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$afzN2lQHS0xYpouZuz7-EA9T4vo, reason: not valid java name */
    public static Unit m504$r8$lambda$afzN2lQHS0xYpouZuz7EA9T4vo(List r0) {
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$fSuzXocaviOMyioLIrFHk-7Y22E, reason: not valid java name */
    public static Unit m505$r8$lambda$fSuzXocaviOMyioLIrFHk7Y22E(List r0) {
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PruningProcessingQueue(int r1, Function1<? super List<T>, Unit> r2, Function1<? super List<? extends T>, Unit> r3, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> r4) {
        this.capacity = r1;
        this.prune = r2;
        this.onUnprocessedElements = r3;
        this.process = r4;
        this.started = AtomicFU.atomic(false);
        this.channel = ChannelKt.Channel$default(r1, null, new PruningProcessingQueue$$ExternalSyntheticLambda2(this), 2, null);
        this.queue = new ArrayDeque();
    }

    public /* synthetic */ PruningProcessingQueue(int r1, Function1 r2, Function1 r3, Function2 r4, int r5, DefaultConstructorMarker r6) {
        if ((r5 & 1) == 0) goto L6;
        r1 = Integer.MAX_VALUE;
    L6:
        if ((r5 & 2) == 0) goto L9;
        r2 = new PruningProcessingQueue$$ExternalSyntheticLambda0();
    L9:
        if ((r5 & 4) == 0) goto L11;
        r3 = new PruningProcessingQueue$$ExternalSyntheticLambda1();
    L11:
        this(r1, r2, r3, r4);
    }

    public static final /* synthetic */ void access$closeAndReleaseUnprocessedElements(PruningProcessingQueue r0, Throwable r1) {
        r0.closeAndReleaseUnprocessedElements(r1);
    }

    public static final /* synthetic */ Channel access$getChannel$p(PruningProcessingQueue r0) {
        return r0.channel;
    }

    public static final /* synthetic */ Function2 access$getProcess$p(PruningProcessingQueue r0) {
        return r0.process;
    }

    public static final /* synthetic */ Function1 access$getPrune$p(PruningProcessingQueue r0) {
        return r0.prune;
    }

    public static final /* synthetic */ ArrayDeque access$getQueue$p(PruningProcessingQueue r0) {
        return r0.queue;
    }

    public static final /* synthetic */ AtomicBoolean access$getStarted$p(PruningProcessingQueue r0) {
        return r0.started;
    }

    public static final /* synthetic */ Object access$processingLoop(PruningProcessingQueue r0, Continuation r1) {
        return r0.processingLoop(r1);
    }

    private final void closeAndReleaseUnprocessedElements(Throwable r3) {
        if (this.channel.close(r3) == false) goto L13;
        Object r32 = this.channel.mo3531tryReceivePtdJZtk();
    L5:
        boolean r0 = ChannelResult.m3542isSuccessimpl(r32);
        ArrayDeque<T> r1 = this.queue;
        if (r0 == false) goto L9;
        r1.add(ChannelResult.m3539getOrThrowimpl(r32));
        r32 = this.channel.mo3531tryReceivePtdJZtk();
        goto L5
    L9:
        if (r1.isEmpty() == true) goto L14;
        this.onUnprocessedElements.invoke(CollectionsKt.toMutableList(this.queue));
        this.queue.clear();
        return;
    L14:
        return;
    }

    private final Object processingLoop(Continuation r3) {
        return SupervisorKt.supervisorScope(new AnonymousClass2(this, null), r3);
    }

    public final boolean tryEmit(T r1) {
        return ChannelResult.m3542isSuccessimpl(this.channel.mo3532trySendJP2dKIU(r1));
    }
}
