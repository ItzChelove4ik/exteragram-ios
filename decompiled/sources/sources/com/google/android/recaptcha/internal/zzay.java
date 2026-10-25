package com.google.android.recaptcha.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.MotionEvent;
import kotlin.Unit;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* loaded from: classes5.dex */
public final class zzay {
    private final int zza;
    private final long zzb;
    private final zzax zzc;
    private long zzd;

    public zzay(Context r1, int r2, long r3, boolean r5, float r6) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = new zzax(r1);
        zzd();
    }

    private final void zzd() {
        SQLiteDatabase r0 = this.zzc.getWritableDatabase();
        zzaju r1 = zzaju.zzb;
        String r2 = zzau.zza(r1);
        String r3 = zzau.zza(r1);
        String r12 = zzau.zza(r1);
        int r10 = this.zza;
        int r4 = RangesKt.coerceAtLeast(1, (int) (r10 * 0.2f));
        int r5 = String.valueOf(r10).length();
        int r6 = r2.length();
        int r7 = r3.length();
        StringBuilder r62 = new StringBuilder(((((((((r5 + 165) + r6) + 25) + r7) + 24) + r12.length()) + 7) + String.valueOf(r4).length()) + 31);
        r62.append("\n        CREATE TRIGGER tgr48932\n        AFTER INSERT ON tbl64158\n        WHEN (SELECT COUNT(*) FROM tbl64158) > ");
        r62.append(r10);
        r62.append("\n        BEGIN\n          DELETE FROM tbl64158 WHERE ");
        r62.append(r2);
        r62.append(" IN (\n            SELECT ");
        r62.append(r3);
        r62.append(" FROM tbl64158 ORDER BY ");
        r62.append(r12);
        r62.append(" LIMIT ");
        r62.append(r4);
        r62.append("\n          );\n        END;\n    ");
        String r102 = StringsKt.trimIndent(r62.toString());
        r0.beginTransaction();
        r0.execSQL("DROP TRIGGER IF EXISTS tgr48932");     // Catch: Throwable -> L6
        r0.execSQL(r102);     // Catch: Throwable -> L6
        Unit r103 = Unit.INSTANCE;     // Catch: Throwable -> L6
        r0.setTransactionSuccessful();     // Catch: Throwable -> L6
        r0.endTransaction();
        return;
    L6:
        th = move-exception;
        r0.endTransaction();
        throw th;
    }

    public final void zza(String r7, MotionEvent r8) {
        long r0 = r8.getEventTime();
        if ((r0 - this.zzd) >= this.zzb) goto L5;
        return;
    L5:
        SQLiteDatabase r2 = this.zzc.getWritableDatabase();
        ContentValues r3 = new ContentValues();
        r3.put(zzau.zza(zzaju.zzc), r7.getBytes(Charsets.UTF_8));
        r3.put(zzau.zza(zzaju.zzf), Long.valueOf(r8.getDownTime()));
        r3.put(zzau.zza(zzaju.zze), Long.valueOf(r8.getEventTime()));
        r3.put(zzau.zza(zzaju.zzd), Integer.valueOf(r8.getAction()));
        r3.put(zzau.zza(zzaju.zzg), Integer.valueOf(r8.getPointerCount()));
        r3.put(zzau.zza(zzaju.zzh), Integer.valueOf(r8.getMetaState()));
        r3.put(zzau.zza(zzaju.zzi), Integer.valueOf(r8.getButtonState()));
        r3.put(zzau.zza(zzaju.zzj), Float.valueOf(r8.getRawX()));
        r3.put(zzau.zza(zzaju.zzk), Float.valueOf(r8.getRawY()));
        r3.put(zzau.zza(zzaju.zzl), Integer.valueOf(r8.getDeviceId()));
        r3.put(zzau.zza(zzaju.zzm), Integer.valueOf(r8.getToolType(r8.getActionIndex())));
        r3.put(zzau.zza(zzaju.zzn), Integer.valueOf(r8.getEdgeFlags()));
        r3.put(zzau.zza(zzaju.zzo), Integer.valueOf(r8.getSource()));
        r3.put(zzau.zza(zzaju.zzp), Float.valueOf(r8.getPressure()));
        r3.put(zzau.zza(zzaju.zzq), Float.valueOf(r8.getSize()));
        r3.put(zzau.zza(zzaju.zzr), Integer.valueOf(r8.getFlags()));
        r2.insert("tbl64158", null, r3);
        this.zzd = r0;
    }

    public final void zzb() {
        this.zzc.getWritableDatabase().delete("tbl64158", null, null);
    }

    public final Cursor zzc(String r1, String[] r2) {
        return this.zzc.getReadableDatabase().rawQuery(r1, r2);
    }
}
