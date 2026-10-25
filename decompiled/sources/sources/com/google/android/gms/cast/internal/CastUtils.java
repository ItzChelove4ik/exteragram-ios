package com.google.android.gms.cast.internal;

import android.os.SystemClock;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class CastUtils {
    public static final /* synthetic */ int zza = 0;
    private static final Pattern zzb = null;
    private static final Random zzc = null;

    static {
        zzb = Pattern.compile("urn:x-cast:[-A-Za-z0-9_]+(\\.[-A-Za-z0-9_]+)*");
        zzc = new Random(SystemClock.elapsedRealtime());
    }

    private CastUtils() {
    }

    public static JSONObject jsonStringToJsonObject(String r2) {
        if (r2 != null) goto L8;
        return null;
    L8:
        return new JSONObject(r2);
    L7:
        return null;
    }

    public static double millisecToSec(long r2) {
        return r2 / 1000.0d;
    }

    public static String optStringOrNull(JSONObject r1, String r2) {
        if (r1 != null) goto L4;
        return null;
    L4:
        if (r1.has(r2) == true) goto L6;
        return null;
    L6:
        return r1.optString(r2);
    }

    public static long secToMillisec(double r2) {
        return (long) (r2 * 1000.0d);
    }

    public static long secToMillisec(long r2) {
        return r2 * 1000;
    }

    public static void throwIfInvalidNamespace(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Namespace cannot be null or empty");
        return;
    L5:
        if (r2.length() <= 128) goto L7;
        f$$ExternalSyntheticBUOutline1.m("Invalid namespace length");
        return;
    L7:
        if (r2.startsWith("urn:x-cast:") == true) goto L9;
        f$$ExternalSyntheticBUOutline1.m("Namespace must begin with the prefix \"urn:x-cast:\"");
        return;
    L9:
        if (r2.length() == 11) goto L11;
        return;
    L11:
        f$$ExternalSyntheticBUOutline1.m("Namespace must begin with the prefix \"urn:x-cast:\" and have non-empty suffix");
    }

    public static long[] toLongArray(Collection<Long> r6) {
        long[] r0 = new long[r6.size()];
        Iterator<Long> r62 = r6.iterator();
        int r1 = 0;
    L4:
        if (r62.hasNext() == false) goto L6;
        r0[r1] = r62.next().longValue();
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public static long[] toLongArraySafely(JSONArray r0) {
        return zzg(r0);
    L4:
        return null;
    }

    public static boolean zza(Object r2, Object r3) {
        if (r2 != null) goto L8;
        if (r3 != null) goto L8;
        return true;
    L8:
        if (r2 == null) goto L13;
        if (r3 == null) goto L13;
        if (r2.equals(r3) == false) goto L13;
        return true;
    L13:
        return false;
    }

    public static long zzb() {
        return zzc.nextLong();
    }

    public static String zzc(String r4) {
        if (zzb.matcher(r4).matches() == false) goto L5;
        return r4;
    L5:
        StringBuilder r0 = new StringBuilder(r4.length());
        int r1 = 0;
    L7:
        if (r1 >= r4.length()) goto L33;
        char r2 = r4.charAt(r1);
        if (r2 < 'A') goto L13;
        if (r2 > 'Z') goto L13;
    L30:
        r0.append(r2);
    L31:
        r1 = r1 + 1;
    L13:
        if (r2 < 'a') goto L17;
        if (r2 <= 'z') goto L30;
    L17:
        if (r2 < '0') goto L21;
        if (r2 <= '9') goto L30;
    L21:
        if (r2 == '_') goto L30;
        if (r2 == '-') goto L30;
        if (r2 == '.') goto L30;
        if (r2 == ':') goto L30;
        r0.append(String.format("%%%04x", new Object[]{Integer.valueOf(r2)}));
        goto L31
    L33:
        return r0.toString();
    }

    public static String zzd(Locale r4) {
        StringBuilder r0 = new StringBuilder(20);
        r0.append(r4.getLanguage());
        String r1 = r4.getCountry();
        if (TextUtils.isEmpty(r1) == true) goto L5;
        r0.append(SignatureVisitor.SUPER);
        r0.append(r1);
    L5:
        String r42 = r4.getVariant();
        if (TextUtils.isEmpty(r42) == true) goto L9;
        r0.append(SignatureVisitor.SUPER);
        r0.append(r42);
    L9:
        return r0.toString();
    }

    public static int[] zze(Collection r4) {
        int[] r0 = new int[r4.size()];
        Iterator r42 = r4.iterator();
        int r1 = 0;
    L4:
        if (r42.hasNext() == false) goto L6;
        r0[r1] = ((Integer) r42.next()).intValue();
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public static List zzf(int[] r4) {
        ArrayList r0 = new ArrayList();
        int r1 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.add(Integer.valueOf(r4[r2]));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    public static long[] zzg(JSONArray r4) {
        if (r4 != null) goto L5;
        return null;
    L5:
        long[] r0 = new long[r4.length()];
        int r1 = 0;
    L7:
        if (r1 >= r4.length()) goto L9;
        r0[r1] = r4.getLong(r1);
        r1 = r1 + 1;
        goto L7
    L9:
        return r0;
    }
}
