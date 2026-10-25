package com.google.android.recaptcha.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Collections;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;

/* loaded from: classes5.dex */
public final class zzax extends SQLiteOpenHelper {
    public zzax(Context r3) {
        super(r3, null, null, 1);
    }

    private static final String zza() {
        List r0 = ArraysKt.toMutableList(zzaju.values());
        CollectionsKt.removeFirstOrNull(r0);
        CollectionsKt.removeLastOrNull(r0);
        Collections.shuffle(r0);
        String r02 = CollectionsKt.joinToString$default(r0, ", ", null, null, 0, null, zzaw.zza, 30, null);
        StringBuilder r2 = new StringBuilder(String.valueOf(r02).length() + 38);
        r2.append("CREATE TABLE IF NOT EXISTS tbl64158 (");
        r2.append(r02);
        r2.append(")");
        return r2.toString();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase r3) {
        r3.execSQL(zza());
        String r2 = zzau.zza(zzaju.zzd);
        StringBuilder r1 = new StringBuilder(r2.length() + 52);
        r1.append("CREATE INDEX IF NOT EXISTS idx_action ON tbl64158(");
        r1.append(r2);
        r1.append(");");
        r3.execSQL(r1.toString());
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase r1, int r2, int r3) {
    }
}
