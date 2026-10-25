package androidx.room.util;

import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TransactionScope;
import androidx.room.Transactor;
import androidx.room.coroutines.RawConnectionAccessor;
import androidx.sqlite.SQLiteConnection;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* JADX INFO: Add missing generic type declarations: [R] */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"R", "Landroidx/room/Transactor;", "transactor", "<anonymous>"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1", f = "DBUtil.android.kt", i = {0, 0, 1, 1, 2, 3}, l = {56, 57, 59, 60}, m = "invokeSuspend", n = {"transactor", "type", "transactor", "type", "transactor", "result"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$0"})
@SourceDebugExtension({"SMAP\nDBUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt$internalPerform$2\n+ 2 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performBlocking$1$1\n*L\n1#1,171:1\n78#2,2:172\n*E\n"})
/* loaded from: classes.dex */
public final class DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1<R> extends SuspendLambda implements Function2<Transactor, Continuation<? super R>, Object> {
    final /* synthetic */ Function1 $block$inlined;
    final /* synthetic */ boolean $inTransaction;
    final /* synthetic */ boolean $isReadOnly;
    final /* synthetic */ RoomDatabase $this_internalPerform;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\n"}, d2 = {"R", "Landroidx/room/TransactionScope;", "<anonymous>"}, k = 3, mv = {2, 1, 0})
    @DebugMetadata(c = "androidx.room.util.DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1$1", f = "DBUtil.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nDBUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt$internalPerform$2$result$1\n+ 2 DBUtil.android.kt\nandroidx/room/util/DBUtil__DBUtil_androidKt$performBlocking$1$1\n*L\n1#1,59:1\n78#2,2:60\n*E\n"})
    /* renamed from: androidx.room.util.DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1$1, reason: invalid class name */
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
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r2);
            SQLiteConnection r22 = ((RawConnectionAccessor) ((TransactionScope) this.L$0)).getRawConnection();
            return this.$block$inlined.invoke(r22);
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1(boolean r1, boolean r2, RoomDatabase r3, Continuation r4, Function1 r5) {
        this.$inTransaction = r1;
        this.$isReadOnly = r2;
        this.$this_internalPerform = r3;
        this.$block$inlined = r5;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
        DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1 r0 = new DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1(this.$inTransaction, this.$isReadOnly, this.$this_internalPerform, r8, this.$block$inlined);
        r0.L$0 = r7;
        return r0;
    }

    public final Object invoke(Transactor r1, Continuation<? super R> r2) {
        return ((DBUtil__DBUtil_androidKt$performBlocking$1$1$invokeSuspend$$inlined$internalPerform$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Transactor r1, Object r2) {
        return invoke(r1, (Continuation) r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r9) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L14;
        if (r1 == 1) goto L13;
        if (r1 == 2) goto L12;
        if (r1 == 3) goto L11;
        if (r1 != 4) goto L9;
        Object r02 = this.L$0;
        ResultKt.throwOnFailure(r9);
    L44:
        if (((Boolean) r9).booleanValue() == true) goto L46;
        this.$this_internalPerform.getInvalidationTracker().refreshAsync();
    L46:
        return r02;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        Transactor r12 = (Transactor) this.L$0;
        ResultKt.throwOnFailure(r9);
    L38:
        if (this.$isReadOnly == true) goto L47;
        this.L$0 = r9;
        this.label = 4;
        Object r13 = r12.inTransaction(this);
        if (r13 != r0) goto L42;
    L41:
        return r0;
    L42:
        r02 = r9;
        r9 = r13;
        goto L44
    L47:
        return r9;
    L12:
        Transactor.SQLiteTransactionType r14 = (Transactor.SQLiteTransactionType) this.L$1;
        Transactor r5 = (Transactor) this.L$0;
        ResultKt.throwOnFailure(r9);
    L31:
        Transactor.SQLiteTransactionType r7 = r14;
        r12 = r5;
    L34:
        AnonymousClass1 r92 = new AnonymousClass1(null, this.$block$inlined);
        this.L$0 = r12;
        this.L$1 = null;
        this.label = 3;
        r9 = r12.withTransaction(r7, r92, this);
        if (r9 != r0) goto L38;
    L13:
        r14 = (Transactor.SQLiteTransactionType) this.L$1;
        Transactor r6 = (Transactor) this.L$0;
        ResultKt.throwOnFailure(r9);
    L26:
        if (((Boolean) r9).booleanValue() == true) goto L32;
        InvalidationTracker r93 = this.$this_internalPerform.getInvalidationTracker();
        this.L$0 = r6;
        this.L$1 = r14;
        this.label = 2;
        if (r93.sync$room_runtime(this) == r0) goto L41;
        r5 = r6;
        goto L31
    L32:
        r7 = r14;
        r12 = r6;
        goto L34
    L14:
        ResultKt.throwOnFailure(r9);
        Transactor r94 = (Transactor) this.L$0;
        if (this.$inTransaction == false) goto L48;
        boolean r15 = this.$isReadOnly;
        if (r15 == false) goto L19;
        r7 = Transactor.SQLiteTransactionType.DEFERRED;
    L20:
        if (r15 == true) goto L33;
        this.L$0 = r94;
        this.L$1 = r7;
        this.label = 1;
        Object r16 = r94.inTransaction(this);
        if (r16 == r0) goto L41;
        r6 = r94;
        r9 = r16;
        r14 = r7;
        goto L26
    L33:
        r12 = r94;
        goto L34
    L19:
        r7 = Transactor.SQLiteTransactionType.IMMEDIATE;
        goto L20
    L48:
        SQLiteConnection r95 = ((RawConnectionAccessor) r94).getRawConnection();
        return this.$block$inlined.invoke(r95);
    }
}
