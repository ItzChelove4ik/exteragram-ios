package com.google.android.gms.internal.measurement;

import android.os.StrictMode;
import android.util.Log;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class zzpg {
    static final zzon zza = null;
    private static final zzpe zzc = null;
    private volatile zzqs zzd;
    private final zzlk zze;
    private final String zzf;
    private final String zzg;
    private final boolean zzh;
    private final ImmutableSet zzi;
    private final zzps zzj;
    private final zzqt zzk;

    static {
        zzc = new zzpe(null);
        zza = new zzon(zzox.zza, false, false, false, false, ImmutableSet.of());
    }

    public /* synthetic */ zzpg(zzlk r2, zzon r3, String r4, byte[] r5) {
        this.zze = r2;
        String r42 = r3.zza(r2.zzc());
        this.zzf = r42;
        this.zzg = _UrlKt.FRAGMENT_ENCODE_SET;
        this.zzh = r3.zzb();
        this.zzi = r3.zzc();
        this.zzd = null;
        this.zzj = new zzps();
        this.zzk = new zzqt(r2, r42, _UrlKt.FRAGMENT_ENCODE_SET, false);
    }

    public static zzpe zzd() {
        return zzc;
    }

    public static /* synthetic */ boolean zzl(List r1) {
        return zzc.zzb(r1);
    }

    private final zzqs zzq() {
        zzqs r0 = this.zzd;
        if (r0 != null) goto L40;
        monitor-enter(this);
        zzqs r02 = this.zzd;     // Catch: Throwable -> L19
        if (r02 != null) goto L36;
        StrictMode.ThreadPolicy r03 = StrictMode.allowThreadDiskWrites();     // Catch: Throwable -> L19
        zzqs r1 = this.zzk.zza();     // Catch: Throwable -> L33
        StrictMode.setThreadPolicy(r03);     // Catch: Throwable -> L19
        if (r1.zzk() == true) goto L27;
        zzlk r04 = this.zze;     // Catch: Throwable -> L19
        r04.zzd().zza();     // Catch: Throwable -> L19
        if (this.zzh == false) goto L15;
    L21:
        r04.zzg().execute(new zzoy(this));     // Catch: Throwable -> L19
        r04.zzk().zza(r1.zze(), this.zzi, this.zzf);     // Catch: Throwable -> L19
        if (this.zzg.equals(_UrlKt.FRAGMENT_ENCODE_SET) == true) goto L25;
        r04.zzg().execute(new zzoq(this));     // Catch: Throwable -> L19
    L25:
        if (this.zzk.zzb() == false) goto L27;
        r04.zzg().execute(new zzor(this));     // Catch: Throwable -> L19
        goto L27
    L15:
        if (this.zzk.zzb() == true) goto L21;
        if (r1.zzd().isEmpty() == false) goto L21;
        r04.zzg().execute(new zzop(this));     // Catch: Throwable -> L19
        r02 = zzqs.zzb(zzqv.zzi(), r1);     // Catch: Throwable -> L19
    L29:
        if (this.zzh == true) goto L31;
    L32:
        this.zzd = r02;     // Catch: Throwable -> L19
        goto L36
    L31:
        if (r02.zzj() == true) goto L36;
    L27:
        r02 = r1;
        goto L29
    L33:
        th = move-exception;
        StrictMode.setThreadPolicy(r03);     // Catch: Throwable -> L19
        throw th;     // Catch: Throwable -> L19
    L36:
        monitor-exit(this);     // Catch: Throwable -> L19
        return r02;
    L19:
        th = move-exception;
        throw th;
    L40:
        return r0;
    }

    private final void zzr() {
        zzqt r0 = this.zzk;
        ListenableFuture r1 = r0.zzd(this.zzg);
        zzpf r2 = new zzpf(r0);
        zzlk r02 = this.zze;
        Futures.transformAsync(r1, r2, r02.zzg()).addListener(new zzov(this, r1), r02.zzg());
    }

    private final /* synthetic */ void zzs(ListenableFuture r4) {
        zzqv r42 = (zzqv) Futures.getDone(r4);     // Catch: CancellationException -> L7 ExecutionException -> L9
        zzqs r0 = zzqs.zza(r42, new zzqr(6, 2));     // Catch: CancellationException -> L7 ExecutionException -> L9
        boolean r1 = this.zzh;     // Catch: CancellationException -> L7 ExecutionException -> L9
        Preconditions.checkNotNull(r0);     // Catch: CancellationException -> L7 ExecutionException -> L9
        if (r1 == true) goto L11;
        zzqs r2 = this.zzd;     // Catch: CancellationException -> L7 ExecutionException -> L9
        if (r2 == null) goto L11;
    L18:
        if (r2.zzf().equals(r0.zzf()) == true) goto L28;
        zzqm r43 = this.zze.zzj();     // Catch: CancellationException -> L7 ExecutionException -> L9
        if (r43 == null) goto L39;
        r43.zza();     // Catch: CancellationException -> L7 ExecutionException -> L9
        return;
    L39:
        return;
    L28:
        if (this.zzh == false) goto L40;
        zzlk r02 = this.zze;     // Catch: CancellationException -> L7 ExecutionException -> L9
        Futures.catching(r02.zzh().zzb(r42.zza()), Throwable.class, new zzow(this), r02.zzg());     // Catch: CancellationException -> L7 ExecutionException -> L9
        return;
    L40:
        return;
    L11:
        monitor-enter(this);     // Catch: CancellationException -> L7 ExecutionException -> L9
        if (r1 == false) goto L37;
    L25:
        this.zzd = r0;     // Catch: Throwable -> L23
        this.zzj.zzb();     // Catch: Throwable -> L23
        monitor-exit(this);     // Catch: Throwable -> L23
        goto L28
    L37:
        r2 = this.zzd;     // Catch: Throwable -> L23
        if (r2 == null) goto L25;
        monitor-exit(this);     // Catch: Throwable -> L23
    L23:
        th = move-exception;
        throw th;     // Catch: CancellationException -> L7 ExecutionException -> L9
    L7:
        e = e;
    L34:
        if ((e.getCause() instanceof SecurityException) == true) goto L41;
        String r3 = this.zzf;
        StringBuilder r12 = new StringBuilder(String.valueOf(r3).length() + 64);
        r12.append("Unable to update local snapshot for ");
        r12.append(r3);
        r12.append(", may result in stale flags.");
        Log.w("FlagStore", r12.toString(), e);
        return;
    L41:
        return;
    L9:
        e = e;
        goto L34
    }

    public final Object zza(String r1, boolean r2) {
        return zzq().zzf().get(r1);
    }

    public final String zzb() {
        return this.zzf;
    }

    public final zzps zzc() {
        return this.zzj;
    }

    public final /* synthetic */ void zze() {
        zzr();
    }

    public final /* synthetic */ ListenableFuture zzf() {
        zzqs r0 = zzq();
        String r1 = r0.zzd();
        zzlk r2 = this.zze;
        zzqn r3 = r2.zzd().zzc(false);
        if (r3.zze() == false) goto L18;
        if (Strings.isNullOrEmpty(r1) == true) goto L7;
    L10:
        zzmb r4 = zzme.zzb();
        r4.zzb(r0.zzi());
        if (Strings.isNullOrEmpty(r1) == true) goto L14;
        r4.zza(r1);
    L14:
        if (r3.zzd() == false) goto L16;
        r4.zzc(this.zzf);
    L16:
        ListenableFuture r02 = r2.zzh().zzc((zzme) r4.zzbd());
    L23:
        return Futures.catchingAsync(r02, zzmk.class, new zzos(this), r2.zzg());
    L7:
        if (r3.zzd() == true) goto L10;
        return Futures.immediateVoidFuture();
    L18:
        if (Strings.isNullOrEmpty(r1) == true) goto L20;
        r02 = r2.zzh().zzb(r1);
        goto L23
    L20:
        return Futures.immediateVoidFuture();
    }

    public final /* synthetic */ void zzg() {
        zzr();
    }

    public final /* synthetic */ void zzh() {
        zzlk r0 = this.zze;
        ListenableFuture r1 = zzpu.zza(r0, this.zzf, this.zzg);
        r1.addListener(new zzot(this, r1), r0.zzg());
    }

    public final /* synthetic */ void zzi() {
        this.zze.zzf().zza(zzabz.zzd, this.zzh, zzou.zza);
    }

    public final /* synthetic */ ListenableFuture zzj(zzmk r2) {
        int r22 = r2.zza();
        if (r22 == 29501) goto L21;
        if (r22 == 29537) goto L21;
        if (r22 == 29538) goto L21;
        if (r22 == 29539) goto L21;
        if (r22 == 29540) goto L21;
        if (r22 == 29541) goto L21;
        if (r22 == 29542) goto L21;
        if (r22 == 29543) goto L21;
        if (r22 == 29544) goto L21;
    L24:
        return Futures.immediateVoidFuture();
    L21:
        if (this.zzk.zzb() == true) goto L24;
        zzr();
        goto L24
    }

    public final /* synthetic */ void zzk(ListenableFuture r3) {
        Futures.getDone(r3);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        String r2 = this.zzf;
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 73);
        r1.append("Failed to store account on flag read for: ");
        r1.append(r2);
        r1.append(" which may lead to stale flags.");
        Log.w("FlagStore", r1.toString(), e);
    }

    public final /* synthetic */ void zzm(ListenableFuture r1) {
        zzs(r1);
    }

    public final /* synthetic */ Void zzn(Throwable r3) {
        Log.w("FlagStore", "Failed to commit to updated flags for ".concat(String.valueOf(this.zzf)), r3);
        return null;
    }

    public final /* synthetic */ boolean zzo() {
        if (this.zzh == true) goto L6;
        return true;
    L6:
        zzqs r0 = this.zzd;
        if (r0 != null) goto L9;
        return false;
    L9:
        if (r0.zzh() == false) goto L11;
    L16:
        monitor-enter(this);
        zzqs r02 = this.zzd;     // Catch: Throwable -> L26
        if (r02 != null) goto L20;
    L29:
        monitor-exit(this);     // Catch: Throwable -> L26
        return false;
    L20:
        if (r02.zzh() == false) goto L22;
    L28:
        this.zzd = null;     // Catch: Throwable -> L26
        this.zzj.zzb();     // Catch: Throwable -> L26
        goto L29
    L22:
        if (r02.zzg() == true) goto L28;
        if (this.zzk.zzb() == false) goto L29;
    L26:
        th = move-exception;
        throw th;
    L11:
        if (r0.zzg() == true) goto L16;
        if (this.zzk.zzb() == true) goto L16;
        return false;
    }
}
