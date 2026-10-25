package com.google.android.gms.internal.clearcut;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class zzy {
    private static final Uri CONTENT_URI = null;
    private static final Uri zzcq = null;
    public static final Pattern zzcr = null;
    public static final Pattern zzcs = null;
    private static final AtomicBoolean zzct = null;
    private static HashMap<String, String> zzcu;
    private static final HashMap<String, Boolean> zzcv = null;
    private static final HashMap<String, Integer> zzcw = null;
    private static final HashMap<String, Long> zzcx = null;
    private static final HashMap<String, Float> zzcy = null;
    private static Object zzcz;
    private static boolean zzda;
    private static String[] zzdb;

    static {
        CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
        zzcq = Uri.parse("content://com.google.android.gsf.gservices/prefix");
        zzcr = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
        zzcs = Pattern.compile("^(0|false|f|off|no|n)$", 2);
        zzct = new AtomicBoolean();
        zzcv = new HashMap();
        zzcw = new HashMap();
        zzcx = new HashMap();
        zzcy = new HashMap();
        zzdb = new String[0];
    }

    public static long getLong(ContentResolver r4, String r5, long r6) {
        Object r62 = zzb(r4);
        long r0 = 0;
        Long r7 = (Long) zza(zzcx, r5, 0L);
        if (r7 != null) goto L5;
        String r42 = zza(r4, r5, null);
        if (r42 != null) goto L14;
    L11:
        zza(r62, zzcx, r5, r7);
        return r0;
    L14:
        long r2 = Long.parseLong(r42);     // Catch: NumberFormatException -> L13
        r7 = Long.valueOf(r2);     // Catch: NumberFormatException -> L13
        r0 = r2;
        goto L11
    L5:
        return r7.longValue();
    }

    private static <T> T zza(HashMap<String, T> r2, String r3, T r4) {
        monitor-enter(zzy.class);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (r2.containsKey(r3) == false) goto L13;
        T r22 = r2.get(r3);     // Catch: Throwable -> L11
        if (r22 == null) goto L9;
        r4 = r22;
    L9:
        monitor-exit(zzy.class);     // Catch: Throwable -> L11
        return r4;
    L13:
        monitor-exit(zzy.class);     // Catch: Throwable -> L11
        return null;
    }

    public static String zza(ContentResolver r12, String r13, String r14) {
        monitor-enter(zzy.class);
        zza(r12);     // Catch: Throwable -> L11
        Object r0 = zzcz;     // Catch: Throwable -> L11
        String r2 = null;
        if (zzcu.containsKey(r13) == false) goto L13;
        String r122 = zzcu.get(r13);     // Catch: Throwable -> L11
        if (r122 == null) goto L9;
    L8:
        r2 = r122;
    L9:
        monitor-exit(zzy.class);     // Catch: Throwable -> L11
        return r2;
    L13:
        String[] r1 = zzdb;     // Catch: Throwable -> L11
        int r3 = r1.length;     // Catch: Throwable -> L11
        int r4 = 0;
    L15:
        if (r4 >= r3) goto L28;
        if (r13.startsWith(r1[r4]) == true) goto L19;
        r4 = r4 + 1;     // Catch: Throwable -> L11
        goto L15
    L19:
        if (zzda == true) goto L21;
    L22:
        zzcu.putAll(zza(r12, zzdb));     // Catch: Throwable -> L11
        zzda = true;     // Catch: Throwable -> L11
        if (zzcu.containsKey(r13) == false) goto L9;
        r122 = zzcu.get(r13);     // Catch: Throwable -> L11
        if (r122 == null) goto L9;
    L21:
        if (zzcu.isEmpty() == false) goto L9;
    L28:
        monitor-exit(zzy.class);     // Catch: Throwable -> L11
        Cursor r123 = r12.query(CONTENT_URI, null, null, new String[]{r13}, null);
        if (r123 != null) goto L55;
    L46:
        zza(r0, r13, null);     // Catch: Throwable -> L39
        if (r123 == null) goto L49;
        r123.close();
    L49:
        return null;
    L55:
    L39:
        th = move-exception;
        if (r123 == null) goto L59;
        r123.close();
        throw th;
    L59:
        throw th;
    L32:
        if (r123.moveToFirst() == false) goto L46;
        String r142 = r123.getString(1);     // Catch: Throwable -> L39
        if (r142 != null) goto L37;
    L41:
        zza(r0, r13, r142);     // Catch: Throwable -> L39
        if (r142 == null) goto L44;
        r2 = r142;
    L44:
        r123.close();
        return r2;
    L37:
        if (r142.equals(null) == false) goto L41;
        r142 = null;
    L11:
        th = move-exception;
        throw th;
    }

    private static Map<String, String> zza(ContentResolver r6, String... r7) {
        Cursor r62 = r6.query(zzcq, null, null, r7, null);
        TreeMap r72 = new TreeMap();
        if (r62 != null) goto L15;
        return r72;
    L15:
        if (r62.moveToNext() == false) goto L11;
        r72.put(r62.getString(0), r62.getString(1));     // Catch: Throwable -> L9
        goto L15
    L11:
        r62.close();
        return r72;
    L9:
        th = move-exception;
        r62.close();
        throw th;
    }

    private static void zza(ContentResolver r3) {
        if (zzcu != null) goto L7;
        zzct.set(false);
        zzcu = new HashMap();
        zzcz = new Object();
        zzda = false;
        r3.registerContentObserver(CONTENT_URI, true, new zzz(null));
        return;
    L7:
        if (zzct.getAndSet(false) == false) goto L10;
        zzcu.clear();
        zzcv.clear();
        zzcw.clear();
        zzcx.clear();
        zzcy.clear();
        zzcz = new Object();
        zzda = false;
        return;
    }

    private static void zza(Object r2, String r3, String r4) {
        monitor-enter(zzy.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r2 != zzcz) goto L9;
        zzcu.put(r3, r4);     // Catch: Throwable -> L7
    L9:
        monitor-exit(zzy.class);     // Catch: Throwable -> L7
    }

    private static <T> void zza(Object r2, HashMap<String, T> r3, String r4, T r5) {
        monitor-enter(zzy.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r2 != zzcz) goto L9;
        r3.put(r4, r5);     // Catch: Throwable -> L7
        zzcu.remove(r4);     // Catch: Throwable -> L7
    L9:
        monitor-exit(zzy.class);     // Catch: Throwable -> L7
    }

    public static boolean zza(ContentResolver r5, String r6, boolean r7) {
        Object r0 = zzb(r5);
        HashMap<String, Boolean> r1 = zzcv;
        Boolean r2 = (Boolean) zza(r1, r6, Boolean.valueOf(r7));
        if (r2 != null) goto L5;
        String r52 = zza(r5, r6, null);
        if (r52 != null) goto L9;
    L18:
        zza(r0, r1, r6, r2);
        return r7;
    L9:
        if (r52.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L18;
        if (zzcr.matcher(r52).matches() == false) goto L15;
        r2 = Boolean.TRUE;
        r7 = true;
        goto L18
    L15:
        if (zzcs.matcher(r52).matches() == false) goto L17;
        r2 = Boolean.FALSE;
        r7 = false;
        goto L18
    L17:
        Log.w("Gservices", "attempt to read gservices key " + r6 + " (value \"" + r52 + "\") as boolean");
        goto L18
    L5:
        return r2.booleanValue();
    }

    private static Object zzb(ContentResolver r1) {
        monitor-enter(zzy.class);
        zza(r1);     // Catch: Throwable -> L7
        Object r12 = zzcz;     // Catch: Throwable -> L7
        monitor-exit(zzy.class);     // Catch: Throwable -> L7
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ AtomicBoolean zze() {
        return zzct;
    }
}
