package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.CredentialsData;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.cast.zzbq;
import com.google.android.gms.internal.cast.zzbx;
import com.google.android.gms.internal.cast.zzce;
import com.google.android.gms.internal.cast.zzee;
import com.google.android.gms.internal.cast.zzek;
import com.google.android.gms.internal.cast.zzwt;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class CastContext {
    public static final String OPTIONS_PROVIDER_CLASS_NAME_KEY = "com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME";
    private static final Logger zzb = null;
    private static final Object zzc = null;
    private static volatile CastContext zzd;
    final com.google.android.gms.internal.cast.zzax zza;
    private final Context zze;
    private final zzai zzf;
    private final SessionManager zzg;
    private final zzab zzh;
    private final PrecacheManager zzi;
    private final MediaNotificationManager zzj;
    private final CastOptions zzk;
    private final com.google.android.gms.cast.internal.zzn zzl;
    private final zzbx zzm;
    private final zzbq zzn;
    private final List zzo;
    private final zzce zzp;
    private com.google.android.gms.internal.cast.zzba zzq;
    private CastReasonCodes zzr;

    static {
        zzb = new Logger("CastContext");
        zzc = new Object();
    }

    private CastContext(Context r8, CastOptions r9, List r10, zzbx r11, com.google.android.gms.cast.internal.zzn r12) {
        this.zze = r8;
        this.zzk = r9;
        this.zzm = r11;
        this.zzl = r12;
        this.zzo = r10;
        zzbq r102 = new zzbq(r8);
        this.zzn = r102;
        zzce r0 = r11.zzu();
        this.zzp = r0;
        zzk();
        Map r1 = zzj();
        r9.zzi(new zzm(1));
        zzai r112 = com.google.android.gms.internal.cast.zzay.zza(r8, r9, r11, r1);     // Catch: RemoteException -> L27
        this.zzf = r112;
        this.zzh = new zzab(r112.zzh());
        SessionManager r3 = new SessionManager(r112.zzg(), r8);
        this.zzg = r3;
        this.zzj = new MediaNotificationManager(r3);
        this.zzi = new PrecacheManager(r9, r3, r12);
        if (r0 == null) goto L11;
        r0.zza(r3);
    L11:
        zzek r02 = new zzek(r8, zzwt.zza(Executors.newFixedThreadPool(3)));
        new Logger("BaseNetUtils");
        r02.zza();
        com.google.android.gms.internal.cast.zzax r82 = new com.google.android.gms.internal.cast.zzax();
        this.zza = r82;
        r112.zzf(r82);     // Catch: RemoteException -> L18
        r82.zzf(r102.zza);
        if (r9.zzg().isEmpty() == true) goto L16;
        Logger r83 = zzb;
        String r113 = "Setting Route Discovery for appIds: ".concat(String.valueOf(r9.zzg()));
        r83.i(r113, new Object[0]);
        r102.zzf(r9.zzg());
    L16:
        r12.zzb(new String[]{"com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_ENABLED", "com.google.android.gms.cast.FLAG_CLIENT_SESSION_ANALYTICS_MODE", "com.google.android.gms.cast.FLAG_FIRELOG_UPLOAD_MODE", "com.google.android.gms.cast.FLAG_ANALYTICS_LOGGING_BUCKET_SIZE", "com.google.android.gms.cast.FLAG_CLIENT_FEATURE_USAGE_ANALYTICS_ENABLED", "com.google.android.gms.cast.FLAG_CLIENT_ANALYTICS_ENABLED", "com.google.android.gms.cast.FLAG_ANALYTICS_CONSENT_TIMEOUT_SECONDS"}).addOnSuccessListener(new zzh(this));
        r12.zzd(new String[]{"com.google.android.gms.cast.MAP_CAST_STATUS_CODES_TO_CAST_REASON_CODES"}).addOnSuccessListener(new zzi(this));
        return;
    L18:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to call addAppVisibilityListener", e);
        throw null;
    L21:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to call getSessionManagerImpl", e);
        throw null;
    L24:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to call getDiscoveryManagerImpl", e);
        throw null;
    L27:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to call newCastContextImpl", e);
        throw null;
    }

    public static CastContext getSharedInstance() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return zzd;
    }

    public static CastContext getSharedInstance(Context r8) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzd != null) goto L22;
        Object r1 = zzc;
        monitor-enter(r1);
    L11:
        th = move-exception;
        throw th;
    L7:
        if (zzd != null) goto L17;
        Context r3 = r8.getApplicationContext();     // Catch: Throwable -> L11
        OptionsProvider r82 = zzi(zzh(r3));     // Catch: Throwable -> L11
        CastOptions r4 = r82.getCastOptions(r3);     // Catch: Throwable -> L11
        com.google.android.gms.cast.internal.zzn r7 = new com.google.android.gms.cast.internal.zzn(r3);     // Catch: Throwable -> L11
        zzbx r6 = new zzbx(r3, MediaRouter.getInstance(r3), r4, r7);     // Catch: Throwable -> L11
        zzd = new CastContext(r3, r4, r82.getAdditionalSessionProviders(r3), r6, r7);     // Catch: Throwable -> L11 ModuleUnavailableException -> L13
    L13:
        e = move-exception;
        throw new RuntimeException(e);     // Catch: Throwable -> L11
    L17:
        monitor-exit(r1);     // Catch: Throwable -> L11
    L22:
        return zzd;
    }

    public static Task<CastContext> getSharedInstance(Context r7, Executor r8) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzd != null) goto L7;
        Context r2 = r7.getApplicationContext();
        OptionsProvider r4 = zzi(zzh(r2));
        CastOptions r3 = r4.getCastOptions(r2);
        com.google.android.gms.cast.internal.zzn r6 = new com.google.android.gms.cast.internal.zzn(r2);
        return Tasks.call(r8, new zzj(r2, r3, r4, new zzbx(r2, MediaRouter.getInstance(r2), r3, r6), r6));
    L7:
        return Tasks.forResult(zzd);
    }

    public static Task<CastContext> getSharedInstance(Context r8, Executor r9, OptionsProvider r10, CastOptions.Modifier r11) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(r11, "optionsModifier must not be null.");
        if (zzd != null) goto L9;
        Context r2 = r8.getApplicationContext();
        String r82 = zzh(r2);
        if (r82 == null) goto L7;
        r10 = zzi(r82);
    L7:
        OptionsProvider r4 = r10;
        CastOptions r3 = r4.getCastOptions(r2);
        r3.zza(r11);
        com.google.android.gms.cast.internal.zzn r6 = new com.google.android.gms.cast.internal.zzn(r2);
        zzbx r5 = new zzbx(r2, MediaRouter.getInstance(r2), r3, r6);
        TaskCompletionSource r7 = new TaskCompletionSource();
        r9.execute(new zzg(r2, r3, r4, r5, r6, r7));
        return r7.getTask();
    L9:
        zzd.applyOptionsModifier(r11);
        return Tasks.forResult(zzd);
    }

    public static CastContext zza(Context r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return getSharedInstance(r2);
    L5:
        e = move-exception;
        zzb.e("Failed to load module from Google Play services. Cast will not work properly. Might due to outdated Google Play services. Ignoring this failure silently.", new Object[]{e});
        return null;
    }

    public static int zzb(int r1) {
        if (zzd != null) goto L5;
        return 0;
    L5:
        return zzd.getCastReasonCodeForCastStatusCode(r1);
    }

    public static /* synthetic */ CastContext zzd(Context r8, CastOptions r9, OptionsProvider r10, zzbx r11, com.google.android.gms.cast.internal.zzn r12) {
        Object r1 = zzc;
        monitor-enter(r1);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (zzd != null) goto L9;
        zzd = new CastContext(r8, r9, r10.getAdditionalSessionProviders(r8), r11, r12);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r1);     // Catch: Throwable -> L7
        return zzd;
    }

    public static /* synthetic */ void zze(Context r8, CastOptions r9, OptionsProvider r10, zzbx r11, com.google.android.gms.cast.internal.zzn r12, TaskCompletionSource r13) {
        Object r1 = zzc;
        monitor-enter(r1);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (zzd == null) goto L18;
        r13.setResult(zzd);     // Catch: Throwable -> L8
    L14:
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L18:
        zzd = new CastContext(r8, r9, r10.getAdditionalSessionProviders(r8), r11, r12);     // Catch: Throwable -> L8 ModuleUnavailableException -> L10
        r13.setResult(zzd);     // Catch: Throwable -> L8 ModuleUnavailableException -> L10
    L10:
        e = move-exception;
        r13.setException(e);     // Catch: Throwable -> L8
        goto L14
    }

    private static String zzh(Context r3) {
        ApplicationInfo r32 = Wrappers.packageManager(r3).getApplicationInfo(r3.getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L11
        if (r32 != null) goto L6;
        return null;
    L6:
        Bundle r33 = r32.metaData;     // Catch: PackageManager.NameNotFoundException -> L11
        if (r33 == null) goto L10;
        return r33.getString(OPTIONS_PROVIDER_CLASS_NAME_KEY);
    L10:
        return null;
    }

    private static OptionsProvider zzi(String r2) {
        if (r2 == null) goto L9;
        return (OptionsProvider) Class.forName(r2).asSubclass(OptionsProvider.class).getDeclaredConstructor(null).newInstance(null);
    L9:
        throw new IllegalStateException("The fully qualified name of the implementation of OptionsProvider must be provided as a metadata in the AndroidManifest.xml with key com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME.");     // Catch: ReflectiveOperationException -> L6
    L6:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to initialize CastContext with manifest options.", e);
        return null;
    }

    private final Map zzj() {
        HashMap r0 = new HashMap();
        com.google.android.gms.internal.cast.zzba r1 = this.zzq;
        if (r1 == null) goto L5;
        r0.put(r1.getCategory(), r1.zza());
    L5:
        List r6 = this.zzo;
        if (r6 == null) goto L11;
        Iterator r62 = r6.iterator();
    L9:
        if (r62.hasNext() == false) goto L11;
        SessionProvider r12 = (SessionProvider) r62.next();
        Preconditions.checkNotNull(r12, "Additional SessionProvider must not be null.");
        String r2 = Preconditions.checkNotEmpty(r12.getCategory(), "Category for SessionProvider must not be null or empty string.");
        Preconditions.checkArgument(!r0.containsKey(r2), String.format("SessionProvider for category %s already added", new Object[]{r2}));
        r0.put(r2, r12.zza());
    L11:
        return r0;
    }

    @RequiresNonNull({"castOptions", "mediaRouter", "appContext"})
    private final void zzk() {
        CastOptions r0 = this.zzk;
        if (TextUtils.isEmpty(r0.getReceiverApplicationId()) == true) goto L6;
        this.zzq = new com.google.android.gms.internal.cast.zzba(this.zze, r0, this.zzm);
        return;
    L6:
        this.zzq = null;
    }

    @Deprecated
    public void addAppVisibilityListener(AppVisibilityListener r1) {
    }

    public void addCastStateListener(CastStateListener r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(r2);
        this.zzg.zzb(r2);
    }

    public void addSessionTransferCallback(SessionTransferCallback r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(r2);
        this.zzm.zzs(r2);
    }

    public void applyOptionsModifier(CastOptions.Modifier r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        String r0 = r4.getReceiverApplicationId();
        if (r0 == null) goto L5;
        setReceiverApplicationId(r0);
    L5:
        Boolean r02 = r4.getShowSystemOutputSwitcherOnCastIconClick();
        if (r02 == null) goto L8;
        this.zzk.zzd(r02.booleanValue());
    L8:
        Boolean r42 = r4.getRemoteToLocalEnabled();
        if (r42 == null) goto L11;
        this.zzk.zzc(r42.booleanValue());
    L11:
        this.zzm.zzr(r02, r42);
    }

    public CastOptions getCastOptions() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzk;
    }

    public int getCastReasonCodeForCastStatusCode(int r3) {
        CastReasonCodes r2 = this.zzr;
        if (r2 != null) goto L7;
        zzb.w("castReasonCodes hasn't been initialized yet", new Object[0]);
        return 0;
    L7:
        return r2.zza(r3);
    }

    public int getCastState() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzg.zza();
    }

    public MediaNotificationManager getMediaNotificationManager() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzj;
    }

    public MediaRouteSelector getMergedSelector() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return MediaRouteSelector.fromBundle(this.zzf.zze());
    L5:
        e = move-exception;
        zzb.d(e, "Unable to call %s on %s.", new Object[]{"getMergedSelectorAsBundle", zzai.class.getSimpleName()});
        return null;
    }

    public PrecacheManager getPrecacheManager() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzi;
    }

    public SessionManager getSessionManager() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzg;
    }

    @Deprecated
    public boolean isAppVisible() {
        return false;
    }

    @Deprecated
    public boolean onDispatchVolumeKeyEventBeforeJellyBean(KeyEvent r1) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return false;
    }

    @Deprecated
    public void removeAppVisibilityListener(AppVisibilityListener r1) {
    }

    public void removeCastStateListener(CastStateListener r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r2 != null) goto L5;
        return;
    L5:
        this.zzg.zzc(r2);
    }

    public void removeSessionTransferCallback(SessionTransferCallback r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(r2);
        this.zzm.zzt(r2);
    }

    public void setLaunchCredentialsData(CredentialsData r4) {
        CastOptions r0 = this.zzk;
        LaunchOptions.Builder r1 = new LaunchOptions.Builder(r0.getLaunchOptions());
        r1.setCredentialsData(r4);
        r0.zze(r1.build());
        zzk();
    }

    public void setReceiverApplicationId(String r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        CastOptions r0 = this.zzk;
        if (TextUtils.equals(r4, r0.getReceiverApplicationId()) == false) goto L5;
        return;
    L5:
        r0.zzb(r4);
        zzk();
        Map r02 = zzj();
        this.zzf.zzi(r4, r02);     // Catch: RemoteException -> L8
    L10:
        zzp r42 = new zzp();
        r42.zza(getCastReasonCodeForCastStatusCode(2423));
        r42.zzb(2423);
        zzq r43 = r42.zzc();
        Context r03 = this.zze;
        zzee.zza(r03, this.zzk, this.zza).zzb(r43);
        CastButtonFactory.zza(r03);
        return;
    L8:
        e = move-exception;
        zzb.d(e, "Unable to call %s on %s.", new Object[]{"setReceiverApplicationId", zzai.class.getSimpleName()});
        goto L10
    }

    public void setShowSystemOutputSwitcherOnCastIconClick(boolean r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzk.zzd(r2);
        this.zzm.zzr(Boolean.valueOf(r2), null);
    }

    public final zzab zzc() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzh;
    }

    public final /* synthetic */ void zzf(Bundle r5) {
        if (com.google.android.gms.internal.cast.zzj.zza == true) goto L5;
        return;
    L5:
        com.google.android.gms.internal.cast.zzj.zza(this.zze, this.zzl, this.zzg, this.zzp, this.zza).zzb(r5);
    }

    public final /* synthetic */ void zzg(Bundle r2) {
        this.zzr = new CastReasonCodes(r2);
    }
}
