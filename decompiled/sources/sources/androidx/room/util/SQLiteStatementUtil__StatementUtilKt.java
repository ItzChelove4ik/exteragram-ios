package androidx.room.util;

import androidx.core.os.BundleKt$$ExternalSyntheticBUOutline0;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001b\u0010\u0007\u001a\u00020\u0004*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0007\u0010\u0006¨\u0006\b"}, d2 = {"Landroidx/sqlite/SQLiteStatement;", "stmt", _UrlKt.FRAGMENT_ENCODE_SET, "name", _UrlKt.FRAGMENT_ENCODE_SET, "getColumnIndexOrThrow", "(Landroidx/sqlite/SQLiteStatement;Ljava/lang/String;)I", "columnIndexOfCommon", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/util/SQLiteStatementUtil")
@SourceDebugExtension({"SMAP\nStatementUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StatementUtil.kt\nandroidx/room/util/SQLiteStatementUtil__StatementUtilKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,112:1\n1#2:113\n*E\n"})
/* loaded from: classes.dex */
abstract /* synthetic */ class SQLiteStatementUtil__StatementUtilKt {
    public static final int columnIndexOfCommon(SQLiteStatement r3, String r4) {
        int r0 = r3.getColumnCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        if (Intrinsics.areEqual(r4, r3.getColumnName(r1)) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r1;
    L8:
        return -1;
    }

    public static final int getColumnIndexOrThrow(SQLiteStatement r11, String r12) {
        int r0 = SQLiteStatementUtil.columnIndexOf(r11, r12);
        if (r0 < 0) goto L5;
        return r0;
    L5:
        int r02 = r11.getColumnCount();
        ArrayList r1 = new ArrayList(r02);
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L8;
        r1.add(r11.getColumnName(r2));
        r2 = r2 + 1;
        goto L6
    L8:
        BundleKt$$ExternalSyntheticBUOutline0.m("Column '", r12, "' does not exist. Available columns: [", CollectionsKt.joinToString$default(r1, null, null, null, 0, null, null, 63, null), 93);
        return 0;
    }
}
