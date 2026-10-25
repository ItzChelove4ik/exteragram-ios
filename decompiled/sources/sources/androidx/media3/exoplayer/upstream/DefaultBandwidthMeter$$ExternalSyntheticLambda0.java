package androidx.media3.exoplayer.upstream;

import androidx.media3.common.util.NetworkTypeObserver;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultBandwidthMeter$$ExternalSyntheticLambda0 implements NetworkTypeObserver.Listener {
    public final /* synthetic */ DefaultBandwidthMeter f$0;

    public /* synthetic */ DefaultBandwidthMeter$$ExternalSyntheticLambda0(DefaultBandwidthMeter r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.NetworkTypeObserver.Listener
    public final void onNetworkTypeChanged(int r1) {
        DefaultBandwidthMeter.$r8$lambda$pO_odySZ98SgTVF8TZTXVnU5Mq8(this.f$0, r1);
    }
}
