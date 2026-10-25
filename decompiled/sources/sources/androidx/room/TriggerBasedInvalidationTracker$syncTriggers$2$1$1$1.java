package androidx.room;

import androidx.room.ObservedTableStates;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/TransactionScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1", f = "InvalidationTracker.kt", i = {0, 0, 1, 1}, l = {318, 319}, m = "invokeSuspend", n = {"$this$forEachIndexed$iv", "index$iv", "$this$forEachIndexed$iv", "index$iv"}, s = {"L$0", "I$0", "L$0", "I$0"})
@SourceDebugExtension({"SMAP\nInvalidationTracker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,640:1\n13537#2,3:641\n*S KotlinDebug\n*F\n+ 1 InvalidationTracker.kt\nandroidx/room/TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1\n*L\n315#1:641,3\n*E\n"})
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1 extends SuspendLambda implements Function2<TransactionScope<Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Transactor $connection;
    final /* synthetic */ ObservedTableStates.ObserveOp[] $tablesToSync;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ TriggerBasedInvalidationTracker this$0;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[ObservedTableStates.ObserveOp.values().length];
            r0[ObservedTableStates.ObserveOp.NO_OP.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[ObservedTableStates.ObserveOp.ADD.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[ObservedTableStates.ObserveOp.REMOVE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1(ObservedTableStates.ObserveOp[] r1, TriggerBasedInvalidationTracker r2, Transactor r3, Continuation<? super TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1> r4) {
        this.$tablesToSync = r1;
        this.this$0 = r2;
        this.$connection = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1(this.$tablesToSync, this.this$0, this.$connection, r4);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(TransactionScope<Unit> r1, Continuation<? super Unit> r2) {
        return ((TriggerBasedInvalidationTracker$syncTriggers$2$1$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(TransactionScope<Unit> r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0083 -> B:27:0x0084). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 == 1) goto L6;
        if (r1 == 2) goto L6;
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L6:
        int r12 = this.I$2;
        int r5 = this.I$1;
        int r6 = this.I$0;
        Transactor r7 = (Transactor) this.L$2;
        TriggerBasedInvalidationTracker r8 = (TriggerBasedInvalidationTracker) this.L$1;
        ObservedTableStates.ObserveOp[] r9 = (ObservedTableStates.ObserveOp[]) this.L$0;
        ResultKt.throwOnFailure(r13);
    L20:
        Transactor r132 = r7;
        int r72 = r6;
    L27:
        r5 = r5 + 1;
    L10:
        if (r5 >= r12) goto L29;
        int r10 = r72 + 1;
        int r62 = WhenMappings.$EnumSwitchMapping$0[r9[r5].ordinal()];
        if (r62 == 1) goto L26;
        if (r62 != 2) goto L15;
        this.L$0 = r9;
        this.L$1 = r8;
        this.L$2 = r132;
        this.I$0 = r10;
        this.I$1 = r5;
        this.I$2 = r12;
        this.label = 1;
        if (TriggerBasedInvalidationTracker.access$startTrackingTable(r8, r132, r72, this) != r0) goto L19;
    L25:
        return r0;
    L30:
        r7 = r132;
        r6 = r10;
        goto L20
    L15:
        if (r62 != 3) goto L21;
        this.L$0 = r9;
        this.L$1 = r8;
        this.L$2 = r132;
        this.I$0 = r10;
        this.I$1 = r5;
        this.I$2 = r12;
        this.label = 2;
        if (TriggerBasedInvalidationTracker.access$stopTrackingTable(r8, r132, r72, this) == r0) goto L25;
    L19:
        r7 = r132;
        r6 = r10;
        goto L20
    L21:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L26:
        r72 = r10;
        goto L27
    L29:
        return Unit.INSTANCE;
    L9:
        ResultKt.throwOnFailure(r13);
        ObservedTableStates.ObserveOp[] r133 = this.$tablesToSync;
        TriggerBasedInvalidationTracker r14 = this.this$0;
        Transactor r52 = this.$connection;
        int r63 = r133.length;
        r72 = 0;
        r9 = r133;
        r8 = r14;
        r132 = r52;
        r12 = r63;
        r5 = 0;
        goto L10
    }
}
