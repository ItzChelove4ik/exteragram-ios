package androidx.room;

import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3", f = "InvalidationTracker.kt", i = {}, l = {394}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$refreshInvalidationAsync$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $onRefreshCompleted;
    int label;
    final /* synthetic */ TriggerBasedInvalidationTracker this$0;

    public TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(TriggerBasedInvalidationTracker r1, Function0<Unit> r2, Continuation<? super TriggerBasedInvalidationTracker$refreshInvalidationAsync$3> r3) {
        this.this$0 = r1;
        this.$onRefreshCompleted = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(this.this$0, this.$onRefreshCompleted, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((TriggerBasedInvalidationTracker$refreshInvalidationAsync$3) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L11;
        if (r1 != 1) goto L9;
        ResultKt.throwOnFailure(r4);     // Catch: Throwable -> L7
    L15:
        Set r42 = (Set) r4;     // Catch: Throwable -> L7
        this.$onRefreshCompleted.invoke();
        return Unit.INSTANCE;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        ResultKt.throwOnFailure(r4);
        TriggerBasedInvalidationTracker r43 = this.this$0;     // Catch: Throwable -> L7
        this.label = 1;     // Catch: Throwable -> L7
        r4 = TriggerBasedInvalidationTracker.access$notifyInvalidation(r43, this);     // Catch: Throwable -> L7
        if (r4 != r0) goto L15;
        return r0;
    L7:
        th = move-exception;
        this.$onRefreshCompleted.invoke();
        throw th;
    }
}
