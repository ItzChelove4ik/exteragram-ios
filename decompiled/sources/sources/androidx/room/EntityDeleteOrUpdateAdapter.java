package androidx.room;

import androidx.room.util.SQLiteConnectionUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.Metadata;
import kotlin.jdk7.AutoCloseableKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H$¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00028\u0000H$¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/room/EntityDeleteOrUpdateAdapter;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "createQuery", "()Ljava/lang/String;", "Landroidx/sqlite/SQLiteStatement;", "statement", "entity", _UrlKt.FRAGMENT_ENCODE_SET, "bind", "(Landroidx/sqlite/SQLiteStatement;Ljava/lang/Object;)V", "Landroidx/sqlite/SQLiteConnection;", "connection", _UrlKt.FRAGMENT_ENCODE_SET, "handle", "(Landroidx/sqlite/SQLiteConnection;Ljava/lang/Object;)I", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class EntityDeleteOrUpdateAdapter<T> {
    public EntityDeleteOrUpdateAdapter() {
    }

    public abstract void bind(SQLiteStatement r1, T r2);

    public abstract String createQuery();

    public final int handle(SQLiteConnection r2, T r3) {
        if (r3 != null) goto L5;
        return 0;
    L5:
        SQLiteStatement r0 = r2.prepare(createQuery());
        bind(r0, r3);     // Catch: Throwable -> L9
        r0.step();     // Catch: Throwable -> L9
        AutoCloseableKt.closeFinally(r0, null);
        return SQLiteConnectionUtil.getTotalChangedRows(r2);
    L9:
        th = move-exception;
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        AutoCloseableKt.closeFinally(r0, th);
        throw th;
    }
}
