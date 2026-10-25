package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.chaquo.python.internal.Common;
import com.google.android.gms.measurement.internal.zzjo;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/* loaded from: classes.dex */
public abstract class zzc {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final ImmutableSet zzb = null;
    private static final ImmutableList zzc = null;
    private static final ImmutableList zzd = null;
    private static final ImmutableList zze = null;
    private static final ImmutableList zzf = null;
    private static final ImmutableList zzg = null;

    static {
        zzb = ImmutableSet.of("_in", "_xa", "_xu", "_aq", "_aa", "_ai", new String[]{"_ac", "campaign_details", "_ug", "_iapx", "_exp_set", "_exp_clear", "_exp_activate", "_exp_timeout", "_exp_expire"});
        zzc = ImmutableList.of("_e", "_f", "_iap", "_s", "_au", "_ui", "_cd");
        zzd = ImmutableList.of("auto", Common.ASSET_APP, "am");
        zze = ImmutableList.of("_r", "_dbg");
        zzf = new ImmutableList.Builder().add(zzjo.zza).add(zzjo.zzb).build();
        zzg = ImmutableList.of("^_ltv_[A-Z]{3}$", "^_cc[1-5]{1}$");
    }

    public static boolean zza(String r1) {
        if (zzd.contains(r1) == true) goto L6;
        return true;
    L6:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean zzb(String r4, Bundle r5) {
        if (zzc.contains(r4) == false) goto L5;
        return false;
    L5:
        if (r5 == null) goto L11;
        ImmutableList r42 = zze;
        int r1 = r42.size();
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L16;
        boolean r3 = r5.containsKey((String) r42.get(r2));
        r2 = r2 + 1;
        if (r3 == false) goto L7;
        return false;
    L16:
        return true;
    L11:
        return true;
    }

    public static boolean zzc(String r1) {
        if (zzb.contains(r1) == true) goto L6;
        return true;
    L6:
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean zzd(String r5, String r6) {
        if ("_ce1".equals(r6) == true) goto L26;
        if ("_ce2".equals(r6) == true) goto L26;
        if ("_ln".equals(r6) == false) goto L17;
        if (r5.equals("fcm") == false) goto L12;
    L15:
        return true;
    L12:
        if (r5.equals("fiam") == true) goto L15;
        return false;
    L17:
        if (zzf.contains(r6) == false) goto L19;
        return false;
    L19:
        ImmutableList r52 = zzg;
        int r0 = r52.size();
        int r1 = 0;
    L20:
        if (r1 >= r0) goto L24;
        boolean r4 = r6.matches((String) r52.get(r1));
        r1 = r1 + 1;
        if (r4 == false) goto L20;
        return false;
    L24:
        return true;
    L26:
        if (r5.equals("fcm") == false) goto L28;
    L31:
        return true;
    L28:
        if (r5.equals("frc") == true) goto L31;
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean zze(String r5, String r6, Bundle r7) {
        if ("_cmp".equals(r6) == true) goto L6;
        return true;
    L6:
        if (zza(r5) == true) goto L8;
        return false;
    L8:
        if (r7 != null) goto L10;
        return false;
    L10:
        ImmutableList r62 = zze;
        int r2 = r62.size();
        int r3 = 0;
    L11:
        if (r3 >= r2) goto L15;
        boolean r4 = r7.containsKey((String) r62.get(r3));
        r3 = r3 + 1;
        if (r4 == false) goto L11;
        return false;
    L15:
        int r63 = r5.hashCode();
        if (r63 == 101200) goto L31;
        if (r63 == 101230) goto L27;
        if (r63 == 3142703) goto L23;
    L34:
        return false;
    L23:
        if (r5.equals("fiam") == false) goto L34;
        r7.putString("_cis", "fiam_integration");
        return true;
    L27:
        if (r5.equals("fdl") == false) goto L34;
        r7.putString("_cis", "fdl_integration");
        return true;
    L31:
        if (r5.equals("fcm") == false) goto L34;
        r7.putString("_cis", "fcm_integration");
        return true;
    }
}
