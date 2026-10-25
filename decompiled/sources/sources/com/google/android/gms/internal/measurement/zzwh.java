package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final /* synthetic */ class zzwh implements Runnable {
    static final /* synthetic */ zzwh zza = null;

    static {
        zza = new zzwh();
    }

    private /* synthetic */ zzwh() {
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        throw new IllegalStateException("Span was closed by an invalid call to SpanEndSignal.run()");
    }
}
