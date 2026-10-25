package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public final class zzgl implements IBinder.DeathRecipient, zzdi {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final String zzc = "zzgl";
    private static final ComponentName zzd = null;
    private static final ComponentName zze = null;
    protected final Context zza;
    private final zzpc zzf;
    private final Executor zzg;
    private final boolean zzh;
    private final long zzi;
    private final ScheduledExecutorService zzj;
    private final UserManager zzk;
    private final boolean zzl;
    private final Object zzm;
    private zzgb zzn;
    private ScheduledFuture zzo;

    static {
        ComponentName r0 = new ComponentName("com.google.android.aicore", "com.google.android.apps.aicore.service.multiuser.AiCoreMultiUserService");
        zzd = r0;
        zze = new ComponentName(r0.getPackageName(), "com.google.android.apps.aicore.service.AiCoreService");
    }

    public zzgl(zzdn r4) {
        this.zzm = new Object();
        Context r0 = r4.zza();
        this.zza = r0;
        this.zzf = zzpi.zzb(r4.zzb());
        this.zzg = r4.zzc();
        this.zzh = r4.zzd();
        this.zzi = r4.zze().toMillis();
        this.zzj = r4.zzf();
        this.zzk = (UserManager) r0.getSystemService("user");
        this.zzl = ((Boolean) r4.zzg().orElse(Boolean.TRUE)).booleanValue();
    }

    public static /* synthetic */ ListenableFuture zzk(int r1, int r2, zzan r3) {
    L7:
        e = move-exception;
        Log.e(zzc, "AiCore service failed to get feature.", e);
        return zzot.zzc(zzdo.zzc(6, "AiCore service failed to get feature.", e));
    L4:
        if (r3.zzg() < 6) goto L6;
        zzn r22 = r3.zzd(r1, -1);     // Catch: RemoteException -> L7
        if (r22 != null) goto L14;
        return zzot.zzc(zzdo.zzf(String.format(Locale.ENGLISH, "Feature %d is not available.", new Object[]{Integer.valueOf(r1)})));
    L14:
        return zzot.zza(zzdq.zzi(r22));
    L6:
        return zzot.zzc(zzdo.zzc(8, "getFeatureOrControl is not supported before AICoreVersion V7", null));
    }

    public static /* synthetic */ ListenableFuture zzl(zzdq r3, zzan r4) {
        return zzot.zza(Integer.valueOf(r4.zze(r3.zzj())));
    L4:
        e = move-exception;
        Log.e(zzc, "AiCore service failed to get feature status for ".concat(r3.zza()), e);
        return zzot.zzc(zzdo.zzc(6, "AiCore service failed to get feature status for ".concat(r3.zza()), e));
    }

    public static /* synthetic */ String zzp() {
        return zzc;
    }

    public static /* synthetic */ ComponentName zzq() {
        return zze;
    }

    public static final ComponentName zzt() {
        return zzd;
    }

    private final void zzu() {
        long r0 = this.zzi;
        if (r0 > 0) goto L5;
        return;
    L5:
        Object r2 = this.zzm;
        monitor-enter(r2);
        ScheduledFuture r3 = this.zzo;     // Catch: Throwable -> L10
        if (r3 == null) goto L12;
        r3.cancel(false);     // Catch: Throwable -> L10
    L12:
        this.zzo = this.zzj.schedule(new zzgf(this), r0, TimeUnit.MILLISECONDS);     // Catch: Throwable -> L10
        monitor-exit(r2);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied(IBinder r3) {
        Log.i(zzc, "Binder died for component: ".concat(String.valueOf(r3)));
        zzj();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi, java.lang.AutoCloseable
    public final void close() {
        zzj();
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final ListenableFuture zza(int r3, int r4) {
        return zzot.zzf(zzi(), new zzgi(r3, -1), this.zzf);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final ListenableFuture zzb(zzdq r3) {
        return zzot.zzf(zzi(), new zzgd(r3), this.zzf);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final ListenableFuture zzc(zzdq r3, zzdy r4) {
        return zzot.zzf(zzd(), new zzge(this, r3, r4), this.zzf);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final ListenableFuture zzd() {
        UserManager r0 = this.zzk;
        if (r0 == null) goto L31;
        if (r0.isUserUnlocked() == false) goto L31;
        Object r02 = this.zzm;
        monitor-enter(r02);
        zzu();     // Catch: Throwable -> L12
        zzgb r2 = this.zzn;     // Catch: Throwable -> L12
        if (r2 == null) goto L14;
        monitor-exit(r02);     // Catch: Throwable -> L12
    L16:
        Object r3 = r2.zza;
        monitor-enter(r3);
    L21:
        th = move-exception;
        throw th;
    L19:
        if (r2.zzb.isCancelled() == false) goto L23;
        r2.zza();     // Catch: Throwable -> L21
    L23:
        zzpk r03 = r2.zzb;     // Catch: Throwable -> L21
        monitor-exit(r3);     // Catch: Throwable -> L21
        return r03;
    L14:
        r2 = new zzgk(this, null);     // Catch: Throwable -> L12
        this.zzn = r2;     // Catch: Throwable -> L12
        r2.zzb();     // Catch: Throwable -> L12
        monitor-exit(r02);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    L31:
        return zzot.zzc(zzdo.zze(601, "User is not unlocked.", null));
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final zzpc zze() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final Executor zzf() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final Context zzg() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdi
    public final boolean zzh() {
        return this.zzl;
    }

    public final ListenableFuture zzi() {
        zzu();
        return (zzom) zzot.zzg(zzom.zzv(zzd()), zzgc.zza, zzpi.zza());
    }

    public final void zzj() {
        Object r0 = this.zzm;
        monitor-enter(r0);
        zzgb r1 = this.zzn;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.zzc();     // Catch: Throwable -> L7
        this.zzn = null;     // Catch: Throwable -> L7
    L9:
        ScheduledFuture r12 = this.zzo;     // Catch: Throwable -> L7
        if (r12 == null) goto L12;
        r12.cancel(false);     // Catch: Throwable -> L7
        this.zzo = null;     // Catch: Throwable -> L7
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ void zzm() {
        Log.i(zzc, "Cleaning up due to autoUnbind.");
        zzj();
    }

    public final /* synthetic */ Object zzn(zzdh r3, zzdq r4, zzdy r5, CallbackToFutureAdapter.Completer r6) {
        r3.zza().zzf(r4.zzj(), new zzga(this, r5, r4, r6));     // Catch: RuntimeException -> L4 RemoteException -> L6
    L15:
        return "requestDownloadableFeatureFuture";
    L6:
        e = move-exception;
        Log.e(zzc, "AiCore service failed to download feature ".concat(r4.zza()), e);
        r6.setException(zzdo.zza(6, "AICore service failed to download feature ".concat(r4.zza()), e));
        return "requestDownloadableFeatureFuture";
    L4:
        e = move-exception;
        Log.e(zzc, "AiCore service failed to download feature due to runtime error ".concat(r4.zza()), e);
        r6.setException(zzdo.zza(0, "AICore service failed to download feature due to runtime error ".concat(r4.zza()), e));
        return "requestDownloadableFeatureFuture";
    }

    public final /* synthetic */ boolean zzo() {
        UserManager r0 = this.zzk;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isSystemUser() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public final /* synthetic */ Executor zzr() {
        return this.zzg;
    }

    public final /* synthetic */ boolean zzs() {
        return this.zzh;
    }
}
