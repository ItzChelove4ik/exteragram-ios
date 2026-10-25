package androidx.room.util;

import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.Metadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0007¨\u0006\u0006"}, d2 = {"getLastInsertedRowId", _UrlKt.FRAGMENT_ENCODE_SET, "connection", "Landroidx/sqlite/SQLiteConnection;", "getTotalChangedRows", _UrlKt.FRAGMENT_ENCODE_SET, "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "SQLiteConnectionUtil")
/* loaded from: classes.dex */
public abstract class SQLiteConnectionUtil {
    public static final long getLastInsertedRowId(SQLiteConnection r3) {
        if (getTotalChangedRows(r3) != 0) goto L6;
        return -1;
    L6:
        SQLiteStatement r32 = r3.prepare("SELECT last_insert_rowid()");
        r32.step();     // Catch: Throwable -> L10
        long r0 = r32.getLong(0);     // Catch: Throwable -> L10
        AutoCloseableKt.closeFinally(r32, null);
        return r0;
    L10:
        th = move-exception;
        throw th;     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        AutoCloseableKt.closeFinally(r32, th);
        throw th;
    }

    public static final int getTotalChangedRows(SQLiteConnection r2) {
        SQLiteStatement r22 = r2.prepare("SELECT changes()");
        r22.step();     // Catch: Throwable -> L6
        int r0 = (int) r22.getLong(0);
        AutoCloseableKt.closeFinally(r22, null);
        return r0;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        AutoCloseableKt.closeFinally(r22, th);
        throw th;
    }
}
