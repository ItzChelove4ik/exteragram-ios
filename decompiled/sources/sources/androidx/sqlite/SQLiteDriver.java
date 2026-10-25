package androidx.sqlite;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\b\u001a\u00020\u00078WX\u0096\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/sqlite/SQLiteDriver;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "fileName", "Landroidx/sqlite/SQLiteConnection;", "open", "(Ljava/lang/String;)Landroidx/sqlite/SQLiteConnection;", _UrlKt.FRAGMENT_ENCODE_SET, "hasConnectionPool", "()Z", "hasConnectionPool$annotations", "()V", "sqlite"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public interface SQLiteDriver {
    @JvmName(name = "hasConnectionPool")
    boolean hasConnectionPool();

    SQLiteConnection open(String r1);
}
