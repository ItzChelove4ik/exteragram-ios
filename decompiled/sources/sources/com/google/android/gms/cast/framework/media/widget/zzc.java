package com.google.android.gms.cast.framework.media.widget;

/* loaded from: classes4.dex */
final /* synthetic */ class zzc implements Runnable {
    private final /* synthetic */ CastSeekBar zza;

    public /* synthetic */ zzc(CastSeekBar r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        this.zza.sendAccessibilityEvent(4);
    }
}
