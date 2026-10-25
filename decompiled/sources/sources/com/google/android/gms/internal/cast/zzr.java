package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.time.DurationKt;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class zzr {
    private static final Logger zza = null;
    private static final String zzb = null;
    private static zzr zzc;
    private final zzj zzd;
    private final SharedPreferences zze;
    private final String zzf;
    private final Runnable zzg;
    private final Handler zzh;
    private final Set zzi;
    private final Set zzj;
    private final Clock zzk;
    private long zzl;

    static {
        zza = new Logger("FeatureUsageAnalytics");
        zzb = "22.3.1";
    }

    private zzr(SharedPreferences r1, zzj r2, String r3) {
        this.zze = r1;
        this.zzd = r2;
        this.zzf = r3;
        this.zzk = DefaultClock.getInstance();
        this.zzi = new HashSet();
        this.zzj = new HashSet();
        this.zzh = new zzfk(Looper.getMainLooper());
        this.zzg = new zzq(this);
    }

    public static synchronized zzr zza(SharedPreferences r2, zzj r3, String r4) {
        monitor-enter(zzr.class);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zzc != null) goto L9;
        zzc = new zzr(r2, r3, r4);     // Catch: Throwable -> L7
    L9:
        zzr r22 = zzc;     // Catch: Throwable -> L7
        monitor-exit(zzr.class);
        return r22;
    }

    public static void zzb(zzpm r5) {
        if (zzj.zza == false) goto L9;
        zzr r0 = zzc;
        if (r0 == null) goto L10;
        String r1 = Integer.toString(r5.zza());
        r0.zze.edit().putLong(r0.zzi(r1), r0.zzh()).apply();
        r0.zzi.add(r5);
        r0.zzg();
        return;
    L10:
        return;
    }

    public static String zzd(String r0, String r1) {
        return String.format("%s%s", new Object[]{r0, r1});
    }

    private final void zzf(Set r2) {
        if (r2.isEmpty() == false) goto L5;
        return;
    L5:
        SharedPreferences.Editor r1 = this.zze.edit();
        Iterator r22 = r2.iterator();
    L7:
        if (r22.hasNext() == false) goto L9;
        r1.remove((String) r22.next());
        goto L7
    L9:
        r1.apply();
    }

    @RequiresNonNull({"handler", "reportFeatureUsageRunnable"})
    private final void zzg() {
        this.zzh.post(this.zzg);
    }

    private final long zzh() {
        return ((Clock) Preconditions.checkNotNull(this.zzk)).currentTimeMillis();
    }

    @RequiresNonNull({"sharedPreferences"})
    private final String zzi(String r2) {
        SharedPreferences r1 = this.zze;
        String r0 = zzd("feature_usage_timestamp_reported_feature_", r2);
        if (r1.contains(r0) == false) goto L6;
        return r0;
    L6:
        return zzd("feature_usage_timestamp_detected_feature_", r2);
    }

    private static zzpm zzj(String r0) {
        switch(Integer.parseInt(r0)) {
            case 0: goto L121;
            case 1: goto L119;
            case 2: goto L117;
            case 3: goto L115;
            case 4: goto L113;
            case 5: goto L111;
            case 6: goto L109;
            case 7: goto L107;
            case 8: goto L105;
            case 9: goto L103;
            case 10: goto L101;
            case 11: goto L99;
            case 12: goto L97;
            case 13: goto L95;
            case 14: goto L93;
            case 15: goto L91;
            case 16: goto L89;
            case 17: goto L87;
            case 18: goto L85;
            case 19: goto L83;
            case 20: goto L81;
            case 21: goto L79;
            case 22: goto L77;
            case 23: goto L75;
            case 24: goto L73;
            case 25: goto L71;
            case 26: goto L69;
            case 27: goto L67;
            case 28: goto L65;
            case 29: goto L63;
            case 30: goto L61;
            case 31: goto L59;
            case 32: goto L57;
            case 33: goto L55;
            case 34: goto L53;
            case 35: goto L51;
            case 36: goto L49;
            case 37: goto L47;
            case 38: goto L45;
            case 39: goto L43;
            case 40: goto L41;
            case 41: goto L39;
            case 42: goto L37;
            case 43: goto L35;
            case 44: goto L33;
            case 45: goto L31;
            case 46: goto L29;
            case 47: goto L27;
            case 48: goto L25;
            case 49: goto L23;
            case 50: goto L21;
            case 51: goto L19;
            case 52: goto L17;
            case 53: goto L15;
            case 54: goto L13;
            case 55: goto L11;
            case 56: goto L9;
            case 57: goto L7;
            default: goto L4;
        };     // Catch: NumberFormatException -> L122
    L4:
        return null;
    L7:
        return zzpm.zzaf;
    L9:
        return zzpm.zzae;
    L11:
        return zzpm.zzad;
    L13:
        return zzpm.zzac;
    L15:
        return zzpm.zzab;
    L17:
        return zzpm.zzaa;
    L19:
        return zzpm.zzZ;
    L21:
        return zzpm.zzY;
    L23:
        return zzpm.zzX;
    L25:
        return zzpm.zzW;
    L27:
        return zzpm.zzV;
    L29:
        return zzpm.zzU;
    L31:
        return zzpm.zzT;
    L33:
        return zzpm.zzS;
    L35:
        return zzpm.zzR;
    L37:
        return zzpm.zzQ;
    L39:
        return zzpm.zzP;
    L41:
        return zzpm.zzO;
    L43:
        return zzpm.zzN;
    L45:
        return zzpm.zzM;
    L47:
        return zzpm.zzL;
    L49:
        return zzpm.zzK;
    L51:
        return zzpm.zzJ;
    L53:
        return zzpm.zzI;
    L55:
        return zzpm.zzH;
    L57:
        return zzpm.zzG;
    L59:
        return zzpm.zzF;
    L61:
        return zzpm.zzE;
    L63:
        return zzpm.zzD;
    L65:
        return zzpm.zzC;
    L67:
        return zzpm.zzB;
    L69:
        return zzpm.zzA;
    L71:
        return zzpm.zzz;
    L73:
        return zzpm.zzy;
    L75:
        return zzpm.zzx;
    L77:
        return zzpm.zzw;
    L79:
        return zzpm.zzv;
    L81:
        return zzpm.zzu;
    L83:
        return zzpm.zzt;
    L85:
        return zzpm.zzs;
    L87:
        return zzpm.zzr;
    L89:
        return zzpm.zzq;
    L91:
        return zzpm.zzp;
    L93:
        return zzpm.zzo;
    L95:
        return zzpm.zzn;
    L97:
        return zzpm.zzm;
    L99:
        return zzpm.zzl;
    L101:
        return zzpm.zzk;
    L103:
        return zzpm.zzj;
    L105:
        return zzpm.zzi;
    L107:
        return zzpm.zzh;
    L109:
        return zzpm.zzg;
    L111:
        return zzpm.zzf;
    L113:
        return zzpm.zze;
    L115:
        return zzpm.zzd;
    L117:
        return zzpm.zzc;
    L119:
        return zzpm.zzb;
    L121:
        return zzpm.zza;
    L123:
        return zzpm.zza;
    }

    public final void zzc() {
        SharedPreferences r0 = this.zze;
        Set r1 = this.zzi;
        String r4 = r0.getString("feature_usage_sdk_version", null);
        String r3 = r0.getString("feature_usage_package_name", null);
        r1.clear();
        Set r6 = this.zzj;
        r6.clear();
        this.zzl = 0;
        String r9 = zzb;
        if (r9.equals(r4) == true) goto L5;
    L29:
        HashSet r12 = new HashSet();
        Iterator<String> r32 = r0.getAll().keySet().iterator();
    L31:
        if (r32.hasNext() == false) goto L35;
        String r42 = r32.next();
        if (r42.startsWith("feature_usage_timestamp_") == false) goto L31;
        r12.add(r42);
        goto L31
    L35:
        r12.add("feature_usage_last_report_time");
        zzf(r12);
        r0.edit().putString("feature_usage_sdk_version", r9).putString("feature_usage_package_name", this.zzf).apply();
        return;
    L5:
        if (this.zzf.equals(r3) == false) goto L29;
        this.zzl = r0.getLong("feature_usage_last_report_time", 0);
        long r2 = zzh();
        HashSet r43 = new HashSet();
        Iterator<String> r5 = r0.getAll().keySet().iterator();
    L9:
        if (r5.hasNext() == false) goto L27;
        String r92 = r5.next();
        if (r92.startsWith("feature_usage_timestamp_") == false) goto L9;
        long r11 = r0.getLong(r92, 0);
        if (r11 == 0) goto L18;
        if ((r2 - r11) <= 1209600000) goto L18;
        r43.add(r92);
    L18:
        if (r92.startsWith("feature_usage_timestamp_reported_feature_") == true) goto L19;
        if (r92.startsWith("feature_usage_timestamp_detected_feature_") == false) goto L9;
        zzpm r93 = zzj(r92.substring(41));
        if (r93 == null) goto L9;
        r1.add(r93);
        goto L9
    L19:
        zzpm r94 = zzj(r92.substring(41));
        if (r94 == null) goto L9;
        r6.add(r94);
        r1.add(r94);
        goto L9
    L27:
        zzf(r43);
        Preconditions.checkNotNull(this.zzh);
        Preconditions.checkNotNull(this.zzg);
        zzg();
    }

    public final /* synthetic */ void zze() {
        Set r0 = this.zzi;
        if (r0.isEmpty() == true) goto L34;
        Set r1 = this.zzj;
        if (true == r1.equals(r0)) goto L8;
        long r2 = DurationKt.MILLIS_IN_DAY;
    L9:
        long r4 = zzh();
        long r6 = this.zzl;
        if (r6 != 0) goto L12;
    L15:
        zza.d("Upload the feature usage report.", new Object[0]);
        zzqb r22 = zzqc.zza();
        r22.zzb(zzb);
        r22.zza(this.zzf);
        zzqc r23 = (zzqc) r22.zzu();
        ArrayList r3 = new ArrayList();
        r3.addAll(r0);
        zzpv r62 = zzpw.zza();
        r62.zzb(r3);
        r62.zza(r23);
        zzpw r24 = (zzpw) r62.zzu();
        zzqq r32 = zzqr.zzc();
        r32.zzm(r24);
        zzqr r25 = (zzqr) r32.zzu();
        this.zzd.zzd(r25, 243);
        SharedPreferences r26 = this.zze;
        SharedPreferences.Editor r33 = r26.edit();
        if (r1.equals(r0) == true) goto L25;
        r1.clear();
        r1.addAll(r0);
        Iterator r02 = r1.iterator();
    L19:
        if (r02.hasNext() == false) goto L25;
        String r12 = Integer.toString(((zzpm) r02.next()).zza());
        String r63 = zzi(r12);
        String r13 = zzd("feature_usage_timestamp_reported_feature_", r12);
        if (TextUtils.equals(r63, r13) == true) goto L19;
        long r10 = r26.getLong(r63, 0);
        r33.remove(r63);
        if (r10 == 0) goto L19;
        r33.putLong(r13, r10);
    L25:
        this.zzl = r4;
        r33.putLong("feature_usage_last_report_time", r4).apply();
        return;
    L12:
        if ((r4 - r6) >= r2) goto L15;
        return;
    L8:
        r2 = 172800000;
        goto L9
    }
}
