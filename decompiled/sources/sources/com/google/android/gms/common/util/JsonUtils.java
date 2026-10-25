package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public abstract class JsonUtils {
    private static final Pattern zza = null;
    private static final Pattern zzb = null;

    static {
        zza = Pattern.compile("\\\\.");
        zzb = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
    }

    public static boolean areJsonValuesEquivalent(Object r5, Object r6) {
        if (r5 != null) goto L8;
        if (r6 != null) goto L8;
        return true;
    L8:
        if (r5 == null) goto L44;
        if (r6 == null) goto L44;
        if ((r5 instanceof JSONObject) == false) goto L28;
        if ((r6 instanceof JSONObject) == false) goto L28;
        JSONObject r52 = (JSONObject) r5;
        JSONObject r62 = (JSONObject) r6;
        if (r52.length() == r62.length()) goto L18;
        return false;
    L18:
        Iterator<String> r2 = r52.keys();
    L20:
        if (r2.hasNext() == false) goto L26;
        String r3 = r2.next();
        if (r62.has(r3) == false) goto L25;
        Preconditions.checkNotNull(r3);     // Catch: JSONException -> L45
        if (areJsonValuesEquivalent(r52.get(r3), r62.get(r3)) == true) goto L20;
    L25:
        return false;
    L26:
        return true;
    L28:
        if ((r5 instanceof JSONArray) == false) goto L43;
        if ((r6 instanceof JSONArray) == false) goto L43;
        JSONArray r53 = (JSONArray) r5;
        JSONArray r63 = (JSONArray) r6;
        if (r53.length() != r63.length()) goto L41;
        int r22 = 0;
    L35:
        if (r22 >= r53.length()) goto L40;
        if (areJsonValuesEquivalent(r53.get(r22), r63.get(r22)) == false) goto L39;
        r22 = r22 + 1;
    L39:
        return false;
    L40:
        return true;
    L41:
        return false;
    L43:
        return r5.equals(r6);
    L44:
        return false;
    }
}
