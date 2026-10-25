package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/* loaded from: classes5.dex */
final /* synthetic */ class zzas implements Runnable {
    private final /* synthetic */ zzaw zza;

    public /* synthetic */ zzas(zzaw r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzaw r5 = this.zza;
        SQLiteDatabase r0 = r5.zze();     // Catch: SQLiteException -> L5
        ContentValues r1 = new ContentValues();     // Catch: SQLiteException -> L5
        r1.put("elapsed_time", 0L);     // Catch: SQLiteException -> L5
        r0.update("raw_events", r1, null, null);     // Catch: SQLiteException -> L5
        return;
    L5:
        e = move-exception;
        r5.zzu.zzaW().zzb().zzb("Failed to remove elapsed times from raw events table", e);
    }
}
