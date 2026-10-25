package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;

/* loaded from: classes5.dex */
public abstract class zzax {
    public static void zza(zzgu r10, SQLiteDatabase r11, String r12, String r13, String r14, String[] r15) {
        if (r10 == null) goto L56;
        Cursor r1 = null;
        SQLiteDatabase r2 = r11;
        Cursor r112 = r2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{r12}, null, null, null);     // Catch: Throwable -> L15 SQLiteException -> L17
        boolean r0 = r112.moveToFirst();     // Catch: Throwable -> L11 SQLiteException -> L13
        r112.close();
        if (r0 == true) goto L60;
    L25:
        r2.execSQL(r13);
    L60:
        HashSet r4 = new HashSet();     // Catch: SQLiteException -> L35
        StringBuilder r6 = new StringBuilder(r12.length() + 22);     // Catch: SQLiteException -> L35
        r6.append("SELECT * FROM ");     // Catch: SQLiteException -> L35
        r6.append(r12);     // Catch: SQLiteException -> L35
        r6.append(" LIMIT 0");     // Catch: SQLiteException -> L35
        Cursor r16 = r2.rawQuery(r6.toString(), null);     // Catch: SQLiteException -> L35
        Collections.addAll(r4, r16.getColumnNames());     // Catch: Throwable -> L48
        r16.close();     // Catch: SQLiteException -> L35
        String[] r142 = r14.split(",");     // Catch: SQLiteException -> L35
        int r02 = r142.length;     // Catch: SQLiteException -> L35
        int r17 = 0;
        int r3 = 0;
    L29:
        if (r3 >= r02) goto L37;
        String r5 = r142[r3];     // Catch: SQLiteException -> L35
        if (r4.remove(r5) == false) goto L33;
        r3 = r3 + 1;     // Catch: SQLiteException -> L35
        goto L29
    L33:
        StringBuilder r03 = new StringBuilder((r12.length() + 35) + String.valueOf(r5).length());     // Catch: SQLiteException -> L35
        r03.append("Table ");     // Catch: SQLiteException -> L35
        r03.append(r12);     // Catch: SQLiteException -> L35
        r03.append(" is missing required column: ");     // Catch: SQLiteException -> L35
        r03.append(r5);     // Catch: SQLiteException -> L35
        throw new SQLiteException(r03.toString());     // Catch: SQLiteException -> L35
    L37:
        if (r15 == null) goto L45;
    L39:
        if (r17 >= r15.length) goto L45;
        if (r4.remove(r15[r17]) == true) goto L43;
        r2.execSQL(r15[r17 + 1]);     // Catch: SQLiteException -> L35
    L43:
        r17 = r17 + 2;     // Catch: SQLiteException -> L35
    L45:
        if (r4.isEmpty() == true) goto L73;
        r10.zze().zzc("Table has extra columns. table, columns", r12, TextUtils.join(", ", r4));     // Catch: SQLiteException -> L35
        return;
    L73:
        return;
    L48:
        th = move-exception;
        r16.close();     // Catch: SQLiteException -> L35
        throw th;     // Catch: SQLiteException -> L35
    L35:
        e = move-exception;
        r10.zzb().zzb("Failed to verify columns on table that was just created", r12);
        throw e;
    L13:
        e = e;
    L22:
        r10.zze().zzc("Error querying for table", r12, e);     // Catch: Throwable -> L11
        if (r112 == null) goto L25;
        r112.close();
    L11:
        th = move-exception;
        Throwable r102 = th;
        r1 = r112;
    L53:
        if (r1 == null) goto L74;
        r1.close();
        throw r102;
    L74:
        throw r102;
    L17:
        e = e;
    L21:
        r112 = null;
    L15:
        th = move-exception;
        r102 = th;
    L19:
        e = e;
        r2 = r11;
        goto L21
    L56:
        f$$ExternalSyntheticBUOutline1.m("Monitor must not be null");
    }

    public static void zzb(zzgu r3, SQLiteDatabase r4) {
        if (r3 == null) goto L16;
        com.google.android.gms.internal.measurement.zzby.zza();
        String r42 = r4.getPath();
        int r1 = com.google.android.gms.internal.measurement.zzcd.$r8$clinit;
        File r0 = new File(r42);
        if (r0.setReadable(false, false) == true) goto L7;
        r3.zze().zza("Failed to turn off database read permission");
    L7:
        if (r0.setWritable(false, false) == true) goto L10;
        r3.zze().zza("Failed to turn off database write permission");
    L10:
        if (r0.setReadable(true, true) == true) goto L13;
        r3.zze().zza("Failed to turn on database read permission for owner");
    L13:
        if (r0.setWritable(true, true) == true) goto L18;
        r3.zze().zza("Failed to turn on database write permission for owner");
        return;
    L18:
        return;
    L16:
        f$$ExternalSyntheticBUOutline1.m("Monitor must not be null");
    }
}
