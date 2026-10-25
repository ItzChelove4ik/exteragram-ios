package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzgj extends com.google.android.gms.internal.measurement.zzcb {
    final /* synthetic */ zzgl zza;

    public zzgj(zzgl r2, Context r3, String r4) {
        Objects.requireNonNull(r2);
        this.zza = r2;
        super(r3, "google_app_measurement_local.db", null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    L12:
        e = move-exception;
        throw e;
    L4:
        zzic r0 = this.zza.zzu;
        r0.zzaW().zzb().zza("Opening the local database failed, dropping and recreating it");
        r0.zzc();
        if (r0.zzaZ().getDatabasePath("google_app_measurement_local.db").delete() == true) goto L14;
        r0.zzaW().zzb().zzb("Failed to delete corrupted local db file", "google_app_measurement_local.db");
    L14:
        return super.getWritableDatabase();
    L9:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzb("Failed to open local database. Events will bypass local storage", e);
        return null;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase r1) {
        zzax.zzb(this.zza.zzu.zzaW(), r1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase r1, int r2, int r3) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase r7) {
        String[] r5 = zzgl.zzr();
        zzax.zza(this.zza.zzu.zzaW(), r7, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", r5);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase r1, int r2, int r3) {
    }
}
