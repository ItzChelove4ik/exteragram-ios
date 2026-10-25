package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Unit;
import kotlin.jvm.internal.Ref;

/* loaded from: classes4.dex */
public final class zzwz implements Runnable {
    final /* synthetic */ Ref.ObjectRef zza;
    final /* synthetic */ zzws zzb;
    final /* synthetic */ Runnable zzc;

    public zzwz(Ref.ObjectRef r1, zzws r2, Runnable r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zza.element);
        zzws r0 = this.zzb;
        Runnable r2 = this.zzc;
        zzwq r1 = zzvy.zzd();
        zzws r02 = zzvy.zzc(r1, r0);
        r2.run();     // Catch: Throwable -> L6
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L6
        zzvy.zzc(r1, r02);
        return;
    L6:
        th = move-exception;
        zzvu.zza(th);     // Catch: Throwable -> L9
        throw th;     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        zzvy.zzc(r1, r02);
        throw th;
    }

    public final String toString() {
        Runnable r2 = this.zzc;
        StringBuilder r1 = new StringBuilder(r2.toString().length() + 14);
        r1.append("propagating=[");
        r1.append(r2);
        r1.append("]");
        return r1.toString();
    }
}
