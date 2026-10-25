package androidx.room.util;

import android.os.Build;
import androidx.sqlite.SQLiteStatement;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u0019\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"columnIndexOf", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/sqlite/SQLiteStatement;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "findColumnIndexBySuffix", "findColumnIndexBySuffix$SQLiteStatementUtil__StatementUtil_androidKt", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/util/SQLiteStatementUtil")
/* loaded from: classes.dex */
abstract /* synthetic */ class SQLiteStatementUtil__StatementUtil_androidKt {
    public static final int columnIndexOf(SQLiteStatement r2, String r3) {
        int r0 = SQLiteStatementUtil.columnIndexOfCommon(r2, r3);
        if (r0 < 0) goto L5;
        return r0;
    L5:
        int r02 = SQLiteStatementUtil.columnIndexOfCommon(r2, "`" + r3 + '`');
        if (r02 < 0) goto L9;
        return r02;
    L9:
        return findColumnIndexBySuffix$SQLiteStatementUtil__StatementUtil_androidKt(r2, r3);
    }

    private static final int findColumnIndexBySuffix$SQLiteStatementUtil__StatementUtil_androidKt(SQLiteStatement r11, String r12) {
        if (Build.VERSION.SDK_INT <= 25) goto L5;
    L20:
        return -1;
    L5:
        if (r12.length() == 0) goto L20;
        int r0 = r11.getColumnCount();
        String r1 = "." + r12;
        String r4 = "." + r12 + '`';
        int r6 = 0;
    L8:
        if (r6 >= r0) goto L20;
        String r7 = r11.getColumnName(r6);
        if (r7.length() < (r12.length() + 2)) goto L19;
        if (StringsKt.endsWith$default(r7, r1, false, 2, null) == true) goto L18;
        if (r7.charAt(0) != '`') goto L19;
        if (StringsKt.endsWith$default(r7, r4, false, 2, null) == false) goto L19;
    L18:
        return r6;
    L19:
        r6 = r6 + 1;
        goto L8
    }
}
