package androidx.sqlite.db.framework;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import kotlin.jvm.functions.Function4;

/* loaded from: classes.dex */
public final /* synthetic */ class FrameworkSQLiteDatabase$$ExternalSyntheticLambda2 implements SQLiteDatabase.CursorFactory {
    public final /* synthetic */ Function4 f$0;

    public /* synthetic */ FrameworkSQLiteDatabase$$ExternalSyntheticLambda2(Function4 r1) {
        this.f$0 = r1;
    }

    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
    public final Cursor newCursor(SQLiteDatabase r1, SQLiteCursorDriver r2, String r3, SQLiteQuery r4) {
        return FrameworkSQLiteDatabase.m962$r8$lambda$TcD1j6gYKY4LbAnrkCXD1zFcJk(this.f$0, r1, r2, r3, r4);
    }
}
