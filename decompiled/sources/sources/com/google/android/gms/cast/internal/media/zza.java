package com.google.android.gms.cast.internal.media;

import android.text.TextUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.images.WebImage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
public final class zza {
    private static final Logger zza = null;
    private static final String[] zzb = null;
    private static final String zzc = null;

    static {
        zza = new Logger("MetadataUtils");
        String[] r0 = {"Z", "+hh", "+hhmm", "+hh:mm"};
        zzb = r0;
        zzc = "yyyyMMdd'T'HHmmss".concat(String.valueOf(r0[0]));
    }

    public static void zza(List r3, JSONArray r4) {
        r3.clear();     // Catch: JSONException -> L10
        int r0 = 0;
    L3:
        if (r0 >= r4.length()) goto L17;
        r3.add(new WebImage(r4.getJSONObject(r0)));     // Catch: IllegalArgumentException -> L9 JSONException -> L10
    L7:
        r0 = r0 + 1;
        goto L3
    L17:
        return;
    }

    public static JSONArray zzb(List r2) {
        r2.getClass();
        JSONArray r0 = new JSONArray();
        Iterator r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.put(((WebImage) r22.next()).toJson());
        goto L4
    L6:
        return r0;
    }

    public static String zzc(Calendar r3) {
        if (r3 != null) goto L6;
        zza.d("Calendar object cannot be null", new Object[0]);
        return null;
    L6:
        String r1 = zzc;
        if (r3.get(11) == 0) goto L9;
    L13:
        SimpleDateFormat r2 = new SimpleDateFormat(r1);
        r2.setTimeZone(r3.getTimeZone());
        String r32 = r2.format(r3.getTime());
        if (r32.endsWith("+0000") == true) goto L16;
        return r32;
    L16:
        return r32.replace("+0000", zzb[0]);
    L9:
        if (r3.get(12) != 0) goto L13;
        if (r3.get(13) != 0) goto L13;
        r1 = "yyyyMMdd";
        goto L13
    }

    public static Calendar zzd(String r6) {
        if (TextUtils.isEmpty(r6) == false) goto L6;
        zza.d("Input string is empty or null", new Object[0]);
        return null;
    L6:
        String r0 = zze(r6);
        if (TextUtils.isEmpty(r0) == false) goto L10;
        zza.d("Invalid date format", new Object[0]);
        return null;
    L10:
        String r62 = zzf(r6);
        if (TextUtils.isEmpty(r62) == true) goto L16;
        int r3 = String.valueOf(r0).length();
        StringBuilder r5 = new StringBuilder((r3 + 1) + String.valueOf(r62).length());
        r5.append(r0);
        r5.append("T");
        r5.append(r62);
        r0 = r5.toString();
        if (r62.length() != 6) goto L15;
        String r63 = "yyyyMMdd'T'HHmmss";
    L17:
        Calendar r32 = Calendar.getInstance();
        r32.setTime(new SimpleDateFormat(r63).parse(r0));
        return r32;
    L21:
        e = move-exception;
        zza.e(e, "Error parsing string", new Object[0]);
        return null;
    L15:
        r63 = zzc;
        goto L17
    L16:
        r63 = "yyyyMMdd";
        goto L17
    }

    private static String zze(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L12;
        zza.d("Input string is empty or null", new Object[0]);
        return null;
    L12:
        return r4.substring(0, 8);
    L7:
        e = move-exception;
        zza.e(e, "Error extracting the date", new Object[0]);
        return null;
    }

    private static String zzf(String r5) {
        if (TextUtils.isEmpty(r5) == false) goto L6;
        zza.d("string is empty or null", new Object[0]);
        return null;
    L6:
        int r0 = r5.indexOf(84);
        int r3 = r0 + 1;
        if (r0 == 8) goto L39;
        zza.d("T delimeter is not found", new Object[0]);
        return null;
    L39:
        String r52 = r5.substring(r3);     // Catch: IndexOutOfBoundsException -> L36
        if (r52.length() != 6) goto L14;
        return r52;
    L14:
        char r02 = r52.charAt(6);
        if (r02 != '+') goto L17;
    L26:
        int r03 = r52.length();
        String[] r2 = zzb;
        if (r03 == (r2[1].length() + 6)) goto L35;
        if (r03 == (r2[2].length() + 6)) goto L35;
        if (r03 == (r2[3].length() + 6)) goto L35;
    L33:
        return null;
    L35:
        return r52.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
    L17:
        if (r02 == '-') goto L26;
        if (r02 != 'Z') goto L33;
        if (r52.length() == (zzb[0].length() + 6)) goto L24;
        return null;
    L24:
        return r52.substring(0, r52.length() - 1).concat("+0000");
    L36:
        e = move-exception;
        zza.e(e, "Error extracting the time substring: %s", new Object[0]);
        return null;
    }
}
