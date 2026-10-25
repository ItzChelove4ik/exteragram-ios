package androidx.room.util;

import androidx.sqlite.SQLiteStatement;
import kotlin.Metadata;

@Metadata(d1 = {"androidx/room/util/SQLiteStatementUtil__StatementUtilKt", "androidx/room/util/SQLiteStatementUtil__StatementUtil_androidKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class SQLiteStatementUtil {
    public static final int columnIndexOf(SQLiteStatement r0, String r1) {
        return SQLiteStatementUtil__StatementUtil_androidKt.columnIndexOf(r0, r1);
    }

    public static final int columnIndexOfCommon(SQLiteStatement r0, String r1) {
        return SQLiteStatementUtil__StatementUtilKt.columnIndexOfCommon(r0, r1);
    }

    public static final int getColumnIndexOrThrow(SQLiteStatement r0, String r1) {
        return SQLiteStatementUtil__StatementUtilKt.getColumnIndexOrThrow(r0, r1);
    }
}
