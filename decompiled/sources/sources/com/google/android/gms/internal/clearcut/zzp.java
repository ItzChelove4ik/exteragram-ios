package com.google.android.gms.internal.clearcut;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.clearcut.zzgw$zza;
import com.google.android.gms.phenotype.Phenotype;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzp implements ClearcutLogger.zza {
    private static final Charset UTF_8 = null;
    private static final zzao zzaq = null;
    private static final zzao zzar = null;
    private static final ConcurrentHashMap<String, zzae<zzgw$zza>> zzas = null;
    private static final HashMap<String, zzae<String>> zzat = null;

    @VisibleForTesting
    private static Boolean zzau;

    @VisibleForTesting
    private static Long zzav;

    @VisibleForTesting
    private static final zzae<Boolean> zzaw = null;
    private final Context zzh;

    static {
        UTF_8 = Charset.forName("UTF-8");
        zzao r0 = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:samplingrules_").zzd("LogSamplingRules__");
        zzaq = r0;
        zzar = new zzao(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).zzc("gms:playlog:service:sampling_").zzd("LogSampling__");
        zzas = new ConcurrentHashMap();
        zzat = new HashMap();
        zzau = null;
        zzav = null;
        zzaw = r0.zzc("enable_log_sampling_rules", false);
    }

    public zzp(Context r1) {
        this.zzh = r1;
        if (r1 == null) goto L6;
        zzae.maybeInit(r1);
        return;
    }

    @VisibleForTesting
    private static long zza(String r2, long r3) {
        if (r2 == null) goto L10;
        if (r2.isEmpty() == true) goto L10;
        byte[] r22 = r2.getBytes(UTF_8);
        ByteBuffer r0 = ByteBuffer.allocate(r22.length + 8);
        r0.put(r22);
        r0.putLong(r3);
        return zzk.zza(r0.array());
    L10:
        return zzk.zza(ByteBuffer.allocate(8).putLong(r3).array());
    }

    @VisibleForTesting
    private static zzgw$zza.zzb zza(String r10) {
        if (r10 != null) goto L5;
        return null;
    L5:
        int r1 = r10.indexOf(44);
        int r2 = 0;
        if (r1 < 0) goto L8;
        String r22 = r10.substring(0, r1);
        r2 = r1 + 1;
        String r12 = r22;
    L9:
        int r3 = r10.indexOf(47, r2);
        if (r3 <= 0) goto L12;
        long r5 = Long.parseLong(r10.substring(r2, r3));     // Catch: NumberFormatException -> L27
        long r23 = Long.parseLong(r10.substring(r3 + 1));     // Catch: NumberFormatException -> L27
        if (r5 >= 0) goto L21;
    L25:
        StringBuilder r102 = new StringBuilder(72);
        r102.append("negative values not supported: ");
        r102.append(r5);
        r102.append("/");
        r102.append(r23);
        Log.e("LogSamplerImpl", r102.toString());
        return null;
    L21:
        if (r23 < 0) goto L25;
        return zzgw$zza.zzb.zzfz().zzn(r12).zzr(r5).zzs(r23).zzbh();
    L27:
        e = move-exception;
        if (r10.length() == 0) goto L31;
        String r103 = "parseLong() failed while parsing: ".concat(r10);
    L32:
        Log.e("LogSamplerImpl", r103, e);
        return null;
    L31:
        r103 = new String("parseLong() failed while parsing: ");
        goto L32
    L12:
        if (r10.length() == 0) goto L14;
        String r104 = "Failed to parse the rule: ".concat(r10);
    L15:
        Log.e("LogSamplerImpl", r104);
        return null;
    L14:
        r104 = new String("Failed to parse the rule: ");
        goto L15
    L8:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    @VisibleForTesting
    private static boolean zzb(long r6, long r8, long r10) {
        if (r8 >= 0) goto L5;
        return true;
    L5:
        if (r10 > 0) goto L7;
        return true;
    L7:
        if (r6 < 0) goto L9;
        long r62 = r6 % r10;
    L11:
        if (r62 < r8) goto L18;
        return false;
    L18:
        return true;
    L9:
        r62 = (((LongCompanionObject.MAX_VALUE % r10) + 1) + ((r6 & LongCompanionObject.MAX_VALUE) % r10)) % r10;
        goto L11
    }

    private static boolean zzc(Context r1) {
        if (zzau != null) goto L10;
        if (Wrappers.packageManager(r1).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") != 0) goto L7;
        boolean r12 = true;
    L8:
        zzau = Boolean.valueOf(r12);
        goto L10
    L7:
        r12 = false;
    L10:
        return zzau.booleanValue();
    }

    @VisibleForTesting
    private static long zzd(Context r3) {
        if (zzav != null) goto L12;
        long r0 = 0;
        if (r3 != null) goto L7;
        return 0;
    L7:
        if (zzc(r3) == false) goto L9;
        r0 = zzy.getLong(r3.getContentResolver(), "android_id", 0);
    L9:
        zzav = Long.valueOf(r0);
    L12:
        return zzav.longValue();
    }

    @Override // com.google.android.gms.clearcut.ClearcutLogger.zza
    public final boolean zza(com.google.android.gms.clearcut.zze r13) {
        zzr r0 = r13.zzag;
        String r1 = r0.zzj;
        int r02 = r0.zzk;
        zzha r132 = r13.zzaa;
        if (r132 == null) goto L5;
        int r133 = r132.zzbji;
    L6:
        String r4 = null;
        if (zzaw.get().booleanValue() == true) goto L29;
        if (r1 != null) goto L10;
    L12:
        if (r02 < 0) goto L14;
        r1 = String.valueOf(r02);
    L15:
        if (r1 == null) goto L58;
        Context r134 = this.zzh;
        if (r134 != null) goto L19;
    L25:
        zzgw$zza.zzb r135 = zza(r4);
        if (r135 != null) goto L28;
        return true;
    L28:
        return zzb(zza(r135.zzfw(), zzd(this.zzh)), r135.zzfx(), r135.zzfy());
    L19:
        if (zzc(r134) == false) goto L25;
        HashMap<String, zzae<String>> r136 = zzat;
        zzae<String> r03 = r136.get(r1);
        if (r03 != null) goto L24;
        r03 = zzar.zza(r1, null);
        r136.put(r1, r03);
    L24:
        r4 = r03.get();
        goto L25
    L58:
        return true;
    L14:
        r1 = null;
        goto L15
    L10:
        if (r1.isEmpty() == true) goto L12;
    L29:
        if (r1 != null) goto L31;
    L33:
        if (r02 < 0) goto L35;
        r1 = String.valueOf(r02);
    L36:
        if (r1 != null) goto L38;
        return true;
    L38:
        if (this.zzh != null) goto L40;
        List<zzgw$zza.zzb> r04 = Collections.EMPTY_LIST;
    L46:
        Iterator<zzgw$zza.zzb> r05 = r04.iterator();
    L48:
        if (r05.hasNext() == false) goto L70;
        zzgw$zza.zzb r12 = r05.next();
        if (r12.zzfv() == false) goto L56;
        if (r12.getEventCode() == 0) goto L56;
        if (r12.getEventCode() != r133) goto L48;
    L56:
        if (zzb(zza(r12.zzfw(), zzd(this.zzh)), r12.zzfx(), r12.zzfy()) == true) goto L48;
        return false;
    L70:
        return true;
    L40:
        ConcurrentHashMap<String, zzae<zzgw$zza>> r06 = zzas;
        zzae<zzgw$zza> r3 = r06.get(r1);
        if (r3 != null) goto L45;
        r3 = zzaq.zza(r1, zzgw$zza.zzft(), zzq.zzax);
        zzae<zzgw$zza> r07 = r06.putIfAbsent(r1, r3);
        if (r07 == null) goto L45;
        r3 = r07;
    L45:
        r04 = r3.get().zzfs();
        goto L46
    L35:
        r1 = null;
        goto L36
    L31:
        if (r1.isEmpty() == true) goto L33;
    L5:
        r133 = 0;
        goto L6
    }
}
