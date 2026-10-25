package androidx.media3.exoplayer.analytics;

import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultAnalyticsCollector$$ExternalSyntheticLambda11 implements ListenerSet.IterationFinishedEvent {
    public final /* synthetic */ DefaultAnalyticsCollector f$0;
    public final /* synthetic */ Player f$1;

    public /* synthetic */ DefaultAnalyticsCollector$$ExternalSyntheticLambda11(DefaultAnalyticsCollector r1, Player r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
    public final void invoke(Object r2, FlagSet r3) {
        DefaultAnalyticsCollector.$r8$lambda$NazTkydGfX6jUSUhgOk3tFa3GFY(this.f$0, this.f$1, (AnalyticsListener) r2, r3);
    }
}
