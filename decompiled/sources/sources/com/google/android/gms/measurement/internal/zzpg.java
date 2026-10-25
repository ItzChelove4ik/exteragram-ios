package com.google.android.gms.measurement.internal;

import android.app.BroadcastOptions;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.android.dx.io.Opcodes;
import com.chaquo.python.internal.Common;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzaeh;
import com.google.android.gms.internal.measurement.zzagr;
import com.google.android.gms.internal.measurement.zzahh;
import com.google.android.gms.internal.measurement.zzahk;
import com.google.android.gms.internal.measurement.zzaif;
import com.google.android.gms.internal.measurement.zzair;
import com.google.android.gms.internal.measurement.zzhv;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.GZIPInputStream;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzpg implements zzjg {
    private static volatile zzpg zzb;
    private List zzA;
    private long zzB;
    private final Map zzC;
    private final Map zzD;
    private final Map zzE;
    private final Map zzF;
    private zzlu zzG;
    private String zzH;
    private zzaz zzI;
    private long zzJ;
    private final zzpo zzK;
    long zza;
    private final zzht zzc;
    private final zzgz zzd;
    private zzaw zze;
    private zzhb zzf;
    private zzok zzg;
    private zzad zzh;
    private final zzpk zzi;
    private zzlp zzj;
    private zznn zzk;
    private final zzou zzl;
    private zzhk zzm;
    private final zzic zzn;
    private final AtomicBoolean zzo;
    private boolean zzp;
    private List zzq;
    private final Deque zzr;
    private int zzs;
    private int zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private FileLock zzx;
    private FileChannel zzy;
    private List zzz;

    public zzpg(zzph r3, zzic r4) {
        this.zzo = new AtomicBoolean(false);
        this.zzr = new LinkedList();
        this.zzF = new HashMap();
        this.zzK = new zzpb(this);
        Preconditions.checkNotNull(r3);
        this.zzn = zzic.zzy(r3.zza, null, null, null);
        this.zzB = -1;
        this.zzl = new zzou(this);
        zzpk r42 = new zzpk(this);
        r42.zzaz();
        this.zzi = r42;
        zzgz r43 = new zzgz(this);
        r43.zzaz();
        this.zzd = r43;
        zzht r44 = new zzht(this);
        r44.zzaz();
        this.zzc = r44;
        this.zzC = new HashMap();
        this.zzD = new HashMap();
        this.zzE = new HashMap();
        zzaX().zzj(new zzov(this, r3));
    }

    public static zzpg zza(Context r3) {
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r3.getApplicationContext());
        if (zzb != null) goto L16;
        monitor-enter(zzpg.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (zzb != null) goto L11;
        zzb = new zzpg((zzph) Preconditions.checkNotNull(new zzph(r3)), null);     // Catch: Throwable -> L9
    L11:
        monitor-exit(zzpg.class);     // Catch: Throwable -> L9
    L16:
        return zzb;
    }

    public static final void zzaB(com.google.android.gms.internal.measurement.zzhr r4, int r5, String r6) {
        List r0 = r4.zza();
        int r1 = 0;
    L4:
        if (r1 >= r0.size()) goto L9;
        if ("_err".equals(((com.google.android.gms.internal.measurement.zzhw) r0.get(r1)).zzb()) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return;
    L9:
        zzhv r02 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r02.zzb("_err");
        r02.zzf(r5);
        com.google.android.gms.internal.measurement.zzhw r52 = (com.google.android.gms.internal.measurement.zzhw) r02.zzbd();
        zzhv r03 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r03.zzb("_ev");
        r03.zzd(r6);
        com.google.android.gms.internal.measurement.zzhw r62 = (com.google.android.gms.internal.measurement.zzhw) r03.zzbd();
        r4.zzf(r52);
        r4.zzf(r62);
    }

    public static final void zzaC(com.google.android.gms.internal.measurement.zzhr r3, String r4) {
        List r0 = r3.zza();
        int r1 = 0;
    L4:
        if (r1 >= r0.size()) goto L10;
        if (r4.equals(((com.google.android.gms.internal.measurement.zzhw) r0.get(r1)).zzb()) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        r3.zzj(r1);
        return;
    }

    private final int zzaD(String r6, zzan r7) {
        zzht r0 = this.zzc;
        if (r0.zzy(r6) != null) goto L6;
        r7.zzc(zzjk.zzd, zzam.zzj);
        return 1;
    L6:
        zzh r5 = zzj().zzu(r6);
        if (r5 != null) goto L9;
    L16:
        zzjk r52 = zzjk.zzd;
        r7.zzc(r52, zzam.zzb);
        if (r0.zzw(r6, r52) == false) goto L19;
        return 0;
    L19:
        return 1;
    L9:
        if (zze.zzc(r5.zzaH()).zza() != zzji.zzb) goto L16;
        zzjk r53 = zzjk.zzd;
        zzji r3 = r0.zzB(r6, r53);
        if (r3 == zzji.zza) goto L16;
        r7.zzc(r53, zzam.zzi);
        if (r3 != zzji.zzd) goto L15;
        return 0;
    L15:
        return 1;
    }

    private final Map zzaE(com.google.android.gms.internal.measurement.zzhs r3) {
        HashMap r0 = new HashMap();
        zzp();
        Iterator r2 = zzpk.zzL(r3, "gad_").entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        Map.Entry r32 = (Map.Entry) r2.next();
        r0.put((String) r32.getKey(), String.valueOf(r32.getValue()));
        goto L4
    L6:
        return r0;
    }

    private final zzaz zzaF() {
        if (this.zzI != null) goto L6;
        this.zzI = new zzoy(this, this.zzn);
    L6:
        return this.zzI;
    }

    private final void zzaG() {
        zzaX().zzg();
        if (this.zzr.isEmpty() == false) goto L5;
        return;
    L5:
        if (zzaF().zzc() == true) goto L9;
        long r0 = Math.max(0, ((Integer) zzfy.zzaA.zzb(null)).intValue() - (zzba().elapsedRealtime() - this.zzJ));
        zzaW().zzk().zzb("Scheduling notify next app runnable, delay in ms", Long.valueOf(r0));
        zzaF().zzb(r0);
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v138 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v49, types: [com.google.android.gms.measurement.internal.zzpg] */
    private final boolean zzaH(String r45, long r46) {
        zzpg r1 = this;
        zzpg r2 = "_ai";
        String r3 = "purchase";
        String r4 = PluginsConstants.Settings.ITEMS;
        Long r7 = 1L;
        r1.zzj().zzb();
        zzpc r15 = new zzpc(r1, null);     // Catch: Throwable -> L23
        r1.zzj().zzav(r45, r46, r1.zzB, r15);     // Catch: Throwable -> L23
        List r9 = r15.zzc;     // Catch: Throwable -> L23
        if (r9 != null) goto L6;
    L7:
        zzpg r22 = r1;
        r22.zzj().zzc();     // Catch: Throwable -> L454
        boolean r10 = false;
        zzpg r23 = r22;
    L480:
        r23.zzj().zzd();
        return r10;
    L6:
        if (r9.isEmpty() == true) goto L7;
        com.google.android.gms.internal.measurement.zzic r92 = (com.google.android.gms.internal.measurement.zzic) r15.zza.zzco();     // Catch: Throwable -> L23
        r92.zzi();     // Catch: Throwable -> L23
        com.google.android.gms.internal.measurement.zzhr r16 = null;
        com.google.android.gms.internal.measurement.zzhr r17 = null;
        int r102 = -1;
        int r11 = -1;
        int r12 = 0;
        int r13 = 0;
        boolean r14 = false;
        boolean r18 = false;
        String r24 = "_ai";
    L10:
        int r47 = r13;
        Long r21 = r7;
        if (r12 >= r15.zzc.size()) goto L229;
        com.google.android.gms.internal.measurement.zzhr r5 = (com.google.android.gms.internal.measurement.zzhr) ((com.google.android.gms.internal.measurement.zzhs) r15.zzc.get(r12)).zzco();     // Catch: Throwable -> L23
        boolean r26 = r14;
        if (r1.zzh().zzj(r15.zza.zzA(), r5.zzk()) == false) goto L27;
        int r27 = r12;
        r1.zzaW().zze().zzc("Dropping blocked raw event. appId", zzgu.zzl(r15.zza.zzA()), r1.zzn.zzl().zza(r5.zzk()));     // Catch: Throwable -> L23
        if (r1.zzh().zzo(r15.zza.zzA()) == false) goto L18;
    L25:
        r13 = r47;
        String r32 = r24;
        String r28 = r3;
        String r8 = r4;
        com.google.android.gms.internal.measurement.zzic r6 = r92;
        int r42 = r27;
    L26:
        r14 = r26;
        r12 = r42 + 1;     // Catch: Throwable -> L23
        r92 = r6;
        r4 = r8;
        r7 = r21;
        r3 = r28;
        r24 = r32;
        goto L10
    L18:
        if (r1.zzh().zzp(r15.zza.zzA()) == true) goto L25;
        if ("_err".equals(r5.zzk()) == true) goto L25;
        r1.zzt().zzP(r1.zzK, r15.zza.zzA(), 11, "_ev", r5.zzk(), 0);     // Catch: Throwable -> L23
        goto L25
    L27:
        int r272 = r12;
        String r62 = r5.zzk();     // Catch: Throwable -> L23
        String r29 = r4;
        if (r62.equals(r3) == false) goto L31;
    L39:
        com.google.android.gms.internal.measurement.zzic r30 = r92;
        int r31 = r102;
        int r282 = r11;
    L40:
        zzhv r63 = com.google.android.gms.internal.measurement.zzhw.zzn();     // Catch: Throwable -> L23
        r63.zzb("_ct");     // Catch: Throwable -> L23
        if (r26 == true) goto L49;
        String r93 = r15.zza.zzA();     // Catch: Throwable -> L23
        if (r1.zzaQ(r93, r3) == false) goto L49;
        if (r1.zzaQ(r93, "_iap") == false) goto L49;
        if (r1.zzaQ(r93, "ecommerce_purchase") == false) goto L49;
        String r94 = "new";
    L50:
        r63.zzd(r94);     // Catch: Throwable -> L23
        r5.zzf((com.google.android.gms.internal.measurement.zzhw) r63.zzbd());     // Catch: Throwable -> L23
        r26 = true;
    L52:
        if (r5.zzk().equals(zzjm.zza(r24)) == false) goto L66;
        r5.zzl(r24);     // Catch: Throwable -> L23
        r1.zzaW().zzk().zza("Renaming ad_impression to _ai");     // Catch: Throwable -> L23
        if (Log.isLoggable(r1.zzaW().zzn(), 5) == false) goto L66;
        int r64 = 0;
    L57:
        if (r64 >= r5.zzb()) goto L66;
        if ("ad_platform".equals(r5.zzc(r64).zzb()) == false) goto L65;
        if (r5.zzc(r64).zzd().isEmpty() == true) goto L65;
        if ("admob".equalsIgnoreCase(r5.zzc(r64).zzd()) == false) goto L65;
        r1.zzaW().zzh().zza("AdMob ad impression logged from app. Potentially duplicative.");     // Catch: Throwable -> L23
    L65:
        r64 = r64 + 1;     // Catch: Throwable -> L23
    L66:
        zzal r65 = r1.zzd();     // Catch: Throwable -> L23
        zzfx r95 = zzfy.zzbf;     // Catch: Throwable -> L23
        if (r65.zzp(null, r95) == true) goto L69;
    L71:
        boolean r66 = r1.zzh().zzk(r15.zza.zzA(), r5.zzk());     // Catch: Throwable -> L23
        if (r1.zzd().zzp(null, r95) == true) goto L74;
    L90:
        if (r66 == true) goto L97;
        r1.zzp();     // Catch: Throwable -> L23
        String r43 = r5.zzk();     // Catch: Throwable -> L23
        Preconditions.checkNotEmpty(r43);     // Catch: Throwable -> L23
        if (r43.hashCode() == 95027) goto L96;
    L98:
        r32 = r24;
        String r96 = r3;
        r66 = false;
    L141:
        if (r66 == false) goto L144;
        r1.zzV(r5);     // Catch: Throwable -> L23
    L144:
        if ("_e".equals(r5.zzk()) == false) goto L156;
        r1.zzp();     // Catch: Throwable -> L23
        if (zzpk.zzI((com.google.android.gms.internal.measurement.zzhs) r5.zzbd(), "_fr") != null) goto L154;
        if (r17 != null) goto L149;
    L153:
        com.google.android.gms.internal.measurement.zzic r67 = r30;
        r16 = r5;
        r102 = r31;
        r11 = r47;
    L186:
        if (r1.zzd().zzp(null, zzfy.zzbe) == false) goto L196;
        if (r5.zzu() == false) goto L196;
        if (r5.zzs() == true) goto L196;
        long r25 = r1.zzp().zzE(r5.zzv());     // Catch: Throwable -> L23
        if (r25 == 0) goto L194;
        r5.zzt(r25);     // Catch: Throwable -> L23
    L194:
        r5.zzw(0);     // Catch: Throwable -> L23
    L196:
        if (r5.zzb() == 0) goto L226;
        r1.zzp();     // Catch: Throwable -> L23
        Bundle r210 = zzpk.zzH(r5.zza());     // Catch: Throwable -> L23
        int r33 = 0;
    L199:
        if (r33 >= r5.zzb()) goto L217;
        com.google.android.gms.internal.measurement.zzhw r44 = r5.zzc(r33);     // Catch: Throwable -> L23
        String r82 = r29;
        if (r44.zzb().equals(r82) == true) goto L203;
    L213:
        int r232 = r33;
        String r283 = r96;
        if (r44.zzb().equals(r82) == true) goto L216;
        r1.zzU(r5.zzk(), (zzhv) r44.zzco(), r210, r15.zza.zzA());     // Catch: Throwable -> L23
    L216:
        r33 = r232 + 1;     // Catch: Throwable -> L23
        r29 = r82;
        r96 = r283;
        goto L199
    L203:
        if (r44.zzl().isEmpty() == true) goto L213;
        String r72 = r15.zza.zzA();     // Catch: Throwable -> L23
        List r48 = r44.zzl();     // Catch: Throwable -> L23
        Bundle[] r122 = new Bundle[r48.size()];     // Catch: Throwable -> L23
        int r132 = 0;
    L206:
        if (r132 >= r48.size()) goto L212;
        com.google.android.gms.internal.measurement.zzhw r142 = (com.google.android.gms.internal.measurement.zzhw) r48.get(r132);     // Catch: Throwable -> L23
        r1.zzp();     // Catch: Throwable -> L23
        int r233 = r33;
        Bundle r34 = zzpk.zzH(r142.zzl());     // Catch: Throwable -> L23
        Iterator r143 = r142.zzl().iterator();     // Catch: Throwable -> L23
    L209:
        if (r143.hasNext() == false) goto L211;
        r1.zzU(r5.zzk(), (zzhv) ((com.google.android.gms.internal.measurement.zzhw) r143.next()).zzco(), r34, r72);     // Catch: Throwable -> L23
        r48 = r48;
        r96 = r96;
        goto L209
    L211:
        r122[r132] = r34;     // Catch: Throwable -> L23
        r132 = r132 + 1;     // Catch: Throwable -> L23
        r33 = r233;
        r48 = r48;
        r96 = r96;
        goto L206
    L212:
        r232 = r33;
        r283 = r96;
        r210.putParcelableArray(r82, r122);     // Catch: Throwable -> L23
        goto L216
    L217:
        r28 = r96;
        r8 = r29;
        r5.zzi();     // Catch: Throwable -> L23
        zzpk r35 = r1.zzp();     // Catch: Throwable -> L23
        ArrayList r49 = new ArrayList();     // Catch: Throwable -> L23
        Iterator<String> r73 = r210.keySet().iterator();     // Catch: Throwable -> L23
    L219:
        if (r73.hasNext() == false) goto L223;
        String r97 = r73.next();     // Catch: Throwable -> L23
        zzhv r123 = com.google.android.gms.internal.measurement.zzhw.zzn();     // Catch: Throwable -> L23
        r123.zzb(r97);     // Catch: Throwable -> L23
        Object r98 = r210.get(r97);     // Catch: Throwable -> L23
        if (r98 == null) goto L219;
        r35.zzd(r123, r98);     // Catch: Throwable -> L23
        r49.add((com.google.android.gms.internal.measurement.zzhw) r123.zzbd());     // Catch: Throwable -> L23
        goto L219
    L223:
        int r211 = r49.size();     // Catch: Throwable -> L23
        int r36 = 0;
    L224:
        if (r36 >= r211) goto L227;
        Object r74 = r49.get(r36);     // Catch: Throwable -> L23
        r36 = r36 + 1;     // Catch: Throwable -> L23
        r5.zzf((com.google.android.gms.internal.measurement.zzhw) r74);     // Catch: Throwable -> L23
    L227:
        r42 = r272;
        r15.zzc.set(r42, (com.google.android.gms.internal.measurement.zzhs) r5.zzbd());     // Catch: Throwable -> L23
        r67.zzg(r5);     // Catch: Throwable -> L23
        r13 = r47 + 1;     // Catch: Throwable -> L23
        r6 = r67;
        goto L26
    L226:
        r28 = r96;
        r8 = r29;
        goto L227
    L149:
        if (Math.abs(r17.zzn() - r5.zzn()) > 1000) goto L153;
        com.google.android.gms.internal.measurement.zzhr r212 = (com.google.android.gms.internal.measurement.zzhr) r17.zzbb();     // Catch: Throwable -> L23
        if (r1.zzaJ(r5, r212) == false) goto L153;
        com.google.android.gms.internal.measurement.zzic r68 = r30;
        int r112 = r31;
        r68.zzf(r112, r212);     // Catch: Throwable -> L23
        r102 = r112;
        r11 = r282;
        r16 = null;
        r17 = null;
        r67 = r68;
        goto L186
    L154:
        com.google.android.gms.internal.measurement.zzic r69 = r30;
        int r113 = r31;
    L155:
        int r37 = r282;
    L184:
        r102 = r113;
        com.google.android.gms.internal.measurement.zzic r610 = r69;
    L166:
        r11 = r37;
        r67 = r610;
        goto L186
    L156:
        r69 = r30;
        r113 = r31;
        if ("_vs".equals(r5.zzk()) == false) goto L168;
        r1.zzp();     // Catch: Throwable -> L23
        r69 = r69;
        if (zzpk.zzI((com.google.android.gms.internal.measurement.zzhs) r5.zzbd(), "_et") != null) goto L155;
        if (r16 != null) goto L162;
    L167:
        r102 = r47;
        r11 = r282;
        r17 = r5;
        r67 = r69;
        goto L186
    L162:
        if (Math.abs(r16.zzn() - r5.zzn()) > 1000) goto L167;
        com.google.android.gms.internal.measurement.zzhr r213 = (com.google.android.gms.internal.measurement.zzhr) r16.zzbb();     // Catch: Throwable -> L23
        if (r1.zzaJ(r213, r5) == false) goto L167;
        r37 = r282;
        r69.zzf(r37, r213);     // Catch: Throwable -> L23
        r102 = r113;
        r16 = null;
        r17 = null;
        r610 = r69;
        goto L166
    L168:
        r37 = r282;
        if ("_f".equals(r5.zzk()) == true) goto L174;
        if ("_v".equals(r5.zzk()) == false) goto L184;
    L174:
        if ("_f".equals(r5.zzk()) == false) goto L176;
    L177:
        int r214 = 0;
    L179:
        if (r214 >= r5.zzb()) goto L184;
        com.google.android.gms.internal.measurement.zzhw r410 = r5.zzc(r214);     // Catch: Throwable -> L23
        if ("_elt".equals(r410.zzb()) == true) goto L182;
        r214 = r214 + 1;     // Catch: Throwable -> L23
        goto L179
    L182:
        r5.zzr(r410.zzf());     // Catch: Throwable -> L23
        r5.zzj(r214);     // Catch: Throwable -> L23
        goto L184
    L176:
        if ("_v".equals(r5.zzk()) == false) goto L184;
    L96:
        if (r43.equals("_ui") == false) goto L98;
    L97:
        int r411 = 0;
        boolean r99 = false;
        boolean r103 = false;
    L101:
        if (r411 >= r5.zzb()) goto L109;
        if ("_c".equals(r5.zzc(r411).zzb()) == false) goto L106;
        zzhv r910 = (zzhv) r5.zzc(r411).zzco();     // Catch: Throwable -> L23
        r910.zzf(1);     // Catch: Throwable -> L23
        r5.zzd(r411, (com.google.android.gms.internal.measurement.zzhw) r910.zzbd());     // Catch: Throwable -> L23
        r99 = true;
    L108:
        r411 = r411 + 1;     // Catch: Throwable -> L23
        goto L101
    L106:
        if ("_r".equals(r5.zzc(r411).zzb()) == false) goto L108;
        zzhv r104 = (zzhv) r5.zzc(r411).zzco();     // Catch: Throwable -> L23
        r104.zzf(1);     // Catch: Throwable -> L23
        r5.zzd(r411, (com.google.android.gms.internal.measurement.zzhw) r104.zzbd());     // Catch: Throwable -> L23
        r103 = true;
        goto L108
    L109:
        if (r99 == true) goto L112;
        if (r66 == false) goto L112;
        r32 = r24;
        r1.zzaW().zzk().zzb("Marking event as conversion", r1.zzn.zzl().zza(r5.zzk()));     // Catch: Throwable -> L23
        zzhv r215 = com.google.android.gms.internal.measurement.zzhw.zzn();     // Catch: Throwable -> L23
        r215.zzb("_c");     // Catch: Throwable -> L23
        r96 = r3;
        r215.zzf(1);     // Catch: Throwable -> L23
        r5.zzg(r215);     // Catch: Throwable -> L23
    L113:
        if (r103 == true) goto L116;
        r1.zzaW().zzk().zzb("Marking event as real-time", r1.zzn.zzl().zza(r5.zzk()));     // Catch: Throwable -> L23
        zzhv r216 = com.google.android.gms.internal.measurement.zzhw.zzn();     // Catch: Throwable -> L23
        r216.zzb("_r");     // Catch: Throwable -> L23
        r216.zzf(1);     // Catch: Throwable -> L23
        r5.zzg(r216);     // Catch: Throwable -> L23
    L116:
        if (r1.zzj().zzw(r1.zzC(), r15.zza.zzA(), false, false, false, false, true, false, false).zze <= r1.zzd().zzm(r15.zza.zzA(), zzfy.zzo)) goto L118;
        zzaC(r5, "_r");     // Catch: Throwable -> L23
    L120:
        if (zzpp.zzh(r5.zzk()) == false) goto L141;
        if (r66 == false) goto L141;
        if (r1.zzj().zzw(r1.zzC(), r15.zza.zzA(), false, false, true, false, false, false, false).zzc <= r1.zzd().zzm(r15.zza.zzA(), zzfy.zzn)) goto L141;
        r1.zzaW().zze().zzb("Too many conversions. Not logging as conversion. appId", zzgu.zzl(r15.zza.zzA()));     // Catch: Throwable -> L23
        int r217 = 0;
        boolean r38 = false;
        zzhv r412 = null;
        int r105 = -1;
    L126:
        if (r217 >= r5.zzb()) goto L134;
        com.google.android.gms.internal.measurement.zzhw r114 = r5.zzc(r217);     // Catch: Throwable -> L23
        if ("_c".equals(r114.zzb()) == false) goto L131;
        r412 = (zzhv) r114.zzco();     // Catch: Throwable -> L23
        r105 = r217;
    L133:
        r217 = r217 + 1;     // Catch: Throwable -> L23
        goto L126
    L131:
        if ("_err".equals(r114.zzb()) == false) goto L133;
        r38 = true;
        goto L133
    L134:
        if (r38 == false) goto L138;
        if (r412 == null) goto L137;
        r5.zzj(r105);     // Catch: Throwable -> L23
        goto L141
    L137:
        r412 = null;
    L138:
        if (r412 == null) goto L140;
        zzhv r218 = (zzhv) r412.zzbb();     // Catch: Throwable -> L23
        r218.zzb("_err");     // Catch: Throwable -> L23
        r218.zzf(10);     // Catch: Throwable -> L23
        r5.zzd(r105, (com.google.android.gms.internal.measurement.zzhw) r218.zzbd());     // Catch: Throwable -> L23
        goto L141
    L140:
        r1.zzaW().zzb().zzb("Did not find conversion parameter. appId", zzgu.zzl(r15.zza.zzA()));     // Catch: Throwable -> L23
        goto L141
    L118:
        r18 = true;
    L112:
        r32 = r24;
        r96 = r3;
        goto L113
    L74:
        if ("_iap".equals(r5.zzk()) == false) goto L90;
        r66 = r1.zzV(r5);     // Catch: Throwable -> L23
        String r911 = r15.zza.zzA();     // Catch: Throwable -> L23
        if ("_iap".equals(r5.zzk()) == false) goto L79;
        r1.zzaK(r5, "value", r911);     // Catch: Throwable -> L23
        r1.zzaK(r5, "price", r911);     // Catch: Throwable -> L23
    L79:
        if ("_iap".equals(r5.zzk()) == false) goto L90;
        ArrayList r413 = new ArrayList(r5.zza());     // Catch: Throwable -> L23
        int r912 = 0;
    L84:
        if (r912 >= r413.size()) goto L89;
        if ("quantity".equals(((com.google.android.gms.internal.measurement.zzhw) r413.get(r912)).zzb()) == true) goto L90;
        r912 = r912 + 1;     // Catch: Throwable -> L23
        goto L84
    L89:
        zzhv r414 = com.google.android.gms.internal.measurement.zzhw.zzn();     // Catch: Throwable -> L23
        r414.zzb("quantity");     // Catch: Throwable -> L23
        r414.zzf(1);     // Catch: Throwable -> L23
        r5.zzf((com.google.android.gms.internal.measurement.zzhw) r414.zzbd());     // Catch: Throwable -> L23
        goto L90
    L69:
        if (r5.zzk().equals("in_app_purchase") == false) goto L71;
        r5.zzl("_iap");     // Catch: Throwable -> L23
        r1.zzaW().zzk().zza("Renaming in_app_purchase to _iap");     // Catch: Throwable -> L23
    L49:
        r94 = "returning";
        goto L50
    L31:
        if (r62.equals("_iap") == true) goto L39;
        if (r62.equals("ecommerce_purchase") == true) goto L39;
        r282 = r11;
        r30 = r92;
        r31 = r102;
        if (r1.zzd().zzp(null, zzfy.zzbf) == false) goto L52;
        if (r62.equals("in_app_purchase") == false) goto L52;
    L229:
        com.google.android.gms.internal.measurement.zzic r611 = r92;
        int r219 = r47;
        int r0 = 0;
        long r39 = 0;
    L230:
        if (r0 >= r219) goto L247;
        com.google.android.gms.internal.measurement.zzhs r52 = r611.zzd(r0);     // Catch: Throwable -> L23
        if ("_e".equals(r52.zzd()) == false) goto L236;
        r1.zzp();     // Catch: Throwable -> L23
        if (zzpk.zzI(r52, "_fr") == null) goto L236;
        r611.zzj(r0);     // Catch: Throwable -> L23
        r219 = r219 - 1;
        r0 = r0 - 1;
    L246:
        r0 = r0 + 1;     // Catch: Throwable -> L23
    L236:
        r1.zzp();     // Catch: Throwable -> L23
        com.google.android.gms.internal.measurement.zzhw r53 = zzpk.zzI(r52, "_et");     // Catch: Throwable -> L23
        if (r53 == null) goto L246;
        if (r53.zze() == false) goto L241;
        Long r54 = Long.valueOf(r53.zzf());     // Catch: Throwable -> L23
    L242:
        if (r54 == null) goto L246;
        if (r54.longValue() <= 0) goto L246;
        r39 = r39 + r54.longValue();     // Catch: Throwable -> L23
        goto L246
    L241:
        r54 = null;
        goto L242
    L247:
        r1.zzaI(r611, r39, false);     // Catch: Throwable -> L23
        Iterator r02 = r611.zzb().iterator();     // Catch: Throwable -> L23
    L250:
        if (r02.hasNext() == false) goto L255;
        if ("_s".equals(((com.google.android.gms.internal.measurement.zzhs) r02.next()).zzd()) == false) goto L250;
        r1.zzj().zzk(r611.zzK(), "_se");     // Catch: Throwable -> L23
    L255:
        if (zzpk.zzx(r611, "_sid") < 0) goto L257;
        r1.zzaI(r611, r39, true);     // Catch: Throwable -> L23
    L260:
        String r03 = r15.zza.zzA();     // Catch: Throwable -> L23
        r1.zzaX().zzg();     // Catch: Throwable -> L23
        r1.zzu();     // Catch: Throwable -> L23
        zzh r220 = r1.zzj().zzu(r03);     // Catch: Throwable -> L23
        if (r220 != null) goto L263;
        r1.zzaW().zzb().zzb("Cannot fix consent fields without appInfo. appId", zzgu.zzl(r03));     // Catch: Throwable -> L23
    L264:
        String r04 = r15.zza.zzA();     // Catch: Throwable -> L23
        r1.zzaX().zzg();     // Catch: Throwable -> L23
        r1.zzu();     // Catch: Throwable -> L23
        zzh r221 = r1.zzj().zzu(r04);     // Catch: Throwable -> L23
        if (r221 != null) goto L267;
        r1.zzaW().zze().zzb("Cannot populate ad_campaign_info without appInfo. appId", zzgu.zzl(r04));     // Catch: Throwable -> L23
    L268:
        r611.zzv(LongCompanionObject.MAX_VALUE);     // Catch: Throwable -> L23
        r611.zzx(Long.MIN_VALUE);     // Catch: Throwable -> L23
        int r05 = 0;
    L270:
        if (r05 >= r611.zzc()) goto L278;
        com.google.android.gms.internal.measurement.zzhs r222 = r611.zzd(r05);     // Catch: Throwable -> L23
        if (r222.zzf() >= r611.zzu()) goto L275;
        r611.zzv(r222.zzf());     // Catch: Throwable -> L23
    L275:
        if (r222.zzf() <= r611.zzw()) goto L277;
        r611.zzx(r222.zzf());     // Catch: Throwable -> L23
    L277:
        r05 = r05 + 1;     // Catch: Throwable -> L23
        goto L270
    L278:
        r611.zzak();     // Catch: Throwable -> L23
        zzjl r06 = zzjl.zza;     // Catch: Throwable -> L23
        zzjl r07 = r1.zzB(r15.zza.zzA()).zzs(zzjl.zzf(r15.zza.zzaf(), 100));     // Catch: Throwable -> L23
        zzjl r223 = r1.zzj().zzaf(r15.zza.zzA());     // Catch: Throwable -> L23
        r1.zzj().zzae(r15.zza.zzA(), r07);     // Catch: Throwable -> L23
        zzjk r310 = zzjk.zzb;     // Catch: Throwable -> L23
        if (r07.zzo(r310) == true) goto L284;
        if (r223.zzo(r310) == false) goto L284;
        r1.zzj().zzi(r15.zza.zzA());     // Catch: Throwable -> L23
    L288:
        zzjk r224 = zzjk.zza;     // Catch: Throwable -> L23
        if (r07.zzo(r224) == true) goto L292;
        r611.zzR();     // Catch: Throwable -> L23
        r611.zzU();     // Catch: Throwable -> L23
        r611.zzan();     // Catch: Throwable -> L23
    L292:
        if (r07.zzo(r310) == true) goto L294;
        r611.zzX();     // Catch: Throwable -> L23
        r611.zzav();     // Catch: Throwable -> L23
    L294:
        zzaif.zza();     // Catch: Throwable -> L23
        if (r1.zzd().zzp(r15.zza.zzA(), zzfy.zzaO) == true) goto L297;
    L303:
        r611.zzag();     // Catch: Throwable -> L23
        r611.zzaf(r1.zzm().zzb(r611.zzK(), r611.zzb(), r611.zzk(), Long.valueOf(r611.zzu()), Long.valueOf(r611.zzw()), !r07.zzo(r310)));     // Catch: Throwable -> L23
        if (r1.zzd().zzD(r15.zza.zzA()) == true) goto L489;
        r10 = true;
    L417:
        String r19 = r15.zza.zzA();     // Catch: Throwable -> L355
        zzh r08 = zzj().zzu(r19);     // Catch: Throwable -> L355
        if (r08 != null) goto L421;
        zzaW().zzb().zzb("Bundling raw events w/o app info. appId", zzgu.zzl(r15.zza.zzA()));     // Catch: Throwable -> L355
    L439:
        if (r611.zzc() <= 0) goto L471;
        zzpg r225 = this;
        r225.zzn.zzaV();     // Catch: Throwable -> L454
        if (r225.zzd().zzp(r15.zza.zzA(), zzfy.zzbj) == false) goto L460;
        String r09 = r611.zzK();     // Catch: Throwable -> L454
        if (TextUtils.isEmpty(r09) == true) goto L460;
        zzh r311 = r225.zzj().zzu(r09);     // Catch: Throwable -> L454
        if (r311 == null) goto L460;
        long r415 = r225.zzba().currentTimeMillis();     // Catch: Throwable -> L454
        if ((r415 - r311.zzaN()) < r225.zzd().zzl(r09, zzfy.zzaB)) goto L460;
        List r75 = r225.zzj().zzac(_UrlKt.FRAGMENT_ENCODE_SET);     // Catch: Throwable -> L454
        if (r75.isEmpty() == true) goto L456;
        r611.zzaR(r75);     // Catch: Throwable -> L454
    L456:
        List r010 = r225.zzj().zzac(r09);     // Catch: Throwable -> L454
        if (r010.isEmpty() == true) goto L459;
        r611.zzaR(r010);     // Catch: Throwable -> L454
    L459:
        r311.zzaM(r415);     // Catch: Throwable -> L454
        r225.zzj().zzv(r311, false, false);     // Catch: Throwable -> L454
    L460:
        com.google.android.gms.internal.measurement.zzgl r011 = r225.zzh().zzb(r15.zza.zzA());     // Catch: Throwable -> L454
        if (r011 == null) goto L467;
        if (r011.zza() == false) goto L467;
        r611.zzal(r011.zzb());     // Catch: Throwable -> L454
    L470:
        r225.zzj().zzz((com.google.android.gms.internal.measurement.zzid) r611.zzbd(), r18);     // Catch: Throwable -> L454
        r2 = r225;
    L472:
        r2.zzj().zzS(r15.zzb);     // Catch: Throwable -> L454
        zzaw r312 = r2.zzj();     // Catch: Throwable -> L454
        r312.zze().execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{r19, r19});     // Catch: Throwable -> L454 SQLiteException -> L475
    L477:
        r2.zzj().zzc();     // Catch: Throwable -> L454
        r23 = r2;
        goto L480
    L475:
        e = move-exception;
        r312.zzu.zzaW().zzb().zzc("Failed to remove unused event metadata. appId", zzgu.zzl(r19), e);     // Catch: Throwable -> L454
    L467:
        if (r15.zza.zzP().isEmpty() == false) goto L469;
        r611.zzal(-1);     // Catch: Throwable -> L454
        goto L470
    L469:
        r225.zzaW().zze().zzb("Did not find measurement config or missing version info. appId", zzgu.zzl(r15.zza.zzA()));     // Catch: Throwable -> L454
        goto L470
    L471:
        r2 = this;
    L355:
        th = th;
        zzpg r226 = this;
    L482:
        r226.zzj().zzd();
        throw th;
    L421:
        if (r611.zzc() <= 0) goto L439;
        long r227 = r08.zzp();     // Catch: Throwable -> L355
        if (r227 == 0) goto L425;
        r611.zzA(r227);     // Catch: Throwable -> L355
    L426:
        long r416 = r08.zzn();     // Catch: Throwable -> L355
        if (r416 == 0) goto L431;
        r227 = r416;
    L431:
        if (r227 == 0) goto L433;
        r611.zzy(r227);     // Catch: Throwable -> L355
    L434:
        r08.zzM(r611.zzc());     // Catch: Throwable -> L355
        r611.zzaJ((int) r08.zzaF());     // Catch: Throwable -> L355
        r611.zzZ((int) r08.zzG());     // Catch: Throwable -> L355
        r08.zzo(r611.zzu());     // Catch: Throwable -> L355
        r08.zzq(r611.zzw());     // Catch: Throwable -> L355
        String r228 = r08.zzaa();     // Catch: Throwable -> L355
        if (r228 == null) goto L437;
        r611.zzaa(r228);     // Catch: Throwable -> L355
    L438:
        zzj().zzv(r08, false, false);     // Catch: Throwable -> L355
        goto L439
    L437:
        r611.zzab();     // Catch: Throwable -> L355
        goto L438
    L433:
        r611.zzz();     // Catch: Throwable -> L355
        goto L434
    L425:
        r611.zzB();     // Catch: Throwable -> L355
        goto L426
    L489:
        HashMap r229 = new HashMap();     // Catch: Throwable -> L355
        ArrayList r313 = new ArrayList();     // Catch: Throwable -> L355
        SecureRandom r417 = r1.zzt().zzf();     // Catch: Throwable -> L355
        int r55 = 0;
        r1 = r1;
    L307:
        if (r55 >= r611.zzc()) goto L409;
        com.google.android.gms.internal.measurement.zzhr r76 = (com.google.android.gms.internal.measurement.zzhr) r611.zzd(r55).zzco();     // Catch: Throwable -> L355
        String r83 = "_efs";
        if (r76.zzk().equals("_ep") == false) goto L336;
        r1.zzp();     // Catch: Throwable -> L23
        String r012 = (String) zzpk.zzM((com.google.android.gms.internal.measurement.zzhs) r76.zzbd(), "_en");     // Catch: Throwable -> L23
        zzbd r106 = (zzbd) r229.get(r012);     // Catch: Throwable -> L23
        if (r106 != null) goto L316;
        r106 = r1.zzj().zzf(r15.zza.zzA(), (String) Preconditions.checkNotNull(r012));     // Catch: Throwable -> L23
        if (r106 == null) goto L316;
        r229.put(r012, r106);     // Catch: Throwable -> L23
    L316:
        if (r106 != null) goto L318;
    L332:
        Long r107 = r21;
    L333:
        r611.zzf(r55, r76);     // Catch: Throwable -> L23
    L334:
        SecureRandom r472 = r417;
        int r124 = r55;
        Long r418 = r107;
    L408:
        r55 = r124 + 1;     // Catch: Throwable -> L355
        r1 = this;
        r21 = r418;
        r417 = r472;
        goto L307
    L318:
        if (r106.zzi != null) goto L332;
        Long r013 = r106.zzj;     // Catch: Throwable -> L23
        if (r013 == null) goto L325;
        if (r013.longValue() <= 1) goto L325;
        r1.zzp();     // Catch: Throwable -> L23
        zzpk.zzF(r76, "_sr", r013);     // Catch: Throwable -> L23
    L325:
        Boolean r014 = r106.zzk;     // Catch: Throwable -> L23
        if (r014 != null) goto L328;
    L330:
        r107 = r21;
    L331:
        r313.add((com.google.android.gms.internal.measurement.zzhs) r76.zzbd());     // Catch: Throwable -> L23
        goto L333
    L328:
        if (r014.booleanValue() == false) goto L330;
        r1.zzp();     // Catch: Throwable -> L23
        r107 = r21;
        zzpk.zzF(r76, "_efs", r107);     // Catch: Throwable -> L23
        goto L331
    L336:
        r107 = r21;
        zzht r115 = r1.zzh();     // Catch: Throwable -> L355
        String r125 = r15.zza.zzA();     // Catch: Throwable -> L355
        String r015 = r115.zza(r125, "measurement.account.time_zone_offset_minutes");     // Catch: Throwable -> L355
        if (TextUtils.isEmpty(r015) == false) goto L491;
    L343:
        long r116 = 0;
    L344:
        long r133 = r1.zzt().zzam(r76.zzn(), r116);     // Catch: Throwable -> L355
        com.google.android.gms.internal.measurement.zzhs r016 = (com.google.android.gms.internal.measurement.zzhs) r76.zzbd();     // Catch: Throwable -> L355
        if (TextUtils.isEmpty("_dbg") == true) goto L358;
        Iterator r017 = r016.zza().iterator();     // Catch: Throwable -> L355
    L348:
        if (r017.hasNext() == false) goto L358;
        com.google.android.gms.internal.measurement.zzhw r162 = (com.google.android.gms.internal.measurement.zzhw) r017.next();     // Catch: Throwable -> L355
        String r452 = r83;
        if ("_dbg".equals(r162.zzb()) == true) goto L352;
        r83 = r452;
        goto L348
    L352:
        if (r107.equals(Long.valueOf(r162.zzf())) == false) goto L359;
        int r018 = 1;
    L360:
        if (r018 > 0) goto L362;
        zzaW().zze().zzc("Sample rate must be positive. event, rate", r76.zzk(), Integer.valueOf(r018));     // Catch: Throwable -> L355
        r313.add((com.google.android.gms.internal.measurement.zzhs) r76.zzbd());     // Catch: Throwable -> L355
        r611.zzf(r55, r76);     // Catch: Throwable -> L355
        goto L334
    L362:
        zzbd r110 = (zzbd) r229.get(r76.zzk());     // Catch: Throwable -> L355
        if (r110 != null) goto L368;
        Long r2110 = r107;
        r110 = zzj().zzf(r15.zza.zzA(), r76.zzk());     // Catch: Throwable -> L355
        if (r110 != null) goto L367;
        long r163 = r116;
        zzaW().zze().zzc("Event being bundled has no eventAggregate. appId, eventName", r15.zza.zzA(), r76.zzk());     // Catch: Throwable -> L355
        r110 = new zzbd(r15.zza.zzA(), r76.zzk(), 1, 1, 1, r76.zzn(), 0, null, null, null, null);     // Catch: Throwable -> L355
    L369:
        zzp();     // Catch: Throwable -> L355
        Long r84 = (Long) zzpk.zzM((com.google.android.gms.internal.measurement.zzhs) r76.zzbd(), "_eid");     // Catch: Throwable -> L355
        if (r84 == null) goto L373;
        boolean r252 = true;
    L374:
        if (r018 != 1) goto L386;
        r313.add((com.google.android.gms.internal.measurement.zzhs) r76.zzbd());     // Catch: Throwable -> L355
        if (r252 == true) goto L378;
    L384:
        r611.zzf(r55, r76);     // Catch: Throwable -> L355
        r472 = r417;
        r124 = r55;
        r418 = r2110;
        goto L408
    L378:
        if (r110.zzi == null) goto L380;
    L383:
        r229.put(r76.zzk(), r110.zzc(null, null, null));     // Catch: Throwable -> L355
        goto L384
    L380:
        if (r110.zzj != null) goto L383;
        if (r110.zzk == null) goto L384;
    L386:
        if (r417.nextInt(r018) != 0) goto L392;
        zzp();     // Catch: Throwable -> L355
        Long r019 = Long.valueOf(r018);     // Catch: Throwable -> L355
        zzpk.zzF(r76, "_sr", r019);     // Catch: Throwable -> L355
        r313.add((com.google.android.gms.internal.measurement.zzhs) r76.zzbd());     // Catch: Throwable -> L355
        if (r252 == false) goto L390;
        r110 = r110.zzc(null, r019, null);     // Catch: Throwable -> L355
    L390:
        r229.put(r76.zzk(), r110.zzb(r76.zzn(), r133));     // Catch: Throwable -> L355
        r472 = r417;
        r124 = r55;
        r418 = r2110;
    L407:
        r611.zzf(r124, r76);     // Catch: Throwable -> L355
        goto L408
    L392:
        Long r117 = r110.zzh;     // Catch: Throwable -> L355
        if (r117 == null) goto L395;
        long r118 = r117.longValue();     // Catch: Throwable -> L355
        r472 = r417;
        long r262 = r133;
        int r144 = r55;
    L397:
        if (r118 == r262) goto L402;
        zzp();     // Catch: Throwable -> L355
        r418 = r2110;
        zzpk.zzF(r76, r452, r418);     // Catch: Throwable -> L355
        zzp();     // Catch: Throwable -> L355
        Long r020 = Long.valueOf(r018);     // Catch: Throwable -> L355
        zzpk.zzF(r76, "_sr", r020);     // Catch: Throwable -> L355
        r313.add((com.google.android.gms.internal.measurement.zzhs) r76.zzbd());     // Catch: Throwable -> L355
        if (r252 == false) goto L401;
        r110 = r110.zzc(null, r020, Boolean.TRUE);     // Catch: Throwable -> L355
    L401:
        r229.put(r76.zzk(), r110.zzb(r76.zzn(), r262));     // Catch: Throwable -> L355
        r124 = r144;
        goto L407
    L402:
        r418 = r2110;
        if (r252 == false) goto L405;
        r229.put(r76.zzk(), r110.zzc(r84, null, null));     // Catch: Throwable -> L355
    L405:
        r124 = r144;
        goto L407
    L395:
        r472 = r417;
        r262 = r133;
        r144 = r55;
        r118 = zzt().zzam(r76.zzp(), r163);     // Catch: Throwable -> L355
        goto L397
    L373:
        r252 = false;
    L367:
        r163 = r116;
        goto L369
    L368:
        r2110 = r107;
    L359:
        r018 = zzh().zzn(r15.zza.zzA(), r76.zzk());     // Catch: Throwable -> L355
    L358:
        r452 = r83;
        goto L359
    L491:
        r116 = Long.parseLong(r015);     // Catch: Throwable -> L23 NumberFormatException -> L341
    L341:
        e = move-exception;
        r115.zzu.zzaW().zze().zzc("Unable to parse timezone offset. appId", zzgu.zzl(r125), e);     // Catch: Throwable -> L23
        goto L343
    L409:
        r10 = true;
        if (r313.size() >= r611.zzc()) goto L412;
        r611.zzi();     // Catch: Throwable -> L355
        r611.zzh(r313);     // Catch: Throwable -> L355
    L412:
        Iterator r021 = r229.entrySet().iterator();     // Catch: Throwable -> L355
    L414:
        if (r021.hasNext() == false) goto L417;
        zzj().zzh((zzbd) ((Map.Entry) r021.next()).getValue());     // Catch: Throwable -> L355
        goto L414
    L297:
        if (r1.zzt().zzZ(r15.zza.zzA()) == false) goto L303;
        if (r1.zzB(r15.zza.zzA()).zzo(r224) == false) goto L303;
        if (r15.zza.zzak() == false) goto L303;
        r1.zzT(r611, r15);     // Catch: Throwable -> L23
    L284:
        if (r07.zzo(r310) == false) goto L288;
        if (r223.zzo(r310) == true) goto L288;
        r1.zzj().zzj(r15.zza.zzA());     // Catch: Throwable -> L23
        goto L288
    L267:
        r1.zzJ(r221, r611);     // Catch: Throwable -> L23
        goto L268
    L263:
        r1.zzI(r220, r611);     // Catch: Throwable -> L23
        goto L264
    L257:
        int r022 = zzpk.zzx(r611, "_se");     // Catch: Throwable -> L23
        if (r022 < 0) goto L260;
        r611.zzr(r022);     // Catch: Throwable -> L23
        r1.zzaW().zzb().zzb("Session engagement user property is in the bundle without session ID. appId", zzgu.zzl(r15.zza.zzA()));     // Catch: Throwable -> L23
    L454:
        th = th;
        r226 = r2;
    L23:
        th = th;
        r226 = r1;
        goto L482
    }

    private final void zzaI(com.google.android.gms.internal.measurement.zzic r11, long r12, boolean r14) {
        if (true == r14) goto L6;
        String r1 = "_lte";
    L5:
        String r5 = r1;
        zzpn r13 = zzj().zzm(r11.zzK(), r5);
        if (r13 == null) goto L13;
        Object r15 = r13.zze;
        if (r15 == null) goto L13;
        zzpn r2 = new zzpn(r11.zzK(), "auto", r5, zzba().currentTimeMillis(), Long.valueOf(((Long) r15).longValue() + r12));
    L14:
        com.google.android.gms.internal.measurement.zzit r16 = com.google.android.gms.internal.measurement.zziu.zzm();
        r16.zzb(r5);
        r16.zza(zzba().currentTimeMillis());
        Object r3 = r2.zze;
        r16.zze(((Long) r3).longValue());
        com.google.android.gms.internal.measurement.zziu r17 = (com.google.android.gms.internal.measurement.zziu) r16.zzbd();
        int r4 = zzpk.zzx(r11, r5);
        if (r4 < 0) goto L17;
        r11.zzn(r4, r17);
    L19:
        if (r12 <= 0) goto L26;
        zzj().zzl(r2);
        if (true == r14) goto L23;
        String r112 = "lifetime";
    L24:
        zzaW().zzk().zzc("Updated engagement user property. scope, value", r112, r3);
        return;
    L23:
        r112 = "session-scoped";
        goto L24
    L26:
        return;
    L17:
        r11.zzo(r17);
    L13:
        r2 = new zzpn(r11.zzK(), "auto", r5, zzba().currentTimeMillis(), Long.valueOf(r12));
        goto L14
    L6:
        r1 = "_se";
        goto L5
    }

    private final boolean zzaJ(com.google.android.gms.internal.measurement.zzhr r9, com.google.android.gms.internal.measurement.zzhr r10) {
        Preconditions.checkArgument("_e".equals(r9.zzk()));
        zzp();
        com.google.android.gms.internal.measurement.zzhw r0 = zzpk.zzI((com.google.android.gms.internal.measurement.zzhs) r9.zzbd(), "_sc");
        String r2 = null;
        if (r0 != null) goto L5;
        String r02 = null;
    L6:
        zzp();
        com.google.android.gms.internal.measurement.zzhw r3 = zzpk.zzI((com.google.android.gms.internal.measurement.zzhs) r10.zzbd(), "_pc");
        if (r3 == null) goto L10;
        r2 = r3.zzd();
    L10:
        if (r2 != null) goto L12;
        return false;
    L12:
        if (r2.equals(r02) == false) goto L33;
        Preconditions.checkArgument("_e".equals(r9.zzk()));
        zzp();
        com.google.android.gms.internal.measurement.zzhw r03 = zzpk.zzI((com.google.android.gms.internal.measurement.zzhs) r9.zzbd(), "_et");
        if (r03 != null) goto L16;
        return true;
    L16:
        if (r03.zze() == true) goto L18;
        return true;
    L18:
        if (r03.zzf() <= 0) goto L32;
        long r22 = r03.zzf();
        zzp();
        com.google.android.gms.internal.measurement.zzhw r04 = zzpk.zzI((com.google.android.gms.internal.measurement.zzhs) r10.zzbd(), "_et");
        if (r04 != null) goto L23;
    L25:
        zzp();
        zzpk.zzF(r10, "_et", Long.valueOf(r22));
        zzp();
        zzpk.zzF(r9, "_fr", 1L);
        return true;
    L23:
        if (r04.zzf() <= 0) goto L25;
        r22 = r22 + r04.zzf();
        goto L25
    L32:
        return true;
    L33:
        return false;
    L5:
        r02 = r0.zzd();
        goto L6
    }

    private final void zzaK(com.google.android.gms.internal.measurement.zzhr r9, String r10, String r11) {
        ArrayList r0 = new ArrayList(r9.zza());
        int r1 = 0;
    L4:
        if (r1 >= r0.size()) goto L9;
        if (r10.equals(((com.google.android.gms.internal.measurement.zzhw) r0.get(r1)).zzb()) == true) goto L10;
        r1 = r1 + 1;
    L10:
        if (r1 != (-1)) goto L12;
        return;
    L12:
        double r2 = r9.zzc(r1).zzk() * 1000000.0d;
        if (r2 != 0.0d) goto L16;
        r2 = r9.zzc(r1).zzf() * 1000000.0d;
    L16:
        if (r2 <= 9.223372036854776E18d) goto L18;
    L21:
        zzaW().zze().zzc("Data lost. Purchase " + r10 + " is too big. appId", zzgu.zzl(r11), Double.valueOf(r2));
        return;
    L18:
        if (r2 < (-9.223372036854776E18d)) goto L21;
        r9.zzj(r1);
        zzhv r8 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r8.zzb(r10);
        r8.zzf(Math.round(r2));
        r9.zzf((com.google.android.gms.internal.measurement.zzhw) r8.zzbd());
        return;
    L9:
        r1 = -1;
        goto L10
    }

    private final boolean zzaL() {
        zzaX().zzg();
        zzu();
        if (zzj().zzP() == false) goto L5;
        return true;
    L5:
        if (TextUtils.isEmpty(zzj().zzF()) == false) goto L11;
        return false;
    L11:
        return true;
    }

    private final void zzaM() {
        zzaX().zzg();
        zzu();
        if (this.zza <= 0) goto L10;
        long r5 = DurationKt.MILLIS_IN_HOUR - Math.abs(zzba().elapsedRealtime() - this.zza);
        if (r5 <= 0) goto L8;
        zzaW().zzk().zzb("Upload has been suspended. Will update scheduling later in approximately ms", Long.valueOf(r5));
        zzk().zzb();
        zzl().zzf();
        return;
    L8:
        this.zza = 0;
    L10:
        if (this.zzn.zzH() == true) goto L12;
    L66:
        zzaW().zzk().zza("Nothing to upload or uploading impossible");
        zzk().zzb();
        zzl().zzf();
        return;
    L12:
        if (zzaL() == false) goto L66;
        long r1 = zzba().currentTimeMillis();
        zzd();
        long r7 = Math.max(0, ((Long) zzfy.zzO.zzb(null)).longValue());
        boolean r10 = true;
        if (zzj().zzR() == false) goto L16;
    L19:
        if (r10 == false) goto L26;
        String r52 = zzd().zzA();
        if (TextUtils.isEmpty(r52) == false) goto L23;
    L25:
        zzd();
        long r11 = Math.max(0, ((Long) zzfy.zzI.zzb(null)).longValue());
    L27:
        long r13 = this.zzk.zzd.zza();
        long r15 = this.zzk.zze.zza();
        long r17 = 0;
        boolean r19 = r10;
        long r3 = Math.max(zzj().zzM(), zzj().zzO());
        if (r3 != 0) goto L30;
        long r72 = 0;
    L51:
        if (r72 != r17) goto L55;
        zzaW().zzk().zza("Next upload time is 0");
        zzk().zzb();
        zzl().zzf();
        return;
    L55:
        if (zzi().zzb() == false) goto L64;
        long r12 = this.zzk.zzc.zza();
        zzd();
        long r32 = Math.max(0, ((Long) zzfy.zzF.zzb(null)).longValue());
        if (zzp().zzs(r12, r32) == true) goto L59;
        r72 = Math.max(r72, r12 + r32);
    L59:
        zzk().zzb();
        long r73 = r72 - zzba().currentTimeMillis();
        if (r73 > 0) goto L62;
        zzd();
        r73 = Math.max(0, ((Long) zzfy.zzK.zzb(null)).longValue());
        this.zzk.zzd.zzb(zzba().currentTimeMillis());
    L62:
        zzaW().zzk().zzb("Upload scheduled in approximately ms", Long.valueOf(r73));
        zzl().zzd(r73);
        return;
    L64:
        zzaW().zzk().zza("No network");
        zzk().zza();
        zzl().zzf();
        return;
    L30:
        long r33 = r1 - Math.abs(r3 - r1);
        long r9 = r1 - Math.abs(r13 - r1);
        long r14 = r1 - Math.abs(r15 - r1);
        r72 = r7 + r33;
        long r92 = Math.max(r9, r14);
        if (r19 == false) goto L36;
        if (r92 <= 0) goto L36;
        r72 = Math.min(r33, r92) + r11;
    L36:
        if (zzp().zzs(r92, r11) == true) goto L39;
        r72 = r92 + r11;
    L39:
        if (r14 != 0) goto L41;
    L47:
        r17 = 0;
        goto L51
    L41:
        if (r14 < r33) goto L47;
        int r34 = 0;
    L43:
        zzd();
        if (r34 >= Math.min(20, Math.max(0, ((Integer) zzfy.zzQ.zzb(null)).intValue()))) goto L49;
        zzd();
        r72 = r72 + (Math.max(r17, ((Long) zzfy.zzP.zzb(null)).longValue()) * (1 << r34));
        if (r72 > r14) goto L47;
        r34 = r34 + 1;
        r17 = 0;
        goto L43
    L49:
        r72 = 0;
        goto L47
    L23:
        if (".none.".equals(r52) == true) goto L25;
        zzd();
        r11 = Math.max(0, ((Long) zzfy.zzJ.zzb(null)).longValue());
        goto L27
    L26:
        zzd();
        r11 = Math.max(0, ((Long) zzfy.zzH.zzb(null)).longValue());
        goto L27
    L16:
        if (zzj().zzG() == true) goto L19;
        r10 = false;
        goto L19
    }

    private final void zzaN() {
        zzaX().zzg();
        if (this.zzu == false) goto L5;
    L18:
        zzaW().zzk().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv), Boolean.valueOf(this.zzw));
        return;
    L5:
        if (this.zzv == true) goto L18;
        if (this.zzw == true) goto L18;
        zzaW().zzk().zza("Stopping uploading service(s)");
        List r0 = this.zzq;
        if (r0 != null) goto L12;
        return;
    L12:
        Iterator r02 = r0.iterator();
    L14:
        if (r02.hasNext() == false) goto L16;
        ((Runnable) r02.next()).run();
        goto L14
    L16:
        ((List) Preconditions.checkNotNull(this.zzq)).clear();
    }

    private final Boolean zzaO(zzh r5) {
        long r0 = r5.zzt();     // Catch: PackageManager.NameNotFoundException -> L17
        zzic r4 = this.zzn;
        if (r0 != (-2147483648L)) goto L6;
        String r42 = Wrappers.packageManager(r4.zzaZ()).getPackageInfo(r5.zzc(), 0).versionName;     // Catch: PackageManager.NameNotFoundException -> L17
        String r52 = r5.zzr();     // Catch: PackageManager.NameNotFoundException -> L17
        if (r52 != null) goto L12;
    L16:
        return Boolean.FALSE;
    L12:
        if (r52.equals(r42) == false) goto L16;
        return Boolean.TRUE;
    L6:
        if (r5.zzt() != Wrappers.packageManager(r4.zzaZ()).getPackageInfo(r5.zzc(), 0).versionCode) goto L16;
        return Boolean.TRUE;
    L17:
        return null;
    }

    private final zzr zzaP(String r43) {
        zzh r0 = zzj().zzu(r43);
        if (r0 != null) goto L5;
    L15:
        zzaW().zzj().zzb("No app data available; dropping", r43);
        return null;
    L5:
        if (TextUtils.isEmpty(r0.zzr()) == true) goto L15;
        Boolean r4 = zzaO(r0);
        if (r4 == null) goto L14;
        if (r4.booleanValue() == true) goto L14;
        zzaW().zzb().zzb("App version does not match; dropping. appId", zzgu.zzl(r43));
        return null;
    L14:
        return new zzr(r43, r0.zzf(), r0.zzr(), r0.zzt(), r0.zzv(), r0.zzx(), r0.zzz(), null, r0.zzD(), false, r0.zzl(), 0, 0, r0.zzac(), false, r0.zzae(), r0.zzB(), r0.zzag(), zzB(r43).zzl(), _UrlKt.FRAGMENT_ENCODE_SET, null, r0.zzai(), r0.zzak(), zzB(r43).zzb(), zzx(r43).zze(), r0.zzao(), r0.zzaw(), r0.zzay(), r0.zzaH(), 0, r0.zzaL(), 0);
    }

    private final boolean zzaQ(String r3, String r4) {
        zzbd r2 = zzj().zzf(r3, r4);
        if (r2 != null) goto L5;
        return true;
    L5:
        if (r2.zzc < 1) goto L11;
        return false;
    L11:
        return true;
    }

    private static void zzaR(Context r2, Intent r3) {
        if (Build.VERSION.SDK_INT >= 34) goto L6;
        r2.sendBroadcast(r3);
        return;
    L6:
        r2.sendBroadcast(r3, null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
    }

    private static final boolean zzaS(zzr r0) {
        if (TextUtils.isEmpty(r0.zzb) == true) goto L6;
        return true;
    L6:
        return false;
    }

    private static final zzos zzaT(zzos r2) {
        if (r2 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Upload Component not created");
        return null;
    L5:
        if (r2.zzax() == false) goto L7;
        return r2;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("Component not initialized: ".concat(String.valueOf(r2.getClass())));
        return null;
    }

    private static final Boolean zzaU(zzr r2) {
        Boolean r0 = r2.zzp;
        String r22 = r2.zzC;
        if (TextUtils.isEmpty(r22) == true) goto L19;
        zzji r23 = zze.zzc(r22).zza();
        zzji r1 = zzji.zza;
        int r24 = r23.ordinal();
        if (r24 != 0) goto L7;
        return null;
    L7:
        if (r24 != 1) goto L9;
        return null;
    L9:
        if (r24 == 2) goto L16;
        if (r24 != 3) goto L19;
        return Boolean.FALSE;
    L16:
        return Boolean.TRUE;
    L19:
        return r0;
    }

    public static /* synthetic */ void zzax(Context r0, Intent r1) {
        zzaR(r0, r1);
    }

    public final void zzA(String r2, zzjl r3) {
        zzaX().zzg();
        zzu();
        this.zzC.put(r2, r3);
        zzj().zzaa(r2, r3);
    }

    public final zzjl zzB(String r2) {
        zzjl r0 = zzjl.zza;
        zzaX().zzg();
        zzu();
        zzjl r02 = (zzjl) this.zzC.get(r2);
        if (r02 != null) goto L8;
        r02 = zzj().zzY(r2);
        if (r02 != null) goto L7;
        r02 = zzjl.zza;
    L7:
        zzA(r2, r02);
    L8:
        return r02;
    }

    public final long zzC() {
        long r0 = zzba().currentTimeMillis();
        zznn r7 = this.zzk;
        r7.zzay();
        r7.zzg();
        zzhe r2 = r7.zzf;
        long r3 = r2.zza();
        if (r3 != 0) goto L6;
        r3 = r7.zzu.zzk().zzf().nextInt(86400000) + 1;
        r2.zzb(r3);
    L6:
        return ((((r0 + r3) / 1000) / 60) / 60) / 24;
    }

    public final void zzD(zzbh r45, String r46) {
        zzh r2 = zzj().zzu(r46);
        if (r2 != null) goto L5;
    L19:
        zzaW().zzj().zzb("No app data available; dropping event", r46);
        return;
    L5:
        if (TextUtils.isEmpty(r2.zzr()) == true) goto L19;
        Boolean r4 = zzaO(r2);
        if (r4 != null) goto L14;
        if ("_ui".equals(r45.zza) == true) goto L17;
        zzaW().zze().zzb("Could not find package. appId", zzgu.zzl(r46));
    L17:
        zzE(r45, new zzr(r46, r2.zzf(), r2.zzr(), r2.zzt(), r2.zzv(), r2.zzx(), r2.zzz(), null, r2.zzD(), false, r2.zzl(), 0, 0, r2.zzac(), false, r2.zzae(), r2.zzB(), r2.zzag(), zzB(r46).zzl(), _UrlKt.FRAGMENT_ENCODE_SET, null, r2.zzai(), r2.zzak(), zzB(r46).zzb(), zzx(r46).zze(), r2.zzao(), r2.zzaw(), r2.zzay(), r2.zzaH(), 0, r2.zzaL(), 0));
        return;
    L14:
        if (r4.booleanValue() == true) goto L17;
        zzaW().zzb().zzb("App version does not match; dropping event. appId", zzgu.zzl(r46));
    }

    public final void zzE(zzbh r9, zzr r10) {
        String r0 = r10.zza;
        Preconditions.checkNotEmpty(r0);
        zzgv r92 = zzgv.zza(r9);
        Bundle r1 = r92.zze;
        zzt().zzK(r1, zzj().zzV(r0));
        zzt().zzI(r92, zzd().zzd(r0));
        zzbh r93 = r92.zzb();
        if (zzd().zzp(null, zzfy.zzaZ) == false) goto L6;
    L12:
        zzF(r93, r10);
        return;
    L6:
        if ("_cmp".equals(r93.zza) == false) goto L12;
        zzbf r02 = r93.zzb;
        if ("referrer API v2".equals(r02.zzd("_cis")) == false) goto L12;
        String r6 = r02.zzd("gclid");
        if (TextUtils.isEmpty(r6) == true) goto L12;
        zzad(new zzpl("_lgclid", r93.zzd, r6, "auto"), r10);
        goto L12
    }

    public final void zzF(zzbh r24, zzr r25) {
        Preconditions.checkNotNull(r25);
        String r5 = r25.zza;
        Preconditions.checkNotEmpty(r5);
        zzaX().zzg();
        zzu();
        long r9 = r24.zzd;
        long r11 = r24.zze;
        zzgv r0 = zzgv.zza(r24);
        zzaX().zzg();
        zzlu r6 = this.zzG;
        if (r6 == null) goto L8;
        String r8 = this.zzH;
        if (r8 == null) goto L8;
        if (r8.equals(r5) == false) goto L8;
    L9:
        zzpp.zzay(r6, r0.zze, false);
        zzbh r02 = r0.zzb();
        zzp();
        if (zzpk.zzG(r02, r25) == true) goto L13;
        return;
    L13:
        if (r25.zzh == true) goto L16;
        zzap(r25);
        return;
    L16:
        List r62 = r25.zzr;
        if (r62 == null) goto L23;
        String r82 = r02.zza;
        if (r62.contains(r82) == false) goto L21;
        Bundle r63 = r02.zzb.zzf();
        r63.putLong("ga_safelisted", 1);
        r02 = new zzbh(r82, new zzbf(r63), r02.zzc, r02.zzd, r02.zze);
        goto L23
    L21:
        zzaW().zzj().zzd("Dropping non-safelisted event. appId, event name, origin", r5, r82, r02.zzc);
        return;
    L23:
        zzj().zzb();
        String r64 = r02.zza;     // Catch: Throwable -> L36
        if ("_s".equals(r64) == true) goto L27;
    L39:
        zzaw r3 = zzj();     // Catch: Throwable -> L36
        Preconditions.checkNotEmpty(r5);     // Catch: Throwable -> L36
        r3.zzg();     // Catch: Throwable -> L36
        r3.zzay();     // Catch: Throwable -> L36
        int r4 = (r9 > 0 ? 1 : (r9 == 0 ? 0 : -1));
        if (r4 >= 0) goto L42;
        r3.zzu.zzaW().zze().zzc("Invalid time querying timed out conditional properties", zzgu.zzl(r5), Long.valueOf(r9));     // Catch: Throwable -> L36
        List r32 = Collections.EMPTY_LIST;     // Catch: Throwable -> L36
    L43:
        Iterator r33 = r32.iterator();     // Catch: Throwable -> L36
    L45:
        if (r33.hasNext() == false) goto L52;
        zzah r13 = (zzah) r33.next();     // Catch: Throwable -> L36
        if (r13 == null) goto L45;
        Iterator r16 = r33;
        zzaW().zzk().zzd("User property timed out", r13.zza, this.zzn.zzl().zzc(r13.zzc.zzb), r13.zzc.zza());     // Catch: Throwable -> L36
        zzbh r83 = r13.zzg;     // Catch: Throwable -> L36
        if (r83 == null) goto L51;
        zzH(new zzbh(r83, r9, r11), r25);     // Catch: Throwable -> L36
    L51:
        zzj().zzr(r5, r13.zzc.zzb);     // Catch: Throwable -> L36
        r33 = r16;
        goto L45
    L52:
        zzaw r34 = zzj();     // Catch: Throwable -> L36
        Preconditions.checkNotEmpty(r5);     // Catch: Throwable -> L36
        r34.zzg();     // Catch: Throwable -> L36
        r34.zzay();     // Catch: Throwable -> L36
        if (r4 >= 0) goto L55;
        r34.zzu.zzaW().zze().zzc("Invalid time querying expired conditional properties", zzgu.zzl(r5), Long.valueOf(r9));     // Catch: Throwable -> L36
        List r35 = Collections.EMPTY_LIST;     // Catch: Throwable -> L36
    L56:
        ArrayList r132 = new ArrayList(r35.size());     // Catch: Throwable -> L36
        Iterator r36 = r35.iterator();     // Catch: Throwable -> L36
    L58:
        if (r36.hasNext() == false) goto L65;
        zzah r7 = (zzah) r36.next();     // Catch: Throwable -> L36
        if (r7 == null) goto L58;
        Iterator r162 = r36;
        int r17 = r4;
        zzaW().zzk().zzd("User property expired", r7.zza, this.zzn.zzl().zzc(r7.zzc.zzb), r7.zzc.zza());     // Catch: Throwable -> L36
        zzj().zzk(r5, r7.zzc.zzb);     // Catch: Throwable -> L36
        zzbh r37 = r7.zzk;     // Catch: Throwable -> L36
        if (r37 == null) goto L64;
        r132.add(r37);     // Catch: Throwable -> L36
    L64:
        zzj().zzr(r5, r7.zzc.zzb);     // Catch: Throwable -> L36
        r36 = r162;
        r4 = r17;
        goto L58
    L65:
        int r172 = r4;
        int r38 = r132.size();     // Catch: Throwable -> L36
        int r42 = 0;
    L66:
        if (r42 >= r38) goto L68;
        Object r72 = r132.get(r42);     // Catch: Throwable -> L36
        r42 = r42 + 1;     // Catch: Throwable -> L36
        zzH(new zzbh((zzbh) r72, r9, r11), r25);     // Catch: Throwable -> L36
        r11 = r11;
        goto L66
    L68:
        long r14 = r11;
        zzaw r39 = zzj();     // Catch: Throwable -> L36
        Preconditions.checkNotEmpty(r5);     // Catch: Throwable -> L36
        Preconditions.checkNotEmpty(r64);     // Catch: Throwable -> L36
        r39.zzg();     // Catch: Throwable -> L36
        r39.zzay();     // Catch: Throwable -> L36
        if (r172 >= 0) goto L71;
        zzic r310 = r39.zzu;     // Catch: Throwable -> L36
        r310.zzaW().zze().zzd("Invalid time querying triggered conditional properties", zzgu.zzl(r5), r310.zzl().zza(r64), Long.valueOf(r9));     // Catch: Throwable -> L36
        List r311 = Collections.EMPTY_LIST;     // Catch: Throwable -> L36
    L72:
        ArrayList r43 = new ArrayList(r311.size());     // Catch: Throwable -> L36
        Iterator r312 = r311.iterator();     // Catch: Throwable -> L36
    L74:
        if (r312.hasNext() == false) goto L85;
        zzah r52 = (zzah) r312.next();     // Catch: Throwable -> L36
        if (r52 == null) goto L74;
        zzpl r65 = r52.zzc;     // Catch: Throwable -> L36
        long r112 = r9;
        zzpn r73 = new zzpn((String) Preconditions.checkNotNull(r52.zza), r52.zzb, r65.zzb, r112, Preconditions.checkNotNull(r65.zza()));     // Catch: Throwable -> L36
        r9 = r112;
        if (zzj().zzl(r73) == false) goto L80;
        zzaW().zzk().zzd("User property triggered", r52.zza, this.zzn.zzl().zzc(r73.zzc), r73.zze);     // Catch: Throwable -> L36
    L81:
        zzbh r66 = r52.zzi;     // Catch: Throwable -> L36
        if (r66 == null) goto L84;
        r43.add(r66);     // Catch: Throwable -> L36
    L84:
        r52.zzc = new zzpl(r73);     // Catch: Throwable -> L36
        r52.zze = true;     // Catch: Throwable -> L36
        zzj().zzp(r52);     // Catch: Throwable -> L36
        goto L74
    L80:
        zzaW().zzb().zzd("Too many active user properties, ignoring", zzgu.zzl(r52.zza), this.zzn.zzl().zzc(r73.zzc), r73.zze);     // Catch: Throwable -> L36
        goto L81
    L85:
        zzH(r02, r25);     // Catch: Throwable -> L36
        int r03 = r43.size();     // Catch: Throwable -> L36
        int r313 = 0;
    L86:
        if (r313 >= r03) goto L88;
        Object r53 = r43.get(r313);     // Catch: Throwable -> L36
        r313 = r313 + 1;     // Catch: Throwable -> L36
        long r113 = r14;
        zzH(new zzbh((zzbh) r53, r9, r113), r25);     // Catch: Throwable -> L36
        r14 = r113;
        goto L86
    L88:
        zzj().zzc();     // Catch: Throwable -> L36
        zzj().zzd();
        return;
    L71:
        r311 = r39.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{r5, r64, String.valueOf(r9)});     // Catch: Throwable -> L36
        goto L72
    L55:
        r35 = r34.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{r5, String.valueOf(r9)});     // Catch: Throwable -> L36
        goto L56
    L42:
        r32 = r3.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{r5, String.valueOf(r9)});     // Catch: Throwable -> L36
        goto L43
    L27:
        if (zzj().zzQ(r5, "_s") == true) goto L39;
        if (r02.zzb.zzb("_sid").longValue() == 0) goto L39;
        if (zzj().zzQ(r5, "_f") == false) goto L33;
    L38:
        zzj().zzX(r5, null, "_sid", zzG(r5, r02));     // Catch: Throwable -> L36
        goto L39
    L33:
        if (zzj().zzQ(r5, "_v") == true) goto L38;
        zzj().zzX(r5, Long.valueOf(zzba().currentTimeMillis() - 15000), "_sid", zzG(r5, r02));     // Catch: Throwable -> L36
    L36:
        th = move-exception;
        zzj().zzd();
        throw th;
    L8:
        r6 = null;
        goto L9
    }

    public final Bundle zzG(String r5, zzbh r6) {
        Bundle r0 = new Bundle();
        r0.putLong("_sid", r6.zzb.zzb("_sid").longValue());
        zzpn r4 = zzj().zzm(r5, "_sno");
        if (r4 == null) goto L7;
        Object r42 = r4.zze;
        if ((r42 instanceof Long) == false) goto L7;
        r0.putLong("_sno", ((Long) r42).longValue());
    L7:
        return r0;
    }

    public final void zzH(zzbh r46, zzr r47) {
        Preconditions.checkNotNull(r47);
        String r10 = r47.zza;
        Preconditions.checkNotEmpty(r10);
        long r25 = System.nanoTime();
        zzaX().zzg();
        zzu();
        zzp();
        if (zzpk.zzG(r46, r47) == true) goto L6;
        return;
    L6:
        if (r47.zzh == true) goto L9;
        zzap(r47);
        return;
    L9:
        zzht r0 = zzh();
        String r13 = r46.zza;
        if (r0.zzj(r10, r13) == false) goto L26;
        zzaW().zze().zzc("Dropping blocked event. appId", zzgu.zzl(r10), this.zzn.zzl().zza(r13));
        if (zzh().zzo(r10) == false) goto L14;
    L20:
        zzh r02 = zzj().zzu(r10);
        if (r02 == null) goto L350;
        long r2 = Math.abs(zzba().currentTimeMillis() - Math.max(r02.zzJ(), r02.zzH()));
        zzd();
        if (r2 <= ((Long) zzfy.zzN.zzb(null)).longValue()) goto L351;
        zzaW().zzj().zza("Fetching config for blocked app");
        zzX(r02);
        return;
    L351:
        return;
    L350:
        return;
    L14:
        if (zzh().zzp(r10) == true) goto L20;
        if ("_err".equals(r13) == true) goto L349;
        zzt().zzP(this.zzK, r10, 11, "_ev", r13, 0);
        return;
    L349:
        return;
    L26:
        zzgv r03 = zzgv.zza(r46);
        zzt().zzI(r03, zzd().zzd(r10));
        int r8 = zzd().zzn(r10, zzfy.zzaf, 10, 35);
        Bundle r11 = r03.zze;
        Iterator r12 = new TreeSet(r11.keySet()).iterator();
    L28:
        if (r12.hasNext() == false) goto L32;
        String r132 = (String) r12.next();
        if (PluginsConstants.Settings.ITEMS.equals(r132) == false) goto L28;
        zzt().zzJ(r11.getParcelableArray(r132), r8);
        goto L28
    L32:
        zzbh r82 = r03.zzb();
        if (Log.isLoggable(zzaW().zzn(), 2) == false) goto L35;
        zzaW().zzk().zzb("Logging event", this.zzn.zzl().zzd(r82));
    L35:
        zzj().zzb();
        zzap(r47);     // Catch: Throwable -> L45
        String r112 = r82.zza;     // Catch: Throwable -> L45
        if ("ecommerce_purchase".equals(r112) == false) goto L40;
    L43:
        boolean r04 = true;
    L48:
        if ("_iap".equals(r112) == true) goto L53;
        if (r04 == false) goto L51;
        r04 = true;
        goto L53
    L51:
        String r27 = "metadata_fingerprint";
        String r28 = "app_id";
    L52:
        zzbh r4 = r82;
    L89:
        String r05 = r4.zza;     // Catch: Throwable -> L45
        boolean r83 = zzpp.zzh(r05);     // Catch: Throwable -> L45
        boolean r17 = "_err".equals(r05);     // Catch: Throwable -> L45
        zzt();     // Catch: Throwable -> L45
        zzbf r9 = r4.zzb;     // Catch: Throwable -> L45
        if (r9 != null) goto L92;
        long r14 = 0;
    L98:
        String r113 = r10;
        zzbh r24 = r4;
        zzar r84 = zzj().zzx(zzC(), r113, r14 + 1, true, r83, false, r17, false, false, false);     // Catch: Throwable -> L45
        long r114 = r84.zzb;     // Catch: Throwable -> L45
        zzd();     // Catch: Throwable -> L45
        long r115 = r114 - zzal.zzH();     // Catch: Throwable -> L45
        if (r115 > 0) goto L101;
        if (r83 == false) goto L112;
        long r116 = r84.zza;     // Catch: Throwable -> L45
        zzd();     // Catch: Throwable -> L45
        long r117 = r116 - ((Integer) zzfy.zzm.zzb(null)).intValue();     // Catch: Throwable -> L45
        if (r117 <= 0) goto L112;
        if ((r117 % 1000) != 1) goto L110;
        zzaW().zzb().zzc("Data loss. Too many public events logged. appId, count", zzgu.zzl(r113), Long.valueOf(r84.zza));     // Catch: Throwable -> L45
    L110:
        zzt().zzP(this.zzK, r113, 16, "_ev", r24.zza, 0);     // Catch: Throwable -> L45
        zzj().zzc();     // Catch: Throwable -> L45
    L147:
        zzj().zzd();
        return;
    L112:
        if (r17 == false) goto L119;
        long r32 = 1;
        int r42 = 0;
        long r122 = r84.zzd - Math.max(0, Math.min(DurationKt.NANOS_IN_MILLIS, zzd().zzm(r47.zza, zzfy.zzl)));     // Catch: Throwable -> L45
        if (r122 > 0) goto L116;
    L120:
        Bundle r3 = r9.zzf();     // Catch: Throwable -> L45
        zzt().zzO(r3, "_o", r24.zzc);     // Catch: Throwable -> L45
        if (zzt().zzad(r113, r47.zzB) == false) goto L125;
        zzpp r85 = zzt();     // Catch: Throwable -> L45
        Long r142 = Long.valueOf(r32);     // Catch: Throwable -> L45
        r85.zzO(r3, "_dbg", r142);     // Catch: Throwable -> L45
        zzt().zzO(r3, "_r", r142);     // Catch: Throwable -> L45
    L125:
        if ("_s".equals(r05) == false) goto L131;
        zzpn r06 = zzj().zzm(r47.zza, "_sno");     // Catch: Throwable -> L45
        if (r06 == null) goto L131;
        Object r07 = r06.zze;     // Catch: Throwable -> L45
        if ((r07 instanceof Long) == false) goto L131;
        zzt().zzO(r3, "_sno", r07);     // Catch: Throwable -> L45
    L131:
        zzaw r7 = zzj();     // Catch: Throwable -> L45
        Preconditions.checkNotEmpty(r113);     // Catch: Throwable -> L45
        r7.zzg();     // Catch: Throwable -> L45
        r7.zzay();     // Catch: Throwable -> L45
        long r72 = r7.zze().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{r113, String.valueOf(Math.max(r42, Math.min(DurationKt.NANOS_IN_MILLIS, r7.zzu.zzc().zzm(r113, zzfy.zzp))))});     // Catch: Throwable -> L45 SQLiteException -> L134
    L137:
        if (r72 <= 0) goto L139;
        zzaW().zze().zzc("Data lost. Too many events stored on disk, deleted. appId", zzgu.zzl(r113), Long.valueOf(r72));     // Catch: Throwable -> L45
    L139:
        zzic r92 = this.zzn;     // Catch: Throwable -> L45
        zzbc r86 = new zzbc(r92, r24.zzc, r113, r24.zza, r24.zzd, r24.zze, 0, r3);     // Catch: Throwable -> L45
        zzaw r5 = zzj();     // Catch: Throwable -> L45
        String r73 = r86.zzb;     // Catch: Throwable -> L45
        zzbd r52 = r5.zzf(r113, r73);     // Catch: Throwable -> L45
        if (r52 == null) goto L142;
        zzbc r87 = r86.zza(r92, r52.zzf);     // Catch: Throwable -> L45
        zzbd r08 = r52.zza(r87.zzd);     // Catch: Throwable -> L45
        zzbc r53 = r87;
        zzbd r88 = r08;
    L151:
        zzj().zzh(r88);     // Catch: Throwable -> L45
        zzaX().zzg();     // Catch: Throwable -> L45
        zzu();     // Catch: Throwable -> L45
        Preconditions.checkNotNull(r53);     // Catch: Throwable -> L45
        Preconditions.checkNotNull(r47);     // Catch: Throwable -> L45
        String r09 = r53.zza;     // Catch: Throwable -> L45
        Preconditions.checkNotEmpty(r09);     // Catch: Throwable -> L45
        String r74 = r47.zza;     // Catch: Throwable -> L45
        Preconditions.checkArgument(r09.equals(r74));     // Catch: Throwable -> L45
        com.google.android.gms.internal.measurement.zzic r89 = com.google.android.gms.internal.measurement.zzid.zzaE();     // Catch: Throwable -> L45
        r89.zza(1);     // Catch: Throwable -> L45
        r89.zzC("android");     // Catch: Throwable -> L45
        if (TextUtils.isEmpty(r74) == true) goto L154;
        r89.zzL(r74);     // Catch: Throwable -> L45
    L154:
        String r010 = r47.zzd;     // Catch: Throwable -> L45
        if (TextUtils.isEmpty(r010) == true) goto L157;
        r89.zzJ(r010);     // Catch: Throwable -> L45
    L157:
        String r93 = r47.zzc;     // Catch: Throwable -> L45
        if (TextUtils.isEmpty(r93) == true) goto L160;
        r89.zzM(r93);     // Catch: Throwable -> L45
    L160:
        String r102 = r47.zzu;     // Catch: Throwable -> L45
        if (TextUtils.isEmpty(r102) == true) goto L163;
        r89.zzau(r102);     // Catch: Throwable -> L45
    L163:
        long r133 = r47.zzj;     // Catch: Throwable -> L45
        if (r133 == (-2147483648L)) goto L166;
        r89.zzaj((int) r133);     // Catch: Throwable -> L45
    L166:
        long r123 = r47.zze;     // Catch: Throwable -> L45
        r89.zzN(r123);     // Catch: Throwable -> L45
        String r118 = r47.zzb;     // Catch: Throwable -> L45
        if (TextUtils.isEmpty(r118) == true) goto L169;
        r89.zzad(r118);     // Catch: Throwable -> L45
    L169:
        zzjl r143 = zzB((String) Preconditions.checkNotNull(r74));     // Catch: Throwable -> L45
        String r15 = r47.zzs;     // Catch: Throwable -> L45
        String r19 = r102;
        zzjl r103 = r143.zzs(zzjl.zzf(r15, 100));     // Catch: Throwable -> L45
        r89.zzat(r103.zzk());     // Catch: Throwable -> L45
        zzaif.zza();     // Catch: Throwable -> L45
        if (zzd().zzp(r74, zzfy.zzaO) == true) goto L172;
    L214:
        long r21 = r123;
    L215:
        long r124 = r47.zzf;     // Catch: Throwable -> L45
        if (r124 == 0) goto L218;
        r89.zzY(r124);     // Catch: Throwable -> L45
    L218:
        long r125 = r47.zzq;     // Catch: Throwable -> L45
        r89.zzar(r125);     // Catch: Throwable -> L45
        if (zzd().zzp(null, zzfy.zzaU) == false) goto L222;
        zzd();     // Catch: Throwable -> L45
        r89.zzaQ(zzagr.zza());     // Catch: Throwable -> L45
    L222:
        if (zzd().zzp(null, zzfy.zzaV) == false) goto L226;
        List r6 = zzh().zzm(r74);     // Catch: Throwable -> L45
        if (r6 == null) goto L226;
        r89.zzaq(r6);     // Catch: Throwable -> L45
    L226:
        zzjl r62 = zzB((String) Preconditions.checkNotNull(r74)).zzs(zzjl.zzf(r15, 100));     // Catch: Throwable -> L45
        zzjk r104 = zzjk.zza;     // Catch: Throwable -> L45
        if (r62.zzo(r104) == false) goto L253;
        boolean r144 = r47.zzn;     // Catch: Throwable -> L45
        if (r144 == false) goto L253;
        Pair r152 = this.zzk.zzc(r47, r62);     // Catch: Throwable -> L45
        if (TextUtils.isEmpty((CharSequence) r152.first) == true) goto L253;
        if (r144 == false) goto L253;
        r89.zzQ((String) r152.first);     // Catch: Throwable -> L45
        Object r145 = r152.second;     // Catch: Throwable -> L45
        if (r145 == null) goto L236;
        r89.zzT(((Boolean) r145).booleanValue());     // Catch: Throwable -> L45
    L236:
        zzbc r20 = r53;
        if (r53.zzb.equals("_fx") == false) goto L239;
    L252:
        String r35 = r93;
        zzjk r38 = r104;
        long r36 = r125;
    L254:
        r92.zzu().zzw();     // Catch: Throwable -> L45
        r89.zzF(Build.MODEL);     // Catch: Throwable -> L45
        r92.zzu().zzw();     // Catch: Throwable -> L45
        r89.zzE(Build.VERSION.RELEASE);     // Catch: Throwable -> L45
        r89.zzI((int) r92.zzu().zzb());     // Catch: Throwable -> L45
        r89.zzH(r92.zzu().zzc());     // Catch: Throwable -> L45
        r89.zzay(r47.zzw);     // Catch: Throwable -> L45
        if (r92.zzB() == false) goto L259;
        r89.zzK();     // Catch: Throwable -> L45
        if (TextUtils.isEmpty(null) == true) goto L259;
        r89.zzam(null);     // Catch: Throwable -> L45
    L259:
        zzh r54 = zzj().zzu(r74);     // Catch: Throwable -> L45
        if (r54 != null) goto L265;
        r54 = new zzh(r92, r74);     // Catch: Throwable -> L45
        r54.zze(zzK(r62));     // Catch: Throwable -> L45
        r54.zzm(r47.zzk);     // Catch: Throwable -> L45
        r54.zzg(r118);     // Catch: Throwable -> L45
        if (r62.zzo(r38) == false) goto L264;
        r54.zzk(this.zzk.zzf(r47, r62));     // Catch: Throwable -> L45
    L264:
        r54.zzF(0);     // Catch: Throwable -> L45
        r54.zzo(0);     // Catch: Throwable -> L45
        r54.zzq(0);     // Catch: Throwable -> L45
        r54.zzs(r35);     // Catch: Throwable -> L45
        r54.zzu(r133);     // Catch: Throwable -> L45
        r54.zzw(r010);     // Catch: Throwable -> L45
        r54.zzy(r21);     // Catch: Throwable -> L45
        r54.zzA(r124);     // Catch: Throwable -> L45
        r54.zzE(r47.zzh);     // Catch: Throwable -> L45
        r54.zzC(r36);     // Catch: Throwable -> L45
        int r146 = 0;
        zzj().zzv(r54, false, false);     // Catch: Throwable -> L45
    L267:
        if (r62.zzo(zzjk.zzb) == false) goto L272;
        if (TextUtils.isEmpty(r54.zzd()) == true) goto L272;
        r89.zzW((String) Preconditions.checkNotNull(r54.zzd()));     // Catch: Throwable -> L45
    L272:
        if (TextUtils.isEmpty(r54.zzl()) == true) goto L274;
        r89.zzah((String) Preconditions.checkNotNull(r54.zzl()));     // Catch: Throwable -> L45
    L274:
        List r011 = zzj().zzn(r74);     // Catch: Throwable -> L45
        int r22 = r146;
    L276:
        if (r22 >= r011.size()) goto L323;
        com.google.android.gms.internal.measurement.zzit r43 = com.google.android.gms.internal.measurement.zziu.zzm();     // Catch: Throwable -> L45
        r43.zzb(((zzpn) r011.get(r22)).zzc);     // Catch: Throwable -> L45
        r43.zza(((zzpn) r011.get(r22)).zzd);     // Catch: Throwable -> L45
        zzp().zzc(r43, ((zzpn) r011.get(r22)).zze);     // Catch: Throwable -> L45
        r89.zzp(r43);     // Catch: Throwable -> L45
        if ("_sid".equals(((zzpn) r011.get(r22)).zzc) == true) goto L280;
    L285:
        String r63 = r19;
    L286:
        r22 = r22 + 1;
        r19 = r63;
        goto L276
    L280:
        if (r54.zzam() == 0) goto L285;
        r63 = r19;
        if (zzp().zzu(r63) == r54.zzam()) goto L286;
        r89.zzav();     // Catch: Throwable -> L45
        goto L286
    L323:
        zzaw r23 = zzj();     // Catch: Throwable -> L45 IOException -> L312
        com.google.android.gms.internal.measurement.zzid r44 = (com.google.android.gms.internal.measurement.zzid) r89.zzbd();     // Catch: Throwable -> L45 IOException -> L312
        r23.zzg();     // Catch: Throwable -> L45 IOException -> L312
        r23.zzay();     // Catch: Throwable -> L45 IOException -> L312
        Preconditions.checkNotNull(r44);     // Catch: Throwable -> L45 IOException -> L312
        Preconditions.checkNotEmpty(r44.zzA());     // Catch: Throwable -> L45 IOException -> L312
        byte[] r012 = r44.zzcd();     // Catch: Throwable -> L45 IOException -> L312
        long r55 = r23.zzg.zzp().zzt(r012);     // Catch: Throwable -> L45 IOException -> L312
        ContentValues r75 = new ContentValues();     // Catch: Throwable -> L45 IOException -> L312
        String r105 = r28;
        r75.put(r105, r44.zzA());     // Catch: Throwable -> L45 IOException -> L312
        String r119 = r27;
        r75.put(r119, Long.valueOf(r55));     // Catch: Throwable -> L45 IOException -> L312
        r75.put("metadata", r012);     // Catch: Throwable -> L45 IOException -> L312
        r23.zze().insertWithOnConflict("raw_events_metadata", null, r75, 4);     // Catch: Throwable -> L45 IOException -> L312 SQLiteException -> L314
        zzaw r26 = zzj();     // Catch: Throwable -> L45
        zzbc r810 = r20;
        zzbe r45 = new zzbe(r810.zzg);     // Catch: Throwable -> L45
    L291:
        if (r45.hasNext() == false) goto L295;
        if ("_r".equals(r45.zza()) == false) goto L291;
    L294:
        int r134 = 1;
    L301:
        r26.zzg();     // Catch: Throwable -> L45
        r26.zzay();     // Catch: Throwable -> L45
        Preconditions.checkNotNull(r810);     // Catch: Throwable -> L45
        String r013 = r810.zza;     // Catch: Throwable -> L45
        Preconditions.checkNotEmpty(r013);     // Catch: Throwable -> L45
        byte[] r33 = r26.zzg.zzp().zzh(r810).zzcd();     // Catch: Throwable -> L45
        ContentValues r48 = new ContentValues();     // Catch: Throwable -> L45
        r48.put(r105, r013);     // Catch: Throwable -> L45
        r48.put("name", r810.zzb);     // Catch: Throwable -> L45
        r48.put("timestamp", Long.valueOf(r810.zzd));     // Catch: Throwable -> L45
        r48.put(r119, Long.valueOf(r55));     // Catch: Throwable -> L45
        r48.put("data", r33);     // Catch: Throwable -> L45
        r48.put("realtime", Integer.valueOf(r134));     // Catch: Throwable -> L45
        r48.put("elapsed_time", Long.valueOf(r810.zze));     // Catch: Throwable -> L45
    L306:
        e = move-exception;
        r26.zzu.zzaW().zzb().zzc("Error storing raw event. appId", zzgu.zzl(r810.zza), e);     // Catch: Throwable -> L45
    L318:
        zzj().zzc();     // Catch: Throwable -> L45
        zzj().zzd();
        zzaM();
        zzaW().zzk().zzb("Background event processing time, ms", Long.valueOf(((System.nanoTime() - r25) + 500000) / 1000000));
        return;
    L303:
        if (r26.zze().insert("raw_events", null, r48) != (-1)) goto L309;
        r26.zzu.zzaW().zzb().zzb("Failed to insert raw event (got -1). appId", zzgu.zzl(r013));     // Catch: Throwable -> L45 SQLiteException -> L306
    L309:
        this.zza = 0;     // Catch: Throwable -> L45
        goto L318
    L295:
        zzht r014 = zzh();     // Catch: Throwable -> L45
        String r34 = r810.zza;     // Catch: Throwable -> L45
        boolean r015 = r014.zzk(r34, r810.zzb);     // Catch: Throwable -> L45
        zzar r37 = zzj().zzw(zzC(), r34, false, false, false, false, false, false, false);     // Catch: Throwable -> L45
        if (r015 == true) goto L298;
    L300:
        r134 = r146;
        goto L301
    L298:
        if (r37.zze >= zzd().zzm(r34, zzfy.zzo)) goto L300;
    L314:
        e = move-exception;
        r23.zzu.zzaW().zzb().zzc("Error storing raw event metadata. appId", zzgu.zzl(r44.zzA()), e);     // Catch: Throwable -> L45 IOException -> L312
        throw e;     // Catch: Throwable -> L45 IOException -> L312
    L312:
        e = move-exception;
        zzaW().zzb().zzc("Data loss. Failed to insert raw event metadata. appId", zzgu.zzl(r89.zzK()), e);     // Catch: Throwable -> L45
        goto L318
    L265:
        r146 = 0;
        goto L267
    L239:
        if (((String) r152.first).equals("00000000-0000-0000-0000-000000000000") == true) goto L252;
        zzh r147 = zzj().zzu(r74);     // Catch: Throwable -> L45
        if (r147 == null) goto L252;
        if (r147.zzaq() == false) goto L252;
        zzR(r74, false, null, null);     // Catch: Throwable -> L45
        Bundle r148 = new Bundle();     // Catch: Throwable -> L45
        Long r153 = r147.zzas();     // Catch: Throwable -> L45
        if (r153 == null) goto L247;
        r36 = r125;
        r35 = r93;
        r38 = r104;
        r148.putLong("_pfo", Math.max(0, r153.longValue()));     // Catch: Throwable -> L45
    L248:
        Long r94 = r147.zzau();     // Catch: Throwable -> L45
        if (r94 == null) goto L251;
        r148.putLong("_uwa", r94.longValue());     // Catch: Throwable -> L45
    L251:
        r148.putLong("_r", r32);     // Catch: Throwable -> L45
        this.zzK.zza(r74, "_fx", r148);     // Catch: Throwable -> L45
        goto L254
    L247:
        r35 = r93;
        r38 = r104;
        r36 = r125;
    L253:
        r20 = r53;
        goto L252
    L172:
        if (zzt().zzZ(r74) == false) goto L214;
        r89.zzaH(r47.zzz);     // Catch: Throwable -> L45
        r21 = r123;
        long r126 = r47.zzA;     // Catch: Throwable -> L45
        if (r103.zzo(zzjk.zza) == true) goto L179;
        if (r126 == 0) goto L179;
        r126 = (r126 & (-2)) | 32;     // Catch: Throwable -> L45
    L179:
        if (r126 != r32) goto L181;
        boolean r64 = true;
    L182:
        r89.zzaz(r64);     // Catch: Throwable -> L45
        if (r126 == 0) goto L215;
        com.google.android.gms.internal.measurement.zzhd r65 = com.google.android.gms.internal.measurement.zzhe.zzi();     // Catch: Throwable -> L45
        if ((r126 & r32) == 0) goto L188;
        boolean r106 = true;
    L189:
        r65.zza(r106);     // Catch: Throwable -> L45
        if ((r126 & 2) == 0) goto L192;
        boolean r107 = true;
    L193:
        r65.zzb(r107);     // Catch: Throwable -> L45
        if ((r126 & 4) == 0) goto L196;
        boolean r108 = true;
    L197:
        r65.zzc(r108);     // Catch: Throwable -> L45
        if ((r126 & 8) == 0) goto L200;
        boolean r109 = true;
    L201:
        r65.zzd(r109);     // Catch: Throwable -> L45
        if ((r126 & 16) == 0) goto L204;
        boolean r1010 = true;
    L205:
        r65.zze(r1010);     // Catch: Throwable -> L45
        if ((r126 & 32) == 0) goto L208;
        boolean r1011 = true;
    L209:
        r65.zzf(r1011);     // Catch: Throwable -> L45
        if ((r126 & 64) == 0) goto L212;
        boolean r135 = true;
    L213:
        r65.zzg(r135);     // Catch: Throwable -> L45
        r89.zzaI((com.google.android.gms.internal.measurement.zzhe) r65.zzbd());     // Catch: Throwable -> L45
        goto L215
    L212:
        r135 = false;
        goto L213
    L208:
        r1011 = false;
        goto L209
    L204:
        r1010 = false;
        goto L205
    L200:
        r109 = false;
        goto L201
    L196:
        r108 = false;
        goto L197
    L192:
        r107 = false;
        goto L193
    L188:
        r106 = false;
        goto L189
    L181:
        r64 = false;
        goto L182
    L142:
        if (zzj().zzT(r113) < zzd().zzh(r113)) goto L149;
        if (r83 == false) goto L149;
        if (zzt().zzq(r73) == true) goto L149;
        zzaW().zzb().zzd("Too many event names used, ignoring event. appId, name, supported count", zzgu.zzl(r113), r92.zzl().zza(r73), Integer.valueOf(zzd().zzh(r113)));     // Catch: Throwable -> L45
        zzt().zzP(this.zzK, r113, 8, null, null, 0);     // Catch: Throwable -> L45
    L149:
        r88 = new zzbd(r113, r73, 0, 0, 0, r86.zzd, 0, null, null, null, null);     // Catch: Throwable -> L45
        r53 = r86;
        goto L151
    L134:
        e = move-exception;
        r7.zzu.zzaW().zzb().zzc("Error deleting over the limit events. appId", zzgu.zzl(r113), e);     // Catch: Throwable -> L45
        r72 = 0;
        goto L137
    L116:
        if (r122 != 1) goto L118;
        zzaW().zzb().zzc("Too many error events logged. appId, count", zzgu.zzl(r113), Long.valueOf(r84.zzd));     // Catch: Throwable -> L45
    L118:
        zzj().zzc();     // Catch: Throwable -> L45
        goto L147
    L119:
        r32 = 1;
        r42 = 0;
        goto L120
    L101:
        if ((r115 % 1000) != 1) goto L103;
        zzaW().zzb().zzc("Data loss. Too many events logged. appId, count", zzgu.zzl(r113), Long.valueOf(r84.zzb));     // Catch: Throwable -> L45
    L103:
        zzj().zzc();     // Catch: Throwable -> L45
        goto L147
    L92:
        zzbe r136 = new zzbe(r9);     // Catch: Throwable -> L45
        r14 = 0;
    L94:
        if (r136.hasNext() == false) goto L98;
        if ((r9.zza(r136.zza()) instanceof Parcelable[]) == false) goto L94;
        r14 = r14 + ((Parcelable[]) r11).length;     // Catch: Throwable -> L45
    L53:
        zzbf r137 = r82.zzb;     // Catch: Throwable -> L45
        String r149 = r137.zzd("currency");     // Catch: Throwable -> L45
        r27 = "metadata_fingerprint";
        if (r04 == false) goto L68;
        double r18 = r137.zzc("value").doubleValue() * 1000000.0d;     // Catch: Throwable -> L45
        if (r18 != 0.0d) goto L59;
        r28 = "app_id";
        r18 = r137.zzb("value").longValue() * 1000000.0d;     // Catch: Throwable -> L45
    L61:
        if (r18 <= 9.223372036854776E18d) goto L63;
    L67:
        zzaW().zze().zzc("Data lost. Currency value is too big. appId", zzgu.zzl(r10), Double.valueOf(r18));     // Catch: Throwable -> L45
        zzj().zzc();     // Catch: Throwable -> L45
        goto L147
    L63:
        if (r18 < (-9.223372036854776E18d)) goto L67;
        long r39 = Math.round(r18);     // Catch: Throwable -> L45
        if ("refund".equals(r112) == false) goto L70;
        r39 = -r39;
    L70:
        if (TextUtils.isEmpty(r149) == true) goto L52;
        String r016 = r149.toUpperCase(Locale.US);     // Catch: Throwable -> L45
        if (r016.matches("[A-Z]{3}") == false) goto L52;
        String r1110 = "_ltv_".concat(r016);     // Catch: Throwable -> L45
        zzpn r017 = zzj().zzm(r10, r1110);     // Catch: Throwable -> L45
        if (r017 == null) goto L77;
        Object r018 = r017.zze;     // Catch: Throwable -> L45
        if ((r018 instanceof Long) == false) goto L77;
        r4 = r82;
        zzpn r811 = new zzpn(r10, r82.zzc, r1110, zzba().currentTimeMillis(), Long.valueOf(((Long) r018).longValue() + r39));     // Catch: Throwable -> L45
        r10 = r10;
    L79:
        zzpn r019 = r811;
        if (zzj().zzl(r019) == true) goto L89;
        zzaW().zzb().zzd("Too many unique user properties are set. Ignoring user property. appId", zzgu.zzl(r10), this.zzn.zzl().zzc(r019.zzc), r019.zze);     // Catch: Throwable -> L45
        zzt().zzP(this.zzK, r10, 9, null, null, 0);     // Catch: Throwable -> L45
    L77:
        long r432 = r39;
        r4 = r82;
        zzaw r138 = zzj();     // Catch: Throwable -> L45
        int r020 = zzd().zzm(r10, zzfy.zzT) - 1;
        Preconditions.checkNotEmpty(r10);     // Catch: Throwable -> L45
        r138.zzg();     // Catch: Throwable -> L45
        r138.zzay();     // Catch: Throwable -> L45
        r138.zze().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '!_ltv!_%' escape '!'order by set_timestamp desc limit ?,10);", new String[]{r10, r10, String.valueOf(r020)});     // Catch: Throwable -> L45 SQLiteException -> L83
    L85:
        r811 = new zzpn(r10, r4.zzc, r1110, zzba().currentTimeMillis(), Long.valueOf(r432));     // Catch: Throwable -> L45
        r10 = r10;
    L83:
        e = move-exception;
        r138.zzu.zzaW().zzb().zzc("Error pruning currencies. appId", zzgu.zzl(r10), e);     // Catch: Throwable -> L45
        goto L85
    L59:
        r28 = "app_id";
        goto L61
    L68:
        r28 = "app_id";
        r39 = r137.zzb("value").longValue();     // Catch: Throwable -> L45
        goto L70
    L40:
        if ("purchase".equals(r112) == true) goto L43;
        if ("refund".equals(r112) == true) goto L43;
        r04 = false;
    L45:
        th = move-exception;
        zzj().zzd();
        throw th;
    }

    public final void zzI(zzh r11, com.google.android.gms.internal.measurement.zzic r12) {
        zzaX().zzg();
        zzu();
        zzan r0 = zzan.zzd(r12.zzaA());
        String r1 = r11.zzc();
        zzaX().zzg();
        zzu();
        zzjl r13 = zzB(r1);
        zzji r2 = zzji.zza;
        int r22 = r13.zzp().ordinal();
        if (r22 == 1) goto L8;
        if (r22 == 2) goto L7;
        if (r22 == 3) goto L7;
        r0.zzc(zzjk.zza, zzam.zzj);
    L9:
        int r23 = r13.zzq().ordinal();
        if (r23 == 1) goto L15;
        if (r23 == 2) goto L14;
        if (r23 == 3) goto L14;
        r0.zzc(zzjk.zzb, zzam.zzj);
    L16:
        String r14 = r11.zzc();
        zzaX().zzg();
        zzu();
        zzba r15 = zzz(r14, zzx(r14), zzB(r14), r0);
        r12.zzaD(((Boolean) Preconditions.checkNotNull(r15.zzj())).booleanValue());
        if (TextUtils.isEmpty(r15.zzk()) == true) goto L19;
        r12.zzaF(r15.zzk());
    L19:
        zzaX().zzg();
        zzu();
        Iterator r16 = r12.zzk().iterator();
    L21:
        if (r16.hasNext() == false) goto L25;
        com.google.android.gms.internal.measurement.zziu r24 = (com.google.android.gms.internal.measurement.zziu) r16.next();
        if ("_npa".equals(r24.zzc()) == false) goto L21;
    L26:
        if (r24 == null) goto L52;
        zzjk r17 = zzjk.zzd;
        if (r0.zza(r17) != zzam.zza) goto L53;
        zzpn r3 = zzj().zzm(r11.zzc(), "_npa");
        if (r3 == null) goto L39;
        String r25 = r3.zzb;
        if ("tcf".equals(r25) == false) goto L36;
        r0.zzc(r17, zzam.zzh);
        goto L53
    L36:
        if (Common.ASSET_APP.equals(r25) == false) goto L38;
        r0.zzc(r17, zzam.zzf);
        goto L53
    L38:
        r0.zzc(r17, zzam.zzd);
        goto L53
    L39:
        Boolean r32 = r11.zzae();
        if (r32 != null) goto L42;
    L51:
        r0.zzc(r17, zzam.zzf);
        goto L53
    L42:
        if (r32.booleanValue() == false) goto L46;
        if (r24.zzh() != 1) goto L51;
    L46:
        if (r32.booleanValue() == false) goto L48;
    L50:
        r0.zzc(r17, zzam.zzd);
        goto L53
    L48:
        if (r24.zzh() == 0) goto L50;
    L53:
        r12.zzaB(r0.toString());
        boolean r10 = this.zzc.zzz(r11.zzc());
        List r112 = r12.zzb();
        int r02 = 0;
        int r18 = 0;
    L55:
        if (r18 >= r112.size()) goto L81;
        if ("_tcf".equals(((com.google.android.gms.internal.measurement.zzhs) r112.get(r18)).zzd()) == true) goto L58;
        r18 = r18 + 1;
        goto L55
    L58:
        com.google.android.gms.internal.measurement.zzhr r113 = (com.google.android.gms.internal.measurement.zzhr) ((com.google.android.gms.internal.measurement.zzhs) r112.get(r18)).zzco();
        List r26 = r113.zza();
        int r33 = 0;
    L60:
        if (r33 >= r26.size()) goto L78;
        if ("_tcfd".equals(((com.google.android.gms.internal.measurement.zzhw) r26.get(r33)).zzb()) == true) goto L63;
        r33 = r33 + 1;
        goto L60
    L63:
        String r27 = ((com.google.android.gms.internal.measurement.zzhw) r26.get(r33)).zzd();
        if (r10 == true) goto L66;
    L76:
        zzhv r102 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r102.zzb("_tcfd");
        r102.zzd(r27);
        r113.zze(r33, r102);
        goto L78
    L66:
        if (r27.length() <= 4) goto L76;
        char[] r103 = r27.toCharArray();
        int r28 = 1;
    L70:
        if (r28 >= 64) goto L75;
        if (r103[4] == "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(r28)) goto L73;
        r28 = r28 + 1;
        goto L70
    L73:
        r02 = r28;
    L75:
        r103[4] = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_".charAt(r02 | 1);
        r27 = String.valueOf(r103);
    L78:
        r12.zzf(r18, r113);
        return;
    L81:
        return;
    L52:
        int r19 = zzaD(r11.zzc(), r0);
        com.google.android.gms.internal.measurement.zzit r29 = com.google.android.gms.internal.measurement.zziu.zzm();
        r29.zzb("_npa");
        r29.zza(zzba().currentTimeMillis());
        r29.zze(r19);
        r12.zzo((com.google.android.gms.internal.measurement.zziu) r29.zzbd());
        zzaW().zzk().zzc("Setting user property", "non_personalized_ads(_npa)", Integer.valueOf(r19));
        goto L53
    L25:
        r24 = null;
    L14:
        r0.zzb(zzjk.zzb, r13.zzb());
        goto L16
    L15:
        r0.zzc(zzjk.zzb, zzam.zzi);
    L7:
        r0.zzb(zzjk.zza, r13.zzb());
        goto L9
    L8:
        r0.zzc(zzjk.zza, zzam.zzi);
        goto L9
    }

    public final void zzJ(zzh r14, com.google.android.gms.internal.measurement.zzic r15) {
        zzaX().zzg();
        zzu();
        com.google.android.gms.internal.measurement.zzgx r0 = com.google.android.gms.internal.measurement.zzha.zzt();
        byte[] r1 = r14.zzaJ();
        if (r1 != null) goto L81;
    L7:
        Iterator r12 = r15.zzb().iterator();
    L9:
        if (r12.hasNext() == false) goto L56;
        com.google.android.gms.internal.measurement.zzhs r2 = (com.google.android.gms.internal.measurement.zzhs) r12.next();
        if (r2.zzd().equals("_cmp") == false) goto L9;
        String r4 = (String) zzpk.zzN(r2, "gclid", _UrlKt.FRAGMENT_ENCODE_SET);
        String r7 = (String) zzpk.zzN(r2, "gbraid", _UrlKt.FRAGMENT_ENCODE_SET);
        String r8 = (String) zzpk.zzN(r2, "gad_source", _UrlKt.FRAGMENT_ENCODE_SET);
        String r3 = (String) zzpk.zzN(r2, "deep_link_url", _UrlKt.FRAGMENT_ENCODE_SET);
        String[] r6 = ((String) zzfy.zzbb.zzb(null)).split(",");
        zzp();
        if (zzpk.zzK(r2, r6).isEmpty() == true) goto L9;
        long r11 = ((Long) zzpk.zzN(r2, "click_timestamp", 0L)).longValue();
        if (r11 > 0) goto L18;
        r11 = r2.zzf();
    L18:
        if ("referrer API v2".equals(zzpk.zzM(r2, "_cis")) == true) goto L20;
        if (r11 <= r0.zzg()) goto L9;
        if (r4.isEmpty() == false) goto L39;
        r0.zzb();
    L41:
        if (r7.isEmpty() == false) goto L43;
        r0.zzd();
    L45:
        if (r8.isEmpty() == false) goto L47;
        r0.zzf();
    L49:
        if (zzd().zzp(null, zzfy.zzba) == true) goto L51;
    L54:
        r0.zzh(r11);
        r0.zzq();
        r0.zzr(zzaE(r2));
        goto L9
    L51:
        if (r3.isEmpty() == false) goto L53;
        r0.zzv();
        goto L54
    L53:
        r0.zzu(r3);
        goto L54
    L47:
        r0.zze(r8);
        goto L49
    L43:
        r0.zzc(r7);
        goto L45
    L39:
        r0.zza(r4);
        goto L41
    L20:
        if (r11 <= r0.zzo()) goto L9;
        if (r4.isEmpty() == false) goto L24;
        r0.zzj();
    L26:
        if (r7.isEmpty() == false) goto L28;
        r0.zzl();
    L30:
        if (r8.isEmpty() == false) goto L32;
        r0.zzn();
    L33:
        r0.zzp(r11);
        r0.zzs();
        r0.zzt(zzaE(r2));
        goto L9
    L32:
        r0.zzm(r8);
        goto L33
    L28:
        r0.zzk(r7);
        goto L30
    L24:
        r0.zzi(r4);
        goto L26
    L56:
        if (((com.google.android.gms.internal.measurement.zzha) r0.zzbd()).equals(com.google.android.gms.internal.measurement.zzha.zzu()) == true) goto L58;
        r15.zzaM((com.google.android.gms.internal.measurement.zzha) r0.zzbd());
    L58:
        r14.zzaI(((com.google.android.gms.internal.measurement.zzha) r0.zzbd()).zzcd());
        if (r14.zza() == false) goto L62;
        zzj().zzv(r14, false, false);
    L62:
        if (zzd().zzp(null, zzfy.zzba) == false) goto L78;
        int r02 = 0;
    L65:
        if (r02 >= r15.zzc()) goto L78;
        com.google.android.gms.internal.measurement.zzhs r22 = r15.zzd(r02);
        if ("_cmp".equals(r22.zzd()) == false) goto L76;
        com.google.android.gms.internal.measurement.zzhr r23 = (com.google.android.gms.internal.measurement.zzhr) r22.zzco();
        List r62 = r23.zza();
        int r72 = 0;
    L71:
        if (r72 >= r62.size()) goto L76;
        if ("deep_link_url".equals(((com.google.android.gms.internal.measurement.zzhw) r62.get(r72)).zzb()) == true) goto L74;
        r72 = r72 + 1;
        goto L71
    L74:
        r23.zzj(r72);
        r15.zzf(r02, r23);
    L76:
        r02 = r02 + 1;
    L78:
        if (zzd().zzp(null, zzfy.zzaZ) == false) goto L102;
        zzj().zzk(r14.zzc(), "_lgclid");
        return;
    L102:
        return;
    L81:
        r0 = (com.google.android.gms.internal.measurement.zzgx) zzpk.zzw(r0, r1);     // Catch: zzaeh -> L6
    L6:
        zzaW().zze().zzb("Failed to parse locally stored ad campaign info. appId", zzgu.zzl(r14.zzc()));
        goto L7
    }

    public final String zzK(zzjl r3) {
        if (r3.zzo(zzjk.zzb) == false) goto L6;
        byte[] r32 = new byte[16];
        zzt().zzf().nextBytes(r32);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r32)});
    L6:
        return null;
    }

    public final void zzL(List r2) {
        Preconditions.checkArgument(!r2.isEmpty());
        if (this.zzz == null) goto L6;
        zzaW().zzb().zza("Set uploading progress before finishing the previous upload");
        return;
    L6:
        this.zzz = new ArrayList(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.google.android.gms.measurement.internal.zzpg] */
    /* JADX WARN: Type inference failed for: r1v12, types: [long] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v22, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v25, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v35 */
    public final void zzM() {
        zzaX().zzg();
        zzu();
        this.zzw = true;
        zzic r1 = this.zzn;     // Catch: Throwable -> L6
        r1.zzaV();     // Catch: Throwable -> L6
        Boolean r12 = r1.zzt().zzJ();     // Catch: Throwable -> L6
        if (r12 != null) goto L9;
        zzaW().zze().zza("Upload data called on the client side before use of service was decided");     // Catch: Throwable -> L6
    L75:
        this.zzw = false;
        zzaN();
        return;
    L9:
        if (r12.booleanValue() == false) goto L12;
        zzaW().zzb().zza("Upload called in the client side when service should be used");     // Catch: Throwable -> L6
        goto L75
    L12:
        if (this.zza <= 0) goto L14;
        zzaM();     // Catch: Throwable -> L6
        goto L75
    L14:
        zzaX().zzg();     // Catch: Throwable -> L6
        if (this.zzz == null) goto L18;
        zzaW().zzk().zza("Uploading requested multiple times");     // Catch: Throwable -> L6
        goto L75
    L18:
        if (zzi().zzb() == true) goto L20;
        zzaW().zzk().zza("Network not connected, ignoring upload request");     // Catch: Throwable -> L6
        zzaM();     // Catch: Throwable -> L6
        goto L75
    L20:
        ?? r13 = zzba().currentTimeMillis();     // Catch: Throwable -> L6
        Cursor r7 = null;
        Cursor r72 = null;
        r72 = null;
        String r73 = null;
        r73 = null;
        r73 = null;
        int r5 = zzd().zzm(null, zzfy.zzah);     // Catch: Throwable -> L6
        zzd();     // Catch: Throwable -> L6
        long r8 = r13 - zzal.zzF();     // Catch: Throwable -> L6
        int r6 = 0;
    L21:
        if (r6 >= r5) goto L25;
        if (zzaH(null, r8) == false) goto L25;
        r6 = r6 + 1;     // Catch: Throwable -> L6
    L25:
        zzaif.zza();     // Catch: Throwable -> L6
        zzaX().zzg();     // Catch: Throwable -> L6
        zzaG();     // Catch: Throwable -> L6
        long r52 = this.zzk.zzd.zza();     // Catch: Throwable -> L6
        if (r52 == 0) goto L28;
        zzaW().zzj().zzb("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(r13 - r52)));     // Catch: Throwable -> L6
    L28:
        String r3 = zzj().zzF();     // Catch: Throwable -> L6
        long r53 = -1;
        if (TextUtils.isEmpty(r3) == false) goto L31;
        this.zzB = -1;     // Catch: Throwable -> L6
        zzaw r32 = zzj();     // Catch: Throwable -> L6
        zzd();     // Catch: Throwable -> L6
        long r14 = r13 - zzal.zzF();     // Catch: Throwable -> L6
        r32.zzg();     // Catch: Throwable -> L6
        r32.zzay();     // Catch: Throwable -> L6
        r13 = r32.zze().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(r14)});     // Catch: Throwable -> L63 SQLiteException -> L65
    L57:
        SQLiteException e = e;
        r13 = r13;
    L67:
        r32.zzu.zzaW().zzb().zzb("Error selecting expired configs", e);     // Catch: Throwable -> L55
        if (r13 == 0) goto L71;
    L60:
        r13.close();     // Catch: Throwable -> L6
    L71:
        if (TextUtils.isEmpty(r73) == true) goto L75;
        zzh r15 = zzj().zzu(r73);     // Catch: Throwable -> L6
        if (r15 == null) goto L75;
        zzX(r15);     // Catch: Throwable -> L6
        goto L75
    L53:
        if (r13.moveToFirst() == true) goto L59;
        r32.zzu.zzaW().zzk().zza("No expired configs for apps with pending events");     // Catch: Throwable -> L55 SQLiteException -> L57
    L87:
        goto L60
    L59:
        r73 = r13.getString(0);     // Catch: Throwable -> L55 SQLiteException -> L57
        goto L87
    L65:
        e = move-exception;
        e = e;
        r13 = 0;
    L63:
        th = move-exception;
        th = th;
    L77:
        if (r7 == null) goto L95;
        r7.close();     // Catch: Throwable -> L6
        throw th;     // Catch: Throwable -> L6
    L95:
        throw th;     // Catch: Throwable -> L6
    L55:
        th = th;
        r7 = r13;
        goto L77
    L31:
        if (this.zzB != (-1)) goto L49;
        zzaw r4 = zzj();     // Catch: Throwable -> L6
        r72 = r4.zze().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);     // Catch: Throwable -> L39 SQLiteException -> L41
        if (r72.moveToFirst() == true) goto L37;
    L35:
        r72.close();     // Catch: Throwable -> L6
    L45:
        this.zzB = r53;     // Catch: Throwable -> L6
        goto L49
    L37:
        r53 = r72.getLong(0);     // Catch: Throwable -> L39 SQLiteException -> L41
    L39:
        th = move-exception;
        if (r72 == null) goto L48;
        r72.close();     // Catch: Throwable -> L6
    L48:
        throw th;     // Catch: Throwable -> L6
    L41:
        e = move-exception;
        r4.zzu.zzaW().zzb().zzb("Error querying raw events", e);     // Catch: Throwable -> L39
        if (r72 == null) goto L45;
    L49:
        zzN(r3, r13);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        this.zzw = false;
        zzaN();
        throw th;
    }

    public final void zzN(String r33, long r34) {
        int r0 = zzd().zzm(r33, zzfy.zzg);
        int r7 = 0;
        int r5 = Math.max(0, zzd().zzm(r33, zzfy.zzh));
        zzaw r8 = zzj();
        r8.zzg();
        r8.zzay();
        int r9 = 1;
        if (r0 <= 0) goto L5;
        boolean r10 = true;
    L6:
        Preconditions.checkArgument(r10);
        if (r5 <= 0) goto L9;
        boolean r102 = true;
    L10:
        Preconditions.checkArgument(r102);
        Preconditions.checkNotEmpty(r33);
        long r23 = -1;
        Cursor r103 = r8.zze().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{r33}, null, null, "rowid", String.valueOf(r0));     // Catch: Throwable -> L99 SQLiteException -> L101
    L416:
    L21:
        e = e;
        Cursor r22 = r103;
    L98:
        r103 = r22;
    L394:
        r8.zzu.zzaW().zzb().zzc("Error querying bundles. appId", zzgu.zzl(r33), e);     // Catch: Throwable -> L381
        List r02 = Collections.EMPTY_LIST;     // Catch: Throwable -> L381
        if (r103 != null) goto L17;
    L18:
        List r11 = r02;
    L111:
        if (r11.isEmpty() == true) goto L476;
        zzahh.zza();
        zzal r03 = zzd();
        zzfx r52 = zzfy.zzbc;
        if (r03.zzp(null, r52) == false) goto L229;
        zzahh.zza();
        if (zzd().zzp(null, r52) == false) goto L226;
        if (zzB(r33).zzo(zzjk.zzb) == false) goto L120;
    L145:
        ArrayList r72 = new ArrayList(r11.size());
        zzaw r92 = zzj();
        Preconditions.checkNotEmpty(r33);
        r92.zzg();
        r92.zzay();
        List r104 = new ArrayList();
        SQLiteDatabase r21 = r92.zze();     // Catch: Throwable -> L167 SQLiteException -> L179
        long r14 = r92.zzu.zzba().currentTimeMillis();     // Catch: Throwable -> L167 SQLiteException -> L179
        Cursor r4 = r21.query("no_data_mode_events", new String[]{"data"}, "app_id=? AND timestamp_millis <= CAST(? AS INTEGER)", new String[]{r33, String.valueOf(r14)}, null, null, "rowid", null);     // Catch: Throwable -> L167 SQLiteException -> L179
        List r16 = r11;
    L156:
        e = e;
        Cursor r232 = r4;
    L183:
        r92.zzu.zzaW().zzb().zzc("Error flushing NO_DATA mode events. appId", zzgu.zzl(r33), e);     // Catch: Throwable -> L171
        r104 = Collections.EMPTY_LIST;     // Catch: Throwable -> L171
        if (r232 != null) goto L177;
    L186:
        Iterator r04 = r16.iterator();
        boolean r42 = true;
    L188:
        if (r04.hasNext() == false) goto L220;
        Pair r53 = (Pair) r04.next();
        com.google.android.gms.internal.measurement.zzic r93 = (com.google.android.gms.internal.measurement.zzic) ((com.google.android.gms.internal.measurement.zzid) r53.first).zzco();
        if (r42 == true) goto L192;
    L194:
        com.google.android.gms.internal.measurement.zzhh r112 = com.google.android.gms.internal.measurement.zzho.zzb();
        com.google.android.gms.internal.measurement.zzgf r12 = zzh().zzy(r33);
        ArrayList r13 = new ArrayList();
        if (r12 != null) goto L197;
    L196:
        Iterator r212 = r04;
        boolean r162 = r42;
        List r233 = r104;
        r112.zza(r13);
        r93.zzaP(r112);
        r72.add(Pair.create((com.google.android.gms.internal.measurement.zzid) r93.zzbd(), (Long) r53.second));
        r42 = r162;
        r04 = r212;
        r104 = r233;
        goto L188
    L197:
        Iterator r122 = r12.zza().iterator();
    L199:
        if (r122.hasNext() == false) goto L196;
        com.google.android.gms.internal.measurement.zzfu r142 = (com.google.android.gms.internal.measurement.zzfu) r122.next();
        com.google.android.gms.internal.measurement.zzhk r15 = com.google.android.gms.internal.measurement.zzhl.zza();
        int r163 = r142.zzb();
        zzji r213 = zzji.zza;
        Iterator r214 = r04;
        int r05 = r163 - 1;
        boolean r164 = r42;
        if (r05 != 1) goto L203;
        List r234 = r104;
        int r105 = 3;
        int r06 = 2;
    L212:
        r15.zza(r06);
        int r07 = r142.zzd() - 1;
        if (r07 != 1) goto L215;
        r105 = 2;
    L218:
        r15.zzb(r105);
        r13.add((com.google.android.gms.internal.measurement.zzhl) r15.zzbd());
        r42 = r164;
        r04 = r214;
        r104 = r234;
        goto L199
    L215:
        if (r07 == 2) goto L218;
        r105 = 1;
        goto L218
    L203:
        if (r05 == 2) goto L210;
        r234 = r104;
        r105 = 3;
        if (r05 == 3) goto L209;
        if (r05 == 4) goto L208;
        r06 = 1;
        goto L212
    L208:
        r06 = 5;
        goto L212
    L209:
        r06 = 4;
        goto L212
    L210:
        r234 = r104;
        r105 = 3;
        r06 = 3;
        goto L212
    L192:
        if (r104.isEmpty() == true) goto L194;
        List r43 = r93.zzb();
        r93.zzi();
        r93.zzh(r104);
        r93.zzh(r43);
        r42 = false;
        goto L194
    L220:
        r11 = r72;
    L177:
        r232.close();
    L171:
        th = th;
    L178:
        Cursor r132 = r232;
    L221:
        if (r132 == null) goto L223;
        r132.close();
    L223:
        throw th;
    L154:
        th = th;
        r232 = r4;
        goto L178
    L149:
        if (r4.moveToFirst() == true) goto L398;
        r232 = r4;
    L398:
        r104.add((com.google.android.gms.internal.measurement.zzhs) ((com.google.android.gms.internal.measurement.zzhr) zzpk.zzw(com.google.android.gms.internal.measurement.zzhs.zzp(), r4.getBlob(0))).zzbd());     // Catch: Throwable -> L154 SQLiteException -> L156 zzaeh -> L158
        r232 = r4;
    L163:
        if (r232.moveToNext() == false) goto L164;
        r4 = r232;
        goto L398
    L164:
        r232.close();     // Catch: Throwable -> L171 SQLiteException -> L173
        int r08 = r21.delete("no_data_mode_events", "app_id=? AND timestamp_millis <= CAST(? AS INTEGER)", new String[]{r33, String.valueOf(r14)});     // Catch: Throwable -> L167 SQLiteException -> L169
        zzgs r44 = r92.zzu.zzaW().zzk();     // Catch: Throwable -> L167 SQLiteException -> L169
        StringBuilder r113 = new StringBuilder(String.valueOf(r08).length() + 34);     // Catch: Throwable -> L167 SQLiteException -> L169
        r113.append("Pruned ");     // Catch: Throwable -> L167 SQLiteException -> L169
        r113.append(r08);     // Catch: Throwable -> L167 SQLiteException -> L169
        r113.append(" NO_DATA mode events. appId");     // Catch: Throwable -> L167 SQLiteException -> L169
        r44.zzb(r113.toString(), r33);     // Catch: Throwable -> L167 SQLiteException -> L169
    L169:
        e = e;
    L182:
        r232 = null;
    L173:
        e = e;
        goto L183
    L158:
        e = move-exception;
        r232 = r4;
        r92.zzu.zzaW().zzh().zzc("Failed to parse stored NO_DATA mode event, appId", zzgu.zzl(r33), e);     // Catch: Throwable -> L171 SQLiteException -> L173
    L179:
        e = e;
        r16 = r11;
    L167:
        th = th;
        r132 = null;
        goto L221
    L120:
        if (zzh().zzC(r33) == false) goto L145;
        List r73 = Arrays.asList(((String) zzfy.zzbd.zzb(null)).split(","));
        Iterator r94 = r11.iterator();
    L123:
        if (r94.hasNext() == false) goto L144;
        Pair r09 = (Pair) r94.next();
        zzj().zzH(((Long) r09.second).longValue());     // Catch: SQLiteException -> L143
        Iterator r106 = ((com.google.android.gms.internal.measurement.zzid) r09.first).zzc().iterator();     // Catch: SQLiteException -> L143
    L126:
        if (r106.hasNext() == false) goto L123;
        com.google.android.gms.internal.measurement.zzhs r010 = (com.google.android.gms.internal.measurement.zzhs) r106.next();     // Catch: SQLiteException -> L143
        if (r73.contains(r010.zzd()) == false) goto L126;
        if (r010.zzd().equals("_f") == false) goto L133;
    L134:
        com.google.android.gms.internal.measurement.zzhr r011 = (com.google.android.gms.internal.measurement.zzhr) r010.zzco();     // Catch: SQLiteException -> L143
        zzp();     // Catch: SQLiteException -> L143
        zzpk.zzF(r011, "_dac", 1L);     // Catch: SQLiteException -> L143
        r010 = (com.google.android.gms.internal.measurement.zzhs) r011.zzbd();     // Catch: SQLiteException -> L143
    L135:
        zzaw r114 = zzj();     // Catch: SQLiteException -> L143
        r114.zzg();     // Catch: SQLiteException -> L143
        r114.zzay();     // Catch: SQLiteException -> L143
        Preconditions.checkNotNull(r010);     // Catch: SQLiteException -> L143
        Preconditions.checkNotEmpty(r33);     // Catch: SQLiteException -> L143
        zzic r123 = r114.zzu;     // Catch: SQLiteException -> L143
        r123.zzaW().zzk().zzb("Caching events in NO_DATA mode", r010);     // Catch: SQLiteException -> L143
        ContentValues r133 = new ContentValues();     // Catch: SQLiteException -> L143
        r133.put("app_id", r33);     // Catch: SQLiteException -> L143
        r133.put("name", r010.zzd());     // Catch: SQLiteException -> L143
        r133.put("data", r010.zzcd());     // Catch: SQLiteException -> L143
        r133.put("timestamp_millis", Long.valueOf(r010.zzf()));     // Catch: SQLiteException -> L143
    L137:
        if (r114.zze().insert("no_data_mode_events", null, r133) != r23) goto L126;
        r123.zzaW().zzb().zzb("Failed to insert NO_DATA mode event (got -1). appId", zzgu.zzl(r33));     // Catch: SQLiteException -> L140
    L140:
        e = move-exception;
        r114.zzu.zzaW().zzb().zzc("Error storing NO_DATA mode event. appId", zzgu.zzl(r33), e);     // Catch: SQLiteException -> L143
        goto L126
    L133:
        if (r010.zzd().equals("_v") == false) goto L135;
    L143:
        zzaW().zzh().zzb("Failed handling NO_DATA mode bundles. appId", r33);
        goto L123
    L144:
        r11 = Collections.EMPTY_LIST;
    L226:
        if (r11.isEmpty() == false) goto L229;
        return;
    L229:
        zzjl r012 = zzB(r33);
        zzjk r45 = zzjk.zza;
        if (r012.zzo(r45) == false) goto L249;
        Iterator r013 = r11.iterator();
    L233:
        if (r013.hasNext() == false) goto L237;
        com.google.android.gms.internal.measurement.zzid r54 = (com.google.android.gms.internal.measurement.zzid) ((Pair) r013.next()).first;
        if (r54.zzG().isEmpty() == true) goto L233;
        String r014 = r54.zzG();
    L238:
        if (r014 == null) goto L249;
        int r95 = 0;
    L241:
        if (r95 >= r11.size()) goto L249;
        com.google.android.gms.internal.measurement.zzid r55 = (com.google.android.gms.internal.measurement.zzid) ((Pair) r11.get(r95)).first;
        if (r55.zzG().isEmpty() == true) goto L248;
        if (r55.zzG().equals(r014) == true) goto L248;
        r11 = r11.subList(0, r95);
    L248:
        r95 = r95 + 1;
        goto L241
    L237:
        r014 = null;
    L249:
        com.google.android.gms.internal.measurement.zzhz r015 = com.google.android.gms.internal.measurement.zzib.zzi();
        int r56 = r11.size();
        List r74 = new ArrayList(r11.size());
        if (zzd().zzC(r33) == true) goto L252;
    L254:
        boolean r96 = false;
    L255:
        boolean r46 = zzB(r33).zzo(r45);
        boolean r107 = zzB(r33).zzo(zzjk.zzb);
        zzair.zza();
        boolean r124 = zzd().zzp(r33, zzfy.zzaM);
        zzou r134 = this.zzl;
        zzot r143 = r134.zza(r33);
        int r152 = 0;
    L256:
        if (r152 >= r56) goto L309;
        boolean r215 = r46;
        com.google.android.gms.internal.measurement.zzic r47 = (com.google.android.gms.internal.measurement.zzic) ((com.google.android.gms.internal.measurement.zzid) ((Pair) r11.get(r152)).first).zzco();
        int r222 = r56;
        r74.add((Long) ((Pair) r11.get(r152)).second);
        zzd().zzi();
        boolean r57 = r96;
        boolean r165 = r107;
        r47.zzO(161000);
        r47.zzs(r34);
        this.zzn.zzaV();
        r47.zzae(false);
        if (r57 == true) goto L260;
        r47.zzan();
    L260:
        if (r215 == true) goto L262;
        r47.zzR();
        r47.zzU();
    L262:
        if (r165 == true) goto L264;
        r47.zzX();
    L264:
        zzS(r33, r47);
        if (r124 == true) goto L267;
        r47.zzav();
    L267:
        if (r165 == true) goto L269;
        r47.zzag();
    L269:
        String r108 = r47.zzP();
        if (TextUtils.isEmpty(r108) == false) goto L272;
    L275:
        ArrayList r97 = new ArrayList(r47.zzb());
        Iterator r109 = r97.iterator();
        boolean r25 = r57;
        Long r58 = null;
        Long r1010 = null;
        boolean r235 = false;
        boolean r24 = false;
    L277:
        if (r109.hasNext() == false) goto L292;
        List r28 = r11;
        com.google.android.gms.internal.measurement.zzhs r115 = (com.google.android.gms.internal.measurement.zzhs) r109.next();
        boolean r27 = r124;
        int r29 = r152;
        if ("_fx".equals(r115.zzd()) == true) goto L280;
        if ("_f".equals(r115.zzd()) == true) goto L284;
        r124 = r27;
        r11 = r28;
        r152 = r29;
        goto L277
    L284:
        zzp();
        com.google.android.gms.internal.measurement.zzhw r125 = zzpk.zzI(r115, "_pfo");
        if (r125 == null) goto L287;
        r58 = Long.valueOf(r125.zzf());
    L287:
        zzp();
        com.google.android.gms.internal.measurement.zzhw r116 = zzpk.zzI(r115, "_uwa");
        if (r116 == null) goto L290;
        r1010 = Long.valueOf(r116.zzf());
    L290:
        r124 = r27;
        r11 = r28;
        r152 = r29;
    L281:
        r24 = true;
        goto L277
    L280:
        r109.remove();
        r124 = r27;
        r11 = r28;
        r152 = r29;
        r235 = true;
        goto L281
    L292:
        List r282 = r11;
        boolean r272 = r124;
        int r292 = r152;
        if (r235 == false) goto L295;
        r47.zzi();
        r47.zzh(r97);
    L295:
        if (r24 == false) goto L298;
        zzR(r47.zzK(), true, r58, r1010);
    L298:
        if (r47.zzc() == 0) goto L307;
        if (zzd().zzp(r33, zzfy.zzaC) == false) goto L303;
        r47.zzas(zzp().zzt(((com.google.android.gms.internal.measurement.zzid) r47.zzbd()).zzcd()));
    L303:
        com.google.android.gms.internal.measurement.zzis r59 = r143.zzd();
        if (r59 == null) goto L306;
        r47.zzaN(r59);
    L306:
        r015.zze(r47);
    L307:
        r152 = r292 + 1;
        r107 = r165;
        r46 = r215;
        r56 = r222;
        r96 = r25;
        r124 = r272;
        r11 = r282;
        goto L256
    L272:
        if (r108.equals("00000000-0000-0000-0000-000000000000") == true) goto L275;
        r25 = r57;
        r282 = r11;
        r272 = r124;
        r292 = r152;
        goto L298
    L309:
        if (r015.zzb() != 0) goto L312;
        zzL(r74);
        zzW(false, Opcodes.SUB_DOUBLE_2ADDR, null, null, r33, Collections.EMPTY_LIST, null);
        return;
    L312:
        com.google.android.gms.internal.measurement.zzib r48 = (com.google.android.gms.internal.measurement.zzib) r015.zzbd();
        List r510 = new ArrayList();
        if (r143.zzc() != zzls.zzd) goto L315;
        boolean r82 = true;
    L317:
        if (r143.zzc() == zzls.zzc) goto L322;
        if (r82 == false) goto L320;
        boolean r49 = true;
    L323:
        Iterator r83 = ((com.google.android.gms.internal.measurement.zzib) r015.zzbd()).zza().iterator();
    L325:
        if (r83.hasNext() == false) goto L329;
        if (((com.google.android.gms.internal.measurement.zzid) r83.next()).zzY() == false) goto L325;
        String r84 = UUID.randomUUID().toString();
    L330:
        com.google.android.gms.internal.measurement.zzib r98 = (com.google.android.gms.internal.measurement.zzib) r015.zzbd();
        zzaX().zzg();
        zzu();
        com.google.android.gms.internal.measurement.zzhz r1011 = com.google.android.gms.internal.measurement.zzib.zzj(r98);
        if (TextUtils.isEmpty(r84) == true) goto L333;
        r1011.zzi(r84);
    L333:
        String r117 = zzh().zzc(r33);
        if (TextUtils.isEmpty(r117) == true) goto L336;
        r1011.zzj(r117);
    L336:
        ArrayList r118 = new ArrayList();
        Iterator r99 = r98.zza().iterator();
    L338:
        if (r99.hasNext() == false) goto L340;
        com.google.android.gms.internal.measurement.zzic r126 = com.google.android.gms.internal.measurement.zzid.zzaF((com.google.android.gms.internal.measurement.zzid) r99.next());
        r126.zzan();
        r118.add((com.google.android.gms.internal.measurement.zzid) r126.zzbd());
        goto L338
    L340:
        r1011.zzg();
        r1011.zzf(r118);
        zzgs r910 = zzaW().zzk();
        if (TextUtils.isEmpty(r84) == false) goto L343;
        Object r119 = "null";
    L344:
        r910.zzb("[sgtm] Processed MeasurementBatch for sGTM with sgtmJoinId: ", r119);
        com.google.android.gms.internal.measurement.zzib r911 = (com.google.android.gms.internal.measurement.zzib) r1011.zzbd();
        if (TextUtils.isEmpty(r84) == true) goto L363;
        com.google.android.gms.internal.measurement.zzib r016 = (com.google.android.gms.internal.measurement.zzib) r015.zzbd();
        zzaX().zzg();
        zzu();
        com.google.android.gms.internal.measurement.zzhz r1012 = com.google.android.gms.internal.measurement.zzib.zzi();
        zzaW().zzk().zzb("[sgtm] Processing Google Signal, sgtmJoinId:", r84);
        r1012.zzi(r84);
        Iterator r017 = r016.zza().iterator();
    L348:
        if (r017.hasNext() == false) goto L350;
        com.google.android.gms.internal.measurement.zzid r85 = (com.google.android.gms.internal.measurement.zzid) r017.next();
        com.google.android.gms.internal.measurement.zzic r1110 = com.google.android.gms.internal.measurement.zzid.zzaE();
        r1110.zzam(r85.zzZ());
        r1110.zzaJ(r85.zzav());
        r1012.zze(r1110);
        goto L348
    L350:
        com.google.android.gms.internal.measurement.zzib r018 = (com.google.android.gms.internal.measurement.zzib) r1012.zzbd();
        String r86 = r134.zzg.zzh().zzc(r33);
        if (TextUtils.isEmpty(r86) == true) goto L357;
        Uri r1013 = Uri.parse((String) zzfy.zzr.zzb(null));
        Uri.Builder r1111 = r1013.buildUpon();
        String r1014 = r1013.getAuthority();
        StringBuilder r153 = new StringBuilder((String.valueOf(r86).length() + 1) + String.valueOf(r1014).length());
        r153.append(r86);
        r153.append(".");
        r153.append(r1014);
        r1111.authority(r153.toString());
        String r1015 = r1111.build().toString();
        if (r49 == false) goto L355;
        zzls r1112 = zzls.zze;
    L356:
        Object r135 = null;
        zzot r87 = new zzot(r1015, Collections.EMPTY_MAP, r1112, null);
    L362:
        r510.add(Pair.create(r018, r87));
    L364:
        if (r49 == false) goto L373;
        com.google.android.gms.internal.measurement.zzhz r019 = (com.google.android.gms.internal.measurement.zzhz) r911.zzco();
        int r410 = 0;
    L367:
        if (r410 >= r911.zzb()) goto L369;
        com.google.android.gms.internal.measurement.zzic r88 = (com.google.android.gms.internal.measurement.zzic) r911.zzc(r410).zzco();
        r88.zzt();
        r88.zzaO(r34);
        r019.zzd(r410, r88);
        r410 = r410 + 1;
        goto L367
    L369:
        r510.add(Pair.create((com.google.android.gms.internal.measurement.zzib) r019.zzbd(), r143));
        zzL(r74);
        zzW(false, Opcodes.SUB_DOUBLE_2ADDR, null, null, r33, r510, null);
        if (zzO(r33, r143.zza()) == false) goto L474;
        zzaW().zzk().zzb("[sgtm] Sending sgtm batches available notification to app", r33);
        Intent r020 = new Intent();
        r020.setAction("com.google.android.gms.measurement.BATCHES_AVAILABLE");
        r020.setPackage(r33);
        zzaR(this.zzn.zzaZ(), r020);
        return;
    L474:
        return;
    L373:
        r48 = r911;
    L375:
        if (zzi().zzb() == true) goto L377;
        return;
    L377:
        if (Log.isLoggable(zzaW().zzn(), 2) == false) goto L379;
        r135 = zzp().zzi(r48);
    L379:
        zzp();
        byte[] r511 = r48.zzcd();
        zzL(r74);
        this.zzk.zze.zzb(r34);
        zzaW().zzk().zzd("Uploading data. app, uncompressed size, data", r33, Integer.valueOf(r511.length), r135);
        this.zzv = true;
        zzi().zzc(r33, r143, r48, new zzow(this, r33, r510));
        return;
    L355:
        r1112 = zzls.zzb;
        goto L356
    L357:
        r135 = null;
        String r1016 = (String) zzfy.zzr.zzb(null);
        if (r49 == false) goto L360;
        zzls r1113 = zzls.zze;
    L361:
        r87 = new zzot(r1016, Collections.EMPTY_MAP, r1113, null);
        goto L362
    L360:
        r1113 = zzls.zzb;
        goto L361
    L363:
        r135 = null;
        goto L364
    L343:
        r119 = r1011.zzh();
        goto L344
    L329:
        r84 = null;
        goto L330
    L320:
        r135 = null;
        goto L375
    L322:
        r49 = r82;
        goto L323
    L315:
        r82 = false;
        goto L317
    L252:
        if (zzB(r33).zzo(r45) == false) goto L254;
        r96 = true;
        goto L255
    L476:
        return;
    L17:
        r103.close();
    L381:
        th = th;
        Cursor r136 = r103;
    L383:
        if (r136 == null) goto L385;
        r136.close();
    L385:
        throw th;
    L19:
        th = th;
        r22 = r103;
    L97:
        r136 = r22;
        goto L383
    L15:
        if (r103.moveToFirst() == true) goto L23;
        r02 = Collections.EMPTY_LIST;     // Catch: Throwable -> L19 SQLiteException -> L21
        goto L17
    L23:
        r11 = new ArrayList();     // Catch: Throwable -> L19 SQLiteException -> L21
        int r144 = 0;
    L24:
        long r154 = r103.getLong(r7);     // Catch: Throwable -> L19 SQLiteException -> L21
        byte[] r021 = r103.getBlob(r9);     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L88
        zzpk r912 = r8.zzg.zzp();     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L88
        ByteArrayInputStream r137 = new ByteArrayInputStream(r021);     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
        GZIPInputStream r022 = new GZIPInputStream(r137);     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
        ByteArrayOutputStream r127 = new ByteArrayOutputStream();     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
        byte[] r75 = new byte[1024];     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
    L27:
        int r138 = r022.read(r75);     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
        if (r138 <= 0) goto L29;
        r22 = r103;
        r127.write(r75, 0, r138);     // Catch: Throwable -> L79 SQLiteException -> L81 IOException -> L83
        r103 = r22;
    L83:
        e = e;
    L392:
        r912.zzu.zzaW().zzb().zzb("Failed to ungzip content", e);     // Catch: Throwable -> L79 SQLiteException -> L81 IOException -> L86
        throw e;     // Catch: Throwable -> L79 SQLiteException -> L81 IOException -> L86
    L86:
        e = e;
    L90:
        r8.zzu.zzaW().zzb().zzc("Failed to unzip queued bundle. appId", zzgu.zzl(r33), e);     // Catch: Throwable -> L79 SQLiteException -> L81
    L91:
        if (r22.moveToNext() == false) goto L96;
        if (r144 > r5) goto L96;
        r103 = r22;
        r7 = 0;
        r9 = 1;
    L96:
        r22.close();
        goto L111
    L81:
        e = e;
    L79:
        th = th;
        goto L97
    L29:
        r022.close();     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
        r137.close();     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
        byte[] r023 = r127.toByteArray();     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L74
        if (r11.isEmpty() == false) goto L34;
    L409:
        com.google.android.gms.internal.measurement.zzic r76 = (com.google.android.gms.internal.measurement.zzic) zzpk.zzw(com.google.android.gms.internal.measurement.zzid.zzaE(), r023);     // Catch: Throwable -> L19 SQLiteException -> L21 IOException -> L71
        if (r11.isEmpty() == true) goto L67;
        com.google.android.gms.internal.measurement.zzid r913 = (com.google.android.gms.internal.measurement.zzid) ((Pair) r11.get(0)).first;     // Catch: Throwable -> L19 SQLiteException -> L21
        com.google.android.gms.internal.measurement.zzid r128 = (com.google.android.gms.internal.measurement.zzid) r76.zzbd();     // Catch: Throwable -> L19 SQLiteException -> L21
        if (r913.zzaf().equals(r128.zzaf()) == false) goto L35;
        if (r913.zzam().equals(r128.zzam()) == false) goto L35;
        if (r913.zzao() != r128.zzao()) goto L35;
        if (r913.zzaq().equals(r128.zzaq()) == false) goto L35;
        Iterator r914 = r913.zzf().iterator();     // Catch: Throwable -> L19 SQLiteException -> L21
    L49:
        Iterator r216 = r914;
        if (r914.hasNext() == false) goto L55;
        com.google.android.gms.internal.measurement.zziu r139 = (com.google.android.gms.internal.measurement.zziu) r216.next();     // Catch: Throwable -> L19 SQLiteException -> L21
        com.google.android.gms.internal.measurement.zzid r223 = r128;
        if ("_npa".equals(r139.zzc()) == true) goto L53;
        r914 = r216;
        r128 = r223;
        goto L49
    L53:
        long r129 = r139.zzh();     // Catch: Throwable -> L19 SQLiteException -> L21
    L56:
        Iterator r217 = r223.zzf().iterator();     // Catch: Throwable -> L19 SQLiteException -> L21
    L58:
        if (r217.hasNext() == false) goto L63;
        com.google.android.gms.internal.measurement.zziu r224 = (com.google.android.gms.internal.measurement.zziu) r217.next();     // Catch: Throwable -> L19 SQLiteException -> L21
        long r252 = r129;
        if ("_npa".equals(r224.zzc()) == true) goto L61;
        r129 = r252;
        goto L58
    L61:
        long r1210 = r224.zzh();     // Catch: Throwable -> L19 SQLiteException -> L21
    L65:
        if (r252 != r1210) goto L35;
    L63:
        r252 = r129;
        r1210 = -1;
        goto L65
    L55:
        r223 = r128;
        r129 = -1;
    L35:
        r22 = r103;
    L67:
        if (r103.isNull(2) == true) goto L69;
        r76.zzao(r103.getInt(2));     // Catch: Throwable -> L19 SQLiteException -> L21
    L69:
        r144 = r144 + r023.length;     // Catch: Throwable -> L19 SQLiteException -> L21
        r11.add(Pair.create((com.google.android.gms.internal.measurement.zzid) r76.zzbd(), Long.valueOf(r154)));     // Catch: Throwable -> L19 SQLiteException -> L21
    L70:
        r22 = r103;
    L71:
        e = move-exception;
        r8.zzu.zzaW().zzb().zzc("Failed to merge queued bundle. appId", zzgu.zzl(r33), e);     // Catch: Throwable -> L19 SQLiteException -> L21
        goto L70
    L34:
        if ((r023.length + r144) <= r5) goto L409;
    L74:
        e = e;
        r22 = r103;
    L88:
        e = e;
        r22 = r103;
    L101:
        e = e;
    L106:
        r103 = null;
    L99:
        th = th;
        r136 = null;
    L103:
        e = e;
        r23 = -1;
        goto L106
    L9:
        r102 = false;
        goto L10
    L5:
        r10 = false;
        goto L6
    }

    public final boolean zzO(String r4, String r5) {
        zzh r0 = zzj().zzu(r4);
        if (r0 != null) goto L5;
    L8:
        zzpe r3 = (zzpe) this.zzF.get(r5);
        if (r3 != null) goto L12;
        return true;
    L12:
        return r3.zzb();
    L5:
        if (zzt().zzad(r4, r0.zzay()) == false) goto L8;
        this.zzF.remove(r5);
        return true;
    }

    public final void zzP(String r9) {
        zzaX().zzg();
        zzu();
        this.zzw = true;
        zzic r2 = this.zzn;     // Catch: Throwable -> L6
        r2.zzaV();     // Catch: Throwable -> L6
        Boolean r22 = r2.zzt().zzJ();     // Catch: Throwable -> L6
        if (r22 != null) goto L9;
        zzaW().zze().zza("Upload data called on the client side before use of service was decided");     // Catch: Throwable -> L6
    L31:
        this.zzw = false;
        zzaN();
        return;
    L9:
        if (r22.booleanValue() == false) goto L12;
        zzaW().zzb().zza("Upload called in the client side when service should be used");     // Catch: Throwable -> L6
        goto L31
    L12:
        if (this.zza <= 0) goto L15;
        zzaM();     // Catch: Throwable -> L6
        goto L31
    L15:
        if (zzi().zzb() == true) goto L18;
        zzaW().zzk().zza("Network not connected, ignoring upload request");     // Catch: Throwable -> L6
        zzaM();     // Catch: Throwable -> L6
        goto L31
    L18:
        if (zzj().zzD(r9) == true) goto L20;
        zzaW().zzk().zzb("[sgtm] Upload queue has no batches for appId", r9);     // Catch: Throwable -> L6
        goto L31
    L20:
        zzaw r23 = zzj();     // Catch: Throwable -> L6
        Preconditions.checkNotEmpty(r9);     // Catch: Throwable -> L6
        r23.zzg();     // Catch: Throwable -> L6
        r23.zzay();     // Catch: Throwable -> L6
        List r24 = r23.zzC(r9, zzoo.zza(new zzls[]{zzls.zzb}), 1);     // Catch: Throwable -> L6
        if (r24.isEmpty() == false) goto L23;
        zzpj r25 = null;
    L24:
        if (r25 == null) goto L31;
        com.google.android.gms.internal.measurement.zzib r3 = r25.zzd();     // Catch: Throwable -> L6
        if (r3 == null) goto L31;
        zzaW().zzk().zzd("[sgtm] Uploading data from upload queue. appId, type, url", r9, r25.zzf(), r25.zze());     // Catch: Throwable -> L6
        byte[] r4 = r3.zzcd();     // Catch: Throwable -> L6
        if (Log.isLoggable(zzaW().zzn(), 2) == false) goto L30;
        String r5 = zzp().zzi(r3);     // Catch: Throwable -> L6
        zzaW().zzk().zzd("[sgtm] Uploading data from upload queue. appId, uncompressed size, data", r9, Integer.valueOf(r4.length), r5);     // Catch: Throwable -> L6
    L30:
        zzot r42 = r25.zza();     // Catch: Throwable -> L6
        this.zzv = true;     // Catch: Throwable -> L6
        zzi().zzc(r9, r42, r3, new zzox(this, r9, r25));     // Catch: Throwable -> L6
        goto L31
    L23:
        r25 = (zzpj) r24.get(0);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        this.zzw = false;
        zzaN();
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.lang.String] */
    public final void zzQ(String r4, int r5, Throwable r6, byte[] r7, zzpj r8) {
        zzaX().zzg();
        zzu();
        if (r7 != null) goto L8;
        r7 = new byte[0];     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        this.zzv = false;
        zzaN();
        throw th;
    L8:
        if (r5 != 200) goto L10;
    L12:
        if (r6 != null) goto L19;
        zzj().zzE(Long.valueOf(r8.zzc()));     // Catch: Throwable -> L5
        zzaW().zzk().zzc("Successfully uploaded batch from upload queue. appId, status", r4, Integer.valueOf(r5));     // Catch: Throwable -> L5
        if (zzi().zzb() == true) goto L16;
    L18:
        zzaM();     // Catch: Throwable -> L5
    L23:
        this.zzv = false;
        zzaN();
        return;
    L16:
        if (zzj().zzD(r4) == false) goto L18;
        zzP(r4);     // Catch: Throwable -> L5
    L19:
        String r1 = new String(r7, StandardCharsets.UTF_8);     // Catch: Throwable -> L5
        ?? r72 = r1.substring(0, Math.min(32, r1.length()));     // Catch: Throwable -> L5
        zzgs r12 = zzaW().zzh();     // Catch: Throwable -> L5
        Integer r52 = Integer.valueOf(r5);     // Catch: Throwable -> L5
        if (r6 != null) goto L22;
        r6 = r72;
    L22:
        r12.zzd("Network upload failed. Will retry later. appId, status, error", r4, r52, r6);     // Catch: Throwable -> L5
        zzj().zzK(Long.valueOf(r8.zzc()));     // Catch: Throwable -> L5
        zzaM();     // Catch: Throwable -> L5
        goto L23
    L10:
        if (r5 != 204) goto L19;
        r5 = 204;
        goto L12
    }

    public final void zzR(String r2, boolean r3, Long r4, Long r5) {
        zzh r22 = zzj().zzu(r2);
        if (r22 == null) goto L8;
        r22.zzar(r3);
        r22.zzat(r4);
        r22.zzav(r5);
        if (r22.zza() == false) goto L9;
        zzj().zzv(r22, false, false);
        return;
    L9:
        return;
    }

    public final void zzS(String r7, com.google.android.gms.internal.measurement.zzic r8) {
        Set r0 = zzh().zzl(r7);
        if (r0 == null) goto L6;
        r8.zzaw(r0);
    L6:
        if (zzh().zzq(r7) == false) goto L9;
        r8.zzG();
    L9:
        if (zzh().zzr(r7) == false) goto L16;
        String r02 = r8.zzD();
        if (TextUtils.isEmpty(r02) == true) goto L16;
        int r2 = r02.indexOf(".");
        if (r2 == (-1)) goto L16;
        r8.zzE(r02.substring(0, r2));
    L16:
        if (zzh().zzs(r7) == false) goto L21;
        int r03 = zzpk.zzx(r8, "_id");
        if (r03 == (-1)) goto L21;
        r8.zzr(r03);
    L21:
        if (zzh().zzt(r7) == false) goto L24;
        r8.zzan();
    L24:
        if (zzh().zzu(r7) == false) goto L34;
        r8.zzX();
        if (zzB(r7).zzo(zzjk.zzb) == false) goto L34;
        Map r04 = this.zzE;
        zzpd r1 = (zzpd) r04.get(r7);
        if (r1 != null) goto L30;
    L31:
        r1 = new zzpd(this, null);
        r04.put(r7, r1);
    L32:
        r8.zzax(r1.zza);
        goto L34
    L30:
        if ((r1.zzb + zzd().zzl(r7, zzfy.zzaj)) >= zzba().elapsedRealtime()) goto L32;
    L34:
        if (zzh().zzv(r7) == false) goto L37;
        r8.zzav();
        return;
    }

    public final void zzT(com.google.android.gms.internal.measurement.zzic r24, zzpc r25) {
        int r3 = 0;
    L4:
        if (r3 >= r24.zzc()) goto L35;
        com.google.android.gms.internal.measurement.zzhr r4 = (com.google.android.gms.internal.measurement.zzhr) r24.zzd(r3).zzco();
        Iterator r5 = r4.zza().iterator();
    L7:
        if (r5.hasNext() == false) goto L34;
        if ("_c".equals(((com.google.android.gms.internal.measurement.zzhw) r5.next()).zzb()) == false) goto L7;
        if (r25.zza.zzar() < zzd().zzm(r25.zza.zzA(), zzfy.zzak)) goto L33;
        int r52 = zzd().zzm(r25.zza.zzA(), zzfy.zzax);
        String r8 = null;
        if (r52 <= 0) goto L26;
        if (zzj().zzw(zzC(), r25.zza.zzA(), false, false, false, false, false, false, true).zzg <= r52) goto L18;
        zzhv r53 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r53.zzb("_tnr");
        r53.zzf(1);
        r4.zzf((com.google.android.gms.internal.measurement.zzhw) r53.zzbd());
        goto L33
    L18:
        if (zzd().zzp(r25.zza.zzA(), zzfy.zzaQ) == false) goto L20;
        r8 = zzt().zzaz();
        zzhv r54 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r54.zzb("_tu");
        r54.zzd(r8);
        r4.zzf((com.google.android.gms.internal.measurement.zzhw) r54.zzbd());
    L20:
        zzhv r55 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r55.zzb("_tr");
        r55.zzf(1);
        r4.zzf((com.google.android.gms.internal.measurement.zzhw) r55.zzbd());
        zzoh r56 = zzp().zzf(r25.zza.zzA(), r24, r4, r8);
        if (r56 == null) goto L33;
        zzaW().zzk().zzc("Generated trigger URI. appId, uri", r25.zza.zzA(), r56.zza);
        zzj().zzZ(r25.zza.zzA(), r56);
        Deque r57 = this.zzr;
        if (r57.contains(r25.zza.zzA()) == true) goto L33;
        r57.add(r25.zza.zzA());
        goto L33
    L26:
        if (zzd().zzp(r25.zza.zzA(), zzfy.zzaQ) == false) goto L28;
        r8 = zzt().zzaz();
        zzhv r58 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r58.zzb("_tu");
        r58.zzd(r8);
        r4.zzf((com.google.android.gms.internal.measurement.zzhw) r58.zzbd());
    L28:
        zzhv r59 = com.google.android.gms.internal.measurement.zzhw.zzn();
        r59.zzb("_tr");
        r59.zzf(1);
        r4.zzf((com.google.android.gms.internal.measurement.zzhw) r59.zzbd());
        zzoh r510 = zzp().zzf(r25.zza.zzA(), r24, r4, r8);
        if (r510 == null) goto L33;
        zzaW().zzk().zzc("Generated trigger URI. appId, uri", r25.zza.zzA(), r510.zza);
        zzj().zzZ(r25.zza.zzA(), r510);
        Deque r511 = this.zzr;
        if (r511.contains(r25.zza.zzA()) == true) goto L33;
        r511.add(r25.zza.zzA());
    L33:
        r24.zze(r3, (com.google.android.gms.internal.measurement.zzhs) r4.zzbd());
    L34:
        r3 = r3 + 1;
        goto L4
    }

    public final void zzU(String r10, zzhv r11, Bundle r12, String r13) {
        if (zzd().zzp(r13, zzfy.zzba) == false) goto L5;
        List r0 = CollectionUtils.listOf(new String[]{"_o", "_sn", "_sc", "_si", "deep_link_url"});
    L7:
        if (zzpp.zzac(r11.zza()) == false) goto L9;
    L13:
        int r102 = zzd().zzf(r13, true);
    L12:
        long r3 = r102;
        long r5 = r11.zzc().codePointCount(0, r11.zzc().length());
        zzpp r103 = zzt();
        String r1 = r11.zza();
        zzd();
        String r104 = r103.zzE(r1, 40, true);
        if (r5 > r3) goto L17;
        return;
    L17:
        if (r0.contains(r11.zza()) == false) goto L19;
        return;
    L19:
        if ("_ev".equals(r11.zza()) == false) goto L22;
        r12.putString("_ev", zzt().zzE(r11.zzc(), zzd().zzf(r13, true), true));
        return;
    L22:
        zzaW().zzh().zzc("Param value is too long; discarded. Name, value length", r104, Long.valueOf(r5));
        if (r12.getLong("_err") != 0) goto L27;
        r12.putLong("_err", 4);
        if (r12.getString("_ev") != null) goto L27;
        r12.putString("_ev", r104);
        r12.putLong("_el", r5);
    L27:
        r12.remove(r11.zza());
        return;
    L9:
        if (zzpp.zzac(r10) == true) goto L13;
        r102 = zzd().zze(r13, true);
        goto L12
    L5:
        r0 = CollectionUtils.listOf(new String[]{"_o", "_sn", "_sc", "_si"});
        goto L7
    }

    public final boolean zzV(com.google.android.gms.internal.measurement.zzhr r11) {
        ArrayList r0 = new ArrayList(r11.zza());
        int r3 = 0;
        int r4 = -1;
        int r5 = -1;
    L4:
        if (r3 >= r0.size()) goto L13;
        if ("value".equals(((com.google.android.gms.internal.measurement.zzhw) r0.get(r3)).zzb()) == false) goto L9;
        r4 = r3;
    L11:
        r3 = r3 + 1;
        goto L4
    L9:
        if ("currency".equals(((com.google.android.gms.internal.measurement.zzhw) r0.get(r3)).zzb()) == false) goto L11;
        r5 = r3;
        goto L11
    L13:
        if (r4 != (-1)) goto L21;
        if (zzd().zzp(null, zzfy.zzbf) == true) goto L17;
        return true;
    L17:
        if ("_iap".equals(r11.zzk()) == false) goto L46;
        zzaC(r11, "_c");
        zzaB(r11, 18, "value");
        return false;
    L46:
        return true;
    L21:
        if (((com.google.android.gms.internal.measurement.zzhw) r0.get(r4)).zze() == false) goto L23;
    L26:
        if (r5 == (-1)) goto L38;
        String r02 = ((com.google.android.gms.internal.measurement.zzhw) r0.get(r5)).zzd();
        if (r02.length() != 3) goto L38;
        int r2 = 0;
    L32:
        if (r2 >= r02.length()) goto L47;
        int r32 = r02.codePointAt(r2);
        if (Character.isLetter(r32) == false) goto L38;
        r2 = r2 + Character.charCount(r32);
        goto L32
    L47:
        return true;
    L38:
        zzaW().zzh().zza("Value parameter discarded. You must also supply a 3-letter ISO_4217 currency code in the currency parameter.");
        r11.zzj(r4);
        zzaC(r11, "_c");
        zzaB(r11, 19, "currency");
        return false;
    L23:
        if (((com.google.android.gms.internal.measurement.zzhw) r0.get(r4)).zzj() == true) goto L26;
        zzaW().zzh().zza("Value must be specified with a numeric type.");
        r11.zzj(r4);
        zzaC(r11, "_c");
        zzaB(r11, 18, "value");
        return false;
    }

    public final void zzW(boolean r19, int r20, Throwable r21, byte[] r22, String r23, List r24, Map r25) {
        int r0 = r20;
        zzaX().zzg();
        zzu();
        if (r22 == null) goto L97;
        byte[] r3 = r22;
    L9:
        if (zzd().zzp(null, zzfy.zzbe) == false) goto L11;
        zzp().zzD(r25);     // Catch: Throwable -> L5
    L11:
        List r11 = (List) Preconditions.checkNotNull(this.zzz);     // Catch: Throwable -> L5
        this.zzz = null;     // Catch: Throwable -> L5
        if (r19 == true) goto L14;
    L25:
        zzgs r2 = zzaW().zzk();     // Catch: Throwable -> L5
        Integer r02 = Integer.valueOf(r0);     // Catch: Throwable -> L5
        r2.zzc("Network upload successful with code, uploadAttempted", r02, Boolean.valueOf(r19));     // Catch: Throwable -> L5
        if (r19 == true) goto L96;
    L30:
        this.zzk.zze.zzb(0);     // Catch: Throwable -> L5 SQLiteException -> L28
        zzaM();     // Catch: Throwable -> L5 SQLiteException -> L28
        if (r19 == false) goto L33;
        zzaW().zzk().zzc("Successful upload. Got network response. code, size", r02, Integer.valueOf(r3.length));     // Catch: Throwable -> L5 SQLiteException -> L28
    L34:
        zzj().zzb();     // Catch: Throwable -> L5 SQLiteException -> L28
        HashMap r03 = new HashMap();     // Catch: Throwable -> L48
        Iterator r14 = r24.iterator();     // Catch: Throwable -> L48
    L37:
        if (r14.hasNext() == false) goto L50;
        Pair r26 = (Pair) r14.next();     // Catch: Throwable -> L48
        com.google.android.gms.internal.measurement.zzib r5 = (com.google.android.gms.internal.measurement.zzib) r26.first;     // Catch: Throwable -> L48
        zzot r15 = (zzot) r26.second;     // Catch: Throwable -> L48
        if (r15.zzc() == zzls.zzd) goto L37;
        long r52 = zzj().zzA(r23, r5, r15.zza(), r15.zzb(), r15.zzc(), null);     // Catch: Throwable -> L48
        if (r15.zzc() != zzls.zze) goto L37;
        if (r52 == (-1)) goto L37;
        if (r5.zze().isEmpty() == true) goto L37;
        r03.put(r5.zze(), Long.valueOf(r52));     // Catch: Throwable -> L48
        goto L37
    L50:
        Iterator r142 = r24.iterator();     // Catch: Throwable -> L48
    L52:
        if (r142.hasNext() == false) goto L57;
        Pair r27 = (Pair) r142.next();     // Catch: Throwable -> L48
        com.google.android.gms.internal.measurement.zzib r4 = (com.google.android.gms.internal.measurement.zzib) r27.first;     // Catch: Throwable -> L48
        zzot r28 = (zzot) r27.second;     // Catch: Throwable -> L48
        if (r28.zzc() != zzls.zzd) goto L52;
        zzj().zzA(r23, r4, r28.zza(), r28.zzb(), r28.zzc(), (Long) r03.get(r4.zze()));     // Catch: Throwable -> L48
        goto L52
    L57:
        List r04 = zzj().zzC(r23, zzoo.zza(new zzls[]{zzls.zzd}), 1);     // Catch: Throwable -> L48
        if (r04.isEmpty() == true) goto L62;
        long r42 = ((zzpj) r04.get(0)).zzg();     // Catch: Throwable -> L48
        if (zzba().currentTimeMillis() <= (((Long) zzfy.zzE.zzb(null)).longValue() + r42)) goto L62;
        zzaW().zze().zzc("[sgtm] client batches are queued too long. appId, creationTime", r23, Long.valueOf(r42));     // Catch: Throwable -> L48
    L62:
        Iterator r29 = r11.iterator();     // Catch: Throwable -> L48
    L64:
        if (r29.hasNext() == false) goto L75;
        Long r43 = (Long) r29.next();     // Catch: Throwable -> L48
        zzj().zzH(r43.longValue());     // Catch: Throwable -> L48 SQLiteException -> L68
    L68:
        e = move-exception;
        List r53 = this.zzA;     // Catch: Throwable -> L48
        if (r53 == null) goto L74;
        if (r53.contains(r43) == true) goto L64;
    L74:
        throw e;     // Catch: Throwable -> L48
    L75:
        zzj().zzc();     // Catch: Throwable -> L48
        zzj().zzd();     // Catch: Throwable -> L5 SQLiteException -> L28
        this.zzA = null;     // Catch: Throwable -> L5 SQLiteException -> L28
        if (zzi().zzb() == false) goto L83;
        if (zzj().zzD(r23) == false) goto L83;
        zzP(r23);     // Catch: Throwable -> L5 SQLiteException -> L28
    L88:
        this.zza = 0;     // Catch: Throwable -> L5 SQLiteException -> L28
    L92:
        this.zzv = false;
        zzaN();
        return;
    L83:
        if (zzi().zzb() == true) goto L85;
    L87:
        this.zzB = -1;     // Catch: Throwable -> L5 SQLiteException -> L28
        zzaM();     // Catch: Throwable -> L5 SQLiteException -> L28
        goto L88
    L85:
        if (zzaL() == false) goto L87;
        zzM();     // Catch: Throwable -> L5 SQLiteException -> L28
    L48:
        th = move-exception;
        zzj().zzd();     // Catch: Throwable -> L5 SQLiteException -> L28
        throw th;     // Catch: Throwable -> L5 SQLiteException -> L28
    L33:
        zzaW().zzk().zza("Purged empty bundles");     // Catch: Throwable -> L5 SQLiteException -> L28
        goto L34
    L96:
        this.zzk.zzd.zzb(zzba().currentTimeMillis());     // Catch: Throwable -> L5 SQLiteException -> L28
    L28:
        e = move-exception;
        zzaW().zzb().zzb("Database error while trying to delete uploaded bundles", e);     // Catch: Throwable -> L5
        this.zza = zzba().elapsedRealtime();     // Catch: Throwable -> L5
        zzaW().zzk().zzb("Disable upload, time", Long.valueOf(this.zza));     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        this.zzv = false;
        zzaN();
        throw th;
    L14:
        if (r0 != 200) goto L16;
    L18:
        if (r21 == null) goto L25;
    L19:
        String r44 = new String(r3, StandardCharsets.UTF_8);     // Catch: Throwable -> L5
        zzaW().zzh().zzd("Network upload failed. Will retry later. code, error", Integer.valueOf(r0), r21, r44.substring(0, Math.min(32, r44.length())));     // Catch: Throwable -> L5
        this.zzk.zze.zzb(zzba().currentTimeMillis());     // Catch: Throwable -> L5
        if (r0 != 503) goto L22;
    L23:
        this.zzk.zzc.zzb(zzba().currentTimeMillis());     // Catch: Throwable -> L5
    L24:
        zzj().zzJ(r11);     // Catch: Throwable -> L5
        zzaM();     // Catch: Throwable -> L5
        goto L92
    L22:
        if (r0 != 429) goto L24;
    L16:
        if (r0 != 204) goto L19;
        r0 = 204;
        goto L18
    L97:
        r3 = new byte[0];     // Catch: Throwable -> L5
        goto L9
    }

    public final void zzX(zzh r7) {
        zzaX().zzg();
        if (TextUtils.isEmpty(r7.zzf()) == false) goto L6;
        zzY((String) Preconditions.checkNotNull(r7.zzc()), Opcodes.SUB_DOUBLE_2ADDR, null, null, null);
        return;
    L6:
        String r6 = (String) Preconditions.checkNotNull(r7.zzc());
        zzaW().zzk().zzb("Fetching remote configuration", r6);
        com.google.android.gms.internal.measurement.zzgl r1 = zzh().zzb(r6);
        String r2 = zzh().zzd(r6);
        ArrayMap r3 = null;
        if (r1 != null) goto L9;
    L16:
        this.zzu = true;
        zzi().zzd(r7, r3, new zzpf(this));
        return;
    L9:
        if (TextUtils.isEmpty(r2) == true) goto L11;
        r3 = new ArrayMap();
        r3.put("If-Modified-Since", r2);
    L11:
        String r62 = zzh().zze(r6);
        if (TextUtils.isEmpty(r62) == true) goto L16;
        if (r3 != null) goto L15;
        r3 = new ArrayMap();
    L15:
        r3.put("If-None-Match", r62);
        goto L16
    }

    public final void zzY(String r8, int r9, Throwable r10, byte[] r11, Map r12) {
        zzaX().zzg();
        zzu();
        Preconditions.checkNotEmpty(r8);
        if (r11 == null) goto L67;
    L7:
        zzgs r1 = zzaW().zzk();     // Catch: Throwable -> L5
        Integer r3 = Integer.valueOf(r11.length);     // Catch: Throwable -> L5
        r1.zzb("onConfigFetched. Response size", r3);     // Catch: Throwable -> L5
        if (zzd().zzp(null, zzfy.zzbe) == false) goto L10;
        zzp().zzD(r12);     // Catch: Throwable -> L5
    L10:
        zzj().zzb();     // Catch: Throwable -> L5
        zzh r13 = zzj().zzu(r8);     // Catch: Throwable -> L22
        if (r9 != 200) goto L14;
    L18:
        if (r10 == null) goto L19;
    L17:
        boolean r2 = false;
    L20:
        if (r13 != null) goto L25;
        zzaW().zze().zzb("App does not exist in onConfigFetched. appId", zzgu.zzl(r8));     // Catch: Throwable -> L22
    L57:
        zzj().zzc();     // Catch: Throwable -> L22
        zzj().zzd();     // Catch: Throwable -> L5
        this.zzu = false;
        zzaN();
        return;
    L25:
        if (r2 == true) goto L34;
        if (r9 == 404) goto L34;
        r13.zzK(zzba().currentTimeMillis());     // Catch: Throwable -> L22
        zzj().zzv(r13, false, false);     // Catch: Throwable -> L22
        zzaW().zzk().zzc("Fetching config failed. code, error", Integer.valueOf(r9), r10);     // Catch: Throwable -> L22
        zzh().zzf(r8);     // Catch: Throwable -> L22
        this.zzk.zze.zzb(zzba().currentTimeMillis());     // Catch: Throwable -> L22
        if (r9 != 503) goto L31;
    L32:
        this.zzk.zzc.zzb(zzba().currentTimeMillis());     // Catch: Throwable -> L22
    L33:
        zzaM();     // Catch: Throwable -> L22
        goto L57
    L31:
        if (r9 != 429) goto L33;
    L34:
        zzp();     // Catch: Throwable -> L22
        String r102 = zzpk.zzJ(r12, "Last-Modified");     // Catch: Throwable -> L22
        zzp();     // Catch: Throwable -> L22
        String r122 = zzpk.zzJ(r12, "ETag");     // Catch: Throwable -> L22
        if (r9 == 404) goto L40;
        if (r9 == 304) goto L40;
        zzh().zzi(r8, r11, r102, r122);     // Catch: Throwable -> L22
    L42:
        r13.zzI(zzba().currentTimeMillis());     // Catch: Throwable -> L22
        zzj().zzv(r13, false, false);     // Catch: Throwable -> L22
        if (r9 != 404) goto L45;
        zzaW().zzh().zzb("Config not found. Using empty config. appId", r8);     // Catch: Throwable -> L22
    L47:
        if (zzi().zzb() == false) goto L52;
        if (zzaL() == false) goto L52;
        zzM();     // Catch: Throwable -> L22
    L52:
        if (zzi().zzb() == true) goto L54;
    L56:
        zzaM();     // Catch: Throwable -> L22
        goto L57
    L54:
        if (zzj().zzD(r13.zzc()) == false) goto L56;
        zzP(r13.zzc());     // Catch: Throwable -> L22
        goto L57
    L45:
        zzaW().zzk().zzc("Successfully fetched config. Got network response. code, size", Integer.valueOf(r9), r3);     // Catch: Throwable -> L22
    L40:
        if (zzh().zzb(r8) != null) goto L42;
        zzh().zzi(r8, null, null, null);     // Catch: Throwable -> L22
        goto L42
    L19:
        r2 = true;
        goto L20
    L14:
        if (r9 == 204) goto L18;
        if (r9 != 304) goto L17;
        r9 = 304;
    L22:
        th = move-exception;
        zzj().zzd();     // Catch: Throwable -> L5
        throw th;     // Catch: Throwable -> L5
    L5:
        th = move-exception;
        this.zzu = false;
        zzaN();
        throw th;
    L67:
        r11 = new byte[0];     // Catch: Throwable -> L5
        goto L7
    }

    public final void zzZ(Runnable r2) {
        zzaX().zzg();
        if (this.zzq != null) goto L5;
        this.zzq = new ArrayList();
    L5:
        this.zzq.add(r2);
    }

    public final /* synthetic */ void zzaA(long r1) {
        this.zzJ = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    public final zzae zzaV() {
        return this.zzn.zzaV();
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    public final zzgu zzaW() {
        return ((zzic) Preconditions.checkNotNull(this.zzn)).zzaW();
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    public final zzhz zzaX() {
        return ((zzic) Preconditions.checkNotNull(this.zzn)).zzaX();
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    public final Context zzaZ() {
        return this.zzn.zzaZ();
    }

    public final void zzaa() {
        zzaX().zzg();
        zzu();
        if (this.zzp == true) goto L51;
        this.zzp = true;
        if (zzab() == false) goto L52;
        FileChannel r1 = this.zzy;
        zzaX().zzg();
        int r6 = 0;
        if (r1 != null) goto L9;
    L22:
        zzaW().zzb().zza("Bad channel to read from");
    L23:
        int r12 = this.zzn.zzv().zzm();
        zzaX().zzg();
        if (r6 <= r12) goto L27;
        zzaW().zzb().zzc("Panic: can't downgrade version. Previous, current version", Integer.valueOf(r6), Integer.valueOf(r12));
        return;
    L27:
        if (r6 >= r12) goto L53;
        FileChannel r7 = this.zzy;
        zzaX().zzg();
        if (r7 != null) goto L31;
    L43:
        zzaW().zzb().zza("Bad channel to read from");
    L44:
        zzaW().zzb().zzc("Storage version upgrade failed. Previous, current version", Integer.valueOf(r6), Integer.valueOf(r12));
        return;
    L31:
        if (r7.isOpen() == false) goto L43;
        ByteBuffer r2 = ByteBuffer.allocate(4);
        r2.putInt(r12);
        r2.flip();
        r7.truncate(0);     // Catch: IOException -> L38
        r7.write(r2);     // Catch: IOException -> L38
        r7.force(true);     // Catch: IOException -> L38
        if (r7.size() == 4) goto L40;
        zzaW().zzb().zzb("Error writing to channel. Bytes written", Long.valueOf(r7.size()));     // Catch: IOException -> L38
    L40:
        zzaW().zzk().zzc("Storage version upgraded. Previous, current version", Integer.valueOf(r6), Integer.valueOf(r12));
        return;
    L38:
        e = move-exception;
        zzaW().zzb().zzb("Failed to write to channel", e);
        goto L44
    L53:
        return;
    L9:
        if (r1.isOpen() == false) goto L22;
        ByteBuffer r72 = ByteBuffer.allocate(4);
        r1.position(0);     // Catch: IOException -> L17
        int r13 = r1.read(r72);     // Catch: IOException -> L17
        if (r13 != 4) goto L15;
        r72.flip();     // Catch: IOException -> L17
        r6 = r72.getInt();     // Catch: IOException -> L17
    L48:
        goto L23
    L15:
        if (r13 == (-1)) goto L48;
        zzaW().zze().zzb("Unexpected data length. Bytes read", Integer.valueOf(r13));     // Catch: IOException -> L17
    L17:
        e = move-exception;
        zzaW().zzb().zzb("Failed to read from channel", e);
        goto L23
    L52:
        return;
    }

    public final boolean zzab() {
        zzaX().zzg();
        FileLock r0 = this.zzx;
        if (r0 != null) goto L5;
    L8:
        this.zze.zzu.zzc();
        File r02 = this.zzn.zzaZ().getFilesDir();
        com.google.android.gms.internal.measurement.zzby.zza();
        int r4 = com.google.android.gms.internal.measurement.zzcd.$r8$clinit;
        FileChannel r03 = new RandomAccessFile(new File(new File(r02, "google_app_measurement.db").getPath()), "rw").getChannel();     // Catch: OverlappingFileLockException -> L13 IOException -> L15 FileNotFoundException -> L17
        this.zzy = r03;     // Catch: OverlappingFileLockException -> L13 IOException -> L15 FileNotFoundException -> L17
        FileLock r04 = r03.tryLock();     // Catch: OverlappingFileLockException -> L13 IOException -> L15 FileNotFoundException -> L17
        this.zzx = r04;     // Catch: OverlappingFileLockException -> L13 IOException -> L15 FileNotFoundException -> L17
        if (r04 == null) goto L19;
        zzaW().zzk().zza("Storage concurrent access okay");     // Catch: OverlappingFileLockException -> L13 IOException -> L15 FileNotFoundException -> L17
        return true;
    L19:
        zzaW().zzb().zza("Storage concurrent data access panic");     // Catch: OverlappingFileLockException -> L13 IOException -> L15 FileNotFoundException -> L17
        return false;
    L17:
        e = move-exception;
        zzaW().zzb().zzb("Failed to acquire storage lock", e);
        return false;
    L15:
        e = move-exception;
        zzaW().zzb().zzb("Failed to access storage lock file", e);
        return false;
    L13:
        e = move-exception;
        zzaW().zze().zzb("Storage lock already acquired", e);
        return false;
    L5:
        if (r0.isValid() == false) goto L8;
        zzaW().zzk().zza("Storage concurrent access okay");
        return true;
    }

    public final void zzac(zzr r11) {
        if (this.zzz == null) goto L5;
        ArrayList r1 = new ArrayList();
        this.zzA = r1;
        r1.addAll(this.zzz);
    L5:
        zzaw r12 = zzj();
        String r2 = (String) Preconditions.checkNotNull(r11.zza);
        Preconditions.checkNotEmpty(r2);
        r12.zzg();
        r12.zzay();
        SQLiteDatabase r3 = r12.zze();     // Catch: SQLiteException -> L9
        String[] r4 = {r2};     // Catch: SQLiteException -> L9
        int r5 = (((((((((((r3.delete("apps", "app_id=?", r4) + r3.delete("events", "app_id=?", r4)) + r3.delete("events_snapshot", "app_id=?", r4)) + r3.delete("user_attributes", "app_id=?", r4)) + r3.delete("conditional_properties", "app_id=?", r4)) + r3.delete("raw_events", "app_id=?", r4)) + r3.delete("raw_events_metadata", "app_id=?", r4)) + r3.delete("queue", "app_id=?", r4)) + r3.delete("audience_filter_values", "app_id=?", r4)) + r3.delete("main_event_params", "app_id=?", r4)) + r3.delete("default_event_params", "app_id=?", r4)) + r3.delete("trigger_uris", "app_id=?", r4)) + r3.delete("upload_queue", "app_id=?", r4);     // Catch: SQLiteException -> L9
        zzahh.zza();     // Catch: SQLiteException -> L9
        zzic r6 = r12.zzu;     // Catch: SQLiteException -> L9
        if (r6.zzc().zzp(null, zzfy.zzbc) == false) goto L11;
        r5 = r5 + r3.delete("no_data_mode_events", "app_id=?", r4);     // Catch: SQLiteException -> L9
    L11:
        int r52 = r5 + r3.delete("diagnostic_signals", "app_id=?", r4);     // Catch: SQLiteException -> L9
        if (r52 <= 0) goto L17;
        r6.zzaW().zzk().zzc("Reset analytics data. app, records", r2, Integer.valueOf(r52));     // Catch: SQLiteException -> L9
    L17:
        if (r11.zzh == false) goto L22;
        zzai(r11);
        return;
    L22:
        return;
    L9:
        e = move-exception;
        r12.zzu.zzaW().zzb().zzc("Error resetting analytics data. appId, error", zzgu.zzl(r2), e);
        goto L17
    }

    public final void zzad(zzpl r22, zzr r23) {
        zzaX().zzg();
        zzu();
        if (zzaS(r23) == true) goto L6;
        return;
    L6:
        if (r23.zzh == true) goto L9;
        zzap(r23);
        return;
    L9:
        zzpp r4 = zzt();
        String r8 = r22.zzb;
        int r12 = r4.zzs(r8);
        int r13 = 0;
        if (r12 == 0) goto L16;
        zzpp r0 = zzt();
        zzd();
        String r14 = r0.zzE(r8, 24, true);
        if (r8 == null) goto L14;
        r13 = r8.length();
    L14:
        zzpp r9 = zzt();
        zzpo r10 = this.zzK;
        String r11 = r23.zza;
        r9.zzP(r10, r11, r12, "_ev", r14, r13);
        return;
    L16:
        int r17 = zzt().zzM(r8, r22.zza());
        if (r17 == 0) goto L29;
        zzpp r3 = zzt();
        zzd();
        String r19 = r3.zzE(r8, 24, true);
        Object r02 = r22.zza();
        if (r02 != null) goto L21;
    L25:
        int r20 = r13;
        zzt().zzP(this.zzK, r23.zza, r17, "_ev", r19, r20);
        return;
    L21:
        if ((r02 instanceof String) == false) goto L23;
    L26:
        r13 = r02.toString().length();
        goto L25
    L23:
        if ((r02 instanceof CharSequence) == false) goto L25;
    L29:
        Object r112 = zzt().zzN(r8, r22.zza());
        if (r112 != null) goto L32;
        return;
    L32:
        if ("_sid".equals(r8) == false) goto L45;
        long r5 = r22.zzc;
        String r7 = r22.zzf;
        String r92 = (String) Preconditions.checkNotNull(r23.zza);
        zzpn r102 = zzj().zzm(r92, "_sno");
        if (r102 == null) goto L38;
        Object r122 = r102.zze;
        if ((r122 instanceof Long) == false) goto L38;
        long r93 = ((Long) r122).longValue();
    L44:
        zzad(new zzpl("_sno", r5, Long.valueOf(r93 + 1), r7), r23);
    L38:
        if (r102 == null) goto L40;
        zzaW().zze().zzb("Retrieved last session number from database does not contain a valid (long) value", r102.zze);
    L40:
        zzbd r94 = zzj().zzf(r92, "_s");
        if (r94 == null) goto L43;
        zzgs r103 = zzaW().zzk();
        long r142 = r94.zzc;
        r103.zzb("Backfill the session number. Last used session number", Long.valueOf(r142));
        r93 = r142;
        goto L44
    L43:
        r93 = 0;
    L45:
        String r123 = r23.zza;
        zzpn r52 = new zzpn((String) Preconditions.checkNotNull(r123), (String) Preconditions.checkNotNull(r22.zzf), r8, r22.zzc, r112);
        zzgs r03 = zzaW().zzk();
        zzic r6 = this.zzn;
        String r72 = r52.zzc;
        r03.zzc("Setting user property", r6.zzl().zzc(r72), r112);
        zzj().zzb();
    L53:
        th = move-exception;
        zzj().zzd();
        throw th;
    L47:
        if ("_id".equals(r72) == false) goto L55;
        zzpn r04 = zzj().zzm(r123, "_id");     // Catch: Throwable -> L53
        if (r04 == null) goto L55;
        if (r52.zze.equals(r04.zze) == true) goto L55;
        zzj().zzk(r123, "_lair");     // Catch: Throwable -> L53
    L55:
        zzap(r23);     // Catch: Throwable -> L53
        boolean r05 = zzj().zzl(r52);     // Catch: Throwable -> L53
        if ("_sid".equals(r8) == false) goto L62;
        long r2 = zzp().zzu(r23.zzu);     // Catch: Throwable -> L53
        zzh r42 = zzj().zzu(r123);     // Catch: Throwable -> L53
        if (r42 == null) goto L62;
        r42.zzan(r2);     // Catch: Throwable -> L53
        if (r42.zza() == false) goto L62;
        zzj().zzv(r42, false, false);     // Catch: Throwable -> L53
    L62:
        zzj().zzc();     // Catch: Throwable -> L53
        if (r05 == true) goto L65;
        zzaW().zzb().zzc("Too many unique user properties are set. Ignoring user property", r6.zzl().zzc(r72), r52.zze);     // Catch: Throwable -> L53
        zzt().zzP(this.zzK, r123, 9, null, null, 0);     // Catch: Throwable -> L53
    L65:
        zzj().zzd();
    }

    public final void zzae(String r9, zzr r10) {
        zzaX().zzg();
        zzu();
        if (zzaS(r10) == true) goto L6;
        return;
    L6:
        if (r10.zzh == true) goto L9;
        zzap(r10);
        return;
    L9:
        Boolean r0 = zzaU(r10);
        if ("_npa".equals(r9) == false) goto L18;
        if (r0 == null) goto L18;
        zzaW().zzj().zza("Falling back to manifest metadata value for ad personalization");
        long r4 = zzba().currentTimeMillis();
        if (true == r0.booleanValue()) goto L15;
        long r02 = 0;
    L16:
        zzad(new zzpl("_npa", r4, Long.valueOf(r02), "auto"), r10);
        return;
    L15:
        r02 = 1;
    L18:
        zzgs r03 = zzaW().zzj();
        zzic r1 = this.zzn;
        r03.zzb("Removing user property", r1.zzl().zzc(r9));
        zzj().zzb();
        zzap(r10);     // Catch: Throwable -> L22
        if ("_id".equals(r9) == false) goto L24;
        zzj().zzk((String) Preconditions.checkNotNull(r10.zza), "_lair");     // Catch: Throwable -> L22
    L24:
        zzj().zzk((String) Preconditions.checkNotNull(r10.zza), r9);     // Catch: Throwable -> L22
        zzj().zzc();     // Catch: Throwable -> L22
        zzaW().zzj().zzb("User property removed", r1.zzl().zzc(r9));     // Catch: Throwable -> L22
        zzj().zzd();
        return;
    L22:
        th = move-exception;
        zzj().zzd();
        throw th;
    }

    public final void zzaf() {
        this.zzs++;
    }

    public final void zzag() {
        this.zzt++;
    }

    public final zzic zzah() {
        return this.zzn;
    }

    public final void zzai(zzr r35) {
        zzaX().zzg();
        zzu();
        Preconditions.checkNotNull(r35);
        String r8 = r35.zza;
        Preconditions.checkNotEmpty(r8);
        if (zzaS(r35) == true) goto L5;
        return;
    L5:
        zzh r9 = zzj().zzu(r8);
        if (r9 == null) goto L13;
        if (TextUtils.isEmpty(r9.zzf()) == false) goto L13;
        if (TextUtils.isEmpty(r35.zzb) == true) goto L13;
        r9.zzI(0);
        zzj().zzv(r9, false, false);
        zzh().zzh(r8);
    L13:
        if (r35.zzh == true) goto L16;
        zzap(r35);
        return;
    L16:
        long r13 = r35.zzl;
        zzal r92 = zzd();
        zzfx r15 = zzfy.zzbe;
        if (r92.zzp(null, r15) == false) goto L19;
        long r10 = r35.zzF;
    L21:
        if (r13 != 0) goto L26;
        r13 = zzba().currentTimeMillis();
        if (zzd().zzp(null, r15) == false) goto L25;
        r10 = zzba().elapsedRealtime();
        goto L26
    L25:
        r10 = 0;
    L26:
        long r24 = r10;
        long r20 = r13;
        int r102 = r35.zzm;
        if (r102 == 0) goto L30;
        if (r102 == 1) goto L30;
        zzaW().zze().zzc("Incorrect app type, assuming installed app. appId, appType", zzgu.zzl(r8), Integer.valueOf(r102));
        r102 = 0;
    L30:
        zzj().zzb();
        zzpn r132 = zzj().zzm(r8, "_npa");     // Catch: Throwable -> L37
        Boolean r14 = zzaU(r35);     // Catch: Throwable -> L37
        int r26 = r102;
        if (r132 != null) goto L34;
    L39:
        if (r14 != null) goto L41;
        long r142 = r20;
        if (r132 == null) goto L53;
        zzae("_npa", r35);     // Catch: Throwable -> L37
    L53:
        if (zzd().zzp(null, zzfy.zzaW) == false) goto L55;
        zzao(r35, r35.zzD);     // Catch: Throwable -> L37
    L56:
        zzap(r35);     // Catch: Throwable -> L37
        if (r26 != 0) goto L59;
        zzbd r6 = zzj().zzf(r8, "_f");     // Catch: Throwable -> L37
        boolean r12 = false;
    L60:
        if (r6 == null) goto L62;
        long r202 = r142;
        if (r35.zzi == false) goto L154;
        zzE(new zzbh("_cd", new zzbf(new Bundle()), "auto", r202, 0), r35);     // Catch: Throwable -> L37
    L154:
        zzj().zzc();     // Catch: Throwable -> L37
        zzj().zzd();
        return;
    L62:
        long r203 = ((r142 / DurationKt.MILLIS_IN_HOUR) + 1) * DurationKt.MILLIS_IN_HOUR;
        if (r12 == true) goto L147;
        Long r22 = Long.valueOf(r203);     // Catch: Throwable -> L37
        long r204 = r142;
        zzad(new zzpl("_fot", r204, r22, "auto"), r35);     // Catch: Throwable -> L37
        zzaX().zzg();     // Catch: Throwable -> L37
        zzhk r122 = (zzhk) Preconditions.checkNotNull(this.zzm);     // Catch: Throwable -> L37
        if (r8 != null) goto L67;
    L68:
        String r31 = "_elt";
        r122.zza.zzaW().zzf().zza("Install Referrer Reporter was called with invalid app package name");     // Catch: Throwable -> L37
    L100:
        zzaX().zzg();     // Catch: Throwable -> L37
        zzu();     // Catch: Throwable -> L37
        Bundle r62 = new Bundle();     // Catch: Throwable -> L37
        r62.putLong("_c", 1);     // Catch: Throwable -> L37
        r62.putLong("_r", 1);     // Catch: Throwable -> L37
        r62.putLong("_uwa", 0);     // Catch: Throwable -> L37
        r62.putLong("_pfo", 0);     // Catch: Throwable -> L37
        r62.putLong("_sys", 0);     // Catch: Throwable -> L37
        r62.putLong("_sysu", 0);     // Catch: Throwable -> L37
        r62.putLong("_et", 1);     // Catch: Throwable -> L37
        if (r35.zzo == false) goto L103;
        r62.putLong("_dac", 1);     // Catch: Throwable -> L37
    L103:
        String r82 = (String) Preconditions.checkNotNull(r35.zza);     // Catch: Throwable -> L37
        zzaw r0 = zzj();     // Catch: Throwable -> L37
        Preconditions.checkNotEmpty(r82);     // Catch: Throwable -> L37
        r0.zzg();     // Catch: Throwable -> L37
        r0.zzay();     // Catch: Throwable -> L37
        long r103 = r0.zzN(r82, "first_open_count");     // Catch: Throwable -> L37
        zzic r02 = this.zzn;     // Catch: Throwable -> L37
        if (r02.zzaZ().getPackageManager() != null) goto L159;
        zzaW().zzb().zzb("PackageManager is null, first open report might be inaccurate. appId", zzgu.zzl(r82));     // Catch: Throwable -> L37
    L144:
        if (r103 < 0) goto L146;
        r62.putLong("_pfo", r103);     // Catch: Throwable -> L37
    L146:
        r62.putLong(r31, zzba().currentTimeMillis());     // Catch: Throwable -> L37
        zzE(new zzbh("_f", new zzbf(r62), "auto", r204, r24), r35);     // Catch: Throwable -> L37
        goto L154
    L159:
        PackageInfo r123 = Wrappers.packageManager(r02.zzaZ()).getPackageInfo(r82, 0);     // Catch: Throwable -> L37 PackageManager.NameNotFoundException -> L109
    L111:
        if (r123 == null) goto L130;
        long r143 = r123.firstInstallTime;     // Catch: Throwable -> L37
        if (r143 == 0) goto L130;
        if (r143 == r123.lastUpdateTime) goto L123;
        ApplicationInfo r133 = null;
        if (zzd().zzp(null, zzfy.zzaI) == true) goto L119;
        r62.putLong("_uwa", 1);     // Catch: Throwable -> L37
    L121:
        boolean r152 = false;
    L125:
        if (true == r152) goto L127;
        long r144 = 0;
    L128:
        zzad(new zzpl("_fi", r204, Long.valueOf(r144), "auto"), r35);     // Catch: Throwable -> L37
    L165:
        ApplicationInfo r03 = Wrappers.packageManager(this.zzn.zzaZ()).getApplicationInfo(r82, 0);     // Catch: Throwable -> L37 PackageManager.NameNotFoundException -> L133
    L135:
        if (r03 == null) goto L144;
        if ((r03.flags & 1) == 0) goto L139;
        long r145 = 1;
        r62.putLong("_sys", 1);     // Catch: Throwable -> L37
    L141:
        if ((r03.flags & 128) == 0) goto L144;
        r62.putLong("_sysu", r145);     // Catch: Throwable -> L37
        goto L144
    L139:
        r145 = 1;
        goto L141
    L133:
        e = move-exception;
        zzaW().zzb().zzc("Application info is null, first open report might be inaccurate. appId", zzgu.zzl(r82), e);     // Catch: Throwable -> L37
        r03 = r133;
        goto L135
    L127:
        r144 = 1;
        goto L128
    L119:
        if (r103 != 0) goto L121;
        r62.putLong("_uwa", 1);     // Catch: Throwable -> L37
        r103 = 0;
        goto L121
    L123:
        r133 = null;
        r152 = true;
    L130:
        r133 = null;
        goto L165
    L109:
        e = move-exception;
        zzaW().zzb().zzc("Package info is null, first open report might be inaccurate. appId", zzgu.zzl(r82), e);     // Catch: Throwable -> L37
        r123 = null;
        goto L111
    L67:
        if (r8.isEmpty() == true) goto L68;
        zzic r146 = r122.zza;     // Catch: Throwable -> L37
        r146.zzaX().zzg();     // Catch: Throwable -> L37
        if (r122.zza() == true) goto L72;
        r146.zzaW().zzi().zza("Install Referrer Reporter is not available");     // Catch: Throwable -> L37
        r31 = "_elt";
        goto L100
    L72:
        zzhj r153 = new zzhj(r122, r8);     // Catch: Throwable -> L37
        r146.zzaX().zzg();     // Catch: Throwable -> L37
        Intent r83 = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");     // Catch: Throwable -> L37
        r31 = "_elt";
        r83.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));     // Catch: Throwable -> L37
        PackageManager r63 = r146.zzaZ().getPackageManager();     // Catch: Throwable -> L37
        if (r63 != null) goto L75;
        r146.zzaW().zzf().zza("Failed to obtain Package Manager to verify binding conditions for Install Referrer");     // Catch: Throwable -> L37
        goto L100
    L75:
        List<ResolveInfo> r64 = r63.queryIntentServices(r83, 0);     // Catch: Throwable -> L37
        if (r64 != null) goto L78;
    L98:
        r122.zza.zzaW().zzi().zza("Play Service for fetching Install Referrer is unavailable on device");     // Catch: Throwable -> L37
        goto L100
    L78:
        if (r64.isEmpty() == true) goto L98;
        ServiceInfo r65 = r64.get(0).serviceInfo;     // Catch: Throwable -> L37
        if (r65 == null) goto L100;
        String r147 = r65.packageName;     // Catch: Throwable -> L37
        if (r65.name != null) goto L84;
    L97:
        r122.zza.zzaW().zze().zza("Play Store version 8.3.73 or higher required for Install Referrer");     // Catch: Throwable -> L37
        goto L100
    L84:
        if ("com.android.vending".equals(r147) == false) goto L97;
        if (r122.zza() == false) goto L97;
        boolean r04 = ConnectionTracker.getInstance().bindService(r146.zzaZ(), new Intent(r83), r153, 1);     // Catch: Throwable -> L37 RuntimeException -> L91
        zzgs r66 = r146.zzaW().zzk();     // Catch: Throwable -> L37 RuntimeException -> L91
        if (r04 == false) goto L93;
        String r05 = "available";
    L94:
        r66.zzb("Install Referrer Service is", r05);     // Catch: Throwable -> L37 RuntimeException -> L91
    L93:
        r05 = "not available";
    L91:
        e = move-exception;
        r122.zza.zzaW().zzb().zzb("Exception occurred while binding to Install Referrer Service", e.getMessage());     // Catch: Throwable -> L37
        goto L100
    L147:
        long r222 = r142;
        zzad(new zzpl("_fvt", r222, Long.valueOf(r203), "auto"), r35);     // Catch: Throwable -> L37
        zzaX().zzg();     // Catch: Throwable -> L37
        zzu();     // Catch: Throwable -> L37
        Bundle r06 = new Bundle();     // Catch: Throwable -> L37
        r06.putLong("_c", 1);     // Catch: Throwable -> L37
        r06.putLong("_r", 1);     // Catch: Throwable -> L37
        r06.putLong("_et", 1);     // Catch: Throwable -> L37
        if (r35.zzo == false) goto L150;
        r06.putLong("_dac", 1);     // Catch: Throwable -> L37
    L150:
        r06.putLong("_elt", zzba().currentTimeMillis());     // Catch: Throwable -> L37
        zzE(new zzbh("_v", new zzbf(r06), "auto", r222, r24), r35);     // Catch: Throwable -> L37
        goto L154
    L59:
        r6 = zzj().zzf(r8, "_v");     // Catch: Throwable -> L37
        r12 = true;
        goto L60
    L55:
        zzao(r35, r142);     // Catch: Throwable -> L37
        goto L56
    L41:
        if (true == r14.booleanValue()) goto L43;
        long r148 = 0;
    L44:
        zzpl r18 = new zzpl("_npa", r20, Long.valueOf(r148), "auto");     // Catch: Throwable -> L37
        r142 = r20;
        if (r132 != null) goto L47;
    L48:
        zzad(r18, r35);     // Catch: Throwable -> L37
        goto L53
    L47:
        if (r132.zze.equals(r18.zzd) == true) goto L53;
    L43:
        r148 = 1;
        goto L44
    L34:
        if ("auto".equals(r132.zzb) == true) goto L39;
        r142 = r20;
    L37:
        th = move-exception;
        zzj().zzd();
        throw th;
    L19:
        r10 = 0;
        goto L21
    }

    public final void zzaj(zzr r9) {
        zzaX().zzg();
        zzu();
        Preconditions.checkNotNull(r9);
        String r0 = r9.zza;
        Preconditions.checkNotEmpty(r0);
        int r2 = 0;
        if (zzd().zzp(null, zzfy.zzay) == false) goto L9;
        long r4 = zzba().currentTimeMillis();
        int r1 = zzd().zzm(null, zzfy.zzah);
        zzd();
        long r42 = r4 - zzal.zzF();
    L5:
        if (r2 >= r1) goto L16;
        if (zzaH(null, r42) == false) goto L16;
        r2 = r2 + 1;
    L16:
        if (zzd().zzp(null, zzfy.zzaz) == false) goto L19;
        zzaX().zzg();
        zzaG();
    L19:
        if (this.zzl.zzc(r0, com.google.android.gms.internal.measurement.zzin.zzb(r9.zzE)) == false) goto L26;
        zzaW().zzk().zzb("[sgtm] Going background, trigger client side upload. appId", r0);
        zzN(r0, zzba().currentTimeMillis());
        return;
    L26:
        return;
    L9:
        zzd();
        long r43 = zzal.zzH();
    L11:
        if (r2 >= r43) goto L16;
        if (zzaH(r0, 0) == false) goto L16;
        r2 = r2 + 1;
        goto L11
    }

    public final void zzak(zzah r2) {
        zzr r0 = zzaP((String) Preconditions.checkNotNull(r2.zza));
        if (r0 == null) goto L6;
        zzal(r2, r0);
        return;
    }

    public final void zzal(zzah r11, zzr r12) {
        Preconditions.checkNotNull(r11);
        Preconditions.checkNotEmpty(r11.zza);
        Preconditions.checkNotNull(r11.zzb);
        Preconditions.checkNotNull(r11.zzc);
        Preconditions.checkNotEmpty(r11.zzc.zzb);
        zzaX().zzg();
        zzu();
        if (zzaS(r12) == true) goto L6;
        return;
    L6:
        if (r12.zzh == true) goto L9;
        zzap(r12);
        return;
    L9:
        zzah r0 = new zzah(r11);
        boolean r112 = false;
        r0.zze = false;
        zzj().zzb();
        zzah r1 = zzj().zzq((String) Preconditions.checkNotNull(r0.zza), r0.zzc.zzb);     // Catch: Throwable -> L15
        if (r1 == null) goto L18;
        if (r1.zzb.equals(r0.zzb) == true) goto L18;
        zzaW().zze().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzl().zzc(r0.zzc.zzb), r0.zzb, r1.zzb);     // Catch: Throwable -> L15
    L18:
        if (r1 == null) goto L23;
        if (r1.zze == false) goto L23;
        r0.zzb = r1.zzb;     // Catch: Throwable -> L15
        r0.zzd = r1.zzd;     // Catch: Throwable -> L15
        r0.zzh = r1.zzh;     // Catch: Throwable -> L15
        r0.zzf = r1.zzf;     // Catch: Throwable -> L15
        r0.zzi = r1.zzi;     // Catch: Throwable -> L15
        r0.zze = true;     // Catch: Throwable -> L15
        zzpl r2 = r0.zzc;     // Catch: Throwable -> L15
        r0.zzc = new zzpl(r2.zzb, r1.zzc.zzc, r2.zza(), r1.zzc.zzf);     // Catch: Throwable -> L15
    L26:
        if (r0.zze == false) goto L36;
        zzpl r13 = r0.zzc;     // Catch: Throwable -> L15
        zzpn r22 = new zzpn((String) Preconditions.checkNotNull(r0.zza), r0.zzb, r13.zzb, r13.zzc, Preconditions.checkNotNull(r13.zza()));     // Catch: Throwable -> L15
        if (zzj().zzl(r22) == false) goto L30;
        zzaW().zzj().zzd("User property updated immediately", r0.zza, this.zzn.zzl().zzc(r22.zzc), r22.zze);     // Catch: Throwable -> L15
    L31:
        if (r112 == false) goto L36;
        if (r0.zzi == null) goto L36;
        zzH(new zzbh(r0.zzi, r0.zzd, 0), r12);     // Catch: Throwable -> L15
        goto L36
    L30:
        zzaW().zzb().zzd("(2)Too many active user properties, ignoring", zzgu.zzl(r0.zza), this.zzn.zzl().zzc(r22.zzc), r22.zze);     // Catch: Throwable -> L15
    L36:
        if (zzj().zzp(r0) == false) goto L38;
        zzaW().zzj().zzd("Conditional property added", r0.zza, this.zzn.zzl().zzc(r0.zzc.zzb), r0.zzc.zza());     // Catch: Throwable -> L15
    L39:
        zzj().zzc();     // Catch: Throwable -> L15
        zzj().zzd();
        return;
    L38:
        zzaW().zzb().zzd("Too many conditional properties, ignoring", zzgu.zzl(r0.zza), this.zzn.zzl().zzc(r0.zzc.zzb), r0.zzc.zza());     // Catch: Throwable -> L15
    L23:
        if (TextUtils.isEmpty(r0.zzf) == false) goto L26;
        zzpl r113 = r0.zzc;     // Catch: Throwable -> L15
        r0.zzc = new zzpl(r113.zzb, r0.zzd, r113.zza(), r0.zzc.zzf);     // Catch: Throwable -> L15
        r0.zze = true;     // Catch: Throwable -> L15
        r112 = true;
    L15:
        th = move-exception;
        zzj().zzd();
        throw th;
    }

    public final void zzam(zzah r2) {
        zzr r0 = zzaP((String) Preconditions.checkNotNull(r2.zza));
        if (r0 == null) goto L6;
        zzan(r2, r0);
        return;
    }

    public final void zzan(zzah r13, zzr r14) {
        Preconditions.checkNotNull(r13);
        Preconditions.checkNotEmpty(r13.zza);
        Preconditions.checkNotNull(r13.zzc);
        Preconditions.checkNotEmpty(r13.zzc.zzb);
        zzaX().zzg();
        zzu();
        if (zzaS(r14) == true) goto L6;
        return;
    L6:
        if (r14.zzh == true) goto L9;
        zzap(r14);
        return;
    L9:
        zzj().zzb();
        zzap(r14);     // Catch: Throwable -> L15
        String r2 = (String) Preconditions.checkNotNull(r13.zza);     // Catch: Throwable -> L15
        zzah r0 = zzj().zzq(r2, r13.zzc.zzb);     // Catch: Throwable -> L15
        if (r0 == null) goto L25;
        zzaW().zzj().zzc("Removing conditional user property", r13.zza, this.zzn.zzl().zzc(r13.zzc.zzb));     // Catch: Throwable -> L15
        zzj().zzr(r2, r13.zzc.zzb);     // Catch: Throwable -> L15
        if (r0.zze == false) goto L17;
        zzj().zzk(r2, r13.zzc.zzb);     // Catch: Throwable -> L15
    L17:
        zzbh r132 = r13.zzk;     // Catch: Throwable -> L15
        if (r132 == null) goto L26;
        zzbf r1 = r132.zzb;     // Catch: Throwable -> L15
        if (r1 == null) goto L23;
        Bundle r12 = r1.zzf();     // Catch: Throwable -> L15
    L22:
        Bundle r4 = r12;
        zzH((zzbh) Preconditions.checkNotNull(zzt().zzaf(r2, ((zzbh) Preconditions.checkNotNull(r132)).zza, r4, r0.zzb, r132.zzd, r132.zze, true, true)), r14);     // Catch: Throwable -> L15
        goto L26
    L23:
        r12 = null;
    L26:
        zzj().zzc();     // Catch: Throwable -> L15
        zzj().zzd();
        return;
    L25:
        zzaW().zze().zzc("Conditional user property doesn't exist", zzgu.zzl(r13.zza), this.zzn.zzl().zzc(r13.zzc.zzb));     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        zzj().zzd();
        throw th;
    }

    public final void zzao(zzr r19, long r20) {
        zzh r3 = zzj().zzu((String) Preconditions.checkNotNull(r19.zza));
        if (r3 != null) goto L5;
    L16:
        if (r3 == null) goto L40;
        boolean r5 = true;
        if (r3.zzt() != (-2147483648L)) goto L20;
    L22:
        boolean r0 = false;
    L23:
        String r9 = r3.zzr();
        if (r3.zzt() != (-2147483648L)) goto L29;
        if (r9 == null) goto L29;
        if (r9.equals(r19.zzc) == true) goto L29;
    L31:
        if ((r0 | r5) == false) goto L41;
        Bundle r02 = new Bundle();
        r02.putString("_pv", r9);
        zzbh r10 = new zzbh("_au", new zzbf(r02), "auto", r20, 0);
        if (zzd().zzp(null, zzfy.zzaX) == false) goto L36;
        zzE(r10, r19);
        return;
    L36:
        zzF(r10, r19);
        return;
    L41:
        return;
    L29:
        r5 = false;
        goto L31
    L20:
        if (r3.zzt() == r19.zzj) goto L22;
        r0 = true;
        goto L23
    L40:
        return;
    L5:
        if (zzt().zzD(r19.zzb, r3.zzf()) == false) goto L16;
        zzaW().zze().zzb("New GMP App Id passed in. Removing cached database data. appId", zzgu.zzl(r3.zzc()));
        zzaw r52 = zzj();
        String r32 = r3.zzc();
        r52.zzay();
        r52.zzg();
        Preconditions.checkNotEmpty(r32);
        SQLiteDatabase r6 = r52.zze();     // Catch: SQLiteException -> L10
        String[] r7 = {r32};     // Catch: SQLiteException -> L10
        int r8 = (((((((((((r6.delete("events", "app_id=?", r7) + r6.delete("user_attributes", "app_id=?", r7)) + r6.delete("conditional_properties", "app_id=?", r7)) + r6.delete("apps", "app_id=?", r7)) + r6.delete("raw_events", "app_id=?", r7)) + r6.delete("raw_events_metadata", "app_id=?", r7)) + r6.delete("event_filters", "app_id=?", r7)) + r6.delete("property_filters", "app_id=?", r7)) + r6.delete("audience_filter_values", "app_id=?", r7)) + r6.delete("consent_settings", "app_id=?", r7)) + r6.delete("default_event_params", "app_id=?", r7)) + r6.delete("trigger_uris", "app_id=?", r7)) + r6.delete("diagnostic_signals", "app_id=?", r7);     // Catch: SQLiteException -> L10
        zzahh.zza();     // Catch: SQLiteException -> L10
        zzic r92 = r52.zzu;     // Catch: SQLiteException -> L10
        if (r92.zzc().zzp(null, zzfy.zzbc) == false) goto L12;
        r8 = r8 + r6.delete("no_data_mode_events", "app_id=?", r7);     // Catch: SQLiteException -> L10
    L12:
        if (r8 <= 0) goto L14;
        r92.zzaW().zzk().zzc("Deleted application data. app, records", r32, Integer.valueOf(r8));     // Catch: SQLiteException -> L10
    L14:
        r3 = null;
    L10:
        e = move-exception;
        r52.zzu.zzaW().zzb().zzc("Error deleting application data. appId, error", zzgu.zzl(r32), e);
        goto L14
    }

    public final zzh zzap(zzr r13) {
        zzaX().zzg();
        zzu();
        Preconditions.checkNotNull(r13);
        String r2 = r13.zza;
        Preconditions.checkNotEmpty(r2);
        String r0 = r13.zzt;
        if (r0.isEmpty() == true) goto L5;
        this.zzE.put(r2, new zzpd(this, r0, null));
    L5:
        zzh r02 = zzj().zzu(r2);
        zzjl r1 = zzB(r2).zzs(zzjl.zzf(r13.zzs, 100));
        String r3 = this.zzk.zzf(r13, r1);
        boolean r9 = true;
        if (r02 != null) goto L16;
        zzh r4 = new zzh(this.zzn, r2);
        if (r1.zzo(zzjk.zzb) == false) goto L11;
        r4.zze(zzK(r1));
    L11:
        if (r1.zzo(zzjk.zza) == false) goto L13;
        r4.zzk(r3);
    L13:
        r02 = r4;
    L14:
        boolean r11 = false;
    L44:
        r02.zzg(r13.zzb);
        String r12 = r13.zzk;
        if (TextUtils.isEmpty(r12) == true) goto L47;
        r02.zzm(r12);
    L47:
        long r14 = r13.zze;
        if (r14 == 0) goto L50;
        r02.zzy(r14);
    L50:
        String r15 = r13.zzc;
        if (TextUtils.isEmpty(r15) == true) goto L53;
        r02.zzs(r15);
    L53:
        r02.zzu(r13.zzj);
        String r16 = r13.zzd;
        if (r16 == null) goto L56;
        r02.zzw(r16);
    L56:
        r02.zzA(r13.zzf);
        r02.zzE(r13.zzh);
        String r17 = r13.zzg;
        if (TextUtils.isEmpty(r17) == true) goto L59;
        r02.zzab(r17);
    L59:
        r02.zzad(r13.zzn);
        r02.zzaf(r13.zzp);
        r02.zzC(r13.zzq);
        r02.zzi(r13.zzu);
        zzahk.zza();
        if (zzd().zzp(null, zzfy.zzaL) == false) goto L62;
        r02.zzah(r13.zzr);
    L65:
        r02.zzaj(r13.zzv);
        r02.zzaz(r13.zzB);
        zzaif.zza();
        if (zzd().zzp(null, zzfy.zzaO) == false) goto L68;
        r02.zzap(r13.zzz);
    L68:
        r02.zzal(r13.zzw);
        r02.zzaG(r13.zzC);
        r02.zzaK(r13.zzE);
        if (r02.zza() == true) goto L73;
        if (r11 == true) goto L74;
        return r02;
    L74:
        zzj().zzv(r02, r9, false);
        return r02;
    L73:
        r9 = r11;
        goto L74
    L62:
        zzahk.zza();
        if (zzd().zzp(null, zzfy.zzaK) == false) goto L65;
        r02.zzah(null);
        goto L65
    L16:
        if (r1.zzo(zzjk.zza) == false) goto L40;
        if (r3 == null) goto L40;
        if (r3.equals(r02.zzj()) == true) goto L40;
        boolean r42 = TextUtils.isEmpty(r02.zzj());
        r02.zzk(r3);
        if (r13.zzn == false) goto L35;
        if ("00000000-0000-0000-0000-000000000000".equals(this.zzk.zzc(r13, r1).first) == true) goto L35;
        if (r42 == true) goto L35;
        if (r1.zzo(zzjk.zzb) == false) goto L28;
        r02.zze(zzK(r1));
        r11 = false;
    L30:
        if (zzj().zzm(r2, "_id") == null) goto L44;
        if (zzj().zzm(r2, "_lair") != null) goto L44;
        zzj().zzl(new zzpn(r2, "auto", "_lair", zzba().currentTimeMillis(), 1L));
        goto L44
    L28:
        r11 = true;
    L35:
        if (TextUtils.isEmpty(r02.zzd()) == false) goto L14;
        if (r1.zzo(zzjk.zzb) == false) goto L14;
        r02.zze(zzK(r1));
    L40:
        if (TextUtils.isEmpty(r02.zzd()) == false) goto L14;
        if (r1.zzo(zzjk.zzb) == false) goto L14;
        r02.zze(zzK(r1));
        goto L14
    }

    public final String zzaq(zzr r5) {
        Future r0 = zzaX().zzh(new zzoz(this, r5));
        return (String) r0.get(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
    L9:
        e = move-exception;
        zzaW().zzb().zzc("Failed to get app instance id. appId", zzgu.zzl(r5.zza), e);
        return null;
    }

    public final List zzar(zzr r17, Bundle r18) {
        zzaX().zzg();
        zzaif.zza();
        zzal r2 = zzd();
        String r3 = r17.zza;
        if (r2.zzp(r3, zzfy.zzaO) == false) goto L45;
        if (r3 == null) goto L45;
        if (r18 == null) goto L23;
        int[] r4 = r18.getIntArray("uriSources");
        long[] r5 = r18.getLongArray("uriTimestamps");
        if (r4 == null) goto L23;
        if (r5 != null) goto L11;
    L22:
        zzaW().zzb().zza("Uri sources and timestamps do not match");
        goto L23
    L11:
        if (r5.length != r4.length) goto L22;
        int r6 = 0;
    L15:
        if (r6 >= r4.length) goto L23;
        zzaw r7 = zzj();
        int r0 = r4[r6];
        long r8 = r5[r6];
        Preconditions.checkNotEmpty(r3);
        r7.zzg();
        r7.zzay();
        int r22 = r7.zze().delete("trigger_uris", "app_id=? and source=? and timestamp_millis<=?", new String[]{r3, String.valueOf(r0), String.valueOf(r8)});     // Catch: SQLiteException -> L19
        zzgs r12 = r7.zzu.zzaW().zzk();     // Catch: SQLiteException -> L19
        StringBuilder r14 = new StringBuilder(String.valueOf(r22).length() + 46);     // Catch: SQLiteException -> L19
        r14.append("Pruned ");     // Catch: SQLiteException -> L19
        r14.append(r22);     // Catch: SQLiteException -> L19
        r14.append(" trigger URIs. appId, source, timestamp");     // Catch: SQLiteException -> L19
        r12.zzd(r14.toString(), r3, Integer.valueOf(r0), Long.valueOf(r8));     // Catch: SQLiteException -> L19
    L21:
        r6 = r6 + 1;
    L19:
        e = move-exception;
        r7.zzu.zzaW().zzb().zzc("Error pruning trigger URIs. appId", zzgu.zzl(r3), e);
    L23:
        zzaw r23 = zzj();
        String r1 = r17.zza;
        Preconditions.checkNotEmpty(r1);
        r23.zzg();
        r23.zzay();
        List r02 = new ArrayList();
        Cursor r32 = null;
        r32 = r23.zze().query("trigger_uris", new String[]{"trigger_uri", "timestamp_millis", "source"}, "app_id=?", new String[]{r1}, null, null, "rowid", null);     // Catch: Throwable -> L30 SQLiteException -> L32
        if (r32.moveToFirst() == true) goto L27;
    L38:
        if (r32 == null) goto L40;
        r32.close();
    L40:
        return r02;
    L27:
        String r52 = r32.getString(0);     // Catch: Throwable -> L30 SQLiteException -> L32
        if (r52 != null) goto L34;
        r52 = _UrlKt.FRAGMENT_ENCODE_SET;
    L34:
        r02.add(new zzoh(r52, r32.getLong(1), r32.getInt(2)));     // Catch: Throwable -> L30 SQLiteException -> L32
        if (r32.moveToNext() == true) goto L27;
    L30:
        th = move-exception;
        if (r32 == null) goto L43;
        r32.close();
    L43:
        throw th;
    L32:
        e = move-exception;
        r23.zzu.zzaW().zzb().zzc("Error querying trigger uris. appId", zzgu.zzl(r1), e);     // Catch: Throwable -> L30
        r02 = Collections.EMPTY_LIST;     // Catch: Throwable -> L30
    L45:
        return new ArrayList();
    }

    public final void zzas(String r12, zzaf r13) {
        zzaX().zzg();
        zzu();
        zzaw r0 = zzj();
        long r1 = r13.zza;
        zzpj r02 = r0.zzB(r1);
        if (r02 != null) goto L6;
        zzaW().zze().zzc("[sgtm] Queued batch doesn't exist. appId, rowId", r12, Long.valueOf(r1));
        return;
    L6:
        String r03 = r02.zze();
        if (r13.zzb != zzlr.zzb.zza()) goto L26;
        Map r3 = this.zzF;
        if (r3.containsKey(r03) == false) goto L11;
        r3.remove(r03);
    L11:
        zzaw r04 = zzj();
        Long r14 = Long.valueOf(r1);
        r04.zzE(r14);
        zzaW().zzk().zzc("[sgtm] queued batch deleted after successful client upload. appId, rowId", r12, r14);
        long r05 = r13.zzc;
        if (r05 <= 0) goto L24;
        zzaw r2 = zzj();
        r2.zzg();
        r2.zzay();
        Long r32 = Long.valueOf(r05);
        Preconditions.checkNotNull(r32);
        ContentValues r4 = new ContentValues();
        r4.put("upload_type", Integer.valueOf(zzls.zzb.zza()));
        zzic r5 = r2.zzu;
        r4.put("creation_timestamp", Long.valueOf(r5.zzba().currentTimeMillis()));
    L18:
        e = move-exception;
        r2.zzu.zzaW().zzb().zzd("Failed to update google Signal pending batch. appid, rowId", r12, Long.valueOf(r05), e);
        throw e;
    L15:
        if (r2.zze().update("upload_queue", r4, "rowid=? AND app_id=? AND upload_type=?", new String[]{String.valueOf(r05), r12, String.valueOf(zzls.zze.zza())}) == 1) goto L20;
        r5.zzaW().zze().zzc("Google Signal pending batch not updated. appId, rowId", r12, r32);     // Catch: SQLiteException -> L18
    L20:
        zzaW().zzk().zzc("[sgtm] queued Google Signal batch updated. appId, signalRowId", r12, Long.valueOf(r13.zzc));
        zzP(r12);
        return;
    L24:
        return;
    L26:
        if (r13.zzb != zzlr.zzd.zza()) goto L32;
        Map r15 = this.zzF;
        zzpe r22 = (zzpe) r15.get(r03);
        if (r22 != null) goto L30;
        r22 = new zzpe(this);
        r15.put(r03, r22);
    L31:
        zzaW().zzk().zzd("[sgtm] Putting sGTM server in backoff mode. appId, destination, nextRetryInSeconds", r12, r03, Long.valueOf((r22.zzc() - zzba().currentTimeMillis()) / 1000));
        goto L32
    L30:
        r22.zza();
    L32:
        zzaw r06 = zzj();
        Long r132 = Long.valueOf(r13.zza);
        r06.zzK(r132);
        zzaW().zzk().zzc("[sgtm] increased batch retry count after failed client upload. appId, rowId", r12, r132);
    }

    public final void zzat(boolean r1) {
        zzaM();
    }

    public final void zzau(String r2, zzlu r3) {
        zzaX().zzg();
        String r0 = this.zzH;
        if (r0 != null) goto L5;
    L9:
        this.zzH = r2;
        this.zzG = r3;
        return;
    L5:
        if (r0.equals(r2) == true) goto L9;
        if (r3 != null) goto L9;
    }

    public final /* synthetic */ void zzav(zzph r4) {
        zzaX().zzg();
        this.zzm = new zzhk(this);
        zzaw r42 = new zzaw(this);
        r42.zzaz();
        this.zze = r42;
        zzht r43 = this.zzc;
        zzd().zza((zzak) Preconditions.checkNotNull(r43));
        zznn r44 = new zznn(this);
        r44.zzaz();
        this.zzk = r44;
        zzad r45 = new zzad(this);
        r45.zzaz();
        this.zzh = r45;
        zzlp r46 = new zzlp(this);
        r46.zzaz();
        this.zzj = r46;
        zzok r47 = new zzok(this);
        r47.zzaz();
        this.zzg = r47;
        this.zzf = new zzhb(this);
        if (this.zzs == this.zzt) goto L5;
        zzaW().zzb().zzc("Not all upload components initialized", Integer.valueOf(this.zzs), Integer.valueOf(this.zzt));
    L5:
        this.zzo.set(true);
        zzaW().zzk().zza("UploadController is now fully initialized");
    }

    public final /* synthetic */ void zzaw() {
        zzaG();
    }

    public final /* synthetic */ zzic zzay() {
        return this.zzn;
    }

    public final /* synthetic */ Deque zzaz() {
        return this.zzr;
    }

    @Override // com.google.android.gms.measurement.internal.zzjg
    public final Clock zzba() {
        return ((zzic) Preconditions.checkNotNull(this.zzn)).zzba();
    }

    public final void zzc() {
        zzaX().zzg();
        zzj().zzI();
        zzaw r0 = zzj();
        r0.zzg();
        r0.zzay();
        if (r0.zzai() == false) goto L11;
        zzfx r1 = zzfy.zzau;
        if (((Long) r1.zzb(null)).longValue() == 0) goto L11;
        SQLiteDatabase r5 = r0.zze();
        zzic r02 = r0.zzu;
        int r12 = r5.delete("trigger_uris", "abs(timestamp_millis - ?) > cast(? as integer)", new String[]{String.valueOf(r02.zzba().currentTimeMillis()), String.valueOf(r1.zzb(null))});
        if (r12 <= 0) goto L11;
        r02.zzaW().zzk().zzb("Deleted stale trigger uris. rowsDeleted", Integer.valueOf(r12));
    L11:
        if (this.zzk.zzd.zza() != 0) goto L13;
        this.zzk.zzd.zzb(zzba().currentTimeMillis());
    L13:
        zzaM();
    }

    public final zzal zzd() {
        return ((zzic) Preconditions.checkNotNull(this.zzn)).zzc();
    }

    public final zzou zzf() {
        return this.zzl;
    }

    public final zzht zzh() {
        zzht r0 = this.zzc;
        zzaT(r0);
        return r0;
    }

    public final zzgz zzi() {
        zzgz r0 = this.zzd;
        zzaT(r0);
        return r0;
    }

    public final zzaw zzj() {
        zzaw r0 = this.zze;
        zzaT(r0);
        return r0;
    }

    public final zzhb zzk() {
        zzhb r0 = this.zzf;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Network broadcast receiver not created");
        return null;
    }

    public final zzok zzl() {
        zzok r0 = this.zzg;
        zzaT(r0);
        return r0;
    }

    public final zzad zzm() {
        zzad r0 = this.zzh;
        zzaT(r0);
        return r0;
    }

    public final zzlp zzn() {
        zzlp r0 = this.zzj;
        zzaT(r0);
        return r0;
    }

    public final zzpk zzp() {
        zzpk r0 = this.zzi;
        zzaT(r0);
        return r0;
    }

    public final zznn zzq() {
        return this.zzk;
    }

    public final zzgn zzs() {
        return this.zzn.zzl();
    }

    public final zzpp zzt() {
        return ((zzic) Preconditions.checkNotNull(this.zzn)).zzk();
    }

    public final void zzu() {
        if (this.zzo.get() == false) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("UploadController is not initialized");
    }

    public final void zzv(zzr r4) {
        zzaX().zzg();
        zzu();
        String r0 = r4.zza;
        Preconditions.checkNotEmpty(r0);
        int r1 = r4.zzx;
        zzjl r42 = zzjl.zzf(r4.zzs, r1);
        zzB(r0);
        zzaW().zzk().zzc("Setting storage consent for package", r0, r42);
        zzA(r0, r42);
    }

    public final void zzw(zzr r13) {
        zzaX().zzg();
        zzu();
        String r4 = r13.zza;
        Preconditions.checkNotEmpty(r4);
        zzba r132 = zzba.zzg(r13.zzy);
        zzaW().zzk().zzc("Setting DMA consent for package", r4, r132);
        zzaX().zzg();
        zzu();
        zzji r0 = zzba.zzh(zzy(r4), 100).zzc();
        this.zzD.put(r4, r132);
        zzj().zzad(r4, r132);
        zzji r133 = zzba.zzh(zzy(r4), 100).zzc();
        zzaX().zzg();
        zzu();
        zzji r1 = zzji.zzc;
        boolean r2 = true;
        if (r0 == r1) goto L5;
    L7:
        boolean r5 = false;
    L9:
        if (r0 != zzji.zzd) goto L12;
        if (r133 != r1) goto L12;
    L13:
        if (r5 == true) goto L17;
        if (r2 == true) goto L17;
        return;
    L17:
        zzaW().zzk().zzb("Generated _dcu event for", r4);
        Bundle r134 = new Bundle();
        if (zzj().zzw(zzC(), r4, false, false, false, false, false, false, false).zzf >= zzd().zzm(r4, zzfy.zzal)) goto L20;
        r134.putLong("_r", 1);
        zzaW().zzk().zzc("_dcu realtime event count", r4, Long.valueOf(zzj().zzw(zzC(), r4, false, false, false, false, false, true, false).zzf));
    L20:
        this.zzK.zza(r4, "_dcu", r134);
        return;
    L12:
        r2 = false;
        goto L13
    L5:
        if (r133 != zzji.zzd) goto L7;
        r5 = true;
        goto L9
    }

    public final zzba zzx(String r3) {
        zzaX().zzg();
        zzu();
        Map r0 = this.zzD;
        zzba r1 = (zzba) r0.get(r3);
        if (r1 != null) goto L6;
        zzba r2 = zzj().zzab(r3);
        r0.put(r3, r2);
        return r2;
    L6:
        return r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Bundle zzy(String r5) {
        zzaX().zzg();
        zzu();
        if (zzh().zzy(r5) != null) goto L6;
        return null;
    L6:
        Bundle r0 = new Bundle();
        zzjl r1 = zzB(r5);
        r0.putAll(r1.zzn());
        r0.putAll(zzz(r5, zzx(r5), r1, new zzan()).zzf());
        zzpn r12 = zzj().zzm(r5, "_npa");
        if (r12 == null) goto L9;
        int r4 = r12.zze.equals(1L);
    L11:
        if (1 == r4) goto L13;
        String r42 = "granted";
    L14:
        r0.putString("ad_personalization", r42);
        return r0;
    L13:
        r42 = "denied";
        goto L14
    L9:
        r4 = zzaD(r5, new zzan());
        goto L11
    }

    public final zzba zzz(String r10, zzba r11, zzjl r12, zzan r13) {
        int r2 = 90;
        if (zzh().zzy(r10) == null) goto L5;
        zzji r0 = r11.zzc();
        zzji r3 = zzji.zzd;
        if (r0 == r3) goto L33;
        zzji r4 = zzji.zzc;
        if (r0 == r4) goto L33;
        if (r0 != zzji.zzb) goto L20;
        zzht r112 = this.zzc;
        zzjk r02 = zzjk.zzc;
        zzji r113 = r112.zzB(r10, r02);
        if (r113 == zzji.zza) goto L20;
        r13.zzc(r02, zzam.zzi);
        r0 = r113;
    L34:
        boolean r114 = this.zzc.zzz(r10);
        SortedSet r9 = zzh().zzA(r10);
        if (r0 == zzji.zzc) goto L45;
        if (r9.isEmpty() == true) goto L45;
        Boolean r122 = Boolean.TRUE;
        Boolean r132 = Boolean.valueOf(r114);
        String r03 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r114 == false) goto L43;
        r03 = TextUtils.join(_UrlKt.FRAGMENT_ENCODE_SET, r9);
    L43:
        return new zzba(r122, r2, r132, r03);
    L45:
        return new zzba(Boolean.FALSE, r2, Boolean.valueOf(r114), "-");
    L20:
        zzht r115 = this.zzc;
        zzjk r04 = zzjk.zzc;
        zzjk r5 = r115.zzx(r10, r04);
        zzji r123 = r12.zzp();
        if (r123 == r3) goto L23;
        if (r123 == r4) goto L23;
        boolean r7 = false;
    L26:
        if (r5 != zzjk.zza) goto L29;
        if (r7 == false) goto L29;
        r13.zzc(r04, zzam.zzc);
        r0 = r123;
    L29:
        r13.zzc(r04, zzam.zzb);
        if (true == r115.zzw(r10, r04)) goto L32;
        r0 = r4;
        goto L34
    L32:
        r0 = r3;
    L23:
        r7 = true;
    L33:
        r2 = r11.zzb();
        r13.zzb(zzjk.zzc, r2);
        goto L34
    L5:
        if (r11.zzc() != zzji.zzc) goto L7;
        r2 = r11.zzb();
        r13.zzb(zzjk.zzc, r2);
    L9:
        return new zzba(Boolean.FALSE, r2, Boolean.TRUE, "-");
    L7:
        r13.zzc(zzjk.zzc, zzam.zzj);
        goto L9
    }
}
