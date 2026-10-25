package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.Map;

/* loaded from: classes4.dex */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda23 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ Map f$1;
    public final /* synthetic */ ClientMetrics.Builder f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda23(SQLiteEventStore r1, Map r2, ClientMetrics.Builder r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object r3) {
        return SQLiteEventStore.m1813$r8$lambda$RbOgrU7H41WTgNTtm08JTmKZL8(this.f$0, this.f$1, this.f$2, (Cursor) r3);
    }
}
