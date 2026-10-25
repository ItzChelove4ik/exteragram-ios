package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0004\u001a\u00020\u0002*\u0006\u0012\u0002\b\u00030\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0086@¢\u0006\u0004\b\u0004\u0010\u0005\u001ab\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\"\u0004\b\u0000\u0010\u0006*\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2-\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a¥\u0001\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\"\u0004\b\u0000\u0010\u0006*\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u00132\b\b\u0002\u0010\u0016\u001a\u00020\u00152-\b\u0002\u0010\u001d\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0017j\u0004\u0018\u0001`\u001c2-\u0010\u0003\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\f¢\u0006\u0002\b\u000fH\u0000¢\u0006\u0004\b\u0011\u0010\u001e¨\u0006\u001f"}, d2 = {"Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "block", "awaitClose", "(Lkotlinx/coroutines/channels/ProducerScope;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "E", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "produce", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlinx/coroutines/CoroutineStart;", "start", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", "Lkotlinx/coroutines/CompletionHandler;", "onCompletion", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;ILkotlinx/coroutines/channels/BufferOverflow;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProduce.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Produce.kt\nkotlinx/coroutines/channels/ProduceKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,359:1\n1#2:360\n433#3,10:361\n*S KotlinDebug\n*F\n+ 1 Produce.kt\nkotlinx/coroutines/channels/ProduceKt\n*L\n63#1:361,10\n*E\n"})
/* loaded from: classes.dex */
public abstract class ProduceKt {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ProduceKt", f = "Produce.kt", i = {0, 0, 0}, l = {361}, m = "awaitClose", n = {"$this$awaitClose", "block", "$i$f$suspendCancellableCoroutine"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    /* renamed from: kotlinx.coroutines.channels.ProduceKt$awaitClose$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return ProduceKt.awaitClose(null, null, this);
        }
    }

    public static final Object awaitClose(ProducerScope<?> r5, Function0<Unit> r6, Continuation<? super Unit> r7) {
        if ((r7 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        r6 = (Function0) r0.L$1;
        ProducerScope r52 = (ProducerScope) r0.L$0;
        ResultKt.throwOnFailure(r72);     // Catch: Throwable -> L14
    L25:
        r6.invoke();
        return Unit.INSTANCE;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r72);
        if (r0.getContext().get(Job.Key) != r5) goto L29;
        r0.L$0 = r5;     // Catch: Throwable -> L14
        r0.L$1 = r6;     // Catch: Throwable -> L14
        r0.I$0 = 0;     // Catch: Throwable -> L14
        r0.label = 1;     // Catch: Throwable -> L14
        CancellableContinuationImpl r73 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r0), 1);     // Catch: Throwable -> L14
        r73.initCancellability();     // Catch: Throwable -> L14
        r5.invokeOnClose(new ProduceKt$awaitClose$4$1(r73));     // Catch: Throwable -> L14
        Object r53 = r73.getResult();     // Catch: Throwable -> L14
        if (r53 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L23;
        DebugProbesKt.probeCoroutineSuspended(r0);     // Catch: Throwable -> L14
    L23:
        if (r53 != r12) goto L25;
        return r12;
    L29:
        Segment$$ExternalSyntheticBUOutline1.m("awaitClose() can only be invoked from the producer context");
        return null;
    L14:
        th = move-exception;
        r6.invoke();
        throw th;
    L7:
        r0 = new AnonymousClass1(r7);
        goto L8
    }

    public static final <E> ReceiveChannel<E> produce(CoroutineScope r7, CoroutineContext r8, int r9, Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> r10) {
        return produce(r7, r8, r9, BufferOverflow.SUSPEND, CoroutineStart.DEFAULT, null, r10);
    }

    public static final <E> ReceiveChannel<E> produce(CoroutineScope r2, CoroutineContext r3, int r4, BufferOverflow r5, CoroutineStart r6, Function1<? super Throwable, Unit> r7, Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> r8) {
        Channel r42 = ChannelKt.Channel$default(r4, r5, null, 4, null);
        ProducerCoroutine r32 = new ProducerCoroutine(CoroutineContextKt.newCoroutineContext(r2, r3), r42);
        if (r7 == null) goto L5;
        r32.invokeOnCompletion(r7);
    L5:
        r32.start(r6, r32, r8);
        return r32;
    }

    public static /* synthetic */ ReceiveChannel produce$default(CoroutineScope r0, CoroutineContext r1, int r2, BufferOverflow r3, CoroutineStart r4, Function1 r5, Function2 r6, int r7, Object r8) {
        if ((r7 & 1) == 0) goto L6;
        r1 = EmptyCoroutineContext.INSTANCE;
    L6:
        if ((r7 & 2) == 0) goto L9;
        r2 = 0;
    L9:
        if ((r7 & 4) == 0) goto L12;
        r3 = BufferOverflow.SUSPEND;
    L12:
        if ((r7 & 8) == 0) goto L15;
        r4 = CoroutineStart.DEFAULT;
    L15:
        if ((r7 & 16) == 0) goto L17;
        r5 = null;
    L17:
        Function1 r72 = r5;
        BufferOverflow r52 = r3;
        CoroutineStart r62 = r4;
        return produce(r0, r1, r2, r52, r62, r72, r6);
    }
}
