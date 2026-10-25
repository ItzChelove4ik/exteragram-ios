package androidx.room.coroutines;

import android.database.SQLException;
import androidx.room.Transactor;
import androidx.room.concurrent.ThreadLocal_jvmAndroidKt;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.ExceptionsKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001B!\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB1\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\fJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0012H\u0002¢\u0006\u0004\b\u0015\u0010\u0016JB\u0010\u001d\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00172\u0006\u0010\u0013\u001a\u00020\u00122\"\u0010\u001c\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0018H\u0096@¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u001f\u0010 R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0014\u0010%\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010$R\u0014\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R$\u0010+\u001a\u0012\u0012\u0004\u0012\u00020\r0)j\b\u0012\u0004\u0012\u00020\r`*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010-\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\"\u00100\u001a\u00020/8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\"\u0010\u0015\u001a\u00020\u00068\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0015\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:¨\u0006;"}, d2 = {"Landroidx/room/coroutines/ConnectionPoolImpl;", "Landroidx/room/coroutines/ConnectionPool;", "Landroidx/sqlite/SQLiteDriver;", "driver", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "preparedStatementCacheSize", "<init>", "(Landroidx/sqlite/SQLiteDriver;Ljava/lang/String;I)V", "maxNumOfReaders", "maxNumOfWriters", "(Landroidx/sqlite/SQLiteDriver;Ljava/lang/String;III)V", "Landroidx/room/coroutines/PooledConnectionImpl;", "connection", "Lkotlin/coroutines/CoroutineContext;", "createConnectionContext", "(Landroidx/room/coroutines/PooledConnectionImpl;)Lkotlin/coroutines/CoroutineContext;", _UrlKt.FRAGMENT_ENCODE_SET, "isReadOnly", _UrlKt.FRAGMENT_ENCODE_SET, "onTimeout", "(Z)V", "R", "Lkotlin/Function2;", "Landroidx/room/Transactor;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "block", "useConnection", "(ZLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "()V", "Landroidx/sqlite/SQLiteDriver;", "Landroidx/room/coroutines/Pool;", "readers", "Landroidx/room/coroutines/Pool;", "writers", "Landroidx/room/coroutines/ConnectionElementKey;", "connectionElementKey", "Landroidx/room/coroutines/ConnectionElementKey;", "Ljava/lang/ThreadLocal;", "Landroidx/room/concurrent/ThreadLocal;", "connectionThreadLocal", "Ljava/lang/ThreadLocal;", "isClosed", "Z", "Lkotlin/time/Duration;", "timeout", "J", "getTimeout-UwyO8pc$room_runtime", "()J", "setTimeout-LRDsOJo$room_runtime", "(J)V", "I", "getOnTimeout$room_runtime", "()I", "setOnTimeout$room_runtime", "(I)V", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConnectionPoolImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/ConnectionPoolImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,624:1\n1#2:625\n*E\n"})
/* loaded from: classes4.dex */
public final class ConnectionPoolImpl implements ConnectionPool {
    private final ConnectionElementKey connectionElementKey;
    private final ThreadLocal<PooledConnectionImpl> connectionThreadLocal;
    private final SQLiteDriver driver;
    private volatile boolean isClosed;
    private int onTimeout;
    private final Pool readers;
    private long timeout;
    private final Pool writers;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.ConnectionPoolImpl", f = "ConnectionPoolImpl.kt", i = {2, 2, 2, 2, 2, 3, 3}, l = {131, 135, 154, 159}, m = "useConnection", n = {"block", "pool", "connection", "currentContext", "isReadOnly", "pool", "connection"}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "L$0", "L$1"})
    /* renamed from: androidx.room.coroutines.ConnectionPoolImpl$useConnection$1, reason: invalid class name */
    public static final class AnonymousClass1<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ ConnectionPoolImpl this$0;

        public AnonymousClass1(ConnectionPoolImpl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.useConnection(false, null, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.ConnectionPoolImpl$useConnection$2", f = "ConnectionPoolImpl.kt", i = {}, l = {132}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.coroutines.ConnectionPoolImpl$useConnection$2, reason: invalid class name */
    public static final class AnonymousClass2<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<Transactor, Continuation<? super R>, Object> $block;
        final /* synthetic */ PooledConnectionImpl $confinedConnection;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r1, PooledConnectionImpl r2, Continuation<? super AnonymousClass2> r3) {
            this.$block = r1;
            this.$confinedConnection = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass2(this.$block, this.$confinedConnection, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super R> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
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
            Function2<Transactor, Continuation<? super R>, Object> r42 = this.$block;
            PooledConnectionImpl r12 = this.$confinedConnection;
            this.label = 1;
            Object r3 = r42.invoke(r12, this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.ConnectionPoolImpl$useConnection$4", f = "ConnectionPoolImpl.kt", i = {}, l = {159}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.coroutines.ConnectionPoolImpl$useConnection$4, reason: invalid class name */
    public static final class AnonymousClass4<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
        final /* synthetic */ Function2<Transactor, Continuation<? super R>, Object> $block;
        final /* synthetic */ Ref.ObjectRef<PooledConnectionImpl> $connection;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r1, Ref.ObjectRef<PooledConnectionImpl> r2, Continuation<? super AnonymousClass4> r3) {
            this.$block = r1;
            this.$connection = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass4(this.$block, this.$connection, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super R> r2) {
            return ((AnonymousClass4) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
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
            Function2<Transactor, Continuation<? super R>, Object> r42 = this.$block;
            PooledConnectionImpl r12 = this.$connection.element;
            this.label = 1;
            Object r3 = r42.invoke(r12, this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    public static Unit $r8$lambda$2YqOiCofN2j4fCYelVb9B9KXm0Y(ConnectionPoolImpl r0, boolean r1) {
        r0.onTimeout(r1);
        return Unit.INSTANCE;
    }

    public static SQLiteConnection $r8$lambda$9UyvH2443RWiLutCP1RPb0RqEy4(SQLiteDriver r0, String r1) {
        SQLiteConnection r02 = r0.open(r1);
        SQLite.execSQL(r02, "PRAGMA query_only = 1");
        return r02;
    }

    public static SQLiteConnection $r8$lambda$PzkXqksVKGI0H8yDlVsmsrucc0E(SQLiteDriver r0, String r1) {
        return r0.open(r1);
    }

    public static SQLiteConnection $r8$lambda$aE8bRSBLrF1sdN7j0A_811rvq9A(SQLiteDriver r0, String r1) {
        return r0.open(r1);
    }

    public ConnectionPoolImpl(SQLiteDriver r3, String r4, int r5) {
        this.connectionElementKey = new ConnectionElementKey();
        this.connectionThreadLocal = new ThreadLocal();
        Duration.Companion r0 = Duration.Companion;
        this.timeout = DurationKt.toDuration(30, DurationUnit.SECONDS);
        this.onTimeout = 2;
        this.driver = r3;
        Pool r02 = new Pool(1, new ConnectionPoolImpl$$ExternalSyntheticLambda3(r3, r4), r5);
        this.readers = r02;
        this.writers = r02;
    }

    public ConnectionPoolImpl(SQLiteDriver r3, String r4, int r5, int r6, int r7) {
        this.connectionElementKey = new ConnectionElementKey();
        this.connectionThreadLocal = new ThreadLocal();
        Duration.Companion r0 = Duration.Companion;
        this.timeout = DurationKt.toDuration(30, DurationUnit.SECONDS);
        this.onTimeout = 2;
        if (r5 <= 0) goto L9;
        if (r6 <= 0) goto L7;
        this.driver = r3;
        this.readers = new Pool(r5, new ConnectionPoolImpl$$ExternalSyntheticLambda0(r3, r4), r7);
        this.writers = new Pool(r6, new ConnectionPoolImpl$$ExternalSyntheticLambda1(r3, r4), r7);
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("Maximum number of writers must be greater than 0");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("Maximum number of readers must be greater than 0");
        throw null;
    }

    private final CoroutineContext createConnectionContext(PooledConnectionImpl r3) {
        return new ConnectionElement(this.connectionElementKey, r3).plus(ThreadLocal_jvmAndroidKt.asContextElement(this.connectionThreadLocal, r3));
    }

    private final void onTimeout(boolean r4) {
        if (r4 == false) goto L4;
        String r42 = "reader";
    L5:
        StringBuilder r0 = new StringBuilder();
        r0.append("Timed out attempting to acquire a " + r42 + " connection.");
        r0.append("\n\nWriter pool:\n");
        this.writers.dump(r0);
        r0.append("Reader pool:");
        r0.append('\n');
        this.readers.dump(r0);
        SQLite.throwSQLiteException(5, r0.toString());     // Catch: SQLException -> L8
        throw new KotlinNothingValueException();     // Catch: SQLException -> L8
    L8:
        e = move-exception;
        int r3 = this.onTimeout;
        if (r3 != 1) goto L12;
        throw e;
    L12:
        if (r3 != 2) goto L19;
        e.printStackTrace();
        return;
    L19:
        return;
    L4:
        r42 = "writer";
        goto L5
    }

    @Override // androidx.room.coroutines.ConnectionPool, java.lang.AutoCloseable
    public void close() {
        if (this.isClosed == true) goto L6;
        this.isClosed = true;
        this.readers.close();
        this.writers.close();
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [T, androidx.room.coroutines.PooledConnectionImpl] */
    @Override // androidx.room.coroutines.ConnectionPool
    public <R> Object useConnection(boolean r18, Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r19, Continuation<? super R> r20) {
        boolean r1 = r18;
        if ((r20 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r4 = (AnonymousClass1) r20;
        int r5 = r4.label;
        if ((r5 & Integer.MIN_VALUE) == 0) goto L7;
        r4.label = r5 - Integer.MIN_VALUE;
    L8:
        Object r3 = r4.result;
        Object r52 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r6 = r4.label;
        boolean r10 = true;
        if (r6 == 0) goto L31;
        if (r6 == 1) goto L29;
        if (r6 == 2) goto L27;
        if (r6 == 3) goto L21;
        if (r6 != 4) goto L19;
        Ref.ObjectRef r12 = (Ref.ObjectRef) r4.L$1;
        Pool r2 = (Pool) r4.L$0;
        ResultKt.throwOnFailure(r3);     // Catch: Throwable -> L17
    L75:
        PooledConnectionImpl r0 = (PooledConnectionImpl) r12.element;
        if (r0 == null) goto L78;
        r0.markRecycled();
        r0.getDelegate().markReleased();
        r2.recycle(r0.getDelegate());
    L78:
        return r3;
    L17:
        th = move-exception;
        Ref.ObjectRef r62 = r12;
        Throwable r13 = th;
    L98:
        throw r13;     // Catch: Throwable -> L86
    L86:
        th = move-exception;
        PooledConnectionImpl r02 = (PooledConnectionImpl) r62.element;     // Catch: Throwable -> L92
        if (r02 == null) goto L108;
        r02.markRecycled();     // Catch: Throwable -> L92
        r02.getDelegate().markReleased();     // Catch: Throwable -> L92
        r2.recycle(r02.getDelegate());     // Catch: Throwable -> L92
        throw th;
    L108:
        throw th;
    L92:
        th = move-exception;
        ExceptionsKt.addSuppressed(r13, th);
        throw th;
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L21:
        r1 = r4.Z$0;
        ConnectionElementKey r22 = (ConnectionElementKey) r4.L$5;
        Ref.ObjectRef r63 = (Ref.ObjectRef) r4.L$4;
        CoroutineContext r8 = (CoroutineContext) r4.L$3;
        Ref.ObjectRef r9 = (Ref.ObjectRef) r4.L$2;
        Pool r122 = (Pool) r4.L$1;
        Function2<? super Transactor, ? super Continuation<? super R>, ? extends Object> r132 = (Function2) r4.L$0;
        ResultKt.throwOnFailure(r3);     // Catch: Throwable -> L24
        Ref.ObjectRef r82 = r63;
        r62 = r9;
        CoroutineContext r92 = r8;
    L97:
        ConnectionWithLock r32 = ((ConnectionWithLock) r3).markAcquired(r92);     // Catch: Throwable -> L79
        if (this.readers == this.writers) goto L68;
        if (r1 == false) goto L68;
    L69:
        r82.element = new PooledConnectionImpl(r22, r32, r10);     // Catch: Throwable -> L79
        T r14 = r62.element;     // Catch: Throwable -> L79
        if (r14 == 0) goto L82;
        CoroutineContext r03 = createConnectionContext((PooledConnectionImpl) r14);     // Catch: Throwable -> L79
        AnonymousClass4 r15 = new AnonymousClass4(r132, r62, null);     // Catch: Throwable -> L79
        r4.L$0 = r122;     // Catch: Throwable -> L79
        r4.L$1 = r62;     // Catch: Throwable -> L79
        r4.L$2 = null;     // Catch: Throwable -> L79
        r4.L$3 = null;     // Catch: Throwable -> L79
        r4.L$4 = null;     // Catch: Throwable -> L79
        r4.L$5 = null;     // Catch: Throwable -> L79
        r4.label = 4;     // Catch: Throwable -> L79
        r3 = BuildersKt.withContext(r03, r15, r4);     // Catch: Throwable -> L79
        if (r3 != r52) goto L74;
    L73:
        return r52;
    L74:
        r12 = r62;
        r2 = r122;
        goto L75
    L82:
        throw new IllegalArgumentException("Required value was null.");     // Catch: Throwable -> L79
    L68:
        r10 = false;
    L79:
        th = move-exception;
        r13 = th;
    L26:
        r2 = r122;
    L24:
        th = move-exception;
        r13 = th;
        r62 = r9;
        goto L26
    L27:
        ResultKt.throwOnFailure(r3);
        return r3;
    L29:
        ResultKt.throwOnFailure(r3);
        return r3;
    L31:
        ResultKt.throwOnFailure(r3);
        if (this.isClosed == true) goto L95;
        PooledConnectionImpl r33 = this.connectionThreadLocal.get();
        if (r33 != null) goto L39;
        ConnectionElement r34 = (ConnectionElement) r4.getContext().get(this.connectionElementKey);
        if (r34 == null) goto L38;
        r33 = r34.getConnectionWrapper();
        goto L39
    L38:
        r33 = null;
    L39:
        if (r33 == null) goto L56;
        if (r1 == true) goto L47;
        if (r33.isReadOnly() == false) goto L47;
        SQLite.throwSQLiteException(1, "Cannot upgrade connection from reader to writer");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L47:
        if (r4.getContext().get(this.connectionElementKey) != null) goto L52;
        CoroutineContext r04 = createConnectionContext(r33);
        AnonymousClass2 r16 = new AnonymousClass2(r19, r33, null);
        r4.label = 1;
        Object r05 = BuildersKt.withContext(r04, r16, r4);
        if (r05 == r52) goto L73;
        return r05;
    L52:
        r4.label = 2;
        Object r06 = r19.invoke(r33, r4);
        if (r06 == r52) goto L73;
        return r06;
    L56:
        if (r1 == false) goto L58;
        Pool r35 = this.readers;
    L59:
        r62 = new Ref.ObjectRef();
        r92 = r4.getContext();     // Catch: Throwable -> L83
        ConnectionElementKey r123 = this.connectionElementKey;     // Catch: Throwable -> L83
        long r133 = this.timeout;     // Catch: Throwable -> L83
        ConnectionPoolImpl$$ExternalSyntheticLambda2 r152 = new ConnectionPoolImpl$$ExternalSyntheticLambda2(this, r1);     // Catch: Throwable -> L83
        r4.L$0 = r19;     // Catch: Throwable -> L83
        r4.L$1 = r35;     // Catch: Throwable -> L83
        r4.L$2 = r62;     // Catch: Throwable -> L83
        r4.L$3 = r92;     // Catch: Throwable -> L83
        r4.L$4 = r62;     // Catch: Throwable -> L83
        r4.L$5 = r123;     // Catch: Throwable -> L83
        r4.Z$0 = r1;     // Catch: Throwable -> L83
        r4.label = 3;     // Catch: Throwable -> L83
        Object r83 = r35.m947acquireWithTimeoutKLykuaI(r133, r152, r4);     // Catch: Throwable -> L83
        if (r83 == r52) goto L73;
        r132 = r19;
        r22 = r123;
        r122 = r35;
        r3 = r83;
        r82 = r62;
    L83:
        th = move-exception;
        r13 = th;
        r2 = r35;
        goto L98
    L58:
        r35 = this.writers;
        goto L59
    L95:
        SQLite.throwSQLiteException(21, "Connection pool is closed");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        r4 = new AnonymousClass1(this, r20);
        goto L8
    }
}
