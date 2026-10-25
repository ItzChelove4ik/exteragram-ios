package androidx.sqlite;

import android.database.SQLException;
import kotlin.Metadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u001f\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/sqlite/SQLiteConnection;", _UrlKt.FRAGMENT_ENCODE_SET, "sql", _UrlKt.FRAGMENT_ENCODE_SET, "execSQL", "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/String;)V", _UrlKt.FRAGMENT_ENCODE_SET, "errorCode", "errorMsg", _UrlKt.FRAGMENT_ENCODE_SET, "throwSQLiteException", "(ILjava/lang/String;)Ljava/lang/Void;", "sqlite"}, k = 2, mv = {2, 1, 0}, xi = 48)
@JvmName(name = "SQLite")
@SourceDebugExtension({"SMAP\nSQLite.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SQLite.kt\nandroidx/sqlite/SQLite\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,69:1\n1#2:70\n*E\n"})
/* loaded from: classes.dex */
public abstract class SQLite {
    public static final void execSQL(SQLiteConnection r1, String r2) {
        SQLiteStatement r12 = r1.prepare(r2);
        r12.step();     // Catch: Throwable -> L6
        AutoCloseableKt.closeFinally(r12, null);
        return;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        AutoCloseableKt.closeFinally(r12, th);
        throw th;
    }

    public static final Void throwSQLiteException(int r3, String r4) {
        StringBuilder r0 = new StringBuilder();
        r0.append("Error code: " + r3);
        if (r4 == null) goto L6;
        r0.append(", message: ".concat(r4));
    L6:
        throw new SQLException(r0.toString());
    }
}
