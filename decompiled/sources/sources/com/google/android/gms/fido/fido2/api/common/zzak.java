package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.fido.zzfv;
import com.google.android.gms.internal.fido.zzfx;
import com.google.android.gms.internal.fido.zzgj;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public final class zzak extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzak> CREATOR = null;
    private static final byte[] zza = null;
    private final byte[][] zzb;

    static {
        CREATOR = new zzal();
        zza = "WebAuthn PRF\u0000".getBytes(StandardCharsets.UTF_8);
    }

    public zzak(byte[][] r6) {
        if (r6 == null) goto L5;
        boolean r2 = true;
    L6:
        Preconditions.checkArgument(r2);
        if (1 == ((r6.length & 1) ^ 1)) goto L9;
        boolean r22 = false;
    L10:
        Preconditions.checkArgument(r22);
        int r23 = 0;
    L12:
        if (r23 >= r6.length) goto L29;
        if (r23 != 0) goto L15;
    L16:
        boolean r3 = true;
    L18:
        Preconditions.checkArgument(r3);
        int r32 = r23 + 1;
        if (r6[r32] == null) goto L21;
        boolean r4 = true;
    L22:
        Preconditions.checkArgument(r4);
        int r33 = r6[r32].length;
        if (r33 != 32) goto L25;
    L26:
        boolean r34 = true;
    L28:
        Preconditions.checkArgument(r34);
        r23 = r23 + 2;
        goto L12
    L25:
        if (r33 == 64) goto L26;
        r34 = false;
        goto L28
    L21:
        r4 = false;
        goto L22
    L15:
        if (r6[r23] != null) goto L16;
        r3 = false;
        goto L18
    L29:
        this.zzb = r6;
        return;
    L9:
        r22 = true;
        goto L10
    L5:
        r2 = false;
        goto L6
    }

    public static zzak zza(JSONObject r4, boolean r5) {
        ArrayList r2 = new ArrayList();
        if (r4.has("eval") == false) goto L10;
        r2.add(null);     // Catch: IllegalArgumentException -> L21
        if (r5 == false) goto L8;
        r2.add(zzd(r4.getJSONObject("eval")));     // Catch: IllegalArgumentException -> L21
        goto L10
    L8:
        r2.add(zze(r4.getJSONObject("eval")));     // Catch: IllegalArgumentException -> L21
    L10:
        if (r4.has("evalByCredential") == false) goto L20;
        JSONObject r42 = r4.getJSONObject("evalByCredential");     // Catch: IllegalArgumentException -> L21
        Iterator<String> r0 = r42.keys();     // Catch: IllegalArgumentException -> L21
    L12:
        if (r0.hasNext() == false) goto L20;
        String r1 = r0.next();     // Catch: IllegalArgumentException -> L21
        r2.add(Base64Utils.decodeUrlSafeNoPadding(r1));     // Catch: IllegalArgumentException -> L21
        if (r5 == true) goto L16;
        r2.add(zze(r42.getJSONObject(r1)));     // Catch: IllegalArgumentException -> L21
        goto L12
    L16:
        r2.add(zzd(r42.getJSONObject(r1)));     // Catch: IllegalArgumentException -> L21
    L20:
        return new zzak((byte[][]) r2.toArray(new byte[0][]));
    L22:
        throw new JSONException("invalid base64url value");
    }

    private static JSONObject zzb(byte[] r5) {
        JSONObject r0 = new JSONObject();
        if (r5.length != 32) goto L6;
        r0.put("first", Base64Utils.encodeUrlSafeNoPadding(r5));
        return r0;
    L6:
        r0.put("first", Base64.encodeToString(r5, 0, 32, 11));
        r0.put("second", Base64.encodeToString(r5, 32, 32, 11));
        return r0;
    }

    private static byte[] zzc(byte[] r2) {
        zzfv r0 = zzfx.zza().zza();
        r0.zza(zza);
        r0.zza(r2);
        return r0.zzc().zzd();
    }

    private static byte[] zzd(JSONObject r5) {
        byte[] r0 = Base64Utils.decodeUrlSafeNoPadding(r5.getString("first"));
        if (r0.length != 32) goto L14;
        if (r5.has("second") == true) goto L7;
        return r0;
    L7:
        byte[] r52 = Base64Utils.decodeUrlSafeNoPadding(r5.getString("second"));
        if (r52.length != 32) goto L12;
        return zzgj.zza(new byte[][]{r0, r52});
    L12:
        throw new JSONException("hashed PRF value with wrong length");
    L14:
        throw new JSONException("hashed PRF value with wrong length");
    }

    private static byte[] zze(JSONObject r3) {
        byte[] r0 = zzc(Base64Utils.decodeUrlSafeNoPadding(r3.getString("first")));
        if (r3.has("second") == true) goto L6;
        return r0;
    L6:
        return zzgj.zza(new byte[][]{r0, zzc(Base64Utils.decodeUrlSafeNoPadding(r3.getString("second")))});
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzak) == true) goto L7;
        return false;
    L7:
        return Arrays.deepEquals(this.zzb, ((zzak) r2).zzb);
    }

    public final int hashCode() {
        byte[][] r4 = this.zzb;
        int r0 = r4.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L8;
        byte[] r3 = r4[r1];
        if (r3 == null) goto L7;
        r2 = r2 ^ Objects.hashCode(new Object[]{r3});
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r2;
    }

    public final String toString() {
        JSONObject r1 = new JSONObject();     // Catch: JSONException -> L9
        int r2 = 0;
        JSONObject r3 = null;
    L4:
        byte[][] r4 = this.zzb;     // Catch: JSONException -> L9
        if (r2 >= r4.length) goto L15;
        if (r4[r2] != null) goto L11;
        r1.put("eval", zzb(r4[r2 + 1]));     // Catch: JSONException -> L9
    L14:
        r2 = r2 + 2;     // Catch: JSONException -> L9
        goto L4
    L11:
        if (r3 != null) goto L13;
        r3 = new JSONObject();     // Catch: JSONException -> L9
        r1.put("evalByCredential", r3);     // Catch: JSONException -> L9
    L13:
        r3.put(Base64Utils.encodeUrlSafeNoPadding(this.zzb[r2]), zzb(this.zzb[r2 + 1]));     // Catch: JSONException -> L9
        goto L14
    L15:
        return "PrfExtension{" + r1.toString() + "}";
    L9:
        e = move-exception;
        return "PrfExtension{Exception:" + e.getMessage() + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r3, int r4) {
        byte[][] r2 = this.zzb;
        int r42 = SafeParcelWriter.beginObjectHeader(r3);
        SafeParcelWriter.writeByteArrayArray(r3, 1, r2, false);
        SafeParcelWriter.finishObjectHeader(r3, r42);
    }
}
