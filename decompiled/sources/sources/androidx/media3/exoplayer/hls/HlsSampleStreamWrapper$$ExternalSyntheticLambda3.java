package androidx.media3.exoplayer.hls;

import androidx.media3.exoplayer.hls.HlsSampleStreamWrapper;

/* loaded from: classes4.dex */
public final /* synthetic */ class HlsSampleStreamWrapper$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ HlsSampleStreamWrapper.Callback f$0;

    public /* synthetic */ HlsSampleStreamWrapper$$ExternalSyntheticLambda3(HlsSampleStreamWrapper.Callback r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onPrepared();
    }
}
