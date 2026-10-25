package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.dx.rop.code.RegisterSpec;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzaif;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public final class zzhd {
    final /* synthetic */ zzhh zza;
    private final String zzb;
    private final Bundle zzc;
    private Bundle zzd;

    public zzhd(zzhh r1, String r2, Bundle r3) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        Preconditions.checkNotEmpty(r2);
        this.zzb = r2;
        this.zzc = new Bundle();
    }

    public final Bundle zza() {
        if (this.zzd != null) goto L62;
        zzhh r0 = this.zza;
        String r1 = r0.zzd().getString(this.zzb, null);
        if (r1 == null) goto L59;
        Bundle r2 = new Bundle();     // Catch: JSONException -> L57
        JSONArray r4 = new JSONArray(r1);     // Catch: JSONException -> L57
        int r5 = 0;
    L9:
        if (r5 >= r4.length()) goto L55;
        JSONObject r6 = r4.getJSONObject(r5);     // Catch: Throwable -> L53
        String r7 = r6.getString("n");     // Catch: Throwable -> L53
        String r8 = r6.getString("t");     // Catch: Throwable -> L53
        int r9 = r8.hashCode();     // Catch: Throwable -> L53
    L12:
        if (r9 == 100) goto L49;
        if (r9 == 108) goto L45;
        if (r9 == 115) goto L41;
        if (r9 == 3352) goto L32;
        if (r9 == 3445) goto L23;
    L51:
        r0.zzu.zzaW().zzb().zzb("Unrecognized persisted bundle type. Type", r8);     // Catch: Throwable -> L53 Throwable -> L53
    L54:
        r5 = r5 + 1;     // Catch: JSONException -> L57
        goto L9
    L23:
        if (r8.equals("la") == false) goto L51;
        zzaif.zza();     // Catch: Throwable -> L53 Throwable -> L53
        if (r0.zzu.zzc().zzp(null, zzfy.zzaP) == false) goto L54;
        JSONArray r82 = new JSONArray(r6.getString(RegisterSpec.PREFIX));     // Catch: Throwable -> L53 Throwable -> L53
        int r62 = r82.length();     // Catch: Throwable -> L53 Throwable -> L53
        long[] r92 = new long[r62];     // Catch: Throwable -> L53 Throwable -> L53
        int r10 = 0;
    L27:
        if (r10 >= r62) goto L29;
        r92[r10] = r82.optLong(r10);     // Catch: Throwable -> L53 Throwable -> L53
        r10 = r10 + 1;     // Catch: Throwable -> L53 Throwable -> L53
        goto L27
    L29:
        r2.putLongArray(r7, r92);     // Catch: Throwable -> L53 Throwable -> L53
        goto L54
    L32:
        if (r8.equals("ia") == false) goto L51;
        zzaif.zza();     // Catch: Throwable -> L53 Throwable -> L53
        if (r0.zzu.zzc().zzp(null, zzfy.zzaP) == false) goto L54;
        JSONArray r83 = new JSONArray(r6.getString(RegisterSpec.PREFIX));     // Catch: Throwable -> L53 Throwable -> L53
        int r63 = r83.length();     // Catch: Throwable -> L53 Throwable -> L53
        int[] r93 = new int[r63];     // Catch: Throwable -> L53 Throwable -> L53
        int r102 = 0;
    L36:
        if (r102 >= r63) goto L38;
        r93[r102] = r83.optInt(r102);     // Catch: Throwable -> L53 Throwable -> L53
        r102 = r102 + 1;     // Catch: Throwable -> L53 Throwable -> L53
        goto L36
    L38:
        r2.putIntArray(r7, r93);     // Catch: Throwable -> L53 Throwable -> L53
        goto L54
    L41:
        if (r8.equals("s") == false) goto L51;
        r2.putString(r7, r6.getString(RegisterSpec.PREFIX));     // Catch: Throwable -> L53 Throwable -> L53
        goto L54
    L45:
        if (r8.equals("l") == false) goto L51;
        r2.putLong(r7, Long.parseLong(r6.getString(RegisterSpec.PREFIX)));     // Catch: Throwable -> L53 Throwable -> L53
        goto L54
    L49:
        if (r8.equals("d") == false) goto L51;
        r2.putDouble(r7, Double.parseDouble(r6.getString(RegisterSpec.PREFIX)));     // Catch: Throwable -> L53 Throwable -> L53
    L53:
        this.zza.zzu.zzaW().zzb().zza("Error reading value from SharedPreferences. Value dropped");     // Catch: JSONException -> L57
        goto L54
    L55:
        this.zzd = r2;     // Catch: JSONException -> L57
    L57:
        this.zza.zzu.zzaW().zzb().zza("Error loading bundle from SharedPreferences. Values will be lost");
    L59:
        if (this.zzd != null) goto L62;
        this.zzd = this.zzc;
    L62:
        return new Bundle((Bundle) Preconditions.checkNotNull(this.zzd));
    }

    public final void zzb(Bundle r17) {
        if (r17 != null) goto L5;
        Bundle r2 = new Bundle();
    L6:
        zzhh r3 = this.zza;
        SharedPreferences.Editor r4 = r3.zzd().edit();
        int r0 = r2.size();
        String r5 = this.zzb;
        if (r0 != 0) goto L9;
        r4.remove(r5);
    L49:
        r4.apply();
        this.zzd = r2;
        return;
    L9:
        JSONArray r6 = new JSONArray();
        Iterator<String> r7 = r2.keySet().iterator();
    L11:
        if (r7.hasNext() == false) goto L48;
        String r02 = r7.next();
        Object r8 = r2.get(r02);
        if (r8 == null) goto L11;
        JSONObject r9 = new JSONObject();     // Catch: JSONException -> L20
        r9.put("n", r02);     // Catch: JSONException -> L20
        zzaif.zza();     // Catch: JSONException -> L20
        zzic r03 = r3.zzu;     // Catch: JSONException -> L20
        if (r03.zzc().zzp(null, zzfy.zzaP) == true) goto L18;
        r9.put(RegisterSpec.PREFIX, r8.toString());     // Catch: JSONException -> L20
        if ((r8 instanceof String) == true) goto L37;
        if ((r8 instanceof Long) == true) goto L40;
        if ((r8 instanceof Double) == true) goto L43;
        r03.zzaW().zzb().zzb("Cannot serialize bundle value to SharedPreferences. Type", r8.getClass());     // Catch: JSONException -> L20
        goto L11
    L43:
        r9.put("t", "d");     // Catch: JSONException -> L20
    L44:
        r6.put(r9);     // Catch: JSONException -> L20
        goto L11
    L40:
        r9.put("t", "l");     // Catch: JSONException -> L20
        goto L44
    L37:
        r9.put("t", "s");     // Catch: JSONException -> L20
        goto L44
    L18:
        if ((r8 instanceof String) == true) goto L19;
        if ((r8 instanceof Long) == true) goto L24;
        if ((r8 instanceof int[]) == true) goto L27;
        if ((r8 instanceof long[]) == true) goto L30;
        if ((r8 instanceof Double) == true) goto L33;
        r03.zzaW().zzb().zzb("Cannot serialize bundle value to SharedPreferences. Type", r8.getClass());     // Catch: JSONException -> L20
        goto L11
    L33:
        r9.put(RegisterSpec.PREFIX, r8.toString());     // Catch: JSONException -> L20
        r9.put("t", "d");     // Catch: JSONException -> L20
        goto L44
    L30:
        r9.put(RegisterSpec.PREFIX, Arrays.toString((long[]) r8));     // Catch: JSONException -> L20
        r9.put("t", "la");     // Catch: JSONException -> L20
        goto L44
    L27:
        r9.put(RegisterSpec.PREFIX, Arrays.toString((int[]) r8));     // Catch: JSONException -> L20
        r9.put("t", "ia");     // Catch: JSONException -> L20
        goto L44
    L24:
        r9.put(RegisterSpec.PREFIX, r8.toString());     // Catch: JSONException -> L20
        r9.put("t", "l");     // Catch: JSONException -> L20
        goto L44
    L19:
        r9.put(RegisterSpec.PREFIX, r8.toString());     // Catch: JSONException -> L20
        r9.put("t", "s");     // Catch: JSONException -> L20
    L20:
        e = move-exception;
        this.zza.zzu.zzaW().zzb().zzb("Cannot serialize bundle value to SharedPreferences", e);
        goto L11
    L48:
        r4.putString(r5, r6.toString());
        goto L49
    L5:
        r2 = new Bundle(r17);
        goto L6
    }
}
