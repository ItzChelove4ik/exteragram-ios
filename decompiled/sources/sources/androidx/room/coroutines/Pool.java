package androidx.room.coroutines;

import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import com.android.dx.io.Opcodes;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ&\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u0004H\u0086@¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0010H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\n¢\u0006\u0004\b\u0019\u0010\fJ\u0019\u0010\u001d\u001a\u00020\n2\n\u0010\u001c\u001a\u00060\u001aj\u0002`\u001b¢\u0006\u0004\b\u001d\u0010\u001eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001f\u001a\u0004\b \u0010!R\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\"\u001a\u0004\b#\u0010$R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b%\u0010!R\u0018\u0010(\u001a\u00060&j\u0002`'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010\u001fR\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u001c\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u001a\u00105\u001a\b\u0012\u0004\u0012\u00020\u0010048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106¨\u00067"}, d2 = {"Landroidx/room/coroutines/Pool;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlin/Function0;", "Landroidx/sqlite/SQLiteConnection;", "connectionFactory", "preparedStatementCacheSize", "<init>", "(ILkotlin/jvm/functions/Function0;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "tryOpenNewConnectionLocked", "()V", "Lkotlin/time/Duration;", "timeout", "onTimeout", "Landroidx/room/coroutines/ConnectionWithLock;", "acquireWithTimeout-KLykuaI", "(JLkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireWithTimeout", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "connection", "recycle", "(Landroidx/room/coroutines/ConnectionWithLock;)V", "close", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "dump", "(Ljava/lang/StringBuilder;)V", "I", "getCapacity", "()I", "Lkotlin/jvm/functions/Function0;", "getConnectionFactory", "()Lkotlin/jvm/functions/Function0;", "getPreparedStatementCacheSize", "Ljava/util/concurrent/locks/ReentrantLock;", "Landroidx/room/concurrent/ReentrantLock;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "size", _UrlKt.FRAGMENT_ENCODE_SET, "isClosed", "Z", _UrlKt.FRAGMENT_ENCODE_SET, "connections", "[Landroidx/room/coroutines/ConnectionWithLock;", "Lkotlinx/coroutines/sync/Semaphore;", "connectionPermits", "Lkotlinx/coroutines/sync/Semaphore;", "Lkotlin/collections/ArrayDeque;", "availableConnections", "Lkotlin/collections/ArrayDeque;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConnectionPoolImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/Pool\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ReentrantLock.kt\nandroidx/room/concurrent/ReentrantLockKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,624:1\n1#2:625\n28#3,5:626\n28#3,5:631\n28#3,3:636\n32#3:641\n28#3,3:642\n32#3:648\n13472#4,2:639\n13537#4,3:645\n*S KotlinDebug\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/Pool\n*L\n255#1:626,5\n285#1:631,5\n290#1:636,3\n290#1:641\n298#1:642,3\n298#1:648\n292#1:639,2\n309#1:645,3\n*E\n"})
/* loaded from: classes4.dex */
final class Pool {
    private final ArrayDeque<ConnectionWithLock> availableConnections;
    private final int capacity;
    private final Function0<SQLiteConnection> connectionFactory;
    private final Semaphore connectionPermits;
    private final ConnectionWithLock[] connections;
    private boolean isClosed;
    private final ReentrantLock lock;
    private final int preparedStatementCacheSize;
    private int size;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.Pool", f = "ConnectionPoolImpl.kt", i = {}, l = {Opcodes.INVOKE_CUSTOM_RANGE}, m = "acquire", n = {}, s = {})
    /* renamed from: androidx.room.coroutines.Pool$acquire$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ Pool this$0;

        public AnonymousClass1(Pool r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.acquire(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Pool(int r2, Function0<? extends SQLiteConnection> r3, int r4) {
        this.capacity = r2;
        this.connectionFactory = r3;
        this.preparedStatementCacheSize = r4;
        this.lock = new ReentrantLock();
        this.connections = new ConnectionWithLock[r2];
        this.connectionPermits = SemaphoreKt.Semaphore$default(r2, 0, 2, null);
        this.availableConnections = new ArrayDeque(r2);
    }

    private final void tryOpenNewConnectionLocked() {
        if (this.size < this.capacity) goto L5;
        return;
    L5:
        ConnectionWithLock r2 = new ConnectionWithLock(this.connectionFactory.invoke(), null, this.preparedStatementCacheSize, 2, null);
        ConnectionWithLock[] r0 = this.connections;
        int r1 = this.size;
        this.size = r1 + 1;
        r0[r1] = r2;
        this.availableConnections.addLast(r2);
    }

    public final Object acquire(Continuation<? super ConnectionWithLock> r5) {
        if ((r5 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r5;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r52);
    L36:
        ReentrantLock r53 = this.lock;     // Catch: Throwable -> L28
        r53.lock();     // Catch: Throwable -> L28
    L23:
        th = move-exception;
        r53.unlock();     // Catch: Throwable -> L28
        throw th;     // Catch: Throwable -> L28
    L19:
        if (this.isClosed == false) goto L21;
        SQLite.throwSQLiteException(21, "Connection pool is closed");     // Catch: Throwable -> L23
        throw new KotlinNothingValueException();     // Catch: Throwable -> L23
    L21:
        if (this.availableConnections.isEmpty() == false) goto L25;
        tryOpenNewConnectionLocked();     // Catch: Throwable -> L23
    L25:
        ConnectionWithLock r02 = this.availableConnections.removeLast();     // Catch: Throwable -> L23
        r53.unlock();     // Catch: Throwable -> L28
        return r02;
    L28:
        th = move-exception;
        this.connectionPermits.release();
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r52);
        Semaphore r54 = this.connectionPermits;
        r0.label = 1;
        if (r54.acquire(r0) != r12) goto L36;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r5);
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0058 -> B:24:0x005a). Please report as a decompilation issue!!! */
    /* renamed from: acquireWithTimeout-KLykuaI, reason: not valid java name */
    public final Object m947acquireWithTimeoutKLykuaI(long r8, Function0<Unit> r10, Continuation<? super ConnectionWithLock> r11) {
        if ((r11 instanceof Pool$acquireWithTimeout$1) == false) goto L7;
        Pool$acquireWithTimeout$1 r0 = (Pool$acquireWithTimeout$1) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        r8 = r0.J$0;
        Ref.ObjectRef r102 = (Ref.ObjectRef) r0.L$1;
        Function0<Unit> r22 = (Function0) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L14
    L24:
        Ref.ObjectRef r113 = r102;
        r10 = r22;
        Pool$acquireWithTimeout$1 r23 = r0;
        Throwable r02 = null;
    L43:
    L31:
        th = move-exception;
        ConnectionWithLock r9 = (ConnectionWithLock) r113.element;
        if (r9 == null) goto L42;
        recycle(r9);
    L42:
        throw th;
    L29:
        if ((r02 instanceof TimeoutCancellationException) == false) goto L33;
        r10.invoke();     // Catch: Throwable -> L31
    L37:
        r0 = r23;
    L19:
        Ref.ObjectRef r114 = new Ref.ObjectRef();
        Pool$acquireWithTimeout$2 r24 = new Pool$acquireWithTimeout$2(r114, this, null);     // Catch: Throwable -> L25
        r0.L$0 = r10;     // Catch: Throwable -> L25
        r0.L$1 = r114;     // Catch: Throwable -> L25
        r0.J$0 = r8;     // Catch: Throwable -> L25
        r0.label = 1;     // Catch: Throwable -> L25
        if (TimeoutKt.m3523withTimeoutKLykuaI(r8, r24, r0) != r12) goto L23;
        return r12;
    L23:
        r22 = r10;
        r102 = r114;
    L25:
        th = move-exception;
        r113 = r114;
        r10 = r10;
        r23 = r0;
        r02 = th;
        goto L43
    L33:
        if (r02 != null) goto L38;
        T r115 = r113.element;     // Catch: Throwable -> L31
        if (r115 == 0) goto L37;
        return r115;
    L38:
        throw r02;     // Catch: Throwable -> L31
    L14:
        th = move-exception;
        r113 = r102;
        r10 = r22;
        r23 = r0;
        r02 = th;
        goto L43
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r112);
    L7:
        r0 = new Pool$acquireWithTimeout$1(this, r11);
        goto L8
    }

    public final void close() {
        ReentrantLock r0 = this.lock;
        r0.lock();
        this.isClosed = true;     // Catch: Throwable -> L8
        ConnectionWithLock[] r4 = this.connections;     // Catch: Throwable -> L8
        int r1 = r4.length;     // Catch: Throwable -> L8
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L11;
        ConnectionWithLock r3 = r4[r2];     // Catch: Throwable -> L8
        if (r3 == null) goto L10;
        r3.close();     // Catch: Throwable -> L8
    L10:
        r2 = r2 + 1;     // Catch: Throwable -> L8
        goto L4
    L11:
        Unit r42 = Unit.INSTANCE;     // Catch: Throwable -> L8
        r0.unlock();
        return;
    L8:
        th = move-exception;
        r0.unlock();
        throw th;
    }

    public final void dump(StringBuilder r15) {
        ReentrantLock r1 = this.lock;
        r1.lock();
        List r2 = CollectionsKt.createListBuilder();     // Catch: Throwable -> L6
        int r3 = this.availableConnections.size();     // Catch: Throwable -> L6
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r5 >= r3) goto L8;
        r2.add(this.availableConnections.get(r5));     // Catch: Throwable -> L6
        r5 = r5 + 1;     // Catch: Throwable -> L6
        goto L4
    L8:
        List r22 = CollectionsKt.build(r2);     // Catch: Throwable -> L6
        r15.append('\t' + super.toString() + " (");     // Catch: Throwable -> L6
        r15.append("capacity=" + this.capacity + ", ");     // Catch: Throwable -> L6
        r15.append("permits=" + this.connectionPermits.getAvailablePermits() + ", ");     // Catch: Throwable -> L6
        r15.append("queue=(size=" + r22.size() + ")[" + CollectionsKt.joinToString$default(r22, null, null, null, 0, null, null, 63, null) + ']');     // Catch: Throwable -> L6
        r15.append(")");     // Catch: Throwable -> L6
        r15.append('\n');     // Catch: Throwable -> L6
        ConnectionWithLock[] r14 = this.connections;     // Catch: Throwable -> L6
        int r23 = r14.length;     // Catch: Throwable -> L6
        int r32 = 0;
    L9:
        if (r4 >= r23) goto L18;
        ConnectionWithLock r52 = r14[r4];     // Catch: Throwable -> L6
        r32 = r32 + 1;     // Catch: Throwable -> L6
        StringBuilder r6 = new StringBuilder();     // Catch: Throwable -> L6
        r6.append("\t\t[");     // Catch: Throwable -> L6
        r6.append(r32);     // Catch: Throwable -> L6
        r6.append("] - ");     // Catch: Throwable -> L6
        if (r52 == null) goto L13;
        String r7 = r52.toString();     // Catch: Throwable -> L6
    L14:
        r6.append(r7);     // Catch: Throwable -> L6
        r15.append(r6.toString());     // Catch: Throwable -> L6
        r15.append('\n');     // Catch: Throwable -> L6
        if (r52 == null) goto L17;
        r52.dump(r15);     // Catch: Throwable -> L6
    L17:
        r4 = r4 + 1;     // Catch: Throwable -> L6
        goto L9
    L13:
        r7 = null;
        goto L14
    L18:
        Unit r142 = Unit.INSTANCE;     // Catch: Throwable -> L6
        r1.unlock();
        return;
    L6:
        th = move-exception;
        r1.unlock();
        throw th;
    }

    public final void recycle(ConnectionWithLock r3) {
        ReentrantLock r0 = this.lock;
        r0.lock();
        this.availableConnections.addLast(r3);     // Catch: Throwable -> L6
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L6
        r0.unlock();
        this.connectionPermits.release();
        return;
    L6:
        th = move-exception;
        r0.unlock();
        throw th;
    }
}
