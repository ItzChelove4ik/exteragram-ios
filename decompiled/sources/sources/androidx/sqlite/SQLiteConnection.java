package androidx.sqlite;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00060\u0001j\u0002`\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Landroidx/sqlite/SQLiteConnection;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "inTransaction", _UrlKt.FRAGMENT_ENCODE_SET, "prepare", "Landroidx/sqlite/SQLiteStatement;", "sql", _UrlKt.FRAGMENT_ENCODE_SET, "close", _UrlKt.FRAGMENT_ENCODE_SET, "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public interface SQLiteConnection extends AutoCloseable {
    @Override // java.lang.AutoCloseable
    void close();

    boolean inTransaction();

    SQLiteStatement prepare(String r1);
}
