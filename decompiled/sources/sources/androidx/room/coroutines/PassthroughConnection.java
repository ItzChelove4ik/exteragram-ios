package androidx.room.coroutines;

import android.database.SQLException;
import androidx.room.TransactionScope;
import androidx.room.Transactor;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ExceptionsKt;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u0001,BQ\u0012@\u0010\b\u001a<\b\u0001\u0012\u0018\u0012\u0016\b\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003j\b\u0012\u0002\b\u0003\u0018\u0001`\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJM\u0010\u0013\u001a\u00028\u0000\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000f\u001a\u00020\u000e2-\u0010\u0012\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0011H\u0082@¢\u0006\u0004\b\u0013\u0010\u0014J2\u0010\u0018\u001a\u00028\u0000\"\u0004\b\u0000\u0010\r2\u0006\u0010\u0016\u001a\u00020\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00028\u00000\u0004H\u0096@¢\u0006\u0004\b\u0018\u0010\u0019JM\u0010\u001a\u001a\u00028\u0000\"\u0004\b\u0000\u0010\r2\u0006\u0010\u000f\u001a\u00020\u000e2-\u0010\u0012\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0011H\u0096@¢\u0006\u0004\b\u001a\u0010\u0014J\u0010\u0010\u001c\u001a\u00020\u001bH\u0096@¢\u0006\u0004\b\u001c\u0010\u001dRQ\u0010\b\u001a<\b\u0001\u0012\u0018\u0012\u0016\b\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003j\b\u0012\u0002\b\u0003\u0018\u0001`\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010&\u001a\u00060$j\u0002`%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0018\u0010(\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010+\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010#¨\u0006-"}, d2 = {"Landroidx/room/coroutines/PassthroughConnection;", "Landroidx/room/Transactor;", "Landroidx/room/coroutines/RawConnectionAccessor;", "Lkotlin/Function2;", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/room/coroutines/TransactionWrapper;", "transactionWrapper", "Landroidx/sqlite/SQLiteConnection;", "delegate", "<init>", "(Lkotlin/jvm/functions/Function2;Landroidx/sqlite/SQLiteConnection;)V", "R", "Landroidx/room/Transactor$SQLiteTransactionType;", "type", "Landroidx/room/TransactionScope;", "Lkotlin/ExtensionFunctionType;", "block", "transaction", "(Landroidx/room/Transactor$SQLiteTransactionType;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Landroidx/sqlite/SQLiteStatement;", "usePrepared", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTransaction", _UrlKt.FRAGMENT_ENCODE_SET, "inTransaction", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/jvm/functions/Function2;", "getTransactionWrapper", "()Lkotlin/jvm/functions/Function2;", "Landroidx/sqlite/SQLiteConnection;", "getDelegate", "()Landroidx/sqlite/SQLiteConnection;", "Ljava/util/concurrent/atomic/AtomicInteger;", "Landroidx/room/concurrent/AtomicInt;", "nestedTransactionCount", "Ljava/util/concurrent/atomic/AtomicInteger;", "currentTransactionType", "Landroidx/room/Transactor$SQLiteTransactionType;", "getRawConnection", "rawConnection", "PassthroughTransactor", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPassthroughConnectionPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PassthroughConnectionPool.kt\nandroidx/room/coroutines/PassthroughConnection\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,177:1\n1#2:178\n*E\n"})
/* loaded from: classes.dex */
final class PassthroughConnection implements Transactor, RawConnectionAccessor {
    private Transactor.SQLiteTransactionType currentTransactionType;
    private final SQLiteConnection delegate;
    private AtomicInteger nestedTransactionCount;
    private final Function2<Function1<? super Continuation<Object>, ? extends Object>, Continuation<Object>, Object> transactionWrapper;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J2\u0010\f\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00062\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00010\tH\u0096@¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/room/coroutines/PassthroughConnection$PassthroughTransactor;", "T", "Landroidx/room/TransactionScope;", "Landroidx/room/coroutines/RawConnectionAccessor;", "<init>", "(Landroidx/room/coroutines/PassthroughConnection;)V", "R", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Lkotlin/Function1;", "Landroidx/sqlite/SQLiteStatement;", "block", "usePrepared", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/sqlite/SQLiteConnection;", "getRawConnection", "()Landroidx/sqlite/SQLiteConnection;", "rawConnection", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class PassthroughTransactor<T> implements TransactionScope<T>, RawConnectionAccessor {
        final /* synthetic */ PassthroughConnection this$0;

        public PassthroughTransactor(PassthroughConnection r1) {
            this.this$0 = r1;
        }

        @Override // androidx.room.coroutines.RawConnectionAccessor
        public SQLiteConnection getRawConnection() {
            return this.this$0.getRawConnection();
        }

        @Override // androidx.room.PooledConnection
        public <R> Object usePrepared(String r1, Function1<? super SQLiteStatement, ? extends R> r2, Continuation<? super R> r3) {
            return this.this$0.usePrepared(r1, r2, r3);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[Transactor.SQLiteTransactionType.values().length];
            r0[Transactor.SQLiteTransactionType.DEFERRED.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
        L11:
            r0[Transactor.SQLiteTransactionType.IMMEDIATE.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
        L15:
            r0[Transactor.SQLiteTransactionType.EXCLUSIVE.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
        L6:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PassthroughConnection", f = "PassthroughConnectionPool.kt", i = {0}, l = {127}, m = "transaction", n = {"success"}, s = {"I$0"})
    /* renamed from: androidx.room.coroutines.PassthroughConnection$transaction$1, reason: invalid class name */
    public static final class AnonymousClass1<R> extends ContinuationImpl {
        int I$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PassthroughConnection this$0;

        public AnonymousClass1(PassthroughConnection r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PassthroughConnection.access$transaction(this.this$0, null, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PassthroughConnection", f = "PassthroughConnectionPool.kt", i = {0, 0}, l = {89, 91}, m = "usePrepared", n = {"sql", "block"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.room.coroutines.PassthroughConnection$usePrepared$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01211<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PassthroughConnection this$0;

        public C01211(PassthroughConnection r1, Continuation<? super C01211> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.usePrepared(null, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PassthroughConnection$usePrepared$2", f = "PassthroughConnectionPool.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    @SourceDebugExtension({"SMAP\nPassthroughConnectionPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PassthroughConnectionPool.kt\nandroidx/room/coroutines/PassthroughConnection$usePrepared$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,177:1\n1#2:178\n*E\n"})
    /* renamed from: androidx.room.coroutines.PassthroughConnection$usePrepared$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super Object>, Object> {
        final /* synthetic */ Function1<SQLiteStatement, R> $block;
        final /* synthetic */ String $sql;
        int label;
        final /* synthetic */ PassthroughConnection this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(PassthroughConnection r1, String r2, Function1<? super SQLiteStatement, ? extends R> r3, Continuation<? super AnonymousClass2> r4) {
            this.this$0 = r1;
            this.$sql = r2;
            this.$block = r3;
            super(1, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r4) {
            return new AnonymousClass2(this.this$0, this.$sql, this.$block, r4);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Object> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<Object> r1) {
            return ((AnonymousClass2) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L13;
            ResultKt.throwOnFailure(r2);
            SQLiteStatement r22 = this.this$0.getDelegate().prepare(this.$sql);
            Object r1 = this.$block.invoke(r22);     // Catch: Throwable -> L8
            AutoCloseableKt.closeFinally(r22, null);
            return r1;
        L8:
            th = move-exception;
            throw th;     // Catch: Throwable -> L10
        L10:
            th = move-exception;
            AutoCloseableKt.closeFinally(r22, th);
            throw th;
        L13:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PassthroughConnection$withTransaction$2", f = "PassthroughConnectionPool.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.coroutines.PassthroughConnection$withTransaction$2, reason: invalid class name and case insensitive filesystem */
    public static final class C01222 extends SuspendLambda implements Function1<Continuation<? super Object>, Object> {
        final /* synthetic */ Function2<TransactionScope<R>, Continuation<? super R>, Object> $block;
        final /* synthetic */ Transactor.SQLiteTransactionType $type;
        int label;
        final /* synthetic */ PassthroughConnection this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public C01222(PassthroughConnection r1, Transactor.SQLiteTransactionType r2, Function2<? super TransactionScope<R>, ? super Continuation<? super R>, ? extends Object> r3, Continuation<? super C01222> r4) {
            this.this$0 = r1;
            this.$type = r2;
            this.$block = r3;
            super(1, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r4) {
            return new C01222(this.this$0, this.$type, this.$block, r4);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Object> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<Object> r1) {
            return ((C01222) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r5);
            return r5;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r5);
            PassthroughConnection r52 = this.this$0;
            Transactor.SQLiteTransactionType r12 = this.$type;
            Function2<TransactionScope<R>, Continuation<? super R>, Object> r3 = this.$block;
            this.label = 1;
            Object r4 = PassthroughConnection.access$transaction(r52, r12, r3, this);
            if (r4 != r0) goto L12;
            return r0;
        L12:
            return r4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PassthroughConnection(Function2<? super Function1<? super Continuation<Object>, ? extends Object>, ? super Continuation<Object>, ? extends Object> r1, SQLiteConnection r2) {
        this.transactionWrapper = r1;
        this.delegate = r2;
        this.nestedTransactionCount = new AtomicInteger(0);
    }

    public static final /* synthetic */ Object access$transaction(PassthroughConnection r0, Transactor.SQLiteTransactionType r1, Function2 r2, Continuation r3) {
        return r0.transaction(r1, r2, r3);
    }

    private final <R> Object transaction(Transactor.SQLiteTransactionType r7, Function2<? super TransactionScope<R>, ? super Continuation<? super R>, ? extends Object> r8, Continuation<? super R> r9) {
        if ((r9 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        int r5 = 1;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        r5 = r0.I$0;
        ResultKt.throwOnFailure(r92);     // Catch: Throwable -> L14
    L35:
        if (this.nestedTransactionCount.decrementAndGet() != 0) goto L38;
        this.currentTransactionType = null;
    L38:
        SQLiteConnection r6 = this.delegate;
        if (r5 == 0) goto L42;
        SQLite.execSQL(r6, "END TRANSACTION");
        return r92;
    L42:
        SQLite.execSQL(r6, "ROLLBACK TRANSACTION");
        return r92;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r92);
        int r93 = WhenMappings.$EnumSwitchMapping$0[r7.ordinal()];
        if (r93 != 1) goto L21;
        SQLite.execSQL(this.delegate, "BEGIN DEFERRED TRANSACTION");
    L30:
        if (this.nestedTransactionCount.incrementAndGet() <= 0) goto L32;
        this.currentTransactionType = r7;
    L32:
        PassthroughTransactor r72 = new PassthroughTransactor(this);     // Catch: Throwable -> L14
        r0.I$0 = 1;     // Catch: Throwable -> L14
        r0.label = 1;     // Catch: Throwable -> L14
        r92 = r8.invoke(r72, r0);     // Catch: Throwable -> L14
        if (r92 != r12) goto L35;
        return r12;
    L21:
        if (r93 != 2) goto L23;
        SQLite.execSQL(this.delegate, "BEGIN IMMEDIATE TRANSACTION");
        goto L30
    L23:
        if (r93 != 3) goto L25;
        SQLite.execSQL(this.delegate, "BEGIN EXCLUSIVE TRANSACTION");
        goto L30
    L25:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L45
    L45:
        th = move-exception;
    L49:
        e = move-exception;
        ExceptionsKt.addSuppressed(th, e);
    L54:
        throw th;
    L47:
        if (this.nestedTransactionCount.decrementAndGet() != 0) goto L51;
        this.currentTransactionType = null;     // Catch: SQLException -> L49
    L51:
        SQLite.execSQL(this.delegate, "ROLLBACK TRANSACTION");     // Catch: SQLException -> L49
    L7:
        r0 = new AnonymousClass1(this, r9);
        goto L8
    }

    public final SQLiteConnection getDelegate() {
        return this.delegate;
    }

    @Override // androidx.room.coroutines.RawConnectionAccessor
    public SQLiteConnection getRawConnection() {
        return this.delegate;
    }

    @Override // androidx.room.Transactor
    public Object inTransaction(Continuation<? super Boolean> r1) {
        if (this.currentTransactionType == null) goto L5;
    L8:
        boolean r0 = true;
    L10:
        return Boxing.boxBoolean(r0);
    L5:
        if (this.delegate.inTransaction() == true) goto L8;
        r0 = false;
        goto L10
    }

    @Override // androidx.room.PooledConnection
    public <R> Object usePrepared(String r7, Function1<? super SQLiteStatement, ? extends R> r8, Continuation<? super R> r9) {
        if ((r9 instanceof C01211) == false) goto L7;
        C01211 r0 = (C01211) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L17;
        if (r2 == 1) goto L16;
        if (r2 != 2) goto L14;
        ResultKt.throwOnFailure(r92);
        return r92;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        r8 = (Function1) r0.L$1;
        r7 = (String) r0.L$0;
        ResultKt.throwOnFailure(r92);
    L21:
        if (((Boolean) r92).booleanValue() == false) goto L28;
        Function2<Function1<? super Continuation<Object>, ? extends Object>, Continuation<Object>, Object> r93 = this.transactionWrapper;
        if (r93 == null) goto L28;
        AnonymousClass2 r22 = new AnonymousClass2(this, r7, r8, null);
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 2;
        Object r6 = r93.invoke(r22, r0);
        if (r6 != r12) goto L27;
    L26:
        return r12;
    L27:
        return r6;
    L28:
        SQLiteStatement r62 = this.delegate.prepare(r7);
        R r72 = r8.invoke(r62);     // Catch: Throwable -> L32
        AutoCloseableKt.closeFinally(r62, null);
        return r72;
    L32:
        th = move-exception;
        throw th;     // Catch: Throwable -> L34
    L34:
        th = move-exception;
        AutoCloseableKt.closeFinally(r62, th);
        throw th;
    L17:
        ResultKt.throwOnFailure(r92);
        r0.L$0 = r7;
        r0.L$1 = r8;
        r0.label = 1;
        r92 = inTransaction(r0);
        if (r92 != r12) goto L21;
    L7:
        r0 = new C01211(this, r9);
        goto L8
    }

    @Override // androidx.room.Transactor
    public <R> Object withTransaction(Transactor.SQLiteTransactionType r4, Function2<? super TransactionScope<R>, ? super Continuation<? super R>, ? extends Object> r5, Continuation<? super R> r6) {
        Function2<Function1<? super Continuation<Object>, ? extends Object>, Continuation<Object>, Object> r0 = this.transactionWrapper;
        if (r0 == null) goto L7;
        Object r3 = r0.invoke(new C01222(this, r4, r5, null), r6);
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return r3;
    L7:
        return transaction(r4, r5, r6);
    }
}
