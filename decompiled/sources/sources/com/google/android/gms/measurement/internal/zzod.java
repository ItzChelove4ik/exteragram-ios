package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public final class zzod {
    private final Map zza;

    public zzod(Map r2) {
        HashMap r0 = new HashMap();
        this.zza = r0;
        r0.putAll(r2);
    }

    private final int zzf() {
        String r2 = (String) this.zza.get("PolicyVersion");     // Catch: NumberFormatException -> L8
        if (TextUtils.isEmpty(r2) == true) goto L7;
        return Integer.parseInt(r2);
    L7:
        return -1;
    }

    public final boolean equals(Object r2) {
        if ((r2 instanceof zzod) == true) goto L7;
        return false;
    L7:
        return zza().equalsIgnoreCase(((zzod) r2).zza());
    }

    public final int hashCode() {
        return zza().hashCode();
    }

    public final String toString() {
        return zza();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String zza() {
        StringBuilder r0 = new StringBuilder();
        ImmutableList r1 = zzof.zza;
        int r2 = r1.size();
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L13;
        String r4 = (String) r1.get(r3);
        Map r5 = this.zza;
        if (r5.containsKey(r4) == false) goto L11;
        if (r0.length() <= 0) goto L10;
        r0.append(";");
    L10:
        r0.append(r4);
        r0.append("=");
        r0.append((String) r5.get(r4));
    L11:
        r3 = r3 + 1;
        goto L3
    L13:
        return r0.toString();
    }

    public final Bundle zzb() {
        Map r0 = this.zza;
        if ("1".equals(r0.get("gdprApplies")) == false) goto L69;
        if ("1".equals(r0.get("EnableAdvertiserConsentMode")) == false) goto L69;
        String r4 = "denied";
        if (r0.get("Version") != null) goto L47;
        if ("1".equals(r0.get("GoogleConsent")) == false) goto L11;
        int r10 = zzf();
        if (r10 < 0) goto L15;
        String r02 = (String) r0.get("PurposeConsents");
        if (TextUtils.isEmpty(r02) == true) goto L19;
        Bundle r1 = new Bundle();
        if (r02.length() <= 0) goto L28;
        String r2 = zzjk.zza.zze;
        if (r02.charAt(0) != '1') goto L25;
        String r8 = "granted";
    L26:
        r1.putString(r2, r8);
        goto L28
    L25:
        r8 = "denied";
    L28:
        if (r02.length() <= 3) goto L37;
        String r22 = zzjk.zzd.zze;
        if (r02.charAt(2) == '1') goto L32;
    L34:
        String r82 = "denied";
    L35:
        r1.putString(r22, r82);
        goto L37
    L32:
        if (r02.charAt(3) != '1') goto L34;
        r82 = "granted";
    L37:
        if (r02.length() <= 6) goto L45;
        if (r10 < 4) goto L45;
        String r102 = zzjk.zzc.zze;
        if (r02.charAt(0) == '1') goto L42;
    L44:
        r1.putString(r102, r4);
        goto L45
    L42:
        if (r02.charAt(6) != '1') goto L44;
        r4 = "granted";
    L45:
        return r1;
    L19:
        return Bundle.EMPTY;
    L15:
        return Bundle.EMPTY;
    L11:
        return Bundle.EMPTY;
    L47:
        if (zzf() < 0) goto L69;
        Bundle r12 = new Bundle();
        String r6 = zzjk.zza.zze;
        if (true == Objects.equals(r0.get("AuthorizePurpose1"), "1")) goto L52;
        String r83 = "denied";
    L53:
        r12.putString(r6, r83);
        String r62 = zzjk.zzd.zze;
        if (Objects.equals(r0.get("AuthorizePurpose3"), "1") == true) goto L56;
    L58:
        String r84 = "denied";
    L59:
        r12.putString(r62, r84);
        if (zzf() < 4) goto L67;
        String r103 = zzjk.zzc.zze;
        if (Objects.equals(r0.get("AuthorizePurpose1"), "1") == true) goto L64;
    L66:
        r12.putString(r103, r4);
        goto L67
    L64:
        if (Objects.equals(r0.get("AuthorizePurpose7"), "1") == false) goto L66;
        r4 = "granted";
    L67:
        return r12;
    L56:
        if (Objects.equals(r0.get("AuthorizePurpose4"), "1") == false) goto L58;
        r84 = "granted";
        goto L59
    L52:
        r83 = "granted";
    L69:
        return Bundle.EMPTY;
    }

    public final String zzc() {
        String r1 = (String) this.zza.get("PurposeDiagnostics");
        if (TextUtils.isEmpty(r1) == false) goto L6;
        return "200000";
    L6:
        return r1;
    }

    public final String zzd(zzod r6) {
        Map r0 = r6.zza;
        boolean r1 = r0.isEmpty();
        String r2 = MVEL.VERSION_SUB;
        if (r1 == false) goto L5;
    L7:
        String r02 = MVEL.VERSION_SUB;
    L8:
        Bundle r5 = zzb();
        Bundle r62 = r6.zzb();
        if (r5.size() == r62.size()) goto L12;
    L19:
        r2 = "1";
    L21:
        return r02.concat(r2);
    L12:
        if (Objects.equals(r5.getString("ad_storage"), r62.getString("ad_storage")) == false) goto L19;
        if (Objects.equals(r5.getString("ad_personalization"), r62.getString("ad_personalization")) == false) goto L19;
        if (Objects.equals(r5.getString("ad_user_data"), r62.getString("ad_user_data")) == true) goto L21;
    L5:
        if (((String) r0.get("Version")) != null) goto L7;
        r02 = "1";
        goto L8
    }

    public final String zze() {
        StringBuilder r0 = new StringBuilder("1");
        int r2 = -1;
        String r3 = (String) this.zza.get("CmpSdkID");     // Catch: NumberFormatException -> L26
        if (TextUtils.isEmpty(r3) == true) goto L7;
        r2 = Integer.parseInt(r3);     // Catch: NumberFormatException -> L26
    L7:
        if (r2 >= 0) goto L9;
    L11:
        r0.append("00");
    L12:
        int r22 = zzf();
        if (r22 < 0) goto L16;
        if (r22 > 63) goto L16;
        r0.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(r22));
    L17:
        Preconditions.checkArgument(true);
        Map r6 = this.zza;
        if (true == "1".equals(r6.get("gdprApplies"))) goto L20;
        int r23 = 0;
    L21:
        int r1 = r23 | 4;
        if ("1".equals(r6.get("EnableAdvertiserConsentMode")) == false) goto L24;
        r1 = r23 | 12;
    L24:
        r0.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(r1));
        return r0.toString();
    L20:
        r23 = 2;
    L16:
        r0.append(MVEL.VERSION_SUB);
        goto L17
    L9:
        if (r2 > 4095) goto L11;
        r0.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(r2 >> 6));
        r0.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(r2 & 63));
        goto L12
    }
}
