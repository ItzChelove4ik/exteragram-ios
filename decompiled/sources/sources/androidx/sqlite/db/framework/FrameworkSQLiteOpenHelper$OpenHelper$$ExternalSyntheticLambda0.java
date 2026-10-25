package androidx.sqlite.db.framework;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper;

/* loaded from: classes.dex */
public final /* synthetic */ class FrameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda0 implements DatabaseErrorHandler {
    public final /* synthetic */ SupportSQLiteOpenHelper.Callback f$0;
    public final /* synthetic */ FrameworkSQLiteOpenHelper.DBRefHolder f$1;

    public /* synthetic */ FrameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda0(SupportSQLiteOpenHelper.Callback r1, FrameworkSQLiteOpenHelper.DBRefHolder r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.database.DatabaseErrorHandler
    public final void onCorruption(SQLiteDatabase r2) {
        FrameworkSQLiteOpenHelper.OpenHelper.m963$r8$lambda$HkATHkNnDWiWKR94KFirzR7ST8(this.f$0, this.f$1, r2);
    }
}
