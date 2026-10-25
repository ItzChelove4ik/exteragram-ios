package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import androidx.collection.ArrayMap;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzlg {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ArrayMap zzb = null;

    static {
        zzb = new ArrayMap();
    }

    public static synchronized Uri zza(String r4) {
        monitor-enter(zzlg.class);
        ArrayMap r0 = zzb;     // Catch: Throwable -> L9
        Uri r2 = (Uri) r0.get("com.google.android.gms.measurement");     // Catch: Throwable -> L9
        if (r2 != null) goto L11;
        Uri r22 = Uri.parse("content://com.google.android.gms.phenotype/".concat(String.valueOf(Uri.encode("com.google.android.gms.measurement"))));     // Catch: Throwable -> L9
        r0.put("com.google.android.gms.measurement", r22);     // Catch: Throwable -> L9
        monitor-exit(zzlg.class);
        return r22;
    L11:
        monitor-exit(zzlg.class);
        return r2;
    L9:
        th = move-exception;
        throw th;
    }

    public static String zzb(Context r3, String r4, boolean r5) {
        if (r4.contains("#") == true) goto L6;
        String r32 = r3.getPackageName();
        int r0 = r4.length();
        int r02 = r0 + 1;
        StringBuilder r2 = new StringBuilder(r02 + String.valueOf(r32).length());
        r2.append(r4);
        r2.append("#");
        r2.append(r32);
        return r2.toString();
    L6:
        f$$ExternalSyntheticBUOutline1.m("The passed in package cannot already have a subpackage: ".concat(r4));
        return null;
    }
}
