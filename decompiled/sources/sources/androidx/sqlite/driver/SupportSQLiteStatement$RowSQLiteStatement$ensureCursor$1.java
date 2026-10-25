package androidx.sqlite.driver;

import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.driver.SupportSQLiteStatement;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"androidx/sqlite/driver/SupportSQLiteStatement$RowSQLiteStatement$ensureCursor$1", "Landroidx/sqlite/db/SupportSQLiteQuery;", "Landroidx/sqlite/db/SupportSQLiteProgram;", "statement", _UrlKt.FRAGMENT_ENCODE_SET, "bindTo", "(Landroidx/sqlite/db/SupportSQLiteProgram;)V", _UrlKt.FRAGMENT_ENCODE_SET, "getSql", "()Ljava/lang/String;", "sql", "sqlite-framework"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SupportSQLiteStatement$RowSQLiteStatement$ensureCursor$1 implements SupportSQLiteQuery {
    final /* synthetic */ SupportSQLiteStatement.RowSQLiteStatement this$0;

    public SupportSQLiteStatement$RowSQLiteStatement$ensureCursor$1(SupportSQLiteStatement.RowSQLiteStatement r1) {
        this.this$0 = r1;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public void bindTo(SupportSQLiteProgram r7) {
        int r0 = SupportSQLiteStatement.RowSQLiteStatement.access$getBindingTypes$p(this.this$0).length;
        int r2 = 1;
    L3:
        if (r2 >= r0) goto L21;
        int r3 = SupportSQLiteStatement.RowSQLiteStatement.access$getBindingTypes$p(this.this$0)[r2];
        if (r3 != 1) goto L7;
        r7.bindLong(r2, SupportSQLiteStatement.RowSQLiteStatement.access$getLongBindings$p(this.this$0)[r2]);
    L20:
        r2 = r2 + 1;
        goto L3
    L7:
        if (r3 != 2) goto L9;
        r7.bindDouble(r2, SupportSQLiteStatement.RowSQLiteStatement.access$getDoubleBindings$p(this.this$0)[r2]);
        goto L20
    L9:
        if (r3 != 3) goto L11;
        r7.bindString(r2, SupportSQLiteStatement.RowSQLiteStatement.access$getStringBindings$p(this.this$0)[r2]);
        goto L20
    L11:
        if (r3 != 4) goto L13;
        r7.bindBlob(r2, SupportSQLiteStatement.RowSQLiteStatement.access$getBlobBindings$p(this.this$0)[r2]);
        goto L20
    L13:
        if (r3 != 5) goto L20;
        r7.bindNull(r2);
        goto L20
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public String getSql() {
        return this.this$0.getSql();
    }
}
