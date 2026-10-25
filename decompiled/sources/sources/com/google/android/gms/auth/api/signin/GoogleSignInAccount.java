package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes4.dex */
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = null;
    public static final Clock zaa = null;
    final List zab;
    private final String zac;
    private final String zad;
    private final String zae;
    private final String zaf;
    private final Uri zag;
    private String zah;
    private final long zai;
    private final String zaj;
    private final String zak;
    private final String zal;
    private final Set zam;

    static {
        CREATOR = new zab();
        zaa = DefaultClock.getInstance();
    }

    public GoogleSignInAccount(String r2, String r3, String r4, String r5, Uri r6, String r7, long r8, String r10, List r11, String r12, String r13) {
        this.zam = new HashSet();
        this.zac = r2;
        this.zad = r3;
        this.zae = r4;
        this.zaf = r5;
        this.zag = r6;
        this.zah = r7;
        this.zai = r8;
        this.zaj = r10;
        this.zab = r11;
        this.zak = r12;
        this.zal = r13;
    }

    public static GoogleSignInAccount zaa(String r12) {
        String r1 = null;
        if (TextUtils.isEmpty(r12) == false) goto L5;
        return null;
    L5:
        JSONObject r0 = new JSONObject(r12);
        String r122 = r0.optString("photoUrl");
        if (TextUtils.isEmpty(r122) == true) goto L8;
        Uri r8 = Uri.parse(r122);
    L9:
        long r2 = Long.parseLong(r0.getString("expirationTime"));
        HashSet r11 = new HashSet();
        JSONArray r123 = r0.getJSONArray("grantedScopes");
        int r4 = r123.length();
        int r5 = 0;
    L10:
        if (r5 >= r4) goto L12;
        r11.add(new Scope(r123.getString(r5)));
        r5 = r5 + 1;
        goto L10
    L12:
        String r124 = r0.optString("id");
        if (r0.has("tokenId") == false) goto L15;
        String r42 = r0.optString("tokenId");
    L17:
        if (r0.has("email") == false) goto L19;
        String r52 = r0.optString("email");
    L21:
        if (r0.has("displayName") == false) goto L23;
        String r6 = r0.optString("displayName");
    L25:
        if (r0.has("givenName") == false) goto L27;
        String r7 = r0.optString("givenName");
    L29:
        if (r0.has("familyName") == false) goto L31;
        String r9 = r0.optString("familyName");
    L32:
        GoogleSignInAccount r125 = zab(r124, r42, r52, r6, r7, r9, r8, Long.valueOf(r2), r0.getString("obfuscatedIdentifier"), r11);
        if (r0.has("serverAuthCode") == false) goto L35;
        r1 = r0.optString("serverAuthCode");
    L35:
        r125.zah = r1;
        return r125;
    L31:
        r9 = null;
        goto L32
    L27:
        r7 = null;
        goto L29
    L23:
        r6 = null;
        goto L25
    L19:
        r52 = null;
        goto L21
    L15:
        r42 = null;
        goto L17
    L8:
        r8 = null;
        goto L9
    }

    public static GoogleSignInAccount zab(String r13, String r14, String r15, String r16, String r17, String r18, Uri r19, Long r20, String r21, Set r22) {
        return new GoogleSignInAccount(r13, r14, r15, r16, r19, null, r20.longValue(), Preconditions.checkNotEmpty(r21), new ArrayList((Collection) Preconditions.checkNotNull(r22)), r17, r18);
    }

    public boolean equals(Object r5) {
        if (r5 != null) goto L6;
        return false;
    L6:
        if (r5 != this) goto L9;
        return true;
    L9:
        if ((r5 instanceof GoogleSignInAccount) == true) goto L11;
        return false;
    L11:
        GoogleSignInAccount r52 = (GoogleSignInAccount) r5;
        if (r52.zaj.equals(this.zaj) == true) goto L14;
    L16:
        return false;
    L14:
        if (r52.getRequestedScopes().equals(getRequestedScopes()) == false) goto L16;
        return true;
    }

    public String getDisplayName() {
        return this.zaf;
    }

    public String getEmail() {
        return this.zae;
    }

    public String getFamilyName() {
        return this.zal;
    }

    public String getGivenName() {
        return this.zak;
    }

    public String getId() {
        return this.zac;
    }

    public String getIdToken() {
        return this.zad;
    }

    public Uri getPhotoUrl() {
        return this.zag;
    }

    public Set<Scope> getRequestedScopes() {
        HashSet r0 = new HashSet(this.zab);
        r0.addAll(this.zam);
        return r0;
    }

    public String getServerAuthCode() {
        return this.zah;
    }

    public int hashCode() {
        int r0 = this.zaj.hashCode() + 527;
        int r02 = r0 * 31;
        return r02 + getRequestedScopes().hashCode();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, getId(), false);
        SafeParcelWriter.writeString(r5, 3, getIdToken(), false);
        SafeParcelWriter.writeString(r5, 4, getEmail(), false);
        SafeParcelWriter.writeString(r5, 5, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(r5, 6, getPhotoUrl(), r6, false);
        SafeParcelWriter.writeString(r5, 7, getServerAuthCode(), false);
        SafeParcelWriter.writeLong(r5, 8, this.zai);
        SafeParcelWriter.writeString(r5, 9, this.zaj, false);
        SafeParcelWriter.writeTypedList(r5, 10, this.zab, false);
        SafeParcelWriter.writeString(r5, 11, getGivenName(), false);
        SafeParcelWriter.writeString(r5, 12, getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final String zac() {
        return this.zaj;
    }

    public final String zad() {
        JSONObject r0 = new JSONObject();
    L34:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L4:
        if (getId() == null) goto L7;
        r0.put("id", getId());     // Catch: JSONException -> L34
    L7:
        if (getIdToken() == null) goto L10;
        r0.put("tokenId", getIdToken());     // Catch: JSONException -> L34
    L10:
        if (getEmail() == null) goto L13;
        r0.put("email", getEmail());     // Catch: JSONException -> L34
    L13:
        if (getDisplayName() == null) goto L16;
        r0.put("displayName", getDisplayName());     // Catch: JSONException -> L34
    L16:
        if (getGivenName() == null) goto L19;
        r0.put("givenName", getGivenName());     // Catch: JSONException -> L34
    L19:
        if (getFamilyName() == null) goto L21;
        r0.put("familyName", getFamilyName());     // Catch: JSONException -> L34
    L21:
        Uri r1 = getPhotoUrl();     // Catch: JSONException -> L34
        if (r1 == null) goto L26;
        r0.put("photoUrl", r1.toString());     // Catch: JSONException -> L34
    L26:
        if (getServerAuthCode() == null) goto L28;
        r0.put("serverAuthCode", getServerAuthCode());     // Catch: JSONException -> L34
    L28:
        r0.put("expirationTime", this.zai);     // Catch: JSONException -> L34
        r0.put("obfuscatedIdentifier", this.zaj);     // Catch: JSONException -> L34
        JSONArray r12 = new JSONArray();     // Catch: JSONException -> L34
        List r6 = this.zab;     // Catch: JSONException -> L34
        Scope[] r62 = (Scope[]) r6.toArray(new Scope[r6.size()]);     // Catch: JSONException -> L34
        Arrays.sort(r62, zaa.zaa);     // Catch: JSONException -> L34
        int r3 = r62.length;     // Catch: JSONException -> L34
        int r4 = 0;
    L29:
        if (r4 >= r3) goto L31;
        r12.put(r62[r4].getScopeUri());     // Catch: JSONException -> L34
        r4 = r4 + 1;     // Catch: JSONException -> L34
        goto L29
    L31:
        r0.put("grantedScopes", r12);     // Catch: JSONException -> L34
        r0.remove("serverAuthCode");
        return r0.toString();
    }
}
