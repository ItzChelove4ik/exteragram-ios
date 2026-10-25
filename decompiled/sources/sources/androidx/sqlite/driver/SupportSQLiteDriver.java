package androidx.sqlite.driver;

import androidx.lifecycle.SavedStateHandleSupport$$ExternalSyntheticBUOutline0;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Request$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000bR\u001a\u0010\r\u001a\u00020\f8WX\u0096\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Landroidx/sqlite/driver/SupportSQLiteDriver;", "Landroidx/sqlite/SQLiteDriver;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", "openHelper", "<init>", "(Landroidx/sqlite/db/SupportSQLiteOpenHelper;)V", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", "Landroidx/sqlite/driver/SupportSQLiteConnection;", "open", "(Ljava/lang/String;)Landroidx/sqlite/driver/SupportSQLiteConnection;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "hasConnectionPool", "()Z", "hasConnectionPool$annotations", "()V", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SupportSQLiteDriver implements SQLiteDriver {
    private final SupportSQLiteOpenHelper openHelper;

    public SupportSQLiteDriver(SupportSQLiteOpenHelper r1) {
        this.openHelper = r1;
    }

    @Override // androidx.sqlite.SQLiteDriver
    @JvmName(name = "hasConnectionPool")
    public boolean hasConnectionPool() {
        return true;
    }

    @Override // androidx.sqlite.SQLiteDriver
    public /* bridge */ /* synthetic */ SQLiteConnection open(String r1) {
        return open(r1);
    }

    @Override // androidx.sqlite.SQLiteDriver
    public SupportSQLiteConnection open(String r6) {
        String r0 = this.openHelper.getDatabaseName();
        if (r0 != null) goto L10;
        if (Intrinsics.areEqual(r6, ":memory:") == true) goto L17;
        Request$Builder$$ExternalSyntheticBUOutline0.m("This driver is configured to open an in-memory database but a file-based named '", r6, "' was requested.");
        return null;
    L17:
        return new SupportSQLiteConnection(this.openHelper.getWritableDatabase());
    L10:
        if (Intrinsics.areEqual(r0, r6) == true) goto L17;
        if (Intrinsics.areEqual(StringsKt.substringAfterLast$default(r0, '/', null, 2, null), StringsKt.substringAfterLast$default(r6, '/', null, 2, null)) == true) goto L17;
        SavedStateHandleSupport$$ExternalSyntheticBUOutline0.m("This driver is configured to open a database named '", this.openHelper.getDatabaseName(), "' but '", r6, "' was requested.");
        return null;
    }
}
