package androidx.sqlite.db.framework;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

/* loaded from: classes4.dex */
public final /* synthetic */ class FrameworkSQLiteDatabase$$ExternalSyntheticLambda0 implements SQLiteDatabase.CursorFactory {
    public final /* synthetic */ SupportSQLiteQuery f$0;

    public /* synthetic */ FrameworkSQLiteDatabase$$ExternalSyntheticLambda0(SupportSQLiteQuery r1) {
        this.f$0 = r1;
    }

    @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
    public final Cursor newCursor(SQLiteDatabase r1, SQLiteCursorDriver r2, String r3, SQLiteQuery r4) {
        return FrameworkSQLiteDatabase.$r8$lambda$VAdvOeOMNnZUpsfStEPetT5hHTM(this.f$0, r1, r2, r3, r4);
    }
}
