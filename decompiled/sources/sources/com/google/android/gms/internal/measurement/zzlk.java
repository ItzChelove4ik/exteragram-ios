package com.google.android.gms.internal.measurement;

import android.content.Context;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzlk {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final Object zzb = null;
    private static final AtomicReference zzc = null;
    private static volatile zzlk zzd;
    private static volatile zzlk zze;
    private static final Supplier zzf = null;
    private final zzoh zzg;
    private final Context zzh;
    private final Supplier zzi;
    private final Supplier zzj;
    private final Supplier zzk;
    private final Supplier zzl;
    private final zzrf zzm;
    private final Supplier zzn;
    private final zzqe zzo;

    static {
        zzb = new Object();
        zzc = new AtomicReference();
        zzd = null;
        zze = null;
        zzf = Suppliers.memoize(zzlp.zza);
    }

    public /* synthetic */ zzlk(Context r1, Supplier r2, Supplier r3, Supplier r4, Supplier r5, Supplier r6, byte[] r7) {
        this.zzg = new zzol();
        Context r12 = r1.getApplicationContext();
        Preconditions.checkNotNull(r12);
        Preconditions.checkNotNull(r2);
        Preconditions.checkNotNull(r3);
        Preconditions.checkNotNull(r4);
        Preconditions.checkNotNull(r5);
        Preconditions.checkNotNull(r6);
        Supplier r22 = Suppliers.memoize(r2);
        Supplier r32 = Suppliers.memoize(r3);
        Supplier r42 = Suppliers.memoize(new zzlq(r4));
        Supplier r52 = Suppliers.memoize(r5);
        Supplier r62 = Suppliers.memoize(r6);
        this.zzh = r12;
        this.zzi = r22;
        this.zzj = r32;
        this.zzk = r42;
        this.zzl = r52;
        this.zzm = new zzrf(r12, r22, r52, r32);
        this.zzn = r62;
        this.zzo = new zzqe(r12, r22, r42, r32);
    }

    public static void zza(Context r4) {
        if (zzc.get() == null) goto L12;
        return;
    L12:
        Context r42 = r4.getApplicationContext();     // Catch: NullPointerException -> L8
    L9:
        if (r42 == null) goto L14;
        AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(zzc, null, r42);
        return;
    L14:
        return;
    L8:
        zzl();
        zzlz.zza(Level.WARNING, (Executor) zzf.get(), "context.getApplicationContext() yielded NullPointerException", new Object[0]);
        r42 = null;
        goto L9
    }

    public static zzlk zzb() {
        zzls.zza();
        zzls.zzc();
        Context r0 = (Context) zzc.get();
        if (r0 == null) goto L24;
        zzlk r1 = zzd;
        if (r1 == null) goto L7;
        return r1;
    L7:
        Context r02 = r0.getApplicationContext();
        Class<?> r2 = r02.getApplicationContext().getClass();     // Catch: IllegalStateException -> L10
        new StringBuilder(String.valueOf(r2).length() + 72);     // Catch: IllegalStateException -> L10
        r2.toString();     // Catch: IllegalStateException -> L10
        throw new IllegalStateException("Given application context does not implement GeneratedComponentManager: ".concat(String.valueOf(r2)));     // Catch: IllegalStateException -> L10
    L11:
        monitor-enter(zzb);
    L17:
        th = move-exception;
        throw th;
    L13:
        if (zzd == null) goto L19;
        zzlk r03 = zzd;     // Catch: Throwable -> L17
    L21:
        return r03;
    L19:
        r03 = (zzlk) Optional.absent().or(new zzll(r02));     // Catch: Throwable -> L17
        zzd = r03;     // Catch: Throwable -> L17
        zzlz.zza(Level.CONFIG, r03.zzg(), "Application doesn't implement PhenotypeApplication interface, falling back to globally set context. See go/phenotype-flag#process-stable-init for more info.", new Object[0]);     // Catch: Throwable -> L17
        goto L21
    L24:
        zzls.zzb();
        Segment$$ExternalSyntheticBUOutline1.m("Must call PhenotypeContext.setContext() first");
        return null;
    }

    public static boolean zzl() {
        zzls.zzb();
        if (zzc.get() != null) goto L5;
        zzls.zzd();
    L5:
        return false;
    }

    public static /* synthetic */ Supplier zzm() {
        return zzf;
    }

    public final Context zzc() {
        return this.zzh;
    }

    public final zzrf zzd() {
        return this.zzm;
    }

    public final Optional zze() {
        return (Optional) this.zzn.get();
    }

    public final zzqe zzf() {
        return this.zzo;
    }

    public final ListeningScheduledExecutorService zzg() {
        return (ListeningScheduledExecutorService) this.zzi.get();
    }

    public final zzmj zzh() {
        return (zzmj) this.zzj.get();
    }

    public final zzru zzi() {
        return (zzru) this.zzl.get();
    }

    public final zzqm zzj() {
        return (zzqm) this.zzk.get();
    }

    public final zzoh zzk() {
        return this.zzg;
    }
}
