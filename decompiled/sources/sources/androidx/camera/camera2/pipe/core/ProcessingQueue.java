package androidx.camera.camera2.pipe.core;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 '*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001'BW\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u001a\b\u0002\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012(\u0010\f\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0007H\u0082@¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0013\u001a\u00020\u00072\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR&\u0010\b\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0004\u0012\u00020\u00070\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001cR6\u0010\f\u001a$\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&¨\u0006("}, d2 = {"Landroidx/camera/camera2/pipe/core/ProcessingQueue;", "T", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "onUnprocessedElements", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/Continuation;", "process", "<init>", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "processingLoop", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "cause", "releaseUnprocessedElements", "(Ljava/lang/Throwable;)V", "element", _UrlKt.FRAGMENT_ENCODE_SET, "tryEmit", "(Ljava/lang/Object;)Z", "I", "getCapacity", "()I", "Lkotlin/jvm/functions/Function1;", "Lkotlin/jvm/functions/Function2;", "Lkotlinx/atomicfu/AtomicBoolean;", "started", "Lkotlinx/atomicfu/AtomicBoolean;", "Lkotlinx/coroutines/channels/Channel;", "channel", "Lkotlinx/coroutines/channels/Channel;", "Lkotlin/collections/ArrayDeque;", "queue", "Lkotlin/collections/ArrayDeque;", "Companion", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProcessingQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProcessingQueue.kt\nandroidx/camera/camera2/pipe/core/ProcessingQueue\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,174:1\n1#2:175\n*E\n"})
/* loaded from: classes3.dex */
public final class ProcessingQueue<T> {
    public static final Companion Companion = null;
    private final int capacity;
    private final Channel<T> channel;
    private final Function1<List<? extends T>, Unit> onUnprocessedElements;
    private final Function2<List<T>, Continuation<? super Unit>, Object> process;
    private final ArrayDeque<T> queue;
    private final AtomicBoolean started;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/core/ProcessingQueue$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "processIn", "Landroidx/camera/camera2/pipe/core/ProcessingQueue;", "T", "scope", "Lkotlinx/coroutines/CoroutineScope;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final <T> ProcessingQueue<T> processIn(ProcessingQueue<T> r7, CoroutineScope r8) {
            if (ProcessingQueue.access$getStarted$p(r7).compareAndSet(false, true) == true) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("ProcessingQueue cannot be re-started!");
            return null;
        L5:
            if (BuildersKt.launch$default(r8, null, null, new ProcessingQueue$Companion$processIn$job$1(r7, null), 3, null).isCancelled() == false) goto L7;
            ProcessingQueue.access$releaseUnprocessedElements(r7, null);
        L7:
            return r7;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.core.ProcessingQueue", f = "ProcessingQueue.kt", i = {1}, l = {102, 117}, m = "processingLoop", n = {"size"}, s = {"I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.core.ProcessingQueue$processingLoop$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ ProcessingQueue<T> this$0;

        public AnonymousClass1(ProcessingQueue<T> r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return ProcessingQueue.access$processingLoop(this.this$0, this);
        }
    }

    public static Unit $r8$lambda$S7l7sqMHhvUaxQ5VH8QW7tCGrXU(ProcessingQueue r0, Object r1) {
        r0.queue.add(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$WIhvWBrNSeX5RqXKtse-OPkvHt4, reason: not valid java name */
    public static Unit m503$r8$lambda$WIhvWBrNSeX5RqXKtseOPkvHt4(List r0) {
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProcessingQueue(int r2, Function1<? super List<? extends T>, Unit> r3, Function2<? super List<T>, ? super Continuation<? super Unit>, ? extends Object> r4) {
        this.capacity = r2;
        this.onUnprocessedElements = r3;
        this.process = r4;
        this.started = AtomicFU.atomic(false);
        this.channel = ChannelKt.Channel$default(r2, null, new ProcessingQueue$$ExternalSyntheticLambda1(this), 2, null);
        this.queue = new ArrayDeque();
    }

    public /* synthetic */ ProcessingQueue(int r1, Function1 r2, Function2 r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = Integer.MAX_VALUE;
    L6:
        if ((r4 & 2) == 0) goto L8;
        r2 = new ProcessingQueue$$ExternalSyntheticLambda0();
    L8:
        this(r1, r2, r3);
    }

    public static final /* synthetic */ AtomicBoolean access$getStarted$p(ProcessingQueue r0) {
        return r0.started;
    }

    public static final /* synthetic */ Object access$processingLoop(ProcessingQueue r0, Continuation r1) {
        return r0.processingLoop(r1);
    }

    public static final /* synthetic */ void access$releaseUnprocessedElements(ProcessingQueue r0, Throwable r1) {
        r0.releaseUnprocessedElements(r1);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0082 -> B:36:0x0085). Please report as a decompilation issue!!! */
    private final Object processingLoop(Continuation<? super Unit> r7) {
        if ((r7 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L21;
        if (r2 == 1) goto L19;
        if (r2 != 2) goto L17;
        int r22 = r0.I$0;
        ResultKt.throwOnFailure(r72);     // Catch: Throwable -> L15
    L36:
        if (r22 != this.queue.size()) goto L27;
    L22:
        Channel<T> r73 = this.channel;     // Catch: Throwable -> L15
        r0.label = 1;     // Catch: Throwable -> L15
        Object r74 = r73.receive(r0);     // Catch: Throwable -> L15
        if (r74 == r12) goto L35;
        this.queue.add(r74);     // Catch: Throwable -> L15
    L35:
        return r12;
    L27:
        if (this.queue.isEmpty() == true) goto L22;
        Object r75 = this.channel.mo3531tryReceivePtdJZtk();     // Catch: Throwable -> L15
    L29:
        boolean r23 = ChannelResult.m3542isSuccessimpl(r75);     // Catch: Throwable -> L15
        ArrayDeque<T> r5 = this.queue;
        if (r23 == false) goto L33;
        r5.add(ChannelResult.m3539getOrThrowimpl(r75));     // Catch: Throwable -> L15
        r75 = this.channel.mo3531tryReceivePtdJZtk();     // Catch: Throwable -> L15
        goto L29
    L33:
        r22 = r5.size();     // Catch: Throwable -> L15
        Function2<List<T>, Continuation<? super Unit>, Object> r76 = this.process;     // Catch: Throwable -> L15
        ArrayDeque<T> r52 = this.queue;     // Catch: Throwable -> L15
        r0.I$0 = r22;     // Catch: Throwable -> L15
        r0.label = 2;     // Catch: Throwable -> L15
        if (r76.invoke(r52, r0) != r12) goto L36;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        ResultKt.throwOnFailure(r72);     // Catch: Throwable -> L15
        this.queue.add(r72);     // Catch: Throwable -> L15
        goto L27
    L21:
        ResultKt.throwOnFailure(r72);
    L15:
        th = move-exception;
        releaseUnprocessedElements(th);
        throw th;
    L7:
        r0 = new AnonymousClass1(this, r7);
        goto L8
    }

    private final void releaseUnprocessedElements(Throwable r3) {
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

    public final boolean tryEmit(T r1) {
        return ChannelResult.m3542isSuccessimpl(this.channel.mo3532trySendJP2dKIU(r1));
    }
}
