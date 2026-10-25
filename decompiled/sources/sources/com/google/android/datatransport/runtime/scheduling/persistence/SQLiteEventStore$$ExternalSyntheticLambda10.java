package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes4.dex */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda10 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ TransportContext f$1;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda10(SQLiteEventStore r1, TransportContext r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object r2) {
        return SQLiteEventStore.m1810$r8$lambda$c5yRpmc2PusxokESpsItypEVU(this.f$0, this.f$1, (SQLiteDatabase) r2);
    }
}
