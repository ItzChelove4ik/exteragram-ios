package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzcb extends SQLiteOpenHelper {
    public zzcb(Context r2, String r3, SQLiteDatabase.CursorFactory r4, int r5) {
        int r42 = zzcd.$r8$clinit;
        zzby.zza();
        if (true != r3.equals(_UrlKt.FRAGMENT_ENCODE_SET)) goto L5;
        r3 = null;
    L5:
        super(r2, r3, null, 1);
    }
}
