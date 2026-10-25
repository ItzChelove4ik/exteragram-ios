package com.google.android.gms.dynamite;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public final class DynamiteModule {
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = null;
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = null;
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = null;
    public static final VersionPolicy PREFER_LOCAL = null;
    public static final VersionPolicy PREFER_REMOTE = null;
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = null;
    public static final VersionPolicy zza = null;
    public static final VersionPolicy zzb = null;
    private static Boolean zzc = null;
    private static String zzd = null;
    private static boolean zze = false;
    private static int zzf = -1;
    private static Boolean zzg;
    private static final ThreadLocal zzh = null;
    private static final ThreadLocal zzi = null;
    private static final VersionPolicy.IVersions zzj = null;
    private static zzp zzl;
    private static zzq zzm;
    private final Context zzk;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;

        public DynamiteLoaderClassLoader() {
        }
    }

    public static class LoadingException extends Exception {
        public /* synthetic */ LoadingException(String r1, Throwable r2, byte[] r3) {
            super(r1, r2);
        }

        public /* synthetic */ LoadingException(String r1, byte[] r2) {
            super(r1);
        }
    }

    public interface VersionPolicy {

        public interface IVersions {
            int zza(Context r1, String r2, boolean r3);

            int zzb(Context r1, String r2);
        }

        public static class SelectionResult {
            public int localVersion;
            public int remoteVersion;
            public int selection;

            public SelectionResult() {
                this.localVersion = 0;
                this.remoteVersion = 0;
                this.selection = 0;
            }
        }

        SelectionResult selectModule(Context r1, String r2, IVersions r3);
    }

    static {
        zzh = new ThreadLocal();
        zzi = new zze();
        zzj = new zzf();
        PREFER_REMOTE = new zzg();
        PREFER_LOCAL = new zzh();
        PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzi();
        PREFER_HIGHEST_OR_LOCAL_VERSION = new zzj();
        PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzk();
        PREFER_HIGHEST_OR_REMOTE_VERSION = new zzl();
        zza = new zzm();
        zzb = new zzc();
    }

    private DynamiteModule(Context r1) {
        Preconditions.checkNotNull(r1);
        this.zzk = r1;
    }

    public static int getLocalVersion(Context r9, String r10) {
        ClassLoader r92 = r9.getApplicationContext().getClassLoader();     // Catch: Exception -> L7 ClassNotFoundException -> L12
        StringBuilder r8 = new StringBuilder(String.valueOf(r10).length() + 61);     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r8.append("com.google.android.gms.dynamite.descriptors.");     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r8.append(r10);     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r8.append(".ModuleDescriptor");     // Catch: Exception -> L7 ClassNotFoundException -> L12
        Class<?> r93 = r92.loadClass(r8.toString());     // Catch: Exception -> L7 ClassNotFoundException -> L12
        Field r4 = r93.getDeclaredField("MODULE_ID");     // Catch: Exception -> L7 ClassNotFoundException -> L12
        Field r94 = r93.getDeclaredField("MODULE_VERSION");     // Catch: Exception -> L7 ClassNotFoundException -> L12
        if (Objects.equal(r4.get(null), r10) == true) goto L10;
        String r95 = String.valueOf(r4.get(null));     // Catch: Exception -> L7 ClassNotFoundException -> L12
        StringBuilder r5 = new StringBuilder(((r95.length() + 50) + String.valueOf(r10).length()) + 1);     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r5.append("Module descriptor id '");     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r5.append(r95);     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r5.append("' didn't match expected id '");     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r5.append(r10);     // Catch: Exception -> L7 ClassNotFoundException -> L12
        r5.append("'");     // Catch: Exception -> L7 ClassNotFoundException -> L12
        Log.e("DynamiteModule", r5.toString());     // Catch: Exception -> L7 ClassNotFoundException -> L12
        return 0;
    L10:
        return r94.getInt(null);
    L12:
        StringBuilder r1 = new StringBuilder(String.valueOf(r10).length() + 45);
        r1.append("Local module descriptor class for ");
        r1.append(r10);
        r1.append(" not found.");
        Log.w("DynamiteModule", r1.toString());
    L13:
        return 0;
    L7:
        e = move-exception;
        Log.e("DynamiteModule", "Failed to load module descriptor class: ".concat(String.valueOf(e.getMessage())));
        goto L13
    }

    public static int getRemoteVersion(Context r1, String r2) {
        return zza(r1, r2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    public static DynamiteModule load(Context r30, VersionPolicy r31, String r32) {
        ?? r4 = ":";
        Context r12 = r30.getApplicationContext();
        if (r12 == null) goto L148;
        ThreadLocal r14 = zzh;
        zzn r15 = (zzn) r14.get();
        zzn r7 = new zzn(null);
        r14.set(r7);
        ThreadLocal r13 = zzi;
        Long r8 = (Long) r13.get();
        long r20 = r8.longValue();
        r13.set(Long.valueOf(SystemClock.uptimeMillis()));     // Catch: Throwable -> L13
        VersionPolicy.SelectionResult r9 = r31.selectModule(r30, r32, zzj);     // Catch: Throwable -> L13
        int r6 = r9.localVersion;     // Catch: Throwable -> L13
        int r2 = r9.remoteVersion;     // Catch: Throwable -> L13
        StringBuilder r1 = new StringBuilder((((((String.valueOf(r32).length() + 26) + String.valueOf(r6).length()) + 19) + String.valueOf(r32).length()) + 1) + String.valueOf(r2).length());     // Catch: Throwable -> L13
        r1.append("Considering local module ");     // Catch: Throwable -> L13
        r1.append(r32);     // Catch: Throwable -> L13
        r1.append(":");     // Catch: Throwable -> L13
        r1.append(r6);     // Catch: Throwable -> L13
        r1.append(" and remote module ");     // Catch: Throwable -> L13
        r1.append(r32);     // Catch: Throwable -> L13
        r1.append(":");     // Catch: Throwable -> L13
        r1.append(r2);     // Catch: Throwable -> L13
        Log.i("DynamiteModule", r1.toString());     // Catch: Throwable -> L13
        int r0 = r9.selection;     // Catch: Throwable -> L13
        if (r0 != 0) goto L8;
    L136:
        int r16 = r9.localVersion;     // Catch: Throwable -> L13
        int r22 = r9.remoteVersion;     // Catch: Throwable -> L13
        StringBuilder r5 = new StringBuilder(((((String.valueOf(r32).length() + 46) + String.valueOf(r16).length()) + 23) + String.valueOf(r22).length()) + 1);     // Catch: Throwable -> L13
        r5.append("No acceptable module ");     // Catch: Throwable -> L13
        r5.append(r32);     // Catch: Throwable -> L13
        r5.append(" found. Local version is ");     // Catch: Throwable -> L13
        r5.append(r16);     // Catch: Throwable -> L13
        r5.append(" and remote version is ");     // Catch: Throwable -> L13
        r5.append(r22);     // Catch: Throwable -> L13
        r5.append(".");     // Catch: Throwable -> L13
        throw new LoadingException(r5.toString(), null);     // Catch: Throwable -> L13
    L8:
        if (r0 != (-1)) goto L15;
        if (r9.localVersion == 0) goto L136;
        r0 = -1;
    L15:
        if (r0 == 1) goto L17;
    L18:
        if (r0 != (-1)) goto L30;
        DynamiteModule r02 = zze(r12, r32);     // Catch: Throwable -> L13
        if (r20 != 0) goto L23;
        r13.remove();
    L24:
        Cursor r17 = r7.zza;
        if (r17 == null) goto L27;
        r17.close();
    L27:
        r14.set(r15);
        return r02;
    L23:
        r13.set(r8);
        goto L24
    L30:
        if (r0 == 1) goto L153;
        StringBuilder r3 = new StringBuilder(String.valueOf(r0).length() + 36);     // Catch: Throwable -> L13
        r3.append("VersionPolicy returned invalid code:");     // Catch: Throwable -> L13
        r3.append(r0);     // Catch: Throwable -> L13
        throw new LoadingException(r3.toString(), null);     // Catch: Throwable -> L13
    L153:
        int r03 = r9.remoteVersion;     // Catch: Throwable -> L13 LoadingException -> L116
        monitor-enter(DynamiteModule.class);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L103:
        th = move-exception;
        throw th;     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L36:
        if (zzb(r30) == false) goto L106;
        Boolean r52 = zzc;     // Catch: Throwable -> L103
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L103
        if (r52 == null) goto L102;
        if (r52.booleanValue() == false) goto L82;
        StringBuilder r132 = new StringBuilder((String.valueOf(r32).length() + 40) + String.valueOf(r03).length());     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r132.append("Selected remote version of ");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r132.append(r32);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r132.append(", version >= ");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r132.append(r03);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        Log.i("DynamiteModule", r132.toString());     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        monitor-enter(DynamiteModule.class);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        zzq r62 = zzm;     // Catch: Throwable -> L79
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L79
        if (r62 == null) goto L78;
        zzn r42 = (zzn) r14.get();     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        if (r42 == null) goto L76;
        if (r42.zza == null) goto L76;
        Context r10 = r30.getApplicationContext();     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        Cursor r43 = r42.zza;     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        ObjectWrapper.wrap(null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        monitor-enter(DynamiteModule.class);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L72:
        th = move-exception;
        throw th;     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L54:
        if (zzf < 2) goto L56;
        boolean r29 = true;
    L57:
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L72
        if (r29 == false) goto L66;
        Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        IObjectWrapper r04 = r62.zzf(ObjectWrapper.wrap(r10), r32, r03, ObjectWrapper.wrap(r43));     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L67:
        Context r05 = (Context) ObjectWrapper.unwrap(r04);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        if (r05 == null) goto L71;
        DynamiteModule r44 = new DynamiteModule(r05);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L124:
        if (r20 != 0) goto L126;
        zzi.remove();
    L127:
        Cursor r06 = r7.zza;
        if (r06 == null) goto L130;
        r06.close();
    L130:
        zzh.set(r15);
        return r44;
    L126:
        zzi.set(r8);
        goto L127
    L71:
        throw new LoadingException("Failed to get module context", null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L66:
        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r04 = r62.zze(ObjectWrapper.wrap(r10), r32, r03, ObjectWrapper.wrap(r43));     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        goto L67
    L56:
        r29 = false;
    L76:
        throw new LoadingException("No result cursor", null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L78:
        throw new LoadingException("DynamiteLoaderV2 was not cached.", null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L79:
        th = move-exception;
        throw th;     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L82:
        StringBuilder r133 = new StringBuilder((String.valueOf(r32).length() + 40) + String.valueOf(r03).length());     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r133.append("Selected remote version of ");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r133.append(r32);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r133.append(", version >= ");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r133.append(r03);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        Log.i("DynamiteModule", r133.toString());     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        zzp r45 = zzg(r30);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        if (r45 == null) goto L100;
        int r63 = r45.zzi();     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        if (r63 < 3) goto L91;
        zzn r53 = (zzn) r14.get();     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        if (r53 == null) goto L90;
        IObjectWrapper r07 = r45.zzk(ObjectWrapper.wrap(r30), r32, r03, ObjectWrapper.wrap(r53.zza));     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L94:
        Object r08 = ObjectWrapper.unwrap(r07);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        if (r08 == null) goto L98;
        r44 = new DynamiteModule((Context) r08);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        goto L124
    L98:
        throw new LoadingException("Failed to load remote module.", null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L90:
        throw new LoadingException("No cached result cursor holder", null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L91:
        if (r63 != 2) goto L93;
        Log.w("DynamiteModule", "IDynamite loader version = 2");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r07 = r45.zzg(ObjectWrapper.wrap(r30), r32, r03);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        goto L94
    L93:
        Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        r07 = r45.zze(ObjectWrapper.wrap(r30), r32, r03);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
        goto L94
    L100:
        throw new LoadingException("Failed to create IDynamiteLoader.", null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L102:
        throw new LoadingException("Failed to determine which loading route to use.", null);     // Catch: Throwable -> L60 LoadingException -> L62 RemoteException -> L64
    L106:
        throw new LoadingException("Remote loading disabled", null);     // Catch: Throwable -> L103
    L64:
        e = move-exception;
        throw new LoadingException("Failed to load remote module.", e, null);     // Catch: Throwable -> L13 LoadingException -> L111
    L62:
        e = move-exception;
        throw e;     // Catch: Throwable -> L13 LoadingException -> L111
    L60:
        th = move-exception;
        CrashUtils.addDynamiteErrorToDropBox(r30, th);     // Catch: Throwable -> L13 LoadingException -> L111
        throw new LoadingException("Failed to load remote module.", th, null);     // Catch: Throwable -> L13 LoadingException -> L111
    L116:
        e = e;
        Context r46 = r30;
    L118:
        String r64 = e.getMessage();     // Catch: Throwable -> L13
        StringBuilder r11 = new StringBuilder(String.valueOf(r64).length() + 30);     // Catch: Throwable -> L13
        r11.append("Failed to load remote module: ");     // Catch: Throwable -> L13
        r11.append(r64);     // Catch: Throwable -> L13
        Log.w("DynamiteModule", r11.toString());     // Catch: Throwable -> L13
        int r54 = r9.localVersion;     // Catch: Throwable -> L13
        if (r54 == 0) goto L133;
        if (r31.selectModule(r46, r32, new zzo(r54, 0)).selection != (-1)) goto L133;
        r44 = zze(r12, r32);     // Catch: Throwable -> L13
    L133:
        throw new LoadingException("Remote load failed. No local fallback found.", e, null);     // Catch: Throwable -> L13
    L111:
        e = e;
        r46 = r4;
        goto L118
    L17:
        if (r9.remoteVersion == 0) goto L136;
    L13:
        th = move-exception;
        if (r20 != 0) goto L141;
        zzi.remove();
    L142:
        Cursor r18 = r7.zza;
        if (r18 == null) goto L145;
        r18.close();
    L145:
        zzh.set(r15);
        throw th;
    L141:
        zzi.set(r8);
        goto L142
    L148:
        throw new LoadingException("null application Context", null);
    }

    public static int zza(Context r12, String r13, boolean r14) {
        monitor-enter(DynamiteModule.class);     // Catch: Throwable -> L69
        Boolean r0 = zzc;     // Catch: Throwable -> L24
        Cursor r4 = null;
        int r5 = 0;
        if (r0 == null) goto L146;
    L64:
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L24
        if (r0.booleanValue() == true) goto L136;
        zzp r6 = zzg(r12);     // Catch: Throwable -> L69
        if (r6 != null) goto L133;
    L121:
        return r5;
    L133:
        int r02 = r6.zzi();     // Catch: RemoteException -> L85 Throwable -> L122
        if (r02 < 3) goto L112;
        zzn r03 = (zzn) zzh.get();     // Catch: RemoteException -> L85 Throwable -> L122
        if (r03 == null) goto L87;
        Cursor r04 = r03.zza;     // Catch: RemoteException -> L85 Throwable -> L122
        if (r04 == null) goto L87;
        r5 = r04.getInt(0);     // Catch: RemoteException -> L85 Throwable -> L122
    L132:
    L87:
        Cursor r132 = (Cursor) ObjectWrapper.unwrap(r6.zzj(ObjectWrapper.wrap(r12), r13, r14, ((Long) zzi.get()).longValue()));     // Catch: RemoteException -> L85 Throwable -> L122
        if (r132 != null) goto L145;
    L105:
        Log.w("DynamiteModule", "Failed to retrieve remote module version.");     // Catch: Throwable -> L97 RemoteException -> L99
        if (r132 == null) goto L132;
        r132.close();     // Catch: Throwable -> L69
        goto L132
    L145:
    L99:
        e = move-exception;
        RemoteException r142 = e;
        r4 = r132;
    L118:
        String r143 = r142.getMessage();     // Catch: Throwable -> L122
        StringBuilder r2 = new StringBuilder(String.valueOf(r143).length() + 42);     // Catch: Throwable -> L122
        r2.append("Failed to retrieve remote module version: ");     // Catch: Throwable -> L122
        r2.append(r143);     // Catch: Throwable -> L122
        Log.w("DynamiteModule", r2.toString());     // Catch: Throwable -> L122
        if (r4 == null) goto L121;
        r4.close();     // Catch: Throwable -> L69
    L97:
        th = move-exception;
        Throwable r144 = th;
        r4 = r132;
    L124:
        if (r4 == null) goto L148;
        r4.close();     // Catch: Throwable -> L69
        throw r144;     // Catch: Throwable -> L69
    L148:
        throw r144;     // Catch: Throwable -> L69
    L90:
        if (r132.moveToFirst() == false) goto L105;
        int r145 = r132.getInt(0);     // Catch: Throwable -> L97 RemoteException -> L99
        if (r145 > 0) goto L95;
    L101:
        r4 = r132;
    L102:
        if (r4 == null) goto L104;
        r4.close();     // Catch: Throwable -> L69
    L104:
        r5 = r145;
        goto L132
    L95:
        if (zzd(r132) == false) goto L101;
    L112:
        if (r02 != 2) goto L114;
        Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");     // Catch: RemoteException -> L85 Throwable -> L122
        r5 = r6.zzh(ObjectWrapper.wrap(r12), r13, r14);     // Catch: RemoteException -> L85 Throwable -> L122
        goto L132
    L114:
        Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");     // Catch: RemoteException -> L85 Throwable -> L122
        r5 = r6.zzf(ObjectWrapper.wrap(r12), r13, r14);     // Catch: RemoteException -> L85 Throwable -> L122
    L85:
        e = move-exception;
        r142 = e;
    L122:
        th = move-exception;
        r144 = th;
        goto L124
    L136:
        return zzc(r12, r13, r14, false);
    L71:
        e = move-exception;
        String r133 = e.getMessage();     // Catch: Throwable -> L69
        StringBuilder r22 = new StringBuilder(String.valueOf(r133).length() + 42);     // Catch: Throwable -> L69
        r22.append("Failed to retrieve remote module version: ");     // Catch: Throwable -> L69
        r22.append(r133);     // Catch: Throwable -> L69
        Log.w("DynamiteModule", r22.toString());     // Catch: Throwable -> L69
        return 0;
    L146:
        Field r05 = r12.getApplicationContext().getClassLoader().loadClass(DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");     // Catch: Throwable -> L24 Throwable -> L57 IllegalAccessException -> L59 ClassNotFoundException -> L61
        Class<?> r62 = r05.getDeclaringClass();     // Catch: Throwable -> L24 Throwable -> L57 IllegalAccessException -> L59 ClassNotFoundException -> L61
        monitor-enter(r62);     // Catch: Throwable -> L24 Throwable -> L57 IllegalAccessException -> L59 ClassNotFoundException -> L61
        ClassLoader r7 = (ClassLoader) r05.get(null);     // Catch: Throwable -> L14
        if (r7 != ClassLoader.getSystemClassLoader()) goto L16;
        r0 = Boolean.FALSE;     // Catch: Throwable -> L14
    L53:
        monitor-exit(r62);     // Catch: Throwable -> L14
    L63:
        zzc = r0;     // Catch: Throwable -> L24
        goto L64
    L16:
        if (r7 == null) goto L20;
        zzf(r7);     // Catch: Throwable -> L14 LoadingException -> L131
    L18:
        r0 = Boolean.TRUE;     // Catch: Throwable -> L14
        goto L53
    L20:
        if (zzb(r12) == true) goto L27;
        monitor-exit(r62);     // Catch: Throwable -> L14
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L24
        return 0;
    L27:
        if (zze == true) goto L52;
        Boolean r72 = Boolean.TRUE;     // Catch: Throwable -> L14
        if (r72.equals(null) == true) goto L52;
        int r8 = zzc(r12, r13, r14, true);     // Catch: Throwable -> L14 LoadingException -> L51
        String r9 = zzd;     // Catch: Throwable -> L14 LoadingException -> L51
        if (r9 != null) goto L35;
    L48:
        monitor-exit(r62);     // Catch: Throwable -> L14
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L24
        return r8;
    L35:
        if (r9.isEmpty() == true) goto L48;
        ClassLoader r92 = zzb.zza();     // Catch: Throwable -> L14 LoadingException -> L51
        if (r92 == null) goto L41;
    L44:
        zzf(r92);     // Catch: Throwable -> L14 LoadingException -> L51
        r05.set(null, r92);     // Catch: Throwable -> L14 LoadingException -> L51
        zzc = r72;     // Catch: Throwable -> L14 LoadingException -> L51
        monitor-exit(r62);     // Catch: Throwable -> L14
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L24
        return r8;
    L41:
        if (Build.VERSION.SDK_INT < 29) goto L43;
        DynamiteModule$$ExternalSyntheticApiModelOutline1.m();     // Catch: Throwable -> L14 LoadingException -> L51
        String r93 = zzd;     // Catch: Throwable -> L14 LoadingException -> L51
        Preconditions.checkNotNull(r93);     // Catch: Throwable -> L14 LoadingException -> L51
        r92 = DynamiteModule$$ExternalSyntheticApiModelOutline0.m(r93, ClassLoader.getSystemClassLoader());     // Catch: Throwable -> L14 LoadingException -> L51
        goto L44
    L43:
        String r10 = zzd;     // Catch: Throwable -> L14 LoadingException -> L51
        Preconditions.checkNotNull(r10);     // Catch: Throwable -> L14 LoadingException -> L51
        r92 = new zzd(r10, ClassLoader.getSystemClassLoader());     // Catch: Throwable -> L14 LoadingException -> L51
    L51:
        r05.set(null, ClassLoader.getSystemClassLoader());     // Catch: Throwable -> L14
        r0 = Boolean.FALSE;     // Catch: Throwable -> L14
    L52:
        r05.set(null, ClassLoader.getSystemClassLoader());     // Catch: Throwable -> L14
        r0 = Boolean.FALSE;     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;     // Catch: Throwable -> L24 Throwable -> L57 IllegalAccessException -> L59 ClassNotFoundException -> L61
    L57:
        e = move-exception;
        String r06 = e.toString();     // Catch: Throwable -> L24
        StringBuilder r82 = new StringBuilder(r06.length() + 30);     // Catch: Throwable -> L24
        r82.append("Failed to load module via V2: ");     // Catch: Throwable -> L24
        r82.append(r06);     // Catch: Throwable -> L24
        Log.w("DynamiteModule", r82.toString());     // Catch: Throwable -> L24
        r0 = Boolean.FALSE;     // Catch: Throwable -> L24
    L24:
        th = move-exception;
        throw th;     // Catch: Throwable -> L69
    L69:
        th = move-exception;
        CrashUtils.addDynamiteErrorToDropBox(r12, th);
        throw th;
    }

    private static boolean zzb(Context r6) {
        Boolean r0 = Boolean.TRUE;
        if (r0.equals(null) == false) goto L6;
        return true;
    L6:
        if (r0.equals(zzg) == false) goto L8;
        return true;
    L8:
        boolean r3 = false;
        if (zzg != null) goto L27;
        PackageManager r02 = r6.getPackageManager();
        if (true == PlatformVersion.isAtLeastQ()) goto L13;
        int r4 = 0;
    L14:
        ProviderInfo r03 = r02.resolveContentProvider("com.google.android.gms.chimera", r4);
        if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(r6, 10000000) != 0) goto L20;
        if (r03 == null) goto L20;
        if ("com.google.android.gms".equals(r03.packageName) == false) goto L20;
        r3 = true;
    L20:
        zzg = Boolean.valueOf(r3);
        if (r3 == false) goto L27;
        ApplicationInfo r62 = r03.applicationInfo;
        if (r62 == null) goto L27;
        if ((r62.flags & 129) != 0) goto L27;
        Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
        zze = true;
        goto L27
    L13:
        r4 = 268435456;
    L27:
        if (r3 == true) goto L29;
        Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
    L29:
        return r3;
    }

    /* JADX WARN: Not initialized variable reg: 8, insn: 0x0149: MOVE (r2 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:92:0x0149 */
    private static int zzc(Context r14, String r15, boolean r16, boolean r17) {
        Cursor r2 = null;
        long r3 = ((Long) zzi.get()).longValue();     // Catch: Throwable -> L15 Exception -> L17
        String r0 = "api_force_staging";
        boolean r6 = true;
        if (true == r16) goto L6;
        r0 = "api";
    L6:
        Uri r8 = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").path(r0).appendPath(r15).appendQueryParameter("requestStartUptime", String.valueOf(r3)).build();     // Catch: Throwable -> L15 Exception -> L17
        ContentProviderClient r7 = r14.getContentResolver().acquireUnstableContentProviderClient(r8);     // Catch: Throwable -> L15 Exception -> L17
        boolean r32 = false;
        if (r7 != null) goto L115;
    L9:
        MatrixCursor r82 = null;
    L58:
        if (r82 != null) goto L113;
    L96:
        Log.w("DynamiteModule", "Failed to retrieve remote module version.");     // Catch: Throwable -> L88 Exception -> L90
        throw new LoadingException("Failed to connect to dynamite module ContentResolver.", null);     // Catch: Throwable -> L88 Exception -> L90
    L113:
    L90:
        e = move-exception;
        Exception r142 = e;
    L100:
        if ((r142 instanceof LoadingException) == true) goto L103;
        String r33 = r142.getMessage();     // Catch: Throwable -> L88
        StringBuilder r5 = new StringBuilder(String.valueOf(r33).length() + 25);     // Catch: Throwable -> L88
        r5.append("V2 version check failed: ");     // Catch: Throwable -> L88
        r5.append(r33);     // Catch: Throwable -> L88
        throw new LoadingException(r5.toString(), r142, null);     // Catch: Throwable -> L88
    L103:
        throw r142;     // Catch: Throwable -> L88
    L60:
        if (r82.moveToFirst() == false) goto L96;
        int r02 = r82.getInt(0);     // Catch: Throwable -> L88 Exception -> L90
        if (r02 > 0) goto L64;
    L83:
        if (r17 == false) goto L93;
        if (r32 == false) goto L93;
        throw new LoadingException("forcing fallback to container DynamiteLoader impl", null);     // Catch: Throwable -> L88 Exception -> L90
    L93:
        if (r82 == null) goto L95;
        r82.close();
    L95:
        return r02;
    L64:
        monitor-enter(DynamiteModule.class);     // Catch: Throwable -> L88 Exception -> L90
        zzd = r82.getString(2);     // Catch: Throwable -> L68
        int r143 = r82.getColumnIndex("loaderVersion");     // Catch: Throwable -> L68
        if (r143 < 0) goto L70;
        zzf = r82.getInt(r143);     // Catch: Throwable -> L68
    L70:
        int r144 = r82.getColumnIndex("disableStandaloneDynamiteLoader2");     // Catch: Throwable -> L68
        if (r144 >= 0) goto L73;
    L77:
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L68
        if (zzd(r82) == false) goto L83;
        r82 = null;
        goto L83
    L73:
        if (r82.getInt(r144) != 0) goto L76;
        r6 = false;
    L76:
        zze = r6;     // Catch: Throwable -> L68
        r32 = r6;
    L68:
        th = move-exception;
        throw th;     // Catch: Throwable -> L88 Exception -> L90
    L115:
        Cursor r4 = r7.query(r8, null, null, null, null);     // Catch: RemoteException -> L107 Throwable -> L49
        if (r4 == null) goto L13;
        int r03 = r4.getCount();     // Catch: Throwable -> L34
        int r52 = r4.getColumnCount();     // Catch: Throwable -> L34
        r82 = new MatrixCursor(r4.getColumnNames(), r03);     // Catch: Throwable -> L34
        int r9 = 0;
    L20:
        if (r9 >= r03) goto L46;
        if (r4.moveToPosition(r9) == false) goto L45;
        Object[] r10 = new Object[r52];     // Catch: Throwable -> L34
        int r11 = 0;
    L24:
        if (r11 >= r52) goto L43;
        int r12 = r4.getType(r11);     // Catch: Throwable -> L34
        if (r12 == 0) goto L41;
        if (r12 == 1) goto L40;
        if (r12 != 2) goto L30;
        r10[r11] = Double.valueOf(r4.getDouble(r11));     // Catch: Throwable -> L34
    L42:
        r11 = r11 + 1;     // Catch: Throwable -> L34
        goto L24
    L30:
        if (r12 != 3) goto L32;
        r10[r11] = r4.getString(r11);     // Catch: Throwable -> L34
        goto L42
    L32:
        if (r12 != 4) goto L37;
        r10[r11] = r4.getBlob(r11);     // Catch: Throwable -> L34
        goto L42
    L37:
        throw new RemoteException("Unknown column type");     // Catch: Throwable -> L34
    L40:
        r10[r11] = Long.valueOf(r4.getLong(r11));     // Catch: Throwable -> L34
        goto L42
    L41:
        r10[r11] = null;     // Catch: Throwable -> L34
        goto L42
    L43:
        r82.addRow(r10);     // Catch: Throwable -> L34
        r9 = r9 + 1;     // Catch: Throwable -> L34
        goto L20
    L45:
        throw new RemoteException("Cursor read incomplete (ContentProvider dead?)");     // Catch: Throwable -> L34
    L46:
        r4.close();     // Catch: RemoteException -> L107 Throwable -> L49
        r7.release();     // Catch: Throwable -> L15 Exception -> L17
    L34:
        th = move-exception;
        r4.close();     // Catch: Throwable -> L53
        goto L13
    L125:
        throw th;     // Catch: RemoteException -> L107 Throwable -> L49
    L53:
        th = move-exception;
        th.addSuppressed(th);     // Catch: RemoteException -> L107 Throwable -> L49
        throw th;     // Catch: RemoteException -> L107 Throwable -> L49
    L49:
        th = move-exception;
        r7.release();     // Catch: Throwable -> L15 Exception -> L17
        throw th;     // Catch: Throwable -> L15 Exception -> L17
    L13:
        r7.release();     // Catch: Throwable -> L15 Exception -> L17
    L88:
        th = move-exception;
        Throwable r145 = th;
        r2 = r8;
    L104:
        if (r2 == null) goto L126;
        r2.close();
        throw r145;
    L126:
        throw r145;
    L17:
        e = move-exception;
        r142 = e;
    L15:
        th = move-exception;
        r145 = th;
        goto L104
    }

    private static boolean zzd(Cursor r2) {
        zzn r0 = (zzn) zzh.get();
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.zza != null) goto L10;
        r0.zza = r2;
        return true;
    L10:
        return false;
    }

    private static DynamiteModule zze(Context r2, String r3) {
        Log.i("DynamiteModule", "Selected local version of ".concat(String.valueOf(r3)));
        return new DynamiteModule(r2);
    }

    private static void zzf(ClassLoader r3) {
        IBinder r32 = (IBinder) r3.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);     // Catch: NoSuchMethodException -> L9 InvocationTargetException -> L11 InstantiationException -> L13 IllegalAccessException -> L15 ClassNotFoundException -> L17
        if (r32 != null) goto L6;
        zzq r1 = null;
    L20:
        zzm = r1;     // Catch: NoSuchMethodException -> L9 InvocationTargetException -> L11 InstantiationException -> L13 IllegalAccessException -> L15 ClassNotFoundException -> L17
        return;
    L6:
        IInterface r12 = r32.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");     // Catch: NoSuchMethodException -> L9 InvocationTargetException -> L11 InstantiationException -> L13 IllegalAccessException -> L15 ClassNotFoundException -> L17
        if ((r12 instanceof zzq) == false) goto L19;
        r1 = (zzq) r12;     // Catch: NoSuchMethodException -> L9 InvocationTargetException -> L11 InstantiationException -> L13 IllegalAccessException -> L15 ClassNotFoundException -> L17
        goto L20
    L19:
        r1 = new zzq(r32);     // Catch: NoSuchMethodException -> L9 InvocationTargetException -> L11 InstantiationException -> L13 IllegalAccessException -> L15 ClassNotFoundException -> L17
    L17:
        e = e;
    L23:
        throw new LoadingException("Failed to instantiate dynamite loader", e, null);
    L15:
        e = e;
    L13:
        e = e;
    L9:
        e = e;
    L11:
        e = e;
        goto L23
    }

    private static zzp zzg(Context r6) {
        monitor-enter(DynamiteModule.class);
        zzp r2 = zzl;     // Catch: Throwable -> L8
        if (r2 == null) goto L29;
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L8
        return r2;
    L29:
        IBinder r62 = (IBinder) r6.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();     // Catch: Throwable -> L8 Exception -> L17
        if (r62 != null) goto L14;
        zzp r3 = null;
    L20:
        if (r3 == null) goto L25;
        zzl = r3;     // Catch: Throwable -> L8 Exception -> L17
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L8
        return r3;
    L25:
        monitor-exit(DynamiteModule.class);     // Catch: Throwable -> L8
        return null;
    L14:
        IInterface r32 = r62.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");     // Catch: Throwable -> L8 Exception -> L17
        if ((r32 instanceof zzp) == false) goto L19;
        r3 = (zzp) r32;     // Catch: Throwable -> L8 Exception -> L17
        goto L20
    L19:
        r3 = new zzp(r62);     // Catch: Throwable -> L8 Exception -> L17
    L17:
        e = move-exception;
        String r63 = e.getMessage();     // Catch: Throwable -> L8
        StringBuilder r5 = new StringBuilder(String.valueOf(r63).length() + 45);     // Catch: Throwable -> L8
        r5.append("Failed to load IDynamiteLoader from GmsCore: ");     // Catch: Throwable -> L8
        r5.append(r63);     // Catch: Throwable -> L8
        Log.e("DynamiteModule", r5.toString());     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public Context getModuleContext() {
        return this.zzk;
    }

    public IBinder instantiate(String r3) {
        return (IBinder) this.zzk.getClassLoader().loadClass(r3).newInstance();
    L4:
        e = move-exception;
        throw new LoadingException("Failed to instantiate module class: ".concat(String.valueOf(r3)), e, null);
    }
}
