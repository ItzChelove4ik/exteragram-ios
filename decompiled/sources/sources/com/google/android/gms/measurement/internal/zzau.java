package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzau {
    final /* synthetic */ zzaw zza;
    private final String zzb;
    private long zzc;

    public zzau(zzaw r1, String r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        Preconditions.checkNotEmpty(r2);
        this.zzb = r2;
        this.zzc = -1;
    }

    public zzau(zzaw r3, String r4, long r5) {
        Objects.requireNonNull(r3);
        this.zza = r3;
        Preconditions.checkNotEmpty(r4);
        this.zzb = r4;
        this.zzc = r3.zzaj("select rowid from raw_events where app_id = ? and timestamp < ? order by rowid desc limit 1", new String[]{r4, String.valueOf(r5)}, -1);
    }

    public final List zza() {
        List r2 = new ArrayList();
        Cursor r3 = null;
        r3 = this.zza.zze().query("raw_events", new String[]{"rowid", "name", "timestamp", "metadata_fingerprint", "data", "realtime", "elapsed_time"}, "app_id = ? and rowid > ?", new String[]{this.zzb, String.valueOf(this.zzc)}, null, null, "rowid", "1000");     // Catch: Throwable -> L12 SQLiteException -> L14
        if (r3.moveToFirst() == false) goto L26;
    L5:
        boolean r0 = false;
        long r5 = r3.getLong(0);     // Catch: Throwable -> L12 SQLiteException -> L14
        long r7 = r3.getLong(3);     // Catch: Throwable -> L12 SQLiteException -> L14
        if (r3.getLong(5) != 1) goto L8;
        r0 = true;
    L8:
        long r10 = r3.getLong(6);     // Catch: Throwable -> L12 SQLiteException -> L14
        byte[] r4 = r3.getBlob(4);     // Catch: Throwable -> L12 SQLiteException -> L14
        if (r5 <= this.zzc) goto L35;
        this.zzc = r5;     // Catch: Throwable -> L12 SQLiteException -> L14
    L35:
        com.google.android.gms.internal.measurement.zzhr r42 = (com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzp(), r4);     // Catch: Throwable -> L12 SQLiteException -> L14 IOException -> L21
        String r9 = r3.getString(1);     // Catch: Throwable -> L12 SQLiteException -> L14
        if (r9 != null) goto L20;
        r9 = _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        r42.zzl(r9);     // Catch: Throwable -> L12 SQLiteException -> L14
        r42.zzo(r3.getLong(2));     // Catch: Throwable -> L12 SQLiteException -> L14
        r42.zzw(r10);     // Catch: Throwable -> L12 SQLiteException -> L14
        r2.add(new zzat(r5, r7, r0, (com.google.android.gms.internal.measurement.zzhs) r42.zzbd()));     // Catch: Throwable -> L12 SQLiteException -> L14
    L24:
        if (r3.moveToNext() == true) goto L5;
    L29:
        if (r3 == null) goto L31;
        r3.close();
    L31:
        return r2;
    L21:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzc("Data loss. Failed to merge raw event. appId", zzgu.zzl(this.zzb), e);     // Catch: Throwable -> L12 SQLiteException -> L14
        goto L24
    L26:
        r2 = Collections.EMPTY_LIST;     // Catch: Throwable -> L12 SQLiteException -> L14
    L12:
        th = move-exception;
        if (0 == 0) goto L34;
        r3.close();
    L34:
        throw th;
    L14:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzc("Data loss. Error querying raw events batch. appId", zzgu.zzl(this.zzb), e);     // Catch: Throwable -> L12
        goto L29
    }
}
