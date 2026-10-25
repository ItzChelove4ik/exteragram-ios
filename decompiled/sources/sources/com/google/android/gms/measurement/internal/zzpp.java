package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ext.SdkExtensions;
import android.text.TextUtils;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;
import kotlin.time.DurationKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes5.dex */
public final class zzpp extends zzjf {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final String[] zzb = null;
    private static final String[] zzc = null;
    private SecureRandom zzd;
    private final AtomicLong zze;
    private int zzf;
    private MeasurementManagerFutures zzg;
    private Boolean zzh;
    private Integer zzi;

    static {
        zzb = new String[]{"firebase_", "google_", "ga_"};
        zzc = new String[]{"_err"};
    }

    public zzpp(zzic r3) {
        super(r3);
        this.zzi = null;
        this.zze = new AtomicLong(0);
    }

    public static MessageDigest zzQ() {
        int r0 = 0;
    L4:
        if (r0 >= 2) goto L10;
        MessageDigest r1 = MessageDigest.getInstance("MD5");     // Catch: NoSuchAlgorithmException -> L12
        if (r1 == null) goto L9;
        return r1;
    L9:
        r0 = r0 + 1;
        goto L4
    L10:
        return null;
    }

    public static long zzR(byte[] r8) {
        Preconditions.checkNotNull(r8);
        int r0 = r8.length;
        int r1 = 0;
        if (r0 <= 0) goto L5;
        boolean r2 = true;
    L6:
        Preconditions.checkState(r2);
        int r02 = r0 - 1;
        long r22 = 0;
    L7:
        if (r02 < 0) goto L11;
        if (r02 < (r8.length - 8)) goto L11;
        r22 = r22 + ((r8[r02] & 255) << r1);
        r1 = r1 + 8;
        r02 = r02 - 1;
    L11:
        return r22;
    L5:
        r2 = false;
        goto L6
    }

    public static boolean zzS(Context r0, boolean r1) {
        Preconditions.checkNotNull(r0);
        return zzT(r0, "com.google.android.gms.measurement.AppMeasurementJobService");
    }

    public static boolean zzT(Context r3, String r4) {
        PackageManager r1 = r3.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L13
        if (r1 != null) goto L6;
        return false;
    L6:
        ServiceInfo r32 = r1.getServiceInfo(new ComponentName(r3, r4), 0);     // Catch: PackageManager.NameNotFoundException -> L13
        if (r32 == null) goto L12;
        if (r32.enabled == false) goto L12;
        return true;
    L12:
        return false;
    }

    public static final boolean zzaA(Bundle r5, int r6) {
        if (r5 != null) goto L5;
        return false;
    L5:
        if (r5.getLong("_err") != 0) goto L8;
        r5.putLong("_err", r6);
        return true;
    L8:
        return false;
    }

    private final Object zzaB(int r3, Object r4, boolean r5, boolean r6, String r7) {
        if (r4 != null) goto L6;
        return null;
    L6:
        if ((r4 instanceof Long) == false) goto L8;
        return r4;
    L8:
        if ((r4 instanceof Double) == false) goto L11;
        return r4;
    L11:
        if ((r4 instanceof Integer) == false) goto L15;
        return Long.valueOf(((Integer) r4).intValue());
    L15:
        if ((r4 instanceof Byte) == false) goto L19;
        return Long.valueOf(((Byte) r4).byteValue());
    L19:
        if ((r4 instanceof Short) == false) goto L23;
        return Long.valueOf(((Short) r4).shortValue());
    L23:
        if ((r4 instanceof Boolean) == false) goto L31;
        if (true == ((Boolean) r4).booleanValue()) goto L27;
        long r2 = 0;
    L29:
        return Long.valueOf(r2);
    L27:
        r2 = 1;
        goto L29
    L31:
        if ((r4 instanceof Float) == false) goto L35;
        return Double.valueOf(((Float) r4).doubleValue());
    L35:
        if ((r4 instanceof String) == true) goto L58;
        if ((r4 instanceof Character) == true) goto L58;
        if ((r4 instanceof CharSequence) == true) goto L58;
        if (r6 == true) goto L43;
    L56:
        return null;
    L43:
        if ((r4 instanceof Bundle[]) == false) goto L45;
    L46:
        ArrayList r32 = new ArrayList();
        Parcelable[] r42 = (Parcelable[]) r4;
        int r52 = r42.length;
        int r62 = 0;
    L47:
        if (r62 >= r52) goto L55;
        Parcelable r0 = r42[r62];
        if ((r0 instanceof Bundle) == false) goto L53;
        Bundle r02 = zzae((Bundle) r0, null);
        if (r02.isEmpty() == true) goto L53;
        r32.add(r02);
    L53:
        r62 = r62 + 1;
        goto L47
    L55:
        return r32.toArray(new Bundle[r32.size()]);
    L45:
        if ((r4 instanceof Parcelable[]) == false) goto L56;
    L58:
        return zzE(r4.toString(), r3, r5);
    }

    private final int zzaC(String r2) {
        if ("_ldl".equals(r2) == false) goto L7;
        this.zzu.zzc();
        return 2048;
    L7:
        if ("_id".equals(r2) == false) goto L10;
        this.zzu.zzc();
        return 256;
    L10:
        boolean r22 = "_lgclid".equals(r2);
        zzic r1 = this.zzu;
        if (r22 == false) goto L14;
        r1.zzc();
        return 100;
    L14:
        r1.zzc();
        return 36;
    }

    private static boolean zzaD(String r3, String[] r4) {
        Preconditions.checkNotNull(r4);
        int r1 = 0;
    L4:
        if (r1 >= r4.length) goto L10;
        if (Objects.equals(r3, r4[r1]) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    private static final boolean zzaE(String r2, String r3) {
        if (TextUtils.isEmpty(r2) == false) goto L6;
        return false;
    L6:
        if (r2.equals("*") == false) goto L8;
        return true;
    L8:
        if (Arrays.asList(r2.split(",")).contains(r3) == true) goto L13;
        return false;
    L13:
        return true;
    }

    public static boolean zzac(String r1) {
        if (TextUtils.isEmpty(r1) == false) goto L5;
        return false;
    L5:
        if (r1.startsWith("_") == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean zzai(String r2) {
        if (zzc[0].equals(r2) == false) goto L5;
        return false;
    L5:
        return true;
    }

    public static ArrayList zzav(List r6) {
        if (r6 == null) goto L4;
        ArrayList r0 = new ArrayList(r6.size());
        Iterator r62 = r6.iterator();
    L7:
        if (r62.hasNext() == false) goto L27;
        zzah r1 = (zzah) r62.next();
        Bundle r2 = new Bundle();
        r2.putString("app_id", r1.zza);
        r2.putString("origin", r1.zzb);
        r2.putLong("creation_timestamp", r1.zzd);
        r2.putString("name", r1.zzc.zzb);
        zzjh.zza(r2, Preconditions.checkNotNull(r1.zzc.zza()));
        r2.putBoolean("active", r1.zze);
        String r3 = r1.zzf;
        if (r3 == null) goto L11;
        r2.putString("trigger_event_name", r3);
    L11:
        zzbh r32 = r1.zzg;
        if (r32 == null) goto L16;
        r2.putString("timed_out_event_name", r32.zza);
        zzbf r33 = r32.zzb;
        if (r33 == null) goto L16;
        r2.putBundle("timed_out_event_params", r33.zzf());
    L16:
        r2.putLong("trigger_timeout", r1.zzh);
        zzbh r34 = r1.zzi;
        if (r34 == null) goto L21;
        r2.putString("triggered_event_name", r34.zza);
        zzbf r35 = r34.zzb;
        if (r35 == null) goto L21;
        r2.putBundle("triggered_event_params", r35.zzf());
    L21:
        r2.putLong("triggered_timestamp", r1.zzc.zzc);
        r2.putLong("time_to_live", r1.zzj);
        zzbh r12 = r1.zzk;
        if (r12 == null) goto L26;
        r2.putString("expired_event_name", r12.zza);
        zzbf r13 = r12.zzb;
        if (r13 == null) goto L26;
        r2.putBundle("expired_event_params", r13.zzf());
    L26:
        r0.add(r2);
        goto L7
    L27:
        return r0;
    L4:
        return new ArrayList(0);
    }

    public static boolean zzax(Context r4) {
        Preconditions.checkNotNull(r4);
        PackageManager r1 = r4.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L13
        if (r1 != null) goto L6;
        return false;
    L6:
        ActivityInfo r42 = r1.getReceiverInfo(new ComponentName(r4, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0);     // Catch: PackageManager.NameNotFoundException -> L13
        if (r42 == null) goto L12;
        if (r42.enabled == false) goto L12;
        return true;
    L12:
        return false;
    }

    public static void zzay(zzlu r4, Bundle r5, boolean r6) {
        if (r5 == null) goto L20;
        if (r4 == null) goto L20;
        if (r5.containsKey("_sc") == false) goto L10;
        if (r6 == true) goto L10;
        r6 = false;
    L10:
        String r62 = r4.zza;
        if (r62 == null) goto L13;
        r5.putString("_sn", r62);
    L14:
        String r63 = r4.zzb;
        if (r63 == null) goto L17;
        r5.putString("_sc", r63);
    L18:
        r5.putLong("_si", r4.zzc);
        return;
    L17:
        r5.remove("_sc");
        goto L18
    L13:
        r5.remove("_sn");
    L20:
        if (r5 == null) goto L25;
        if (r4 != null) goto L26;
        if (r6 == false) goto L27;
        r5.remove("_sn");
        r5.remove("_sc");
        r5.remove("_si");
        return;
    L27:
        return;
    L26:
        return;
    }

    public static boolean zzh(String r3) {
        Preconditions.checkNotEmpty(r3);
        if (r3.charAt(0) == '_') goto L5;
        return true;
    L5:
        if (r3.equals("_ep") == true) goto L10;
        return false;
    L10:
        return true;
    }

    public final boolean zzA(String r4, String r5, int r6, Object r7) {
        if (r7 != null) goto L6;
        return true;
    L6:
        if ((r7 instanceof Long) == false) goto L8;
    L32:
        return true;
    L8:
        if ((r7 instanceof Float) == true) goto L32;
        if ((r7 instanceof Integer) == true) goto L32;
        if ((r7 instanceof Byte) == true) goto L32;
        if ((r7 instanceof Short) == true) goto L32;
        if ((r7 instanceof Boolean) == true) goto L32;
        if ((r7 instanceof Double) == true) goto L32;
        if ((r7 instanceof String) == false) goto L23;
    L28:
        String r72 = r7.toString();
        if (r72.codePointCount(0, r72.length()) <= r6) goto L32;
        this.zzu.zzaW().zzh().zzd("Value is too long; discarded. Value kind, name, value length", r4, r5, Integer.valueOf(r72.length()));
        return false;
    L23:
        if ((r7 instanceof Character) == true) goto L28;
        if ((r7 instanceof CharSequence) == true) goto L28;
        return false;
    }

    public final void zzB(String r18, String r19, String r20, Bundle r21, List r22, boolean r23) {
        List r6 = r22;
        if (r21 == null) goto L49;
        zzic r9 = this.zzu;
        if (true == r9.zzc().zzu.zzk().zzaj(231100000, true)) goto L8;
        int r13 = 0;
    L9:
        Iterator r14 = new TreeSet(r21.keySet()).iterator();
        int r15 = 0;
        boolean r16 = false;
    L11:
        if (r14.hasNext() == false) goto L61;
        String r3 = (String) r14.next();
        if (r6 != null) goto L15;
    L18:
        if (r23 == true) goto L20;
        int r1 = zzt(r3);
    L21:
        if (r1 != 0) goto L23;
        r1 = zzu(r3);
    L23:
        if (r1 != 0) goto L25;
        if (zzz(r21.get(r3)) == false) goto L32;
        r9.zzaW().zzh().zzd("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", r19, r20, r3);
        int r12 = 22;
    L33:
        if (r12 == 0) goto L38;
        if ("_ev".equals(r3) == true) goto L38;
        zzL(r21, r12, r3, r21.get(r3));
        r21.remove(r3);
    L48:
        r6 = r22;
    L38:
        if (zzh(r3) == false) goto L48;
        if (zzaD(r3, zzjn.zzd) == true) goto L48;
        r15 = r15 + 1;
        if (zzaj(231100000, true) == false) goto L43;
        if (r15 <= r13) goto L48;
        if (r16 == true) goto L47;
        zzgs r17 = r9.zzaW().zzd();
        StringBuilder r62 = new StringBuilder(String.valueOf(r13).length() + 55);
        r62.append("Item can't contain more than ");
        r62.append(r13);
        r62.append(" item-scoped custom params");
        r17.zzc(r62.toString(), r9.zzl().zza(r19), r9.zzl().zze(r21));
    L47:
        zzaA(r21, 28);
        r21.remove(r3);
        r6 = r22;
        r16 = true;
        goto L11
    L43:
        r9.zzaW().zzd().zzc("Item array not supported on client's version of Google Play Services (Android Only)", r9.zzl().zza(r19), r9.zzl().zze(r21));
        zzaA(r21, 23);
        r21.remove(r3);
        goto L48
    L32:
        r12 = zzF(r18, r19, r3, r21.get(r3), r21, r6, r23, false);
        goto L33
    L25:
        if (r1 != 3) goto L27;
        String r4 = r3;
    L28:
        zzL(r21, r1, r3, r4);
        r21.remove(r3);
        goto L48
    L27:
        r4 = null;
        goto L28
    L20:
        r1 = 0;
        goto L21
    L15:
        if (r6.contains(r3) == false) goto L18;
        r1 = 0;
        goto L23
    L61:
        return;
    L8:
        r13 = 35;
        goto L9
    }

    public final boolean zzC(String r3) {
        if (TextUtils.isEmpty(r3) == true) goto L10;
        Preconditions.checkNotNull(r3);
        if (r3.matches("^1:\\d+:android:[a-f0-9]+$") == true) goto L8;
        this.zzu.zzaW().zzd().zzb("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzgu.zzl(r3));
        return false;
    L8:
        return true;
    L10:
        this.zzu.zzaW().zzd().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
        return false;
    }

    public final boolean zzD(String r3, String r4) {
        boolean r2 = TextUtils.isEmpty(r3);
        boolean r0 = TextUtils.isEmpty(r4);
        if (r2 == true) goto L9;
        if (r0 == true) goto L9;
        Preconditions.checkNotNull(r3);
        if (r3.equals(r4) == true) goto L9;
        return true;
    L9:
        return false;
    }

    public final String zzE(String r3, int r4, boolean r5) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3.codePointCount(0, r3.length()) <= r4) goto L10;
        if (r5 == true) goto L9;
        return null;
    L9:
        return r3.substring(0, r3.offsetByCodePoints(0, r4)).concat("...");
    L10:
        return r3;
    }

    public final int zzF(String r13, String r14, String r15, Object r16, Bundle r17, List r18, boolean r19, boolean r20) {
        zzg();
        if (zzz(r16) == false) goto L35;
        if (r20 == true) goto L6;
        return 21;
    L6:
        if (zzaD(r15, zzjn.zzc) == true) goto L9;
        return 20;
    L9:
        zznl r4 = this.zzu.zzt();
        r4.zzg();
        r4.zzb();
        if (r4.zzK() == true) goto L13;
    L16:
        zzic r42 = this.zzu;
        r42.zzc();
        boolean r7 = r16 instanceof Parcelable[];
        if (r7 == false) goto L20;
        int r8 = ((Parcelable[]) r16).length;
    L23:
        if (r8 <= 200) goto L35;
        r42.zzaW().zzh().zzd("Parameter array is too long; discarded. Value kind, name, array length", "param", r15, Integer.valueOf(r8));
        r42.zzc();
        if (r7 == false) goto L31;
        Parcelable[] r72 = (Parcelable[]) r16;
        if (r72.length <= 200) goto L29;
        r17.putParcelableArray(r15, (Parcelable[]) Arrays.copyOf(r72, 200));
    L29:
        int r73 = 17;
    L39:
        if (zzac(r14) == false) goto L41;
    L44:
        int r2 = this.zzu.zzc().zzf(null, false);
    L46:
        if (zzA("param", r15, r2, r16) == true) goto L73;
        if (r20 == true) goto L50;
        return 4;
    L50:
        if ((r16 instanceof Bundle) == false) goto L54;
        zzB(r13, r14, r15, (Bundle) r16, r18, r19);
        return r73;
    L54:
        if ((r16 instanceof Parcelable[]) == false) goto L62;
        Parcelable[] r82 = (Parcelable[]) r16;
        int r9 = r82.length;
        int r10 = 0;
    L56:
        if (r10 >= r9) goto L73;
        Parcelable r1 = r82[r10];
        if ((r1 instanceof Bundle) == false) goto L59;
        zzB(r13, r14, r15, (Bundle) r1, r18, r19);
        r10 = r10 + 1;
        goto L56
    L59:
        this.zzu.zzaW().zzh().zzc("All Parcelable[] elements must be of type Bundle. Value type, name", r1.getClass(), r15);
        return 4;
    L62:
        if ((r16 instanceof ArrayList) == false) goto L81;
        ArrayList r83 = (ArrayList) r16;
        int r92 = r83.size();
        int r102 = 0;
    L64:
        if (r102 >= r92) goto L73;
        Object r12 = r83.get(r102);
        if ((r12 instanceof Bundle) == false) goto L67;
        zzB(r13, r14, r15, (Bundle) r12, r18, r19);
        r102 = r102 + 1;
        goto L64
    L67:
        zzgs r0 = this.zzu.zzaW().zzh();
        if (r12 == null) goto L70;
        Object r110 = r12.getClass();
    L71:
        r0.zzc("All ArrayList elements must be of type Bundle. Value type, name", r110, r15);
        return 4;
    L70:
        r110 = "null";
        goto L71
    L81:
        return 4;
    L73:
        return r73;
    L41:
        if (zzac(r15) == true) goto L44;
        r2 = this.zzu.zzc().zze(null, false);
        goto L46
    L31:
        if ((r16 instanceof ArrayList) == false) goto L29;
        ArrayList r74 = (ArrayList) r16;
        if (r74.size() <= 200) goto L29;
        r17.putParcelableArrayList(r15, new ArrayList(r74.subList(0, 200)));
        goto L29
    L20:
        if ((r16 instanceof ArrayList) == false) goto L35;
        r8 = ((ArrayList) r16).size();
        goto L23
    L13:
        if (r4.zzu.zzk().zzak() >= 200900) goto L16;
        return 25;
    L35:
        r73 = 0;
        goto L39
    }

    public final Object zzG(String r10, Object r11) {
        if ("_ev".equals(r10) == true) goto L5;
        boolean r9 = zzac(r10);
        zzic r102 = this.zzu;
        if (r9 == false) goto L10;
        int r92 = r102.zzc().zzf(null, false);
    L12:
        return zzaB(r92, r11, false, true, null);
    L10:
        r92 = r102.zzc().zze(null, false);
        goto L12
    L5:
        return zzaB(this.zzu.zzc().zzf(null, false), r11, true, true, null);
    }

    public final Bundle zzH(String r18, String r19, Bundle r20, List r21, boolean r22) {
        List r6 = r21;
        boolean r8 = zzaD(r19, zzjm.zzg);
        if (r20 == null) goto L44;
        Bundle r5 = new Bundle(r20);
        zzic r11 = this.zzu;
        int r12 = r11.zzc().zzc();
        Iterator r13 = new TreeSet(r20.keySet()).iterator();
        int r15 = 0;
        boolean r16 = false;
    L6:
        if (r13.hasNext() == false) goto L43;
        String r3 = (String) r13.next();
        if (r6 != null) goto L10;
    L13:
        if (r22 == true) goto L15;
        int r1 = zzt(r3);
    L16:
        if (r1 != 0) goto L18;
        r1 = zzu(r3);
    L18:
        if (r1 != 0) goto L20;
        int r4 = zzF(r18, r19, r3, r20.get(r3), r5, r6, r22, r8);
        if (r4 != 17) goto L27;
        zzL(r5, 17, r3, Boolean.FALSE);
    L36:
        if (zzh(r3) == false) goto L42;
        r15 = r15 + 1;
        if (r15 <= r12) goto L42;
        if (r16 == true) goto L41;
        StringBuilder r42 = new StringBuilder(String.valueOf(r12).length() + 37);
        r42.append("Event can't contain more than ");
        r42.append(r12);
        r42.append(" params");
        r11.zzaW().zzd().zzc(r42.toString(), r11.zzl().zza(r19), r11.zzl().zze(r20));
    L41:
        zzaA(r5, 5);
        r5.remove(r3);
        r16 = true;
    L42:
        r6 = r21;
        goto L6
    L27:
        if (r4 == 0) goto L36;
        if ("_ev".equals(r3) == true) goto L36;
        if (r4 != 21) goto L33;
        String r14 = r19;
    L34:
        zzL(r5, r4, r14, r20.get(r3));
        r5.remove(r3);
        goto L42
    L33:
        r14 = r3;
        goto L34
    L20:
        if (r1 != 3) goto L22;
        String r43 = r3;
    L23:
        zzL(r5, r1, r3, r43);
        r5.remove(r3);
        goto L42
    L22:
        r43 = null;
        goto L23
    L15:
        r1 = 0;
        goto L16
    L10:
        if (r6.contains(r3) == false) goto L13;
        r1 = 0;
        goto L18
    L43:
        return r5;
    L44:
        return null;
    }

    public final void zzI(zzgv r10, int r11) {
        Bundle r1 = r10.zze;
        Iterator r0 = new TreeSet(r1.keySet()).iterator();
        int r2 = 0;
        boolean r3 = false;
    L4:
        if (r0.hasNext() == false) goto L12;
        String r4 = (String) r0.next();
        if (zzh(r4) == false) goto L4;
        r2 = r2 + 1;
        if (r2 <= r11) goto L4;
        if (r3 == true) goto L11;
        StringBuilder r5 = new StringBuilder(String.valueOf(r11).length() + 37);
        r5.append("Event can't contain more than ");
        r5.append(r11);
        r5.append(" params");
        String r32 = r5.toString();
        zzic r52 = this.zzu;
        r52.zzaW().zzd().zzc(r32, r52.zzl().zza(r10.zza), r52.zzl().zze(r1));
        zzaA(r1, 5);
    L11:
        r1.remove(r4);
        r3 = true;
        goto L4
    }

    public final void zzJ(Parcelable[] r12, int r13) {
        Preconditions.checkNotNull(r12);
        int r0 = r12.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L17;
        Bundle r3 = (Bundle) r12[r2];
        Iterator r4 = new TreeSet(r3.keySet()).iterator();
        int r5 = 0;
        boolean r6 = false;
    L6:
        if (r4.hasNext() == false) goto L16;
        String r7 = (String) r4.next();
        if (zzh(r7) == false) goto L6;
        if (zzaD(r7, zzjn.zzd) == true) goto L6;
        r5 = r5 + 1;
        if (r5 <= r13) goto L6;
        if (r6 == true) goto L15;
        zzic r62 = this.zzu;
        zzgs r8 = r62.zzaW().zzd();
        StringBuilder r10 = new StringBuilder(String.valueOf(r13).length() + 60);
        r10.append("Param can't contain more than ");
        r10.append(r13);
        r10.append(" item-scoped custom parameters");
        r8.zzc(r10.toString(), r62.zzl().zzb(r7), r62.zzl().zze(r3));
    L15:
        zzaA(r3, 28);
        r3.remove(r7);
        r6 = true;
        goto L6
    L16:
        r2 = r2 + 1;
        goto L3
    }

    public final void zzK(Bundle r5, Bundle r6) {
        if (r6 == null) goto L10;
        Iterator<String> r0 = r6.keySet().iterator();
    L6:
        if (r0.hasNext() == false) goto L16;
        String r1 = r0.next();
        if (r5.containsKey(r1) == true) goto L6;
        this.zzu.zzk().zzO(r5, r1, r6.get(r1));
        goto L6
    L16:
        return;
    }

    public final void zzL(Bundle r2, int r3, String r4, Object r5) {
        if (zzaA(r2, r3) == false) goto L12;
        this.zzu.zzc();
        r2.putString("_ev", zzE(r4, 40, true));
        if (r5 == null) goto L13;
        Preconditions.checkNotNull(r2);
        if ((r5 instanceof String) == false) goto L9;
    L10:
        r2.putLong("_el", r5.toString().length());
        return;
    L9:
        if ((r5 instanceof CharSequence) == true) goto L10;
        return;
    L13:
        return;
    }

    public final int zzM(String r3, Object r4) {
        if ("_ldl".equals(r3) == false) goto L5;
        boolean r2 = zzA("user property referrer", r3, zzaC(r3), r4);
    L6:
        if (r2 == false) goto L9;
        return 0;
    L9:
        return 7;
    L5:
        r2 = zzA("user property", r3, zzaC(r3), r4);
        goto L6
    }

    public final Object zzN(String r8, Object r9) {
        if ("_ldl".equals(r8) == false) goto L7;
        return zzaB(zzaC(r8), r9, true, false, null);
    L7:
        return zzaB(zzaC(r8), r9, false, false, null);
    }

    public final void zzO(Bundle r3, String r4, Object r5) {
        if (r3 != null) goto L5;
        return;
    L5:
        if ((r5 instanceof Long) == false) goto L9;
        r3.putLong(r4, ((Long) r5).longValue());
        return;
    L9:
        if ((r5 instanceof String) == false) goto L13;
        r3.putString(r4, String.valueOf(r5));
        return;
    L13:
        if ((r5 instanceof Double) == false) goto L17;
        r3.putDouble(r4, ((Double) r5).doubleValue());
        return;
    L17:
        if ((r5 instanceof Bundle[]) == false) goto L20;
        r3.putParcelableArray(r4, (Bundle[]) r5);
        return;
    L20:
        if (r4 == null) goto L26;
        if (r5 == null) goto L23;
        String r32 = r5.getClass().getSimpleName();
    L24:
        zzic r2 = this.zzu;
        r2.zzaW().zzh().zzc("Not putting event parameter. Invalid value type. name, type", r2.zzl().zzb(r4), r32);
        return;
    L23:
        r32 = null;
        goto L24
    }

    public final void zzP(zzpo r2, String r3, int r4, String r5, String r6, int r7) {
        Bundle r1 = new Bundle();
        zzaA(r1, r4);
        if (TextUtils.isEmpty(r5) == true) goto L8;
        if (TextUtils.isEmpty(r6) == true) goto L8;
        r1.putString(r5, r6);
    L8:
        if (r4 != 6) goto L10;
    L13:
        r1.putLong("_el", r7);
    L14:
        r2.zza(r3, "_err", r1);
        return;
    L10:
        if (r4 == 7) goto L13;
        if (r4 != 2) goto L14;
        goto L13
    }

    public final boolean zzU() {
        zzg();
        if (zzX() != 1) goto L6;
        return true;
    L6:
        return false;
    }

    public final MeasurementManagerFutures zzV() {
        if (this.zzg != null) goto L6;
        this.zzg = MeasurementManagerFutures.from(this.zzu.zzaZ());
    L6:
        return this.zzg;
    }

    public final int zzW() {
        if (Build.VERSION.SDK_INT >= 30) goto L5;
        return 0;
    L5:
        if (SdkExtensions.getExtensionVersion(30) > 3) goto L7;
        return 0;
    L7:
        return SdkExtensions.getExtensionVersion(DurationKt.NANOS_IN_MILLIS);
    }

    public final long zzX() {
        zzg();
        if (zzZ(this.zzu.zzv().zzj()) == true) goto L6;
        return 0;
    L6:
        if (Build.VERSION.SDK_INT >= 30) goto L9;
        long r3 = 4;
    L16:
        if (zzab("android.permission.ACCESS_ADSERVICES_ATTRIBUTION") == true) goto L19;
        r3 = r3 | 2;
    L19:
        if (r3 != 0) goto L24;
        if (zzY() == true) goto L24;
        r3 = 64;
    L24:
        if (r3 != 0) goto L27;
        return 1;
    L27:
        return r3;
    L9:
        if (SdkExtensions.getExtensionVersion(30) >= 4) goto L11;
        r3 = 8;
        goto L16
    L11:
        zzic r0 = this.zzu;
        int r32 = zzW();
        r0.zzc();
        if (r32 >= ((Integer) zzfy.zzak.zzb(null)).intValue()) goto L14;
        r3 = 16;
        goto L16
    L14:
        r3 = 0;
        goto L16
    }

    @TargetApi(30)
    public final boolean zzY() {
        if (this.zzh != null) goto L35;
        MeasurementManagerFutures r0 = zzV();
        boolean r1 = false;
        if (r0 != null) goto L38;
        return false;
    L38:
        Integer r02 = r0.getMeasurementApiStatusAsync().get(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, TimeUnit.MILLISECONDS);     // Catch: TimeoutException -> L23 InterruptedException -> L25 ExecutionException -> L27 Throwable -> L29
        if (r02 != null) goto L36;
    L21:
        this.zzh = Boolean.valueOf(r1);     // Catch: TimeoutException -> L13 InterruptedException -> L15 ExecutionException -> L17 CancellationException -> L19
    L33:
        this.zzu.zzaW().zzk().zzb("Measurement manager api status result", r02);
        goto L35
    L15:
        Object e = e;
    L32:
        this.zzu.zzaW().zze().zzb("Measurement manager api exception", e);
        this.zzh = Boolean.FALSE;
    L19:
        e = e;
    L17:
        e = e;
    L13:
        e = e;
        goto L32
    L36:
        if (r02.intValue() != 1) goto L21;
        r1 = true;
    L29:
        e = move-exception;
        e = e;
        r02 = null;
    L35:
        return this.zzh.booleanValue();
    }

    public final boolean zzZ(String r2) {
        return zzaE((String) zzfy.zzaq.zzb(null), r2);
    }

    @Override // com.google.android.gms.measurement.internal.zzjf
    public final boolean zza() {
        return true;
    }

    public final boolean zzaa(String r2) {
        if (zzaE((String) zzfy.zzbg.zzb(null), r2) == false) goto L5;
        return true;
    L5:
        if (zzaE((String) zzfy.zzbh.zzb(null), r2) == false) goto L7;
        return true;
    L7:
        if (zzaE((String) zzfy.zzbi.zzb(null), r2) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public final boolean zzab(String r2) {
        zzg();
        zzic r1 = this.zzu;
        if (Wrappers.packageManager(r1.zzaZ()).checkCallingOrSelfPermission(r2) != 0) goto L6;
        return true;
    L6:
        r1.zzaW().zzj().zzb("Permission not granted", r2);
        return false;
    }

    public final boolean zzad(String r1, String r2) {
        if (TextUtils.isEmpty(r2) == true) goto L7;
        return true;
    L7:
        if (TextUtils.isEmpty(r1) == false) goto L10;
        return false;
    L10:
        zzic r0 = this.zzu;
        String r22 = r0.zzc().zzA();
        r0.zzaV();
        return r22.equals(r1);
    }

    public final Bundle zzae(Bundle r5, String r6) {
        Bundle r62 = new Bundle();
        if (r5 == null) goto L11;
        Iterator<String> r0 = r5.keySet().iterator();
    L6:
        if (r0.hasNext() == false) goto L11;
        String r1 = r0.next();
        Object r2 = zzG(r1, r5.get(r1));
        if (r2 == null) goto L9;
        zzO(r62, r1, r2);
        goto L6
    L9:
        zzic r22 = this.zzu;
        r22.zzaW().zzh().zzb("Param value can't be null", r22.zzl().zzb(r1));
    L11:
        return r62;
    }

    public final zzbh zzaf(String r9, String r10, Bundle r11, String r12, long r13, long r15, boolean r17, boolean r18) {
        if (TextUtils.isEmpty(r10) == false) goto L6;
        return null;
    L6:
        if (zzp(r10) != 0) goto L16;
        if (r11 == null) goto L10;
        Bundle r0 = new Bundle(r11);
    L9:
        Bundle r4 = r0;
        r4.putString("_o", r12);
        Bundle r112 = zzH(r9, r10, r4, CollectionUtils.listOf("_o"), true);
        if (r17 == false) goto L14;
        r112 = zzae(r112, r9);
    L14:
        Preconditions.checkNotNull(r112);
        return new zzbh(r10, new zzbf(r112), r12, r13, r15);
    L10:
        r0 = new Bundle();
        goto L9
    L16:
        zzic r8 = this.zzu;
        r8.zzaW().zzb().zzb("Invalid conditional property event name", r8.zzl().zzc(r10));
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public final boolean zzag(Context r3, String r4) {
        X500Principal r0 = new X500Principal("CN=Android Debug,O=Android,C=US");
        PackageInfo r32 = Wrappers.packageManager(r3).getPackageInfo(r4, 64);     // Catch: PackageManager.NameNotFoundException -> L11 CertificateException -> L13
        if (r32 == null) goto L21;
        Signature[] r33 = r32.signatures;     // Catch: PackageManager.NameNotFoundException -> L11 CertificateException -> L13
        if (r33 != null) goto L8;
        return true;
    L8:
        if (r33.length <= 0) goto L23;
        Signature r34 = r33[0];     // Catch: PackageManager.NameNotFoundException -> L11 CertificateException -> L13
        return ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(r34.toByteArray()))).getSubjectX500Principal().equals(r0);
    L23:
        return true;
    L21:
        return true;
    L11:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Package name not found", e);
        return true;
    L13:
        e = move-exception;
        this.zzu.zzaW().zzb().zzb("Error obtaining certificate", e);
        return true;
    }

    public final byte[] zzah(Parcelable r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        Parcel r1 = Parcel.obtain();
        r2.writeToParcel(r1, 0);     // Catch: Throwable -> L9
        byte[] r22 = r1.marshall();     // Catch: Throwable -> L9
        r1.recycle();
        return r22;
    L9:
        th = move-exception;
        r1.recycle();
        throw th;
    }

    public final boolean zzaj(int r2, boolean r3) {
        Boolean r32 = this.zzu.zzt().zzJ();
        if (zzak() < (r2 / 1000)) goto L5;
        return true;
    L5:
        if (r32 != null) goto L7;
    L9:
        return false;
    L7:
        if (r32.booleanValue() == true) goto L9;
        return true;
    }

    @EnsuresNonNull({"this.apkVersion"})
    public final int zzak() {
        if (this.zzi != null) goto L6;
        zzic r0 = this.zzu;
        this.zzi = Integer.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(r0.zzaZ()) / 1000);
    L6:
        return this.zzi.intValue();
    }

    public final int zzal(int r2) {
        zzic r1 = this.zzu;
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(r1.zzaZ(), 12451000);
    }

    public final long zzam(long r3, long r5) {
        return (r3 + (r5 * 60000)) / DurationKt.MILLIS_IN_DAY;
    }

    public final void zzan(Bundle r7, long r8) {
        long r1 = r7.getLong("_et");
        if (r1 == 0) goto L5;
        this.zzu.zzaW().zze().zzb("Params already contained engagement", Long.valueOf(r1));
    L6:
        r7.putLong("_et", r8 + r1);
        return;
    L5:
        r1 = 0;
        goto L6
    }

    public final void zzao(com.google.android.gms.internal.measurement.zzcs r3, String r4) {
        Bundle r0 = new Bundle();
        r0.putString("r", r4);
        r3.zzb(r0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error returning string value to wrapper", e);
    }

    public final void zzap(com.google.android.gms.internal.measurement.zzcs r3, long r4) {
        Bundle r0 = new Bundle();
        r0.putLong("r", r4);
        r3.zzb(r0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error returning long value to wrapper", e);
    }

    public final void zzaq(com.google.android.gms.internal.measurement.zzcs r3, int r4) {
        Bundle r0 = new Bundle();
        r0.putInt("r", r4);
        r3.zzb(r0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error returning int value to wrapper", e);
    }

    public final void zzar(com.google.android.gms.internal.measurement.zzcs r3, byte[] r4) {
        Bundle r0 = new Bundle();
        r0.putByteArray("r", r4);
        r3.zzb(r0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error returning byte array to wrapper", e);
    }

    public final void zzas(com.google.android.gms.internal.measurement.zzcs r3, boolean r4) {
        Bundle r0 = new Bundle();
        r0.putBoolean("r", r4);
        r3.zzb(r0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error returning boolean value to wrapper", e);
    }

    public final void zzat(com.google.android.gms.internal.measurement.zzcs r1, Bundle r2) {
        r1.zzb(r2);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error returning bundle value to wrapper", e);
    }

    public final void zzau(com.google.android.gms.internal.measurement.zzcs r3, ArrayList r4) {
        Bundle r0 = new Bundle();
        r0.putParcelableArrayList("r", r4);
        r3.zzb(r0);     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Error returning bundle list to wrapper", e);
    }

    public final URL zzaw(long r3, String r5, String r6, long r7, String r9) {
        Preconditions.checkNotEmpty(r6);     // Catch: IllegalArgumentException -> L5 MalformedURLException -> L7
        Preconditions.checkNotEmpty(r5);     // Catch: IllegalArgumentException -> L5 MalformedURLException -> L7
        String r32 = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", new Object[]{String.format("v%s.%s", new Object[]{161000L, Integer.valueOf(zzak())}), r6, r5, Long.valueOf(r7)});     // Catch: IllegalArgumentException -> L5 MalformedURLException -> L7
        if (r5.equals(this.zzu.zzc().zzB()) == false) goto L10;
        r32 = r32.concat("&ddl_test=1");     // Catch: IllegalArgumentException -> L5 MalformedURLException -> L7
    L10:
        if (r9.isEmpty() == false) goto L12;
    L15:
        return new URL(r32);
    L12:
        if (r9.charAt(0) == '&') goto L14;
        r32 = r32.concat("&");     // Catch: IllegalArgumentException -> L5 MalformedURLException -> L7
    L14:
        r32 = r32.concat(r9);     // Catch: IllegalArgumentException -> L5 MalformedURLException -> L7
    L5:
        e = e;
    L17:
        this.zzu.zzaW().zzb().zzb("Failed to create BOW URL for Deferred Deep Link. exception", e.getMessage());
        return null;
    L7:
        e = e;
        goto L17
    }

    public final String zzaz() {
        byte[] r0 = new byte[16];
        zzf().nextBytes(r0);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r0)});
    }

    @Override // com.google.android.gms.measurement.internal.zzjf
    public final void zzbb() {
        zzg();
        SecureRandom r0 = new SecureRandom();
        long r1 = r0.nextLong();
        if (r1 != 0) goto L7;
        r1 = r0.nextLong();
        if (r1 != 0) goto L7;
        this.zzu.zzaW().zze().zza("Utils falling back to Random for random id");
    L7:
        this.zze.set(r1);
    }

    public final long zzd() {
        AtomicLong r0 = this.zze;
        if (r0.get() != 0) goto L11;
        monitor-enter(r0);
        long r1 = new Random(System.nanoTime() ^ this.zzu.zzba().currentTimeMillis()).nextLong();     // Catch: Throwable -> L8
        int r3 = this.zzf + 1;     // Catch: Throwable -> L8
        this.zzf = r3;     // Catch: Throwable -> L8
        long r12 = r1 + r3;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r12;
    L8:
        th = move-exception;
        throw th;
    L11:
        AtomicLong r6 = this.zze;
        monitor-enter(r6);
        r6.compareAndSet(-1, 1);     // Catch: Throwable -> L17
        long r02 = r6.getAndIncrement();     // Catch: Throwable -> L17
        monitor-exit(r6);     // Catch: Throwable -> L17
        return r02;
    L17:
        th = move-exception;
        throw th;
    }

    @EnsuresNonNull({"this.secureRandom"})
    public final SecureRandom zzf() {
        zzg();
        if (this.zzd != null) goto L6;
        this.zzd = new SecureRandom();
    L6:
        return this.zzd;
    }

    public final Bundle zzi(Uri r20) {
        if (r20 == null) goto L106;
    L9:
        e = move-exception;
        this.zzu.zzaW().zze().zzb("Install referrer url isn't a hierarchical URI", e);
        return null;
    L6:
        if (r20.isHierarchical() == false) goto L11;
        String r3 = r20.getQueryParameter("utm_campaign");     // Catch: UnsupportedOperationException -> L9
        String r9 = r20.getQueryParameter("utm_source");     // Catch: UnsupportedOperationException -> L9
        String r10 = r20.getQueryParameter("utm_medium");     // Catch: UnsupportedOperationException -> L9
        String r11 = r20.getQueryParameter("gclid");     // Catch: UnsupportedOperationException -> L9
        String r12 = r20.getQueryParameter("gbraid");     // Catch: UnsupportedOperationException -> L9
        String r13 = r20.getQueryParameter("utm_id");     // Catch: UnsupportedOperationException -> L9
        String r14 = r20.getQueryParameter("dclid");     // Catch: UnsupportedOperationException -> L9
        String r15 = r20.getQueryParameter("srsltid");     // Catch: UnsupportedOperationException -> L9
        String r16 = r20.getQueryParameter("sfmc_id");     // Catch: UnsupportedOperationException -> L9
    L13:
        if (TextUtils.isEmpty(r3) == true) goto L15;
    L32:
        Bundle r2 = new Bundle();
        if (TextUtils.isEmpty(r3) == true) goto L36;
        r2.putString("campaign", r3);
    L36:
        if (TextUtils.isEmpty(r9) == true) goto L39;
        r2.putString("source", r9);
    L39:
        if (TextUtils.isEmpty(r10) == true) goto L42;
        r2.putString("medium", r10);
    L42:
        if (TextUtils.isEmpty(r11) == true) goto L45;
        r2.putString("gclid", r11);
    L45:
        if (TextUtils.isEmpty(r12) == true) goto L47;
        r2.putString("gbraid", r12);
    L47:
        String r32 = r20.getQueryParameter("gad_source");
        if (TextUtils.isEmpty(r32) == true) goto L50;
        r2.putString("gad_source", r32);
    L50:
        String r1 = r20.getQueryParameter("utm_term");
        if (TextUtils.isEmpty(r1) == true) goto L53;
        r2.putString("term", r1);
    L53:
        String r17 = r20.getQueryParameter("utm_content");
        if (TextUtils.isEmpty(r17) == true) goto L56;
        r2.putString("content", r17);
    L56:
        String r33 = r20.getQueryParameter("aclid");
        if (TextUtils.isEmpty(r33) == true) goto L59;
        r2.putString("aclid", r33);
    L59:
        String r34 = r20.getQueryParameter("cp1");
        if (TextUtils.isEmpty(r34) == true) goto L62;
        r2.putString("cp1", r34);
    L62:
        String r35 = r20.getQueryParameter("anid");
        if (TextUtils.isEmpty(r35) == true) goto L66;
        r2.putString("anid", r35);
    L66:
        if (TextUtils.isEmpty(r13) == true) goto L69;
        r2.putString("campaign_id", r13);
    L69:
        if (TextUtils.isEmpty(r14) == true) goto L71;
        r2.putString("dclid", r14);
    L71:
        String r18 = r20.getQueryParameter("utm_source_platform");
        if (TextUtils.isEmpty(r18) == true) goto L74;
        r2.putString("source_platform", r18);
    L74:
        String r19 = r20.getQueryParameter("utm_creative_format");
        if (TextUtils.isEmpty(r19) == true) goto L77;
        r2.putString("creative_format", r19);
    L77:
        String r110 = r20.getQueryParameter("utm_marketing_tactic");
        if (TextUtils.isEmpty(r110) == true) goto L81;
        r2.putString("marketing_tactic", r110);
    L81:
        if (TextUtils.isEmpty(r15) == true) goto L84;
        r2.putString("srsltid", r15);
    L84:
        if (TextUtils.isEmpty(r16) == true) goto L86;
        r2.putString("sfmc_id", r16);
    L86:
        Iterator<String> r111 = r20.getQueryParameterNames().iterator();
    L88:
        if (r111.hasNext() == false) goto L94;
        String r36 = r111.next();
        if (r36.startsWith("gad_") == false) goto L88;
        String r4 = r20.getQueryParameter(r36);
        if (TextUtils.isEmpty(r4) == true) goto L88;
        r2.putString(r36, r4);
        goto L88
    L94:
        zzic r112 = this.zzu;
        if (r112.zzc().zzp(null, zzfy.zzba) == false) goto L102;
        String r0 = new Uri.Builder().scheme(r20.getScheme()).authority(r20.getAuthority()).path(r20.getPath()).build().toString();
        int r113 = r112.zzc().zzf(null, false);
        if (r0.length() <= r113) goto L100;
        r0 = zzE(r0, r113 - 3, true);
    L100:
        if (TextUtils.isEmpty(r0) == true) goto L102;
        r2.putString("deep_link_url", r0);
    L102:
        return r2;
    L15:
        if (TextUtils.isEmpty(r9) == false) goto L32;
        if (TextUtils.isEmpty(r10) == false) goto L32;
        if (TextUtils.isEmpty(r11) == false) goto L32;
        if (TextUtils.isEmpty(r12) == false) goto L32;
        if (TextUtils.isEmpty(r13) == false) goto L32;
        if (TextUtils.isEmpty(r14) == false) goto L32;
        if (TextUtils.isEmpty(r15) == false) goto L32;
        if (TextUtils.isEmpty(r16) == false) goto L32;
        return null;
    L11:
        r3 = null;
        r9 = null;
        r10 = null;
        r11 = null;
        r12 = null;
        r13 = null;
        r14 = null;
        r15 = null;
        r16 = null;
        goto L13
    L106:
        return null;
    }

    public final boolean zzj(Intent r2) {
        String r1 = r2.getStringExtra("android.intent.extra.REFERRER_NAME");
        if ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(r1) == false) goto L5;
        return true;
    L5:
        if ("android-app://com.google.appcrawler".equals(r1) == false) goto L8;
        return true;
    L8:
        if (TextUtils.isEmpty(r1) == false) goto L18;
        return false;
    L18:
        String r12 = new URL(r1).getHost();     // Catch: MalformedURLException -> L15
        if (TextUtils.isEmpty(r12) == false) goto L13;
        return false;
    L13:
        return r12.matches("^(www\\.)?google(\\.com?)?(\\.[a-z]{2}t?)?$");
    L15:
        return false;
    }

    public final boolean zzk(String r6, String r7) {
        if (r7 != null) goto L7;
        this.zzu.zzaW().zzd().zzb("Name is required and can't be null. Type", r6);
        return false;
    L7:
        if (r7.length() != 0) goto L10;
        this.zzu.zzaW().zzd().zzb("Name is required and can't be empty. Type", r6);
        return false;
    L10:
        int r1 = r7.codePointAt(0);
        if (Character.isLetter(r1) == true) goto L14;
        this.zzu.zzaW().zzd().zzc("Name must start with a letter. Type, name", r6, r7);
        return false;
    L14:
        int r2 = r7.length();
        int r12 = Character.charCount(r1);
    L15:
        if (r12 >= r2) goto L23;
        int r3 = r7.codePointAt(r12);
        if (r3 == 95) goto L22;
        if (Character.isLetterOrDigit(r3) == true) goto L22;
        this.zzu.zzaW().zzd().zzc("Name must consist of letters, digits or _ (underscores). Type, name", r6, r7);
        return false;
    L22:
        r12 = r12 + Character.charCount(r3);
        goto L15
    L23:
        return true;
    }

    public final boolean zzl(String r7, String r8) {
        if (r8 != null) goto L7;
        this.zzu.zzaW().zzd().zzb("Name is required and can't be null. Type", r7);
        return false;
    L7:
        if (r8.length() != 0) goto L10;
        this.zzu.zzaW().zzd().zzb("Name is required and can't be empty. Type", r7);
        return false;
    L10:
        int r1 = r8.codePointAt(0);
        if (Character.isLetter(r1) == true) goto L16;
        if (r1 != 95) goto L14;
        r1 = 95;
        goto L16
    L14:
        this.zzu.zzaW().zzd().zzc("Name must start with a letter or _ (underscore). Type, name", r7, r8);
        return false;
    L16:
        int r2 = r8.length();
        int r12 = Character.charCount(r1);
    L17:
        if (r12 >= r2) goto L25;
        int r4 = r8.codePointAt(r12);
        if (r4 == 95) goto L24;
        if (Character.isLetterOrDigit(r4) == true) goto L24;
        this.zzu.zzaW().zzd().zzc("Name must consist of letters, digits or _ (underscores). Type, name", r7, r8);
        return false;
    L24:
        r12 = r12 + Character.charCount(r4);
        goto L17
    L25:
        return true;
    }

    public final boolean zzm(String r5, String[] r6, String[] r7, String r8) {
        if (r8 != null) goto L6;
        this.zzu.zzaW().zzd().zzb("Name is required and can't be null. Type", r5);
        return false;
    L6:
        Preconditions.checkNotNull(r8);
        String[] r1 = zzb;
        int r2 = 0;
    L8:
        if (r2 >= 3) goto L14;
        if (r8.startsWith(r1[r2]) == true) goto L11;
        r2 = r2 + 1;
        goto L8
    L11:
        this.zzu.zzaW().zzd().zzc("Name starts with reserved prefix. Type, name", r5, r8);
        return false;
    L14:
        if (r6 != null) goto L16;
        return true;
    L16:
        if (zzaD(r8, r6) == false) goto L26;
        if (r7 != null) goto L19;
    L20:
        this.zzu.zzaW().zzd().zzc("Name is reserved. Type, name", r5, r8);
        return false;
    L19:
        if (zzaD(r8, r7) == false) goto L20;
        return true;
    L26:
        return true;
    }

    public final boolean zzn(String r3, int r4, String r5) {
        if (r5 != null) goto L7;
        this.zzu.zzaW().zzd().zzb("Name is required and can't be null. Type", r3);
        return false;
    L7:
        if (r5.codePointCount(0, r5.length()) <= r4) goto L10;
        this.zzu.zzaW().zzd().zzd("Name is too long. Type, maximum supported length, name", r3, Integer.valueOf(r4), r5);
        return false;
    L10:
        return true;
    }

    public final int zzp(String r8) {
        if (zzl("event", r8) == true) goto L5;
        return 2;
    L5:
        zzic r1 = this.zzu;
        String[] r3 = zzjm.zza;
        if (r1.zzc().zzp(null, zzfy.zzbf) == false) goto L8;
        String[] r4 = zzjm.zzc;
    L10:
        if (zzm("event", r3, r4, r8) == true) goto L13;
        return 13;
    L13:
        r1.zzc();
        if (zzn("event", 40, r8) == true) goto L16;
        return 2;
    L16:
        return 0;
    L8:
        r4 = zzjm.zzb;
        goto L10
    }

    public final boolean zzq(String r3) {
        if (this.zzu.zzc().zzp(null, zzfy.zzbf) == false) goto L7;
        return zzaD(r3, zzjm.zze);
    L7:
        return zzaD(r3, zzjm.zzd);
    }

    public final int zzs(String r5) {
        if (zzl("user property", r5) == true) goto L6;
        return 6;
    L6:
        if (zzm("user property", zzjo.zza, null, r5) == true) goto L9;
        return 15;
    L9:
        this.zzu.zzc();
        if (zzn("user property", 24, r5) == true) goto L12;
        return 6;
    L12:
        return 0;
    }

    public final int zzt(String r4) {
        if (zzk("event param", r4) == true) goto L6;
        return 3;
    L6:
        if (zzm("event param", null, null, r4) == true) goto L9;
        return 14;
    L9:
        this.zzu.zzc();
        if (zzn("event param", 40, r4) == true) goto L12;
        return 3;
    L12:
        return 0;
    }

    public final int zzu(String r4) {
        if (zzl("event param", r4) == true) goto L6;
        return 3;
    L6:
        if (zzm("event param", null, null, r4) == true) goto L9;
        return 14;
    L9:
        this.zzu.zzc();
        if (zzn("event param", 40, r4) == true) goto L12;
        return 3;
    L12:
        return 0;
    }

    public final boolean zzz(Object r1) {
        if ((r1 instanceof Parcelable[]) == false) goto L5;
        return true;
    L5:
        if ((r1 instanceof ArrayList) == false) goto L7;
        return true;
    L7:
        if ((r1 instanceof Bundle) == true) goto L14;
        return false;
    L14:
        return true;
    }
}
