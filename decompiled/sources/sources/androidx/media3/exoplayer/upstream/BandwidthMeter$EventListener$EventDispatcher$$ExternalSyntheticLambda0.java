package androidx.media3.exoplayer.upstream;

import androidx.media3.exoplayer.upstream.BandwidthMeter;

/* loaded from: classes4.dex */
public final /* synthetic */ class BandwidthMeter$EventListener$EventDispatcher$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ BandwidthMeter$EventListener$EventDispatcher$$ExternalSyntheticLambda0(BandwidthMeter.EventListener.EventDispatcher.HandlerAndListener r1, int r2, long r3, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BandwidthMeter.EventListener.EventDispatcher.m847$r8$lambda$GKE2Ys_1MhNRoBQ2ySsnFXCbI(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
