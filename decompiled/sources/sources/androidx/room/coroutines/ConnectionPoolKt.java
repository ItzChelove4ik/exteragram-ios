package androidx.room.coroutines;

import androidx.sqlite.SQLiteDriver;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0000\u001a2\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0000¨\u0006\u000b"}, d2 = {"newSingleConnectionPool", "Landroidx/room/coroutines/ConnectionPool;", "driver", "Landroidx/sqlite/SQLiteDriver;", "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "preparedStatementCacheSize", _UrlKt.FRAGMENT_ENCODE_SET, "newConnectionPool", "maxNumOfReaders", "maxNumOfWriters", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public abstract class ConnectionPoolKt {
    public static final ConnectionPool newConnectionPool(SQLiteDriver r6, String r7, int r8, int r9, int r10) {
        return new ConnectionPoolImpl(r6, r7, r8, r9, r10);
    }

    public static final ConnectionPool newSingleConnectionPool(SQLiteDriver r1, String r2, int r3) {
        return new ConnectionPoolImpl(r1, r2, r3);
    }
}
