package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.Map;

/* loaded from: classes4.dex */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda17 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Map f$2;
    public final /* synthetic */ ClientMetrics.Builder f$3;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda17(SQLiteEventStore r1, String r2, Map r3, ClientMetrics.Builder r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object r4) {
        return SQLiteEventStore.$r8$lambda$wkPqtdo8abVVVntIT0LL_IpuNhI(this.f$0, this.f$1, this.f$2, this.f$3, (SQLiteDatabase) r4);
    }
}
