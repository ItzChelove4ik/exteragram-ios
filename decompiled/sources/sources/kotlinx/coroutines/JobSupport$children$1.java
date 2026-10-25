package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.SequenceScope;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import okhttp3.internal.url._UrlKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/SequenceScope;", "Lkotlinx/coroutines/Job;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.JobSupport$children$1", f = "JobSupport.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1003, WebSocketProtocol.CLOSE_NO_STATUS_CODE}, m = "invokeSuspend", n = {"$this$sequence", "state", "$this$sequence", "state", "list", "this_$iv", "cur$iv", "it", "$i$a$-let-JobSupport$children$1$1", "$i$f$forEach", "$i$a$-forEach-JobSupport$children$1$1$1"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "I$1", "I$2"}, v = 1)
@SourceDebugExtension({"SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport$children$1\n+ 2 LockFreeLinkedList.kt\nkotlinx/coroutines/internal/LockFreeLinkedListHead\n*L\n1#1,1583:1\n273#2,6:1584\n*S KotlinDebug\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/JobSupport$children$1\n*L\n1005#1:1584,6\n*E\n"})
/* loaded from: classes5.dex */
public final class JobSupport$children$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Job>, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ JobSupport this$0;

    public JobSupport$children$1(JobSupport r1, Continuation<? super JobSupport$children$1> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        JobSupport$children$1 r0 = new JobSupport$children$1(this.this$0, r3);
        r0.L$0 = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(SequenceScope<? super Job> r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(SequenceScope<? super Job> r1, Continuation<? super Unit> r2) {
        return ((JobSupport$children$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0082 -> B:27:0x00b0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x00ad -> B:27:0x00b0). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        SequenceScope r0 = (SequenceScope) this.L$0;
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.label;
        if (r2 == 0) goto L10;
        if (r2 == 1) goto L9;
        if (r2 != 2) goto L7;
        int r22 = this.I$1;
        int r4 = this.I$0;
        LockFreeLinkedListNode r6 = (LockFreeLinkedListNode) this.L$5;
        LockFreeLinkedListNode r62 = (LockFreeLinkedListNode) this.L$4;
        LockFreeLinkedListHead r7 = (LockFreeLinkedListHead) this.L$3;
        LockFreeLinkedListHead r8 = (NodeList) this.L$2;
        Object r9 = this.L$1;
        ResultKt.throwOnFailure(r12);
    L27:
        r62 = r62.getNextNode();
    L21:
        if (Intrinsics.areEqual(r62, r7) == true) goto L29;
        if ((r62 instanceof ChildHandleNode) == false) goto L27;
        ChildJob r122 = ((ChildHandleNode) r62).childJob;
        this.L$0 = r0;
        this.L$1 = SpillingKt.nullOutSpilledVariable(r9);
        this.L$2 = SpillingKt.nullOutSpilledVariable(r8);
        this.L$3 = r7;
        this.L$4 = r62;
        this.L$5 = SpillingKt.nullOutSpilledVariable(r62);
        this.I$0 = r4;
        this.I$1 = r22;
        this.I$2 = 0;
        this.label = 2;
        if (r0.yield(r122, this) != r1) goto L27;
    L26:
        return r1;
    L29:
        return Unit.INSTANCE;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r12);
        goto L29
    L10:
        ResultKt.throwOnFailure(r12);
        Object r123 = this.this$0.getState$kotlinx_coroutines_core();
        if ((r123 instanceof ChildHandleNode) == false) goto L16;
        ChildJob r23 = ((ChildHandleNode) r123).childJob;
        this.L$0 = SpillingKt.nullOutSpilledVariable(r0);
        this.L$1 = SpillingKt.nullOutSpilledVariable(r123);
        this.label = 1;
        if (r0.yield(r23, this) != r1) goto L29;
    L16:
        if ((r123 instanceof Incomplete) == false) goto L29;
        NodeList r24 = ((Incomplete) r123).getList();
        if (r24 == null) goto L29;
        r9 = r123;
        r7 = r24;
        r8 = r7;
        r62 = (LockFreeLinkedListNode) r24.getNext();
        r22 = 0;
        r4 = 0;
        goto L21
    }
}
