package com.google.android.gms.internal.mlkit_genai_prompt;

import android.os.RemoteException;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public abstract class zzfv implements zzdp {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final String zzd = "zzfv";
    protected final zzdq zza;
    protected final zzpc zzb;
    private final zzdi zze;
    private final zzdy zzf;
    private final Executor zzg;
    private final Object zzh;
    private ListenableFuture zzi;
    private ListenableFuture zzj;
    private ListenableFuture zzk;

    static {
    }

    public zzfv(zzdi r2, zzdq r3, zzdy r4) {
        this.zzh = new Object();
        this.zze = r2;
        this.zza = r3;
        this.zzf = r4;
        this.zzb = r2.zze();
        this.zzg = r2.zzf();
    }

    private final ListenableFuture zzd() {
        Object r0 = this.zzh;
        monitor-enter(r0);
        ListenableFuture r1 = this.zzj;     // Catch: Throwable -> L10
        zzdi r2 = this.zze;     // Catch: Throwable -> L10
        if (r1 != r2.zzd()) goto L12;
        ListenableFuture r12 = this.zzk;     // Catch: Throwable -> L10
        if (r12 == null) goto L12;
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r12;
    L12:
        zzdy r13 = this.zzf;     // Catch: Throwable -> L10
        if (r2.zzh() == true) goto L15;
        Log.i(zzd, "Auto-download is disabled, skipping download.");     // Catch: Throwable -> L10
        ListenableFuture r14 = zzot.zzb();     // Catch: Throwable -> L10
    L16:
        zzom r15 = zzom.zzv(r14);     // Catch: Throwable -> L10
        zzfm r3 = new zzfm(this);     // Catch: Throwable -> L10
        zzpc r4 = this.zzb;     // Catch: Throwable -> L10
        zzom r16 = (zzom) zzot.zzf((zzom) zzot.zzf(r15, r3, r4), new zzfn(this), r4);     // Catch: Throwable -> L10
        this.zzk = r16;     // Catch: Throwable -> L10
        this.zzj = r2.zzd();     // Catch: Throwable -> L10
        zzot.zzh(r16, new zzey(this), zzpi.zza());     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r16;
    L15:
        r14 = (zzom) zzot.zzf(zzom.zzv(r2.zzb(this.zza)), new zzfo(this, r13), this.zzb);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void zzp(zzav r2) {
        if (r2 == null) goto L10;
        r2.zzd();     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        Log.w(zzd, "Failed to cancel inference", e);
        return;
    }

    public static /* synthetic */ void zzr(zzav r2) {
        if (r2 == null) goto L10;
        r2.zzd();     // Catch: RemoteException -> L5
        return;
    L5:
        e = move-exception;
        Log.w(zzd, "Failed to cancel preparation of inference engine", e);
        return;
    }

    public static /* synthetic */ void zzt(zzav r2) {
        r2.zzd();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        Log.w(zzd, "Failed to cancel tokenization request", e);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdp
    public final ListenableFuture zza() {
        return zzot.zzf(zzi(), new zzez(this), this.zzb);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdp
    public final ListenableFuture zzb(Object r3) {
        return zzot.zzf(zzi(), new zzfj(this, r3), this.zzb);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzdp
    public final ListenableFuture zzc(Object r3, boolean r4) {
        return zzot.zzf(zzi(), new zzfs(this, r3, true), this.zzb);
    }

    public abstract Object zze(zzan r1);

    public abstract zzav zzf(Object r1, Object r2, zzgw r3);

    public abstract zzav zzg(Object r1, zzbn r2);

    public zzav zzh(Object r1, boolean r2, Object r3, zzbp r4) {
        Log.w(zzd, "getTokenInfo() is not supported.");
        r4.zzc(8);
        return new zzgv();
    }

    public final ListenableFuture zzi() {
        Object r0 = this.zzh;
        monitor-enter(r0);
        ListenableFuture r1 = this.zzj;     // Catch: Throwable -> L9
        zzdi r2 = this.zze;     // Catch: Throwable -> L9
        if (r1 == r2.zzd()) goto L7;
    L11:
        this.zzi = (zzom) zzot.zzf(zzom.zzv(r2.zzd()), new zzfl(this), this.zzb);     // Catch: Throwable -> L9
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return (zzom) zzot.zzf(zzom.zzv(this.zzi), new zzfk(this), this.zzb);
    L7:
        if (this.zzi != null) goto L12;
    L9:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ ListenableFuture zzj(Integer r3) {
        if (r3.intValue() == 0) goto L10;
        if (r3.intValue() == 2147483646) goto L10;
        return (zzom) zzot.zzf(zzom.zzv(zzed.zza(r3.intValue(), this.zze.zze())), new zzfr(this), this.zzb);
    L10:
        return zzd();
    }

    public final /* synthetic */ ListenableFuture zzk(Void r1) {
        return this.zze.zzd();
    }

    public final /* synthetic */ ListenableFuture zzl(zzdh r3) {
        Object r2 = zze(r3.zza());     // Catch: RuntimeException -> L6 RemoteException -> L8
    L5:
        return zzot.zza(new zzgm(r2, r3.zzb()));
    L8:
        e = move-exception;
        Log.e(zzd, "Failed to initialize service.", e);
        return zzot.zzc(zzdo.zzb(6, "Failed to initialize service.", e));
    L6:
        e = move-exception;
        Log.e(zzd, "Failed to initialize service.", e);
        return zzot.zzc(zzdo.zzb(0, "Failed to initialize service.", e));
    }

    public final /* synthetic */ ListenableFuture zzm(zzdy r5, Integer r6) {
        int r0 = r6.intValue();
        if (r0 != 0) goto L5;
        Executor r62 = this.zzg;
        zzdo r02 = zzdo.zza(0, "Feature is unavailable.", null);
        r62.execute(new zzfp(this, r5, r02));
        return zzot.zzc(r02);
    L5:
        if (r0 == 1) goto L15;
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L12;
        new StringBuilder(String.valueOf(r6).length() + 27);
        return zzot.zzc(zzdo.zza(0, "Unexpected feature status: ".concat(String.valueOf(r6)), null));
    L12:
        zzdq r63 = this.zza;
        String r03 = zzd;
        String r64 = r63.zza();
        StringBuilder r2 = new StringBuilder(r64.length() + 33);
        r2.append("Feature ");
        r2.append(r64);
        r2.append(" is downloaded and ready.");
        Log.i(r03, r2.toString());
        this.zzg.execute(new zzfq(this, r5));
        return zzot.zzb();
    L15:
        return this.zze.zzc(this.zza, r5);
    }

    public final /* synthetic */ ListenableFuture zzn(Boolean r2) {
        if (r2.booleanValue() == true) goto L7;
        return zzot.zzc(zzdo.zze(30, "Request blocked due to background use.", null));
    L7:
        return zzd();
    }

    public final /* synthetic */ Object zzo(zzfu r3, Object r4, CallbackToFutureAdapter.Completer r5) {
        r5.addCancellationListener(new zzfc(zzf(r3.zza(), r4, new zzft(this, r5))), this.zzb);
        return "runInferenceFuture";
    L8:
        e = move-exception;
        Log.e(zzd, "Failed to run inference.", e);
        r5.setException(zzdo.zzb(6, "Failed to run inference.", e));
        return null;
    L6:
        e = move-exception;
        Log.e(zzd, "Failed to run inference.", e);
        r5.setException(zzdo.zzb(0, "Failed to run inference.", e));
        return null;
    }

    public final /* synthetic */ Object zzq(zzfu r3, CallbackToFutureAdapter.Completer r4) {
        zzex r0 = new zzex(this, r4);
        r4.addCancellationListener(new zzff(zzg(r3.zza(), r0)), this.zzb);
        return "prepareInferenceEngineFuture";
    L8:
        e = move-exception;
        Log.e(zzd, "Failed to prepare inference engine.", e);
        r4.setException(zzdo.zzc(6, "Failed to prepare inference engine.", e));
        return null;
    L6:
        e = move-exception;
        Log.e(zzd, "Failed to prepare inference engine.", e);
        r4.setException(zzdo.zzc(0, "Failed to prepare inference engine.", e));
        return null;
    }

    public final /* synthetic */ Object zzs(Object r3, boolean r4, zzfu r5, CallbackToFutureAdapter.Completer r6) {
        zzew r42 = new zzew(this, r6);
        r6.addCancellationListener(new zzfi(zzh(r3, true, r5.zza(), r42)), this.zzb);
        return "getTokenInfoFuture";
    L8:
        e = move-exception;
        Log.e(zzd, "Failed to tokenize the input query.", e);
        r6.setException(zzdo.zzd(6, "Failed to tokenize the input query.", e));
        return null;
    L6:
        e = move-exception;
        Log.e(zzd, "Failed to tokenize the input query.", e);
        r6.setException(zzdo.zzd(0, "Failed to tokenize the input query.", e));
        return null;
    }

    public final /* synthetic */ Object zzu() {
        return this.zzh;
    }

    public final /* synthetic */ void zzv(ListenableFuture r1) {
        this.zzk = null;
    }
}
