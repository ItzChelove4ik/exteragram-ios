package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.chaquo.python.internal.Common;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.measurement.zzadf;
import com.google.android.gms.internal.measurement.zzafb;
import com.google.android.gms.internal.measurement.zzaif;
import com.google.android.gms.internal.measurement.zzair;
import com.google.android.gms.internal.measurement.zzhv;
import j$.time.ZonedDateTime;
import j$.time.format.DateTimeFormatter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.NotificationBadge;

/* loaded from: classes5.dex */
public final class zzpk extends zzos {
    private long zza;
    private long zzb;

    public zzpk(zzpg r1) {
        super(r1);
    }

    public static final void zzF(com.google.android.gms.internal.measurement.zzhr r3, String r4, Object r5) {
        List r0 = r3.zza();
        int r1 = 0;
    L4:
        if (r1 >= r0.size()) goto L9;
        if (r4.equals(((com.google.android.gms.internal.measurement.zzhw) r0.get(r1)).zzb()) == true) goto L10;
        r1 = r1 + 1;
    L10:
        zzhv r02 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r02.zzb(r4);
        r02.zzf(((Long) r5).longValue());
        if (r1 < 0) goto L14;
        r3.zze(r1, r02);
        return;
    L14:
        r3.zzg(r02);
        return;
    L9:
        r1 = -1;
        goto L10
    }

    public static final boolean zzG(zzbh r0, zzr r1) {
        Preconditions.checkNotNull(r0);
        Preconditions.checkNotNull(r1);
        if (TextUtils.isEmpty(r1.zzb) == false) goto L6;
        return false;
    L6:
        return true;
    }

    public static final Bundle zzH(List r5) {
        Bundle r0 = new Bundle();
        Iterator r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L17;
        com.google.android.gms.internal.measurement.zzhw r1 = (com.google.android.gms.internal.measurement.zzhw) r52.next();
        String r2 = r1.zzb();
        if (r1.zzj() == true) goto L7;
        if (r1.zzh() == true) goto L10;
        if (r1.zzc() == true) goto L13;
        if (r1.zze() == false) goto L4;
        r0.putLong(r2, r1.zzf());
        goto L4
    L13:
        r0.putString(r2, r1.zzd());
        goto L4
    L10:
        r0.putFloat(r2, r1.zzi());
        goto L4
    L7:
        r0.putDouble(r2, r1.zzk());
        goto L4
    L17:
        return r0;
    }

    public static final com.google.android.gms.internal.measurement.zzhw zzI(com.google.android.gms.internal.measurement.zzhs r2, String r3) {
        Iterator r22 = r2.zza().iterator();
    L4:
        if (r22.hasNext() == false) goto L8;
        com.google.android.gms.internal.measurement.zzhw r0 = (com.google.android.gms.internal.measurement.zzhw) r22.next();
        if (r0.zzb().equals(r3) == false) goto L4;
        return r0;
    L8:
        return null;
    }

    public static final String zzJ(Map r2, String r3) {
        if (r2 == null) goto L21;
        Iterator r22 = r2.entrySet().iterator();
    L6:
        if (r22.hasNext() == false) goto L22;
        Map.Entry r0 = (Map.Entry) r22.next();
        if (r3.equalsIgnoreCase((String) r0.getKey()) == false) goto L6;
        if (r0.getValue() != null) goto L12;
        return null;
    L12:
        if (((List) r0.getValue()).isEmpty() == false) goto L14;
        return null;
    L14:
        return (String) ((List) r0.getValue()).get(0);
    L22:
        return null;
    L21:
        return null;
    }

    public static final Map zzK(com.google.android.gms.internal.measurement.zzhs r4, String... r5) {
        HashMap r0 = new HashMap();
        Iterator r42 = r4.zza().iterator();
    L4:
        if (r42.hasNext() == false) goto L10;
        com.google.android.gms.internal.measurement.zzhw r1 = (com.google.android.gms.internal.measurement.zzhw) r42.next();
        if (Arrays.asList(r5).contains(r1.zzb()) == false) goto L4;
        Object r2 = zzT(r1);
        if (r2 == null) goto L4;
        r0.put(r1.zzb(), r2);
        goto L4
    L10:
        return r0;
    }

    public static final Map zzL(com.google.android.gms.internal.measurement.zzhs r3, String r4) {
        HashMap r42 = new HashMap();
        Iterator r32 = r3.zza().iterator();
    L4:
        if (r32.hasNext() == false) goto L10;
        com.google.android.gms.internal.measurement.zzhw r0 = (com.google.android.gms.internal.measurement.zzhw) r32.next();
        if (r0.zzb().startsWith("gad_") == false) goto L4;
        Object r1 = zzT(r0);
        if (r1 == null) goto L4;
        r42.put(r0.zzb(), r1);
        goto L4
    L10:
        return r42;
    }

    public static final Object zzM(com.google.android.gms.internal.measurement.zzhs r0, String r1) {
        com.google.android.gms.internal.measurement.zzhw r02 = zzI(r0, r1);
        if (r02 != null) goto L7;
        return null;
    L7:
        return zzT(r02);
    }

    public static final Object zzN(com.google.android.gms.internal.measurement.zzhs r0, String r1, Object r2) {
        Object r02 = zzM(r0, r1);
        if (r02 != null) goto L5;
        return r2;
    L5:
        return r02;
    }

    private final void zzO(StringBuilder r6, int r7, List r8) {
        if (r8 == null) goto L28;
        int r72 = r7 + 1;
        Iterator r82 = r8.iterator();
    L6:
        if (r82.hasNext() == false) goto L34;
        com.google.android.gms.internal.measurement.zzhw r0 = (com.google.android.gms.internal.measurement.zzhw) r82.next();
        if (r0 == null) goto L6;
        zzQ(r6, r72);
        r6.append("param {\n");
        Double r2 = null;
        if (r0.zza() == false) goto L12;
        String r1 = this.zzu.zzl().zzb(r0.zzb());
    L13:
        zzW(r6, r72, "name", r1);
        if (r0.zzc() == false) goto L16;
        String r12 = r0.zzd();
    L17:
        zzW(r6, r72, "string_value", r12);
        if (r0.zze() == false) goto L20;
        Long r13 = Long.valueOf(r0.zzf());
    L21:
        zzW(r6, r72, "int_value", r13);
        if (r0.zzj() == false) goto L24;
        r2 = Double.valueOf(r0.zzk());
    L24:
        zzW(r6, r72, "double_value", r2);
        if (r0.zzm() <= 0) goto L27;
        zzO(r6, r72, r0.zzl());
    L27:
        zzQ(r6, r72);
        r6.append("}\n");
        goto L6
    L20:
        r13 = null;
        goto L21
    L16:
        r12 = null;
        goto L17
    L12:
        r1 = null;
        goto L13
    L34:
        return;
    }

    private final void zzP(StringBuilder r5, int r6, com.google.android.gms.internal.measurement.zzfh r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        zzQ(r5, r6);
        r5.append("filter {\n");
        if (r7.zze() == false) goto L8;
        zzW(r5, r6, "complement", Boolean.valueOf(r7.zzf()));
    L8:
        if (r7.zzh() == false) goto L11;
        zzW(r5, r6, "param_name", this.zzu.zzl().zzb(r7.zzi()));
    L11:
        if (r7.zza() == false) goto L42;
        int r4 = r6 + 1;
        com.google.android.gms.internal.measurement.zzfr r1 = r7.zzb();
        if (r1 == null) goto L42;
        zzQ(r5, r4);
        r5.append("string_filter {\n");
        if (r1.zza() == false) goto L28;
        switch(r1.zzk()) {
            case 1: goto L25;
            case 2: goto L24;
            case 3: goto L23;
            case 4: goto L22;
            case 5: goto L21;
            case 6: goto L20;
            default: goto L19;
        };
    L19:
        String r2 = "IN_LIST";
    L26:
        zzW(r5, r4, "match_type", r2);
        goto L28
    L20:
        r2 = "EXACT";
        goto L26
    L21:
        r2 = "PARTIAL";
        goto L26
    L22:
        r2 = "ENDS_WITH";
        goto L26
    L23:
        r2 = "BEGINS_WITH";
        goto L26
    L24:
        r2 = "REGEXP";
        goto L26
    L25:
        r2 = "UNKNOWN_MATCH_TYPE";
    L28:
        if (r1.zzb() == false) goto L31;
        zzW(r5, r4, "expression", r1.zzc());
    L31:
        if (r1.zzd() == false) goto L34;
        zzW(r5, r4, "case_sensitive", Boolean.valueOf(r1.zze()));
    L34:
        if (r1.zzh() <= 0) goto L40;
        zzQ(r5, r6 + 2);
        r5.append("expression_list {\n");
        Iterator r12 = r1.zzf().iterator();
    L37:
        if (r12.hasNext() == false) goto L39;
        String r22 = (String) r12.next();
        zzQ(r5, r6 + 3);
        r5.append(r22);
        r5.append("\n");
        goto L37
    L39:
        r5.append("}\n");
    L40:
        zzQ(r5, r4);
        r5.append("}\n");
    L42:
        if (r7.zzc() == false) goto L44;
        zzX(r5, r6 + 1, "number_filter", r7.zzd());
    L44:
        zzQ(r5, r6);
        r5.append("}\n");
    }

    private static final void zzQ(StringBuilder r2, int r3) {
        int r0 = 0;
    L3:
        if (r0 >= r3) goto L5;
        r2.append("  ");
        r0 = r0 + 1;
        goto L3
    }

    private static final void zzR(Uri.Builder r0, String r1, String r2, Set r3) {
        if (r3.contains(r1) == false) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r2) == true) goto L10;
        r0.appendQueryParameter(r1, r2);
        return;
    }

    private static final String zzS(boolean r1, boolean r2, boolean r3) {
        StringBuilder r0 = new StringBuilder();
        if (r1 == false) goto L5;
        r0.append("Dynamic ");
    L5:
        if (r2 == false) goto L7;
        r0.append("Sequence ");
    L7:
        if (r3 == false) goto L10;
        r0.append("Session-Scoped ");
    L10:
        return r0.toString();
    }

    private static final Object zzT(com.google.android.gms.internal.measurement.zzhw r2) {
        if (r2.zzc() == false) goto L7;
        return r2.zzd();
    L7:
        if (r2.zze() == false) goto L11;
        return Long.valueOf(r2.zzf());
    L11:
        if (r2.zzj() == false) goto L15;
        return Double.valueOf(r2.zzk());
    L15:
        if (r2.zzm() > 0) goto L17;
        return null;
    L17:
        return zzy(r2.zzl());
    }

    private static final void zzU(Uri.Builder r5, String[] r6, Bundle r7, Set r8) {
        int r1 = 0;
    L4:
        if (r1 >= r6.length) goto L9;
        String[] r2 = r6[r1].split(",");
        String r3 = r2[0];
        String r22 = r2[r2.length - 1];
        String r32 = r7.getString(r3);
        if (r32 == null) goto L8;
        zzR(r5, r22, r32, r8);
    L8:
        r1 = r1 + 1;
        goto L4
    }

    private static final void zzV(StringBuilder r9, int r10, String r11, com.google.android.gms.internal.measurement.zzii r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        zzQ(r9, 3);
        r9.append(r11);
        r9.append(" {\n");
        if (r12.zzd() == 0) goto L15;
        zzQ(r9, 4);
        r9.append("results: ");
        Iterator r112 = r12.zzc().iterator();
        int r4 = 0;
    L8:
        if (r112.hasNext() == false) goto L13;
        Long r5 = (Long) r112.next();
        int r6 = r4 + 1;
        if (r4 == 0) goto L12;
        r9.append(", ");
    L12:
        r9.append(r5);
        r4 = r6;
        goto L8
    L13:
        r9.append('\n');
    L15:
        if (r12.zzb() == 0) goto L25;
        zzQ(r9, 4);
        r9.append("status: ");
        Iterator r113 = r12.zza().iterator();
        int r42 = 0;
    L18:
        if (r113.hasNext() == false) goto L23;
        Long r52 = (Long) r113.next();
        int r62 = r42 + 1;
        if (r42 == 0) goto L22;
        r9.append(", ");
    L22:
        r9.append(r52);
        r42 = r62;
        goto L18
    L23:
        r9.append('\n');
    L25:
        if (r12.zzf() == 0) goto L43;
        zzQ(r9, 4);
        r9.append("dynamic_filter_timestamps: {");
        Iterator r114 = r12.zze().iterator();
        int r53 = 0;
    L28:
        if (r114.hasNext() == false) goto L41;
        com.google.android.gms.internal.measurement.zzhq r63 = (com.google.android.gms.internal.measurement.zzhq) r114.next();
        int r7 = r53 + 1;
        if (r53 == 0) goto L33;
        r9.append(", ");
    L33:
        if (r63.zza() == false) goto L35;
        Integer r54 = Integer.valueOf(r63.zzb());
    L36:
        r9.append(r54);
        r9.append(":");
        if (r63.zzc() == false) goto L39;
        Long r55 = Long.valueOf(r63.zzd());
    L40:
        r9.append(r55);
        r53 = r7;
        goto L28
    L39:
        r55 = null;
        goto L40
    L35:
        r54 = null;
        goto L36
    L41:
        r9.append("}\n");
    L43:
        if (r12.zzi() == 0) goto L63;
        zzQ(r9, 4);
        r9.append("sequence_filter_timestamps: {");
        Iterator r115 = r12.zzh().iterator();
        int r122 = 0;
    L46:
        if (r115.hasNext() == false) goto L62;
        com.google.android.gms.internal.measurement.zzik r1 = (com.google.android.gms.internal.measurement.zzik) r115.next();
        int r56 = r122 + 1;
        if (r122 == 0) goto L51;
        r9.append(", ");
    L51:
        if (r1.zza() == false) goto L53;
        Integer r123 = Integer.valueOf(r1.zzb());
    L54:
        r9.append(r123);
        r9.append(": [");
        Iterator r124 = r1.zzc().iterator();
        int r13 = 0;
    L56:
        if (r124.hasNext() == false) goto L61;
        long r64 = ((Long) r124.next()).longValue();
        int r8 = r13 + 1;
        if (r13 == 0) goto L60;
        r9.append(", ");
    L60:
        r9.append(r64);
        r13 = r8;
        goto L56
    L61:
        r9.append("]");
        r122 = r56;
        goto L46
    L53:
        r123 = null;
        goto L54
    L62:
        r9.append("}\n");
    L63:
        zzQ(r9, 3);
        r9.append("}\n");
    }

    private static final void zzW(StringBuilder r0, int r1, String r2, Object r3) {
        if (r3 != null) goto L4;
        return;
    L4:
        zzQ(r0, r1 + 1);
        r0.append(r2);
        r0.append(": ");
        r0.append(r3);
        r0.append('\n');
    }

    private static final void zzX(StringBuilder r1, int r2, String r3, com.google.android.gms.internal.measurement.zzfl r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        zzQ(r1, r2);
        r1.append(r3);
        r1.append(" {\n");
        if (r4.zza() == false) goto L21;
        int r32 = r4.zzm();
        if (r32 != 1) goto L9;
        String r33 = "UNKNOWN_COMPARISON_TYPE";
    L19:
        zzW(r1, r2, "comparison_type", r33);
        goto L21
    L9:
        if (r32 != 2) goto L11;
        r33 = "LESS_THAN";
        goto L19
    L11:
        if (r32 != 3) goto L13;
        r33 = "GREATER_THAN";
        goto L19
    L13:
        if (r32 == 4) goto L15;
        r33 = "BETWEEN";
        goto L19
    L15:
        r33 = "EQUAL";
    L21:
        if (r4.zzb() == false) goto L24;
        zzW(r1, r2, "match_as_float", Boolean.valueOf(r4.zzc()));
    L24:
        if (r4.zzd() == false) goto L27;
        zzW(r1, r2, "comparison_value", r4.zze());
    L27:
        if (r4.zzf() == false) goto L30;
        zzW(r1, r2, "min_comparison_value", r4.zzh());
    L30:
        if (r4.zzi() == false) goto L32;
        zzW(r1, r2, "max_comparison_value", r4.zzj());
    L32:
        zzQ(r1, r2);
        r1.append("}\n");
    }

    public static boolean zzm(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.matches("([+-])?([0-9]+\\.?[0-9]*|[0-9]*\\.?[0-9]+)") == true) goto L6;
        return false;
    L6:
        if (r1.length() > 310) goto L12;
        return true;
    L12:
        return false;
    }

    public static boolean zzn(List r4, int r5) {
        if (r5 >= (r4.size() * 64)) goto L8;
        long r0 = ((Long) r4.get(r5 / 64)).longValue();
        if (((1 << (r5 % 64)) & r0) == 0) goto L10;
        return true;
    L10:
        return false;
    L8:
        return false;
    }

    public static List zzp(BitSet r10) {
        int r0 = (r10.length() + 63) / 64;
        ArrayList r1 = new ArrayList(r0);
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L14;
        long r5 = 0;
        int r7 = 0;
    L5:
        if (r7 >= 64) goto L13;
        int r8 = (r4 * 64) + r7;
        if (r8 >= r10.length()) goto L13;
        if (r10.get(r8) == false) goto L12;
        r5 = r5 | (1 << r7);
    L12:
        r7 = r7 + 1;
    L13:
        r1.add(Long.valueOf(r5));
        r4 = r4 + 1;
        goto L3
    L14:
        return r1;
    }

    public static zzafb zzw(zzafb r1, byte[] r2) {
        zzadf r0 = zzadf.zzb();
        if (r0 == null) goto L7;
        return r1.zzaW(r2, r0);
    L7:
        return r1.zzaX(r2);
    }

    public static int zzx(com.google.android.gms.internal.measurement.zzic r2, String r3) {
        int r0 = 0;
    L4:
        if (r0 >= r2.zzl()) goto L9;
        if (r3.equals(r2.zzm(r0).zzc()) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    public static Bundle[] zzy(List r7) {
        ArrayList r0 = new ArrayList();
        Iterator r72 = r7.iterator();
    L4:
        if (r72.hasNext() == false) goto L23;
        com.google.android.gms.internal.measurement.zzhw r1 = (com.google.android.gms.internal.measurement.zzhw) r72.next();
        if (r1 == null) goto L4;
        Bundle r2 = new Bundle();
        Iterator r12 = r1.zzl().iterator();
    L9:
        if (r12.hasNext() == false) goto L20;
        com.google.android.gms.internal.measurement.zzhw r3 = (com.google.android.gms.internal.measurement.zzhw) r12.next();
        if (r3.zzc() == true) goto L12;
        if (r3.zze() == true) goto L15;
        if (r3.zzj() == false) goto L9;
        r2.putDouble(r3.zzb(), r3.zzk());
        goto L9
    L15:
        r2.putLong(r3.zzb(), r3.zzf());
        goto L9
    L12:
        r2.putString(r3.zzb(), r3.zzd());
        goto L9
    L20:
        if (r2.isEmpty() == true) goto L4;
        r0.add(r2);
        goto L4
    L23:
        return (Bundle[]) r0.toArray(new Bundle[r0.size()]);
    }

    public final zzbh zzA(com.google.android.gms.internal.measurement.zzaa r10) {
        Bundle r9 = zzB(r10.zzf(), true);
        if (r9.containsKey("_o") == false) goto L8;
        Object r0 = r9.get("_o");
        if (r0 == null) goto L8;
        String r02 = r0.toString();
    L7:
        String r4 = r02;
        String r03 = zzjm.zzb(r10.zzb());
        if (r03 != null) goto L13;
        r03 = r10.zzb();
    L13:
        return new zzbh(r03, new zzbf(r9), r4, r10.zza(), 0);
    L8:
        r02 = Common.ASSET_APP;
        goto L7
    }

    public final Bundle zzB(Map r10, boolean r11) {
        Bundle r0 = new Bundle();
        Iterator r1 = r10.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L22;
        String r2 = (String) r1.next();
        Object r3 = r10.get(r2);
        if (r3 == null) goto L7;
        if ((r3 instanceof Long) == true) goto L10;
        if ((r3 instanceof Double) == true) goto L13;
        if ((r3 instanceof ArrayList) == true) goto L16;
        r0.putString(r2, r3.toString());
        goto L4
    L16:
        if (r11 == false) goto L4;
        ArrayList r32 = (ArrayList) r3;
        ArrayList r4 = new ArrayList();
        int r5 = r32.size();
        int r7 = 0;
    L18:
        if (r7 >= r5) goto L20;
        r4.add(zzB((Map) r32.get(r7), false));
        r7 = r7 + 1;
        goto L18
    L20:
        r0.putParcelableArray(r2, (Parcelable[]) r4.toArray(new Parcelable[0]));
        goto L4
    L13:
        r0.putDouble(r2, ((Double) r3).doubleValue());
        goto L4
    L10:
        r0.putLong(r2, ((Long) r3).longValue());
        goto L4
    L7:
        r0.putString(r2, null);
        goto L4
    L22:
        return r0;
    }

    @TargetApi(26)
    public final long zzC(String r5) {
        if (Build.VERSION.SDK_INT >= 26) goto L8;
    L7:
        return 0;
    L8:
        return ZonedDateTime.parse(r5, DateTimeFormatter.RFC_1123_DATE_TIME).toInstant().toEpochMilli();
    L6:
        this.zzu.zzaW().zze().zzb("Unable to parse header time, time", r5);
        goto L7
    }

    public final void zzD(Map r9) {
        if (Build.VERSION.SDK_INT < 26) goto L12;
        String r92 = zzJ(r9, "Date");
        if (TextUtils.isEmpty(r92) == true) goto L13;
        long r0 = zzC(r92);
        if (r0 <= 0) goto L14;
        long r4 = this.zzu.zzba().elapsedRealtime();
        zzg();
        if (this.zzb != 0) goto L15;
        this.zza = r4;
        this.zzb = r0;
        return;
    L15:
        return;
    L14:
        return;
    L13:
        return;
    }

    public final long zzE(long r6) {
        zzg();
        long r0 = this.zzb;
        if (r0 != 0) goto L5;
    L8:
        return 0;
    L5:
        if (r6 == 0) goto L8;
        return (r0 - this.zza) + r6;
    }

    @Override // com.google.android.gms.measurement.internal.zzos
    public final boolean zzbc() {
        return false;
    }

    public final void zzc(com.google.android.gms.internal.measurement.zzit r3, Object r4) {
        Preconditions.checkNotNull(r4);
        r3.zzd();
        r3.zzf();
        r3.zzh();
        if ((r4 instanceof String) == false) goto L7;
        r3.zzc((String) r4);
        return;
    L7:
        if ((r4 instanceof Long) == false) goto L11;
        r3.zze(((Long) r4).longValue());
        return;
    L11:
        if ((r4 instanceof Double) == false) goto L14;
        r3.zzg(((Double) r4).doubleValue());
        return;
    L14:
        this.zzu.zzaW().zzb().zzb("Ignoring invalid (type) user attribute value", r4);
    }

    public final void zzd(zzhv r10, Object r11) {
        Preconditions.checkNotNull(r11);
        r10.zze();
        r10.zzg();
        r10.zzi();
        r10.zzm();
        if ((r11 instanceof String) == false) goto L7;
        r10.zzd((String) r11);
        return;
    L7:
        if ((r11 instanceof Long) == false) goto L11;
        r10.zzf(((Long) r11).longValue());
        return;
    L11:
        if ((r11 instanceof Double) == false) goto L15;
        r10.zzh(((Double) r11).doubleValue());
        return;
    L15:
        if ((r11 instanceof Bundle[]) == false) goto L40;
        Bundle[] r112 = (Bundle[]) r11;
        ArrayList r9 = new ArrayList();
        int r0 = r112.length;
        int r1 = 0;
    L17:
        if (r1 >= r0) goto L38;
        Bundle r2 = r112[r1];
        if (r2 == null) goto L37;
        zzhv r3 = com.google.android.gms.internal.measurement.zzhw.zzn();
        Iterator<String> r4 = r2.keySet().iterator();
    L23:
        if (r4.hasNext() == false) goto L35;
        String r5 = r4.next();
        zzhv r6 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r6.zzb(r5);
        Object r52 = r2.get(r5);
        if ((r52 instanceof Long) == true) goto L26;
        if ((r52 instanceof String) == true) goto L29;
        if ((r52 instanceof Double) == false) goto L23;
        r6.zzh(((Double) r52).doubleValue());
    L33:
        r3.zzk(r6);
        goto L23
    L29:
        r6.zzd((String) r52);
        goto L33
    L26:
        r6.zzf(((Long) r52).longValue());
        goto L33
    L35:
        if (r3.zzj() <= 0) goto L37;
        r9.add((com.google.android.gms.internal.measurement.zzhw) r3.zzbd());
    L37:
        r1 = r1 + 1;
        goto L17
    L38:
        r10.zzl(r9);
        return;
    L40:
        this.zzu.zzaW().zzb().zzb("Ignoring invalid (type) event param value", r11);
    }

    @TargetApi(30)
    public final zzoh zzf(String r15, com.google.android.gms.internal.measurement.zzic r16, com.google.android.gms.internal.measurement.zzhr r17, String r18) {
        zzaif.zza();
        zzic r1 = this.zzu;
        if (r1.zzc().zzp(r15, zzfy.zzaO) == false) goto L92;
        long r2 = r1.zzba().currentTimeMillis();
        HashSet r5 = new HashSet(Arrays.asList(r1.zzc().zzk(r15, zzfy.zzat).split(",")));
        zzpg r14 = this.zzg;
        zzou r4 = r14.zzf();
        String r6 = r4.zzg.zzh().zzc(r15);
        Uri.Builder r7 = new Uri.Builder();
        zzic r42 = r4.zzu;
        r7.scheme(r42.zzc().zzk(r15, zzfy.zzam));
        if (TextUtils.isEmpty(r6) == true) goto L7;
        String r8 = r42.zzc().zzk(r15, zzfy.zzan);
        StringBuilder r13 = new StringBuilder((String.valueOf(r6).length() + 1) + String.valueOf(r8).length());
        r13.append(r6);
        r13.append(".");
        r13.append(r8);
        r7.authority(r13.toString());
    L8:
        r7.path(r42.zzc().zzk(r15, zzfy.zzao));
        zzR(r7, "gmp_app_id", r16.zzac(), r5);
        r1.zzc().zzi();
        zzR(r7, "gmp_version", String.valueOf(161000), r5);
        String r43 = r16.zzV();
        zzal r62 = r1.zzc();
        zzfx r82 = zzfy.zzaR;
        if (r62.zzp(r15, r82) == true) goto L11;
    L13:
        zzR(r7, "app_instance_id", r43, r5);
        zzR(r7, "rdid", r16.zzP(), r5);
        zzR(r7, "bundle_id", r16.zzK(), r5);
        String r44 = r17.zzk();
        String r63 = zzjm.zza(r44);
        if (true == TextUtils.isEmpty(r63)) goto L16;
        r44 = r63;
    L16:
        zzR(r7, "app_event_name", r44, r5);
        zzR(r7, "app_version", String.valueOf(r16.zzai()), r5);
        String r45 = r16.zzD();
        if (r1.zzc().zzp(r15, r82) == true) goto L19;
    L25:
        zzR(r7, "os_version", r45, r5);
        zzR(r7, "timestamp", String.valueOf(r17.zzn()), r5);
        String r46 = "1";
        if (r16.zzS() == false) goto L28;
        zzR(r7, "lat", "1", r5);
    L28:
        zzR(r7, "privacy_sandbox_version", String.valueOf(r16.zzaG()), r5);
        zzR(r7, "trigger_uri_source", "1", r5);
        zzR(r7, "trigger_uri_timestamp", String.valueOf(r2), r5);
        zzR(r7, "request_uuid", r18, r5);
        List r142 = r17.zza();
        Bundle r64 = new Bundle();
        Iterator r143 = r142.iterator();
    L30:
        if (r143.hasNext() == false) goto L43;
        com.google.android.gms.internal.measurement.zzhw r83 = (com.google.android.gms.internal.measurement.zzhw) r143.next();
        String r9 = r83.zzb();
        if (r83.zzj() == true) goto L33;
        if (r83.zzh() == true) goto L36;
        if (r83.zzc() == true) goto L39;
        if (r83.zze() == false) goto L30;
        r64.putString(r9, String.valueOf(r83.zzf()));
        goto L30
    L39:
        r64.putString(r9, r83.zzd());
        goto L30
    L36:
        r64.putString(r9, String.valueOf(r83.zzi()));
        goto L30
    L33:
        r64.putString(r9, String.valueOf(r83.zzk()));
        goto L30
    L43:
        zzU(r7, r1.zzc().zzk(r15, zzfy.zzas).split("\\|"), r64, r5);
        List r144 = r16.zzk();
        Bundle r65 = new Bundle();
        Iterator r145 = r144.iterator();
    L45:
        if (r145.hasNext() == false) goto L58;
        com.google.android.gms.internal.measurement.zziu r92 = (com.google.android.gms.internal.measurement.zziu) r145.next();
        String r11 = r92.zzc();
        if (r92.zzk() == true) goto L48;
        if (r92.zzi() == true) goto L51;
        if (r92.zzd() == true) goto L54;
        if (r92.zzf() == false) goto L45;
        r65.putString(r11, String.valueOf(r92.zzh()));
        goto L45
    L54:
        r65.putString(r11, r92.zze());
        goto L45
    L51:
        r65.putString(r11, String.valueOf(r92.zzj()));
        goto L45
    L48:
        r65.putString(r11, String.valueOf(r92.zzl()));
        goto L45
    L58:
        zzU(r7, r1.zzc().zzk(r15, zzfy.zzar).split("\\|"), r65, r5);
        if (true == r16.zzaC()) goto L61;
        r46 = MVEL.VERSION_SUB;
    L61:
        zzR(r7, "dma", r46, r5);
        if (r16.zzaE().isEmpty() == true) goto L65;
        zzR(r7, "dma_cps", r16.zzaE(), r5);
    L65:
        if (r16.zzaK() == false) goto L91;
        com.google.android.gms.internal.measurement.zzha r146 = r16.zzaL();
        if (r146.zzb().isEmpty() == true) goto L70;
        zzR(r7, "dl_gclid", r146.zzb(), r5);
    L70:
        if (r146.zzd().isEmpty() == true) goto L73;
        zzR(r7, "dl_gbraid", r146.zzd(), r5);
    L73:
        if (r146.zzf().isEmpty() == true) goto L76;
        zzR(r7, "dl_gs", r146.zzf(), r5);
    L76:
        if (r146.zzi() <= 0) goto L79;
        zzR(r7, "dl_ss_ts", String.valueOf(r146.zzi()), r5);
    L79:
        if (r146.zzk().isEmpty() == true) goto L82;
        zzR(r7, "mr_gclid", r146.zzk(), r5);
    L82:
        if (r146.zzm().isEmpty() == true) goto L85;
        zzR(r7, "mr_gbraid", r146.zzm(), r5);
    L85:
        if (r146.zzo().isEmpty() == true) goto L88;
        zzR(r7, "mr_gs", r146.zzo(), r5);
    L88:
        if (r146.zzq() <= 0) goto L91;
        zzR(r7, "mr_click_ts", String.valueOf(r146.zzq()), r5);
    L91:
        return new zzoh(r7.build().toString(), r2, 1);
    L19:
        if (r14.zzh().zzr(r15) == false) goto L25;
        if (TextUtils.isEmpty(r45) == true) goto L25;
        int r147 = r45.indexOf(".");
        if (r147 == (-1)) goto L25;
        r45 = r45.substring(0, r147);
        goto L25
    L11:
        if (r14.zzh().zzu(r15) == false) goto L13;
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    L7:
        r7.authority(r42.zzc().zzk(r15, zzfy.zzan));
        goto L8
    L92:
        return null;
    }

    public final com.google.android.gms.internal.measurement.zzhs zzh(zzbc r6) {
        com.google.android.gms.internal.measurement.zzhr r0 = com.google.android.gms.internal.measurement.zzhs.zzp();
        r0.zzq(r6.zzf);
        r0.zzw(r6.zze);
        zzbf r2 = r6.zzg;
        zzbe r1 = new zzbe(r2);
    L4:
        if (r1.hasNext() == false) goto L6;
        String r3 = r1.zza();
        zzhv r4 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r4.zzb(r3);
        Object r32 = r2.zza(r3);
        Preconditions.checkNotNull(r32);
        zzd(r4, r32);
        r0.zzg(r4);
        goto L4
    L6:
        String r5 = r6.zzc;
        if (TextUtils.isEmpty(r5) == true) goto L12;
        if (r2.zza("_o") != null) goto L12;
        zzhv r12 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r12.zzb("_o");
        r12.zzd(r5);
        r0.zzf((com.google.android.gms.internal.measurement.zzhw) r12.zzbd());
    L12:
        return (com.google.android.gms.internal.measurement.zzhs) r0.zzbd();
    }

    public final String zzi(com.google.android.gms.internal.measurement.zzib r13) {
        if (r13 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        StringBuilder r0 = new StringBuilder();
        r0.append("\nbatch {\n");
        if (r13.zzf() == false) goto L9;
        zzW(r0, 0, "upload_subdomain", r13.zzh());
    L9:
        if (r13.zzd() == false) goto L11;
        zzW(r0, 0, "sgtm_join_id", r13.zze());
    L11:
        Iterator r132 = r13.zza().iterator();
    L13:
        if (r132.hasNext() == false) goto L245;
        com.google.android.gms.internal.measurement.zzid r1 = (com.google.android.gms.internal.measurement.zzid) r132.next();
        if (r1 == null) goto L13;
        zzQ(r0, 1);
        r0.append("bundle {\n");
        if (r1.zza() == false) goto L19;
        zzW(r0, 1, "protocol_version", Integer.valueOf(r1.zzb()));
    L19:
        zzair.zza();
        zzic r3 = this.zzu;
        if (r3.zzc().zzp(r1.zzA(), zzfy.zzaM) == true) goto L22;
    L24:
        zzW(r0, 1, "platform", r1.zzt());
        if (r1.zzC() == false) goto L28;
        zzW(r0, 1, "gmp_version", Long.valueOf(r1.zzD()));
    L28:
        if (r1.zzE() == false) goto L31;
        zzW(r0, 1, "uploading_gmp_version", Long.valueOf(r1.zzF()));
    L31:
        if (r1.zzac() == false) goto L34;
        zzW(r0, 1, "dynamite_version", Long.valueOf(r1.zzad()));
    L34:
        if (r1.zzW() == false) goto L36;
        zzW(r0, 1, "config_version", Long.valueOf(r1.zzX()));
    L36:
        zzW(r0, 1, "gmp_app_id", r1.zzP());
        zzW(r0, 1, "app_id", r1.zzA());
        zzW(r0, 1, "app_version", r1.zzB());
        if (r1.zzU() == false) goto L39;
        zzW(r0, 1, "app_version_major", Integer.valueOf(r1.zzV()));
    L39:
        zzW(r0, 1, "firebase_instance_id", r1.zzT());
        if (r1.zzK() == false) goto L42;
        zzW(r0, 1, "dev_cert_hash", Long.valueOf(r1.zzL()));
    L42:
        zzW(r0, 1, "app_store", r1.zzz());
        if (r1.zzj() == false) goto L46;
        zzW(r0, 1, "upload_timestamp_millis", Long.valueOf(r1.zzk()));
    L46:
        if (r1.zzl() == false) goto L49;
        zzW(r0, 1, "start_timestamp_millis", Long.valueOf(r1.zzm()));
    L49:
        if (r1.zzn() == false) goto L52;
        zzW(r0, 1, "end_timestamp_millis", Long.valueOf(r1.zzo()));
    L52:
        if (r1.zzp() == false) goto L55;
        zzW(r0, 1, "previous_bundle_start_timestamp_millis", Long.valueOf(r1.zzq()));
    L55:
        if (r1.zzr() == false) goto L57;
        zzW(r0, 1, "previous_bundle_end_timestamp_millis", Long.valueOf(r1.zzs()));
    L57:
        zzW(r0, 1, "app_instance_id", r1.zzJ());
        zzW(r0, 1, "resettable_device_id", r1.zzG());
        zzW(r0, 1, "ds_id", r1.zzZ());
        if (r1.zzH() == false) goto L60;
        zzW(r0, 1, "limited_ad_tracking", Boolean.valueOf(r1.zzI()));
    L60:
        zzW(r0, 1, "os_version", r1.zzu());
        zzW(r0, 1, "device_model", r1.zzv());
        zzW(r0, 1, "user_default_language", r1.zzw());
        if (r1.zzx() == false) goto L64;
        zzW(r0, 1, "time_zone_offset_minutes", Integer.valueOf(r1.zzy()));
    L64:
        if (r1.zzM() == false) goto L67;
        zzW(r0, 1, "bundle_sequential_index", Integer.valueOf(r1.zzN()));
    L67:
        if (r1.zzau() == false) goto L70;
        zzW(r0, 1, "delivery_index", Integer.valueOf(r1.zzav()));
    L70:
        if (r1.zzQ() == false) goto L72;
        zzW(r0, 1, "service_upload", Boolean.valueOf(r1.zzR()));
    L72:
        zzW(r0, 1, "health_monitor", r1.zzO());
        if (r1.zzaa() == false) goto L76;
        zzW(r0, 1, "retry_counter", Integer.valueOf(r1.zzab()));
    L76:
        if (r1.zzae() == false) goto L79;
        zzW(r0, 1, "consent_signals", r1.zzaf());
    L79:
        if (r1.zzan() == false) goto L82;
        zzW(r0, 1, "is_dma_region", Boolean.valueOf(r1.zzao()));
    L82:
        if (r1.zzap() == false) goto L85;
        zzW(r0, 1, "core_platform_services", r1.zzaq());
    L85:
        if (r1.zzal() == false) goto L88;
        zzW(r0, 1, "consent_diagnostics", r1.zzam());
    L88:
        if (r1.zzai() == false) goto L90;
        zzW(r0, 1, "target_os_version", Long.valueOf(r1.zzaj()));
    L90:
        zzaif.zza();
        if (r3.zzc().zzp(r1.zzA(), zzfy.zzaO) == false) goto L98;
        zzW(r0, 1, "ad_services_version", Integer.valueOf(r1.zzar()));
        if (r1.zzas() == false) goto L98;
        com.google.android.gms.internal.measurement.zzhe r4 = r1.zzat();
        if (r4 == null) goto L98;
        zzQ(r0, 2);
        r0.append("attribution_eligibility_status {\n");
        zzW(r0, 2, "eligible", Boolean.valueOf(r4.zza()));
        zzW(r0, 2, "no_access_adservices_attribution_permission", Boolean.valueOf(r4.zzb()));
        zzW(r0, 2, "pre_r", Boolean.valueOf(r4.zzc()));
        zzW(r0, 2, "r_extensions_too_old", Boolean.valueOf(r4.zzd()));
        zzW(r0, 2, "adservices_extension_too_old", Boolean.valueOf(r4.zze()));
        zzW(r0, 2, "ad_storage_not_allowed", Boolean.valueOf(r4.zzf()));
        zzW(r0, 2, "measurement_manager_disabled", Boolean.valueOf(r4.zzh()));
        zzQ(r0, 2);
        r0.append("}\n");
    L98:
        if (r1.zzaw() == false) goto L128;
        com.google.android.gms.internal.measurement.zzha r42 = r1.zzax();
        zzQ(r0, 2);
        r0.append("ad_campaign_info {\n");
        if (r42.zza() == false) goto L103;
        zzW(r0, 2, "deep_link_gclid", r42.zzb());
    L103:
        if (r42.zzc() == false) goto L106;
        zzW(r0, 2, "deep_link_gbraid", r42.zzd());
    L106:
        if (r42.zze() == false) goto L109;
        zzW(r0, 2, "deep_link_gad_source", r42.zzf());
    L109:
        if (r42.zzr() == false) goto L112;
        zzW(r0, 2, "deep_link_url", r42.zzs());
    L112:
        if (r42.zzh() == false) goto L115;
        zzW(r0, 2, "deep_link_session_millis", Long.valueOf(r42.zzi()));
    L115:
        if (r42.zzj() == false) goto L118;
        zzW(r0, 2, "market_referrer_gclid", r42.zzk());
    L118:
        if (r42.zzl() == false) goto L121;
        zzW(r0, 2, "market_referrer_gbraid", r42.zzm());
    L121:
        if (r42.zzn() == false) goto L124;
        zzW(r0, 2, "market_referrer_gad_source", r42.zzo());
    L124:
        if (r42.zzp() == false) goto L126;
        zzW(r0, 2, "market_referrer_click_millis", Long.valueOf(r42.zzq()));
    L126:
        zzQ(r0, 2);
        r0.append("}\n");
    L128:
        if (r1.zzaA() == false) goto L131;
        zzW(r0, 1, "batching_timestamp_millis", Long.valueOf(r1.zzaB()));
    L131:
        if (r1.zzay() == false) goto L157;
        com.google.android.gms.internal.measurement.zzis r43 = r1.zzaz();
        zzQ(r0, 2);
        r0.append("sgtm_diagnostics {\n");
        int r9 = r43.zzf();
        if (r9 == 1) goto L141;
        if (r9 == 2) goto L140;
        if (r9 == 3) goto L139;
        if (r9 == 4) goto L138;
        String r92 = "SDK_SERVICE_UPLOAD";
    L142:
        zzW(r0, 2, "upload_type", r92);
        zzW(r0, 2, "client_upload_eligibility", r43.zza().name());
        int r44 = r43.zzh();
        if (r44 == 1) goto L154;
        if (r44 == 2) goto L153;
        if (r44 == 3) goto L152;
        if (r44 != 4) goto L148;
        String r45 = "MISSING_SGTM_SETTINGS";
    L155:
        zzW(r0, 2, "service_upload_eligibility", r45);
        zzQ(r0, 2);
        r0.append("}\n");
        goto L157
    L148:
        if (r44 == 5) goto L150;
        r45 = "NON_PLAY_MISSING_SGTM_SERVER_URL";
        goto L155
    L150:
        r45 = "MISSING_SGTM_PROXY_INFO";
        goto L155
    L152:
        r45 = "NOT_IN_ROLLOUT";
        goto L155
    L153:
        r45 = "SERVICE_UPLOAD_ELIGIBLE";
        goto L155
    L154:
        r45 = "SERVICE_UPLOAD_ELIGIBILITY_UNKNOWN";
        goto L155
    L138:
        r92 = "PACKAGE_SERVICE_UPLOAD";
        goto L142
    L139:
        r92 = "SDK_CLIENT_UPLOAD";
        goto L142
    L140:
        r92 = "GA_UPLOAD";
        goto L142
    L141:
        r92 = "UPLOAD_TYPE_UNKNOWN";
    L157:
        if (r1.zzaC() == false) goto L179;
        com.google.android.gms.internal.measurement.zzho r46 = r1.zzaD();
        zzQ(r0, 2);
        r0.append("consent_info_extra {\n");
        Iterator r47 = r46.zza().iterator();
    L160:
        if (r47.hasNext() == false) goto L178;
        com.google.android.gms.internal.measurement.zzhl r93 = (com.google.android.gms.internal.measurement.zzhl) r47.next();
        zzQ(r0, 3);
        r0.append("limited_data_modes {\n");
        int r10 = r93.zzc();
        if (r10 == 1) goto L170;
        if (r10 == 2) goto L169;
        if (r10 == 3) goto L168;
        if (r10 == 4) goto L167;
        String r102 = "AD_PERSONALIZATION";
    L171:
        zzW(r0, 3, "type", r102);
        int r94 = r93.zzd();
        if (r94 == 1) goto L176;
        if (r94 == 2) goto L175;
        String r95 = "NO_DATA_MODE";
    L177:
        zzW(r0, 3, "mode", r95);
        zzQ(r0, 3);
        r0.append("}\n");
        goto L160
    L175:
        r95 = "LIMITED_MODE";
        goto L177
    L176:
        r95 = "NOT_LIMITED";
        goto L177
    L167:
        r102 = "AD_USER_DATA";
        goto L171
    L168:
        r102 = "ANALYTICS_STORAGE";
        goto L171
    L169:
        r102 = "AD_STORAGE";
        goto L171
    L170:
        r102 = "CONSENT_TYPE_UNSPECIFIED";
        goto L171
    L178:
        zzQ(r0, 2);
        r0.append("}\n");
    L179:
        List r48 = r1.zzf();
        if (r48 == null) goto L200;
        Iterator r49 = r48.iterator();
    L184:
        if (r49.hasNext() == false) goto L200;
        com.google.android.gms.internal.measurement.zziu r96 = (com.google.android.gms.internal.measurement.zziu) r49.next();
        if (r96 == null) goto L184;
        zzQ(r0, 2);
        r0.append("user_property {\n");
        if (r96.zza() == false) goto L190;
        Long r103 = Long.valueOf(r96.zzb());
    L191:
        zzW(r0, 2, "set_timestamp_millis", r103);
        zzW(r0, 2, "name", r3.zzl().zzc(r96.zzc()));
        zzW(r0, 2, "string_value", r96.zze());
        if (r96.zzf() == false) goto L194;
        Long r104 = Long.valueOf(r96.zzh());
    L195:
        zzW(r0, 2, "int_value", r104);
        if (r96.zzk() == false) goto L198;
        Double r97 = Double.valueOf(r96.zzl());
    L199:
        zzW(r0, 2, "double_value", r97);
        zzQ(r0, 2);
        r0.append("}\n");
        goto L184
    L198:
        r97 = null;
        goto L199
    L194:
        r104 = null;
        goto L195
    L190:
        r103 = null;
    L200:
        List r410 = r1.zzS();
        if (r410 == null) goto L218;
        Iterator r411 = r410.iterator();
    L205:
        if (r411.hasNext() == false) goto L218;
        com.google.android.gms.internal.measurement.zzhg r98 = (com.google.android.gms.internal.measurement.zzhg) r411.next();
        if (r98 == null) goto L205;
        zzQ(r0, 2);
        r0.append("audience_membership {\n");
        if (r98.zza() == false) goto L212;
        zzW(r0, 2, "audience_id", Integer.valueOf(r98.zzb()));
    L212:
        if (r98.zzf() == false) goto L214;
        zzW(r0, 2, "new_audience", Boolean.valueOf(r98.zzh()));
    L214:
        zzV(r0, 2, "current_data", r98.zzc());
        if (r98.zzd() == false) goto L217;
        zzV(r0, 2, "previous_data", r98.zze());
    L217:
        zzQ(r0, 2);
        r0.append("}\n");
    L218:
        List r12 = r1.zzc();
        if (r12 == null) goto L244;
        Iterator r14 = r12.iterator();
    L223:
        if (r14.hasNext() == false) goto L244;
        com.google.android.gms.internal.measurement.zzhs r412 = (com.google.android.gms.internal.measurement.zzhs) r14.next();
        if (r412 == null) goto L223;
        zzQ(r0, 2);
        r0.append("event {\n");
        zzW(r0, 2, "name", r3.zzl().zza(r412.zzd()));
        if (r412.zze() == false) goto L230;
        zzW(r0, 2, "timestamp_millis", Long.valueOf(r412.zzf()));
    L230:
        if (r3.zzc().zzp(null, zzfy.zzbe) == false) goto L235;
        if (r412.zzl() == false) goto L235;
        zzW(r0, 2, "corrected_timestamp_millis", Long.valueOf(r412.zzm()));
    L235:
        if (r412.zzh() == false) goto L238;
        zzW(r0, 2, "previous_timestamp_millis", Long.valueOf(r412.zzi()));
    L238:
        if (r412.zzj() == false) goto L241;
        zzW(r0, 2, NotificationBadge.NewHtcHomeBadger.COUNT, Integer.valueOf(r412.zzk()));
    L241:
        if (r412.zzb() == 0) goto L243;
        zzO(r0, 2, r412.zza());
    L243:
        zzQ(r0, 2);
        r0.append("}\n");
    L244:
        zzQ(r0, 1);
        r0.append("}\n");
        goto L13
    L22:
        if (r1.zzag() == false) goto L24;
        zzW(r0, 1, "session_stitching_token", r1.zzah());
        goto L24
    L245:
        r0.append("} // End-of-batch\n");
        return r0.toString();
    }

    public final String zzj(com.google.android.gms.internal.measurement.zzff r6) {
        if (r6 != null) goto L5;
        return "null";
    L5:
        StringBuilder r0 = new StringBuilder();
        r0.append("\nevent_filter {\n");
        if (r6.zza() == false) goto L8;
        zzW(r0, 0, "filter_id", Integer.valueOf(r6.zzb()));
    L8:
        zzW(r0, 0, "event_name", this.zzu.zzl().zza(r6.zzc()));
        String r1 = zzS(r6.zzj(), r6.zzk(), r6.zzm());
        if (r1.isEmpty() == true) goto L12;
        zzW(r0, 0, "filter_type", r1);
    L12:
        if (r6.zzh() == false) goto L15;
        zzX(r0, 1, "event_count_filter", r6.zzi());
    L15:
        if (r6.zze() <= 0) goto L20;
        r0.append("  filters {\n");
        Iterator r62 = r6.zzd().iterator();
    L18:
        if (r62.hasNext() == false) goto L20;
        zzP(r0, 2, (com.google.android.gms.internal.measurement.zzfh) r62.next());
    L20:
        zzQ(r0, 1);
        r0.append("}\n}\n");
        return r0.toString();
    }

    public final String zzk(com.google.android.gms.internal.measurement.zzfn r6) {
        if (r6 != null) goto L5;
        return "null";
    L5:
        StringBuilder r0 = new StringBuilder();
        r0.append("\nproperty_filter {\n");
        if (r6.zza() == false) goto L8;
        zzW(r0, 0, "filter_id", Integer.valueOf(r6.zzb()));
    L8:
        zzW(r0, 0, "property_name", this.zzu.zzl().zzc(r6.zzc()));
        String r1 = zzS(r6.zze(), r6.zzf(), r6.zzi());
        if (r1.isEmpty() == true) goto L11;
        zzW(r0, 0, "filter_type", r1);
    L11:
        zzP(r0, 1, r6.zzd());
        r0.append("}\n");
        return r0.toString();
    }

    public final Parcelable zzl(byte[] r5, Parcelable.Creator r6) {
        Parcelable r0 = null;
        if (r5 != null) goto L5;
        return null;
    L5:
        Parcel r1 = Parcel.obtain();
        r1.unmarshall(r5, 0, r5.length);     // Catch: Throwable -> L8 SafeParcelReader.ParseException -> L10
        r1.setDataPosition(0);     // Catch: Throwable -> L8 SafeParcelReader.ParseException -> L10
        r0 = (Parcelable) r6.createFromParcel(r1);     // Catch: Throwable -> L8 SafeParcelReader.ParseException -> L10
    L11:
        r1.recycle();
        return r0;
    L10:
        this.zzu.zzaW().zzb().zza("Failed to load parcelable from buffer");     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        r1.recycle();
        throw th;
    }

    public final List zzq(List r8, List r9) {
        ArrayList r0 = new ArrayList(r8);
        Iterator r82 = r9.iterator();
    L4:
        if (r82.hasNext() == false) goto L12;
        Integer r92 = (Integer) r82.next();
        if (r92.intValue() < 0) goto L7;
        int r1 = r92.intValue() / 64;
        if (r1 >= r0.size()) goto L10;
        r0.set(r1, Long.valueOf(((Long) r0.get(r1)).longValue() & (~(1 << (r92.intValue() % 64)))));
        goto L4
    L10:
        this.zzu.zzaW().zze().zzc("Ignoring bit index greater than bitSet size", r92, Integer.valueOf(r0.size()));
        goto L4
    L7:
        this.zzu.zzaW().zze().zzb("Ignoring negative bit index to be cleared", r92);
        goto L4
    L12:
        int r7 = r0.size();
        int r83 = r0.size() - 1;
    L13:
        int r6 = r83;
        int r84 = r7;
        r7 = r6;
        if (r7 < 0) goto L20;
        if (((Long) r0.get(r7)).longValue() != 0) goto L20;
        r83 = r7 - 1;
    L20:
        return r0.subList(0, r84);
    }

    public final boolean zzs(long r4, long r6) {
        if (r4 != 0) goto L5;
        return true;
    L5:
        if (r6 > 0) goto L7;
        return true;
    L7:
        if (Math.abs(this.zzu.zzba().currentTimeMillis() - r4) > r6) goto L14;
        return false;
    L14:
        return true;
    }

    public final long zzt(byte[] r2) {
        Preconditions.checkNotNull(r2);
        zzic r1 = this.zzu;
        r1.zzk().zzg();
        MessageDigest r0 = zzpp.zzQ();
        if (r0 != null) goto L7;
        r1.zzaW().zzb().zza("Failed to get MD5");
        return 0;
    L7:
        return zzpp.zzR(r0.digest(r2));
    }

    public final long zzu(String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L7;
        return 0;
    L7:
        return zzt(r2.getBytes(StandardCharsets.UTF_8));
    }

    public final byte[] zzv(byte[] r3) {
        ByteArrayOutputStream r0 = new ByteArrayOutputStream();     // Catch: IOException -> L4
        GZIPOutputStream r1 = new GZIPOutputStream(r0);     // Catch: IOException -> L4
        r1.write(r3);     // Catch: IOException -> L4
        r1.close();     // Catch: IOException -> L4
        r0.close();     // Catch: IOException -> L4
        return r0.toByteArray();
    L4:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Failed to gzip content", e);
        throw e;
    }

    public final Map zzz(Bundle r11, boolean r12) {
        HashMap r0 = new HashMap();
        Iterator<String> r1 = r11.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L35;
        String r2 = r1.next();
        Object r3 = r11.get(r2);
        boolean r4 = r3 instanceof Parcelable[];
        if (r4 == true) goto L14;
        if ((r3 instanceof ArrayList) == true) goto L14;
        if ((r3 instanceof Bundle) == true) goto L14;
        if (r3 == null) goto L4;
        r0.put(r2, r3);
    L14:
        if (r12 == false) goto L4;
        ArrayList r5 = new ArrayList();
        if (r4 == false) goto L24;
        Parcelable[] r32 = (Parcelable[]) r3;
        int r42 = r32.length;
        int r7 = 0;
    L18:
        if (r7 >= r42) goto L34;
        Parcelable r8 = r32[r7];
        if ((r8 instanceof Bundle) == false) goto L22;
        r5.add(zzz((Bundle) r8, false));
    L22:
        r7 = r7 + 1;
    L34:
        r0.put(r2, r5);
        goto L4
    L24:
        if ((r3 instanceof ArrayList) == false) goto L32;
        ArrayList r33 = (ArrayList) r3;
        int r43 = r33.size();
        int r72 = 0;
    L26:
        if (r72 >= r43) goto L34;
        Object r82 = r33.get(r72);
        if ((r82 instanceof Bundle) == false) goto L30;
        r5.add(zzz((Bundle) r82, false));
    L30:
        r72 = r72 + 1;
        goto L26
    L32:
        if ((r3 instanceof Bundle) == false) goto L34;
        r5.add(zzz((Bundle) r3, false));
        goto L34
    L35:
        return r0;
    }
}
