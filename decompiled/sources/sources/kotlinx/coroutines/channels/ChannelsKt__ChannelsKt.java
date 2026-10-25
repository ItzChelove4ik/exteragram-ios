package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ChannelResult;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a+\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0002\u001a\u00028\u0000¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"E", "Lkotlinx/coroutines/channels/SendChannel;", "element", "Lkotlinx/coroutines/channels/ChannelResult;", _UrlKt.FRAGMENT_ENCODE_SET, "trySendBlocking", "(Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/channels/ChannelsKt")
@SourceDebugExtension({"SMAP\nChannels.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channels.kt\nkotlinx/coroutines/channels/ChannelsKt__ChannelsKt\n+ 2 Channel.kt\nkotlinx/coroutines/channels/ChannelKt\n*L\n1#1,61:1\n1011#2,2:62\n*S KotlinDebug\n*F\n+ 1 Channels.kt\nkotlinx/coroutines/channels/ChannelsKt__ChannelsKt\n*L\n37#1:62,2\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class ChannelsKt__ChannelsKt {

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/channels/ChannelResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__ChannelsKt$trySendBlocking$2", f = "Channels.kt", i = {0, 0, 0}, l = {39}, m = "invokeSuspend", n = {"$this$runBlocking", "$this$invokeSuspend_u24lambda_u240", "$i$a$-runCatching-ChannelsKt__ChannelsKt$trySendBlocking$2$r$1"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    @SourceDebugExtension({"SMAP\nChannels.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channels.kt\nkotlinx/coroutines/channels/ChannelsKt__ChannelsKt$trySendBlocking$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"})
    /* renamed from: kotlinx.coroutines.channels.ChannelsKt__ChannelsKt$trySendBlocking$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ChannelResult<? extends Unit>>, Object> {
        final /* synthetic */ E $element;
        final /* synthetic */ SendChannel<E> $this_trySendBlocking;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(SendChannel<? super E> r1, E r2, Continuation<? super AnonymousClass2> r3) {
            this.$this_trySendBlocking = r1;
            this.$element = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
            AnonymousClass2 r0 = new AnonymousClass2(this.$this_trySendBlocking, this.$element, r4);
            r0.L$0 = r3;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super ChannelResult<? extends Unit>> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super ChannelResult<Unit>> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            CoroutineScope r0 = (CoroutineScope) this.L$0;
            Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = this.label;
            if (r2 == 0) goto L10;
            if (r2 != 1) goto L8;
            CoroutineScope r5 = (CoroutineScope) this.L$1;
            ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L16
        L14:
            Object r52 = Result.m2018constructorimpl(Unit.INSTANCE);     // Catch: Throwable -> L16
        L19:
            if (Result.m2025isSuccessimpl(r52) == false) goto L21;
            Object r53 = ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
        L23:
            return ChannelResult.m3534boximpl(r53);
        L21:
            r53 = ChannelResult.Companion.m3545closedJP2dKIU(Result.m2021exceptionOrNullimpl(r52));
            goto L23
        L8:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L10:
            ResultKt.throwOnFailure(r6);
            SendChannel<E> r62 = this.$this_trySendBlocking;
            E r22 = this.$element;
            Result.Companion r4 = Result.Companion;     // Catch: Throwable -> L16
            this.L$0 = SpillingKt.nullOutSpilledVariable(r0);     // Catch: Throwable -> L16
            this.L$1 = SpillingKt.nullOutSpilledVariable(r0);     // Catch: Throwable -> L16
            this.I$0 = 0;     // Catch: Throwable -> L16
            this.label = 1;     // Catch: Throwable -> L16
            if (r62.send(r22, this) != r1) goto L14;
            return r1;
        L16:
            th = move-exception;
            Result.Companion r63 = Result.Companion;
            r52 = Result.m2018constructorimpl(ResultKt.createFailure(th));
            goto L19
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> Object trySendBlocking(SendChannel<? super E> r2, E r3) {
        Object r0 = r2.mo3532trySendJP2dKIU(r3);
        if ((r0 instanceof ChannelResult.Failed) == true) goto L7;
        Unit r02 = (Unit) r0;
        return ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
    L7:
        return ((ChannelResult) BuildersKt.runBlockingK$default(null, new AnonymousClass2(r2, r3, null), 1, null)).m3544unboximpl();
    }
}
