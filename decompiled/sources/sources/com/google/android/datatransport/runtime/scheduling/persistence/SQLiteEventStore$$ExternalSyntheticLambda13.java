package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes4.dex */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda13 implements SQLiteEventStore.Function {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ TransportContext f$1;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda13(long r1, TransportContext r3) {
        this.f$0 = r1;
        this.f$1 = r3;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object r3) {
        return SQLiteEventStore.$r8$lambda$GHMqzva9B_XnmPprSGyr5JoZ7b8(this.f$0, this.f$1, (SQLiteDatabase) r3);
    }
}
