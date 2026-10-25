package androidx.room.util;

import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TransactionScope;
import androidx.room.Transactor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [R] */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"R", "Landroidx/room/Transactor;", "transactor", "<anonymous>"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1", f = "DBUtil.android.kt", i = {0, 0, 1, 1, 2, 3}, l = {56, 57, 59, 60, 172}, m = "invokeSuspend", n = {"transactor", "type", "transactor", "type", "transactor", "result"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$0"})
@SourceDebugExtension({"SMAP\nDBUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt$internalPerform$2\n+ 2 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performInTransactionSuspending$2\n*L\n1#1,171:1\n98#2:172\n*E\n"})
/* loaded from: classes.dex */
public final class DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1<R> extends SuspendLambda implements Function2<Transactor, Continuation<? super R>, Object> {
    final /* synthetic */ Function1 $block$inlined;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ boolean $isReadOnly;
    final /* synthetic */ RoomDatabase $this_internalPerform;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\n"}, d2 = {"R", "Landroidx/room/TransactionScope;", "<anonymous>"}, k = 3, mv = {2, 1, 0})
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1$1", f = "DBUtil.android.kt", i = {}, l = {60}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nDBUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt$internalPerform$2$result$1\n+ 2 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performInTransactionSuspending$2\n*L\n1#1,59:1\n98#2:60\n*E\n"})
    /* renamed from: androidx.room.util.DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<TransactionScope<R>, Continuation<? super R>, Object> {
        final /* synthetic */ Function1 $block$inlined;
        private /* synthetic */ Object L$0;
        int label;

        public AnonymousClass1(Continuation r1, Function1 r2) {
            this.$block$inlined = r2;
            super(2, r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass1 r0 = new AnonymousClass1(r3, this.$block$inlined);
            r0.L$0 = r2;
            return r0;
        }

        public final Object invoke(TransactionScope<R> r1, Continuation<? super R> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
            return invoke((TransactionScope) r1, (Continuation) r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r4);
            return r4;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r4);
            TransactionScope r42 = (TransactionScope) this.L$0;
            Function1 r43 = this.$block$inlined;
            this.label = 1;
            InlineMarker.mark(6);
            Object r3 = r43.invoke(this);
            InlineMarker.mark(7);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    public DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1(boolean r1, boolean r2, RoomDatabase r3, Continuation r4, Function1 r5) {
        this.$inTransaction = r1;
        this.$isReadOnly = r2;
        this.$this_internalPerform = r3;
        this.$block$inlined = r5;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
        DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1 r0 = new DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1(this.$inTransaction, this.$isReadOnly, this.$this_internalPerform, r8, this.$block$inlined);
        r0.L$0 = r7;
        return r0;
    }

    public final Object invoke(Transactor r1, Continuation<? super R> r2) {
        return ((DBUtil__DBUtil_androidKt$performInTransactionSuspending$2$invokeSuspend$$inlined$internalPerform$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Transactor r1, Object r2) {
        return invoke(r1, (Continuation) r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L17;
        if (r1 == 1) goto L16;
        if (r1 == 2) goto L15;
        if (r1 == 3) goto L14;
        if (r1 == 4) goto L13;
        if (r1 != 5) goto L11;
        ResultKt.throwOnFailure(r10);
        return r10;
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L13:
        Object r02 = this.L$0;
        ResultKt.throwOnFailure(r10);
    L45:
        if (((Boolean) r10).booleanValue() == true) goto L47;
        this.$this_internalPerform.getInvalidationTracker().refreshAsync();
    L47:
        return r02;
    L14:
        Transactor r12 = (Transactor) this.L$0;
        ResultKt.throwOnFailure(r10);
    L39:
        if (this.$isReadOnly == true) goto L48;
        this.L$0 = r10;
        this.label = 4;
        Object r13 = r12.inTransaction(this);
        if (r13 == r0) goto L51;
        r02 = r10;
        r10 = r13;
    L51:
        return r0;
    L48:
        return r10;
    L15:
        Transactor.SQLiteTransactionType r14 = (Transactor.SQLiteTransactionType) this.L$1;
        Transactor r3 = (Transactor) this.L$0;
        ResultKt.throwOnFailure(r10);
    L33:
        Transactor r8 = r3;
        Transactor.SQLiteTransactionType r32 = r14;
        r12 = r8;
    L35:
        AnonymousClass1 r102 = new AnonymousClass1(null, this.$block$inlined);
        this.L$0 = r12;
        this.L$1 = null;
        this.label = 3;
        r10 = r12.withTransaction(r32, r102, this);
        if (r10 != r0) goto L39;
    L16:
        r14 = (Transactor.SQLiteTransactionType) this.L$1;
        r3 = (Transactor) this.L$0;
        ResultKt.throwOnFailure(r10);
    L29:
        if (((Boolean) r10).booleanValue() == true) goto L33;
        InvalidationTracker r103 = this.$this_internalPerform.getInvalidationTracker();
        this.L$0 = r3;
        this.L$1 = r14;
        this.label = 2;
        if (r103.sync$room_runtime(this) != r0) goto L33;
    L17:
        ResultKt.throwOnFailure(r10);
        Transactor r104 = (Transactor) this.L$0;
        if (this.$inTransaction == false) goto L49;
        boolean r15 = this.$isReadOnly;
        if (r15 == false) goto L22;
        r32 = Transactor.SQLiteTransactionType.DEFERRED;
    L23:
        if (r15 == true) goto L34;
        this.L$0 = r104;
        this.L$1 = r32;
        this.label = 1;
        Object r16 = r104.inTransaction(this);
        if (r16 == r0) goto L51;
        Transactor.SQLiteTransactionType r82 = r32;
        r3 = r104;
        r10 = r16;
        r14 = r82;
        goto L29
    L34:
        r12 = r104;
        goto L35
    L22:
        r32 = Transactor.SQLiteTransactionType.IMMEDIATE;
        goto L23
    L49:
        Function1 r105 = this.$block$inlined;
        this.label = 5;
        InlineMarker.mark(6);
        Object r9 = r105.invoke(this);
        InlineMarker.mark(7);
        if (r9 == r0) goto L51;
        return r9;
    }
}
