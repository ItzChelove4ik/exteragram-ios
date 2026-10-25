package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzot extends zzov {
    public static ListenableFuture zza(Object r1) {
        if (r1 != null) goto L6;
        return zzox.zza;
    L6:
        return new zzox(r1);
    }

    public static ListenableFuture zzb() {
        return zzox.zza;
    }

    public static ListenableFuture zzc(Throwable r1) {
        r1.getClass();
        return new zzow(r1);
    }

    public static ListenableFuture zzd(Callable r1, Executor r2) {
        zzpm r0 = new zzpm(r1);
        r2.execute(r0);
        return r0;
    }

    public static ListenableFuture zze(ListenableFuture r1, Class r2, zzoj r3, Executor r4) {
        int r0 = zzob.$r8$clinit;
        zzoa r02 = new zzoa(r1, r2, r3);
        r1.addListener(r02, zzpi.zzc(r4, r02));
        return r02;
    }

    public static ListenableFuture zzf(ListenableFuture r1, zzoj r2, Executor r3) {
        int r0 = zzoi.$r8$clinit;
        zzog r02 = new zzog(r1, r2);
        r1.addListener(r02, zzpi.zzc(r3, r02));
        return r02;
    }

    public static ListenableFuture zzg(ListenableFuture r1, zzks r2, Executor r3) {
        int r0 = zzoi.$r8$clinit;
        zzoh r02 = new zzoh(r1, r2);
        r1.addListener(r02, zzpi.zzc(r3, r02));
        return r02;
    }

    public static void zzh(ListenableFuture r1, zzor r2, Executor r3) {
        r1.addListener(new zzos(r1, r2), r3);
    }

    public static Object zzi(Future r1) {
        if (r1.isDone() == false) goto L15;
        boolean r0 = false;
    L17:
        Object r12 = r1.get();     // Catch: Throwable -> L9 InterruptedException -> L14
    L6:
        if (r0 == false) goto L8;
        Thread.currentThread().interrupt();
    L8:
        return r12;
    L14:
        r0 = true;
    L9:
        th = move-exception;
        if (r0 == false) goto L13;
        Thread.currentThread().interrupt();
    L13:
        throw th;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m(zzlb.zzd("Future was expected to be done: %s", new Object[]{r1}));
        return null;
    }
}
