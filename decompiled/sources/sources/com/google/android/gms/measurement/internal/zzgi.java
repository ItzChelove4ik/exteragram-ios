package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzaif;
import com.google.android.gms.internal.measurement.zzaja;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes5.dex */
public final class zzgi extends zzg {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private final long zzh;
    private List zzi;
    private String zzj;
    private final String zzk;
    private int zzl;
    private String zzm;
    private String zzn;
    private long zzo;
    private String zzp;

    public zzgi(zzic r3, long r4, long r6, String r8) {
        super(r3);
        this.zzo = 0;
        this.zzp = null;
        this.zzg = r4;
        this.zzh = r6;
        this.zzk = r8;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zze() {
        return true;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    @EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    public final void zzf() {
        zzic r0 = this.zzu;
        r0.zzaW().zzk().zzc("sdkVersion bundled with app, dynamiteVersion", Long.valueOf(this.zzh), Long.valueOf(this.zzg));
        String r1 = r0.zzaZ().getPackageName();
        PackageManager r2 = r0.zzaZ().getPackageManager();
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        int r5 = Integer.MIN_VALUE;
        String r6 = "Unknown";
        String r7 = "unknown";
        if (r2 != null) goto L85;
        r0.zzaW().zzb().zzb("PackageManager is null, app identity information might be inaccurate. appId", zzgu.zzl(r1));
    L5:
        String r8 = "Unknown";
    L26:
        this.zza = r1;
        this.zzd = r7;
        this.zzb = r6;
        this.zzc = r5;
        this.zze = r8;
        this.zzf = 0;
        zzic r02 = this.zzu;
        int r52 = r02.zzC();
        if (r52 != 0) goto L29;
        this.zzu.zzaW().zzk().zza("App measurement collection enabled");
    L48:
        this.zzm = _UrlKt.FRAGMENT_ENCODE_SET;
        zzic r62 = this.zzu;
        r62.zzaV();
        String r72 = this.zzk;     // Catch: IllegalStateException -> L61
        if (TextUtils.isEmpty(r72) == false) goto L54;
        r72 = zzlt.zza(r62.zzaZ(), "google_app_id", r02.zzq());     // Catch: IllegalStateException -> L61
    L54:
        if (TextUtils.isEmpty(r72) == true) goto L57;
        r4 = r72;
    L57:
        this.zzm = r4;     // Catch: IllegalStateException -> L61
        if (r52 != 0) goto L63;
        r62.zzaW().zzk().zzc("App measurement enabled for app package, google app id", this.zza, this.zzm);     // Catch: IllegalStateException -> L61
    L63:
        this.zzi = null;
        zzic r03 = this.zzu;
        r03.zzaV();
        List r12 = r03.zzc().zzs("analytics.safelisted_events");
        if (r12 != null) goto L67;
    L75:
        this.zzi = r12;
    L76:
        if (r2 == null) goto L79;
        this.zzl = InstantApps.isInstantApp(r03.zzaZ()) ? 1 : 0;
        return;
    L79:
        this.zzl = 0;
        return;
    L67:
        if (r12.isEmpty() == false) goto L69;
        r03.zzaW().zzh().zza("Safelisted event list is empty. Ignoring");
        goto L76
    L69:
        Iterator r42 = r12.iterator();
    L71:
        if (r42.hasNext() == false) goto L75;
        String r53 = (String) r42.next();
        if (r03.zzk().zzl("safelisted event", r53) == true) goto L71;
    L61:
        e = move-exception;
        this.zzu.zzaW().zzb().zzc("Fetching Google App Id failed with exception. appId", zzgu.zzl(r1), e);
        goto L63
    L29:
        if (r52 != 1) goto L31;
        this.zzu.zzaW().zzi().zza("App measurement deactivated via the manifest");
        goto L48
    L31:
        if (r52 != 3) goto L33;
        this.zzu.zzaW().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
        goto L48
    L33:
        if (r52 != 4) goto L35;
        this.zzu.zzaW().zzi().zza("App measurement disabled via the manifest");
        goto L48
    L35:
        if (r52 != 6) goto L37;
        this.zzu.zzaW().zzh().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
        goto L48
    L37:
        if (r52 == 7) goto L42;
        zzic r63 = this.zzu;
        if (r52 == 8) goto L41;
        r63.zzaW().zzi().zza("App measurement disabled");
        r63.zzaW().zzc().zza("Invalid scion state in identity");
        goto L48
    L41:
        r63.zzaW().zzi().zza("App measurement disabled due to denied storage consent");
        goto L48
    L42:
        this.zzu.zzaW().zzi().zza("App measurement disabled via the global data collection setting");
        goto L48
    L85:
        r7 = r2.getInstallerPackageName(r1);     // Catch: IllegalArgumentException -> L8
    L9:
        if (r7 != null) goto L12;
        r7 = "manual_install";
    L87:
        PackageInfo r04 = r2.getPackageInfo(this.zzu.zzaZ().getPackageName(), 0);     // Catch: PackageManager.NameNotFoundException -> L24
        if (r04 == null) goto L5;
        CharSequence r82 = r2.getApplicationLabel(r04.applicationInfo);     // Catch: PackageManager.NameNotFoundException -> L24
        if (TextUtils.isEmpty(r82) == true) goto L20;
        r8 = r82.toString();     // Catch: PackageManager.NameNotFoundException -> L24
    L81:
        r6 = r04.versionName;     // Catch: PackageManager.NameNotFoundException -> L23
        r5 = r04.versionCode;     // Catch: PackageManager.NameNotFoundException -> L23
    L23:
        String r05 = r6;
        r6 = r8;
    L25:
        this.zzu.zzaW().zzb().zzc("Error retrieving package info. appId, appName", zzgu.zzl(r1), r6);
        r8 = r6;
        r6 = r05;
        goto L26
    L20:
        r8 = "Unknown";
    L24:
        r05 = "Unknown";
        goto L25
    L12:
        if ("com.android.vending".equals(r7) == false) goto L87;
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        this.zzu.zzaW().zzb().zzb("Error retrieving app installer package name. appId", zzgu.zzl(r1));
        goto L9
    }

    public final zzr zzh(String r50) {
        zzg();
        String r2 = zzj();
        String r3 = zzk();
        zzb();
        String r4 = this.zzb;
        zzb();
        long r6 = this.zzc;
        zzb();
        Preconditions.checkNotNull(this.zzd);
        String r7 = this.zzd;
        zzic r0 = this.zzu;
        r0.zzc().zzi();
        zzb();
        zzg();
        long r9 = this.zzf;
        if (r9 != 0) goto L30;
        zzpp r92 = this.zzu.zzk();
        Context r10 = r0.zzaZ();
        String r02 = r0.zzaZ().getPackageName();
        r92.zzg();
        Preconditions.checkNotNull(r10);
        Preconditions.checkNotEmpty(r02);
        PackageManager r13 = r10.getPackageManager();
        MessageDigest r15 = zzpp.zzQ();
        long r16 = -1;
        if (r15 != null) goto L8;
        r92.zzu.zzaW().zzb().zza("Could not get MD5 instance");
        long r18 = 0;
    L7:
        r9 = r16;
    L28:
        this.zzf = r9;
    L29:
        long r102 = r9;
        zzic r03 = this.zzu;
        boolean r132 = r03.zzB();
        boolean r93 = !r03.zzd().zzm;
        zzg();
        if (r03.zzB() == true) goto L34;
    L33:
        String r152 = null;
    L47:
        zzic r04 = this.zzu;
        long r12 = r04.zzd().zzc.zza();
        long r22 = r04.zza;
        if (r12 == r18) goto L51;
        r22 = Math.min(r22, r12);
    L51:
        zzb();
        int r05 = this.zzl;
        zzic r122 = this.zzu;
        boolean r133 = r122.zzc().zzu();
        zzhh r14 = r122.zzd();
        r14.zzg();
        long r25 = r22;
        boolean r06 = r14.zzd().getBoolean("deferred_analytics_collection", false);
        if (r122.zzc().zzw("google_analytics_default_allow_ad_personalization_signals", true) == zzji.zzd) goto L55;
        boolean r23 = true;
    L56:
        long r42 = this.zzg;
        Boolean r24 = Boolean.valueOf(r23);
        List r62 = this.zzi;
        String r29 = r122.zzd().zzl().zzl();
        if (this.zzj != null) goto L59;
        this.zzj = r122.zzk().zzaz();
    L59:
        String r07 = this.zzj;
        if (r122.zzd().zzl().zzo(zzjk.zzb) == true) goto L62;
        long r33 = r42;
        String r08 = null;
    L76:
        boolean r26 = r122.zzc().zzx();
        zzpp r43 = r122.zzk();
        String r5 = zzj();
        zzic r123 = r43.zzu;
        if (r123.zzaZ().getPackageManager() != null) goto L117;
        boolean r35 = r26;
        long r44 = r18;
    L89:
        zzic r124 = this.zzu;
        int r17 = r124.zzd().zzl().zzb();
        String r36 = r124.zzd().zzj().zze();
        zzaif.zza();
        zzal r27 = r124.zzc();
        String r38 = r08;
        zzfx r09 = zzfy.zzaP;
        long r39 = r44;
        if (r27.zzp(null, r09) == false) goto L92;
        int r28 = r124.zzk().zzW();
    L93:
        zzaif.zza();
        if (r124.zzc().zzp(null, r09) == false) goto L96;
        long r45 = r124.zzk().zzX();
    L97:
        String r010 = r124.zzc().zzz();
        int r41 = r28;
        String r011 = new zze(r124.zzc().zzw("google_analytics_default_allow_ad_personalization_signals", true)).zzb();
        zzic r1 = this.zzu;
        long r210 = r1.zza;
        int r162 = r1.zzx().zzj().zza();
        if (r124.zzc().zzp(null, zzfy.zzbe) == false) goto L101;
        long r012 = r1.zzb;
    L103:
        return new zzr(r2, r3, r4, r6, r7, 161000, r102, r50, r132, r93, r152, r25, r05, r133, r06, r24, r33, r62, r29, r07, r38, r35, r39, r17, r36, r41, r45, r010, r011, r210, r162, r012);
    L101:
        r012 = r18;
        goto L103
    L96:
        r45 = r18;
        goto L97
    L92:
        r28 = 0;
        goto L93
    L117:
        r35 = r26;
        int r211 = 0;
        ApplicationInfo r125 = Wrappers.packageManager(r123.zzaZ()).getApplicationInfo(r5, 0);     // Catch: PackageManager.NameNotFoundException -> L105
        if (r125 == null) goto L85;
        int r46 = r125.targetSdkVersion;     // Catch: PackageManager.NameNotFoundException -> L105
    L88:
        r44 = r46;
    L85:
        r46 = r211;
    L87:
        zzic r47 = r43.zzu;
        r47.zzaV();
        r47.zzaW().zzi().zzb("PackageManager failed to find running app: app_id", r5);
    L86:
        r35 = r26;
        r211 = 0;
        goto L87
    L62:
        zzg();
        r33 = r42;
        if (this.zzo == r18) goto L73;
        long r48 = r122.zzba().currentTimeMillis() - this.zzo;
        if (this.zzn == null) goto L73;
        if (r48 <= DurationKt.MILLIS_IN_DAY) goto L73;
        if (this.zzp != null) goto L73;
        zzi();
    L73:
        if (this.zzn != null) goto L75;
        zzi();
    L75:
        r08 = this.zzn;
        goto L76
    L55:
        r23 = false;
        goto L56
    L34:
        zzaja.zza();
        if (r03.zzc().zzp(null, zzfy.zzaH) == false) goto L119;
        this.zzu.zzaW().zzk().zza("Disabled IID for tests.");
        goto L33
    L119:
        Class<?> r013 = r03.zzaZ().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");     // Catch: ClassNotFoundException -> L104
        if (r013 == null) goto L33;
        Object r142 = r013.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{this.zzu.zzaZ()});     // Catch: Exception -> L46
        if (r142 == null) goto L33;
        r152 = (String) r013.getDeclaredMethod("getFirebaseInstanceId", null).invoke(r142, null);     // Catch: Exception -> L45
    L45:
        this.zzu.zzaW().zzh().zza("Failed to retrieve Firebase Instance Id");
    L46:
        this.zzu.zzaW().zzf().zza("Failed to obtain Firebase Analytics instance");
        goto L33
    L8:
        if (r13 != null) goto L115;
        r18 = 0;
    L26:
        r9 = r18;
        goto L28
    L115:
    L22:
        e = e;
        r18 = 0;
    L25:
        r92.zzu.zzaW().zzb().zzb("Package name not found", e);
        goto L26
    L10:
        if (r92.zzag(r10, r02) == true) goto L24;
        PackageManagerWrapper r014 = Wrappers.packageManager(r10);     // Catch: PackageManager.NameNotFoundException -> L22
        zzic r103 = r92.zzu;     // Catch: PackageManager.NameNotFoundException -> L22
        r18 = 0;
        Signature[] r015 = r014.getPackageInfo(r103.zzaZ().getPackageName(), 64).signatures;     // Catch: PackageManager.NameNotFoundException -> L18
        if (r015 != null) goto L16;
    L20:
        r103.zzaW().zze().zza("Could not get signatures");     // Catch: PackageManager.NameNotFoundException -> L18
    L112:
        goto L7
    L16:
        if (r015.length <= 0) goto L20;
        r16 = zzpp.zzR(r15.digest(r015[0].toByteArray()));     // Catch: PackageManager.NameNotFoundException -> L18
    L18:
        e = e;
        goto L25
    L24:
        r18 = 0;
        r16 = 0;
        goto L7
    L30:
        r18 = 0;
        goto L29
    }

    public final void zzi() {
        zzg();
        zzic r0 = this.zzu;
        if (r0.zzd().zzl().zzo(zzjk.zzb) == true) goto L5;
        r0.zzaW().zzj().zza("Analytics Storage consent is not granted");
        String r1 = null;
    L6:
        zzgs r2 = r0.zzaW().zzj();
        if (r1 != null) goto L9;
        String r3 = "null";
    L10:
        r2.zza(String.format("Resetting session stitching token to %s", new Object[]{r3}));
        this.zzn = r1;
        this.zzo = r0.zzba().currentTimeMillis();
        return;
    L9:
        r3 = "not null";
        goto L10
    L5:
        byte[] r12 = new byte[16];
        r0.zzk().zzf().nextBytes(r12);
        r1 = String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r12)});
        goto L6
    }

    public final String zzj() {
        zzb();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    public final String zzk() {
        zzg();
        zzb();
        Preconditions.checkNotNull(this.zzm);
        return this.zzm;
    }

    public final String zzl() {
        zzb();
        Preconditions.checkNotNull(this.zze);
        return this.zze;
    }

    public final int zzm() {
        zzb();
        return this.zzc;
    }

    public final long zzn() {
        return this.zzh;
    }

    public final int zzo() {
        zzb();
        return this.zzl;
    }

    public final List zzp() {
        return this.zzi;
    }

    public final boolean zzq(String r3) {
        String r0 = this.zzp;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L7:
        this.zzp = r3;
        return r1;
    L5:
        if (r0.equals(r3) == true) goto L7;
        r1 = true;
        goto L7
    }
}
