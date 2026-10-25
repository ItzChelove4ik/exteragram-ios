package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes4.dex */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda7 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda7(SQLiteEventStore r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object r4) {
        return SQLiteEventStore.$r8$lambda$oWyA6AJGDizu9ZmA0KcBicBGgCY(this.f$0, this.f$1, (SQLiteDatabase) r4);
    }
}
