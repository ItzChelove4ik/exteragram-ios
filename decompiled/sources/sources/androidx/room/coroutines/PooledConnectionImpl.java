package androidx.room.coroutines;

import android.database.SQLException;
import androidx.room.TransactionScope;
import androidx.room.Transactor;
import androidx.room.concurrent.ThreadLocal_jvmAndroidKt;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.InstantKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.sync.Mutex;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u000389:B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJO\u0010\u0014\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\f2-\u0010\u0013\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000e¢\u0006\u0002\b\u0012H\u0082@¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\fH\u0082@¢\u0006\u0004\b\u0017\u0010\u0018J\u0018\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0007H\u0082@¢\u0006\u0004\b\u001a\u0010\u001bJ2\u0010 \u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\u001d\u001a\u00020\u001c2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00028\u00000\u001eH\u0096@¢\u0006\u0004\b \u0010!JM\u0010\"\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\r\u001a\u00020\f2-\u0010\u0013\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000e¢\u0006\u0002\b\u0012H\u0096@¢\u0006\u0004\b\"\u0010\u0015J\u0010\u0010#\u001a\u00020\u0007H\u0096@¢\u0006\u0004\b#\u0010$J\r\u0010%\u001a\u00020\u0016¢\u0006\u0004\b%\u0010&R\u0017\u0010\u0004\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010*\u001a\u0004\b+\u0010,R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010-\u001a\u0004\b\b\u0010.R\u001a\u00101\u001a\b\u0012\u0004\u0012\u0002000/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u0010-R\u0014\u00107\u001a\u0002048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106¨\u0006;"}, d2 = {"Landroidx/room/coroutines/PooledConnectionImpl;", "Landroidx/room/Transactor;", "Landroidx/room/coroutines/RawConnectionAccessor;", "Landroidx/room/coroutines/ConnectionElementKey;", "connectionElementKey", "Landroidx/room/coroutines/ConnectionWithLock;", "delegate", _UrlKt.FRAGMENT_ENCODE_SET, "isReadOnly", "<init>", "(Landroidx/room/coroutines/ConnectionElementKey;Landroidx/room/coroutines/ConnectionWithLock;Z)V", "R", "Landroidx/room/Transactor$SQLiteTransactionType;", "type", "Lkotlin/Function2;", "Landroidx/room/TransactionScope;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "transaction", "(Landroidx/room/Transactor$SQLiteTransactionType;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "beginTransaction", "(Landroidx/room/Transactor$SQLiteTransactionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "success", "endTransaction", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Lkotlin/Function1;", "Landroidx/sqlite/SQLiteStatement;", "usePrepared", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTransaction", "inTransaction", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markRecycled", "()V", "Landroidx/room/coroutines/ConnectionElementKey;", "getConnectionElementKey", "()Landroidx/room/coroutines/ConnectionElementKey;", "Landroidx/room/coroutines/ConnectionWithLock;", "getDelegate", "()Landroidx/room/coroutines/ConnectionWithLock;", "Z", "()Z", "Lkotlin/collections/ArrayDeque;", "Landroidx/room/coroutines/PooledConnectionImpl$TransactionItem;", "transactionStack", "Lkotlin/collections/ArrayDeque;", "isRecycled", "Landroidx/sqlite/SQLiteConnection;", "getRawConnection", "()Landroidx/sqlite/SQLiteConnection;", "rawConnection", "TransactionItem", "TransactionImpl", "StatementWrapper", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConnectionPoolImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/PooledConnectionImpl\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,624:1\n551#1,11:625\n551#1,11:646\n551#1,11:657\n116#2,7:636\n124#2,2:644\n116#2,10:668\n116#2,10:678\n1#3:643\n*S KotlinDebug\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/PooledConnectionImpl\n*L\n434#1:625,11\n443#1:646,11\n445#1:657,11\n435#1:636,7\n435#1:644,2\n490#1:668,10\n507#1:678,10\n*E\n"})
/* loaded from: classes4.dex */
final class PooledConnectionImpl implements Transactor, RawConnectionAccessor {
    private final ConnectionElementKey connectionElementKey;
    private final ConnectionWithLock delegate;
    private final boolean isReadOnly;
    private volatile boolean isRecycled;
    private final ArrayDeque<TransactionItem> transactionStack;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000f\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\r\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010\u001f\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u001f\u0010\u0019J\u000f\u0010 \u001a\u00020\u001aH\u0016¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\tH\u0016¢\u0006\u0004\b\"\u0010#J\u000f\u0010$\u001a\u00020\tH\u0016¢\u0006\u0004\b$\u0010#J\u000f\u0010%\u001a\u00020\tH\u0016¢\u0006\u0004\b%\u0010#R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010&R\u0014\u0010'\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(¨\u0006)"}, d2 = {"Landroidx/room/coroutines/PooledConnectionImpl$StatementWrapper;", "Landroidx/sqlite/SQLiteStatement;", "delegate", "<init>", "(Landroidx/room/coroutines/PooledConnectionImpl;Landroidx/sqlite/SQLiteStatement;)V", _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "bindBlob", "(I[B)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindLong", "(IJ)V", _UrlKt.FRAGMENT_ENCODE_SET, "bindText", "(ILjava/lang/String;)V", "bindNull", "(I)V", "getBlob", "(I)[B", "getLong", "(I)J", "getText", "(I)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "isNull", "(I)Z", "getColumnCount", "()I", "getColumnName", "step", "()Z", "reset", "()V", "clearBindings", "close", "Landroidx/sqlite/SQLiteStatement;", "threadId", "J", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nConnectionPoolImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/PooledConnectionImpl$StatementWrapper\n*L\n1#1,624:1\n611#1,10:625\n611#1,10:635\n611#1,10:645\n611#1,10:655\n611#1,10:665\n611#1,10:675\n611#1,10:685\n611#1,10:695\n611#1,10:705\n611#1,10:715\n611#1,10:725\n611#1,10:735\n611#1,10:745\n611#1,10:755\n611#1,10:765\n611#1,10:775\n611#1,10:785\n*S KotlinDebug\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/PooledConnectionImpl$StatementWrapper\n*L\n568#1:625,10\n572#1:635,10\n576#1:645,10\n580#1:655,10\n584#1:665,10\n586#1:675,10\n588#1:685,10\n590#1:695,10\n592#1:705,10\n594#1:715,10\n596#1:725,10\n598#1:735,10\n600#1:745,10\n602#1:755,10\n604#1:765,10\n606#1:775,10\n608#1:785,10\n*E\n"})
    public final class StatementWrapper implements SQLiteStatement {
        private final SQLiteStatement delegate;
        final /* synthetic */ PooledConnectionImpl this$0;
        private final long threadId;

        public StatementWrapper(PooledConnectionImpl r1, SQLiteStatement r2) {
            this.this$0 = r1;
            this.delegate = r2;
            this.threadId = ThreadLocal_jvmAndroidKt.currentThreadId();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindBlob(int r7, byte[] r8) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L5:
            if (this.threadId != ThreadLocal_jvmAndroidKt.currentThreadId()) goto L8;
            this.delegate.bindBlob(r7, r8);
            return;
        L8:
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindLong(int r7, long r8) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L5:
            if (this.threadId != ThreadLocal_jvmAndroidKt.currentThreadId()) goto L8;
            this.delegate.bindLong(r7, r8);
            return;
        L8:
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindNull(int r7) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L5:
            if (this.threadId != ThreadLocal_jvmAndroidKt.currentThreadId()) goto L8;
            this.delegate.bindNull(r7);
            return;
        L8:
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void bindText(int r7, String r8) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L5:
            if (this.threadId != ThreadLocal_jvmAndroidKt.currentThreadId()) goto L8;
            this.delegate.bindText(r7, r8);
            return;
        L8:
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void clearBindings() {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L5:
            if (this.threadId != ThreadLocal_jvmAndroidKt.currentThreadId()) goto L8;
            this.delegate.clearBindings();
            return;
        L8:
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.sqlite.SQLiteStatement, java.lang.AutoCloseable
        public void close() {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L5:
            if (this.threadId != ThreadLocal_jvmAndroidKt.currentThreadId()) goto L8;
            this.delegate.close();
            return;
        L8:
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public byte[] getBlob(int r7) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L5:
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) goto L7;
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L7:
            return this.delegate.getBlob(r7);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public int getColumnCount() {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return 0;
        L5:
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) goto L7;
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return 0;
        L7:
            return this.delegate.getColumnCount();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getColumnName(int r7) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L5:
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) goto L7;
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L7:
            return this.delegate.getColumnName(r7);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public long getLong(int r7) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return 0;
        L5:
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) goto L7;
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return 0;
        L7:
            return this.delegate.getLong(r7);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public String getText(int r7) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L5:
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) goto L7;
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return null;
        L7:
            return this.delegate.getText(r7);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean isNull(int r7) {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return false;
        L5:
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) goto L7;
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return false;
        L7:
            return this.delegate.isNull(r7);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public void reset() {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return;
        L5:
            if (this.threadId != ThreadLocal_jvmAndroidKt.currentThreadId()) goto L8;
            this.delegate.reset();
            return;
        L8:
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public boolean step() {
            if (PooledConnectionImpl.access$isRecycled$p(this.this$0) == false) goto L5;
            SQLite.throwSQLiteException(21, "Statement is recycled");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return false;
        L5:
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) goto L7;
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            InstantKt$$ExternalSyntheticBUOutline0.m();
            return false;
        L7:
            return this.delegate.step();
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J2\u0010\f\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00062\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00028\u00010\tH\u0096@¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u0011\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/room/coroutines/PooledConnectionImpl$TransactionImpl;", "T", "Landroidx/room/TransactionScope;", "Landroidx/room/coroutines/RawConnectionAccessor;", "<init>", "(Landroidx/room/coroutines/PooledConnectionImpl;)V", "R", _UrlKt.FRAGMENT_ENCODE_SET, "sql", "Lkotlin/Function1;", "Landroidx/sqlite/SQLiteStatement;", "block", "usePrepared", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/sqlite/SQLiteConnection;", "getRawConnection", "()Landroidx/sqlite/SQLiteConnection;", "rawConnection", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nConnectionPoolImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/PooledConnectionImpl$TransactionImpl\n+ 2 ConnectionPoolImpl.kt\nandroidx/room/coroutines/PooledConnectionImpl\n+ 3 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,624:1\n551#2,11:625\n551#2,11:636\n116#3,10:647\n*S KotlinDebug\n*F\n+ 1 ConnectionPoolImpl.kt\nandroidx/room/coroutines/PooledConnectionImpl$TransactionImpl\n*L\n539#1:625,11\n541#1:636,11\n545#1:647,10\n*E\n"})
    public final class TransactionImpl<T> implements TransactionScope<T>, RawConnectionAccessor {
        final /* synthetic */ PooledConnectionImpl this$0;

        public TransactionImpl(PooledConnectionImpl r1) {
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

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/room/coroutines/PooledConnectionImpl$TransactionItem;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "shouldRollback", "<init>", "(IZ)V", "I", "getId", "()I", "Z", "getShouldRollback", "()Z", "setShouldRollback", "(Z)V", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class TransactionItem {
        private final int id;
        private boolean shouldRollback;

        public TransactionItem(int r1, boolean r2) {
            this.id = r1;
            this.shouldRollback = r2;
        }

        public final int getId() {
            return this.id;
        }

        public final boolean getShouldRollback() {
            return this.shouldRollback;
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
    @DebugMetadata(c = "androidx.room.coroutines.PooledConnectionImpl", f = "ConnectionPoolImpl.kt", i = {0, 0}, l = {629}, m = "beginTransaction", n = {"type", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1"})
    /* renamed from: androidx.room.coroutines.PooledConnectionImpl$beginTransaction$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PooledConnectionImpl this$0;

        public AnonymousClass1(PooledConnectionImpl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PooledConnectionImpl.access$beginTransaction(this.this$0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PooledConnectionImpl", f = "ConnectionPoolImpl.kt", i = {0, 0}, l = {629}, m = "endTransaction", n = {"$this$withLock_u24default$iv", "success"}, s = {"L$0", "Z$0"})
    /* renamed from: androidx.room.coroutines.PooledConnectionImpl$endTransaction$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01231 extends ContinuationImpl {
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PooledConnectionImpl this$0;

        public C01231(PooledConnectionImpl r1, Continuation<? super C01231> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PooledConnectionImpl.access$endTransaction(this.this$0, false, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PooledConnectionImpl", f = "ConnectionPoolImpl.kt", i = {0, 1, 4}, l = {464, 468, 482, 482, 482}, m = "transaction", n = {"block", "success", "exception"}, s = {"L$0", "I$0", "L$0"})
    /* renamed from: androidx.room.coroutines.PooledConnectionImpl$transaction$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01241<R> extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PooledConnectionImpl this$0;

        public C01241(PooledConnectionImpl r1, Continuation<? super C01241> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PooledConnectionImpl.access$transaction(this.this$0, null, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.PooledConnectionImpl", f = "ConnectionPoolImpl.kt", i = {0, 0, 0}, l = {640}, m = "usePrepared", n = {"sql", "block", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2"})
    /* renamed from: androidx.room.coroutines.PooledConnectionImpl$usePrepared$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01251<R> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PooledConnectionImpl this$0;

        public C01251(PooledConnectionImpl r1, Continuation<? super C01251> r2) {
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

    public PooledConnectionImpl(ConnectionElementKey r1, ConnectionWithLock r2, boolean r3) {
        this.connectionElementKey = r1;
        this.delegate = r2;
        this.isReadOnly = r3;
        this.transactionStack = new ArrayDeque();
    }

    public static final /* synthetic */ Object access$beginTransaction(PooledConnectionImpl r0, Transactor.SQLiteTransactionType r1, Continuation r2) {
        return r0.beginTransaction(r1, r2);
    }

    public static final /* synthetic */ Object access$endTransaction(PooledConnectionImpl r0, boolean r1, Continuation r2) {
        return r0.endTransaction(r1, r2);
    }

    public static final /* synthetic */ boolean access$isRecycled$p(PooledConnectionImpl r0) {
        return r0.isRecycled;
    }

    public static final /* synthetic */ Object access$transaction(PooledConnectionImpl r0, Transactor.SQLiteTransactionType r1, Function2 r2, Continuation r3) {
        return r0.transaction(r1, r2, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10, types: [kotlinx.coroutines.sync.Mutex] */
    private final Object beginTransaction(Transactor.SQLiteTransactionType r7, Continuation<? super Unit> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r1 = (AnonymousClass1) r8;
        int r2 = r1.label;
        if ((r2 & Integer.MIN_VALUE) == 0) goto L7;
        r1.label = r2 - Integer.MIN_VALUE;
    L8:
        Object r82 = r1.result;
        Object r22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r3 = r1.label;
        if (r3 == 0) goto L14;
        if (r3 != 1) goto L12;
        ?? r72 = (Mutex) r1.L$1;
        Transactor.SQLiteTransactionType r12 = (Transactor.SQLiteTransactionType) r1.L$0;
        ResultKt.throwOnFailure(r82);
        ConnectionWithLock r83 = r72;
        r7 = r12;
    L38:
        int r13 = this.transactionStack.size();     // Catch: Throwable -> L26
        if (this.transactionStack.isEmpty() == false) goto L32;
        int r73 = WhenMappings.$EnumSwitchMapping$0[r7.ordinal()];     // Catch: Throwable -> L26
        if (r73 != 1) goto L22;
        SQLite.execSQL(this.delegate, "BEGIN DEFERRED TRANSACTION");     // Catch: Throwable -> L26
    L33:
        this.transactionStack.addLast(new TransactionItem(r13, false));     // Catch: Throwable -> L26
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L26
        r83.unlock(null);
        return r6;
    L22:
        if (r73 != 2) goto L24;
        SQLite.execSQL(this.delegate, "BEGIN IMMEDIATE TRANSACTION");     // Catch: Throwable -> L26
        goto L33
    L24:
        if (r73 != 3) goto L29;
        SQLite.execSQL(this.delegate, "BEGIN EXCLUSIVE TRANSACTION");     // Catch: Throwable -> L26
        goto L33
    L29:
        throw new NoWhenBranchMatchedException();     // Catch: Throwable -> L26
    L32:
        SQLite.execSQL(this.delegate, "SAVEPOINT '" + r13 + '\'');     // Catch: Throwable -> L26
    L26:
        th = move-exception;
        r83.unlock(null);
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r82);
        r83 = this.delegate;
        r1.L$0 = r7;
        r1.L$1 = r83;
        r1.label = 1;
        if (r83.lock(null, r1) != r22) goto L38;
        return r22;
    L7:
        r1 = new AnonymousClass1(this, r8);
        goto L8
    }

    private final Object endTransaction(boolean r8, Continuation<? super Unit> r9) {
        if ((r9 instanceof C01231) == false) goto L7;
        C01231 r2 = (C01231) r9;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.label = r3 - Integer.MIN_VALUE;
    L8:
        Object r92 = r2.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.label;
        if (r4 == 0) goto L14;
        if (r4 != 1) goto L12;
        r8 = r2.Z$0;
        Mutex r22 = (Mutex) r2.L$0;
        ResultKt.throwOnFailure(r92);
    L43:
    L28:
        th = move-exception;
        r22.unlock(null);
        throw th;
    L19:
        if (this.transactionStack.isEmpty() == true) goto L40;
        TransactionItem r93 = (TransactionItem) CollectionsKt.removeLast(this.transactionStack);     // Catch: Throwable -> L28
        if (r8 == true) goto L23;
    L31:
        boolean r82 = this.transactionStack.isEmpty();     // Catch: Throwable -> L28
        ConnectionWithLock r7 = this.delegate;
        if (r82 == false) goto L35;
        SQLite.execSQL(r7, "ROLLBACK TRANSACTION");     // Catch: Throwable -> L28
    L36:
        Unit r72 = Unit.INSTANCE;     // Catch: Throwable -> L28
        r22.unlock(null);
        return r72;
    L35:
        SQLite.execSQL(r7, "ROLLBACK TRANSACTION TO SAVEPOINT '" + r93.getId() + '\'');     // Catch: Throwable -> L28
        goto L36
    L23:
        if (r93.getShouldRollback() == true) goto L31;
        boolean r83 = this.transactionStack.isEmpty();     // Catch: Throwable -> L28
        ConnectionWithLock r73 = this.delegate;
        if (r83 == false) goto L30;
        SQLite.execSQL(r73, "END TRANSACTION");     // Catch: Throwable -> L28
        goto L36
    L30:
        SQLite.execSQL(r73, "RELEASE SAVEPOINT '" + r93.getId() + '\'');     // Catch: Throwable -> L28
        goto L36
    L40:
        throw new IllegalStateException("Not in a transaction");     // Catch: Throwable -> L28
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r92);
        ConnectionWithLock r94 = this.delegate;
        r2.L$0 = r94;
        r2.Z$0 = r8;
        r2.label = 1;
        if (r94.lock(null, r2) != r32) goto L17;
        return r32;
    L17:
        r22 = r94;
    L7:
        r2 = new C01231(this, r9);
        goto L8
    }

    private final <R> Object transaction(Transactor.SQLiteTransactionType r11, Function2<? super TransactionScope<R>, ? super Continuation<? super R>, ? extends Object> r12, Continuation<? super R> r13) {
        if ((r13 instanceof C01241) == false) goto L7;
        C01241 r0 = (C01241) r13;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r132 = r0.result;
        Object r14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r4 = false;
        if (r2 == 0) goto L31;
        if (r2 == 1) goto L30;
        if (r2 == 2) goto L25;
        if (r2 != 3) goto L14;
    L23:
        Object r10 = r0.L$0;
        ResultKt.throwOnFailure(r132);
        return r10;
    L14:
        if (r2 == 4) goto L23;
        if (r2 == 5) goto L18;
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        Throwable r102 = (Throwable) r0.L$1;
        th = (Throwable) r0.L$0;
        ResultKt.throwOnFailure(r132);     // Catch: SQLException -> L21
        throw r102;
    L21:
        SQLException e = e;
    L55:
        if (th == null) goto L58;
        ExceptionsKt.addSuppressed(th, e);
        throw r102;
    L58:
        throw e;
    L25:
        int r112 = r0.I$0;
        ResultKt.throwOnFailure(r132);     // Catch: Throwable -> L28
    L41:
        if (r112 == 0) goto L43;
        r4 = true;
    L43:
        r0.L$0 = r132;
        r0.label = 3;
        if (endTransaction(r4, r0) == r14) goto L51;
        return r132;
    L51:
        return r14;
    L30:
        r12 = (Function2) r0.L$0;
        ResultKt.throwOnFailure(r132);
    L37:
        TransactionImpl r113 = new TransactionImpl(this);     // Catch: Throwable -> L28
        r0.L$0 = null;     // Catch: Throwable -> L28
        r0.I$0 = 1;     // Catch: Throwable -> L28
        r0.label = 2;     // Catch: Throwable -> L28
        r132 = r12.invoke(r113, r0);     // Catch: Throwable -> L28
        if (r132 == r14) goto L51;
        r112 = 1;
        goto L41
    L31:
        ResultKt.throwOnFailure(r132);
        if (r11 != null) goto L34;
        r11 = Transactor.SQLiteTransactionType.DEFERRED;
    L34:
        r0.L$0 = r12;
        r0.label = 1;
        if (beginTransaction(r11, r0) != r14) goto L37;
    L28:
        th = th;
        throw th;     // Catch: Throwable -> L48
    L48:
        th = move-exception;
        r0.L$0 = th;     // Catch: SQLException -> L53
        r0.L$1 = th;     // Catch: SQLException -> L53
        r0.label = 5;     // Catch: SQLException -> L53
        if (endTransaction(false, r0) == r14) goto L51;
        throw th;
    L53:
        e = move-exception;
        e = e;
        r102 = th;
    L7:
        r0 = new C01241(this, r13);
        goto L8
    }

    public final ConnectionElementKey getConnectionElementKey() {
        return this.connectionElementKey;
    }

    public final ConnectionWithLock getDelegate() {
        return this.delegate;
    }

    @Override // androidx.room.coroutines.RawConnectionAccessor
    public SQLiteConnection getRawConnection() {
        return this.delegate;
    }

    @Override // androidx.room.Transactor
    public Object inTransaction(Continuation<? super Boolean> r4) {
        if (access$isRecycled$p(this) == true) goto L19;
        ConnectionElement r42 = (ConnectionElement) r4.getContext().get(getConnectionElementKey());
        if (r42 != null) goto L7;
    L17:
        SQLite.throwSQLiteException(21, "Attempted to use connection on a different coroutine");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        if (r42.getConnectionWrapper() != this) goto L17;
        if (this.transactionStack.isEmpty() == true) goto L11;
    L14:
        boolean r3 = true;
    L16:
        return Boxing.boxBoolean(r3);
    L11:
        if (this.delegate.inTransaction() == true) goto L14;
        r3 = false;
        goto L16
    L19:
        SQLite.throwSQLiteException(21, "Connection is recycled");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final boolean isReadOnly() {
        return this.isReadOnly;
    }

    public final void markRecycled() {
        if (this.isRecycled == true) goto L8;
        this.isRecycled = true;
        if (this.delegate.inTransaction() == false) goto L9;
        SQLite.execSQL(this.delegate, "ROLLBACK TRANSACTION");
        return;
    L9:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v5, types: [kotlinx.coroutines.sync.Mutex] */
    @Override // androidx.room.PooledConnection
    public <R> Object usePrepared(String r7, Function1<? super SQLiteStatement, ? extends R> r8, Continuation<? super R> r9) {
        if ((r9 instanceof C01251) == false) goto L7;
        C01251 r0 = (C01251) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ?? r72 = (Mutex) r0.L$2;
        r8 = (Function1) r0.L$1;
        String r02 = (String) r0.L$0;
        ResultKt.throwOnFailure(r92);
        ConnectionWithLock r93 = r72;
        r7 = r02;
    L41:
        StatementWrapper r03 = new StatementWrapper(this, this.delegate.prepare(r7));     // Catch: Throwable -> L28
        R r6 = r8.invoke(r03);     // Catch: Throwable -> L30
        AutoCloseableKt.closeFinally(r03, null);     // Catch: Throwable -> L28
        r93.unlock(null);
        return r6;
    L30:
        th = move-exception;
        throw th;     // Catch: Throwable -> L32
    L32:
        th = move-exception;
        AutoCloseableKt.closeFinally(r03, th);     // Catch: Throwable -> L28
        throw th;     // Catch: Throwable -> L28
    L28:
        th = move-exception;
        r93.unlock(null);
        throw th;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r92);
        if (access$isRecycled$p(this) == true) goto L39;
        ConnectionElement r94 = (ConnectionElement) r0.getContext().get(getConnectionElementKey());
        if (r94 != null) goto L19;
    L37:
        SQLite.throwSQLiteException(21, "Attempted to use connection on a different coroutine");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L19:
        if (r94.getConnectionWrapper() != this) goto L37;
        r93 = this.delegate;
        r0.L$0 = r7;
        r0.L$1 = r8;
        r0.L$2 = r93;
        r0.label = 1;
        if (r93.lock(null, r0) != r12) goto L41;
        return r12;
    L39:
        SQLite.throwSQLiteException(21, "Connection is recycled");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        r0 = new C01251(this, r9);
        goto L8
    }

    @Override // androidx.room.Transactor
    public <R> Object withTransaction(Transactor.SQLiteTransactionType r5, Function2<? super TransactionScope<R>, ? super Continuation<? super R>, ? extends Object> r6, Continuation<? super R> r7) {
        if (access$isRecycled$p(this) == true) goto L12;
        ConnectionElement r0 = (ConnectionElement) r7.getContext().get(getConnectionElementKey());
        if (r0 != null) goto L7;
    L10:
        SQLite.throwSQLiteException(21, "Attempted to use connection on a different coroutine");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L7:
        if (r0.getConnectionWrapper() != this) goto L10;
        return transaction(r5, r6, r7);
    L12:
        SQLite.throwSQLiteException(21, "Connection is recycled");
        InstantKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }
}
