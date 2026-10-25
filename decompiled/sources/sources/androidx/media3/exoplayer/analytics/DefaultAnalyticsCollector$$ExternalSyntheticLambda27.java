package androidx.media3.exoplayer.analytics;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.analytics.AnalyticsListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda27 implements ListenerSet.Event {
    public final /* synthetic */ AnalyticsListener.EventTime f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Player.PositionInfo f$2;
    public final /* synthetic */ Player.PositionInfo f$3;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda27(AnalyticsListener.EventTime r1, int r2, Player.PositionInfo r3, Player.PositionInfo r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r4) {
        DefaultAnalyticsCollector.$r8$lambda$_PoTDKFsO54zbf7w82zCgY1yIbI(this.f$0, this.f$1, this.f$2, this.f$3, (AnalyticsListener) r4);
    }
}
