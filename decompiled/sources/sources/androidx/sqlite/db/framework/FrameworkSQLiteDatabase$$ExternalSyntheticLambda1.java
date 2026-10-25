package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;
import kotlin.jvm.functions.Function4;

/* loaded from: classes.dex */
public final /* synthetic */ class FrameworkSQLiteDatabase$$ExternalSyntheticLambda1 implements Function4 {
    public final /* synthetic */ SupportSQLiteQuery f$0;

    public /* synthetic */ FrameworkSQLiteDatabase$$ExternalSyntheticLambda1(SupportSQLiteQuery r1) {
        this.f$0 = r1;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object r1, Object r2, Object r3, Object r4) {
        return FrameworkSQLiteDatabase.$r8$lambda$lJwI5rfEgbbousRT0mbuHaL1K3E(this.f$0, (SQLiteDatabase) r1, (SQLiteCursorDriver) r2, (String) r3, (SQLiteQuery) r4);
    }
}
