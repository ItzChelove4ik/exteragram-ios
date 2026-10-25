package androidx.media3.exoplayer.analytics;

import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda44 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda44(AnalyticsListener.EventTime r1, long r2, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r4) {
        DefaultAnalyticsCollector.$r8$lambda$kmGavx8Pgn_nN_ahC3jiF2xm5e0(this.f$0, this.f$1, this.f$2, (AnalyticsListener) r4);
    }
}
