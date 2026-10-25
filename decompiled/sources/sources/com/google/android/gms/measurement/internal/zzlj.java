package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.chaquo.python.internal.Common;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzabw;
import com.google.android.gms.internal.measurement.zzabx;
import com.google.android.gms.internal.measurement.zzaif;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzlj extends zzg {
    protected zzky zza;
    final zzx zzb;
    protected boolean zzc;
    private zzjp zzd;
    private final Set zze;
    private boolean zzf;
    private final AtomicReference zzg;
    private final Object zzh;
    private boolean zzi;
    private int zzj;
    private zzaz zzk;
    private zzaz zzl;
    private PriorityQueue zzm;
    private zzjl zzn;
    private final AtomicLong zzo;
    private long zzp;
    private zzaz zzq;
    private SharedPreferences.OnSharedPreferenceChangeListener zzr;
    private zzaz zzs;
    private final zzpo zzt;

    public zzlj(zzic r4) {
        super(r4);
        this.zze = new CopyOnWriteArraySet();
        this.zzh = new Object();
        this.zzi = false;
        this.zzj = 1;
        this.zzc = true;
        this.zzt = new zzkn(this);
        this.zzg = new AtomicReference();
        this.zzn = zzjl.zza;
        this.zzp = -1;
        this.zzo = new AtomicLong(0);
        this.zzb = new zzx(r4);
    }

    private final zzlr zzap(zzom r11) {
        URL r5 = new URI(r11.zzc).toURL();     // Catch: MalformedURLException -> L32 Throwable -> L34
        AtomicReference r1 = new AtomicReference();
        String r4 = this.zzu.zzv().zzl();
        zzic r0 = this.zzu;
        zzgs r2 = r0.zzaW().zzk();
        Long r3 = Long.valueOf(r11.zza);
        r2.zzd("[sgtm] Uploading data from app. row_id, url, uncompressed size", r3, r11.zzc, Integer.valueOf(r11.zzb.length));
        if (TextUtils.isEmpty(r11.zzg) == true) goto L6;
        r0.zzaW().zzk().zzc("[sgtm] Uploading data from app. row_id", r3, r11.zzg);
    L6:
        HashMap r7 = new HashMap();
        Bundle r22 = r11.zzd;
        Iterator<String> r32 = r22.keySet().iterator();
    L8:
        if (r32.hasNext() == false) goto L12;
        String r6 = r32.next();
        String r8 = r22.getString(r6);
        if (TextUtils.isEmpty(r8) == true) goto L8;
        r7.put(r6, r8);
        goto L8
    L12:
        zzlo r33 = r0.zzn();
        byte[] r62 = r11.zzb;
        zzkz r82 = new zzkz(this, r1, r11);
        r33.zzw();
        Preconditions.checkNotNull(r5);
        Preconditions.checkNotNull(r62);
        Preconditions.checkNotNull(r82);
        r33.zzu.zzaX().zzm(new zzln(r33, r4, r5, r62, r7, r82));
        zzic r112 = r0.zzk().zzu;     // Catch: InterruptedException -> L26
        long r42 = 60000;
        long r23 = r112.zzba().currentTimeMillis() + 60000;     // Catch: InterruptedException -> L26
        monitor-enter(r1);     // Catch: InterruptedException -> L26
    L38:
    L20:
        th = move-exception;
        throw th;     // Catch: InterruptedException -> L26
    L16:
        if (r1.get() != null) goto L22;
        if (r42 <= 0) goto L22;
        r1.wait(r42);     // Catch: Throwable -> L20
        r42 = r23 - r112.zzba().currentTimeMillis();     // Catch: Throwable -> L20
    L22:
        monitor-exit(r1);     // Catch: Throwable -> L20
    L28:
        if (r1.get() != null) goto L31;
        return zzlr.zza;
    L31:
        return (zzlr) r1.get();
    L26:
        this.zzu.zzaW().zze().zza("[sgtm] Interrupted waiting for uploading batch");
    L34:
        e = move-exception;
        this.zzu.zzaW().zzb().zzd("[sgtm] Bad upload url for row_id", r11.zzc, Long.valueOf(r11.zza), e);
        return zzlr.zzc;
    }

    private final void zzaq(Boolean r4, boolean r5) {
        zzg();
        zzb();
        zzic r0 = this.zzu;
        r0.zzaW().zzj().zzb("Setting app measurement enabled (FE)", r4);
        r0.zzd().zzh(r4);
        if (r5 == false) goto L10;
        zzhh r52 = r0.zzd();
        zzic r02 = r52.zzu;
        r52.zzg();
        SharedPreferences.Editor r53 = r52.zzd().edit();
        if (r4 == null) goto L7;
        r53.putBoolean("measurement_enabled_from_api", r4.booleanValue());
    L8:
        r53.apply();
        goto L10
    L7:
        r53.remove("measurement_enabled_from_api");
    L10:
        if (this.zzu.zzE() == true) goto L16;
        if (r4 != null) goto L13;
        return;
    L13:
        if (r4.booleanValue() == false) goto L16;
        return;
    L16:
        zzar();
    }

    private final void zzar() {
        zzg();
        zzic r0 = this.zzu;
        String r1 = r0.zzd().zzh.zza();
        if (r1 != null) goto L5;
        zzlj r2 = this;
    L14:
        if (r2.zzu.zzB() == true) goto L16;
    L19:
        r0.zzaW().zzj().zza("Updating Scion state (FE)");
        r2.zzu.zzt().zzi();
        return;
    L16:
        if (r2.zzc == false) goto L19;
        r0.zzaW().zzj().zza("Recording app launch after enabling measurement for the first time (FE)");
        r2.zzT();
        r2.zzu.zzh().zza.zza();
        r0.zzaX().zzj(new zzjz(r2));
        return;
    L5:
        if ("unset".equals(r1) == false) goto L7;
        r2 = this;
        r2.zzM(Common.ASSET_APP, "_npa", null, r0.zzba().currentTimeMillis());
        goto L14
    L7:
        r2 = this;
        if (true == "true".equals(r1)) goto L10;
        long r3 = 0;
    L11:
        r2.zzM(Common.ASSET_APP, "_npa", Long.valueOf(r3), r0.zzba().currentTimeMillis());
        goto L14
    L10:
        r3 = 1;
        goto L11
    }

    public final void zzA(String r13, String r14, Bundle r15) {
        zzic r0 = this.zzu;
        long r8 = r0.zzba().currentTimeMillis();
        if (r0.zzc().zzp(null, zzfy.zzbe) == false) goto L6;
        long r02 = r0.zzba().elapsedRealtime();
    L7:
        zzB(r13, r14, r15, true, true, r8, r02);
        return;
    L6:
        r02 = 0;
        goto L7
    }

    public final void zzB(String r13, String r14, Bundle r15, boolean r16, boolean r17, long r18, long r20) {
        if (r15 != null) goto L4;
        r15 = new Bundle();
    L4:
        Bundle r7 = r15;
        if (Objects.equals(r14, "screen_view") == false) goto L13;
        zzic r12 = this.zzu;
        long r4 = 0;
        zzmb r0 = r12.zzs();
        if (true != r12.zzc().zzp(null, zzfy.zzbe)) goto L10;
        r4 = r20;
    L10:
        r0.zzj(r7, r18, r4);
        return;
    L13:
        if (r17 == true) goto L15;
    L18:
        boolean r9 = true;
    L20:
        if (r13 != null) goto L22;
        r13 = Common.ASSET_APP;
    L22:
        String r1 = r13;
        if (true == this.zzu.zzc().zzp(null, zzfy.zzbe)) goto L25;
        long r5 = 0;
    L26:
        zzI(r1, r14, r18, r5, r7, r17, r9, r16, null);
        return;
    L25:
        r5 = r20;
        goto L26
    L15:
        if (this.zzd == null) goto L18;
        if (zzpp.zzac(r14) == true) goto L18;
        r9 = false;
        goto L20
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzC() {
        zzg();
        zzic r1 = this.zzu;
        r1.zzaW().zzj().zza("Handle tcf update.");
        SharedPreferences r2 = r1.zzd().zze();
        ImmutableList r3 = zzof.zza;
        zzabw r4 = zzabw.zzb;
        zzoe r5 = zzoe.zza;
        zzabw r6 = zzabw.zzc;
        zzoe r7 = zzoe.zzd;
        ImmutableMap r18 = ImmutableMap.of(r4, r5, r6, r7, zzabw.zzd, r5, zzabw.zze, r5, zzabw.zzh, r7, zzabw.zzj, r7, zzabw.zzk, r7);
        ImmutableSet r20 = ImmutableSet.of("CH");
        char[] r32 = new char[5];
        boolean r322 = r2.contains("IABTCF_TCString");
        int r22 = zzof.zzb(r2, "IABTCF_CmpSdkID");
        int r25 = zzof.zzb(r2, "IABTCF_PolicyVersion");
        int r24 = zzof.zzb(r2, "IABTCF_gdprApplies");
        int r26 = zzof.zzb(r2, "IABTCF_PurposeOneTreatment");
        int r23 = zzof.zzb(r2, "IABTCF_EnableAdvertiserConsentMode");
        String r27 = zzof.zza(r2, "IABTCF_PublisherCC");
        ImmutableMap.Builder r42 = ImmutableMap.builder();
        UnmodifiableIterator r52 = r18.keySet().iterator();
    L4:
        if (r52.hasNext() == false) goto L24;
        zzabw r62 = (zzabw) r52.next();
        int r11 = r62.zza();
        StringBuilder r13 = new StringBuilder(String.valueOf(r11).length() + 28);
        r13.append("IABTCF_PublisherRestrictions");
        r13.append(r11);
        String r112 = zzof.zza(r2, r13.toString());
        if (TextUtils.isEmpty(r112) == false) goto L8;
    L22:
        zzabx r72 = zzabx.zzd;
    L23:
        r42.put(r62, r72);
        goto L4
    L8:
        if (r112.length() < 755) goto L22;
        int r8 = Character.digit(r112.charAt(754), 10);
        if (r8 >= 0) goto L13;
    L21:
        r72 = zzabx.zza;
        goto L23
    L13:
        if (r8 > zzabx.values().length) goto L21;
        if (r8 == 0) goto L21;
        if (r8 == 1) goto L20;
        if (r8 == 2) goto L19;
        r72 = zzabx.zzd;
        goto L23
    L19:
        r72 = zzabx.zzc;
        goto L23
    L20:
        r72 = zzabx.zzb;
        goto L23
    L24:
        ImmutableMap r19 = r42.buildOrThrow();
        String r28 = zzof.zza(r2, "IABTCF_PurposeConsents");
        String r43 = zzof.zza(r2, "IABTCF_VendorConsents");
        if (TextUtils.isEmpty(r43) == false) goto L27;
    L31:
        boolean r30 = false;
    L32:
        String r29 = zzof.zza(r2, "IABTCF_PurposeLegitimateInterests");
        String r210 = zzof.zza(r2, "IABTCF_VendorLegitimateInterests");
        if (TextUtils.isEmpty(r210) == false) goto L35;
    L39:
        boolean r31 = false;
    L40:
        r32[0] = '2';
        zzod r211 = new zzod(zzof.zzd(r18, r19, r20, r32, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r322));
        r1.zzaW().zzk().zzb("Tcf preferences read", r211);
        zzhh r33 = r1.zzd();
        r33.zzg();
        String r34 = r33.zzd().getString("stored_tcf_param", _UrlKt.FRAGMENT_ENCODE_SET);
        HashMap r63 = new HashMap();
        if (TextUtils.isEmpty(r34) == false) goto L43;
        zzod r35 = new zzod(r63);
    L52:
        zzhh r64 = r1.zzd();
        r64.zzg();
        String r53 = r64.zzd().getString("stored_tcf_param", _UrlKt.FRAGMENT_ENCODE_SET);
        String r73 = r211.zza();
        if (r73.equals(r53) == true) goto L69;
        SharedPreferences.Editor r54 = r64.zzd().edit();
        r54.putString("stored_tcf_param", r73);
        r54.apply();
        Bundle r44 = r211.zzb();
        r1.zzaW().zzk().zzb("Consent generated from Tcf", r44);
        if (r44 == Bundle.EMPTY) goto L57;
        zzp(r44, -30, r1.zzba().currentTimeMillis());
    L57:
        Bundle r12 = new Bundle();
        r12.putString("_tcfm", r211.zzd(r35));
        r12.putString("_tcfd2", r211.zzc());
        r12.putString("_tcfd", r211.zze());
        zzE("auto", "_tcf", r12);
        return;
    L69:
        return;
    L43:
        String[] r36 = r34.split(";");
        int r82 = r36.length;
        int r9 = 0;
    L44:
        if (r9 >= r82) goto L51;
        String[] r122 = r36[r9].split("=");
        if (r122.length < 2) goto L50;
        if (zzof.zza.contains(r122[0]) == false) goto L50;
        r63.put(r122[0], r122[1]);
    L50:
        r9 = r9 + 1;
        goto L44
    L51:
        r35 = new zzod(r63);
        goto L52
    L35:
        if (r210.length() < 755) goto L39;
        if (r210.charAt(754) != '1') goto L39;
        r31 = true;
        goto L40
    L27:
        if (r43.length() < 755) goto L31;
        if (r43.charAt(754) != '1') goto L31;
        r30 = true;
        goto L32
    }

    public final void zzD() {
        zzg();
        zzic r0 = this.zzu;
        r0.zzaW().zzj().zza("Register tcfPrefChangeListener.");
        if (this.zzr != null) goto L5;
        this.zzs = new zzkb(this, this.zzu);
        this.zzr = new zzle(this);
    L5:
        r0.zzd().zze().registerOnSharedPreferenceChangeListener(this.zzr);
    }

    public final void zzE(String r11, String r12, Bundle r13) {
        zzg();
        zzic r0 = this.zzu;
        long r5 = r0.zzba().currentTimeMillis();
        if (r0.zzc().zzp(null, zzfy.zzbe) == false) goto L6;
        long r02 = r0.zzba().elapsedRealtime();
    L7:
        zzF(r11, r12, r5, r02, r13);
        return;
    L6:
        r02 = 0;
        goto L7
    }

    public final void zzF(String r13, String r14, long r15, long r17, Bundle r19) {
        zzg();
        boolean r2 = true;
        if (this.zzd == null) goto L8;
        if (zzpp.zzac(r14) == true) goto L8;
        r2 = false;
    L8:
        zzG(r13, r14, r15, r17, r19, true, r2, true, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r22v0 */
    /* JADX WARN: Type inference failed for: r22v1 */
    /* JADX WARN: Type inference failed for: r22v3 */
    /* JADX WARN: Type inference failed for: r22v4 */
    public final void zzG(String r26, String r27, long r28, long r30, Bundle r32, boolean r33, boolean r34, boolean r35, String r36) {
        String r7 = r26;
        Preconditions.checkNotEmpty(r7);
        Preconditions.checkNotNull(r32);
        zzg();
        zzb();
        zzic r0 = this.zzu;
        boolean r2 = r0.zzB();
        zzic r3 = this.zzu;
        if (r2 == false) goto L172;
        List r22 = r3.zzv().zzp();
        if (r22 == null) goto L11;
        if (r22.contains(r27) == true) goto L11;
        this.zzu.zzaW().zzj().zzc("Dropping non-safelisted event. event name, origin", r27, r7);
        return;
    L11:
        if (this.zzf == true) goto L24;
        this.zzf = true;
    L23:
        this.zzu.zzaW().zzi().zza("Tag Manager is not found and thus will not be used");
        goto L24
    L15:
        if (r0.zzp() == true) goto L17;
        Class<?> r02 = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, this.zzu.zzaZ().getClassLoader());     // Catch: ClassNotFoundException -> L23
    L174:
        r02.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{this.zzu.zzaZ()});     // Catch: Exception -> L20
    L20:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Failed to invoke Tag Manager's initialize() method", e);     // Catch: ClassNotFoundException -> L23
        goto L24
    L17:
        r02 = Class.forName("com.google.android.gms.tagmanager.TagManagerService");     // Catch: ClassNotFoundException -> L23
    L24:
        zzic r12 = this.zzu;
        if (r12.zzc().zzp(null, zzfy.zzaZ) == false) goto L27;
    L31:
        r12.zzaV();
        if (r33 == true) goto L34;
    L36:
        int r13 = 0;
        if (r35 == true) goto L60;
        r12.zzaV();
        if ("_iap".equals(r27) == true) goto L60;
        zzic r1 = this.zzu;
        zzpp r23 = r1.zzk();
        if (r23.zzk("event", r27) == true) goto L43;
    L42:
        int r5 = 2;
    L54:
        if (r5 == 0) goto L60;
        r12.zzaW().zzd().zzb("Invalid public event name. Event will not be logged (FE)", r12.zzl().zza(r27));
        zzpp r24 = r1.zzk();
        r1.zzc();
        String r03 = r24.zzE(r27, 40, true);
        if (r27 == null) goto L58;
        r13 = r27.length();
    L58:
        r1.zzk().zzP(this.zzt, null, r5, "_ev", r03, r13);
        return;
    L43:
        zzic r4 = r23.zzu;
        String[] r14 = zzjm.zza;
        if (r4.zzc().zzp(null, zzfy.zzbf) == false) goto L46;
        String[] r52 = zzjm.zzc;
    L48:
        if (r23.zzm("event", r14, r52, r27) == true) goto L50;
        r5 = 13;
        goto L54
    L50:
        r4.zzc();
        if (r23.zzn("event", 40, r27) == false) goto L42;
        r5 = 0;
        goto L54
    L46:
        r52 = zzjm.zzb;
    L60:
        r12.zzaV();
        zzic r142 = this.zzu;
        zzlu r15 = r142.zzs().zzh(false);
        if (r15 != null) goto L63;
    L65:
        if (r33 == false) goto L68;
        if (r35 == true) goto L68;
        boolean r37 = true;
    L69:
        zzpp.zzay(r15, r32, r37);
        boolean r16 = "am".equals(r7);
        boolean r38 = zzpp.zzac(r27);
        if (r33 == true) goto L72;
    L78:
        boolean r9 = r16;
    L79:
        zzic r152 = this.zzu;
        if (r152.zzH() == false) goto L187;
        int r17 = r12.zzk().zzp(r27);
        if (r17 == 0) goto L89;
        r12.zzaW().zzd().zzb("Invalid event name. Event will not be logged (FE)", r12.zzl().zza(r27));
        zzpp r25 = r12.zzk();
        r12.zzc();
        String r04 = r25.zzE(r27, 40, true);
        if (r27 == null) goto L87;
        r13 = r27.length();
    L87:
        r152.zzk().zzP(this.zzt, r36, r17, "_ev", r04, r13);
        return;
    L89:
        Bundle r05 = r12.zzk().zzH(r36, r27, r32, CollectionUtils.listOf(new String[]{"_o", "_sn", "_sc", "_si"}), r35);
        Preconditions.checkNotNull(r05);
        r12.zzaV();
        if (r142.zzs().zzh(false) != null) goto L92;
    L96:
        boolean r222 = 0;
        zzic r21 = r142;
        long r322 = 0;
    L98:
        if ("auto".equals(r7) == true) goto L112;
        if ("_ssr".equals(r27) == false) goto L112;
        zzpp r29 = r12.zzk();
        String r42 = r05.getString("_ffr");
        if (Strings.isEmptyOrWhitespace(r42) == false) goto L104;
        r42 = null;
    L106:
        zzic r210 = r29.zzu;
        if (Objects.equals(r42, r210.zzd().zzq.zza()) == true) goto L109;
        r210.zzd().zzq.zzb(r42);
    L116:
        ArrayList r132 = new ArrayList();
        r132.add(r05);
        if (r12.zzc().zzp(null, zzfy.zzaS) == false) goto L119;
        boolean r211 = r21.zzh().zzi();
    L121:
        if (r12.zzd().zzk.zza() > r322) goto L123;
    L126:
        boolean r332 = r9;
        long r92 = r322;
        String r143 = "_ae";
        zzlj r11 = this;
    L128:
        if (r05.getLong("extend_session", r92) != 1) goto L130;
        r12.zzaW().zzk().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
        r152.zzh().zza.zzb(r28, r30, true);
    L130:
        ArrayList r18 = new ArrayList(r05.keySet());
        Collections.sort(r18);
        int r212 = r18.size();
        int r39 = r222;
    L131:
        if (r39 >= r212) goto L147;
        String r43 = (String) r18.get(r39);
        if (r43 == null) goto L146;
        r12.zzk();
        Object r53 = r05.get(r43);
        if ((r53 instanceof Bundle) == false) goto L138;
        Bundle[] r93 = new Bundle[1];
        r93[r222] = (Bundle) r53;
    L144:
        if (r93 == null) goto L146;
        r05.putParcelableArray(r43, r93);
        goto L146
    L138:
        if ((r53 instanceof Parcelable[]) == false) goto L141;
        Parcelable[] r54 = (Parcelable[]) r53;
        r93 = (Bundle[]) Arrays.copyOf(r54, r54.length, Bundle[].class);
        goto L144
    L141:
        if ((r53 instanceof ArrayList) == false) goto L143;
        ArrayList r55 = (ArrayList) r53;
        r93 = (Bundle[]) r55.toArray(new Bundle[r55.size()]);
        goto L144
    L143:
        r93 = null;
    L146:
        r39 = r39 + 1;
        goto L131
    L147:
        int r94 = r222;
    L149:
        if (r94 >= r132.size()) goto L166;
        Bundle r06 = (Bundle) r132.get(r94);
        if (r94 == 0) goto L153;
        String r19 = "_ep";
    L154:
        r06.putString("_o", r7);
        if (r34 == false) goto L157;
        r06 = r12.zzk().zzae(r06, null);
    L157:
        Bundle r153 = r06;
        r21.zzt().zzn(new zzbh(r19, new zzbf(r153), r7, r28, r30), r36);
        if (r332 == true) goto L165;
        Iterator r72 = r11.zze.iterator();
    L163:
        if (r72.hasNext() == false) goto L165;
        ((zzjq) r72.next()).onEvent(r26, r27, new Bundle(r153), r28);
    L165:
        r94 = r94 + 1;
        r7 = r26;
        goto L149
    L153:
        r19 = r27;
        goto L154
    L166:
        r12.zzaV();
        if (r21.zzs().zzh(r222) != null) goto L169;
        return;
    L169:
        if (r143.equals(r27) == false) goto L186;
        zzoc r07 = r21.zzh();
        r07.zzb.zzd(true, true, r12.zzba().elapsedRealtime());
        return;
    L186:
        return;
    L123:
        if (r12.zzd().zzo(r28) == false) goto L126;
        if (r211 == false) goto L126;
        r12.zzaW().zzk().zza("Current session is expired, remove the session number, ID, and engagement time");
        r332 = r9;
        r92 = r322;
        r143 = "_ae";
        zzM("auto", "_sid", null, r12.zzba().currentTimeMillis());
        zzM("auto", "_sno", null, r12.zzba().currentTimeMillis());
        zzM("auto", "_se", null, r12.zzba().currentTimeMillis());
        r11 = this;
        r12.zzd().zzl.zzb(r92);
        goto L128
    L119:
        r211 = r12.zzd().zzn.zza();
        goto L121
    L109:
        r210.zzaW().zzj().zza("Not logging duplicate session_start_with_rollout event");
        return;
    L104:
        if (r42 == null) goto L106;
        r42 = r42.trim();
    L112:
        if ("_ae".equals(r27) == false) goto L116;
        String r213 = r12.zzk().zzu.zzd().zzq.zza();
        if (TextUtils.isEmpty(r213) == true) goto L116;
        r05.putString("_ffr", r213);
        goto L116
    L92:
        if ("_ae".equals(r27) == false) goto L96;
        zzoa r214 = r142.zzh().zzb;
        r322 = 0;
        long r44 = r214.zzc.zzu.zzba().elapsedRealtime();
        boolean r223 = false;
        r21 = r142;
        long r133 = r44 - r214.zzb;
        r214.zzb = r44;
        r222 = r223;
        if (r133 <= 0) goto L98;
        r12.zzk().zzan(r05, r133);
        r222 = r223;
        goto L98
    L187:
        return;
    L72:
        if (this.zzd == null) goto L78;
        if (r38 == true) goto L78;
        if (r16 == false) goto L76;
        r9 = true;
        goto L79
    L76:
        r12.zzaW().zzj().zzc("Passing event to registered event handler (FE)", r12.zzl().zza(r27), r12.zzl().zze(r32));
        Preconditions.checkNotNull(this.zzd);
        this.zzd.interceptEvent(r7, r27, r32, r28);
        return;
    L68:
        r37 = false;
        goto L69
    L63:
        if (r32.containsKey("_sc") == true) goto L65;
        r15.zzd = true;
        goto L65
    L34:
        if (zzpp.zzai(r27) == false) goto L36;
        r12.zzk().zzK(r32, r12.zzd().zzt.zza());
        goto L36
    L27:
        if ("_cmp".equals(r27) == false) goto L31;
        if (r32.containsKey("gclid") == false) goto L31;
        r12.zzaV();
        zzM("auto", "_lgclid", r32.getString("gclid"), r12.zzba().currentTimeMillis());
        goto L31
    L172:
        r3.zzaW().zzj().zza("Event not sent since app measurement is disabled");
    }

    public final void zzH(String r13, String r14, Bundle r15, String r16) {
        zzic.zzL();
        zzic r1 = this.zzu;
        long r3 = r1.zzba().currentTimeMillis();
        if (r1.zzc().zzp(null, zzfy.zzbe) == false) goto L6;
        long r12 = r1.zzba().elapsedRealtime();
    L7:
        zzI("auto", r14, r3, r12, r15, false, true, true, r16);
        return;
    L6:
        r12 = 0;
        goto L7
    }

    public final void zzI(String r15, String r16, long r17, long r19, Bundle r21, boolean r22, boolean r23, boolean r24, String r25) {
        int r0 = zzpp.$r8$clinit;
        Bundle r9 = new Bundle(r21);
        Iterator<String> r02 = r9.keySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L26;
        String r1 = r02.next();
        Object r2 = r9.get(r1);
        if ((r2 instanceof Bundle) == true) goto L7;
        int r3 = 0;
        if ((r2 instanceof Parcelable[]) == true) goto L10;
        if ((r2 instanceof List) == false) goto L4;
        List r26 = (List) r2;
    L21:
        if (r3 >= r26.size()) goto L4;
        Object r12 = r26.get(r3);
        if ((r12 instanceof Bundle) == false) goto L25;
        r26.set(r3, new Bundle((Bundle) r12));
    L25:
        r3 = r3 + 1;
        goto L21
    L10:
        Parcelable[] r27 = (Parcelable[]) r2;
    L12:
        if (r3 >= r27.length) goto L4;
        Parcelable r13 = r27[r3];
        if ((r13 instanceof Bundle) == false) goto L16;
        r27[r3] = new Bundle((Bundle) r13);
    L16:
        r3 = r3 + 1;
        goto L12
    L7:
        r9.putBundle(r1, new Bundle((Bundle) r2));
        goto L4
    L26:
        this.zzu.zzaX().zzj(new zzkc(this, r15, r16, r17, r19, r9, r22, r23, r24, r25));
    }

    public final void zzJ(String r8, String r9, Object r10, boolean r11) {
        zzK("auto", "_ldl", r10, true, this.zzu.zzba().currentTimeMillis());
    }

    public final void zzK(String r11, String r12, Object r13, boolean r14, long r15) {
        zzic r3 = this.zzu;
        int r4 = 0;
        if (r14 == false) goto L5;
        int r32 = r3.zzk().zzs(r12);
    L16:
        if (r32 == 0) goto L22;
        zzic r1 = this.zzu;
        zzpp r7 = r1.zzk();
        r1.zzc();
        String r16 = r7.zzE(r12, 24, true);
        if (r12 == null) goto L20;
        r4 = r12.length();
    L20:
        zzic r2 = this.zzu;
        r2.zzk().zzP(this.zzt, null, r32, "_ev", r16, r4);
        return;
    L22:
        if (r11 != null) goto L24;
        String r33 = Common.ASSET_APP;
    L25:
        if (r13 == null) goto L39;
        zzic r72 = this.zzu;
        int r8 = r72.zzk().zzM(r12, r13);
        if (r8 == 0) goto L35;
        zzpp r34 = r72.zzk();
        r72.zzc();
        String r22 = r34.zzE(r12, 24, true);
        if ((r13 instanceof String) == false) goto L31;
    L32:
        r4 = r13.toString().length();
    L33:
        zzic r17 = this.zzu;
        r17.zzk().zzP(this.zzt, null, r8, "_ev", r22, r4);
        return;
    L31:
        if ((r13 instanceof CharSequence) == false) goto L33;
    L35:
        Object r5 = r72.zzk().zzN(r12, r13);
        if (r5 == null) goto L41;
        zzL(r33, r12, r15, r5);
        return;
    L41:
        return;
    L39:
        zzL(r33, r12, r15, null);
        return;
    L24:
        r33 = r11;
        goto L25
    L5:
        zzpp r35 = r3.zzk();
        if (r35.zzk("user property", r12) == true) goto L9;
    L7:
        r32 = 6;
        goto L16
    L9:
        if (r35.zzm("user property", zzjo.zza, null, r12) == true) goto L11;
        r32 = 15;
        goto L16
    L11:
        r35.zzu.zzc();
        if (r35.zzn("user property", 24, r12) == false) goto L7;
        r32 = 0;
        goto L16
    }

    public final void zzL(String r9, String r10, long r11, Object r13) {
        this.zzu.zzaX().zzj(new zzkd(this, r9, r10, r13, r11));
    }

    public final void zzM(String r10, String r11, Object r12, long r13) {
        Preconditions.checkNotEmpty(r10);
        Preconditions.checkNotEmpty(r11);
        zzg();
        zzb();
        Object r122 = r12;
        if ("allow_personalized_ads".equals(r11) == true) goto L5;
    L20:
        String r4 = r11;
        Object r7 = r122;
        zzic r112 = this.zzu;
        if (r112.zzB() == true) goto L25;
        this.zzu.zzaW().zzk().zza("User property not set since app measurement is disabled");
        return;
    L25:
        if (r112.zzH() == true) goto L27;
        return;
    L27:
        zzic r9 = this.zzu;
        r9.zzt().zzA(new zzpl(r4, r13, r7, r10));
        return;
    L5:
        if ((r12 instanceof String) == false) goto L17;
        String r0 = (String) r12;
        if (TextUtils.isEmpty(r0) == true) goto L17;
        String r113 = r0.toLowerCase(Locale.ENGLISH);
        String r02 = "false";
        if (true == "false".equals(r113)) goto L11;
        long r114 = 0;
    L12:
        zzic r42 = this.zzu;
        Long r123 = Long.valueOf(r114);
        zzhg r115 = r42.zzd().zzh;
        if (r123.longValue() != 1) goto L15;
        r02 = "true";
    L15:
        r115.zzb(r02);
        r12 = r123;
    L16:
        r11 = "_npa";
    L19:
        this.zzu.zzaW().zzk().zzc("Setting user property(FE)", "non_personalized_ads(_npa)", r12);
        r122 = r12;
        goto L20
    L11:
        r114 = 1;
    L17:
        if (r12 != null) goto L19;
        this.zzu.zzd().zzh.zzb("unset");
        r12 = r12;
        goto L16
    }

    public final Map zzO(String r10, String r11, boolean r12) {
        zzic r0 = this.zzu;
        if (r0.zzaX().zze() == false) goto L6;
        r0.zzaW().zzb().zza("Cannot get user properties from analytics worker thread");
        return Collections.EMPTY_MAP;
    L6:
        r0.zzaV();
        if (zzae.zza() == false) goto L10;
        r0.zzaW().zzb().zza("Cannot get user properties from main thread");
        return Collections.EMPTY_MAP;
    L10:
        AtomicReference r2 = new AtomicReference();
        this.zzu.zzaX().zzk(r2, 5000, "get user properties", new zzkl(this, r2, null, r10, r11, r12));
        List r9 = (List) r2.get();
        if (r9 != null) goto L14;
        r0.zzaW().zzb().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(r12));
        return Collections.EMPTY_MAP;
    L14:
        ArrayMap r102 = new ArrayMap(r9.size());
        Iterator r92 = r9.iterator();
    L16:
        if (r92.hasNext() == false) goto L20;
        zzpl r112 = (zzpl) r92.next();
        Object r122 = r112.zza();
        if (r122 == null) goto L16;
        r102.put(r112.zzb, r122);
        goto L16
    L20:
        return r102;
    }

    public final String zzP() {
        return (String) this.zzg.get();
    }

    public final void zzQ(String r1) {
        this.zzg.set(r1);
    }

    public final void zzR() {
        zzg();
        zzic r0 = this.zzu;
        if (r0.zzd().zzo.zza() == true) goto L13;
        long r1 = r0.zzd().zzp.zza();
        r0.zzd().zzp.zzb(1 + r1);
        r0.zzc();
        if (r1 < 5) goto L9;
        r0.zzaW().zze().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
        r0.zzd().zzo.zzb(true);
        return;
    L9:
        if (this.zzq != null) goto L11;
        this.zzq = new zzkg(this, this.zzu);
    L11:
        this.zzq.zzb(0);
        return;
    L13:
        r0.zzaW().zzj().zza("Deferred Deep Link already retrieved. Not fetching again.");
    }

    public final void zzS(long r1, long r3) {
        this.zzg.set(null);
        this.zzu.zzaX().zzj(new zzkh(this, r1));
    }

    public final void zzT() {
        zzg();
        zzb();
        if (this.zzu.zzH() == false) goto L23;
        zzic r0 = this.zzu;
        zzal r1 = r0.zzc();
        r1.zzu.zzaV();
        Boolean r12 = r1.zzr("google_analytics_deferred_deep_link_enabled");
        if (r12 != null) goto L8;
    L10:
        this.zzu.zzt().zzE();
        this.zzc = false;
        zzhh r13 = r0.zzd();
        r13.zzg();
        String r2 = r13.zzd().getString("previous_os_version", null);
        r13.zzu.zzu().zzw();
        String r3 = Build.VERSION.RELEASE;
        if (TextUtils.isEmpty(r3) == true) goto L16;
        if (r3.equals(r2) == true) goto L16;
        SharedPreferences.Editor r14 = r13.zzd().edit();
        r14.putString("previous_os_version", r3);
        r14.apply();
    L16:
        if (TextUtils.isEmpty(r2) == true) goto L21;
        r0.zzu().zzw();
        if (r2.equals(r3) == true) goto L22;
        Bundle r02 = new Bundle();
        r02.putString("_po", r2);
        zzE("auto", "_ou", r02);
        return;
    L22:
        return;
    L21:
        return;
    L8:
        if (r12.booleanValue() == false) goto L10;
        r0.zzaW().zzj().zza("Deferred Deep Link feature enabled.");
        r0.zzaX().zzj(new zzlh(this));
        goto L10
    }

    public final void zzU(zzjp r3) {
        zzg();
        zzb();
        if (r3 == null) goto L10;
        zzjp r0 = this.zzd;
        if (r3 == r0) goto L10;
        if (r0 != null) goto L8;
        boolean r02 = true;
    L9:
        Preconditions.checkState(r02, "EventInterceptor already set.");
        goto L10
    L8:
        r02 = false;
    L10:
        this.zzd = r3;
    }

    public final void zzV(zzjq r2) {
        zzb();
        Preconditions.checkNotNull(r2);
        if (this.zze.add(r2) == true) goto L6;
        this.zzu.zzaW().zze().zza("OnEventListener already registered");
        return;
    }

    public final void zzW(zzjq r2) {
        zzb();
        Preconditions.checkNotNull(r2);
        if (this.zze.remove(r2) == true) goto L6;
        this.zzu.zzaW().zze().zza("OnEventListener had not been registered");
        return;
    }

    public final int zzX(String r1) {
        Preconditions.checkNotEmpty(r1);
        this.zzu.zzc();
        return 25;
    }

    public final void zzY(Bundle r3) {
        zzZ(r3, this.zzu.zzba().currentTimeMillis());
    }

    public final void zzZ(Bundle r12, long r13) {
        Preconditions.checkNotNull(r12);
        Bundle r0 = new Bundle(r12);
        if (TextUtils.isEmpty(r0.getString("app_id")) == true) goto L5;
        this.zzu.zzaW().zze().zza("Package name should be null when calling setConditionalUserProperty");
    L5:
        r0.remove("app_id");
        Preconditions.checkNotNull(r0);
        zzjh.zzb(r0, "app_id", String.class, null);
        zzjh.zzb(r0, "origin", String.class, null);
        zzjh.zzb(r0, "name", String.class, null);
        zzjh.zzb(r0, "value", Object.class, null);
        zzjh.zzb(r0, "trigger_event_name", String.class, null);
        zzjh.zzb(r0, "trigger_timeout", Long.class, 0L);
        zzjh.zzb(r0, "timed_out_event_name", String.class, null);
        zzjh.zzb(r0, "timed_out_event_params", Bundle.class, null);
        zzjh.zzb(r0, "triggered_event_name", String.class, null);
        zzjh.zzb(r0, "triggered_event_params", Bundle.class, null);
        zzjh.zzb(r0, "time_to_live", Long.class, 0L);
        zzjh.zzb(r0, "expired_event_name", String.class, null);
        zzjh.zzb(r0, "expired_event_params", Bundle.class, null);
        Preconditions.checkNotEmpty(r0.getString("name"));
        Preconditions.checkNotEmpty(r0.getString("origin"));
        Preconditions.checkNotNull(r0.get("value"));
        r0.putLong("creation_timestamp", r13);
        String r122 = r0.getString("name");
        Object r132 = r0.get("value");
        zzic r14 = this.zzu;
        if (r14.zzk().zzs(r122) == 0) goto L8;
        r14.zzaW().zzb().zzb("Invalid conditional user property name", r14.zzl().zzc(r122));
        return;
    L8:
        if (r14.zzk().zzM(r122, r132) != 0) goto L30;
        Object r1 = r14.zzk().zzN(r122, r132);
        if (r1 != null) goto L13;
        r14.zzaW().zzb().zzc("Unable to normalize conditional user property value", r14.zzl().zzc(r122), r132);
        return;
    L13:
        zzjh.zza(r0, r1);
        long r15 = r0.getLong("trigger_timeout");
        if (TextUtils.isEmpty(r0.getString("trigger_event_name")) == true) goto L21;
        r14.zzc();
        if (r15 <= 15552000000L) goto L18;
    L19:
        r14.zzaW().zzb().zzc("Invalid conditional user property timeout", r14.zzl().zzc(r122), Long.valueOf(r15));
        return;
    L18:
        if (r15 < 1) goto L19;
    L21:
        long r16 = r0.getLong("time_to_live");
        r14.zzc();
        if (r16 <= 15552000000L) goto L24;
    L28:
        r14.zzaW().zzb().zzc("Invalid conditional user property time to live", r14.zzl().zzc(r122), Long.valueOf(r16));
        return;
    L24:
        if (r16 < 1) goto L28;
        r14.zzaX().zzj(new zzki(this, r0));
        return;
    L30:
        r14.zzaW().zzb().zzc("Invalid conditional user property value", r14.zzl().zzc(r122), r132);
    }

    public final void zzaa(String r6, String r7, Bundle r8) {
        zzic r0 = this.zzu;
        long r1 = r0.zzba().currentTimeMillis();
        Preconditions.checkNotEmpty(r6);
        Bundle r3 = new Bundle();
        r3.putString("name", r6);
        r3.putLong("creation_timestamp", r1);
        if (r7 == null) goto L5;
        r3.putString("expired_event_name", r7);
        r3.putBundle("expired_event_params", r8);
    L5:
        r0.zzaX().zzj(new zzkj(this, r3));
    }

    public final ArrayList zzab(String r10, String r11) {
        zzic r0 = this.zzu;
        if (r0.zzaX().zze() == false) goto L6;
        r0.zzaW().zzb().zza("Cannot get conditional user properties from analytics worker thread");
        return new ArrayList(0);
    L6:
        r0.zzaV();
        if (zzae.zza() == false) goto L10;
        r0.zzaW().zzb().zza("Cannot get conditional user properties from main thread");
        return new ArrayList(0);
    L10:
        AtomicReference r4 = new AtomicReference();
        this.zzu.zzaX().zzk(r4, 5000, "get conditional user properties", new zzkk(this, r4, null, r10, r11));
        List r9 = (List) r4.get();
        if (r9 != null) goto L15;
        r0.zzaW().zzb().zzb("Timed out waiting for get conditional user properties", null);
        return new ArrayList();
    L15:
        return zzpp.zzav(r9);
    }

    public final String zzac() {
        zzlu r0 = this.zzu.zzs().zzl();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.zza;
    }

    public final String zzad() {
        zzlu r0 = this.zzu.zzs().zzl();
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.zzb;
    }

    public final String zzae() {
        return zzlt.zza(this.zzu.zzaZ(), "google_app_id", this.zzu.zzq());
    L4:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("getGoogleAppId failed with exception", e);
        return null;
    }

    public final /* synthetic */ void zzaf(SharedPreferences r1, String r2) {
        if (Objects.equals(r2, "IABTCF_TCString") == false) goto L5;
    L10:
        this.zzu.zzaW().zzk().zza("IABTCF_TCString change picked up in listener.");
        ((zzaz) Preconditions.checkNotNull(this.zzs)).zzb(500);
        return;
    L5:
        if (Objects.equals(r2, "IABTCF_gdprApplies") == true) goto L10;
        if (Objects.equals(r2, "IABTCF_EnableAdvertiserConsentMode") == true) goto L10;
    }

    public final /* synthetic */ void zzag(Bundle r14) {
        if (r14.isEmpty() == true) goto L38;
        zzic r0 = this.zzu;
        Bundle r1 = new Bundle(r0.zzd().zzt.zza());
        Iterator<String> r2 = r14.keySet().iterator();
    L5:
        int r4 = 0;
        if (r2.hasNext() == false) goto L27;
        String r3 = r2.next();
        Object r5 = r14.get(r3);
        if (r5 == null) goto L20;
        if ((r5 instanceof String) == true) goto L20;
        if ((r5 instanceof Long) == true) goto L20;
        if ((r5 instanceof Double) == true) goto L20;
        if (r0.zzk().zzz(r5) == false) goto L18;
        r0.zzk().zzP(this.zzt, null, 27, null, null, 0);
    L18:
        r0.zzaW().zzh().zzc("Invalid default event parameter type. Name, value", r3, r5);
    L20:
        if (zzpp.zzac(r3) == true) goto L21;
        if (r5 == null) goto L23;
        if (r0.zzk().zzA("param", r3, r0.zzc().zze(null, false), r5) == false) goto L5;
        r0.zzk().zzO(r1, r3, r5);
        goto L5
    L23:
        r1.remove(r3);
        goto L5
    L21:
        r0.zzaW().zzh().zzb("Invalid default event parameter name. Name", r3);
        goto L5
    L27:
        r0.zzk();
        int r142 = r0.zzc().zzc();
        if (r1.size() <= r142) goto L37;
        Iterator r22 = new TreeSet(r1.keySet()).iterator();
    L32:
        if (r22.hasNext() == false) goto L36;
        String r32 = (String) r22.next();
        r4 = r4 + 1;
        if (r4 <= r142) goto L32;
        r1.remove(r32);
        goto L32
    L36:
        r0.zzk().zzP(this.zzt, null, 26, null, null, 0);
        r0.zzaW().zzh().zza("Too many default event parameters set. Discarding beyond event parameter limit");
    L37:
        r14 = r1;
    L38:
        this.zzu.zzd().zzt.zzb(r14);
        this.zzu.zzt().zzH(r14);
    }

    public final /* synthetic */ void zzah(int r5) {
        if (this.zzk != null) goto L5;
        this.zzk = new zzjx(this, this.zzu);
    L5:
        this.zzk.zzb(r5 * 1000);
    }

    public final /* synthetic */ void zzai(Boolean r1, boolean r2) {
        zzaq(r1, true);
    }

    public final /* synthetic */ void zzaj(zzjl r7, long r8, boolean r10, boolean r11) {
        zzg();
        zzb();
        zzic r0 = this.zzu;
        zzjl r1 = r0.zzd().zzl();
        if (r8 <= this.zzp) goto L5;
    L8:
        zzhh r12 = r0.zzd();
        zzic r2 = r12.zzu;
        r12.zzg();
        int r22 = r7.zzb();
        if (r12.zzk(r22) == false) goto L17;
        zzic r3 = this.zzu;
        SharedPreferences.Editor r13 = r12.zzd().edit();
        r13.putString("consent_settings", r7.zzl());
        r13.putInt("consent_source", r22);
        r13.apply();
        r0.zzaW().zzk().zzb("Setting storage consent(FE)", r7);
        this.zzp = r8;
        if (r3.zzt().zzP() == false) goto L13;
        r3.zzt().zzk(r10);
    L14:
        if (r11 == false) goto L19;
        r3.zzt().zzC(new AtomicReference());
        return;
    L19:
        return;
    L13:
        r3.zzt().zzj(r10);
        goto L14
    L17:
        r0.zzaW().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(r7.zzb()));
        return;
    L5:
        if (zzjl.zzu(r1.zzb(), r7.zzb()) == false) goto L8;
        r0.zzaW().zzi().zzb("Dropped out-of-date consent setting, proposed settings", r7);
    }

    public final /* synthetic */ void zzak() {
        zzar();
    }

    public final /* synthetic */ void zzal(boolean r1) {
        this.zzi = false;
    }

    public final /* synthetic */ int zzam() {
        return this.zzj;
    }

    public final /* synthetic */ void zzan(int r1) {
        this.zzj = r1;
    }

    public final /* synthetic */ zzaz zzao() {
        return this.zzq;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zze() {
        return false;
    }

    public final void zzh() {
        zzic r0 = this.zzu;
        if ((r0.zzaZ().getApplicationContext() instanceof Application) == true) goto L5;
        return;
    L5:
        if (this.zza == null) goto L9;
        ((Application) r0.zzaZ().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        return;
    }

    public final Boolean zzi() {
        AtomicReference r1 = new AtomicReference();
        return (Boolean) this.zzu.zzaX().zzk(r1, 15000, "boolean test flag value", new zzke(this, r1));
    }

    public final String zzj() {
        AtomicReference r1 = new AtomicReference();
        return (String) this.zzu.zzaX().zzk(r1, 15000, "String test flag value", new zzko(this, r1));
    }

    public final Long zzk() {
        AtomicReference r1 = new AtomicReference();
        return (Long) this.zzu.zzaX().zzk(r1, 15000, "long test flag value", new zzkp(this, r1));
    }

    public final Integer zzl() {
        AtomicReference r1 = new AtomicReference();
        return (Integer) this.zzu.zzaX().zzk(r1, 15000, "int test flag value", new zzkq(this, r1));
    }

    public final Double zzm() {
        AtomicReference r1 = new AtomicReference();
        return (Double) this.zzu.zzaX().zzk(r1, 15000, "double test flag value", new zzkr(this, r1));
    }

    public final void zzn(Boolean r3) {
        zzb();
        this.zzu.zzaX().zzj(new zzks(this, r3));
    }

    public final void zzp(Bundle r9, int r10, long r11) {
        zzb();
        zzjl r0 = zzjl.zza;
        zzjk[] r02 = zzjj.zza.zzb();
        int r1 = r02.length;
        int r2 = 0;
    L3:
        Object r3 = null;
        if (r2 >= r1) goto L18;
        String r4 = r02[r2].zze;
        if (r9.containsKey(r4) == false) goto L17;
        String r42 = r9.getString(r4);
        if (r42 == null) goto L17;
        if (r42.equals("granted") == false) goto L13;
        r3 = Boolean.TRUE;
    L15:
        if (r3 != null) goto L17;
        r3 = r42;
        goto L18
    L13:
        if (r42.equals("denied") == false) goto L15;
        r3 = Boolean.FALSE;
    L17:
        r2 = r2 + 1;
    L18:
        if (r3 == null) goto L20;
        zzic r03 = this.zzu;
        r03.zzaW().zzh().zzb("Ignoring invalid consent setting", r3);
        r03.zzaW().zzh().zza("Valid consent values are 'granted', 'denied'");
    L20:
        boolean r04 = this.zzu.zzaX().zze();
        zzjl r12 = zzjl.zze(r9, r10);
        if (r12.zzc() == false) goto L23;
        zzs(r12, r04);
    L23:
        zzba r13 = zzba.zzh(r9, r10);
        if (r13.zzd() == false) goto L26;
        zzq(r13, r04);
    L26:
        Boolean r92 = zzba.zzi(r9);
        if (r92 != null) goto L29;
        return;
    L29:
        if (r10 != (-30)) goto L32;
        String r102 = "tcf";
    L31:
        String r22 = r102;
        if (r04 == false) goto L36;
        zzM(r22, "allow_personalized_ads", r92.toString(), r11);
        return;
    L36:
        zzK(r22, "allow_personalized_ads", r92.toString(), false, r11);
        return;
    L32:
        r102 = Common.ASSET_APP;
        goto L31
    }

    public final void zzq(zzba r2, boolean r3) {
        zzkt r0 = new zzkt(this, r2);
        if (r3 == false) goto L6;
        zzg();
        r0.run();
        return;
    L6:
        this.zzu.zzaX().zzj(r0);
    }

    public final void zzs(zzjl r10, boolean r11) {
        zzb();
        int r0 = r10.zzb();
        if (r0 == (-10)) goto L10;
        zzji r2 = r10.zzp();
        zzji r3 = zzji.zza;
        if (r2 != r3) goto L10;
        if (r10.zzq() != r3) goto L10;
        this.zzu.zzaW().zzh().zza("Ignoring empty consent settings");
        return;
    L10:
        Object r22 = this.zzh;
        monitor-enter(r22);
        boolean r4 = false;
        if (zzjl.zzu(r0, this.zzn.zzb()) == false) goto L23;
        boolean r32 = r10.zzr(this.zzn);     // Catch: Throwable -> L19
        zzjk r5 = zzjk.zzb;     // Catch: Throwable -> L19
        if (r10.zzo(r5) == true) goto L17;
    L21:
        r10 = r10.zzt(this.zzn);     // Catch: Throwable -> L19
        this.zzn = r10;     // Catch: Throwable -> L19
        boolean r8 = r4;
        r4 = true;
    L22:
        zzjl r52 = r10;
        monitor-exit(r22);     // Catch: Throwable -> L19
        if (r4 == true) goto L28;
        this.zzu.zzaW().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", r52);
        return;
    L28:
        long r6 = this.zzo.getAndIncrement();
        if (r32 == false) goto L36;
        this.zzg.set(null);
        zzku r33 = new zzku(this, r52, r6, r8);
        if (r11 == false) goto L34;
        zzg();
        r33.run();
        return;
    L34:
        this.zzu.zzaX().zzl(r33);
        return;
    L36:
        zzkv r34 = new zzkv(this, r52, r6, r8);
        if (r11 == false) goto L41;
        zzg();
        r34.run();
        return;
    L41:
        if (r0 == 30) goto L46;
        if (r0 == (-10)) goto L46;
        this.zzu.zzaX().zzj(r34);
        return;
    L46:
        this.zzu.zzaX().zzl(r34);
        return;
    L17:
        if (this.zzn.zzo(r5) == true) goto L21;
        r4 = true;
        goto L21
    L23:
        r32 = false;
        r8 = false;
    L19:
        th = move-exception;
        throw th;
    }

    public final void zzt(Runnable r13) {
        zzb();
        zzic r0 = this.zzu;
        if (r0.zzaX().zze() == false) goto L5;
        r0.zzaW().zzb().zza("Cannot retrieve and upload batches from analytics worker thread");
        return;
    L5:
        if (r0.zzaX().zzf() == true) goto L29;
        r0.zzaV();
        if (zzae.zza() == true) goto L27;
        r0.zzaW().zzk().zza("[sgtm] Started client-side batch upload work.");
        boolean r2 = false;
        int r3 = 0;
        int r4 = 0;
    L9:
        if (r2 == true) goto L25;
        r0.zzaW().zzk().zza("[sgtm] Getting upload batches from service (FE)");
        AtomicReference r7 = new AtomicReference();
        r0.zzaX().zzk(r7, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, "[sgtm] Getting upload batches", new zzli(this, r7));
        zzoq r22 = (zzoq) r7.get();
        if (r22 == null) goto L25;
        List r23 = r22.zza;
        if (r23.isEmpty() == true) goto L25;
        r0.zzaW().zzk().zzb("[sgtm] Retrieved upload batches. count", Integer.valueOf(r23.size()));
        r3 = r3 + r23.size();
        Iterator r24 = r23.iterator();
    L17:
        if (r24.hasNext() == false) goto L24;
        zzlr r5 = zzap((zzom) r24.next());
        if (r5 == zzlr.zzb) goto L20;
        if (r5 != zzlr.zzd) goto L17;
        r2 = true;
        goto L9
    L20:
        r4 = r4 + 1;
        goto L17
    L24:
        r2 = false;
    L25:
        r0.zzaW().zzk().zzc("[sgtm] Completed client-side batch upload work. total, success", Integer.valueOf(r3), Integer.valueOf(r4));
        r13.run();
        return;
    L27:
        r0.zzaW().zzb().zza("Cannot retrieve and upload batches from main thread");
        return;
    L29:
        r0.zzaW().zzb().zza("Cannot retrieve and upload batches from analytics network thread");
    }

    public final void zzu(long r3) {
        zzg();
        if (this.zzl != null) goto L5;
        this.zzl = new zzju(this, this.zzu);
    L5:
        this.zzl.zzb(r3);
    }

    public final void zzv() {
        zzg();
        zzaz r0 = this.zzl;
        if (r0 == null) goto L6;
        r0.zzd();
        return;
    }

    public final void zzw() {
        zzaif.zza();
        zzic r0 = this.zzu;
        if (r0.zzc().zzp(null, zzfy.zzaP) == true) goto L5;
        return;
    L5:
        if (r0.zzaX().zze() == true) goto L16;
        r0.zzaV();
        if (zzae.zza() == true) goto L14;
        zzb();
        r0.zzaW().zzk().zza("Getting trigger URIs (FE)");
        AtomicReference r4 = new AtomicReference();
        r0.zzaX().zzk(r4, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, "get trigger URIs", new zzla(this, r4));
        List r1 = (List) r4.get();
        if (r1 != null) goto L12;
        r0.zzaW().zzd().zza("Timed out waiting for get trigger URIs");
        return;
    L12:
        r0.zzaX().zzj(new zzlb(this, r1));
        return;
    L14:
        r0.zzaW().zzb().zza("Cannot get trigger URIs from main thread");
        return;
    L16:
        r0.zzaW().zzb().zza("Cannot get trigger URIs from analytics worker thread");
    }

    @TargetApi(30)
    public final PriorityQueue zzx() {
        if (this.zzm != null) goto L6;
        this.zzm = new PriorityQueue(Comparator.comparing(zzlc.zza, zzld.zza));
    L6:
        return this.zzm;
    }

    @TargetApi(30)
    public final void zzy() {
        zzg();
        if (zzx().isEmpty() == false) goto L5;
        return;
    L5:
        if (this.zzi == true) goto L20;
        zzoh r0 = (zzoh) zzx().poll();
        if (r0 == null) goto L18;
        zzic r1 = this.zzu;
        MeasurementManagerFutures r2 = r1.zzk().zzV();
        if (r2 == null) goto L19;
        this.zzi = true;
        zzgs r12 = r1.zzaW().zzk();
        String r3 = r0.zza;
        r12.zzb("Registering trigger URI", r3);
        ListenableFuture<Unit> r13 = r2.registerTriggerAsync(Uri.parse(r3));
        if (r13 != null) goto L15;
        this.zzi = false;
        zzx().add(r0);
        return;
    L15:
        zzjv r22 = new zzjv(this);
        Futures.addCallback(r13, new zzjw(this, r0), r22);
        return;
    L19:
        return;
    L18:
        return;
    }

    public final void zzz(zzjl r6) {
        zzg();
        if (r6.zzo(zzjk.zzb) == false) goto L9;
        if (r6.zzo(zzjk.zza) == false) goto L9;
    L7:
        boolean r62 = true;
    L12:
        zzic r0 = this.zzu;
        if (r62 == r0.zzE()) goto L24;
        r0.zzD(r62);
        zzhh r02 = this.zzu.zzd();
        zzic r3 = r02.zzu;
        r02.zzg();
        if (r02.zzd().contains("measurement_enabled_from_api") == false) goto L17;
        Boolean r03 = Boolean.valueOf(r02.zzd().getBoolean("measurement_enabled_from_api", true));
    L18:
        if (r62 == false) goto L22;
        if (r03 == null) goto L22;
        if (r03.booleanValue() == true) goto L22;
        return;
    L22:
        zzaq(Boolean.valueOf(r62), false);
        return;
    L17:
        r03 = null;
        goto L18
    L24:
        return;
    L9:
        if (this.zzu.zzt().zzO() == true) goto L7;
        r62 = false;
        goto L12
    }
}
