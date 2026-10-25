package androidx.datastore.core;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ClosedSendChannelException;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002Bc\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0014\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b0\n\u0012\"\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n¢\u0006\u0002\u0010\rJ\u0013\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015R,\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/datastore/core/SimpleActor;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "scope", "Lkotlinx/coroutines/CoroutineScope;", "onComplete", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "onUndeliveredElement", "Lkotlin/Function2;", "consumeMessage", "Lkotlin/coroutines/Continuation;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "messageQueue", "Lkotlinx/coroutines/channels/Channel;", "remainingMessages", "Landroidx/datastore/core/AtomicInt;", "offer", "msg", "(Ljava/lang/Object;)V", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSimpleActor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleActor.kt\nandroidx/datastore/core/SimpleActor\n+ 2 Channel.kt\nkotlinx/coroutines/channels/ChannelKt\n*L\n1#1,127:1\n548#2,5:128\n*S KotlinDebug\n*F\n+ 1 SimpleActor.kt\nandroidx/datastore/core/SimpleActor\n*L\n104#1:128,5\n*E\n"})
/* loaded from: classes.dex */
public final class SimpleActor<T> {
    private final Function2<T, Continuation<? super Unit>, Object> consumeMessage;
    private final Channel<T> messageQueue;
    private final AtomicInt remainingMessages;
    private final CoroutineScope scope;

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "ex", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.datastore.core.SimpleActor$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Lambda implements Function1<Throwable, Unit> {
        final /* synthetic */ Function1<Throwable, Unit> $onComplete;
        final /* synthetic */ Function2<T, Throwable, Unit> $onUndeliveredElement;
        final /* synthetic */ SimpleActor<T> this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function1<? super Throwable, Unit> r1, SimpleActor<T> r2, Function2<? super T, ? super Throwable, Unit> r3) {
            this.$onComplete = r1;
            this.this$0 = r2;
            this.$onUndeliveredElement = r3;
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
            invoke2(r1);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable r3) {
            this.$onComplete.invoke(r3);
            SimpleActor.access$getMessageQueue$p(this.this$0).close(r3);
        L3:
            Object r0 = ChannelResult.m3538getOrNullimpl(SimpleActor.access$getMessageQueue$p(this.this$0).mo3531tryReceivePtdJZtk());
            if (r0 == null) goto L6;
            this.$onUndeliveredElement.invoke(r0, r3);
            Unit r02 = Unit.INSTANCE;
        L7:
            if (r02 != null) goto L3;
            return;
        L6:
            r02 = null;
            goto L7
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.datastore.core.SimpleActor$offer$2", f = "SimpleActor.kt", i = {}, l = {121, 121}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.datastore.core.SimpleActor$offer$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        final /* synthetic */ SimpleActor<T> this$0;

        public AnonymousClass2(SimpleActor<T> r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0059 -> B:19:0x005c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L11;
            if (r1 == 1) goto L10;
            if (r1 != 2) goto L7;
            ResultKt.throwOnFailure(r6);
        L20:
            if (SimpleActor.access$getRemainingMessages$p(this.this$0).decrementAndGet() == 0) goto L22;
        L13:
            CoroutineScopeKt.ensureActive(SimpleActor.access$getScope$p(this.this$0));
            Function2 r12 = SimpleActor.access$getConsumeMessage$p(this.this$0);
            Channel r62 = SimpleActor.access$getMessageQueue$p(this.this$0);
            this.L$0 = r12;
            this.label = 1;
            r6 = r62.receive(this);
            if (r6 == r0) goto L18;
        L16:
            this.L$0 = null;
            this.label = 2;
            if (r12.invoke(r6, this) != r0) goto L20;
        L18:
            return r0;
        L22:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            r12 = (Function2) this.L$0;
            ResultKt.throwOnFailure(r6);
            goto L16
        L11:
            ResultKt.throwOnFailure(r6);
            if (SimpleActor.access$getRemainingMessages$p(this.this$0).get() > 0) goto L13;
            Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SimpleActor(CoroutineScope r3, Function1<? super Throwable, Unit> r4, Function2<? super T, ? super Throwable, Unit> r5, Function2<? super T, ? super Continuation<? super Unit>, ? extends Object> r6) {
        this.scope = r3;
        this.consumeMessage = r6;
        this.messageQueue = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
        this.remainingMessages = new AtomicInt(0);
        Job r32 = (Job) r3.getCoroutineContext().get(Job.Key);
        if (r32 == null) goto L6;
        r32.invokeOnCompletion(new AnonymousClass1(r4, this, r5));
        return;
    }

    public static final /* synthetic */ Function2 access$getConsumeMessage$p(SimpleActor r0) {
        return r0.consumeMessage;
    }

    public static final /* synthetic */ Channel access$getMessageQueue$p(SimpleActor r0) {
        return r0.messageQueue;
    }

    public static final /* synthetic */ AtomicInt access$getRemainingMessages$p(SimpleActor r0) {
        return r0.remainingMessages;
    }

    public static final /* synthetic */ CoroutineScope access$getScope$p(SimpleActor r0) {
        return r0.scope;
    }

    public final void offer(T r7) {
        Object r72 = this.messageQueue.mo3532trySendJP2dKIU(r7);
        if ((r72 instanceof ChannelResult.Closed) == false) goto L9;
        Throwable r6 = ChannelResult.m3537exceptionOrNullimpl(r72);
        if (r6 == null) goto L7;
        throw r6;
    L7:
        throw new ClosedSendChannelException("Channel was closed normally");
    L9:
        if (ChannelResult.m3542isSuccessimpl(r72) == true) goto L11;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L11:
        if (this.remainingMessages.getAndIncrement() != 0) goto L17;
        BuildersKt.launch$default(this.scope, null, null, new AnonymousClass2(this, null), 3, null);
        return;
    }
}
