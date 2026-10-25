package androidx.room.coroutines;

import androidx.collection.LruCache;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.Iterator;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u000234B!\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u001a\u001a\u00020\u000e2\n\u0010\u0019\u001a\u00060\u0017j\u0002`\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ\u000f\u0010\u001c\u001a\u00020\tH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001f\u001a\u00020\u001eH\u0096\u0001¢\u0006\u0004\b\u001f\u0010 J\u001a\u0010\u0004\u001a\u00020\u000e2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0096A¢\u0006\u0004\b\u0004\u0010#J\u001a\u0010$\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0096\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010&\u001a\u00020\u000e2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0096\u0001¢\u0006\u0004\b&\u0010'R\u0014\u0010\u0003\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010(R\u0014\u0010\u0004\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0018\u0010-\u001a\u0004\u0018\u00010,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u001a\u00100\u001a\b\u0018\u00010/R\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u001e8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b2\u0010 ¨\u00065"}, d2 = {"Landroidx/room/coroutines/ConnectionWithLock;", "Landroidx/sqlite/SQLiteConnection;", "Lkotlinx/coroutines/sync/Mutex;", "delegate", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "preparedStatementCacheSize", "<init>", "(Landroidx/sqlite/SQLiteConnection;Lkotlinx/coroutines/sync/Mutex;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Landroidx/sqlite/SQLiteStatement;", "prepare", "(Ljava/lang/String;)Landroidx/sqlite/SQLiteStatement;", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "Lkotlin/coroutines/CoroutineContext;", "context", "markAcquired", "(Lkotlin/coroutines/CoroutineContext;)Landroidx/room/coroutines/ConnectionWithLock;", "markReleased", "()Landroidx/room/coroutines/ConnectionWithLock;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "dump", "(Ljava/lang/StringBuilder;)V", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "inTransaction", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "owner", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "(Ljava/lang/Object;)Z", "unlock", "(Ljava/lang/Object;)V", "Landroidx/sqlite/SQLiteConnection;", "Lkotlinx/coroutines/sync/Mutex;", "acquireCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", _UrlKt.FRAGMENT_ENCODE_SET, "acquireThrowable", "Ljava/lang/Throwable;", "Landroidx/room/coroutines/ConnectionWithLock$PreparedStatementCache;", "preparedStatementCache", "Landroidx/room/coroutines/ConnectionWithLock$PreparedStatementCache;", "isLocked", "PreparedStatementCache", "CachedStatement", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConnectionPoolImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/ConnectionWithLock\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,624:1\n1#2:625\n1869#3,2:626\n*S KotlinDebug\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/ConnectionWithLock\n*L\n360#1:626,2\n*E\n"})
/* loaded from: classes4.dex */
final class ConnectionWithLock implements SQLiteConnection, Mutex {
    private CoroutineContext acquireCoroutineContext;
    private Throwable acquireThrowable;
    private final SQLiteConnection delegate;
    private final Mutex lock;
    private final PreparedStatementCache preparedStatementCache;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\"\u0010\f\u001a\u00020\u00052\b\b\u0001\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0096\u0001¢\u0006\u0004\b\f\u0010\rJ\"\u0010\u000f\u001a\u00020\u00052\b\b\u0001\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u000eH\u0096\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\"\u0010\u0012\u001a\u00020\u00052\b\b\u0001\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0011H\u0096\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u001a\u0010\u0014\u001a\u00020\u00052\b\b\u0001\u0010\t\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\n2\b\b\u0001\u0010\t\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0018\u001a\u00020\u000e2\b\b\u0001\u0010\t\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001b\u001a\u00020\u001a2\b\b\u0001\u0010\t\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001a\u0010\u001d\u001a\u00020\u00112\b\b\u0001\u0010\t\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u001a\u0010\u001f\u001a\u00020\u001a2\b\b\u0001\u0010\t\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u001f\u0010\u001cJ\u0010\u0010 \u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b \u0010!J\u001a\u0010\"\u001a\u00020\u00112\b\b\u0001\u0010\t\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\"\u0010\u001eJ\u0010\u0010#\u001a\u00020\u001aH\u0096\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0005H\u0096\u0001¢\u0006\u0004\b%\u0010\u0007J\u0010\u0010&\u001a\u00020\u0005H\u0096\u0001¢\u0006\u0004\b&\u0010\u0007R\u0017\u0010\u0002\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010'\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Landroidx/room/coroutines/ConnectionWithLock$CachedStatement;", "Landroidx/sqlite/SQLiteStatement;", "delegate", "<init>", "(Landroidx/sqlite/SQLiteStatement;)V", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "value", "bindBlob", "(I[B)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindLong", "(IJ)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindText", "(ILjava/lang/String;)V", "bindNull", "(I)V", "getBlob", "(I)[B", "getLong", "(I)J", _UrlKt.FRAGMENT_ENCODE_SET, "getBoolean", "(I)Z", "getText", "(I)Ljava/lang/String;", "isNull", "getColumnCount", "()I", "getColumnName", "step", "()Z", "reset", "clearBindings", "Landroidx/sqlite/SQLiteStatement;", "getDelegate", "()Landroidx/sqlite/SQLiteStatement;", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CachedStatement implements SQLiteStatement {
        private final SQLiteStatement delegate;

        public CachedStatement(SQLiteStatement r1) {
            this.delegate = r1;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindBlob(int r1, byte[] r2) {
            this.delegate.bindBlob(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindLong(int r1, long r2) {
            this.delegate.bindLong(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindNull(int r1) {
            this.delegate.bindNull(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindText(int r1, String r2) {
            this.delegate.bindText(r1, r2);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void clearBindings() {
            this.delegate.clearBindings();
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            this.delegate.reset();
            this.delegate.clearBindings();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int r1) {
            return this.delegate.getBlob(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean getBoolean(int r1) {
            return this.delegate.getBoolean(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            return this.delegate.getColumnCount();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int r1) {
            return this.delegate.getColumnName(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int r1) {
            return this.delegate.getLong(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int r1) {
            return this.delegate.getText(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int r1) {
            return this.delegate.isNull(r1);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
            this.delegate.reset();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            return this.delegate.step();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0014J*\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0014¨\u0006\u0010"}, d2 = {"Landroidx/room/coroutines/ConnectionWithLock$PreparedStatementCache;", "Landroidx/collection/LruCache;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/sqlite/SQLiteStatement;", "maxSize", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/room/coroutines/ConnectionWithLock;I)V", "create", PluginsConstants.Settings.KEY, "entryRemoved", _UrlKt.FRAGMENT_ENCODE_SET, "evicted", _UrlKt.FRAGMENT_ENCODE_SET, "oldValue", "newValue", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public final class PreparedStatementCache extends LruCache<String, SQLiteStatement> {
        final /* synthetic */ ConnectionWithLock this$0;

        public PreparedStatementCache(ConnectionWithLock r1, int r2) {
            this.this$0 = r1;
            super(r2);
        }

        /* renamed from: create, reason: avoid collision after fix types in other method */
        public SQLiteStatement create2(String r1) {
            return ConnectionWithLock.access$getDelegate$p(this.this$0).prepare(r1);
        }

        @Override // androidx.collection.LruCache
        public /* bridge */ /* synthetic */ SQLiteStatement create(String r1) {
            return create2(r1);
        }

        @Override // androidx.collection.LruCache
        public /* bridge */ /* synthetic */ void entryRemoved(boolean r1, String r2, SQLiteStatement r3, SQLiteStatement r4) {
            entryRemoved2(r1, r2, r3, r4);
        }

        /* renamed from: entryRemoved, reason: avoid collision after fix types in other method */
        public void entryRemoved2(boolean r1, String r2, SQLiteStatement r3, SQLiteStatement r4) {
            r3.close();
            super.entryRemoved(r1, r2, r3, r4);
        }
    }

    public ConnectionWithLock(SQLiteConnection r1, Mutex r2, int r3) {
        this.delegate = r1;
        this.lock = r2;
        if (r3 <= 0) goto L5;
        PreparedStatementCache r12 = new PreparedStatementCache(this, r3);
    L6:
        this.preparedStatementCache = r12;
        return;
    L5:
        r12 = null;
        goto L6
    }

    public /* synthetic */ ConnectionWithLock(SQLiteConnection r1, Mutex r2, int r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 2) == 0) goto L5;
        r2 = MutexKt.Mutex$default(false, 1, null);
    L5:
        this(r1, r2, r3);
    }

    public static final /* synthetic */ SQLiteConnection access$getDelegate$p(ConnectionWithLock r0) {
        return r0.delegate;
    }

    @Override // androidx.sqlite.SQLiteConnection, java.lang.AutoCloseable
    public void close() {
        PreparedStatementCache r0 = this.preparedStatementCache;
        if (r0 == null) goto L5;
        r0.evictAll();
    L5:
        this.delegate.close();
    }

    public final void dump(StringBuilder r6) {
        if (this.acquireCoroutineContext == null) goto L5;
    L8:
        r6.append("\t\tStatus: Acquired connection");
        r6.append('\n');
        CoroutineContext r0 = this.acquireCoroutineContext;
        if (r0 == null) goto L11;
        r6.append("\t\tCoroutine: " + r0);
        r6.append('\n');
    L11:
        Throwable r02 = this.acquireThrowable;
        if (r02 == null) goto L18;
        r6.append("\t\tAcquired:");
        r6.append('\n');
        Iterator r03 = CollectionsKt.drop(StringsKt.lines(ExceptionsKt.stackTraceToString(r02)), 1).iterator();
    L15:
        if (r03.hasNext() == false) goto L18;
        r6.append("\t\t" + ((String) r03.next()));
        r6.append('\n');
    L18:
        if (this.preparedStatementCache == null) goto L22;
        r6.append("\t\tPrepared Statement Cache Size: " + this.preparedStatementCache.size());
        r6.append('\n');
        return;
    L22:
        return;
    L5:
        if (this.acquireThrowable != null) goto L8;
        r6.append("\t\tStatus: Free connection");
        r6.append('\n');
        goto L18
    }

    @Override // androidx.sqlite.SQLiteConnection
    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean isLocked() {
        return this.lock.isLocked();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public Object lock(Object r1, Continuation<? super Unit> r2) {
        return this.lock.lock(r1, r2);
    }

    public final ConnectionWithLock markAcquired(CoroutineContext r1) {
        this.acquireCoroutineContext = r1;
        this.acquireThrowable = new Throwable();
        return this;
    }

    public final ConnectionWithLock markReleased() {
        this.acquireCoroutineContext = null;
        this.acquireThrowable = null;
        return this;
    }

    @Override // androidx.sqlite.SQLiteConnection
    public SQLiteStatement prepare(String r2) {
        PreparedStatementCache r0 = this.preparedStatementCache;
        if (r0 == null) goto L7;
        return new CachedStatement(r0.get(r2));
    L7:
        return this.delegate.prepare(r2);
    }

    public String toString() {
        return this.delegate.toString();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public boolean tryLock(Object r1) {
        return this.lock.tryLock(r1);
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(Object r1) {
        this.lock.unlock(r1);
    }
}
