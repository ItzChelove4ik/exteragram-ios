package androidx.room.util;

import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/sqlite/SQLiteConnection;", "connection", _UrlKt.FRAGMENT_ENCODE_SET, "dropFtsSyncTriggers", "(Landroidx/sqlite/SQLiteConnection;)V", "room-runtime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "androidx/room/util/DBUtil")
@SourceDebugExtension({"SMAP\nDBUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,171:1\n1869#2,2:172\n*S KotlinDebug\n*F\n+ 1 DBUtil.kt\nandroidx/room/util/DBUtil__DBUtilKt\n*L\n109#1:172,2\n*E\n"})
/* loaded from: classes4.dex */
abstract /* synthetic */ class DBUtil__DBUtilKt {
    public static final void dropFtsSyncTriggers(SQLiteConnection r6) {
        List r0 = CollectionsKt.createListBuilder();
        SQLiteStatement r1 = r6.prepare("SELECT name FROM sqlite_master WHERE type = 'trigger'");
    L22:
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        AutoCloseableKt.closeFinally(r1, th);
        throw th;
    L4:
        if (r1.step() == false) goto L8;
        r0.add(r1.getText(0));     // Catch: Throwable -> L6
        goto L22
    L8:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L6
        AutoCloseableKt.closeFinally(r1, null);
        Iterator r02 = CollectionsKt.build(r0).iterator();
    L11:
        if (r02.hasNext() == false) goto L15;
        String r12 = (String) r02.next();
        if (StringsKt.startsWith$default(r12, "room_fts_content_sync_", false, 2, null) == false) goto L11;
        SQLite.execSQL(r6, "DROP TRIGGER IF EXISTS " + r12);
        goto L11
    }
}
