package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "T", "it", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.ChannelFlow$collectToFun$1", f = "ChannelFlow.kt", i = {0}, l = {56}, m = "invokeSuspend", n = {"it"}, s = {"L$0"}, v = 1)
/* loaded from: classes.dex */
public final class ChannelFlow$collectToFun$1<T> extends SuspendLambda implements Function2<ProducerScope<? super T>, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ChannelFlow<T> this$0;

    public ChannelFlow$collectToFun$1(ChannelFlow<T> r1, Continuation<? super ChannelFlow$collectToFun$1> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        ChannelFlow$collectToFun$1 r0 = new ChannelFlow$collectToFun$1(this.this$0, r3);
        r0.L$0 = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Object r1, Continuation<? super Unit> r2) {
        return invoke((ProducerScope) r1, r2);
    }

    public final Object invoke(ProducerScope<? super T> r1, Continuation<? super Unit> r2) {
        return ((ChannelFlow$collectToFun$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        ProducerScope<? super T> r0 = (ProducerScope) this.L$0;
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.label;
        if (r2 == 0) goto L8;
        if (r2 != 1) goto L6;
        ResultKt.throwOnFailure(r5);
    L12:
        return Unit.INSTANCE;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r5);
        ChannelFlow<T> r52 = this.this$0;
        this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
        this.label = 1;
        if (r52.collectTo(r0, this) != r1) goto L12;
        return r1;
    }
}
