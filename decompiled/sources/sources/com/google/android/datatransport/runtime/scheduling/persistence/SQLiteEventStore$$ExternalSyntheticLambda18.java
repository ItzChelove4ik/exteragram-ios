package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes4.dex */
public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda18 implements SQLiteEventStore.Function {
    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda18() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public final Object apply(Object r1) {
        return Boolean.valueOf(((Cursor) r1).moveToNext());
    }
}
