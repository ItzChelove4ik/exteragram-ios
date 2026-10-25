package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes5.dex */
final class zzhx extends FutureTask implements Comparable {
    final boolean zza;
    final /* synthetic */ zzhz zzb;
    private final long zzc;
    private final String zzd;

    public zzhx(zzhz r3, Runnable r4, boolean r5, String r6) {
        Objects.requireNonNull(r3);
        this.zzb = r3;
        super(r4, null);
        Preconditions.checkNotNull(r6);
        long r0 = zzhz.zzu().getAndIncrement();
        this.zzc = r0;
        this.zzd = r6;
        this.zza = r5;
        if (r0 != LongCompanionObject.MAX_VALUE) goto L6;
        r3.zzu.zzaW().zzb().zza("Tasks index overflow");
        return;
    }

    public zzhx(zzhz r3, Callable r4, boolean r5, String r6) {
        Objects.requireNonNull(r3);
        this.zzb = r3;
        super(r4);
        Preconditions.checkNotNull("Task exception on worker thread");
        long r0 = zzhz.zzu().getAndIncrement();
        this.zzc = r0;
        this.zzd = "Task exception on worker thread";
        this.zza = r5;
        if (r0 != LongCompanionObject.MAX_VALUE) goto L6;
        r3.zzu.zzaW().zzb().zza("Tasks index overflow");
        return;
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object r7) {
        zzhx r72 = (zzhx) r7;
        boolean r0 = r72.zza;
        boolean r1 = this.zza;
        if (r1 == r0) goto L7;
        if (r1 == true) goto L6;
        return 1;
    L6:
        return -1;
    L7:
        long r02 = this.zzc;
        long r4 = r72.zzc;
        if (r02 >= r4) goto L10;
        return -1;
    L10:
        if (r02 <= r4) goto L12;
        return 1;
    L12:
        this.zzb.zzu.zzaW().zzc().zzb("Two tasks share the same index. index", Long.valueOf(r02));
        return 0;
    }

    @Override // java.util.concurrent.FutureTask
    public final void setException(Throwable r3) {
        this.zzb.zzu.zzaW().zzb().zzb(this.zzd, r3);
        super.setException(r3);
    }
}
