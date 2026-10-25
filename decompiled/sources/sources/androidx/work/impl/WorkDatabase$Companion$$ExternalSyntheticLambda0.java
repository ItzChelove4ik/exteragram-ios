package androidx.work.impl;

import android.content.Context;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.work.impl.WorkDatabase;

/* loaded from: classes.dex */
public final /* synthetic */ class WorkDatabase$Companion$$ExternalSyntheticLambda0 implements SupportSQLiteOpenHelper.Factory {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ WorkDatabase$Companion$$ExternalSyntheticLambda0(Context r1) {
        this.f$0 = r1;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Factory
    public final SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration r1) {
        return WorkDatabase.Companion.$r8$lambda$d9uKx9JAMiDvrmcMenix4D379lU(this.f$0, r1);
    }
}
