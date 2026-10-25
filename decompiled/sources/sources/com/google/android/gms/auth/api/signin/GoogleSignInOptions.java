package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
/* loaded from: classes4.dex */
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions, ReflectedParcelable {
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR = null;
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = null;
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = null;
    public static final Scope zaa = null;
    public static final Scope zab = null;
    public static final Scope zac = null;
    public static final Scope zad = null;
    public static final Scope zae = null;
    private static final Comparator zaq = null;
    final int zaf;
    private final ArrayList zag;
    private Account zah;
    private boolean zai;
    private final boolean zaj;
    private final boolean zak;
    private String zal;
    private String zam;
    private ArrayList zan;
    private String zao;
    private Map zap;

    @Deprecated
    public static final class Builder {
        private Set zaa;
        private boolean zab;
        private boolean zac;
        private boolean zad;
        private String zae;
        private Account zaf;
        private String zag;
        private Map zah;
        private String zai;

        public Builder() {
            this.zaa = new HashSet();
            this.zah = new HashMap();
        }

        public Builder(GoogleSignInOptions r3) {
            this.zaa = new HashSet();
            this.zah = new HashMap();
            Preconditions.checkNotNull(r3);
            this.zaa = new HashSet(r3.zad());
            this.zab = r3.zag();
            this.zac = r3.zah();
            this.zad = r3.zaf();
            this.zae = r3.zai();
            this.zaf = r3.zae();
            this.zag = r3.zaj();
            this.zah = GoogleSignInOptions.zac(r3.zak());
            this.zai = r3.zal();
        }

        private final String zaa(String r2) {
            Preconditions.checkNotEmpty(r2);
            String r1 = this.zae;
            boolean r0 = true;
            if (r1 != null) goto L5;
        L8:
            Preconditions.checkArgument(r0, "two different server client ids provided");
            return r2;
        L5:
            if (r1.equals(r2) == true) goto L8;
            r0 = false;
            goto L8
        }

        public GoogleSignInOptions build() {
            if (this.zaa.contains(GoogleSignInOptions.zae) == false) goto L8;
            Set r0 = this.zaa;
            Scope r1 = GoogleSignInOptions.zad;
            if (r0.contains(r1) == false) goto L8;
            this.zaa.remove(r1);
        L8:
            if (this.zad == false) goto L15;
            if (this.zaf != null) goto L12;
        L13:
            requestId();
            goto L15
        L12:
            if (this.zaa.isEmpty() == false) goto L13;
        L15:
            return new GoogleSignInOptions(3, new ArrayList(this.zaa), this.zaf, this.zad, this.zab, this.zac, this.zae, this.zag, this.zah, this.zai, null);
        }

        public Builder requestEmail() {
            this.zaa.add(GoogleSignInOptions.zab);
            return this;
        }

        public Builder requestId() {
            this.zaa.add(GoogleSignInOptions.zac);
            return this;
        }

        public Builder requestIdToken(String r2) {
            this.zad = true;
            zaa(r2);
            this.zae = r2;
            return this;
        }

        public Builder requestProfile() {
            this.zaa.add(GoogleSignInOptions.zaa);
            return this;
        }

        public Builder requestScopes(Scope r2, Scope... r3) {
            this.zaa.add(r2);
            this.zaa.addAll(Arrays.asList(r3));
            return this;
        }

        public Builder setLogSessionId(String r1) {
            this.zai = r1;
            return this;
        }
    }

    static {
        zaa = new Scope("profile");
        zab = new Scope("email");
        zac = new Scope("openid");
        Scope r0 = new Scope("https://www.googleapis.com/auth/games_lite");
        zad = r0;
        zae = new Scope("https://www.googleapis.com/auth/games");
        Builder r1 = new Builder();
        r1.requestId();
        r1.requestProfile();
        DEFAULT_SIGN_IN = r1.build();
        Builder r12 = new Builder();
        r12.requestScopes(r0, new Scope[0]);
        DEFAULT_GAMES_SIGN_IN = r12.build();
        CREATOR = new zad();
        zaq = new zac();
    }

    public GoogleSignInOptions(int r1, ArrayList r2, Account r3, boolean r4, boolean r5, boolean r6, String r7, String r8, ArrayList r9, String r10) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, zam(r9), r10);
    }

    private GoogleSignInOptions(int r1, ArrayList r2, Account r3, boolean r4, boolean r5, boolean r6, String r7, String r8, Map r9, String r10) {
        this.zaf = r1;
        this.zag = r2;
        this.zah = r3;
        this.zai = r4;
        this.zaj = r5;
        this.zak = r6;
        this.zal = r7;
        this.zam = r8;
        this.zan = new ArrayList(r9.values());
        this.zap = r9;
        this.zao = r10;
    }

    public /* synthetic */ GoogleSignInOptions(int r1, ArrayList r2, Account r3, boolean r4, boolean r5, boolean r6, String r7, String r8, Map r9, String r10, byte[] r11) {
        this(3, r2, r3, r4, r5, r6, r7, r8, r9, r10);
    }

    public static GoogleSignInOptions zaa(String r17) {
        String r1 = null;
        if (TextUtils.isEmpty(r17) == false) goto L5;
        return null;
    L5:
        JSONObject r0 = new JSONObject(r17);
        HashSet r2 = new HashSet();
        JSONArray r3 = r0.getJSONArray("scopes");
        int r4 = r3.length();
        int r5 = 0;
    L6:
        if (r5 >= r4) goto L9;
        r2.add(new Scope(r3.getString(r5)));
        r5 = r5 + 1;
        goto L6
    L9:
        if (r0.has("accountName") == false) goto L11;
        String r32 = r0.optString("accountName");
    L13:
        if (TextUtils.isEmpty(r32) == true) goto L15;
        Account r9 = new Account(r32, "com.google");
    L16:
        ArrayList r8 = new ArrayList(r2);
        boolean r10 = r0.getBoolean("idTokenRequested");
        boolean r11 = r0.getBoolean("serverAuthRequested");
        boolean r12 = r0.getBoolean("forceCodeForRefreshToken");
        if (r0.has("serverClientId") == false) goto L19;
        String r13 = r0.optString("serverClientId");
    L21:
        if (r0.has("hostedDomain") == false) goto L24;
        r1 = r0.optString("hostedDomain");
    L24:
        return new GoogleSignInOptions(3, r8, r9, r10, r11, r12, r13, r1, new HashMap(), null);
    L19:
        r13 = null;
        goto L21
    L15:
        r9 = null;
        goto L16
    L11:
        r32 = null;
        goto L13
    }

    public static /* synthetic */ Map zac(List r0) {
        return zam(r0);
    }

    private static Map zam(List r3) {
        HashMap r0 = new HashMap();
        if (r3 == null) goto L9;
        Iterator r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L9;
        GoogleSignInOptionsExtensionParcelable r1 = (GoogleSignInOptionsExtensionParcelable) r32.next();
        r0.put(Integer.valueOf(r1.getType()), r1);
    L9:
        return r0;
    }

    public boolean equals(Object r5) {
        if (r5 != null) goto L42;
        return false;
    L42:
        GoogleSignInOptions r52 = (GoogleSignInOptions) r5;     // Catch: ClassCastException -> L41
        if (this.zan.isEmpty() == false) goto L40;
        if (r52.zan.isEmpty() == false) goto L40;
        ArrayList r1 = this.zag;     // Catch: ClassCastException -> L41
        if (r1.size() != r52.getScopes().size()) goto L40;
        if (r1.containsAll(r52.getScopes()) == false) goto L40;
        Account r12 = this.zah;     // Catch: ClassCastException -> L41
        if (r12 != null) goto L21;
        if (r52.getAccount() != null) goto L40;
    L23:
        if (TextUtils.isEmpty(this.zal) == false) goto L28;
        if (TextUtils.isEmpty(r52.getServerClientId()) == false) goto L40;
    L31:
        if (this.zak != r52.isForceCodeForRefreshToken()) goto L40;
        if (this.zai != r52.isIdTokenRequested()) goto L40;
        if (this.zaj != r52.isServerAuthCodeRequested()) goto L40;
        if (TextUtils.equals(this.zao, r52.getLogSessionId()) == false) goto L40;
        return true;
    L28:
        if (this.zal.equals(r52.getServerClientId()) == true) goto L31;
    L21:
        if (r12.equals(r52.getAccount()) == true) goto L23;
    L40:
        return false;
    }

    public Account getAccount() {
        return this.zah;
    }

    public ArrayList<GoogleSignInOptionsExtensionParcelable> getExtensions() {
        return this.zan;
    }

    public String getLogSessionId() {
        return this.zao;
    }

    public ArrayList<Scope> getScopes() {
        return new ArrayList(this.zag);
    }

    public String getServerClientId() {
        return this.zal;
    }

    public int hashCode() {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = this.zag;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        r0.add(((Scope) r1.get(r3)).getScopeUri());
        r3 = r3 + 1;
        goto L3
    L5:
        Collections.sort(r0);
        HashAccumulator r12 = new HashAccumulator();
        r12.addObject(r0);
        r12.addObject(this.zah);
        r12.addObject(this.zal);
        r12.zaa(this.zak);
        r12.zaa(this.zai);
        r12.zaa(this.zaj);
        r12.addObject(this.zao);
        return r12.hash();
    }

    public boolean isForceCodeForRefreshToken() {
        return this.zak;
    }

    public boolean isIdTokenRequested() {
        return this.zai;
    }

    public boolean isServerAuthCodeRequested() {
        return this.zaj;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zaf;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r0);
        SafeParcelWriter.writeTypedList(r5, 2, getScopes(), false);
        SafeParcelWriter.writeParcelable(r5, 3, getAccount(), r6, false);
        SafeParcelWriter.writeBoolean(r5, 4, isIdTokenRequested());
        SafeParcelWriter.writeBoolean(r5, 5, isServerAuthCodeRequested());
        SafeParcelWriter.writeBoolean(r5, 6, isForceCodeForRefreshToken());
        SafeParcelWriter.writeString(r5, 7, getServerClientId(), false);
        SafeParcelWriter.writeString(r5, 8, this.zam, false);
        SafeParcelWriter.writeTypedList(r5, 9, getExtensions(), false);
        SafeParcelWriter.writeString(r5, 10, getLogSessionId(), false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final String zab() {
        JSONObject r0 = new JSONObject();
        JSONArray r1 = new JSONArray();     // Catch: JSONException -> L17
        ArrayList r2 = this.zag;     // Catch: JSONException -> L17
        Collections.sort(r2, zaq);     // Catch: JSONException -> L17
        int r3 = r2.size();     // Catch: JSONException -> L17
        int r4 = 0;
    L4:
        if (r4 >= r3) goto L6;
        Object r5 = r2.get(r4);     // Catch: JSONException -> L17
        r4 = r4 + 1;     // Catch: JSONException -> L17
        r1.put(((Scope) r5).getScopeUri());     // Catch: JSONException -> L17
        goto L4
    L6:
        r0.put("scopes", r1);     // Catch: JSONException -> L17
        Account r12 = this.zah;     // Catch: JSONException -> L17
        if (r12 == null) goto L9;
        r0.put("accountName", r12.name);     // Catch: JSONException -> L17
    L9:
        r0.put("idTokenRequested", this.zai);     // Catch: JSONException -> L17
        r0.put("forceCodeForRefreshToken", this.zak);     // Catch: JSONException -> L17
        r0.put("serverAuthRequested", this.zaj);     // Catch: JSONException -> L17
        if (TextUtils.isEmpty(this.zal) == true) goto L13;
        r0.put("serverClientId", this.zal);     // Catch: JSONException -> L17
    L13:
        if (TextUtils.isEmpty(this.zam) == true) goto L16;
        r0.put("hostedDomain", this.zam);     // Catch: JSONException -> L17
    L16:
        return r0.toString();
    L17:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public final /* synthetic */ ArrayList zad() {
        return this.zag;
    }

    public final /* synthetic */ Account zae() {
        return this.zah;
    }

    public final /* synthetic */ boolean zaf() {
        return this.zai;
    }

    public final /* synthetic */ boolean zag() {
        return this.zaj;
    }

    public final /* synthetic */ boolean zah() {
        return this.zak;
    }

    public final /* synthetic */ String zai() {
        return this.zal;
    }

    public final /* synthetic */ String zaj() {
        return this.zam;
    }

    public final /* synthetic */ ArrayList zak() {
        return this.zan;
    }

    public final /* synthetic */ String zal() {
        return this.zao;
    }
}
