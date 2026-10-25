package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes4.dex */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda15 implements SQLiteEventStore.Function {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ LogEventDropped.Reason f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda15(String r1, LogEventDropped.Reason r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object r5) {
        return SQLiteEventStore.m1816$r8$lambda$m2CidbNtM3iXXm7FvzU6_pgJWc(this.f$0, this.f$1, this.f$2, (SQLiteDatabase) r5);
    }
}
