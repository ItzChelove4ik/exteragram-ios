package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzabw;
import com.google.android.gms.internal.measurement.zzabx;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

/* loaded from: classes5.dex */
public abstract class zzof {
    static final ImmutableList zza = null;

    static {
        zza = ImmutableList.of("Version", "GoogleConsent", "VendorConsent", "VendorLegitimateInterest", "gdprApplies", "EnableAdvertiserConsentMode", "PolicyVersion", "PurposeConsents", "PurposeOneTreatment", "Purpose1", "Purpose3", "Purpose4", new String[]{"Purpose7", "CmpSdkID", "PublisherCC", "PublisherRestrictions1", "PublisherRestrictions3", "PublisherRestrictions4", "PublisherRestrictions7", "AuthorizePurpose1", "AuthorizePurpose3", "AuthorizePurpose4", "AuthorizePurpose7", "PurposeDiagnostics"});
    }

    public static String zza(SharedPreferences r1, String r2) {
        return r1.getString(r2, _UrlKt.FRAGMENT_ENCODE_SET);
    L5:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static int zzb(SharedPreferences r1, String r2) {
        return r1.getInt(r2, -1);
    L5:
        return -1;
    }

    public static final boolean zzc(zzabw r18, ImmutableMap r19, ImmutableMap r20, ImmutableSet r21, char[] r22, int r23, int r24, int r25, int r26, int r27, String r28, String r29, String r30, boolean r31, boolean r32, boolean r33) {
        int r0 = zze(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, true);
        if (r0 <= 0) goto L12;
        int r8 = r25;
        if (r8 != 1) goto L10;
        int r7 = r24;
        if (r7 == 1) goto L9;
        r8 = 1;
    L11:
        r22[r0] = '2';
    L14:
        if (zzi(r18, r19, r20, r21, r22, r23, r7, r8, r26, r27, r28, r29, r30, r31, r32, true) != zzabx.zza) goto L16;
        char r02 = '3';
    L64:
        if (r0 > 0) goto L66;
        return false;
    L66:
        if (r22[r0] == '2') goto L73;
        r22[r0] = r02;
        return false;
    L73:
        return false;
    L16:
        int r10 = r27;
        if (r18 != zzabw.zzb) goto L28;
        ImmutableSet r4 = r21;
        String r11 = r28;
        if (r10 != 1) goto L30;
        if (r4.contains(r11) == false) goto L27;
        if (r0 > 0) goto L24;
    L26:
        return true;
    L24:
        if (r22[r0] == '2') goto L26;
        r22[r0] = '1';
        goto L26
    L27:
        r10 = 1;
    L30:
        if (r19.containsKey(r18) == true) goto L32;
    L31:
        r02 = '0';
        goto L64
    L32:
        zzoe r03 = (zzoe) r19.get(r18);
        if (r03 == null) goto L31;
        int r04 = r03.ordinal();
        if (r04 == 0) goto L62;
        if (r04 == 1) goto L57;
        if (r04 == 2) goto L51;
        if (r04 != 3) goto L31;
        if (zzi(r18, r19, r20, r4, r22, r23, r7, r8, r26, r10, r11, r29, r30, r31, r32, true) != zzabx.zzb) goto L49;
        return zzg(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true);
    L49:
        return zzh(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true);
    L51:
        if (zzi(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true) != zzabx.zzc) goto L55;
        return zzh(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true);
    L55:
        return zzg(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true);
    L57:
        if (zzi(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true) != zzabx.zzb) goto L60;
    L58:
        r02 = '8';
        goto L64
    L60:
        return zzh(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true);
    L62:
        if (zzi(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true) == zzabx.zzc) goto L58;
        return zzg(r18, r19, r20, r21, r22, r23, r7, r8, r26, r10, r28, r29, r30, r31, r32, true);
    L28:
        r4 = r21;
        r11 = r28;
        goto L30
    L9:
        r7 = 1;
        r8 = 1;
        goto L14
    L10:
        r7 = r24;
        goto L11
    L12:
        r7 = r24;
        r8 = r25;
        goto L14
    }

    public static final Map zzd(ImmutableMap r27, ImmutableMap r28, ImmutableSet r29, char[] r30, int r31, int r32, int r33, int r34, int r35, String r36, String r37, String r38, boolean r39, boolean r40, boolean r41) {
        if (r41 == false) goto L5;
        zzabw r1 = zzabw.zzb;
        zzabx r0 = (zzabx) r28.get(r1);
        zzabw r2 = zzabw.zzd;
        zzabx r4 = (zzabx) r28.get(r2);
        zzabw r5 = zzabw.zze;
        zzabx r6 = (zzabx) r28.get(r5);
        zzabw r7 = zzabw.zzh;
        zzabx r8 = (zzabx) r28.get(r7);
        ImmutableMap.Builder r9 = ImmutableMap.builder().put("Version", "2");
        String r17 = "1";
        if (true == r39) goto L9;
        Object r11 = MVEL.VERSION_SUB;
    L10:
        ImmutableMap.Builder r92 = r9.put("VendorConsent", r11);
        if (true == r40) goto L13;
        Object r112 = MVEL.VERSION_SUB;
    L14:
        ImmutableMap.Builder r93 = r92.put("VendorLegitimateInterest", r112);
        if (r33 == 1) goto L17;
        Object r12 = MVEL.VERSION_SUB;
    L18:
        ImmutableMap.Builder r94 = r93.put("gdprApplies", r12);
        if (r32 == 1) goto L21;
        Object r13 = MVEL.VERSION_SUB;
    L22:
        ImmutableMap.Builder r95 = r94.put("EnableAdvertiserConsentMode", r13).put("PolicyVersion", String.valueOf(r34)).put("CmpSdkID", String.valueOf(r31));
        if (r35 == 1) goto L26;
        Object r132 = MVEL.VERSION_SUB;
    L27:
        ImmutableMap.Builder r02 = r95.put("PurposeOneTreatment", r132).put("PublisherCC", r36);
        if (r0 == null) goto L31;
        int r96 = r0.zza();
    L32:
        ImmutableMap.Builder r03 = r02.put("PublisherRestrictions1", String.valueOf(r96));
        if (r4 == null) goto L35;
        int r14 = r4.zza();
    L36:
        ImmutableMap.Builder r04 = r03.put("PublisherRestrictions3", String.valueOf(r14));
        if (r6 == null) goto L39;
        int r15 = r6.zza();
    L40:
        ImmutableMap.Builder r05 = r04.put("PublisherRestrictions4", String.valueOf(r15));
        if (r8 == null) goto L43;
        int r16 = r8.zza();
    L44:
        ImmutableMap.Builder r06 = r05.put("PublisherRestrictions7", String.valueOf(r16)).putAll(ImmutableMap.of("Purpose1", zzf(r1, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true), "Purpose3", zzf(r2, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true), "Purpose4", zzf(r5, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true), "Purpose7", zzf(r7, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true)));
        if (true == zzc(r1, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true)) goto L47;
        Object r19 = MVEL.VERSION_SUB;
    L49:
        if (true == zzc(r2, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true)) goto L51;
        Object r20 = MVEL.VERSION_SUB;
    L53:
        if (true == zzc(r5, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true)) goto L55;
        Object r22 = MVEL.VERSION_SUB;
    L57:
        if (true == zzc(r7, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, true)) goto L59;
        r17 = MVEL.VERSION_SUB;
    L59:
        String r07 = new String(r30);
        Object r282 = r19;
        Object r302 = r20;
        Object r322 = r22;
        return r06.putAll(ImmutableMap.of("AuthorizePurpose1", r282, "AuthorizePurpose3", r302, "AuthorizePurpose4", r322, "AuthorizePurpose7", r17, "PurposeDiagnostics", r07)).buildOrThrow();
    L55:
        r22 = "1";
        goto L57
    L51:
        r20 = "1";
        goto L53
    L47:
        r19 = "1";
        goto L49
    L43:
        r16 = zzabx.zzd.zza();
        goto L44
    L39:
        r15 = zzabx.zzd.zza();
        goto L40
    L35:
        r14 = zzabx.zzd.zza();
        goto L36
    L31:
        r96 = zzabx.zzd.zza();
        goto L32
    L26:
        r132 = "1";
        goto L27
    L21:
        r13 = "1";
        goto L22
    L17:
        r12 = "1";
        goto L18
    L13:
        r112 = "1";
        goto L14
    L9:
        r11 = "1";
        goto L10
    L5:
        return ImmutableMap.of();
    }

    private static final int zze(zzabw r0, ImmutableMap r1, ImmutableMap r2, ImmutableSet r3, char[] r4, int r5, int r6, int r7, int r8, int r9, String r10, String r11, String r12, boolean r13, boolean r14, boolean r15) {
        if (r0 != zzabw.zzb) goto L7;
        return 1;
    L7:
        if (r0 != zzabw.zzd) goto L11;
        return 2;
    L11:
        if (r0 != zzabw.zze) goto L15;
        return 3;
    L15:
        if (r0 != zzabw.zzh) goto L18;
        return 4;
    L18:
        return -1;
    }

    private static final String zzf(zzabw r0, ImmutableMap r1, ImmutableMap r2, ImmutableSet r3, char[] r4, int r5, int r6, int r7, int r8, int r9, String r10, String r11, String r12, boolean r13, boolean r14, boolean r15) {
        boolean r16 = TextUtils.isEmpty(r11);
        String r22 = MVEL.VERSION_SUB;
        if (r16 == false) goto L5;
    L7:
        String r17 = MVEL.VERSION_SUB;
    L9:
        if (TextUtils.isEmpty(r12) == true) goto L14;
        if (r12.length() < r0.zza()) goto L14;
        r22 = String.valueOf(r12.charAt(r0.zza() - 1));
    L14:
        return String.valueOf(r17).concat(String.valueOf(r22));
    L5:
        if (r11.length() < r0.zza()) goto L7;
        r17 = String.valueOf(r11.charAt(r0.zza() - 1));
        goto L9
    }

    private static final boolean zzg(zzabw r17, ImmutableMap r18, ImmutableMap r19, ImmutableSet r20, char[] r21, int r22, int r23, int r24, int r25, int r26, String r27, String r28, String r29, boolean r30, boolean r31, boolean r32) {
        int r0 = zze(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, true);
        boolean r1 = false;
        if (r30 == true) goto L6;
        char r3 = '4';
    L8:
        if (r0 > 0) goto L10;
    L12:
        return false;
    L10:
        if (r21[r0] == '2') goto L12;
        r21[r0] = r3;
        goto L12
    L6:
        if (r28.length() >= r17.zza()) goto L13;
        r3 = '0';
        goto L8
    L13:
        char r33 = r28.charAt(r17.zza() - 1);
        char r4 = '1';
        if (r33 != '1') goto L16;
        r1 = true;
    L16:
        if (r0 > 0) goto L18;
    L23:
        return r1;
    L18:
        if (r21[r0] == '2') goto L23;
        if (r33 == '1') goto L22;
        r4 = '6';
    L22:
        r21[r0] = r4;
        goto L23
    }

    private static final boolean zzh(zzabw r17, ImmutableMap r18, ImmutableMap r19, ImmutableSet r20, char[] r21, int r22, int r23, int r24, int r25, int r26, String r27, String r28, String r29, boolean r30, boolean r31, boolean r32) {
        int r0 = zze(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, true);
        boolean r1 = false;
        if (r31 == true) goto L6;
        char r3 = '5';
    L8:
        if (r0 > 0) goto L10;
    L12:
        return false;
    L10:
        if (r21[r0] == '2') goto L12;
        r21[r0] = r3;
        goto L12
    L6:
        if (r29.length() >= r17.zza()) goto L13;
        r3 = '0';
        goto L8
    L13:
        char r33 = r29.charAt(r17.zza() - 1);
        char r4 = '1';
        if (r33 != '1') goto L16;
        r1 = true;
    L16:
        if (r0 > 0) goto L18;
    L23:
        return r1;
    L18:
        if (r21[r0] == '2') goto L23;
        if (r33 == '1') goto L22;
        r4 = '7';
    L22:
        r21[r0] = r4;
        goto L23
    }

    private static final zzabx zzi(zzabw r0, ImmutableMap r1, ImmutableMap r2, ImmutableSet r3, char[] r4, int r5, int r6, int r7, int r8, int r9, String r10, String r11, String r12, boolean r13, boolean r14, boolean r15) {
        return (zzabx) r2.getOrDefault(r0, zzabx.zzd);
    }
}
