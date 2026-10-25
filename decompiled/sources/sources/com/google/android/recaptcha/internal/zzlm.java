package com.google.android.recaptcha.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes5.dex */
public final class zzlm extends SQLiteOpenHelper implements zzln {
    public static final zzll zza = null;
    private static final int zzb = 0;
    private static final String zzc = null;
    private static zzlm zzd;

    static {
        zzll r0 = new zzll(null);
        zza = r0;
        zzb = zzll.zza(r0, "18.9.2");
        zzc = zzll.zzb(r0, "18.9.2");
    }

    public /* synthetic */ zzlm(Context r3, DefaultConstructorMarker r4) {
        super(r3, zzc, null, zzb);
    }

    public static final /* synthetic */ zzlm zze() {
        return zzd;
    }

    public static final /* synthetic */ void zzf(zzlm r0) {
        zzd = r0;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase r1) {
        r1.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase r1, int r2, int r3) {
        r1.execSQL("DROP TABLE IF EXISTS ce");
        r1.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase r1, int r2, int r3) {
        r1.execSQL("DROP TABLE IF EXISTS ce");
        r1.execSQL("CREATE TABLE ce (id INTEGER PRIMARY KEY,ts BIGINT NOT NULL,ss TEXT NOT NULL)");
    }

    @Override // com.google.android.recaptcha.internal.zzln
    public final long zza(zzlo r4) {
        ContentValues r0 = new ContentValues();
        r0.put("ss", r4.zza());
        r0.put(HlsSegmentFormat.TS, Long.valueOf(r4.zzb()));
        return getWritableDatabase().insert("ce", null, r0);
    }

    @Override // com.google.android.recaptcha.internal.zzln
    public final int zzb() {
        Cursor r2 = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM ce", null);
        int r0 = -1;
    L7:
        th = move-exception;
        r2.close();
        throw th;
    L4:
        if (r2.moveToNext() == false) goto L10;
        r0 = r2.getInt(0);     // Catch: Throwable -> L7 Exception -> L12
    L10:
        r2.close();
        return r0;
    }

    @Override // com.google.android.recaptcha.internal.zzln
    public final List zzc() {
        Cursor r8 = getReadableDatabase().query("ce", null, null, null, null, null, "ts ASC");
        List r0 = new ArrayList();
    L14:
        if (r8.moveToNext() == false) goto L10;
        int r1 = r8.getInt(r8.getColumnIndexOrThrow("id"));     // Catch: Throwable -> L7 Exception -> L9
        r0.add(new zzlo(r8.getString(r8.getColumnIndexOrThrow("ss")), r8.getLong(r8.getColumnIndexOrThrow(HlsSegmentFormat.TS)), r1));     // Catch: Throwable -> L7 Exception -> L9
    L10:
        r8.close();
        return r0;
    L7:
        th = move-exception;
        r8.close();
        throw th;
    L9:
        r0 = CollectionsKt.emptyList();     // Catch: Throwable -> L7
        goto L10
    }

    @Override // com.google.android.recaptcha.internal.zzln
    public final int zzd(List r10) {
        if (r10.isEmpty() == false) goto L6;
        return 0;
    L6:
        String r102 = String.valueOf(CollectionsKt.joinToString$default(r10, ", ", "(", ")", 0, null, zzlk.zza, 24, null));
        return getWritableDatabase().delete("ce", "id IN ".concat(r102), null);
    }
}
